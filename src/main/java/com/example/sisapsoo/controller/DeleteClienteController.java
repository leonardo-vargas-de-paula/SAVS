package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.dao.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DeleteClienteController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField cpfField;

    @FXML
    private Label cpfLabel;

    @FXML
    private DialogPane dialogPane;

    private ClienteDAO cDAO = new ClienteDAO();

    @FXML
    void remover(ActionEvent event){
        if(cpfField.getText().isEmpty()){
            showAlert("Campos vazios!", "Não deixe nenhum campo vazio.");
            return;
        }

        String cpf = cpfField.getText();

        try{
            cDAO.remove(Integer.parseInt(cpf));
        }catch(Exception e){
            showAlert("Erro ao deletar: ", "" + e);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
