package dev.netho.jupiter.daos;

import dev.netho.jupiter.daos.interfaces.PatientDAO;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.utils.MysqlBridge;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PatientJDBC implements PatientDAO {

    private final MysqlBridge mysqlBridge;

    public PatientJDBC(MysqlBridge mysqlBridge) {
        this.mysqlBridge = mysqlBridge;
    }

    @Override
    public boolean postPatient(int idPsychologist, Patient patient, String password) throws SQLException {
        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL post_patient(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, idPsychologist);
        preparedStatement.setString(2, patient.getName());
        preparedStatement.setString(3, patient.getEmail());
        preparedStatement.setString(4, password);
        preparedStatement.setDate(5, Date.valueOf(patient.getBirthday()));
        preparedStatement.setString(6, patient.getPhone());
        preparedStatement.setString(7, patient.getGender());
        preparedStatement.setString(8, patient.getProfilePicture());

       ResultSet resultSet = preparedStatement.getGeneratedKeys();

       resultSet.next();
       int id = resultSet.getInt(1);

       resultSet.close();
       preparedStatement.close();
       connection.close();

        patient.setId(id);

       return true;
    }

    @Override
    public Patient getPatient(int id) throws SQLException {
        Patient patient = null;

        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL get_patient_by_id(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            patient = buildPatient(resultSet);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return patient;
    }

    @Override
    public Patient getPatient(String email) throws SQLException {
        Patient patient = null;

        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL get_patient_by_email(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            patient = buildPatient(resultSet);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return patient;
    }

    @Override
    public boolean patchPatient(int id, Patient patient) throws SQLException {
        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL patch_patient(?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setInt(1, patient.getId());
        preparedStatement.setString(2, patient.getName());
        preparedStatement.setString(3, patient.getEmail());
        preparedStatement.setDate(4, Date.valueOf(patient.getBirthday()));
        preparedStatement.setString(5, patient.getPhone());
        preparedStatement.setString(6, patient.getGender());
        preparedStatement.setString(7, patient.profilePicture);

        int ret = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return ret==1;
    }

    @Override
    public boolean deletePatient(int id) throws SQLException {
        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL delete_patient(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setInt(1, id);

        int ret = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return ret==1;
    }

    @Override
    public ArrayList<Patient> loadPsychologistPatients(int idPsychologist) throws SQLException {

        ArrayList<Patient> patients = new ArrayList<>();

        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL load_psychologist_patients(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, idPsychologist);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            Patient patient = buildPatient(resultSet);
            patients.add(patient);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return patients;
    }

    private Patient buildPatient(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("patient_id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String gender = resultSet.getString("gender");
        LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
        LocalDate ingress = resultSet.getDate("created").toLocalDate();
        String profilePicture = resultSet.getString("profile_picture");

        return new Patient(id, name, email, phone, gender, birthday, ingress, profilePicture, null);
    }

}
