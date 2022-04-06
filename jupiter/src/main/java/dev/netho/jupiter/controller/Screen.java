package dev.netho.jupiter.controller;

public enum Screen {

    LOGIN("/dev/netho/fxml/login.fxml"),
    DASHBOARD("/dev/netho/fxml/home_admin.fxml");

    private final String source;

    Screen(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
