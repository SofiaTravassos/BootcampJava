package com.bootcamp.repository;

import com.bootcamp.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(Usuario usuario) {
        em.persist(usuario);
    }

    public List<Usuario> listarTodos() {
        return em.createQuery("FROM Usuario", Usuario.class).getResultList();
    }

    @Transactional
    public void atualizar(Usuario usuario) {
        em.merge(usuario);
    }

    @Transactional
    public void remover(Long id) {
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.remove(usuario);
        }
    }

    public Optional<Usuario> buscarPorId(Long id) {
        Usuario usuario = em.find(Usuario.class, id);
        return Optional.ofNullable(usuario);
    }
    
}