package com.example.sisapsoo.model;

public abstract class Usuario {
    protected String id;
    protected String senha;

    public Usuario(){
    }

    public Usuario(String id){
    }

    public Usuario(String id, String senha){
        this.id = id;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

