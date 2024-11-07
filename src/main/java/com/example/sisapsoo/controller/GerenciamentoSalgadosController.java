package com.example.sisapsoo.controller;


import com.example.sisapsoo.model.Salgado;
import com.example.sisapsoo.model.dao.SalgadoDAO;
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

public class GerenciamentoSalgadosController implements Initializable {
    private SalgadoDAO sDAO = new SalgadoDAO();

    @FXML
    private BorderPane borderPanel;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoRemover;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TableColumn<Salgado, Integer> idSalgado;

    @FXML
    private TableColumn<Salgado, String> nome;

    @FXML
    private Label labelTitulo;

    @FXML
    private Pane mainPanel;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableColumn<Salgado, Double> preco;

    @FXML
    private TableView<Salgado> tabelaSalgado;

    private LoginController loginController = new LoginController();

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
    void voltarHome(ActionEvent event) {
        trocarCena(event, "/com/example/sisapsoo/home-view.fxml");
    }

    @FXML
    void adicionar() {
        if(loginController.verificaGerente()) {
            try {
                FXMLLoader fxml_loader = new FXMLLoader();
                fxml_loader.setLocation(getClass().getResource("/com/example/sisapsoo/cadastro-salgado-dialog.fxml"));
                DialogPane cadastro_salgado = fxml_loader.load();

                CadastroSalgadoController add_salg_controller = fxml_loader.getController();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(cadastro_salgado);
                dialog.setTitle("Cadastrar Salgado");

                Optional<ButtonType> clicked_button = dialog.showAndWait();
                if (clicked_button.isPresent() && clicked_button.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                    ActionEvent event = new ActionEvent();
                    add_salg_controller.salvar(event);
                    atualizarTabela();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            showAlert("Acesso Negado", "Apenas gerentes podem cadastrar outros gerentes.");
    }

    public void atualizarTabela() {
        ObservableList<Salgado> salgados = FXCollections.observableArrayList(sDAO.findAll());
        tabelaSalgado.setItems(salgados);
    }

    @FXML
    void remover() {
        if(loginController.verificaGerente()) {
            try {
                FXMLLoader fxml_loader = new FXMLLoader();
                fxml_loader.setLocation(getClass().getResource("/com/example/sisapsoo/delete-salg-dialog.fxml"));
                DialogPane salgado_dialog = fxml_loader.load();

                DeleteSalgController delete_salg_controller = fxml_loader.getController();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(salgado_dialog);
                dialog.setTitle("Deletar Salgado");

                Optional<ButtonType> clicked_button = dialog.showAndWait();
                if (clicked_button.isPresent() && clicked_button.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                    ActionEvent event = new ActionEvent();
                    delete_salg_controller.remover(event);
                    atualizarTabela();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            showAlert("Acesso Negado", "Apenas gerentes podem cadastrar outros gerentes.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Salgado> salgados = FXCollections.observableArrayList(sDAO.findAll());

        idSalgado.setCellValueFactory(new PropertyValueFactory<>("idSalgado"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabelaSalgado.setItems(salgados);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
