package com.example.sisapsoo.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Button botaoClientes;

    @FXML
    private Button botaoFuncionarios;

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoPedidos;

    @FXML
    private Button botaoSair;

    @FXML
    private Button botaoSalgados;

    @FXML
    private Label cargoFuncionario;

    @FXML
    private Label nomeUsuario;

    @FXML
    void clientes(ActionEvent event) {

    }

    @FXML
    void funcionarios(ActionEvent event) {

    }

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    void pedidos(ActionEvent event) {

    }

    @FXML
    void sairSistema(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void salgados(ActionEvent event) {

    }

}
