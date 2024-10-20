package com.example.sisapsoo.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    private String id;
    @Column(name = "senha")
    private String senha;
//    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
//    private Funcionario funcionario;

    /* CONSTRUTORES */

    public Usuario(){

    }

    public Usuario(String id){

    }

    public Usuario(String id, String senha){
        this.id = id;
        this.senha = senha;
    }

    /* GETTERS */

    public String getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    /* SETTERS */

    public void setId(String id) {
        this.id = id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
//
//    public Funcionario getFuncionario() {
//        return funcionario;
//    }
//
//    public void setFuncionario(Funcionario funcionario) {
//        this.funcionario = funcionario;
//    }
}

