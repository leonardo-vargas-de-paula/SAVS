package com.example.sisapsoo.builder;

import com.example.sisapsoo.model.Funcionario;

public class FuncionarioBuilder {
    private String id;
    private String senha;
    private String nome;
    private String cpf;
    private String telefone;
    private double salario;

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
        return new Funcionario(id, senha, nome, cpf, telefone, salario);
    }
}
