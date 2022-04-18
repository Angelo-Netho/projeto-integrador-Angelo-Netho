package dev.netho.jupiter.controller;

import dev.netho.jupiter.Main;
import dev.netho.jupiter.models.Psychologist;
import dev.netho.jupiter.repositories.PatientRepository;
import dev.netho.jupiter.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class Home {

    @FXML
    private StackPane centralPanel;

    private final AuthService authService;

    private final PatientRepository patientRepository;

    public Home(AuthService authService, PatientRepository patientRepository) {
        this.authService = authService;
        this.patientRepository = patientRepository;
    }

    public void initialize() {
        update();
    }

    public void update() {
        if(!authService.loggedIn()) {
            loadScreen(Screen.LOGIN);
        }else {
            if(authService.getProfile() instanceof Psychologist) {
                loadScreen(Screen.PSYCHOLOGIST_HOME);
            } else {
                loadScreen(Screen.PATIENT_HOME);
            }
        }
    }

    public void loadScreen(Screen screen) {

        if (screen == Screen.LOGIN) {
            centralPanel.getChildren().clear();
            centralPanel.getChildren().add(Main.loadTela(screen.getSource(), (o) -> new Login(authService, this)));
        }

        if (screen == Screen.PSYCHOLOGIST_HOME) {
            centralPanel.getChildren().clear();
            centralPanel.getChildren().add(Main.loadTela(screen.getSource(), (o) -> new HomePsychologist(authService)));
        }

        if (screen == Screen.PATIENT_HOME) {
            centralPanel.getChildren().clear();
            centralPanel.getChildren().add(Main.loadTela(screen.getSource(), (o) -> new HomePatient(authService, this, patientRepository)));
        }
    }

}
