package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Cliente;
import com.example.sisapsoo.model.Pedido;
import com.example.sisapsoo.model.PedidoSalgado;
import com.example.sisapsoo.model.Salgado;
import com.example.sisapsoo.model.Endereco;
import com.example.sisapsoo.model.dao.ClienteDAO;
import com.example.sisapsoo.model.dao.PedidoDAO;
import com.example.sisapsoo.model.dao.SalgadoDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdicionarPedidosController {

    @FXML
    private ComboBox<Cliente> comboboxCliente;
    @FXML
    private ComboBox<Salgado> comboboxSalgado;
    @FXML
    private TextField textFieldQuant;
    @FXML
    private TextField textFieldRua;
    @FXML
    private TextField textFieldNumero;
    @FXML
    private TableView<PedidoSalgado> tabelaPedidos;
    @FXML
    private TableColumn<PedidoSalgado, String> salgado;
    @FXML
    private TableColumn<PedidoSalgado, Integer> quantidade;
    @FXML
    private Button botaoSalvar;
    @FXML
    private Button botaoReiniciar;
    @FXML
    private Button botaoSalvarEndereco;
    @FXML
    private Button buttonVoltar;

    private Pedido pedidoAtual;
    private ObservableList<PedidoSalgado> listaSalgados;

    @FXML

    private void configurarTableView() {
        salgado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSalgado().getNome())
        );
        quantidade.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getQuantidade()).asObject()
        );
    }

    private void carregarClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.findAll();

        ObservableList<Cliente> observableClientes = FXCollections.observableArrayList(clientes);
        comboboxCliente.setItems(observableClientes);

        // Define a aparência dos itens na lista da ComboBox
        comboboxCliente.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Cliente cliente, boolean empty) {
                super.updateItem(cliente, empty);
                if (empty || cliente == null) {
                    setText(null);
                } else {
                    setText(cliente.getNome()); // Exibe o nome do cliente
                }
            }
        });

        // Define a célula que exibe o item selecionado
        comboboxCliente.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Cliente cliente, boolean empty) {
                super.updateItem(cliente, empty);
                if (empty || cliente == null) {
                    setText(null);
                } else {
                    setText(cliente.getNome());
                }
            }
        });
    }

    private void carregarSalgados() {
        SalgadoDAO salgadoDAO = new SalgadoDAO();
        List<Salgado> salgados = salgadoDAO.findAll();

        ObservableList<Salgado> observableSalgados = FXCollections.observableArrayList(salgados);
        comboboxSalgado.setItems(observableSalgados);

        // Define a aparência dos itens na lista da ComboBox
        comboboxSalgado.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Salgado salgado, boolean empty) {
                super.updateItem(salgado, empty);
                if (empty || salgado == null) {
                    setText(null);
                } else {
                    setText(salgado.getNome()); // Exibe o nome do salgado
                }
            }
        });

        // Define a célula que exibe o item selecionado
        comboboxSalgado.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Salgado salgado, boolean empty) {
                super.updateItem(salgado, empty);
                if (empty || salgado == null) {
                    setText(null);
                } else {
                    setText(salgado.getNome());
                }
            }
        });
    }

    public void initialize() {
        carregarClientes();
        carregarSalgados();
        configurarTableView();
        pedidoAtual = new Pedido();

        listaSalgados = FXCollections.observableArrayList();
        tabelaPedidos.setItems(listaSalgados);

        comboboxSalgado.valueProperty().addListener((obs, oldSalgado, newSalgado) -> {
            if (newSalgado != null && !textFieldNumero.getText().isEmpty()) {
                try {
                    PedidoSalgado ps = new PedidoSalgado();
                    int quantidade = Integer.parseInt(textFieldNumero.getText());
//                    PedidoSalgado pedidoSalgado = new PedidoSalgado(newSalgado, quantidade);
                    ps.setSalgado(newSalgado);
                    ps.setQuantidade(quantidade);

                    // Adiciona o item na TableView
                    tabelaPedidos.getItems().add(ps);

                    // Limpa o campo de quantidade após a adição
                    textFieldNumero.clear();
                } catch (NumberFormatException e) {
                    // Trata caso de entrada inválida no campo de quantidade
                    System.out.println("Insira um número válido para a quantidade.");
                }
            }
        });

        // Adiciona ação ao botão 'Salvar' para persistir o pedido no banco de dados
        botaoSalvarEndereco.setOnAction(event -> salvarPedido());
    }

    @FXML
    private void salvarSalgado() {
        Cliente cliente = comboboxCliente.getSelectionModel().getSelectedItem();
        Salgado salgado = comboboxSalgado.getSelectionModel().getSelectedItem();
        int quantidade = Integer.parseInt(textFieldQuant.getText());


        if (cliente != null && salgado != null && quantidade > 0) {
            pedidoAtual.setCliente(cliente);

            PedidoSalgado pedidoSalgado = new PedidoSalgado();
            pedidoSalgado.setSalgado(salgado);
            pedidoSalgado.setQuantidade(quantidade);
            pedidoSalgado.setPedido(pedidoAtual);

            listaSalgados.add(pedidoSalgado);
            pedidoAtual.getPedidoSalgados().add(pedidoSalgado);


            tabelaPedidos.refresh();
        } else {
            // Exibe uma mensagem de erro
            exibirAlerta("Erro", "Todos os campos são obrigatórios!");
        }
    }

    @FXML
    private void reiniciarPedido() {
        pedidoAtual = new Pedido();
        listaSalgados.clear();
        comboboxCliente.getSelectionModel().clearSelection();
        comboboxSalgado.getSelectionModel().clearSelection();
        textFieldQuant.clear();
        textFieldRua.clear();
        textFieldNumero.clear();
    }

    @FXML
    private void salvarPedido() {
        PedidoDAO pDAO = new PedidoDAO();
        Integer numero = Integer.valueOf(textFieldNumero.getText());

        if (pedidoAtual.getCliente() != null && !listaSalgados.isEmpty()) {
            Endereco endereco = new Endereco();
            endereco.setRua(textFieldRua.getText());
            endereco.setNumero(numero);
            pedidoAtual.setLoc(endereco);
            pedidoAtual.setPreco(pedidoAtual.calcularPrecoTotal());
            pedidoAtual.setStatus("Em espera");

            // Salvar pedido no banco de dados (exemplo fictício)
            pDAO.save(pedidoAtual);

            exibirAlerta("Sucesso", "Pedido salvo com sucesso!");
            reiniciarPedido();
        } else {
            exibirAlerta("Erro", "Selecione um cliente e adicione pelo menos um salgado.");
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
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
            Logger.getLogger(AdicionarPedidosController.class.getName()).log(Level.SEVERE, "Erro ao carregar a cena: " + fxml, e);
        }
    }

    @FXML
    private void voltar(ActionEvent event) {
        trocarCena(event, "/com/example/sisapsoo/gerenciamento-pedidos-view.fxml");
    }

    //teste
}
