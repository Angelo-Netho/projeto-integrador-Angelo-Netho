package dev.netho.jupiter.daos;

import dev.netho.jupiter.daos.interfaces.AuthDAO;
import dev.netho.jupiter.utils.MysqlBridge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthJDBC implements AuthDAO {

    private final MysqlBridge mysqlBridge;

    public AuthJDBC(MysqlBridge mysqlBridge) {
        this.mysqlBridge = mysqlBridge;
    }

    @Override
    public boolean authenticatePatient(String email, String password) throws SQLException {
        String SQL = "CALL authenticate_patient(?,?)";

        return authenticate(email, password, SQL);
    }

    @Override
    public boolean authenticatePsychologist(String email, String password) throws SQLException {
        String SQL = "CALL authenticate_psychologist(?,?)";

        return authenticate(email, password, SQL);
    }

    private boolean authenticate(String email, String password, String SQL) throws SQLException {
        Connection connection = mysqlBridge.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean validation = false;

        if(resultSet.next()) {
            validation = resultSet.getBoolean("validation");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return validation;
    }
}
