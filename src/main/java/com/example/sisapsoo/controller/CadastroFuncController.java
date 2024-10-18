package com.example.sisapsoo.controller;

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
    void cancelar(javafx.event.ActionEvent actionEvent) {
        // volta pra página inicial
        return;
    }

    @FXML
    void salvar(javafx.event.ActionEvent actionEvent) {
        System.out.println("ok");
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String salario = campoSalario.getText();
        String telefone = campoTelefone.getText();

        // Adicionar um try-catch com o código usando o dao

        labelSalvo.setVisible(true);
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
