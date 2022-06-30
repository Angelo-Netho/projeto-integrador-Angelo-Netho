module dev.netho.jupiter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mail;
    requires itextpdf;

    exports dev.netho.jupiter;
    exports dev.netho.jupiter.services;
    exports dev.netho.jupiter.models;
    exports dev.netho.jupiter.repositories;
    exports dev.netho.jupiter.daos.interfaces;
    exports dev.netho.jupiter.controller;
    opens dev.netho.jupiter.controller to javafx.fxml;
}