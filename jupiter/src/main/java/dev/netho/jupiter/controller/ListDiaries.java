package dev.netho.jupiter.controller;

import dev.netho.jupiter.models.Diary;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.repositories.PatientRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.Collections;

public class ListDiaries {

    @FXML
    public Label lblName;

    @FXML
    public ListView<Diary> lvDiaries;

    private final HomePsychologist homePsychologist;
    private final HomePatient homePatient;
    private Patient patient;

    public ListDiaries(Patient patient, HomePsychologist homePsychologist) {
        this.patient = patient;
        this.homePsychologist = homePsychologist;
        this.homePatient = null;
    }

    public ListDiaries(Patient patient, HomePatient homePatient, PatientRepository patientRepository) {
        this.homePatient = homePatient;
        this.homePsychologist = null;

        try {
            //Refresh data
            this.patient = patientRepository.getPatient(patient.getId());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    public void initialize() {
        updateList();
        lblName.setText("Mostrando os registros de " + patient.getName());
    }

    @FXML
    public void loadDiary(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount()==2) {
            Diary diary = lvDiaries.getSelectionModel().getSelectedItem();

            if(diary != null) {
                if(homePsychologist != null) {
                    homePsychologist.loadDiaryViewer(patient, diary);
                }else {
                    assert homePatient != null;
                    homePatient.loadDiaryEditor(patient, diary);
                }
            }

        }
    }

    public void updateList() {
        lvDiaries.getItems().clear();
        lvDiaries.getItems().addAll(patient.getDiaries());

        //Invert to always get the newest diaries on top
        Collections.reverse(lvDiaries.getItems());
    }
}
