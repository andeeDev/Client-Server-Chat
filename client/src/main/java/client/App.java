package client;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.*;
import java.net.Socket;

public class App extends Application  {


    public static Stage stage;

    public static Stage getStage() {
        return stage;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;


        try {
            Socket socket = new Socket("localhost", 9010);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ServerListener serverListener = new ServerListener(stage, new BufferedReader(new InputStreamReader(socket.getInputStream())));
            DIFactoryServerHandlers diFactoryHandlers = DIFactoryServerHandlers.getInstance();
            diFactoryHandlers.invoke(bufferedWriter);
            Thread thread = new Thread(serverListener);
            thread.setDaemon(true);
            thread.start();
        } catch(IOException ex){
            //closeAplication();
        }
        Parent root = FXMLLoader.load(getClass().getResource("/client/views/Registration.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}