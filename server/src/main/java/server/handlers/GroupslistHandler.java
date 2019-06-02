package server.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ClientThread;
import server.Group;
import server.Server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static server.Server.*;



public class GroupslistHandler extends Handler {
//stic import correct singleton
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(ClientThread clientThread, JsonNode jsonString) {

        String node = jsonString.get("status").textValue();
        logger.info("1111" + jsonString);
        logger.info("1111" + node);

        HashMap<String, Object> hm = new HashMap<>();
        hm.put("status",  jsonString.get("status").textValue());
        Map mapNew = new HashMap();
        for(Map.Entry<Integer, Group> map : groupsList.entrySet()){
            mapNew.put(map.getKey(), map.getValue().getName());
        }

        hm.put("groupsmap", mapNew);
        try {
            String json = objectMapper.writeValueAsString(hm);
            clientThread.sendMessage(json);

        } catch(IOException ex){
            System.out.println(ex);
        }

        //clientThread.sendMessage();
    }
}
