package client.controllers.handlers;

import client.controllers.Transport;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import client.Utils.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public abstract class Handler implements Transport {

    public abstract void handle(ActionEvent event);

    public static EventHandler<ActionEvent> dispatcherHandler = (event) -> {
        event.getTarget();
    };

    public void loader(ActionEvent event, String path){
        Stage stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            stage.setTitle("Hello World");
            stage.setScene(new Scene(root, 800, 500));
            stage.show();
        } catch (IOException ex){
            System.out.println(ex);
        }
    }

    public boolean writeJsonToStrream(Map<String, String> map){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(map);
            sendMessage(jsonString);
            System.out.println(jsonString);

        } catch (IOException ex){
            System.out.println(ex);
            return false;
        }
        return true;
    }

}
