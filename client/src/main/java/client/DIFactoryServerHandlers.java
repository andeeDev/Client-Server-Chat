package client;

import client.controllers.handlers.Handler;
import org.reflections.Reflections;

import java.io.BufferedWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DIFactoryServerHandlers {
    private final static DIFactoryServerHandlers diFactory = new DIFactoryServerHandlers();
    private static HashMap<String, Handler> map = new HashMap<>();
    private BufferedWriter bufferedWriter;

    public void invoke(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
        Reflections reflections = new Reflections("client.controllers.handlers");
        Set<Class<? extends Handler>> subTypes = reflections.getSubTypesOf(Handler.class);
        for (Iterator<Class<? extends Handler>> iterable = subTypes.iterator(); iterable.hasNext();) {
            Class c = iterable.next();
            ClassLoader classLoader = DIFactoryHandlers.class.getClassLoader();
            try {
                Class aClass = classLoader.loadClass(c.getName());
                Handler Handler = (Handler)(aClass.getConstructors()[0]).newInstance();
                map.put(aClass.getName(), Handler);


            } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | IllegalAccessException  e) {
                e.printStackTrace();
            }
        }
        for (Map.Entry<String, Handler> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    }


    public static DIFactoryServerHandlers getInstance(){
        return diFactory;
    }

    public Handler getObject(String className){
        return map.get(className);
    }



}
