package server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import java.io.*;
import java.net.Socket;
import java.util.*;
import static server.Server.clientsThreads;

public class ClientThread implements Runnable, Transport {
    private String login;
    private boolean isOnline;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private static final Logger logger = LogManager.getLogger(ClientThread.class);

    public  Map<Integer, ClientThread> getMap(){
        return clientsThreads;
    }

    ClientThread(Socket socket) {

        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            logger.info("Created I/O stream for connection with clients");
        } catch (IOException ex) {
            logger.error("Error during  initialization of streams " + ex);
        }
    }

    public void run() {
        DIFactory diFactory = DIFactory.getInstance();
        logger.info("Invoked DIFactory");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String string = bufferedReader.readLine();

                if (string != null) {


                    ObjectMapper objectMapper = new ObjectMapper();

                    JsonNode message = objectMapper.readTree(string);
                    if(validateJson(message)) {
                        logger.info("Message is valid: " + message );
                        String status = message.get("status").textValue();
                        logger.info(status.toUpperCase().toCharArray()[0] + status.toLowerCase().substring(1) + "Handler" + " was invoked");
                        diFactory.getHandler(status.toUpperCase().toCharArray()[0] + status.toLowerCase().substring(1) + "Handler").handle(this, message);
                    } else {
                        logger.info("Server receive message. Message is not valid: " + message);
                        logger.error("Message is not valid: " + message);
                    }
                }
            } catch (IOException ex) {
               logger.error("Thread is interapted" + ex);
                Thread.currentThread().interrupt();
            }


        }


    }
    public boolean validateJson(JsonNode userJson){
        try (BufferedReader schemaString = new BufferedReader(new FileReader("server/src/main/java/server/JsonSchemas/common"))) {
            StringBuilder statusBuilder = new StringBuilder();
            String statusbuf;
            while((statusbuf = schemaString.readLine()) != null){
                statusBuilder.append(statusbuf);
            }
            String StringSchema = statusBuilder.toString();
            SchemaLoader loader = SchemaLoader.builder()
                    .schemaJson(new JSONObject(StringSchema))
                    .draftV7Support() // or draftV7Support()
                    .build();
            Schema schema = loader.load().build();

            try {
                schema.validate(new JSONObject(userJson.toString()));
                logger.info("Json validation was successful");
            } catch (ValidationException val) {
                return  false;
            }
        } catch (IOException ex){
            logger.info("file with validation schema has not been found");
        }
        return true;
    }

    public void sendMessage(String string){
        try {

            bufferedWriter.write(string + "\n");
            bufferedWriter.flush();
            logger.info("Message " + string  + " was sent");
        } catch (IOException exception){
            logger.error("Exception while sending message" + exception);
//                closeAplication();
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}

