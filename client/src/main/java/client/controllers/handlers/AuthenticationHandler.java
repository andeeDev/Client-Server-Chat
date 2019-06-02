package client.controllers.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class AuthenticationHandler extends Handler{

    BufferedWriter bufferedWriter;


    public AuthenticationHandler() {
    }

    public AuthenticationHandler(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }


    @Override
    public void handle(ActionEvent event) {
        JFXTextField login = (JFXTextField)((Node)event.getTarget()).getScene().getRoot().lookup("#Username");
        JFXTextField password = (JFXTextField)((Node)event.getTarget()).getScene().getRoot().lookup("#password");

        String loginText = login.getText().trim();
        String passwordText = password.getText().trim();
        if( !loginText.equals("") && !passwordText.equals("")){
            Map<String,String> dt = new Hashtable();
            dt.put("status", "authentication");
            dt.put("login",loginText );
            dt.put("password",  passwordText);
            if(writeJsonToStrream(dt)) {
                freezeScene(event);
            }
            System.out.println("login = "+ login.getText());
            System.out.println("password = "+ password.getText());
        }

    }


    void freezeScene(ActionEvent event){
        Stage stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
        synchronized (stage) {
            try {
                stage.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
