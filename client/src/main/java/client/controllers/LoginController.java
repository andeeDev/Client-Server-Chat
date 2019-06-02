package client.controllers;



//import javafx.fxml.FXML;


import client.controllers.handlers.AuthenticationHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class LoginController  {


    @FXML
    private Text ErrorText;

    @FXML
    private JFXButton Register;

    @FXML
    private Text ErrorD;

    @FXML
    private JFXTextField Username;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXButton loginButton;


    @FXML
    private JFXButton back;

    @FXML
    private ImageView BackToSystem;

    @FXML
    void initialize() {

        loginButton.setOnAction( event -> { new AuthenticationHandler().handle(event); });
        BackToSystem.setOnMouseClicked(event -> { System.exit(0);});
        back.setOnAction( event -> {System.exit(0);});
        //Register.setOnAction(event -> { new AuthenticationHandler().loader(event, "/client/views/Registration.fxml");});
    }
}
