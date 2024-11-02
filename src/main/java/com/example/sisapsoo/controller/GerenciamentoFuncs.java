package com.example.sisapsoo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class GerenciamentoFuncs implements Initializable{
    private FuncionarioDAO fDAO = new FuncionarioDAO();

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
    private TableColumn<Funcionario, Integer> id;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Pane painelDeCima;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(fDAO.findAll());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        salario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tabela.setItems(funcionarios);
    }

    @FXML
    void addFunc() {
        // abrir uma tela tipo pop-up para cadastrar funcionário ou levar para a tela normal
    }

    @FXML
    void removerFunc(ActionEvent event) {
        /*ObservableList<Funcionario> all_funcs, single_func;
        all_funcs = tabela.getItems();
        single_func = tabela.getSelectionModel().getSelectedItems();
        single_func.forEach(all_funcs::remove);*/

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/sisapsoo/delete-func-dialog.fxml"));
            DialogPane funcDialogPane = fxmlLoader.load();
            
            DeleteFuncController deleteFuncController = fxmlLoader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(funcDialogPane);
            dialog.setTitle("Deletar Funcionário");

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if(clickedButton.get() == ButtonType.OK){
                deleteFuncController.remover();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
