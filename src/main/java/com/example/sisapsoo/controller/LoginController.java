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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private FuncionarioDAO fDAO;
    private Usuario usuarioAtual; // Variável para armazenar o usuário autenticado

    public LoginController() {
        fDAO = new FuncionarioDAO();
    }

    private Usuario autenticar(String username, String password) throws LoginException {
        ArrayList<Funcionario> funcionarios = new ArrayList<>(fDAO.findAll());
        String passHash = hashPassword(password);

        for(Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().equals(username) && funcionario.getSenha().equals(passHash)) {
                return funcionario;
            }
        }
        throw new LoginException("Usuário ou senha incorretos.");
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            usuarioAtual = autenticar(username, password); // salva o usuario autenticado
            abrirCadastroFuncionario();
        } catch (LoginException e) {
            showAlert("Erro de Login", e.getMessage());
        } catch (Exception e) {
            showAlert("Erro", "Ocorreu um erro ao abrir a tela de cadastro.");
        }
    }

    private void abrirCadastroFuncionario() throws Exception {
        if (usuarioAtual != null && usuarioAtual instanceof Funcionario) {
            String cpfAtual = ((Funcionario) usuarioAtual).getCpf(); // obtenha o CPF do usuário logado
            Funcionario funcionarioAtual = fDAO.findById(cpfAtual);

            if (!(funcionarioAtual instanceof Gerente)) {
                showAlert("Acesso Negado", "Apenas gerentes podem cadastrar outros gerentes.");
                return;
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sisapsoo/cadastro-funcionario-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);

        Stage stage = new Stage();
        stage.setTitle("Cadastro de Funcionário");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Stage loginStage = (Stage) usernameField.getScene().getWindow();
        loginStage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
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