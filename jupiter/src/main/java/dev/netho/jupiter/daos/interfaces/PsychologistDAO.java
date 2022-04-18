package dev.netho.jupiter.daos.interfaces;

import dev.netho.jupiter.models.Psychologist;

import java.sql.SQLException;

public interface PsychologistDAO {

    Psychologist getPsychologist(int id) throws SQLException;
    Psychologist getPsychologist(String email) throws SQLException;
    boolean patchPsychologist(int id, Psychologist psychologist) throws SQLException;
    boolean deletePsychologist(int id) throws SQLException;

}
