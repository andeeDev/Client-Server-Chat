package client.controllers;




import java.io.IOException;

import client.DIFactoryServerHandlers;
import client.controllers.handlers.RegistrationHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class RegistrationController extends PrimaryController {

    @FXML
    private Label errorMessange;

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXTextField passwordField;

    @FXML
    private JFXButton confirm;

    @FXML
    private JFXButton alreadyAMember;

    @FXML
    void initialize() {
        DIFactoryServerHandlers.getInstance();
        errorMessange.setVisible(false);
        confirm.setOnAction(e -> {(new RegistrationHandler()).handle(e);});
        alreadyAMember.setOnAction(e->{
            Stage stage = (Stage)(((Node)e.getSource()).getParent().getScene().getWindow());
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(("/client/views/Login.fxml")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Scene scene = new Scene(root, 800, 500);
            stage.setScene(scene);


        });


    }

}





