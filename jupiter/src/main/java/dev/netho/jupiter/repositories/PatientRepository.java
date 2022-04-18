package dev.netho.jupiter.repositories;


import dev.netho.jupiter.daos.interfaces.DiaryDAO;
import dev.netho.jupiter.daos.interfaces.PatientDAO;
import dev.netho.jupiter.models.Patient;

import java.time.LocalDate;

public class PatientRepository {

    private final PatientDAO patientDAO;
    private final DiaryDAO diaryDAO;

    public PatientRepository(PatientDAO patientDAO, DiaryDAO diaryDAO) {
        this.patientDAO = patientDAO;
        this.diaryDAO = diaryDAO;
    }

    public boolean postPatient() {
        return false;
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

    public boolean patchPatient(int id, String name, String email, String phone, String gender, LocalDate birthday, String profilePicture) throws Exception{
        Patient patient = new Patient(id, name, email, phone, gender, birthday, profilePicture, null);
        return patientDAO.patchPatient(id, patient);
    }
}
