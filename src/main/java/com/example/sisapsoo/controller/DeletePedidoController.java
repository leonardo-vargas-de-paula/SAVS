
package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.dao.PedidoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import javafx.event.ActionEvent;


public class DeletePedidoController {

    PedidoDAO pDAO = new PedidoDAO();

    @FXML
    private Label idLabel;

    @FXML
    private DialogPane dialogPanePedido;

    @FXML
    private TextField numeroPedido;

    @FXML
    private Button botaoOk;

    @FXML
    private Button botaoCancelar;


    @FXML
    public void remover(){
        if(numeroPedido.getText().isEmpty()){
            showAlert("Campos vazios!", "Não deixe nenhum campo vazio.");
            return;
        }

        Integer id = Integer.valueOf(numeroPedido.getText());

        try{
            pDAO.remove(id);
            Stage stage = (Stage) dialogPanePedido.getScene().getWindow();
            stage.close();
        }catch(Exception e){
            showAlert("Erro ao cadastrar: ", "" + e);
        }
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) dialogPanePedido.getScene().getWindow();
        stage.close();
    }
}