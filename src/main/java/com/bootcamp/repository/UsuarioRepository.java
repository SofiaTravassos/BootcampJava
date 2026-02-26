package com.bootcamp.repository;

import com.bootcamp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //findbynome e findbyemail nao serao mais usados
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNome(String nome);

}