package com.example.sisapsoo.model;

import java.util.Map;

import jakarta.persistence.*;

@Entity
public class Gerente extends Funcionario {

	public Gerente() {
		super();
	}

	public Gerente(String nome, String cpf, String senha, String telefone, double salario) {
		super(nome, cpf, senha, telefone, salario);
	}

}
