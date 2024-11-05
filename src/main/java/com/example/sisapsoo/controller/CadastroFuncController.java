package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.Gerente;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import com.example.sisapsoo.model.dao.GerenteDAO;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class CadastroFuncController implements Initializable {
    @FXML
    private TextField campoCpf;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoSalario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private TextField campoTelefone;

    @FXML
    private ChoiceBox<String> choiceBox;

    private String[] opcoes = {"Gerente", "Funcionario"};

    @FXML
    private DialogPane dialogPane;

    @FXML
    private Label labelCpf;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelSalario;

    @FXML
    private Label labelSenha;

    @FXML
    private Label labelTelefone;

    @FXML
    private Label labelTipo;

    @FXML
    private Label labelTitulo;

    private Funcionario f;

    private Gerente g;

    private FuncionarioDAO fDAO;

    private GerenteDAO gDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll(opcoes);
    }

    @FXML
    void salvar(javafx.event.ActionEvent actionEvent) {
        f = new Funcionario();
        g = new Gerente();
        fDAO = new FuncionarioDAO();
        gDAO = new GerenteDAO();

        if (campoSenha.getText().isEmpty() || campoCpf.getText().isEmpty() || campoNome.getText().isEmpty() || campoSalario.getText().isEmpty() || campoTelefone.getText().isEmpty()) {
            showAlert("Campos vazios!", "Não deixe nenhum campo vazio.");
            return;
        }

        String senha = campoSenha.getText();
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String salario = campoSalario.getText();
        String telefone = campoTelefone.getText();

        if(choiceBox.getValue() == "Gerente"){
            try {
                g.setSenha(hashPassword(senha));
                g.setNome(nome);
                g.setCpf(cpf);
                g.setSalario(Double.parseDouble(salario));
                g.setTelefone(telefone);
                g.setTipoFuncionario("Gerente");

                gDAO.save(g);
            } catch (Exception e) {
                showAlert("Erro ao cadastrar: ", "" + e);
            }
        }else {
            try {
                f.setSenha(hashPassword(senha));
                f.setNome(nome);
                f.setCpf(cpf);
                f.setSalario(Double.parseDouble(salario));
                f.setTelefone(telefone);
                f.setTipoFuncionario("Funcionário");

                fDAO.save(f);
            } catch (Exception e) {
                showAlert("Erro ao cadastrar: ", "" + e);
            }
        }
        campoNome.setDisable(true);
        campoSenha.setDisable(true);
        campoCpf.setDisable(true);
        campoSalario.setDisable(true);
        campoTelefone.setDisable(true);

        return;
    }

    @FXML
    void reiniciar(javafx.event.ActionEvent actionEvent) {
        campoNome.clear();
        campoCpf.clear();
        campoSalario.clear();
        campoSenha.clear();
        campoTelefone.clear();

        campoNome.setDisable(false);
        campoSenha.setDisable(false);
        campoCpf.setDisable(false);
        campoSalario.setDisable(false);
        campoTelefone.setDisable(false);

        return;
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
