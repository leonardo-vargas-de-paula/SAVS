package com.example.sisapsoo.model;

public class Usuario {
	private String ID;
	private String senha;
	private String nome;

	public Usuario(String ID, String senha) {
		this.ID = ID;
		this.senha = senha;
	}

	public Usuario(String ID, String senha, String nome) {
		this.ID = ID;
		this.senha = senha;
		this.nome = nome;
	}

	public String getId() {
		return ID;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public void setId(String id) {
		this.ID = id;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
