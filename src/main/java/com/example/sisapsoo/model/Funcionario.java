package com.example.sisapsoo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Funcionario")
public class Funcionario extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "senha")
    private String senha;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "salario")
    private double salario;

    @Column(name = "tipoFuncionario") // Novo campo
    private String tipoFuncionario;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, String telefone, double salario, String tipoFuncionario) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.salario = salario;
        this.tipoFuncionario = tipoFuncionario;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSenha() {
        return this.senha;
    }

    public double getSalario() {
        return salario;
    }

    public String getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setTipoFuncionario(String tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }
}
