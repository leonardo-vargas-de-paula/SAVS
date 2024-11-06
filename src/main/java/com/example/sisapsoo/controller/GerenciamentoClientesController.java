package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Cliente;
import com.example.sisapsoo.model.dao.ClienteDAO;
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
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerenciamentoClientesController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private BorderPane borderPanel;

    @FXML
    private Button buttonVoltar;

    @FXML
    private TableColumn<Cliente, Integer> id;

    @FXML
    private Label labelTitulo;

    @FXML
    private Pane mainPanel;

    @FXML
    private TableColumn<Cliente, String> nome;

    @FXML
    private Pane painelDeCima;

    @FXML
    private Button removeButton;

    @FXML
    private TableView<Cliente> tabela;

    @FXML
    private TableColumn<Cliente, String> telefone;

    private ClienteDAO cDAO = new ClienteDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(cDAO.findAll());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tabela.setItems(clientes);
    }

    @FXML
    void add() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/sisapsoo/cadastro-cliente-dialog.fxml"));
            DialogPane clienteDialog = fxmlLoader.load();

            CadastroClienteController cadastrarClienteController = fxmlLoader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(clienteDialog);
            dialog.setTitle("Cadastrar Cliente");

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if(clickedButton.isPresent() && clickedButton.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
                ActionEvent event = new ActionEvent();
                cadastrarClienteController.salvar(event);
                atualizarTabela();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void remover() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/sisapsoo/delete-cliente-dialog.fxml"));
            DialogPane clienteDialog = fxmlLoader.load();

            DeleteClienteController deleteClienteController = fxmlLoader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(clienteDialog);
            dialog.setTitle("Deletar Cliente");

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if(clickedButton.isPresent() && clickedButton.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
                ActionEvent event = new ActionEvent();
                deleteClienteController.remover(event);
                atualizarTabela();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void atualizarTabela() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(cDAO.findAll());
        tabela.setItems(clientes);
    }

    @FXML
    void voltar(ActionEvent event) {
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
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, "Erro ao carregar a cena: " + fxml, e);
        }
    }
}
