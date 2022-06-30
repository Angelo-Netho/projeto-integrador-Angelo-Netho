package dev.netho.jupiter.controller;
import dev.netho.jupiter.models.Psychologist;
import dev.netho.jupiter.services.MailService;
import dev.netho.jupiter.utils.ReportGenerator;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;

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
        ReportGenerator reportGenerator = new ReportGenerator(psychologist);
        try{
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(null);

            if(file != null) {
                reportGenerator.generateReport(file.getName());
            }

            assert file != null;
            new MailService().sendNotificationEmail(psychologist.getEmail(), file.getName(), psychologist.getName());
        }catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
            alert.showAndWait();
        }
    }


    private void loadProfile() {

        if(psychologist.getProfilePicture().isBlank()) return;

        Image image = new Image(psychologist.getProfilePicture());

        if(!image.isError()) {
            cImage.setFill(new ImagePattern(image));
        }
    }
}
