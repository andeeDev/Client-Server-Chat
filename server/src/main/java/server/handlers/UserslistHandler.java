package server.handlers;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.ClientThread;
import server.Server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UserslistHandler extends Handler{

    private static final Logger logger = LogManager.getLogger(UserslistHandler.class);


    @Override
    public void handle(ClientThread clientThread, JsonNode jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> hashMap = new HashMap();

        hashMap.put("status", "userslist");
//        for(Map.Entry<Integer,ClientThread> map : clientThread.getUserList().entrySet()){
//            hashMap.put(map.getKey(), map.getValue());
//        }
        Map mapNew = new HashMap();
        for(Map.Entry<Integer, ClientThread> map : clientThread.getMap().entrySet()){
            if(map.getValue().getLogin() != null && !map.getValue().equals(clientThread)){
                mapNew.put(map.getKey(), map.getValue().getLogin());
            }
        }

        try {
            hashMap.put("usersmap", mapNew);
            String jsonStrin = objectMapper.writeValueAsString(hashMap);
            clientThread.sendMessage(jsonStrin);
            logger.info("Messenge was sent " + jsonStrin);
        } catch(IOException ex){
            logger.error("Send message error  -  " + ex);
            //ex.printStackTrace();
            //System.out.println(ex);
        }

    }

}
