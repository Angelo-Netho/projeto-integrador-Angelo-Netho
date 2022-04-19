package dev.netho.jupiter.daos;

import dev.netho.jupiter.daos.interfaces.DiaryDAO;
import dev.netho.jupiter.models.Diary;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.utils.MysqlBridge;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DiaryJDBC implements DiaryDAO {

    private final MysqlBridge mysqlBridge;

    public DiaryJDBC(MysqlBridge mysqlBridge) {
        this.mysqlBridge = mysqlBridge;
    }

    @Override
    public boolean postDiary(int idPatient, Diary diary) throws SQLException {
        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL post_diary(?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, idPatient);
        preparedStatement.setString(2, diary.getContent());
        preparedStatement.setInt(3, diary.getMoodLevel());

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        resultSet.next();
        int id = resultSet.getInt(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();

        diary.setId(id);

        return true;
    }

    @Override
    public Diary getDiary(int id) throws SQLException {
        Diary diary = null;

        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL get_diary(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            diary = buildDiary(resultSet);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return diary;
    }

    @Override
    public boolean patchDiary(int id, Diary diary) throws SQLException {
        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL patch_diary(?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, diary.getContent());
        preparedStatement.setInt(3, diary.getMoodLevel());

        int ret = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return ret==1;
    }

    @Override
    public boolean deleteDiary(int id) throws SQLException {
        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL delete_diary(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setInt(1, id);

        int ret = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return ret==1;
    }

    @Override
    public ArrayList<Diary> loadPatientDiaries(int idPatient) throws SQLException {
        ArrayList<Diary> diaries = new ArrayList<>();

        Connection connection = mysqlBridge.getConnection();

        String SQL = "CALL load_patient_diaries(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, idPatient);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            Diary diary = buildDiary(resultSet);
            diaries.add(diary);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return diaries;
    }

    private Diary buildDiary(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("diary_id");
        int moodLevel = resultSet.getInt("mood_level");
        String content = resultSet.getString("content");
        LocalDateTime receive = resultSet.getTimestamp("receive").toLocalDateTime();

        return new Diary(id, moodLevel, content, receive);
    }
}
