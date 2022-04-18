package dev.netho.jupiter.controller;

import dev.netho.jupiter.services.AuthService;
import javafx.scene.control.Label;

public class HomePsychologist {

    public Label lblWelcome;
    private AuthService authService;

    public HomePsychologist(AuthService authService) {
        this.authService = authService;
    }

    public void initialize() {
        lblWelcome.setText("Olá de volta, ADMIN " + authService.getProfile().getName() + "!");
    }


}
