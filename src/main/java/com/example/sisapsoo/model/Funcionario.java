package com.example.sisapsoo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Funcionario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Funcionario extends Usuario{
	/* VARIÁVEIS DA CLASSE */

    @Id
    private String id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "senha")
    private String senha;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "salario")
    private double salario;
    @OneToOne
    @JoinColumn(name = "usuario_fk", referencedColumnName = "id")
    private Usuario usuario;

	/* CONSTRUTORES */

    public Funcionario(String id, String nome, String senha, String cpf, String telefone, double salario){
        super(id, senha);
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.salario = salario;
    }

    public Funcionario(String id, String nome, String senha, String cpf, String telefone, double salario, Usuario usuario){
        super(id, senha);
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.salario = salario;
        this.usuario = usuario;
    }

	/* GETTERS */

    public String getId(){
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

    // public String getSenha(){
    //     return senha;
    // }

    public double getSalario() {
        return salario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

	/* SETTERS */

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    // public void setSenha(String senha){
	// 	this.senha = senha;
	// }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
