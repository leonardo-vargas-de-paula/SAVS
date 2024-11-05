package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Pedido;
import com.example.sisapsoo.model.PedidoSalgado;
import com.example.sisapsoo.model.Salgado;
import com.example.sisapsoo.model.dao.PedidoDAO;
//import com.example.sisapsoo.model.enums.Status;
import com.example.sisapsoo.model.enums.StatusPedido;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidosController implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private ComboBox<StatusPedido> comboboxStatus;

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

    private Pedido pedidoAtual;

    @FXML
    void addFunc(ActionEvent event) {
        trocarCena(event, "/com/example/sisapsoo/adicionar-pedidos-view.fxml");
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
        PedidoDAO pDAO = new PedidoDAO();
        List<Pedido> pedidos = pDAO.findAllPs();

        ObservableList<Pedido> observablePedidos = FXCollections.observableArrayList(pedidos);
        combobox1.setItems(observablePedidos);

        // Configura a aparência dos itens na lista da ComboBox
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

        // Configura a célula que exibe o item selecionado
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
        carregarPedidos();

        configurarTableView();

        configurarComboBoxStatus();

        // Adiciona o listener para atualizar a TableView ao selecionar um pedido
        combobox1.valueProperty().addListener((obs, oldPedido, newPedido) -> {
            if (newPedido != null) {
                // Carregar os salgados associados ao novo pedido selecionado
                pedidoAtual = newPedido;
                ObservableList<PedidoSalgado> salgados = FXCollections.observableArrayList(newPedido.getPedidoSalgados());
                tabelaPedidos.setItems(salgados);

                // Atualizar as labels com as informações do pedido
                nomeCliente.setText(newPedido.getCliente().getNome());
                statusPedido.setText(newPedido.getStatus());
                valorPedido.setText("R$ " + newPedido.getPreco());
            }
        });
    }

    @FXML
    public void removerPedido(ActionEvent event) {

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/sisapsoo/delete-pedido-dialog.fxml"));
            DialogPane funcDialogPane = fxmlLoader.load();

            DeletePedidoController deletePedidoController = fxmlLoader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(funcDialogPane);
            dialog.setTitle("Deletar Pedido");

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if(clickedButton.isPresent() && clickedButton.get() == ButtonType.OK){
                deletePedidoController.remover();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void configurarComboBoxStatus() {
        // Adiciona os valores do enum na ComboBox
        comboboxStatus.setItems(FXCollections.observableArrayList(StatusPedido.values()));

        // Define como a descrição será exibida na ComboBox
        comboboxStatus.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(StatusPedido status, boolean empty) {
                super.updateItem(status, empty);
                setText(empty || status == null ? null : status.getDescricao());
            }
        });

        // Configura o botão da ComboBox
        comboboxStatus.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(StatusPedido status, boolean empty) {
                super.updateItem(status, empty);
                setText(empty || status == null ? null : status.getDescricao());
            }
        });

        // Listener para atualizar o status do pedido ao mudar a seleção
        comboboxStatus.valueProperty().addListener((obs, oldStatus, newStatus) -> {
            if (newStatus != null && pedidoAtual != null) {
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoAtual.setStatus(newStatus.getDescricao()); // Atualiza o status do pedido atual
                pedidoDAO.save(pedidoAtual);

            }
        });


    }

    public void setPedidoAtual(Pedido pedido) {
        this.pedidoAtual = pedido;
        // Atualiza a ComboBox de status para o status atual do pedido
        comboboxStatus.setValue(StatusPedido.fromString(pedido.getStatus())); // Converte a String para StatusPedido
    }
}

