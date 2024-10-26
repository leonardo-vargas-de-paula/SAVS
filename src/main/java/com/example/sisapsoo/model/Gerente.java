package com.example.sisapsoo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Gerente")
public class Gerente extends Funcionario {

	public Gerente() {
		super();
	}

	public Gerente(String nome, String cpf, String senha, String telefone, double salario) {
		super(nome, cpf, senha, telefone, salario);
	}

}
