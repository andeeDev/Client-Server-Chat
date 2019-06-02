package client.controllers.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GroupslistHandler extends Handler {


    @Override
    public void handle(ActionEvent event) {
        HashMap <String, String>  hashMap= new HashMap();
        hashMap.put("status", "groupslist");
        writeJsonToStrream(hashMap);
    }


}
