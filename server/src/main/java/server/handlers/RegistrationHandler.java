package server.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import server.ClientThread;
import server.Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class RegistrationHandler extends Handler {

    private static final Logger logger = LogManager.getLogger(RegistrationHandler.class);

    @Override
    public void handle(ClientThread clientThread, JsonNode userJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> dt = new Hashtable<>();
        dt.put("status", "registration");
        dt.put("response", "ok");
        clientThread.setLogin(userJson.get("login").textValue());
//        if (validateJson(userJson)){
//            System.out.println("Json validation is good");
//        } else {
//            System.out.println("Json not valid");
//        }
        /*dt.put("status", "registration");
        dt.put("login", login.getText());
        dt.put("password",  password.getText());
         */

        try {
            String jsonString = objectMapper.writeValueAsString(dt);
            clientThread.sendMessage(jsonString);
        } catch(IOException ex){
            System.out.println(ex);
        }


    }

}
