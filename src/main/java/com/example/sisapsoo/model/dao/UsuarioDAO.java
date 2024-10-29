package com.example.sisapsoo.model.dao;

import com.example.sisapsoo.connection.ConnectionFactory;
import com.example.sisapsoo.model.Usuario;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UsuarioDAO {
    EntityManager entityManager = new ConnectionFactory().getConnection();

    //insert-update
    public Usuario save(Usuario usuario) {
        try {
            entityManager.getTransaction().begin();
            if (usuario.getId() == null) {
                entityManager.persist(usuario);
                System.out.println("SALVOU USUARIO ----------------------------------------------");
            } else {
                entityManager.merge(usuario);
            }
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return usuario;
    }

    //select where id = <valor desejado>
    public Usuario findById(String id) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        Usuario usuario = null;

        try {
            usuario = entityManager.find(Usuario.class, id);
        } catch (Exception exception) {
            System.err.println(exception);
        } finally {
            entityManager.close();
        }
        return usuario;
    }

    //Lista de todos os objetos Tabelateste
    public List<Usuario> findAll() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        List<Usuario> usuarios = null;

        try {
            usuarios = entityManager.createQuery("from Usuario us").getResultList();
        } catch (Exception exception) {
            System.err.println(exception);
        } finally {
            entityManager.close();
        }
        return usuarios;
    }

    //delete
    public Usuario remove(String id) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        Usuario usuarios = null;

        try {
            usuarios = entityManager.find(Usuario.class, id);
            entityManager.getTransaction().begin();
            entityManager.remove(usuarios);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            System.err.println(exception);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return usuarios;

    }
}
