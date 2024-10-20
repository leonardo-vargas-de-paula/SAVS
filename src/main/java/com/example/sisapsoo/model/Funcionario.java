package com.example.sisapsoo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Funcionario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario extends Usuario{
	/* VARIÁVEIS DA CLASSE */

    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "salario")
    private double salario;

	/* CONSTRUTORES */

//    public Funcionario(String id, String nome, String cpf, String telefone, double salario){
//
//        this.id = id;
//        this.nome = nome;
//        this.cpf = cpf;
//        this.telefone = telefone;
//        this.salario = salario;
//    }
//
//    public Funcionario(String id, String nome, String cpf, String telefone, double salario, Usuario usuario){
//
//        this.id = id;
//        this.nome = nome;
//        this.cpf = cpf;
//        this.telefone = telefone;
//        this.salario = salario;
//        this.usuario = usuario;
//    }

	/* GETTERS */


    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }


    public double getSalario() {
        return salario;
    }

//    public Usuario getUsuario() {
//        return usuario;
//    }

	/* SETTERS */

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
}
