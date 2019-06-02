package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    //public static List<ClientThread> clientThreads = new ArrayList<>();


    public static Map<Integer, ClientThread> clientsThreads = new HashMap<>();
    //public static Map<Integer, String> clientsLogins = new HashMap<>();
    public static Map<Integer, Group> groupsList = new HashMap<>();//singlton synchronized
    //syn
    private static final Logger logger = LogManager.getLogger(Server.class);

    public static void main(String [] args){
        logger.info("Server has started");
        groupsList.put(0, new Group("Main"));
        int numberOfGroups = 1;
        int i = 0;

        try {
            ServerSocket serverSocket = new ServerSocket(9010);
            while(true) {
                    Socket socket = serverSocket.accept();
                    ClientThread clientThread = new ClientThread(socket);
                    Thread thread = new Thread(clientThread);
                    //clientThreads.add(clientThread);
                    clientsThreads.put(i++, clientThread);
                    groupsList.get(0).addUser(clientThread);
                    thread.start();
                    logger.info("new client added");
            }
        } catch (IOException ex) {
            logger.error(ex);
        }
    }
}
