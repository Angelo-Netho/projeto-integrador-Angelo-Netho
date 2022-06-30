package dev.netho.jupiter.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Psychologist extends Profile{

    private final String crp;
    private ArrayList<Patient> patients;

    public Psychologist(int id, String name, String email, String phone, String gender, LocalDate birthday, LocalDate ingress, String profilePicture, String crp, ArrayList<Patient> patients) {
        super(id, name, email, phone, gender, birthday, ingress, profilePicture);
        this.crp = crp;
        this.patients = patients;
    }

    public String getCrp() {
        return crp;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
}
