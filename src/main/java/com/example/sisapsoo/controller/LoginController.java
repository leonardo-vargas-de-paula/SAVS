package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.*;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import org.hibernate.mapping.List;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private FuncionarioDAO fDAO;

    public LoginController() {
        fDAO = new FuncionarioDAO();
    }

    private Usuario autenticar(String username, String password) throws LoginException {
        ArrayList<Funcionario> funcionarios = new ArrayList<>(fDAO.findAll());  // Cria uma lista de funcionarios
        // ArrayList<Gerente> gerentes = (ArrayList<Gerentes>) gDAO.findAll();  // Cria uma lista de gerentes
        
        // Verifica se há funcionários com as credenciais
        for(Funcionario funcionario : funcionarios){
            if (funcionario.getNome().equals(username) && funcionario.getSenha().equals(password)) {
                return funcionario;
            }
        }

        // for (Gerente gerente : gerentes) {
        //     if (gerente.getNome().equals(username) && gerente.getSenha().equals(password)) {
        //         return gerente;
        //     }
        // }

        throw new LoginException("Usuário ou senha incorretos.");
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            Usuario usuario = autenticar(username, password);
        } catch (LoginException e) {
            showAlert("Erro de Login", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
