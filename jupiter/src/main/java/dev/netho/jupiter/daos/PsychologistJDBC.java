package dev.netho.jupiter.daos;

import dev.netho.jupiter.daos.interfaces.PsychologistDAO;
import dev.netho.jupiter.models.Psychologist;
import dev.netho.jupiter.utils.MysqlBridge;

import java.sql.*;
import java.time.LocalDate;

public class PsychologistJDBC implements PsychologistDAO {

    private final MysqlBridge mysqlBridge;

    public PsychologistJDBC(MysqlBridge mysqlBridge) {
        this.mysqlBridge = mysqlBridge;
    }

    @Override
    public Psychologist getPsychologist(int id) throws SQLException {
        Psychologist psychologist = null;

        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL get_psychologist_by_id(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            psychologist = buildPsychologist(resultSet);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return psychologist;
    }

    @Override
    public Psychologist getPsychologist(String email) throws SQLException {
        Psychologist psychologist = null;

        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL get_psychologist_by_email(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            psychologist = buildPsychologist(resultSet);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return psychologist;
    }

    @Override
    public boolean patchPsychologist(int id, Psychologist psychologist) throws SQLException {
        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL patch_psychologist(?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setInt(1, psychologist.getId());
        preparedStatement.setString(2, psychologist.getCrp());
        preparedStatement.setString(3, psychologist.getName());
        preparedStatement.setString(4, psychologist.getEmail());
        preparedStatement.setDate(5, Date.valueOf(psychologist.getBirthday()));
        preparedStatement.setString(6, psychologist.getPhone());
        preparedStatement.setString(7, psychologist.getGender());
        preparedStatement.setString(8, psychologist.profilePicture);

        int ret = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return ret==1;
    }

    @Override
    public boolean deletePsychologist(int id) throws SQLException {
        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL delete_psychologist(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setInt(1, id);

        int ret = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return ret==1;
    }

    private Psychologist buildPsychologist(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("psychologist_id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String gender = resultSet.getString("gender");
        LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
        LocalDate ingress = resultSet.getDate("created").toLocalDate();
        String profilePicture = resultSet.getString("profile_picture");
        String crp = resultSet.getString("crp");

        return new Psychologist(id, name, email, phone, gender, birthday, ingress, profilePicture, crp, null);

    }

}
