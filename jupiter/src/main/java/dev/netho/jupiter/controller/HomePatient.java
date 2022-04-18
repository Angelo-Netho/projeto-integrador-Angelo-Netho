package dev.netho.jupiter.controller;

import dev.netho.jupiter.Main;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.repositories.PatientRepository;
import dev.netho.jupiter.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class HomePatient {

    private final AuthService authService;
    private final Home home;
    private final Patient patient;

    @FXML
    public StackPane centralPanel;

    @FXML
    public Button btnMainPage;

    @FXML
    public Button btnDiary;

    @FXML
    public Button btnProfile;

    @FXML
    public Button btnPreferences;

    private final PatientRepository patientRepository;


    public HomePatient(AuthService authService, Home home, PatientRepository patientRepository) {
        this.authService = authService;
        this.home = home;
        this.patientRepository = patientRepository;
        patient = (Patient) authService.getProfile();
    }

    public void initialize() {
        updateButtonRender(btnMainPage);

    }

    public void loadProfile() {
        updateButtonRender(btnProfile);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadTela(Screen.PATIENT_PROFILE.getSource(), (o) -> new ProfilePatient(patient, patientRepository)));
    }


    public void logOut() {
        authService.logOut();
        home.update();

    }

    public void updateButtonRender(Button selected) {
        String notSelectedStyle = "-fx-background-color: rgba(0, 247, 99, 0);-fx-opacity: 0.5;";

        btnMainPage.setStyle(notSelectedStyle);
        btnDiary.setStyle(notSelectedStyle);
        btnProfile.setStyle(notSelectedStyle);
        btnPreferences.setStyle(notSelectedStyle);

        selected.setStyle("-fx-background-color: #00F763;-fx-opacity: 1;");

    }

}
