package br.com.ucsal.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

    private static final String URL = "jdbc:hsqldb:mem:lojadb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // Método para inicializar o banco de dados
    public static void iniciarBanco() {
        try {
            // Carrega o driver JDBC
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            // Estabelece a conexão e cria a tabela e insere os dados
            try (Connection conn = getConnection()) {
                criarTabela(conn);
                inserirDados(conn);
                System.out.println("Banco de dados inicializado com sucesso!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver do banco de dados: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para criar a tabela de produtos
    private static void criarTabela(Connection conn) throws SQLException {
        String createTableSQL = "CREATE TABLE produtos (" +
                "id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, " +
                "nome VARCHAR(50), " +
                "preco DOUBLE)";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
        }
    }

    // Método para inserir dados iniciais na tabela
    private static void inserirDados(Connection conn) throws SQLException {
        String insertDataSQL1 = "INSERT INTO produtos (nome, preco) VALUES ('Notebook', 3500.00)";
        String insertDataSQL2 = "INSERT INTO produtos (nome, preco) VALUES ('Smartphone', 1500.00)";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(insertDataSQL1);
            stmt.executeUpdate(insertDataSQL2);
        }
    }

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}