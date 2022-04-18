package dev.netho.jupiter.daos.interfaces;

import java.sql.SQLException;

public interface AuthDAO {
    boolean authenticatePatient(String email, String password) throws SQLException;
    boolean authenticatePsychologist(String email, String password) throws SQLException;
}
