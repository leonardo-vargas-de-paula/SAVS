package com.example.sisapsoo.model.dao;

import com.example.sisapsoo.connection.ConnectionFactory;
import com.example.sisapsoo.model.Gerente;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class GerenteDAO {

    public Gerente save(Gerente gerente) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        try {
            entityManager.getTransaction().begin();
            if (gerente.getId() == null) {
                entityManager.persist(gerente);
                System.out.println("Gerente salvo com sucesso.");
            } else {
                entityManager.merge(gerente);
            }
            entityManager.getTransaction().commit();
        } catch (Exception excecao) {
            entityManager.getTransaction().rollback();
            System.err.println("Erro ao salvar o gerente: " + excecao.getMessage());
        } finally {
            entityManager.close();
        }
        return gerente;
    }

    public Optional<Gerente> findById(String id) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        Gerente gerente = null;
        try {
            gerente = entityManager.find(Gerente.class, id);
        } catch (Exception excecao) {
            System.err.println("Erro ao buscar gerente por ID: " + excecao.getMessage());
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(gerente);
    }

    public List<Gerente> findAll() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        List<Gerente> listaGerentes = null;
        try {
            listaGerentes = entityManager.createQuery("from Gerente", Gerente.class).getResultList();
        } catch (Exception excecao) {
            System.err.println("Erro ao listar gerentes: " + excecao.getMessage());
        } finally {
            entityManager.close();
        }
        return listaGerentes;
    }

    public Optional<Gerente> remove(String id) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        Gerente gerente = null;
        try {
            gerente = entityManager.find(Gerente.class, id);
            if (gerente != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(gerente);
                entityManager.getTransaction().commit();
                System.out.println("Gerente removido com sucesso.");
            } else {
                System.out.println("Gerente não encontrado para remoção.");
            }
        } catch (Exception excecao) {
            System.err.println("Erro ao remover gerente: " + excecao.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(gerente);
    }
}
