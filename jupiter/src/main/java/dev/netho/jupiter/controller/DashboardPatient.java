package dev.netho.jupiter.controller;

import dev.netho.jupiter.models.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class DashboardPatient {

    @FXML
    public Circle cImage;

    private final Patient patient;

    private final HomePatient home;
    public Button btnWriteDiary;

    public DashboardPatient(Patient patient, HomePatient home) {
        this.patient = patient;
        this.home = home;
    }

    public void initialize() {
        loadProfile();

    }

    @FXML
    public void loadDiaryEditor() {
        home.loadDiaryEditor(patient);
    }

    @FXML
    public void loadProfileEditor() {
        home.loadProfile();
    }

    private void loadProfile() {

        if(patient.getProfilePicture().isBlank()) return;

        Image image = new Image(patient.getProfilePicture());

        if(!image.isError()) {
            cImage.setFill(new ImagePattern(image));
        }
    }

}
