package server;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class Group {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    List<ClientThread> list = new ArrayList<>();

    Group(String name){
        this.name = name;
    }

    Group(ClientThread clientThread, String name){
        this.name = name;
        list.add(clientThread);
    }

    void addUser(ClientThread clientThread){
        list.add(clientThread);
    }

    void sendToEveryOneInGroup(JsonNode jsonNode) {

        for(ClientThread t : list){
            t.sendMessage(jsonNode.toString());
        }
    }



}
