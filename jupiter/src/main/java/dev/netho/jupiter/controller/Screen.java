package dev.netho.jupiter.controller;

public enum Screen {

    LOGIN("/dev/netho/fxml/login.fxml"),
    PSYCHOLOGIST_HOME("/dev/netho/fxml/home_psychologist.fxml"),
    PATIENT_HOME("/dev/netho/fxml/home_patient.fxml"),
    PATIENT_PROFILE("/dev/netho/fxml/profile_patient.fxml");

    private final String source;

    Screen(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
