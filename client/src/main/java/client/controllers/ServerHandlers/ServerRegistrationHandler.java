package client.controllers.ServerHandlers;


import com.fasterxml.jackson.databind.JsonNode;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ServerRegistrationHandler extends ServerHandler {

    private static final Logger logger = LogManager.getLogger(ServerRegistrationHandler.class);

    @Override
    public void handle(JsonNode message, Stage stage){
        String responseStr = message.get("response").textValue();


        if(responseStr.equals("ok")){
            Platform.runLater( () -> {

                JFXTextField textField = (JFXTextField) stage.getScene().getRoot().lookup("#loginField");
                String userName = textField.getText();

                System.out.println();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource(("/client/views/Chat.fxml")));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Scene scene = new Scene(root, 800, 500);
                //System.out.println("fakjlj");
                stage.setScene(scene);
                Label label = (Label) stage.getScene().getRoot().lookup("#usernameLabel");
                label.setText(userName);
                //System.out.println("kjskdf");
            });
        } else {
            Platform.runLater( () -> {
                Label label = (Label) stage.getScene().getRoot().lookup("#errorMessange");
                label.setText("server denied access");
                label.setVisible(true);
            });
        }

    }
}
