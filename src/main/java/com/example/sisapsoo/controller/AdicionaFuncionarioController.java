package com.example.sisapsoo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.dao.FuncionarioDAO;

public class AdicionaFuncionarioController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField salarioField;

    @FXML
    private TextField telefoneField;

    @FXML
    private RadioButton funcionarioRadio;

    @FXML
    private RadioButton gerenteRadio;

    private ToggleGroup roleGroup;
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private GerenciamentoFuncs gerenciamentoFuncs;

    @FXML
    public void initialize() {
        roleGroup = new ToggleGroup();
        funcionarioRadio.setToggleGroup(roleGroup);
        gerenteRadio.setToggleGroup(roleGroup);
    }

    public void setGerenciamentoFuncs(GerenciamentoFuncs gerenciamentoFuncs) {
        this.gerenciamentoFuncs = gerenciamentoFuncs;
    }

    @FXML
    private TextField senhaField;

    @FXML
    protected void adicionarFuncionario() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String telefone = telefoneField.getText();
        String senha = senhaField.getText();
        String tipoFuncionario = funcionarioRadio.isSelected() ? "Funcionario" : "Gerente";

        double salario;
        try {
            salario = Double.parseDouble(salarioField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Formato");
            alert.setHeaderText("Formato Inválido");
            alert.setContentText("Por favor, insira um valor numérico válido para o salário.");
            alert.showAndWait();
            return;
        }

        Funcionario novoFuncionario = new Funcionario(nome, cpf, telefone, salario, tipoFuncionario);
        funcionarioDAO.save(novoFuncionario);
        gerenciamentoFuncs.atualizarTabela();

        fecharDialog();
    }

    @FXML
    private void cancelar() {
        fecharDialog();
    }

    private void fecharDialog() {
        Stage stage = (Stage) nomeField.getScene().getWindow();
        stage.close();
    }
}
