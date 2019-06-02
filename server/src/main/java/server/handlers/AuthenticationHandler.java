package server.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ClientThread;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class AuthenticationHandler extends Handler {
    @Override
    public void handle(ClientThread clientThread, JsonNode jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> dt = new Hashtable();
        dt.put("status", "Authentication");
        dt.put("response", "ok");
        clientThread.setOnline(true);
        clientThread.setLogin(jsonString.get("login").textValue());
        logger.info("login = " + clientThread.getLogin());
        logger.info(jsonString.toString());


//        if (validateJson(jsonString)){
//            System.out.println("Json validation is good");
//        } else {
//            System.out.println("Json is not valid");
//        }

        /*dt.put("status", "registration");
        dt.put("login", login.getText());
        dt.put("password",  password.getText());
         */

        try {
            String jsonStrin = objectMapper.writeValueAsString(dt);
            clientThread.sendMessage(jsonStrin);
        } catch(IOException ex){
            logger.info(ex.getMessage());
        }
    }
}
