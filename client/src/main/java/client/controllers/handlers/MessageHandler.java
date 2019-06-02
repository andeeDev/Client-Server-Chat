package client.controllers.handlers;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class MessageHandler extends Handler {

    private static final Logger logger = LogManager.getLogger(MessageHandler.class);

    @Override
    public void handle(ActionEvent event) {
        Platform.runLater(()->{
            Stage stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
            TextArea textArea = (TextArea)stage.getScene().getRoot().lookup("#messageBox");
            JFXListView jfxList = (JFXListView)stage.getScene().getRoot().lookup("#messengerList");
            Map<String, String> map = new HashMap<>();
            String string = textArea.getText().trim();
            if(!"".equals(string)) {
                jfxList.getItems().addAll(string);
                map.put("status", "message");
                int idUser = Integer.parseInt(((Node) (((JFXListView) stage.getScene().lookup("#usersList"))
                        .getSelectionModel().getSelectedItem())).getId());
                map.put("to", String.valueOf(idUser));
                map.put("body", string);
                writeJsonToStrream(map);
            }
        });


    }
}
