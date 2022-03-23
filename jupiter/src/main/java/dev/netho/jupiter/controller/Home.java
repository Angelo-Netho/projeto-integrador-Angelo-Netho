package dev.netho.jupiter.controller;

import dev.netho.jupiter.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

public class Home {

    @FXML
    private StackPane centralPanel;


    public void initialize() {
        loadLogin();
    }

    private void loadLogin() {
        loadView(centralPanel, "view/login.fxml", aClass -> new Login());

    }

    private void loadView(StackPane stackPane, String reference,  Callback controller) {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(Main.loadTela(reference, (o) -> controller));
    }
}
