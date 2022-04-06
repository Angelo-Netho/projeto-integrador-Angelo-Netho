package dev.netho.jupiter.daos.interfaces;

import dev.netho.jupiter.models.Profile;

public interface AuthDAO {
    Profile login(String email, String password) throws Exception;
    boolean register(String email, String password) throws Exception;
}
