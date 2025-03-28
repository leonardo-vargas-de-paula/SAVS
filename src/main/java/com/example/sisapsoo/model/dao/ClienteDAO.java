package com.example.sisapsoo.model.dao;

import com.example.sisapsoo.connection.ConnectionFactory;
import com.example.sisapsoo.model.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDAO {
    EntityManager em = new ConnectionFactory().getConnection();

    //insert-update
    public Cliente save(Cliente c) {
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.merge(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return c;
    }

    //select where id = <valor desejado>
    public Cliente findById(Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Cliente c= null;

        try {
            c= em.find(Cliente.class, id);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return c;
    }

    //Lista de todos os objetos Tabelateste
    public List<Cliente> findAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Cliente> cs = null;

        try {
            cs = em.createQuery("from Cliente vv").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return cs;
    }

    //delete
    public Cliente remove(Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Cliente c= null;

        try {
            c= em.find(Cliente.class, id);
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return c;

    }

}