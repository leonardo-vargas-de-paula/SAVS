package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.Gerente;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CadastroFuncController {
    @FXML
    private Button botaoReiniciar;
    @FXML
    private Button botaoSalvar;
    @FXML
    private TextField campoCpf;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoSalario;
    @FXML
    private TextField campoTelefone;
    @FXML
    private TextField campoSenha;
    @FXML
    private Label labelCpf;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelSalario;
    @FXML
    private Label labelTelefone;
    @FXML
    private Label labelSalvo;
    @FXML
    private BorderPane mainPanel;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Pane painelCadastro;
    @FXML
    private Pane painelLateral;
    @FXML
    private Separator separador;
    @FXML
    private Label titulo;

    private Funcionario f;
    private FuncionarioDAO fDAO;
    private int idAtual;

    // metodo para definir o id do usuário logado
    public void setIdAtual(int idAtual) {
        this.idAtual = idAtual;
    }

    @FXML
    void salvar(javafx.event.ActionEvent actionEvent) {
        f = new Funcionario();
        fDAO = new FuncionarioDAO();

        if (campoSenha.getText().isEmpty() || campoCpf.getText().isEmpty() || campoNome.getText().isEmpty() || campoSalario.getText().isEmpty() || campoTelefone.getText().isEmpty()) {
            showAlert("Campos vazios!", "Não deixe nenhum campo vazio.");
            return;
        }

        if (!isGerenteAutorizado()) {
            showAlert("Acesso Negado", "Apenas gerentes podem cadastrar outros gerentes.");
            return;
        }

        String senha = campoSenha.getText();
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String salario = campoSalario.getText();
        String telefone = campoTelefone.getText();

        try {
            f.setSenha(hashPassword(senha));
            f.setNome(nome);
            f.setCpf(cpf);
            f.setSalario(Double.parseDouble(salario));
            f.setTelefone(telefone);

            fDAO.save(f);

            labelSalvo.setVisible(true);
            campoNome.setDisable(true);
            campoSenha.setDisable(true);
            campoCpf.setDisable(true);
            campoSalario.setDisable(true);
            campoTelefone.setDisable(true);
        } catch (Exception e) {
            showAlert("Erro ao cadastrar: ", "" + e);
        }

        return;
    }

    private boolean isGerenteAutorizado() {
        Funcionario funcionarioAtual = fDAO.findById(idAtual);
        return funcionarioAtual instanceof Gerente;
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

        labelSalvo.setVisible(false);

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
