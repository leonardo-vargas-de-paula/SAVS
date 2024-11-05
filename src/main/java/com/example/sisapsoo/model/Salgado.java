package com.example.sisapsoo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Salgado")
public class Salgado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSalgado;
	@Column(name = "nome")
	private String nome;
	@Column(name = "preco")
	private double preco;

	// CONSTRUTORES
	public Salgado() {

	}

	public Salgado(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	// GETTER
	public Integer getIdSalgado() {
		return idSalgado;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	// SETTER
	public void setIdSalgado(Integer idSalgado) {
		this.idSalgado = idSalgado;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}