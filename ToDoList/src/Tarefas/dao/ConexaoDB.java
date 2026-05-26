package Tarefas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/todolist_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public static void main(String[] args) {
        try {
            Connection conexao = getConexao();
            System.out.println("✅ Conexão bem-sucedida com o banco!");
            conexao.close();
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar:");
            e.printStackTrace();
        }
    }
}
