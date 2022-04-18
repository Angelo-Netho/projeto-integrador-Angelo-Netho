package dev.netho.jupiter.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Psychologist extends Profile{

    private final String cpr;
    private ArrayList<Patient> patients;

    public Psychologist(int id, String name, String email, String phone, String gender, LocalDate birthday, String profilePicture, String cpr, ArrayList<Patient> patients) {
        super(id, name, email, phone, gender, birthday, profilePicture);
        this.cpr = cpr;
        this.patients = patients;
    }

    public String getCpr() {
        return cpr;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
}
