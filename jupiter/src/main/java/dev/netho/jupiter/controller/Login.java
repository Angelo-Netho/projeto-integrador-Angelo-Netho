package dev.netho.jupiter.controller;

import dev.netho.jupiter.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class Login {

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
    public GridPane root;

    private AuthService authService;
    private Home homeController;

    public Login(AuthService authService, Home homeController) {
        this.authService = authService;
        this.homeController = homeController;
    }

    @FXML
    public void validateLogin() {

        String email = tfEmail.getText();
        String password = tfPassword.getText();

        try{
            authService.login(email, password);

            if(authService.loggedIn()) {
                homeController.update();
            }

        }catch (Exception exception) {
            exception.printStackTrace();
        }

    }


}
