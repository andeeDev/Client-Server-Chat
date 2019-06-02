package client.controllers;


import client.controllers.handlers.GroupslistHandler;
import client.controllers.handlers.MessageHandler;
import client.controllers.handlers.UserslistHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleNode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.*;


public class Chat {


    @FXML
    private BorderPane borderPane;

    @FXML
    private JFXListView<String> messengerList;

    @FXML
    private TextArea messageBox;

    @FXML
    private Button buttonSend;

    @FXML
    private HBox onlineUsersHbox;

    @FXML
    private Button groupsButton;

    @FXML
    private Button usersButton;

    @FXML
    private JFXListView<String> usersList;

    @FXML
    private ImageView unnecessaryButton;

    @FXML
    private JFXToggleNode add;

    @FXML
    private ImageView userImageView;

    @FXML
    private Label usernameLabel;

    ObjectMapper objectMappper = new ObjectMapper();
    void updateUsers(){
        Map<String, String> hashTable = new HashMap<>();
        hashTable.put("status", "userslist");

    }

    @FXML
    void initialize() {
        new UserslistHandler().handle(new ActionEvent());

        //usersButton.setOnAction(Handler.dispatcherHandler);

        usersButton.setOnAction(event -> {
            System.out.println(event.getTarget());
            new UserslistHandler().handle(event);
        });

        buttonSend.setOnAction(event -> {
            new MessageHandler().handle(event);
        });

        groupsButton.setOnAction( event -> {
            new GroupslistHandler().handle(event);
        });

    }

}