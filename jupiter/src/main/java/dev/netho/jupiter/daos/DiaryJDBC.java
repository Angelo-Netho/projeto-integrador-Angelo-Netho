package dev.netho.jupiter.daos;

import dev.netho.jupiter.daos.interfaces.DiaryDAO;
import dev.netho.jupiter.models.Diary;
import dev.netho.jupiter.utils.MysqlBridge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DiaryJDBC implements DiaryDAO {

    private final MysqlBridge mysqlBridge;

    public DiaryJDBC(MysqlBridge mysqlBridge) {
        this.mysqlBridge = mysqlBridge;
    }

    @Override
    public boolean postDiary(int idPatient, Diary diary) throws SQLException {
        return false;
    }

    @Override
    public Diary getDiary(int idPatient) throws SQLException {
        return null;
    }

    @Override
    public boolean patchDiary(int id, Diary diary) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteDiary(int id) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Diary> loadPatientDiaries(int idPatient) throws SQLException {
        ArrayList<Diary> diaries = new ArrayList<>();

        Connection connection = mysqlBridge.getConnection();

        String SQL = "SELECT * FROM diary WHERE patient_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, idPatient);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int id = resultSet.getInt("diary_id");
            int moodLevel = resultSet.getInt("mood_level");
            String content = resultSet.getString("content");
            LocalDateTime receive = resultSet.getTimestamp("receive").toLocalDateTime();

            Diary diary = new Diary(id, moodLevel, content, receive);
            diaries.add(diary);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return diaries;
    }
}
