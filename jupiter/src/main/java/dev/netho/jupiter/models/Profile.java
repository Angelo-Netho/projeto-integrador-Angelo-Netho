package dev.netho.jupiter.models;

public class Profile {
    private int id;
    private String email;
    private String senha;
    private boolean admin;

    public Profile(int id, String email, boolean admin) {
        this.id = id;
        this.email = email;
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }


}
