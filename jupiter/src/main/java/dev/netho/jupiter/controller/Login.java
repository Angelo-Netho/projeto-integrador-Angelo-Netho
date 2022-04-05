package dev.netho.jupiter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class Login {

    @FXML
    public GridPane root;

    @FXML
    public TextField tfEmail;

    @FXML
    public PasswordField tfPassword;

    @FXML
    public CheckBox cbRemember;

    @FXML
    public Button btConfirm;

    @FXML
    public Hyperlink hlHelp;

    private void clear() {
        tfEmail.setText("");
        tfPassword.setText("");
        cbRemember.setSelected(false);
    }
}
