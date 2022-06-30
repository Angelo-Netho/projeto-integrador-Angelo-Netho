package dev.netho.jupiter.daos.interfaces;

import dev.netho.jupiter.models.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientDAO {

    boolean postPatient(int idPsychologist, Patient patient, String password) throws SQLException;
    Patient getPatient(int id) throws SQLException;
    Patient getPatient(String email) throws SQLException;
    boolean patchPatient(int id, Patient patient) throws SQLException;
    boolean deletePatient(int id) throws SQLException;
    ArrayList<Patient> loadPsychologistPatients(int idPsychologist) throws SQLException;

}
