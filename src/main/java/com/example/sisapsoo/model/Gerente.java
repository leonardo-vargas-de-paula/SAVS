package com.example.sisapsoo.model;

import java.util.Map;

import jakarta.persistence.*;

@Entity
@Table(name = "Gerente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Gerente extends Funcionario {
	/* VARIÁVEIS DA CLASSE */
	@Column(name = "nome")
	private String nome;
	@Column(name = "cpf")
	private String cpf;

	/* CONSTRUTORES */

//	public Gerente(String ID, String nome, String cpf, String telefone, double salario) {
//		super(ID, nome, cpf, telefone, salario);
//		this.id = ID;
//		this.nome = nome;
//		this.cpf = cpf;
//		this.telefone = telefone;
//		this.salario = salario;
//	}

	/* GETTERS */

//	public String getId(){
//		return id;
//	}

//	public String getNome(){
//		return nome;
//	}
//
//	public String getCpf(){
//		return cpf;
//	}

//	public String getSenha(){
//		return super.getSenha();
//	}

//	public String getTelefone(){
//		return telefone;
//	}
//
//	public double getSalario(){
//		return salario;
//	}

	/* SETTERS */

//	public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }

//    public void setSenha(String senha){
//		super.setSenha(senha);
//	}

//    public void setTelefone(String telefone) {
//        this.telefone = telefone;
//    }
//
//    public void setSalario(double salario) {
//        this.salario = salario;
//    }



}
