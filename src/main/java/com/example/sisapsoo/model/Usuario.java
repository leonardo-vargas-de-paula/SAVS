package com.example.sisapsoo.model;

public abstract class Usuario {
    protected int id;
    protected String senha;

    /* CONSTRUTORES */

    public Usuario(){

    }

    public Usuario(int id){

    }

    public Usuario(int id, String senha){
        this.id = id;
        this.senha = senha;
    }

    /* GETTERS */

    public int getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    /* SETTERS */

    public void setId(int id) {
        this.id = id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

