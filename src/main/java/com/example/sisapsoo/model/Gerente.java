package com.example.sisapsoo.model;

import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Gerente")
public class Gerente extends Funcionario {
	/* VARIÁVEIS DA CLASSE */

	@Id
	private String id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "telefone")
	private String telefone;
	@Column(name = "salario")
	private double salario;

	/* CONSTRUTORES */

	public Gerente(String ID, String nome, String cpf, String telefone, double salario) {
		super(ID, nome, cpf, telefone, salario);
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

	public String getSenha(){
		return super.getSenha();
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
    
    public void setSenha(String senha){
		super.setSenha(senha);
	}

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
