package com.example.sisapsoo.builder;

import com.example.sisapsoo.model.Funcionario;

public class FuncionarioBuilder {
    protected String id;
    protected String senha;
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected double salario;

    public FuncionarioBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public FuncionarioBuilder setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public FuncionarioBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public FuncionarioBuilder setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public FuncionarioBuilder setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public FuncionarioBuilder setSalario(double salario) {
        this.salario = salario;
        return this;
    }

    public Funcionario build() {
        Funcionario funcionario = new Funcionario(id, senha, nome, cpf, telefone, salario);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setTelefone(telefone);
        funcionario.setSalario(salario);
        return funcionario;
    }
}
