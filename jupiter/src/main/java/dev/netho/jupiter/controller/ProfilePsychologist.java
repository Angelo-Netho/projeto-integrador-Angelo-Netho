package dev.netho.jupiter.controller;

import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.models.Psychologist;
import dev.netho.jupiter.repositories.PatientRepository;
import dev.netho.jupiter.repositories.PsychologistRepository;
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

public class ProfilePsychologist {

    @FXML
    public TextField tfName;

    @FXML
    public TextField tfCrp;

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


    private Psychologist psychologist;

    private final PsychologistRepository psychologistRepository;

    public ProfilePsychologist(Psychologist psychologist, PsychologistRepository psychologistRepository) {
        this.psychologist = psychologist;
        this.psychologistRepository = psychologistRepository;
    }

    public void initialize() {
        loadProfile();
        System.out.println(psychologist.getPatients());
    }

    @FXML
    public void sendForm() {
        String name = tfName.getText();
        String crp = tfCrp.getText();
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

        if(crp.isBlank()) {
            //coisa pika
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

            result = psychologistRepository.patchPsychologist(psychologist.getId(), name, email, phone, gender, date, null, image, crp);

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
            this.psychologist = psychologistRepository.getPsychologist(psychologist.getId());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }


        tfName.setText(psychologist.getName());
        tfEmail.setText(psychologist.getEmail());
        tfPhone.setText(psychologist.getPhone());
        tfGender.setText(psychologist.getGender());
        dpBirthday.setValue(psychologist.getBirthday());
        tfImage.setText(psychologist.getProfilePicture());
        tfCrp.setText(psychologist.getCrp());

        Locale BRAZIL = new Locale("pt", "BR");

        String month = psychologist.getIngress().getMonth().getDisplayName(TextStyle.FULL_STANDALONE, BRAZIL);
        lblJoinDate.setText("Ingressou em " + month + " de " + psychologist.getIngress().getYear());

        if(!psychologist.getProfilePicture().isBlank()) {
            Image image = new Image(psychologist.getProfilePicture());

            if(!image.isError()) {
                cImage.setFill(new ImagePattern(image));
            }
        }
    }
}
