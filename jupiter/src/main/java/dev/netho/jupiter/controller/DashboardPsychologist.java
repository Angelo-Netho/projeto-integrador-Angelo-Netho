package dev.netho.jupiter.controller;
import dev.netho.jupiter.models.Psychologist;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class DashboardPsychologist {

    @FXML
    public Circle cImage;

    @FXML
    public Button btnMyPatients;

    @FXML
    public Button btnGenerateReport;

    private final Psychologist psychologist;

    private final HomePsychologist home;

    public DashboardPsychologist(Psychologist psychologist, HomePsychologist home) {
        this.psychologist = psychologist;
        this.home = home;
    }

    public void initialize() {
        loadProfile();

    }

    @FXML
    public void loadProfileEditor() {
        home.loadProfile();
    }

    @FXML
    public void loadMyPatients() {
        home.loadPatients();
    }

    @FXML
    public void generateReport() {
        home.loadProfile();
    }

    private void loadProfile() {

        if(psychologist.getProfilePicture().isBlank()) return;

        Image image = new Image(psychologist.getProfilePicture());

        if(!image.isError()) {
            cImage.setFill(new ImagePattern(image));
        }
    }
}
