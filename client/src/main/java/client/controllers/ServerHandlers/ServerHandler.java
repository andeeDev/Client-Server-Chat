package client.controllers.ServerHandlers;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.stage.Stage;

public abstract class ServerHandler {

     public abstract void handle(JsonNode message, Stage stage);

}
