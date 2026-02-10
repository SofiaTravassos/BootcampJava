package com.bootcamp.service;

import com.bootcamp.model.Usuario;
import com.bootcamp.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final UsuarioRepository repository;

    public usuarioService {
        this.repository = new UsuarioRepository();
    }

    public void criarUsuario(String nome, String email){
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome é obrigatório!");
        }
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email é obrigatório!");
        }

        repository.salvar(new Usuario(nome, email));
    }

    public List<>


}
