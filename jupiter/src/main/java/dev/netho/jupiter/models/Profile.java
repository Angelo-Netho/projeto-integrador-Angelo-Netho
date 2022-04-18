package dev.netho.jupiter.models;

import java.time.LocalDate;

public class Profile {

    private final int id;
    private final String name;
    private final String email;
    private final String phone;
    private final String gender;
    private final LocalDate birthday;
    private final LocalDate ingress;
    public final String profilePicture;

    public Profile(int id, String name, String email, String phone, String gender, LocalDate birthday, LocalDate ingress, String profilePicture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.ingress = ingress;
        this.profilePicture = profilePicture;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getIngress() {
        return ingress;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

}
