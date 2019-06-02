package client.controllers.ServerHandlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.apache.logging.log4j.*;
public class ServerMessageHandler extends ServerHandler {

    private static final Logger logger = LogManager.getLogger(ServerMessageHandler.class);

    @Override
    public void handle(JsonNode message, Stage stage) {
        Platform.runLater(() -> {




            logger.info("Start");
            JFXListView messengesList = (JFXListView)stage.getScene().lookup("#messengerList");

            JFXListView jfxListView = (JFXListView)stage.getScene().getRoot().lookup("#usersList");

            int idOfSelectionModel = Integer.parseInt(((ServerUserslistHandler.HBoxCell) (jfxListView.getSelectionModel().getSelectedItem())).getId());
            int fromUserId = Integer.parseInt(message.get("from").textValue());


            logger.info(messengesList.getItems());
            logger.info(message.get("body").textValue());

            String user = ((ServerUserslistHandler.HBoxCell)jfxListView.getSelectionModel().getSelectedItem()).getUserName();

            messengesList.getItems().addAll(user + " : " + message.get("body").textValue());
            logger.info("end");
        });

    }
}
