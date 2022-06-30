package dev.netho.jupiter.controller;

import dev.netho.jupiter.models.Diary;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.utils.TimeStampGenerator;
import dev.netho.jupiter.utils.TimeStampStyle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class DiaryViewer {

    @FXML
    public Circle cImage;

    @FXML
    public Label lblReceive;

    @FXML
    public TextArea taContent;

    @FXML
    public Label lblMood;

    private final Patient patient;
    private final Diary diary;
    private final HomePsychologist homePsychologist;

    public DiaryViewer(Patient patient, Diary diary, HomePsychologist homePsychologist) {
        this.patient = patient;
        this.diary = diary;
        this.homePsychologist = homePsychologist;

    }

    public void initialize() {
        loadProfileImage();
        loadReceived();
        taContent.setText(diary.getContent());
        lblMood.setText("Escala sentimental: " + diary.getMoodLevel());

    }

    @FXML
    public void loadProfileEditor() {
        homePsychologist.loadPatientProfile(patient);
    }

    private void loadProfileImage() {

        if(patient.getProfilePicture().isBlank()) return;

        Image image = new Image(patient.getProfilePicture());

        if(!image.isError()) {
            cImage.setFill(new ImagePattern(image));
        }
    }

    private void loadReceived() {
        String received = new TimeStampGenerator().generateString(diary.getReceive(), TimeStampStyle.LONG);
        lblReceive.setText(received);
    }

}
