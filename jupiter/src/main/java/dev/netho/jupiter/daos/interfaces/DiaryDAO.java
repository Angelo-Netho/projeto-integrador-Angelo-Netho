package dev.netho.jupiter.daos.interfaces;

import dev.netho.jupiter.models.Diary;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DiaryDAO {

    boolean postDiary(int idPatient, Diary diary) throws SQLException;
    Diary getDiary(int idPatient) throws SQLException;
    boolean patchDiary(int id, Diary diary) throws SQLException;
    boolean deleteDiary(int id) throws SQLException;
    ArrayList<Diary> loadPatientDiaries(int idPatient) throws SQLException;

}
