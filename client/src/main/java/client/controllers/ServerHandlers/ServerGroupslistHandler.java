package client.controllers.ServerHandlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ServerGroupslistHandler extends ServerHandler {

    private static final Logger logger = LogManager.getLogger(ServerGroupslistHandler.class);
    ObjectMapper objectmapper = new ObjectMapper();


    public static class HBoxCell extends HBox {
        Image avatar = new Image(getClass().getResource("/client/res/team.png").toExternalForm(),
                50,50,false,false);
        ImageView imageView = new ImageView(avatar);
        Label label = new Label();
        StackPane stackPane = new StackPane();
        HBoxCell(String name, int id){
            super();
            label.setText(name);
            this.setId(String.valueOf(id));
            //this.getChildren().add(avatar);
            //hbox.setPadding(new Insets(2));
            this.getChildren().addAll(imageView, label);
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);
        }
        String getUserName(){
            return label.getText();
        }

    }

    @Override
    public void handle(JsonNode message, Stage stage) {
        Platform.runLater( () -> {
            JFXListView list = (JFXListView)stage.getScene().getRoot().lookup("#usersList");

            logger.info("1111" + message);
            Map<String, Object> result = objectmapper.convertValue(message.get("groupsmap"), Map.class);
            ObservableList observableList = FXCollections.observableArrayList();
            for(Map.Entry map : result.entrySet()){
                observableList.add(new HBoxCell(String.valueOf(map.getValue()) , Integer.parseInt(String.valueOf(map.getKey()))));

            }
            list.getItems().clear();
            list.getItems().addAll(observableList);
        });
    }
}
