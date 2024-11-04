package com.example.sisapsoo.model.dao;

import com.example.sisapsoo.connection.ConnectionFactory;
import com.example.sisapsoo.model.Pedido;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PedidoDAO {
    EntityManager em = new ConnectionFactory().getConnection();

    //insert-update
    public Pedido save(Pedido u) {
        EntityManager em = new ConnectionFactory().getConnection();
        try {
            em.getTransaction().begin();
            if (u.getIdPedido() == null) {
                em.persist(u); // insere novo pedido
            } else {
                u = em.merge(u); // atualiza o pedido existente
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return u;
    }

    //select where id = <valor desejado>
    public Pedido findById(String id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Pedido u = null;

        try {
            u = em.find(Pedido.class, id);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return u;
    }

    //Lista de todos os objetos Tabelateste
    public List<Pedido> findAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Pedido> us = null;

        try {
            us = em.createQuery("from Pedido us").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return us;
    }

    //delete
    public Pedido remove(String id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Pedido us = null;

        try {
            us = em.find(Pedido.class, id);
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
