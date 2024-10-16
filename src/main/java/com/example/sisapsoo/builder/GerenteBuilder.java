package com.example.sisapsoo.builder;

import com.example.sisapsoo.model.Estoque;
import com.example.sisapsoo.model.Gerente;
import com.example.sisapsoo.model.Relatorio;

public class GerenteBuilder extends FuncionarioBuilder {
    private Relatorio[] relatorios;
    private Estoque estoque;

    public GerenteBuilder setRelatorios(Relatorio[] relatorios) {
        this.relatorios = relatorios;
        return this;
    }

    public GerenteBuilder setEstoque(Estoque estoque) {
        this.estoque = estoque;
        return this;
    }

    @Override
    public Gerente build() {
        Gerente gerente = new Gerente(id, senha, nome, cpf, telefone, salario, relatorios, estoque);
        gerente.setNome(nome);
        gerente.setCpf(cpf);
        gerente.setTelefone(telefone);
        gerente.setSalario(salario);
        gerente.setRelatorios(relatorios);
        gerente.setEstoque(estoque);
        return gerente;
    }
}
