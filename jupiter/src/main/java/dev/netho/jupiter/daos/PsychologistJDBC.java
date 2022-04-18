package dev.netho.jupiter.daos;

import dev.netho.jupiter.daos.interfaces.PsychologistDAO;
import dev.netho.jupiter.models.Psychologist;
import dev.netho.jupiter.utils.MysqlBridge;

import java.sql.SQLException;

public class PsychologistJDBC implements PsychologistDAO {

    private final MysqlBridge mysqlBridge;

    public PsychologistJDBC(MysqlBridge mysqlBridge) {
        this.mysqlBridge = mysqlBridge;
    }

    @Override
    public Psychologist getPsychologist(int id) throws SQLException {
        return null;
    }

    @Override
    public Psychologist getPsychologist(String email) throws SQLException {
        return null;
    }

    @Override
    public boolean patchPsychologist(int id, Psychologist psychologist) throws SQLException {
        return false;
    }

    @Override
    public boolean deletePsychologist(int id) throws SQLException {
        return false;
    }

}
