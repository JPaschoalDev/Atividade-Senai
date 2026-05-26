package Tarefas.dao;

import Tarefas.model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    public void inserir(Tarefa tarefa) throws SQLException {
        String sql = "INSERT INTO tarefas (titulo, descricao, status, usuario_id) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getStatus().name());  // enum → String
            stmt.setInt(4, tarefa.getUsuarioId());

            stmt.executeUpdate();
        }
    }

    public List<Tarefa> listarPorUsuario(int usuarioId) throws SQLException {
        String sql = "SELECT id, titulo, descricao, status, data_criacao, usuario_id " +
                "FROM tarefas WHERE usuario_id = ? " +
                "ORDER BY data_criacao DESC";

        List<Tarefa> tarefas = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getInt("id"));
                    tarefa.setTitulo(rs.getString("titulo"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setStatus(Tarefa.StatusTarefa.valueOf(rs.getString("status")));
                    tarefa.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                    tarefa.setUsuarioId(rs.getInt("usuario_id"));

                    tarefas.add(tarefa);
                }
            }
        }

        return tarefas;
    }

    public boolean atualizarStatus(int id, Tarefa.StatusTarefa novoStatus) throws SQLException {
        String sql = "UPDATE tarefas SET status = ? WHERE id = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus.name());
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean atualizarTituloEDescricao(int id, String novoTitulo, String novaDescricao) throws SQLException {
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ? WHERE id = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoTitulo);
            stmt.setString(2, novaDescricao);
            stmt.setInt(3, id);

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean remover(int id) throws SQLException {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    public List<Tarefa> listarPorStatus(int usuarioId, Tarefa.StatusTarefa status) throws SQLException {
        String sql = "SELECT id, titulo, descricao, status, data_criacao, usuario_id " +
                "FROM tarefas WHERE usuario_id = ? AND status = ? " +
                "ORDER BY data_criacao DESC";

        List<Tarefa> tarefas = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            stmt.setString(2, status.name());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getInt("id"));
                    tarefa.setTitulo(rs.getString("titulo"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setStatus(Tarefa.StatusTarefa.valueOf(rs.getString("status")));
                    tarefa.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                    tarefa.setUsuarioId(rs.getInt("usuario_id"));

                    tarefas.add(tarefa);
                }
            }
        }

        return tarefas;
    }
}
