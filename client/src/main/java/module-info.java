module client {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.jfoenix;
//    requires json.simple;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires reflections;

    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;

    exports client.controllers;
    opens client.controllers;

    opens client;
}