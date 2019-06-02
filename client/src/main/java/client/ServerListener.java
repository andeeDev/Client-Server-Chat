package client;

import client.controllers.ServerHandlers.ServerAuthenticationHandler;
import client.controllers.ServerHandlers.ServerHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ServerListener implements Runnable {//
    private static final Logger logger = LogManager.getLogger(ServerAuthenticationHandler.class);
    private Socket socket;
    private Stage stage;
    private BufferedReader bufferedReader;

    public ServerListener(Stage stage, BufferedReader bf){
        this.stage = stage;
        this.bufferedReader = bf;
    }


    @Override
    public void run(){
        JsonNode message;
        ObjectMapper objectMapper = new ObjectMapper();
        String status;
        String controllerName;
        DIFactoryHandlers diFactory = DIFactoryHandlers.getInstance();
        while(!Thread.currentThread().isInterrupted()){
            try {
                String readMessage = bufferedReader.readLine();
                if(readMessage != null){
                    System.out.println("readMessage" + readMessage);
                    logger.info("readMessage" + readMessage);
                    message = objectMapper.readTree(readMessage);
                    status = message.get("status").textValue();
                    controllerName = "client.controllers.ServerHandlers.Server" + status.toUpperCase().toCharArray()[0] +
                                    status.toLowerCase().substring(1) + "Handler";
                    ServerHandler serverHandler = diFactory.getObject(controllerName);

                     synchronized (stage) {
                        serverHandler.handle(message, stage);
                        stage.notify();
                    }
                }

            } catch(IOException ex){
                System.out.println(ex);
            }
        }

    }
}
