package dev.netho.jupiter.controller;

import dev.netho.jupiter.Main;
import dev.netho.jupiter.models.Diary;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.models.Psychologist;
import dev.netho.jupiter.repositories.PatientRepository;
import dev.netho.jupiter.repositories.PsychologistRepository;
import dev.netho.jupiter.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class HomePsychologist {

    @FXML
    public StackPane centralPanel;

    @FXML
    public Button btnMainPage;

    @FXML
    public Button btnPatients;

    @FXML
    public Button btnProfile;

    @FXML
    public Button btnPreferences;

    private final AuthService authService;
    private final Home home;
    private final Psychologist psychologist;
    private final PsychologistRepository psychologistRepository;
    private final PatientRepository patientRepository;

    public HomePsychologist(AuthService authService, Home home, PsychologistRepository psychologistRepository, PatientRepository patientRepository) {
        this.authService = authService;
        this.home = home;
        this.psychologistRepository = psychologistRepository;
        this.patientRepository = patientRepository;
        psychologist = (Psychologist) authService.getProfile();
    }

    public void initialize() {
        loadDashboard();
    }

    @FXML
    public void loadDashboard() {
        updateButtonRender(btnMainPage);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.PSYCHOLOGIST_DASHBOARD.getSource(), (o) -> new DashboardPsychologist(psychologist, this)));
    }

    @FXML
    public void loadPatients() {
        updateButtonRender(btnPatients);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.LIST_PATIENTS.getSource(), (o) -> new ListPatients(this, psychologist, patientRepository, psychologistRepository)));
    }

    @FXML
    public void loadProfile() {
        updateButtonRender(btnProfile);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.PSYCHOLOGIST_PROFILE.getSource(), (o) -> new ProfilePsychologist(psychologist, psychologistRepository)));
    }

    @FXML
    public void loadPreferences() {
        updateButtonRender(btnPreferences);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.PREFERENCES.getSource(), (o) -> new Preferences(home)));
    }

    @FXML
    public void logOut() {
        authService.logOut();
        home.update();

    }

    public void loadPatientProfile(Patient patient) {
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.PATIENT_PROFILE.getSource(), (o) -> new ProfilePatient(patient, patientRepository)));
    }

    public void loadPatientDiaries(Patient patient) {
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.LIST_DIARIES.getSource(), (o) -> new ListDiaries(patient, this)));
    }

    public void loadDiaryViewer(Patient patient, Diary diary) {
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.DIARY_VIEWER.getSource(), (o) -> new DiaryViewer(patient, diary, this)));
    }

    public void loadRegisterPatient() {
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.REGISTER_PATIENT.getSource(), (o) -> new RegisterPatient(psychologist, patientRepository)));
    }

    private void updateButtonRender(Button selected) {
        String notSelectedStyle = "-fx-background-color: rgba(0, 247, 99, 0);-fx-opacity: 0.5;";

        btnMainPage.setStyle(notSelectedStyle);
        btnPatients.setStyle(notSelectedStyle);
        btnProfile.setStyle(notSelectedStyle);
        btnPreferences.setStyle(notSelectedStyle);

        selected.setStyle("-fx-background-color: #00F763;-fx-opacity: 1;");

    }
}
