package client.controllers.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class RegistrationHandler extends Handler  {

    @Override
    public void handle(ActionEvent event){
        JFXTextField login = (JFXTextField)((Node)event.getTarget()).getScene().getRoot().lookup("#loginField");
        JFXTextField password = (JFXTextField)((Node)event.getTarget()).getScene().getRoot().lookup("#passwordField");
        Label errorMessange = (Label)((Node)event.getTarget()).getScene().getRoot().lookup("#errorMessange");
        Stage stage = (Stage)((Node)event.getTarget()).getScene().getWindow();

        //Stage stage = (Stage)(((Node)event.getSource()).getParent().getScene().getWindow());

        if(!login.getText().trim().equals("") && !password.getText().trim().equals("") ) {

            String[] keys = new String[]{"status", "login", "password"};
            String[] values = new String[]{"registration", login.getText(), password.getText()};

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,String> dt = new Hashtable();
            dt.put("status", "registration");
            dt.put("login", login.getText());
            dt.put("password",  password.getText());


            ObjectMapper object = new ObjectMapper();
            try {
                String jsonString = objectMapper.writeValueAsString(dt);
                sendMessage(jsonString);
                System.out.println(jsonString);

            } catch (IOException ex){
                System.out.println(ex);
            }
            synchronized (stage) {
                try {
                    stage.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
//            synchronized (stage) {
//                try {
//                    stage.wait();
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }

        } else {
            errorMessange.setVisible(true);
        }

    }

}
