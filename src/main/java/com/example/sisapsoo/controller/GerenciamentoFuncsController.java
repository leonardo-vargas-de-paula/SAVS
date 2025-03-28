package com.example.sisapsoo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.dao.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GerenciamentoFuncsController implements Initializable {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @FXML
    private BorderPane borderPanel;

    @FXML
    private Label labelTitulo;

    @FXML
    private Pane mainPanel;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableView<Funcionario> tabela;

    @FXML
    private TableColumn<Funcionario, String> nome;

    @FXML
    private TableColumn<Funcionario, String> cpf;

    @FXML
    private TableColumn<Funcionario, String> salario;

    @FXML
    private TableColumn<Funcionario, String> telefone;

    @FXML
    private TableColumn<Funcionario, String> colunaTipo;

    @FXML
    private TableColumn<Funcionario, Integer> id;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Pane painelDeCima;

    private LoginController loginController = new LoginController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(funcionarioDAO.findAll());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        salario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipoFuncionario"));

        tabela.setItems(funcionarios);
    }

    @FXML
    private void voltar(ActionEvent event) {
        trocarCena(event, "/com/example/sisapsoo/home-view.fxml");
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
            Logger.getLogger(GerenciamentoFuncsController.class.getName()).log(Level.SEVERE, "Erro ao carregar a cena: " + fxml, e);
        }
    }

    @FXML
    void addFunc() {
        if(loginController.verificaGerente())
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sisapsoo/cadastro-func-dialog.fxml"));
                DialogPane dialogPane = fxmlLoader.load();

                CadastroFuncController controller = fxmlLoader.getController();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(dialogPane);
                dialog.setTitle("Adicionar Funcionário");

                Optional<ButtonType> result = dialog.showAndWait();

                if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                    ActionEvent evento = new ActionEvent();
                    controller.salvar(evento);
                    atualizarTabela();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        else
            showAlert("Acesso Negado", "Apenas gerentes podem cadastrar outros gerentes.");
    }

    public void atualizarTabela() {
        ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(funcionarioDAO.findAll());
        tabela.setItems(funcionarios);
    }

    @FXML
    void removerFunc(ActionEvent event) {
        if(loginController.verificaGerente())
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sisapsoo/delete-func-dialog.fxml"));
                DialogPane funcDialogPane = fxmlLoader.load();

                DeleteFuncController deleteFuncController = fxmlLoader.getController();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(funcDialogPane);
                dialog.setTitle("Deletar Funcionário");

                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    deleteFuncController.remover(); // Executa remoção
                    atualizarTabela(); // Atualiza tabela após remoção
                }
            } catch (IOException e) {
                Logger.getLogger(GerenciamentoFuncsController.class.getName()).log(Level.SEVERE, "Erro ao carregar o diálogo de deletar funcionário.", e);
            }
        else
            showAlert("Acesso Negado", "Apenas gerentes podem cadastrar outros gerentes.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}