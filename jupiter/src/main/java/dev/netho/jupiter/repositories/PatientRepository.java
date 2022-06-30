package dev.netho.jupiter.repositories;


import dev.netho.jupiter.daos.interfaces.DiaryDAO;
import dev.netho.jupiter.daos.interfaces.PatientDAO;
import dev.netho.jupiter.models.Patient;

import java.sql.SQLException;
import java.time.LocalDate;

public class PatientRepository {

    private final PatientDAO patientDAO;
    private final DiaryDAO diaryDAO;

    public PatientRepository(PatientDAO patientDAO, DiaryDAO diaryDAO) {
        this.patientDAO = patientDAO;
        this.diaryDAO = diaryDAO;
    }

    public boolean postPatient(int idPsychologist, String name, String email, String password, LocalDate birthday, String phone, String gender, String profilePicture) throws SQLException {
        Patient patient = new Patient(-1, name, email, phone, gender, birthday, null, profilePicture, null);

        return patientDAO.postPatient(idPsychologist, patient, password);
    }

    public Patient getPatient(int id) throws Exception {
        Patient patient = patientDAO.getPatient(id);
        patient.setDiaries(diaryDAO.loadPatientDiaries(patient.getId()));
        return patient;
    }

    public Patient getPatient(String email) throws Exception {
        Patient patient = patientDAO.getPatient(email);
        patient.setDiaries(diaryDAO.loadPatientDiaries(patient.getId()));
        return patient;
    }

    public boolean patchPatient(int id, String name, String email, String phone, String gender, LocalDate birthday, LocalDate ingress, String profilePicture) throws Exception{
        Patient patient = new Patient(id, name, email, phone, gender, birthday, ingress, profilePicture, null);
        return patientDAO.patchPatient(id, patient);
    }

    public boolean deletePatient(int id) throws SQLException{
        return patientDAO.deletePatient(id);
    }

}
