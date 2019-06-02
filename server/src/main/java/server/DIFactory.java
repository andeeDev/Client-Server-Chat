package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import server.handlers.Handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DIFactory {
    private static HashMap<String, Class> map = new HashMap<>();
    private static DIFactory instance = new DIFactory();
    private static final Logger logger = LogManager.getLogger(DIFactory.class);

    static {
        Reflections reflections = new Reflections("server.handlers");
        Set<Class<? extends Handler>> subTypes = reflections.getSubTypesOf(Handler.class);
        for (Iterator<Class<? extends Handler>> iterable = subTypes.iterator(); iterable.hasNext();) {
            Class c = iterable.next();
            ClassLoader classLoader = ClientThread.class.getClassLoader();
            try {
                Class aClass = classLoader.loadClass(c.getName());
                map.put(aClass.getName(), aClass);
            } catch (ClassNotFoundException e) {
                logger.error("Exception during class reflection" + e);
            }
        }
    }

    private DIFactory() {}

    public static DIFactory getInstance() {
        return instance;
    }

    public Handler getHandler(String claz) {
        String clazz = "server.handlers." + claz;
        try {
            logger.info("App get  " + map.get(clazz) + "handler");
//            for(Map.Entry entry : map.entrySet()){
//               //logger.info(entry.getKey() + " " + entry.getValue());
//                //System.out.println(entry.getKey() + " " + entry.getValue());
//            }
            Constructor constructor =  map.get(clazz).getConstructors()[0];
            Handler handler = (Handler) constructor.newInstance();
            return handler;
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException  ex){
            logger.error(ex);
        }
        return null;
    }


}
