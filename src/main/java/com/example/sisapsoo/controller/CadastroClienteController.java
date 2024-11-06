package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Cliente;
import com.example.sisapsoo.model.dao.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CadastroClienteController {

    @FXML
    private Button botaoReiniciar;

    @FXML
    private Button botaoSalvar;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoTelefone;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelSalvo;

    @FXML
    private Label labelTelefone;

    @FXML
    private BorderPane mainPanel;

    @FXML
    private Pane painelCadastro;

    @FXML
    private Separator separador;

    @FXML
    private Label titulo;

    private Cliente c = new Cliente();
    private ClienteDAO cDAO = new ClienteDAO();

    @FXML
    void reiniciar(ActionEvent event) {
        campoNome.clear();
        campoTelefone.clear();

        campoNome.setDisable(false);
        campoTelefone.setDisable(false);

        labelSalvo.setVisible(false);

        return;
    }

    @FXML
    void salvar(ActionEvent event) {
        c = new Cliente();
        cDAO = new ClienteDAO();

        // Verifica se todos os campos estão preenchidos
        if(campoNome.getText().isEmpty() || campoTelefone.getText().isEmpty()){
            showAlert("Campos vazios!", "Não deixe nenhum campo vazio.");
            return;
        }

        String nome = campoNome.getText();
        String telefone = campoTelefone.getText();

        try {
            c.setNome(nome);
            c.setTelefone(telefone);

            cDAO.save(c);

            labelSalvo.setVisible(true);
            campoNome.setDisable(true);
            campoTelefone.setDisable(true);
        }catch(Exception e){
            showAlert("Erro ao cadastrar: ", "" + e);
        }

        return;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
