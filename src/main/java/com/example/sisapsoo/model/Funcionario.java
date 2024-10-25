package com.example.sisapsoo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Funcionario")
public class Funcionario extends Usuario{
	/* VARIÁVEIS DA CLASSE */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "func_sequence")
    @SequenceGenerator(sequenceName = "func_sequence", name = "func_seq")
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

	/* CONSTRUTORES */

    public Funcionario(){
        
    }

    public Funcionario(String nome, String cpf, String senha, String telefone, double salario){
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.telefone = telefone;
        this.salario = salario;
    }

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

    public String getSenha() {
        return this.senha;
    }

    public double getSalario() {
        return salario;
    }

	/* SETTERS */

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
}
