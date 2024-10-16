package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private List<Usuario> usuarios = new ArrayList<>();

    public LoginController() {
        usuarios.add(new Usuario("admin", "senha"));
        usuarios.add(new Usuario("funcionario", "func2023"));
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticate(username, password)) {
            showAlert("Login bem-sucedido", "Bem-vindo ao Salgado Smart!");
        } else {
            showAlert("Erro de login", "Usuário ou senha incorretos.");
        }
    }

    private boolean authenticate(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(username) && usuario.getSenha().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
