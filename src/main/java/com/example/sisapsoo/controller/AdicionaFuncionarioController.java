package com.example.sisapsoo.controller;

import com.example.sisapsoo.model.Funcionario;
import com.example.sisapsoo.model.dao.FuncionarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AdicionaFuncionarioController {

    private GerenciamentoFuncs gerenciamentoFuncs;
    private FuncionarioDAO funcionarioDAO;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField salarioField;

    @FXML
    private TextField telefoneField;

    // Construtor para garantir que FuncionarioDAO é inicializado
    public AdicionaFuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public void setGerenciamentoFuncs(GerenciamentoFuncs gerenciamentoFuncs) {
        this.gerenciamentoFuncs = gerenciamentoFuncs;
    }

    @FXML
    public void initialize() {
        // Listener para restringir entrada a números e pontos decimais
        salarioField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                salarioField.setText(oldValue);
            }
        });
    }

    @FXML
    public void adicionarFuncionario() {
        // Verifica se GerenciamentoFuncs foi injetado corretamente
        if (gerenciamentoFuncs == null) {
            System.err.println("Erro: GerenciamentoFuncs não foi configurado.");
            return;
        }

        // Cria e preenche o objeto Funcionario
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nomeField.getText());
        funcionario.setCpf(cpfField.getText());
        funcionario.setTelefone(telefoneField.getText());

        // Valida e define o salário
        try {
            double salario = Double.parseDouble(salarioField.getText());
            funcionario.setSalario(salario);
        } catch (NumberFormatException exception) {
            System.out.println("Erro: O salário deve ser um número válido.");
            return;
        }

        // Tenta salvar o funcionário no banco de dados
        try {
            funcionarioDAO.save(funcionario);
            System.out.println("Funcionário adicionado com sucesso.");
            gerenciamentoFuncs.atualizarTabela(); // Atualiza a tabela na interface
        } catch (Exception e) {
            System.err.println("Erro ao adicionar funcionário:");
            e.printStackTrace(); // Log completo da exceção
        }
    }
}
