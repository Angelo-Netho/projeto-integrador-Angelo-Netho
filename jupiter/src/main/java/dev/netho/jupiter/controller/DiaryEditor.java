package dev.netho.jupiter.controller;

import dev.netho.jupiter.models.Diary;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.repositories.DiaryRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class DiaryEditor {

    @FXML
    public TextField tfMood;

    @FXML
    public TextArea taContent;

    @FXML
    public Button btSend;

    @FXML
    public Button btClear;


    private final Patient patient;
    private final DiaryRepository diaryRepository;
    private final Diary diary;
    private final HomePatient home;


    public DiaryEditor(Patient patient, DiaryRepository diaryRepository, HomePatient home) {
        this.patient = patient;
        this.diary = null;
        this.diaryRepository = diaryRepository;
        this.home = home;
    }

    public DiaryEditor(Patient patient, Diary diary, DiaryRepository diaryRepository, HomePatient home) {
        this.patient = patient;
        this.diary = diary;
        this.diaryRepository = diaryRepository;
        this.home = home;
    }

    public void initialize() {
        if(diary != null) {
            loadDiary();
        }
    }

    @FXML
    public void sendForm() {
        String content = taContent.getText();
        String strMoodLevel = tfMood.getText();

        boolean flag = true;
        String msg="";

        int moodLevel = 0;

        if(content.isBlank() || strMoodLevel.isBlank() ) {
            flag = false;
            msg="Preencha todos os campos";
        }

        try{
            moodLevel = Integer.parseInt(strMoodLevel);
        }catch (NumberFormatException exception) {
            flag = false;
            msg="Digite um número entre 1 e 10";
        }

        if(moodLevel < 1 || moodLevel > 10) {
            flag = false;
            msg="Digite um número entre 1 e 10";
        }

        if(flag) {
            try{
                boolean ret;

                if(diary != null) {
                    ret = diaryRepository.patchDiary(diary.getId(), moodLevel, content);
                }else {
                    ret = diaryRepository.postDiary(patient.getId(), moodLevel, content);
                }

                if(ret) {
                    home.loadDiaries();
                    return;
                }else {
                    msg = "Erro ao registrar diário";
                }

            } catch (SQLException exception) {
                msg = "Erro: " + exception.getMessage();
            }

        }

        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();

    }

    @FXML
    public void clear() {
        tfMood.clear();
        taContent.clear();
    }

    private void delete(int id) {
        try {
            diaryRepository.deleteDiary(id);
            patient.getDiaries().remove(diary);
            home.loadDiaries();

        } catch (SQLException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao excluir: " + exception.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

    public void loadDiary() {
        assert diary != null;
        tfMood.setText(diary.getMoodLevel() + "");
        taContent.setText(diary.getContent());

        btSend.setText("Atualizar");
        btClear.setText("Apagar");
        btClear.setOnAction((o) -> delete(diary.getId()));
        
    }

}
