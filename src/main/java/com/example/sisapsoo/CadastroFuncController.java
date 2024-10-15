package com.example.sisapsoo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;

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
        return;
    }

    @FXML
    void salvar(javafx.event.ActionEvent actionEvent) {
        labelSalvo.setVisible(true);
        return;
    }
}
