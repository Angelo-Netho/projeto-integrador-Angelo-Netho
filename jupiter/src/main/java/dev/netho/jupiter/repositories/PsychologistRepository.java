package dev.netho.jupiter.repositories;

import dev.netho.jupiter.daos.interfaces.DiaryDAO;
import dev.netho.jupiter.daos.interfaces.PatientDAO;
import dev.netho.jupiter.daos.interfaces.PsychologistDAO;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.models.Psychologist;

import java.sql.SQLException;
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

    public Psychologist getPsychologist(int id) throws Exception {
        Psychologist psychologist = psychologistDAO.getPsychologist(id);

        return buildPsychologist(psychologist);
    }

    public Psychologist getPsychologist(String email) throws Exception {
        Psychologist psychologist = psychologistDAO.getPsychologist(email);

        return buildPsychologist(psychologist);
    }

    private Psychologist buildPsychologist(Psychologist psychologist) throws SQLException {
        ArrayList<Patient> patients = patientDAO.loadPsychologistPatients(psychologist.getId());

        for(Patient patient : patients) {
            patient.setDiaries(diaryDAO.loadPatientDiaries(patient.getId()));
        }

        return psychologist;
    }

}
