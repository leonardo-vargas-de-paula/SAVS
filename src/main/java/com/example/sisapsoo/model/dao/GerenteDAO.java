package com.example.sisapsoo.model.dao;

import com.example.sisapsoo.connection.ConnectionFactory;
import com.example.sisapsoo.model.Gerente;
import jakarta.persistence.EntityManager;
import java.util.List;

public class GerenteDAO {
    EntityManager em = new ConnectionFactory().getConnection();

    public Gerente save(Gerente u) {
        try {
            em.getTransaction().begin();
            em.persist(u);
            System.out.println("SALVOU USUARIO ----------------------------------------------");
            em.merge(u);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar o gerente: " + e.getMessage());
        } finally {
            em.close();
        }

        return u;
    }

    //select where id = <valor desejado>
    public Gerente findById(String id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Gerente u = null;

        try {
            u = em.find(Gerente.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar gerente por ID: " + e.getMessage());
        } finally {
            em.close();
        }
        return u;
    }

    public List<Gerente> findAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Gerente> us = null;

        try {
            us = em.createQuery("from Gerente us").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return us;
    }

    //delete
    public Gerente remove(String id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Gerente us = null;

        try {
            us = em.find(Gerente.class, id);
            if (us != null) {
                em.getTransaction().begin();
                em.remove(us);
                em.getTransaction().commit();
                System.out.println("Gerente removido com sucesso.");
            } else {
                System.out.println("Gerente não encontrado para remoção.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao remover gerente: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return us;

    }
}
