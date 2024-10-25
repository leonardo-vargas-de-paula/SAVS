package com.example.sisapsoo.controller;

import jakarta.persistence.criteria.Root;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

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
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/sisapsoo/login-view.fxml"))); //teste
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
