package com.example.sisapsoo.controller;

import com.example.sisapsoo.builder.FuncionarioBuilder;
import com.example.sisapsoo.builder.GerenteBuilder;
import com.example.sisapsoo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

import javax.security.auth.login.LoginException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // Simula banco de dados
    private Funcionario[] funcionarios;
    private Gerente[] gerentes;

    public LoginController() {
        initializeUsers();
    }

    private void initializeUsers() {
        funcionarios = new Funcionario[]{
                new FuncionarioBuilder()
                        .setId("func1")
                        .setSenha("senhaFunc1")
                        .setNome("Funcionario 1")
                        .setCpf("12345678900")
                        .setTelefone("11111111111")
                        .setSalario(2000.00)
                        .build(),
                new FuncionarioBuilder()
                        .setId("func2")
                        .setSenha("senhaFunc2")
                        .setNome("Funcionario 2")
                        .setCpf("09876543210")
                        .setTelefone("22222222222")
                        .setSalario(2500.00)
                        .build()
        };

        gerentes = new Gerente[]{
                new GerenteBuilder()
                        .setId("gerente1")
                        .setSenha("senhaGerente1")
                        .setNome("Gerente 1")
                        .setCpf("12345678901")
                        .setTelefone("99999999999")
                        .setSalario(5000.00)
                        .setRelatorios(new Relatorio[0])
                        .setEstoque(new Estoque())
                        .build()
        };
    }

    private Usuario autenticar(String username, String password) throws LoginException {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId().equals(username) && funcionario.getSenha().equals(password)) {
                return funcionario;
            }
        }

        for (Gerente gerente : gerentes) {
            if (gerente.getId().equals(username) && gerente.getSenha().equals(password)) {
                return gerente;
            }
        }

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

    public void setFuncionarios(Funcionario[] funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void setGerentes(Gerente[] gerentes) {
        this.gerentes = gerentes;
    }

}
