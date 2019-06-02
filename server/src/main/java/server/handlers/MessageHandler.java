package server.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ClientThread;
import server.Group;
import server.Server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessageHandler extends Handler {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(ClientThread clientThread, JsonNode jsonNode) {
        String id = jsonNode.get("to").textValue();
        String text = jsonNode.get("body").textValue();
        ClientThread client = clientThread.getMap().get(Integer.parseInt(id));
        Map map = new HashMap<>();
        map.put("status", "message");

        int key = -1;
        for (Map.Entry<Integer, ClientThread> entry : clientThread.getMap().entrySet()) {
                if (!clientThread.equals(entry.getValue())) {
                    key = entry.getKey();
                    break;
                }
        }
        if(key != -1) {
            map.put("from", String.valueOf(key));
            map.put("body", text);
        }

        try {
            String jsonString = objectMapper.writeValueAsString(map);
            client.sendMessage(jsonString);

        } catch(IOException ex){
            System.out.println(ex);
        }

        //client.sendMessage(text);

        //ClientThread ct = Server.clientsThreads.get(idUser);
        //if (ct != null) {
            //ct.sendMessage();
        //}


    }
}
