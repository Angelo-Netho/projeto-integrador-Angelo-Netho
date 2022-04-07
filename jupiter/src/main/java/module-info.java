module dev.netho.jupiter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mail;

    opens dev.netho.jupiter to javafx.fxml;
    exports dev.netho.jupiter;
    exports dev.netho.jupiter.services;
    exports dev.netho.jupiter.controller;
    opens dev.netho.jupiter.controller to javafx.fxml;
}