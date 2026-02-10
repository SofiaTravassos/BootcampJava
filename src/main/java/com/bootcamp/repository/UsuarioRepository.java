package com.bootcamp.repository;
import com.bootcamp.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public UsuarioRepository() {
        criartabela();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    };

    private void criartabela() {
        String sql = """
                CREATE TABLE IF NOT EXISTS usuario (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL,
                """;

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Usuario usuario) {
        String sql = """
                INSERT INTO usuario (nome, email) VALUES (?, ?)
        """;

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                usuarios.add(new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("email")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }

    public void atualizar(Usuario usuario) {
        String sql = """
                UPDATE usuario SET nome = ?, email = ? WHERE id = ?;
        """;

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setLong(3, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
