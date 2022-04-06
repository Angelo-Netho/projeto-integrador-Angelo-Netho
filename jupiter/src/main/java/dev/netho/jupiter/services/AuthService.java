package dev.netho.jupiter.services;

import dev.netho.jupiter.daos.interfaces.AuthDAO;
import dev.netho.jupiter.models.Profile;

public class AuthService {

    private Profile profile;
    private final AuthDAO authDAO;

    public AuthService(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    public Profile login(String email, String password) throws Exception{
        this.profile = authDAO.login(email, password);
        return this.profile;
    }

    public void register(String email, String password) throws Exception{
        authDAO.register(email, password);
    }

    public Profile getProfile(){
        return this.profile;
    }

    public boolean loggedIn() {
        return this.profile != null;
    }
}
