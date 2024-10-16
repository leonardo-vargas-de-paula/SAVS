package com.example.sisapsoo.model;

import com.example.sisapsoo.controller.EstoqueManager;
import com.example.sisapsoo.service.RelatorioGenerator;

import java.util.Map;

public class Gerente extends Funcionario implements RelatorioGenerator, EstoqueManager {

	private Relatorio[] relatorios;
	private Estoque estoque;

	public Gerente(String ID, String senha, String nome, String cpf, String telefone, double salario, Relatorio[] relatorios, Estoque estoque) {
		super(ID, senha, nome, cpf, telefone, salario);
		this.relatorios = relatorios;
		this.estoque = estoque;
	}

	public Relatorio[] getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(Relatorio[] relatorios) {
		this.relatorios = relatorios;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	@Override
	public void gerarRelatorioDiario() {
	}

	@Override
	public void gerarRelatorioMensal() {
	}

	@Override
	public void gerarRelatorioAnual() {
	}

	@Override
	public void atualizarEstoque() {
	}

	@Override
	public Map<String, Integer> getIngredientes() {
		return Map.of();
	}

	public void gerenciarFuncionario() {
	}

	public void alterarValoresPedido() {
	}
}
