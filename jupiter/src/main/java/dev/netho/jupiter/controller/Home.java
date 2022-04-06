package dev.netho.jupiter.controller;

import dev.netho.jupiter.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class Home {

    @FXML
    private StackPane centralPanel;

    public void initialize() {
        loadLogin();
    }

    private void loadLogin() {
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadTela("/dev/netho/fxml/login.fxml", (o) -> new Login()));
    }

}
