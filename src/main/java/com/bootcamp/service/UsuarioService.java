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

        repository.save(new Usuario(nome, email));
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

        repository.save(new Funcionario(nome, email, cargo));
    }

    public List<Usuario> listarUsuarios(){
        return repository.findAll();
    }

    public Usuario buscarUsuario(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario com id " + id + " não encontrado!"));
    }

    public void atualizarUsuario(Long id, String novoNome, String novoEmail){
        Optional<Usuario> usuarioOpt = repository.findById(id);

        if(usuarioOpt.isPresent()){
            Usuario usuario = usuarioOpt.get();
            if(novoNome != null && !novoNome.isEmpty()){
                usuario.setNome(novoNome);
            }
            if(novoEmail != null && !novoEmail.isEmpty()){
                usuario.setEmail(novoEmail);
            }
            repository.save(usuario); 
        } else{
            throw new RuntimeException("Usuário com id " + id + " não encontrado!");
        }
    }

    public void removerUsuario(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else{
            throw new RuntimeException("Usuário com id " + id + " não encontrado!");
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email não pode estar vazio!");
        }
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário com email " + email + " não encontrado!"));
    }

        public Usuario buscarUsuarioPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode estar vazio!");
        }
        return repository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Usuário com nome " + nome + " não encontrado!"));
    }

}
