package com.example.sisapsoo.controller;

import java.net.URL;
import java.util.ResourceBundle;    
import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

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

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(fDAO.findAll());

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        salario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tabela.setItems(funcionarios);
    }
}
