package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.Gerente;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import com.example.sisapsoo.model.dao.GerenteDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CadastroGerenteController {
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
    @FXML
    private ComboBox<String> campoPapel;

    private Gerente gerente;
    private GerenteDAO gerenteDAO;
    private Funcionario funcionario;
    private FuncionarioDAO funcionarioDAO;
    private Integer idAtual;

    @FXML
    public void initialize() {
        campoPapel.getItems().addAll("Funcionario", "Gerente");
        funcionarioDAO = new FuncionarioDAO();
        gerenteDAO = new GerenteDAO();
    }

    public void setIdAtual(int idAtual) {
        this.idAtual = idAtual;
    }

    @FXML
    void salvar(javafx.event.ActionEvent actionEvent) {
        if (campoSenha.getText().isEmpty() || campoCpf.getText().isEmpty() || campoNome.getText().isEmpty() ||
                campoSalario.getText().isEmpty() || campoTelefone.getText().isEmpty() || campoPapel.getSelectionModel().isEmpty()) {
            showAlert("Campos vazios!", "Não deixe nenhum campo vazio.");
            return;
        }

        String papelSelecionado = campoPapel.getSelectionModel().getSelectedItem();

        // Verifica se o usuário logado tem permissão para cadastrar um gerente
        if ("Gerente".equals(papelSelecionado) && !isGerenteAutorizado()) {
            showAlert("Acesso Negado", "Apenas gerentes podem cadastrar outros gerentes.");
            return;
        }

        String senha = campoSenha.getText();
        String senhaHash = hashPassword(senha);
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String salario = campoSalario.getText();
        String telefone = campoTelefone.getText();

        try {
            // Criação condicional do objeto com base no papel selecionado
            if ("Gerente".equals(papelSelecionado)) {
                gerente = new Gerente();
                gerente.setSenha(senhaHash);
                gerente.setNome(nome);
                gerente.setCpf(cpf);
                gerente.setSalario(Double.parseDouble(salario));
                gerente.setTelefone(telefone);

                gerenteDAO.save(gerente);
            } else {
                funcionario = new Funcionario();
                funcionario.setSenha(senhaHash);
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setSalario(Double.parseDouble(salario));
                funcionario.setTelefone(telefone);

                funcionarioDAO.save(funcionario);
                showAlert("Sucesso", "Cadastro realizado com sucesso!");
            }

            labelSalvo.setVisible(true);
            desabilitarCamposCadastro();
        } catch (Exception e) {
            showAlert("Erro ao cadastrar: ", "" + e);
        }
    }

    private void desabilitarCamposCadastro() {
        campoNome.setDisable(true);
        campoSenha.setDisable(true);
        campoCpf.setDisable(true);
        campoSalario.setDisable(true);
        campoTelefone.setDisable(true);
    }

    private boolean isGerenteAutorizado() {
        if (idAtual == null) {
            return false;
        }
        Funcionario funcionarioAtual = funcionarioDAO.findById(idAtual);
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
            // Cria instância do MessageDigest para SHA-256
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
