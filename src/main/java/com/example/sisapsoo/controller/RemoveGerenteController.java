package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.Gerente;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import com.example.sisapsoo.model.dao.GerenteDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RemoveGerenteController {
    private GerenteDAO gerenteDAO = new GerenteDAO();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Integer idAtual;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField idField;

    @FXML
    private Label idLabel;

    @FXML
    private DialogPane dialogPane;

    public void setIdAtual(int idAtual) {
        this.idAtual = idAtual;
    }

    @FXML
    void remover() {
        if (idField.getText().isEmpty()) {
            showAlert("Campos vazios!", "Não deixe nenhum campo vazio.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idField.getText());
        } catch (NumberFormatException exception) {
            showAlert("Erro", "O ID deve ser um número válido.");
            return;
        }

        // usuário logado tem permissão para remover um gerente?
        if (!isGerenteAutorizado()) {
            showAlert("Acesso Negado", "Apenas gerentes podem remover outros gerentes.");
            return;
        }

        try {
            gerenteDAO.remove(id);
            showAlert("Sucesso", "Gerente removido com sucesso.");
        } catch (Exception exception) {
            showAlert("Erro ao remover: ", "" + exception.getMessage());
        }
    }

    private boolean isGerenteAutorizado() {
        if (idAtual == null) {
            return false;
        }
        Funcionario funcionarioAtual = funcionarioDAO.findById(idAtual);
        return funcionarioAtual instanceof Gerente; // Verifica se o usuário atual é um gerente
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
