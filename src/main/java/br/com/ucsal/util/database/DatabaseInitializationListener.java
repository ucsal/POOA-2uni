package br.com.ucsal.util.database;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DatabaseInitializationListener implements ServletContextListener {

    // Método chamado quando o contexto é inicializado
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("\nIniciando o banco de dados HSQLDB...");
            // Inicia o banco de dados
            DatabaseUtil.iniciarBanco();
        } catch (Exception e) {
            // Tratamento de erro caso ocorra algum problema ao inicializar o banco
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método chamado quando o contexto é destruído
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nAplicação sendo finalizada.");
        // Pode adicionar aqui alguma limpeza ou fechamento de conexões, se necessário
    }
}
