package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.Salgado;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import com.example.sisapsoo.model.dao.SalgadoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CadastroSalgadoController {

    @FXML
    private Button botaoReiniciar;

    @FXML
    private Button botaoSalvar;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoPreco;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelPreco;

    @FXML
    private Label labelSalvo;

    @FXML
    private BorderPane mainPanel;

    @FXML
    private Pane painelCadastro;

    @FXML
    private Separator separador;

    @FXML
    private Label titulo;

    private Salgado s;
    private SalgadoDAO sDAO;

    @FXML
    void salvar(ActionEvent event) {
        s = new Salgado();
        sDAO = new SalgadoDAO();

        // Verifica se todos os campos estão preenchidos
        if(campoNome.getText().isEmpty() || campoPreco.getText().isEmpty()){
            showAlert("Campos vazios!", "Não deixe nenhum campo vazio.");
            return;
        }

        String nome = campoNome.getText();
        String preco = campoPreco.getText();

        try {
            s.setNome(nome);
            s.setPreco(Double.parseDouble(preco));

            sDAO.save(s);

            labelSalvo.setVisible(true);
            campoNome.setDisable(true);
            campoPreco.setDisable(true);
        }catch(Exception e){
            showAlert("Erro ao cadastrar: ", "" + e);
        }

        return;
    }

    /**
     * Limpa todos os campos
     */
    @FXML
    void reiniciar(javafx.event.ActionEvent actionEvent) {
        campoNome.clear();
        campoPreco.clear();

        campoNome.setDisable(false);
        campoPreco.setDisable(false);

        labelSalvo.setVisible(false);

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
