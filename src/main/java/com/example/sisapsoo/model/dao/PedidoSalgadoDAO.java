package com.example.sisapsoo.model.dao;

import com.example.sisapsoo.connection.ConnectionFactory;
import com.example.sisapsoo.model.PedidoSalgado;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PedidoSalgadoDAO {
    EntityManager em = new ConnectionFactory().getConnection();

    //insert-update
    public PedidoSalgado save(PedidoSalgado u) {
        try {
            em.getTransaction().begin();
            if (u.getId() == null) {
                em.persist(u);
                System.out.println("SALVOU PEDIDOSALGADO ----------------------------------------------");
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
    public PedidoSalgado findById(String id) {
        EntityManager em = new ConnectionFactory().getConnection();
        PedidoSalgado u = null;

        try {
            u = em.find(PedidoSalgado.class, id);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return u;
    }

    //Lista de todos os objetos Tabelateste
    public List<PedidoSalgado> findAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<PedidoSalgado> us = null;

        try {
            us = em.createQuery("from PedidoSalgado us").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return us;
    }

    //delete
    public PedidoSalgado remove(String id) {
        EntityManager em = new ConnectionFactory().getConnection();
        PedidoSalgado us = null;

        try {
            us = em.find(PedidoSalgado.class, id);
            em.getTransaction().begin();
            em.remove(us);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return us;

    }
}
