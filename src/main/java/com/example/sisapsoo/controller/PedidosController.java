package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Pedido;
import com.example.sisapsoo.model.PedidoSalgado;
import com.example.sisapsoo.model.Salgado;
import com.example.sisapsoo.model.dao.PedidoDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidosController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private BorderPane borderPanel;

    @FXML
    private Button buttonVoltar;

    @FXML
    private ComboBox<Pedido> combobox1;

    @FXML
    private Label labelTitulo;

    @FXML
    private Pane mainPanel;

    @FXML
    private Pane painelDeCima;

    @FXML
    private TableColumn<PedidoSalgado, Integer> quantidade;

    @FXML
    private Button removeButton;

    @FXML
    private TableColumn<PedidoSalgado, String> salgado;

    @FXML
    private TableView<PedidoSalgado> tabelaPedidos;

    @FXML
    private Label nomeCliente;

    @FXML
    private Label statusPedido;

    @FXML
    private Label valorPedido;

    @FXML
    void addFunc(ActionEvent event) {

    }

    @FXML
    void removerFunc(ActionEvent event) {

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

    private void carregarPedidos(){
        Pedido p = new Pedido();
        PedidoDAO pDAO = new PedidoDAO();

        List<Pedido> pedidos = pDAO.findAllPs();


        ObservableList<Pedido> observablePedidos = FXCollections.observableArrayList(pedidos);
        combobox1.setItems(observablePedidos);

        // Define o rótulo exibido no ComboBox
        combobox1.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Pedido pedido, boolean empty) {
                super.updateItem(pedido, empty);
                if (empty || pedido == null) {
                    setText(null);
                } else {
                    setText("Pedido " + pedido.getIdPedido());
                }
            }
        });
        combobox1.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Pedido pedido, boolean empty) {
                super.updateItem(pedido, empty);
                if (empty || pedido == null) {
                    setText(null);
                } else {
                    setText("Pedido " + pedido.getIdPedido());
                }
            }
        });
    }

    private void configurarTableView() {
        salgado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSalgado().getNome())
        );
        quantidade.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getQuantidade()).asObject()
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Carrega os pedidos no ComboBox
        carregarPedidos();  //Metodo para carregar os pedidos no ComboBox

        // Configura as colunas da TableView (nome, quantidade e preço do salgado)
        configurarTableView();

        // Adiciona o listener para atualizar a TableView ao selecionar um pedido
        combobox1.valueProperty().addListener((obs, oldPedido, newPedido) -> {
            if (newPedido != null) {
                // Carregar os salgados associados ao novo pedido selecionado
                ObservableList<PedidoSalgado> salgados = FXCollections.observableArrayList(newPedido.getPedidoSalgados());
                tabelaPedidos.setItems(salgados);

                // Atualizar as labels com as informações do pedido
                nomeCliente.setText(newPedido.getCliente().getNome());
                statusPedido.setText(newPedido.getStatus());
                valorPedido.setText("R$ " + newPedido.getPreco());
            }
        });
    }


}
