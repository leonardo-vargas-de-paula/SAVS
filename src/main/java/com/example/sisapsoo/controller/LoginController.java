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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

        String passHash = hashPassword(password);

        // Verifica se há funcionários com as credenciais
        for(Funcionario funcionario : funcionarios){
            if (funcionario.getNome().equals(username) && funcionario.getSenha().equals(passHash)) {
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

    public static String hashPassword(String password) {
        try {
            // Cria uma instância do MessageDigest para SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Executa o hash da senha
            byte[] hashBytes = digest.digest(password.getBytes());
            
            // Converte o hash em uma string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}