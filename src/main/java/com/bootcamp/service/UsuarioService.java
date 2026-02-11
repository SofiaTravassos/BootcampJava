package com.bootcamp.service;

import com.bootcamp.model.Usuario;
import com.bootcamp.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService() {
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

    public List<Usuario> listarUsuarios(){
        return repository.listarTodos();
    }

    public Usuario buscarUsuario(Long id){
        return repository.buscarPorId(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
    }

    public void atualizarUsuario(Long id, String novoNome, String novoEmail){
        Optional<Usuario> usuarioOpt = repository.buscarPorId(id);

        if(usuarioOpt.isPresent()){
            Usuario usuario = usuarioOpt.get();
            if(novoNome != null && !novoNome.isEmpty()){
                usuario.setNome(novoNome);
            }
            if(novoEmail != null && !novoEmail.isEmpty()){
                usuario.setEmail(novoEmail);
            }
            repository.atualizar(usuario);
        } else{
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public void removerUsuario(Long id){
        if(repository.buscarPorId(id).isPresent()){
            repository.remover(id);
        } else{
            throw new RuntimeException("Usuário não encontrado!");
        }
    }
}
