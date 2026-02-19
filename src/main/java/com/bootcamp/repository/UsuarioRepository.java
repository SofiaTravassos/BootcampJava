package com.bootcamp.repository;

import com.bootcamp.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    private final EntityManagerFactory emf;

    public UsuarioRepository() {
        this.emf = Persistence.createEntityManagerFactory("bootcamp");
    }

    public void salvar(Usuario usuario) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(usuario);

        em.getTransaction().commit();
        em.close();

    }

    public List<Usuario> listarTodos() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Usuario> usuarios = em.createQuery("FROM Usuario", Usuario.class).getResultList();

        em.getTransaction().commit();
        em.close();

        return usuarios;

    }

    public void atualizar(Usuario usuario) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(usuario);

        em.getTransaction().commit();
        em.close();

    }

    public void remover(Long id) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.remove(usuario);
        }

        em.getTransaction().commit();
        em.close();

    }

    public Optional<Usuario> buscarPorId(Long id) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Usuario usuario = em.find(Usuario.class, id);
        return Optional.ofNullable(usuario);

    }

    public void fechar(){
        if(emf != null && emf.isOpen()){
            emf.close();
        }
    }

}
