package dev.netho.jupiter.services;

import dev.netho.jupiter.daos.interfaces.AuthDAO;
import dev.netho.jupiter.models.Profile;
import dev.netho.jupiter.repositories.PatientRepository;
import dev.netho.jupiter.repositories.PsychologistRepository;

public class AuthService {

    private Profile profile;
    private final AuthDAO authDAO;

    private final PatientRepository patientRepository;
    private final PsychologistRepository psychologistRepository;

    public AuthService(AuthDAO authDAO, PatientRepository patientRepository, PsychologistRepository psychologistRepository) {
        this.authDAO = authDAO;
        this.patientRepository = patientRepository;
        this.psychologistRepository = psychologistRepository;
    }

    public void login(String email, String password) throws Exception{

        if(authDAO.authenticatePatient(email,password)){
            this.profile = patientRepository.getPatient(email);
        } else {
            if(authDAO.authenticatePsychologist(email, password)) {
                this.profile = psychologistRepository.getPsychologist(email);
            }
        }

        if(profile == null) throw new Exception("Null Perfil");

    }

    public void logOut() {
        this.profile = null;
    }

    public Profile getProfile(){
        return this.profile;
    }

    public boolean loggedIn() {
        return this.profile != null;
    }
}
