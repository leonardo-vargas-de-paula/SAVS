package com.example.sisapsoo.model;

import com.example.sisapsoo.controller.ClienteManager;
import com.example.sisapsoo.controller.PedidoManager;
import com.example.sisapsoo.repository.ClienteRepository;
import com.example.sisapsoo.repository.PedidoRepository;

public class Funcionario extends Usuario implements ClienteManager, PedidoManager {

	private String nome;
	private String cpf;
	private String telefone;
	private double salario;

	private Cliente cliente;
	private ClienteRepository clienteRepository;
	private PedidoRepository pedidoRepository;

	public Funcionario(String id, String senha, String nome, String cpf, String telefone, double salario) {
		super(id, senha);
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public void alterarCliente() {
	}

	@Override
	public void removerCliente() {
	}

	public void registrarPedido() {
	}

	public void cadastrarCliente() {
	}
}
