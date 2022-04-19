package dev.netho.jupiter.repositories;

import dev.netho.jupiter.daos.interfaces.DiaryDAO;
import dev.netho.jupiter.daos.interfaces.PatientDAO;
import dev.netho.jupiter.daos.interfaces.PsychologistDAO;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.models.Psychologist;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PsychologistRepository {

    private final PsychologistDAO psychologistDAO;
    private final PatientDAO patientDAO;
    private final DiaryDAO diaryDAO;

    public PsychologistRepository(PsychologistDAO psychologistDAO, PatientDAO patientDAO, DiaryDAO diaryDAO) {
        this.psychologistDAO = psychologistDAO;
        this.patientDAO = patientDAO;
        this.diaryDAO = diaryDAO;
    }

    public Psychologist getPsychologist(int id) throws SQLException {
        Psychologist psychologist = psychologistDAO.getPsychologist(id);

        return buildPsychologist(psychologist);
    }

    public Psychologist getPsychologist(String email) throws SQLException {
        Psychologist psychologist = psychologistDAO.getPsychologist(email);

        return buildPsychologist(psychologist);
    }

    public boolean patchPsychologist(int id, String name, String email, String phone, String gender, LocalDate birthday, LocalDate ingress, String profilePicture, String crp) throws SQLException {
        Psychologist psychologist = new Psychologist(id, name, email, phone, gender, birthday, ingress, profilePicture, crp, null);

        return psychologistDAO.patchPsychologist(id, psychologist);
    }

    public boolean deletePsychologist(int id) throws SQLException {
        return psychologistDAO.deletePsychologist(id);
    }

    private Psychologist buildPsychologist(Psychologist psychologist) throws SQLException {
        ArrayList<Patient> patients = patientDAO.loadPsychologistPatients(psychologist.getId());

        for(Patient patient : patients) {
            patient.setDiaries(diaryDAO.loadPatientDiaries(patient.getId()));
        }

        return psychologist;
    }

}
