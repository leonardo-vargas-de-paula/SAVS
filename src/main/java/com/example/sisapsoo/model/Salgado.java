package com.example.sisapsoo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Salgado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSalgado;

	private String nome;
	private double preco;
	// Getters e Setters

	public Integer getIdSalgado() {
		return idSalgado;
	}

	public void setIdSalgado(Integer idSalgado) {
		this.idSalgado = idSalgado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}