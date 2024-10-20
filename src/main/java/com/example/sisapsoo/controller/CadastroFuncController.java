package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
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

public class CadastroFuncController {
    @FXML
    private Button botaoCancelar;

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

    @FXML
    void salvar(javafx.event.ActionEvent actionEvent) {
        f = new Funcionario();
        fDAO = new FuncionarioDAO();

        int id = 0;
        String senha = campoSenha.getText();
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String salario = campoSalario.getText();
        String telefone = campoTelefone.getText();

        try {
            f.setId(Integer.toString(id));
            f.setSenha(senha);
            f.setNome(nome);
            f.setCpf(cpf);
            f.setSalario(Double.parseDouble(salario));
            f.setTelefone(telefone);
            
            fDAO.save(f);

            labelSalvo.setVisible(true);
        }catch(Exception e){
            showAlert("Erro ao cadastrar: ", "" + e);
        }
    
        return;
    }

    /**
     * Limpa todos os campos
     */
    @FXML
    void cancelar(javafx.event.ActionEvent actionEvent) {
        campoNome.clear();
        campoCpf.clear();
        campoSalario.clear();
        campoSenha.clear();
        campoTelefone.clear();

        return;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
