package dev.netho.jupiter.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Patient extends Profile{

    private ArrayList<Diary> diaries;

    public Patient(int id, String name, String email, String phone, String gender, LocalDate birthday, LocalDate ingress, String profilePicture, ArrayList<Diary> diaries) {
        super(id, name, email, phone, gender, birthday, ingress, profilePicture);
        this.diaries = diaries;
    }

    public ArrayList<Diary> getDiaries() {
        return diaries;
    }

    public void setDiaries(ArrayList<Diary> diaries) {
        this.diaries = diaries;
    }

    @Override
    public String toString() {
        return getName();
    }
}
