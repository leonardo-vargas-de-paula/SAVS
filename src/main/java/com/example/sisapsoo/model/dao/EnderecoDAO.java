package com.example.sisapsoo.model.dao;

import com.example.sisapsoo.connection.ConnectionFactory;
import com.example.sisapsoo.model.Endereco;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EnderecoDAO {
    EntityManager em = new ConnectionFactory().getConnection();

    //insert-update
    public Endereco save(Endereco c) {
        try {
            em.getTransaction().begin();
            if (c.getIdEndereco() == null) {
                em.persist(c);
            } else {
                em.merge(c);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return c;
    }

    //select where id = <valor desejado>
    public Endereco findById(Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Endereco c= null;

        try {
            c= em.find(Endereco.class, id);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return c;
    }

    //Lista de todos os objetos Tabelateste
    public List<Endereco> findAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Endereco> cs = null;

        try {
            cs = em.createQuery("from Endereco vv").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            em.close();
        }
        return cs;
    }

    //delete
    public Endereco remove(Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Endereco c= null;

        try {
            c= em.find(Endereco.class, id);
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
