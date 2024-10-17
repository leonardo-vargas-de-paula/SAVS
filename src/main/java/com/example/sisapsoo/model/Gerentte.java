package com.example.sisapsoo.model;

import com.example.sisapsoo.controller.EstoqueManager;
import com.example.sisapsoo.model.teste.Funcionario2;
import com.example.sisapsoo.service.RelatorioGenerator;

import java.util.Map;

public class Gerentte extends Funcionario2 implements RelatorioGenerator, EstoqueManager {

	private Relatorio[] relatorio;

	private Estoque estoque;

	private void gerenciarEstoque() {

	}

	private void gerenciarRelatorio() {

	}

	private void gerenciarFuncionario() {

	}

	private void alterarValoresPedido() {

	}

	@Override
	public void cadastrarCliente() {

	}

	public void removerCliente() {

	}

	@Override
	public void atualizarEstoque() {

	}

	@Override
	public Map getIngredientes() {
		return Map.of();
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

	/*
	/**
	 * @see RelatorioGenerator#gerarRelatorioDiario()
	 /
	private void gerarRelatorioDiario() {

	}


	/**
	 * @see RelatorioGenerator#gerarRelatorioMensal()
	 /
	private void gerarRelatorioMensal() {

	}


	/**
	 * @see RelatorioGenerator#gerarRelatorioAnual()
	 /
	private void gerarRelatorioAnual() {

	}


	/**
	 * @see EstoqueManager#atualizarEstoque()
	 /
	private void atualizarEstoque() {

	}


	/**
	 * @see EstoqueManager#getIngredientes()
	 /
	private Map getIngredientes() {
		return null;
	}
	*/
}
