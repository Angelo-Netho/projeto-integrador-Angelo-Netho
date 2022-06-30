package dev.netho.jupiter.repositories;

import dev.netho.jupiter.daos.interfaces.DiaryDAO;
import dev.netho.jupiter.models.Diary;

import java.sql.SQLException;

public class DiaryRepository {

    private final DiaryDAO diaryDAO;

    public DiaryRepository(DiaryDAO diaryDAO) {
        this.diaryDAO = diaryDAO;
    }

    public boolean postDiary(int idPatient, int moodLevel, String content) throws SQLException{
        Diary diary = new Diary(moodLevel, content);

        return diaryDAO.postDiary(idPatient, diary);
    }

    public Diary getDiary(int id) throws SQLException {
        return diaryDAO.getDiary(id);
    }

    public boolean patchDiary(int id, int moodLevel, String content) throws SQLException {
        Diary diary = new Diary(moodLevel, content);

        return diaryDAO.patchDiary(id, diary);
    }

    public boolean deleteDiary(int id) throws SQLException {
        return diaryDAO.deleteDiary(id);
    }
}
