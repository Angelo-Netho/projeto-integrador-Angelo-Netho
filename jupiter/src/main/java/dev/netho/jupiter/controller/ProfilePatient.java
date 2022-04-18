package dev.netho.jupiter.controller;

import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.repositories.PatientRepository;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ProfilePatient {

    private Patient patient;

    @FXML
    public TextField tfName;

    @FXML
    public TextField tfEmail;

    @FXML
    public TextField tfPhone;

    @FXML
    public TextField tfGender;

    @FXML
    public DatePicker dpBirthday;

    @FXML
    public TextField tfImage;

    @FXML
    public Circle cImage;

    @FXML
    public Label lblJoinDate;

    private final PatientRepository patientRepository;

    public ProfilePatient(Patient patient, PatientRepository patientRepository) {
        this.patient = patient;
        this.patientRepository = patientRepository;
    }

    public void initialize() {
        loadProfile();
        System.out.println(patient.getDiaries());
    }

    @FXML
    public void sendForm() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String gender = tfGender.getText();
        String image = tfImage.getText();
        LocalDate date;

        try {
            String valid = dpBirthday.getValue().toString();
            date = dpBirthday.getValue();
        } catch (NullPointerException exception) {
            System.out.println("sapao!");
            return;
        }


        if(name.isBlank()) {
            //Coisa pika
            System.out.println("sapo");
            return;
        }

        if(email.isBlank()) {
            //coisa pika
            System.out.println("sapo");
            return;
        }

        try{
            boolean result = true;

            result = patientRepository.patchPatient(patient.getId(), name, email, phone, gender, date, image);

            if(result) {
                //coisa pika boa
                System.out.println("Informações atualizadas com sucesso.");
                loadProfile();
            }else {
                //coisa pika
                System.out.println("Erro ao atualizar o usuário.");
            }
        }catch (Exception exception) {
            //coisa pika
            System.out.println("Erro ao dar update: " + exception.getMessage());
        }

    }

    @FXML
    private void loadProfile() {

        try {
            //Refresh data
            this.patient = patientRepository.getPatient(patient.getId());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }


        tfName.setText(patient.getName());
        tfEmail.setText(patient.getEmail());
        tfPhone.setText(patient.getPhone());
        tfGender.setText(patient.getGender());
        dpBirthday.setValue(patient.getBirthday());
        tfImage.setText(patient.getProfilePicture());

        String month = patient.getBirthday().getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.ROOT);
        lblJoinDate.setText("Ingressou em " + month + " de " + patient.getBirthday().getYear());

        Image image = new Image(patient.getProfilePicture());

        if(!image.isError()) {
            cImage.setFill(new ImagePattern(image));
        }
    }
}
