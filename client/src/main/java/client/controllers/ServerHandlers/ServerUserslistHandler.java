package client.controllers.ServerHandlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class ServerUserslistHandler extends ServerHandler {

    private static final Logger logger = LogManager.getLogger(ServerUserslistHandler.class);

    @FXML
    ListView<String> userList;

    public static class HBoxCell extends HBox {
        Image avatar = new Image(getClass().getResource("/client/res/user.png").toExternalForm(),
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

        ObjectMapper objectmapper = new ObjectMapper();
        System.out.println(message.get("usersmap").toString());
        Iterator<JsonNode> usersmap = message.get("usersmap").iterator();
        Map<String, Object> result = objectmapper.convertValue(message.get("usersmap"), Map.class);

        JFXListView jfxListView = (JFXListView)stage.getScene().lookup("#usersList");
        Platform.runLater(() -> {
            List<HBoxCell> list = new LinkedList<>();

            for(Map.Entry<String, Object> map : result.entrySet()){
                //System.out.println("key = " + map.getKey() + "value = " + map.getValue());
                list.add(new HBoxCell( (String)map.getValue(),  Integer.parseInt((String)map.getKey())));
            }
            jfxListView.getItems().clear();

            //list.add(new StackPane())
            jfxListView.getItems().addAll(FXCollections.observableArrayList(list));
            jfxListView.getSelectionModel().select(0);
            Node node = (Node)jfxListView.getSelectionModel().getSelectedItem();
            //System.out.println(node.getId());
            Image avatar = new Image(getClass().getResource("/client/res/user.png").toExternalForm(),
                    30,30,false,false);
            //userList.setItems(FXCollections.observableArrayList( "salmon", "gold"));
            new HBox();



//            jfxListView.setCellFactory(param ->  new ListCell<String>() {
//                ImageView img = new ImageView();
//                public void  updateItem(String name, boolean empty){
//                    super.updateItem(name, empty);
//                    if(name != null) {
//                        setText(name);
//                        img.setImage(avatar);
//                        setGraphic(img);
//                    }else {
//                        setGraphic(null);
//                    }
//                }
//            });



        });
    }
}
