package client;

import client.controllers.ServerHandlers.ServerHandler;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;

public class DIFactoryHandlers {
    private static DIFactoryHandlers diFactory;
    private static HashMap<String, ServerHandler> map = new HashMap<>();

    static {

        Reflections reflections = new Reflections("client.controllers.ServerHandlers");
        Set<Class<? extends ServerHandler>> subTypes = reflections.getSubTypesOf(ServerHandler.class);
        for (Iterator<Class<? extends ServerHandler>> iterable = subTypes.iterator(); iterable.hasNext();) {
            Class c = iterable.next();
            ClassLoader classLoader = DIFactoryHandlers.class.getClassLoader();
            try {
                Class aClass = classLoader.loadClass(c.getName());
                ServerHandler serverHandler = (ServerHandler)(aClass.getConstructors()[0]).newInstance();
                map.put(aClass.getName(), serverHandler);


            } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | IllegalAccessException  e) {
                e.printStackTrace();
            }
        }
        for (Map.Entry<String, ServerHandler> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    }
    public static DIFactoryHandlers getInstance(){
        if(diFactory != null){
            return diFactory;
        } else {
            diFactory = new DIFactoryHandlers();
            return diFactory;
        }
    }

    ServerHandler getObject(String className){
        return map.get(className);
    }
}
