package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.dao.SalgadoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteSalgController {
    private SalgadoDAO sDAO = new SalgadoDAO();

    @FXML
    private DialogPane dialogPane;

    @FXML
    private TextField idField;

    @FXML
    private Label labelId;

    void remover(){
        if(idField.getText().isEmpty()){
            showAlert("Campos vazios!", "Não deixe nenhum campo vazio.");
            return;
        }

        String id = idField.getText();

        try{
            sDAO.remove(Integer.parseInt(id));
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