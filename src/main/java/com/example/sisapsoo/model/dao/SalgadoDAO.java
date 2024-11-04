package com.example.sisapsoo.model.dao;

import com.example.sisapsoo.connection.ConnectionFactory;
import com.example.sisapsoo.model.Salgado;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SalgadoDAO {
    EntityManager em = new ConnectionFactory().getConnection();

    //insert-update
    public Salgado save(Salgado u) {
        try {
            em.getTransaction().begin();
            if (u.getIdSalgado() == null) {
                em.persist(u);
                System.out.println("SALVOU USUARIO ----------------------------------------------");
            } else {
                em.merge(u);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return u;
    }

    //select where id = <valor desejado>
    public Salgado findById(String id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Salgado u = null;

        try {
            u = em.find(Salgado.class, id);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return u;
    }

    //Lista de todos os objetos Tabelateste
    public List<Salgado> findAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Salgado> us = null;

        try {
            us = em.createQuery("from Salgado us").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return us;
    }

    //delete
    public Salgado remove(Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Salgado us = null;

        try {
            us = em.find(Salgado.class, id);
            em.getTransaction().begin();
            em.remove(us);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
        return us;

    }
}

