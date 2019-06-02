package client.controllers.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserslistHandler extends Handler{

    @Override
    public void handle(ActionEvent event) {
        Map<String, String> hashTable = new HashMap<>();
        hashTable.put("status", "userslist");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(hashTable);
            sendMessage(jsonString);

        } catch (IOException ex){
            System.out.println(ex);
        }

    }
}
