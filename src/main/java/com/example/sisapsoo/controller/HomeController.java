package com.example.sisapsoo.controller;


import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.Gerente;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    LoginController loginController = new LoginController();

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

    @FXML
    void clientes(ActionEvent event) {
        trocarCena(event, "/com/example/sisapsoo/gerenciamento-clientes-view.fxml");
    }

    @FXML
    void funcionarios(ActionEvent event) {
        trocarCena(event, "/com/example/sisapsoo/gerenciamento-funcionarios-view.fxml");
    }

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    void pedidos(ActionEvent event) {
        trocarCena(event, "/com/example/sisapsoo/pedidos-view.fxml");
    }

    @FXML
    void sairSistema(ActionEvent event) {
        Platform.exit();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void salgados(ActionEvent event) {
        trocarCena(event, "/com/example/sisapsoo/gerenciamento-salgados-view.fxml");
    }

}
