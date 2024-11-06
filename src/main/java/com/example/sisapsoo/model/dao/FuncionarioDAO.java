package com.example.sisapsoo.model.dao;
import com.example.sisapsoo.connection.ConnectionFactory;
import com.example.sisapsoo.model.Funcionario;
import jakarta.persistence.EntityManager;


import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    EntityManager em = getEntityManager();

    private EntityManager getEntityManager() {
        return ConnectionFactory.getConnection();
    }

    public Funcionario save(Funcionario f) {
//        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            System.out.println("SALVOU FUNCIONARIO");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar funcionário: " + e.getMessage());
        } finally {
            em.close();
        }
        return f;
    }

//    public Funcionario update(Funcionario f){
//        EntityManager em = getEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.merge(f);
//            em.getTransaction().commit();
//        } catch(Exception e){
//            em.getTransaction().rollback();
//            System.err.println("Erro ao atualizar o funcionário: " + e);
//        } finally {
//            em.close();
//        }
//        return f;
//    }

    public Funcionario findById(Integer id) {
        EntityManager em = getEntityManager();
        Funcionario f = null;

        try {
            f = em.find(Funcionario.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar funcionário: " + e.getMessage());
        } finally {
            em.close();
        }
        return f;
    }

    public List<Funcionario> findAll() {
        EntityManager em = getEntityManager();
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            funcionarios = em.createQuery("from Funcionario f", Funcionario.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            em.close();
        }
        return funcionarios;
    }

    public void remove(Integer id) {
        EntityManager em = getEntityManager();
        try {
            Funcionario f = em.find(Funcionario.class, id);
            em.getTransaction().begin();
            em.remove(f);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao remover funcionário: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }
}
