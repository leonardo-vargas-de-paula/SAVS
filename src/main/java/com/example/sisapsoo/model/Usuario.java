package com.example.sisapsoo.model;

public class Usuario {

	private String ID;
	private String senha;

	public Usuario(String ID, String senha) {
		this.ID = ID;
		this.senha = senha;
	}

	public String getId() {
		return ID;
	}

	public String getSenha() {
		return senha;
	}

	public void setId(String ID) {
		this.ID = ID;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void login() {
	}
}
