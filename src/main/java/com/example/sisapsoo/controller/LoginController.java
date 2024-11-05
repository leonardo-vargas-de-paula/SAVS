package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.*;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private FuncionarioDAO fDAO;

    private static Funcionario usuarioAtual; // Variável para armazenar o usuário autenticado ESTÁTICAMENTE -> FAZ PARTE DA CLASSE -> PERMITE A VERIFICAÇÃO

    public LoginController() {
        fDAO = new FuncionarioDAO();
    }

    private Funcionario autenticar(String username, String password) throws LoginException {
        ArrayList<Funcionario> funcionarios = new ArrayList<>(fDAO.findAll());
        String passHash = hashPassword(password);

        for (Funcionario funcionario : funcionarios) {
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
            trocarCena(event, "/com/example/sisapsoo/home-view.fxml");
            //abrirCadastroFuncionario();
        } catch (LoginException e) {
            showAlert("Erro de Login", e.getMessage());
        } catch (Exception e) {
//            showAlert("Erro", "Ocorreu um erro ao abrir a tela de cadastro.");
        }
    }

    public boolean verificaGerente(){
        if (usuarioAtual != null && usuarioAtual instanceof Funcionario)
            return usuarioAtual instanceof Gerente; // Retorna true se usuarioAtual é uma instância de Gerente
        return false;
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

    private void trocarCena(ActionEvent event, String fxml) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, "Erro ao carregar a cena: " + fxml, e);
        }
    }
}