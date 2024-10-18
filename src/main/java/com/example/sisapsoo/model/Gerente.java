package com.example.sisapsoo.model;

import java.util.Map;

public class Gerente extends Funcionario {
	/* VARIÁVEIS DA CLASSE */

	private String id;
	private String nome;
	//private String senha;
	private String cpf;
	private String telefone;
	private double salario;

	/* CONSTRUTORES */

	public Gerente(String ID, String nome, String senha, String cpf, String telefone, double salario) {
		super(ID, nome, senha, cpf, telefone, salario);
		this.id = ID;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.salario = salario;
	}

	/* GETTERS */

	public String getId(){
		return id;
	}

	public String getNome(){
		return nome;
	}

	public String getCpf(){
		return cpf;
	}

	public String getTelefone(){
		return telefone;
	}

	public double getSalario(){
		return salario;
	}

	/* SETTERS */

	public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    // public void setSenha(String senha){
	// 	this.senha = senha;
	// }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

	
	public void gerenciarFuncionario() {
	}

	public void alterarValoresPedido() {
	}
}
