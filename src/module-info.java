module javaproje {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires com.jfoenix;
    requires java.sql;
    opens JavaProje;
    opens JavaProje.controller;
    opens JavaProje.model;
    opens JavaProje.views;
    opens JavaProje.Database;
}