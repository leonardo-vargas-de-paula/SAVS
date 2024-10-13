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

	public void registrarPedido() {

	}

	private void realizarLogin() {

	}

	public void cadastrarCliente() {

	}

	private void alterarStatus() {

	}

	private void alterarInfoCliente() {

	}

	public void getNome() {

	}

	public void getCpf() {

	}

	public void getTelefone() {

	}

	public void getSalario() {

	}

	public void setSalario() {

	}

	public void setNome() {

	}

	public void setCpf() {

	}

	public void setTelefone() {

	}


	/**
	 * @see ClienteManager#alterarCliente()
	 */
	public void alterarCliente() {

	}


	/**
	 * @see ClienteManager#removerCliente()
	 */
	public void removerCliente() {

	}

}
