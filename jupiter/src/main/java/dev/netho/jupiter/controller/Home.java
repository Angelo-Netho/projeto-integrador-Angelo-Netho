package dev.netho.jupiter.controller;

import dev.netho.jupiter.Main;
import dev.netho.jupiter.models.Psychologist;
import dev.netho.jupiter.repositories.DiaryRepository;
import dev.netho.jupiter.repositories.PatientRepository;
import dev.netho.jupiter.repositories.PsychologistRepository;
import dev.netho.jupiter.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Home {

    @FXML
    public BorderPane root;

    @FXML
    private StackPane centralPanel;

    private final AuthService authService;

    private final PatientRepository patientRepository;
    private final PsychologistRepository psychologistRepository;
    private final DiaryRepository diaryRepository;

    public Home(AuthService authService, PatientRepository patientRepository, PsychologistRepository psychologistRepository, DiaryRepository diaryRepository) {
        this.authService = authService;
        this.patientRepository = patientRepository;
        this.psychologistRepository = psychologistRepository;
        this.diaryRepository = diaryRepository;
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
            centralPanel.getChildren().add(Main.loadScreen(screen.getSource(), (o) -> new Login(authService, this)));
        }

        if (screen == Screen.PSYCHOLOGIST_HOME) {
            centralPanel.getChildren().clear();
            centralPanel.getChildren().add(Main.loadScreen(screen.getSource(), (o) -> new HomePsychologist(authService, this, psychologistRepository, patientRepository)));
        }

        if (screen == Screen.PATIENT_HOME) {
            centralPanel.getChildren().clear();
            centralPanel.getChildren().add(Main.loadScreen(screen.getSource(), (o) -> new HomePatient(authService, this, patientRepository, diaryRepository)));
        }
    }

}
