package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class AdicionaFuncionarioController {
    private GerenciamentoFuncs gerenciamentoFuncs;
    private FuncionarioDAO fDAO = new FuncionarioDAO();

    @FXML
    private TextField nomeField;

    @FXML
    private TextField cpfField;

    @FXML
    private Spinner<Double> salarioSpinner;

    @FXML
    private TextField telefoneField;

    public void setGerenciamentoFuncs(GerenciamentoFuncs gerenciamentoFuncs) {
        this.gerenciamentoFuncs = gerenciamentoFuncs;
    }

    @FXML
    public void initialize() {
        // define o intervalo e o incremento do spinner de salário
        SpinnerValueFactory<Double> valueFactory =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 100000.0, 0.0, 100.0);
        salarioSpinner.setValueFactory(valueFactory);
    }

    @FXML
    public void adicionarFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nomeField.getText());
        funcionario.setCpf(cpfField.getText());
        funcionario.setSalario(salarioSpinner.getValue());
        funcionario.setTelefone(telefoneField.getText());

        fDAO.save(funcionario);
        gerenciamentoFuncs.atualizarTabela();
    }
}
