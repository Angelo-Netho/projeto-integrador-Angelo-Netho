package dev.netho.jupiter.controller;

import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.models.Psychologist;
import dev.netho.jupiter.repositories.PatientRepository;
import dev.netho.jupiter.repositories.PsychologistRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ListPatients {

    @FXML
    public TextField tfSearch;

    @FXML
    public ListView<Patient> lvPatients;

    @FXML
    public Button btnAddPatient;

    @FXML
    public Button btnEditPatient;

    @FXML
    public Button btnDeletePatient;

    private final HomePsychologist home;
    private Psychologist psychologist;
    private final PatientRepository patientRepository;
    private final PsychologistRepository psychologistRepository;

    public ListPatients(HomePsychologist home, Psychologist psychologist, PatientRepository patientRepository, PsychologistRepository psychologistRepository) {
        this.home = home;
        this.psychologist = psychologist;
        this.patientRepository = patientRepository;
        this.psychologistRepository = psychologistRepository;
    }

    public void initialize() {

        try {
            //Refresh data
            this.psychologist = psychologistRepository.getPsychologist(psychologist.getId());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        updateList();
    }

    @FXML
    public void updateList() {
        String search = tfSearch.getText();

        if(search.length() >= 2) {
            lvPatients.getItems().clear();
            lvPatients.getItems().addAll(searchPatient(search));
        }else {
            lvPatients.getItems().clear();
            lvPatients.getItems().addAll(psychologist.getPatients());
        }
    }

    @FXML
    public void loadDiaries(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount()==2) {
            Patient patient = lvPatients.getSelectionModel().getSelectedItem();

            if(patient != null)
                home.loadPatientDiaries(patient);
        }
    }

    @FXML
    public void addPatient() {
        home.loadRegisterPatient();
    }

    @FXML
    public void editPatient() {
        Patient patient = lvPatients.getSelectionModel().getSelectedItem();

        if(patient != null)
         home.loadPatientProfile(patient);
    }

    @FXML
    public void deletePatient() {
        Patient patient = lvPatients.getSelectionModel().getSelectedItem();

        if(patient == null) return;

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Tem certeza que deseja desativar a conta de " + patient.getName() + "?", ButtonType.YES);
            alert.setHeaderText("Exclusão de conta");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.YES) {
                patientRepository.deletePatient(patient.getId());
                psychologist.getPatients().remove(patient);
                updateList();
            }

        }catch (SQLException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao apagar conta, tente novamente mais tarde");
            alert.showAndWait();
        }
    }

    private ArrayList<Patient> searchPatient(String search) {
        ArrayList<Patient> result = new ArrayList<>();

        for(Patient patient : psychologist.getPatients()) {
            if(patient.getName().toUpperCase().startsWith(search.toUpperCase())){
                result.add(patient);
            }
        }
        return result;
    }
}
