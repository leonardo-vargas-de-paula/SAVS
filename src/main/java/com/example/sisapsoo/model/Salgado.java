package com.example.sisapsoo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Salgado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSalgado;

	private String nome;
	private float preco;
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

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

}