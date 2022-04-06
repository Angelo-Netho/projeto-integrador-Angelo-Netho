package dev.netho.jupiter.controller;

import dev.netho.jupiter.Main;
import dev.netho.jupiter.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class Home {

    @FXML
    private StackPane centralPanel;

    private final AuthService authService;

    public Home(AuthService authService) {
        this.authService = authService;
    }

    public void initialize() {
        update();
    }

    public void update() {
        if(!authService.loggedIn()) {
            loadScreen(Screen.LOGIN);
        }else {
            loadScreen(Screen.DASHBOARD);
        }
    }

    public void loadScreen(Screen screen) {

        if (screen == Screen.LOGIN) {
            centralPanel.getChildren().clear();
            centralPanel.getChildren().add(Main.loadTela(screen.getSource(), (o) -> new Login(authService, this)));
        }

        if (screen == Screen.DASHBOARD) {
            centralPanel.getChildren().clear();
            centralPanel.getChildren().add(Main.loadTela(screen.getSource(), (o) -> new HomeAdmin()));
        }
    }

}
