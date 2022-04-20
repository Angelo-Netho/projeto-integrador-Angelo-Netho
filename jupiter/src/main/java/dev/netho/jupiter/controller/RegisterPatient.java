package dev.netho.jupiter.controller;

import dev.netho.jupiter.models.Psychologist;
import dev.netho.jupiter.repositories.PatientRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;

public class RegisterPatient {

    @FXML
    public TextField tfName;

    @FXML
    public TextField tfEmail;

    @FXML
    public DatePicker dpBirthday;

    @FXML
    public TextField tfPhone;

    @FXML
    public TextField tfGender;

    @FXML
    public TextField tfImage;

    @FXML
    public PasswordField tfPassword;

    private final Psychologist psychologist;
    private final PatientRepository patientRepository;

    public RegisterPatient(Psychologist psychologist, PatientRepository patientRepository) {
        this.psychologist = psychologist;
        this.patientRepository = patientRepository;
    }

    @FXML
    public void sendForm() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String gender = tfGender.getText();
        String image = tfImage.getText();
        String password = tfPassword.getText();
        LocalDate birthday = dpBirthday.getValue();

        boolean flag = true;
        String message = "";

        if(name.isBlank() || email.isBlank() || password.isBlank() || birthday == null) {
            message+="Preencha todos os campos obrigatórios";
            flag = false;
        }

        if(flag) {
            try{
                boolean ret = patientRepository.postPatient(psychologist.getId(), name, email, password, birthday, phone, gender, image);

                if(ret) {
                    message = "Paciente " + name + " cadastrado com sucesso";
                    clear();
                }else {
                    message = "Erro ao cadastrar paciente";
                }
            }catch (SQLException exception) {
                message = exception.getMessage();
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    public void clear() {
        tfName.clear();
        tfEmail.clear();
        tfPhone.clear();
        tfGender.clear();
        tfImage.clear();
        tfPassword.clear();
        dpBirthday.setValue(null);
    }
}
