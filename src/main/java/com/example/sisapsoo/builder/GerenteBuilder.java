package com.example.sisapsoo.builder;

import com.example.sisapsoo.model.Gerente;
import com.example.sisapsoo.model.Relatorio;
import com.example.sisapsoo.model.Estoque;

public class GerenteBuilder {
    private String id;
    private String senha;
    private String nome;
    private String cpf;
    private String telefone;
    private double salario;
    private Relatorio[] relatorios;
    private Estoque estoque;

    public GerenteBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public GerenteBuilder setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public GerenteBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public GerenteBuilder setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public GerenteBuilder setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public GerenteBuilder setSalario(double salario) {
        this.salario = salario;
        return this;
    }

    public GerenteBuilder setRelatorios(Relatorio[] relatorios) {
        this.relatorios = relatorios;
        return this;
    }

    public GerenteBuilder setEstoque(Estoque estoque) {
        this.estoque = estoque;
        return this;
    }

    public Gerente build() {
        return new Gerente(id, senha, nome, cpf, telefone, salario, relatorios, estoque);
    }
}
