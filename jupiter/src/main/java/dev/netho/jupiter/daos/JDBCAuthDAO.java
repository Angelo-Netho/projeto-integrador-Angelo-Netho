package dev.netho.jupiter.daos;

import dev.netho.jupiter.daos.interfaces.AuthDAO;
import dev.netho.jupiter.models.Profile;

public class JDBCAuthDAO implements AuthDAO {

    @Override
    public Profile login(String email, String password) throws Exception {

        if(email.equals("angelonetho@gmail.com") && password.equals("senha")){
            return new Profile(1, email, false);
        }

        if(email.equals("angelonetho@netho.dev") && password.equals("senha")){
            return new Profile(2, email, true);
        }
        return null;
    }

    @Override
    public boolean register(String email, String senha) throws Exception {
        return false;
    }
}
