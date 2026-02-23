package com.bootcamp.service;

import com.bootcamp.model.Usuario;
import com.bootcamp.model.Funcionario;
import com.bootcamp.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
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

    public void criarFuncionario(String nome, String email, String cargo){
        if (cargo == null || cargo.isEmpty()){
            throw new IllegalArgumentException("Cargo é obrigatório para funcionários!");
        }

        if (nome == null || nome .isEmpty()){
            throw new IllegalArgumentException("Nome é obrigatório!");
        }
        if (email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email é obrigatório!");
        }

        repository.salvar(new Funcionario(nome, email, cargo));
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
