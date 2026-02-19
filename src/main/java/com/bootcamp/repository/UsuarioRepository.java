package com.bootcamp.repository;

import com.bootcamp.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.bootcamp.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    private final EntityManagerFactory emf;

    public UsuarioRepository() {
        this.emf = Persistence.createEntityManagerFactory("bootcamp");
    }

    // private static final String URL = "jdbc:h2:file:~/bootcamp_db";
    // private static final String USER = "sa";
    // private static final String PASSWORD = "";

    // private Connection getConnection() throws SQLException {
    //     return DriverManager.getConnection(URL, USER, PASSWORD);
    // };

    // private void criarTabela() {
    //     var sql = """
    //             CREATE TABLE IF NOT EXISTS usuario (
    //                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
    //                 nome VARCHAR(255) NOT NULL,
    //                 email VARCHAR(255) NOT NULL,
    //                 cargo VARCHAR(255),
    //                 tipo_usuario VARCHAR(50)
    //             );
    //             """;
    //     try (var conn = getConnection();
    //         var stmt = conn.createStatement()) {
    //         stmt.execute(sql);
    //         System.out.println("DEBUG: Tabela 'usuario' criada ou verificada com sucesso!");
    //     } catch (SQLException e) {
    //         System.out.println("ERRO CRÍTICO AO CRIAR TABELA:");
    //         e.printStackTrace();
    //     }
    // }

    public void salvar(Usuario usuario) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(usuario);

        em.getTransaction().commit();
        em.close();

        // var sql = """
        //         INSERT INTO usuario (nome, email, cargo, tipo_usuario) VALUES (?, ?, ?, ?)
        // """;

        // try (var conn = getConnection(); var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

        //     stmt.setString(1, usuario.getNome());
        //     stmt.setString(2, usuario.getEmail());

        //     if (usuario instanceof Funcionario funcionario){
        //         stmt.setString(3, funcionario.getCargo());
        //         stmt.setString(4, "FUNCIONARIO");
        //     } else{
        //         stmt.setNull(3, Types.VARCHAR);
        //         stmt.setString(4, "USUARIO");
        //     }

        //     int affected = stmt.executeUpdate();
        //     if (affected > 0) {
        //         try (var keys = stmt.getGeneratedKeys()){
        //             if (keys.next()){
        //                 usuario.setId(keys.getLong(1));
        //             }
        //         }
        //     }
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
    }

    public List<Usuario> listarTodos() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Usuario> usuarios = em.createQuery("FROM Usuario", Usuario.class).getResultList();

        em.getTransaction().commit();
        em.close();

        return usuarios;

        // var usuarios = new ArrayList<Usuario>();
        // String sql = "SELECT * FROM usuario";

        // try (var conn = getConnection(); var stmt = conn.createStatement(); var rs = stmt.executeQuery(sql)){
        //     while(rs.next()) {
        //         usuarios.add(mapearUsuario(rs));
        //     }
        // } catch (SQLException e){
        //     e.printStackTrace();
        // }
        // return usuarios;
    }

    public void atualizar(Usuario usuario) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(usuario);

        em.getTransaction().commit();
        em.close();

        // var sql = """
        //         UPDATE usuario SET nome = ?, email = ?, cargo = ?  WHERE id = ?;
        // """;

        // try (var conn = getConnection(); var stmt = conn.prepareStatement(sql)){
        //     stmt.setString(1, usuario.getNome());
        //     stmt.setString(2, usuario.getEmail());

        //     if (usuario instanceof Funcionario f){
        //         stmt.setString(3, f.getCargo());
        //     } else{
        //         stmt.setNull(3, Types.VARCHAR);
        //     }

        //     stmt.setLong(4, usuario.getId());
        //     stmt.executeUpdate();
        // } catch (SQLException e){
        //     e.printStackTrace();
        // }
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

        // var sql = "DELETE FROM usuario WHERE id = ?";

        // try (var conn = getConnection(); var stmt = conn.prepareStatement(sql)){
        //     stmt.setLong(1, id);
        //     stmt.executeUpdate();
        // } catch (SQLException e){
        //     e.printStackTrace();
        // }
    }

    public Optional<Usuario> buscarPorId(Long id) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Usuario usuario = em.find(Usuario.class, id);
        return Optional.ofNullable(usuario);

        em.getTransaction().commit();
        em.close();

        // var sql = "SELECT * FROM usuario WHERE id = ?";

        // try (var conn = getConnection();
        //      var stmt = conn.prepareStatement(sql)) {
        //     stmt.setLong(1, id);
        //     try (var rs = stmt.executeQuery()) {
        //         if (rs.next()) {
        //             return Optional.of(new Usuario(
        //                     rs.getLong("id"),
        //                     rs.getString("nome"),
        //                     rs.getString("email")
        //             ));
        //         }
        //     }
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        // return Optional.empty();
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        String cargo = rs.getString("cargo");
        String tipo = rs.getString("tipo_usuario");

        if ("FUNCIONARIO".equals(tipo)){
            return new Funcionario(id, nome, email, cargo);
        } else{
            return new Usuario(id, nome, email);
        }
    }
}
