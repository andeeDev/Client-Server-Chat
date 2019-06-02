package server.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import server.ClientThread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public abstract class Handler {
    final  static Logger logger = Logger.getLogger(Handler.class.getName());
    public abstract void handle(ClientThread clientThread, JsonNode jsonString);

    /*boolean validateJson(JsonNode userJson){
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
                System.out.println("Json validation was successful");
            } catch (ValidationException val) {
                return  false;
            }
        } catch (IOException ex){
            System.out.println("file with validation schema has not been found");
        }
        return true;
    }*/
}
