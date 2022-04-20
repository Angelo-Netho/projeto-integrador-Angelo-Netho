package dev.netho.jupiter.controller;

import dev.netho.jupiter.models.Diary;
import dev.netho.jupiter.models.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

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
    public void loadProfileEditor(MouseEvent event) {
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
        LocalDateTime time = diary.getReceive();

        Locale BRAZIL = new Locale("pt", "BR");

        String month = time.getMonth().getDisplayName(TextStyle.SHORT, BRAZIL);

        String minute;
        String hour;

        if(time.getMinute() == 0) {
            minute = "00";
        }else {
            minute = time.getMinute() + "";
        }

        if(time.getHour() == 0) {
            hour = "00";
        }else {
            hour = time.getHour() + "";
        }

        String hourMinute = hour + ":" + minute;

        String received = time.getDayOfMonth() + " de " + month + " de " + time.getYear() + " " +
                hourMinute + " (" + diary + ")";

        lblReceive.setText(received);
    }

}
