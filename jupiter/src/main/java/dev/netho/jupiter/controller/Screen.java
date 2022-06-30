package dev.netho.jupiter.controller;

public enum Screen {

    LOGIN("/dev/netho/fxml/login.fxml"),
    PSYCHOLOGIST_HOME("/dev/netho/fxml/home_psychologist.fxml"),
    PATIENT_HOME("/dev/netho/fxml/home_patient.fxml"),
    PATIENT_PROFILE("/dev/netho/fxml/profile_patient.fxml"),
    PATIENT_DASHBOARD("/dev/netho/fxml/dashboard_patient.fxml"),
    PSYCHOLOGIST_PROFILE("/dev/netho/fxml/profile_psychologist.fxml"),
    PSYCHOLOGIST_DASHBOARD("/dev/netho/fxml/dashboard_psychologist.fxml"),
    LIST_PATIENTS("/dev/netho/fxml/list_patients.fxml"),
    LIST_DIARIES("/dev/netho/fxml/list_diaries.fxml"),
    DIARY_VIEWER("/dev/netho/fxml/diary_viewer.fxml"),
    DIARY_EDITOR("/dev/netho/fxml/diary_editor.fxml"),
    PREFERENCES("/dev/netho/fxml/preferences.fxml"),
    REGISTER_PATIENT("/dev/netho/fxml/register_patient.fxml");

    private final String source;

    Screen(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
