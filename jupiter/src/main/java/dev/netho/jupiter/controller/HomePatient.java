package dev.netho.jupiter.controller;

import dev.netho.jupiter.Main;
import dev.netho.jupiter.models.Diary;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.repositories.DiaryRepository;
import dev.netho.jupiter.repositories.PatientRepository;
import dev.netho.jupiter.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class HomePatient {

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

    private final AuthService authService;
    private final Home home;
    private final Patient patient;
    private final PatientRepository patientRepository;
    private final DiaryRepository diaryRepository;

    public HomePatient(AuthService authService, Home home, PatientRepository patientRepository, DiaryRepository diaryRepository) {
        this.authService = authService;
        this.home = home;
        this.patientRepository = patientRepository;
        this.diaryRepository = diaryRepository;
        patient = (Patient) authService.getProfile();
    }

    public void initialize() {
        loadDashboard();

    }

    @FXML
    public void loadDashboard() {
        updateButtonRender(btnMainPage);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.PATIENT_DASHBOARD.getSource(), (o) -> new DashboardPatient(patient, this)));
    }

    @FXML
    public void loadDiaries() {
        updateButtonRender(btnDiary);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.LIST_DIARIES.getSource(), (o) -> new ListDiaries(patient, this, patientRepository)));
    }

    @FXML
    public void loadProfile() {
        updateButtonRender(btnProfile);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.PATIENT_PROFILE.getSource(), (o) -> new ProfilePatient(patient, patientRepository)));
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

    public void loadDiaryEditor(Patient patient, Diary diary) {
        updateButtonRender(btnDiary);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.DIARY_EDITOR.getSource(), (o) -> new DiaryEditor(patient, diary, diaryRepository, this)));
    }

    public void loadDiaryEditor(Patient patient) {
        updateButtonRender(btnDiary);
        centralPanel.getChildren().clear();
        centralPanel.getChildren().add(Main.loadScreen(Screen.DIARY_EDITOR.getSource(), (o) -> new DiaryEditor(patient, diaryRepository, this)));
    }

    private void updateButtonRender(Button selected) {
        String notSelectedStyle = "-fx-background-color: rgba(0, 247, 99, 0);-fx-opacity: 0.5;";

        btnMainPage.setStyle(notSelectedStyle);
        btnDiary.setStyle(notSelectedStyle);
        btnProfile.setStyle(notSelectedStyle);
        btnPreferences.setStyle(notSelectedStyle);

        selected.setStyle("-fx-background-color: #00F763;-fx-opacity: 1;");

    }

}
