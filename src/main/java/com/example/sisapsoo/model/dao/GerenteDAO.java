package com.example.sisapsoo.model.dao;

import com.example.sisapsoo.connection.ConnectionFactory;
import com.example.sisapsoo.model.Gerente;
import jakarta.persistence.EntityManager;
import java.util.List;

public class GerenteDAO {
    EntityManager entityManager = new ConnectionFactory().getConnection();

    public Gerente save(Gerente gerente) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(gerente);
            System.out.println("SALVOU USUARIO ----------------------------------------------");
            entityManager.merge(gerente);

            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println("Erro ao salvar o gerente: " + exception.getMessage());
        } finally {
            entityManager.close();
        }

        return gerente;
    }

    //select where id = <valor desejado>
    public Gerente findById(String id) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        Gerente gerente = null;

        try {
            gerente = entityManager.find(Gerente.class, id);
        } catch (Exception exception) {
            System.err.println("Erro ao buscar gerente por ID: " + exception.getMessage());
        } finally {
            entityManager.close();
        }
        return gerente;
    }

    public List<Gerente> findAll() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        List<Gerente> gerentes = null;

        try {
            gerentes = entityManager.createQuery("from Gerente us").getResultList();
        } catch (Exception exception) {
            System.err.println(exception);
        } finally {
            entityManager.close();
        }
        return gerentes;
    }

    //delete
    public Gerente remove(int id) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        Gerente gerente = null;

        try {
            gerente = entityManager.find(Gerente.class, id);
            if (gerente != null) {
                this.entityManager.getTransaction().begin();
                entityManager.remove(gerente);
                entityManager.getTransaction().commit();
                System.out.println("Gerente removido com sucesso.");
            } else {
                System.out.println("Gerente não encontrado para remoção.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao remover gerente: " + e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return gerente;

    }
}
