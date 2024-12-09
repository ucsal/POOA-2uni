package br.com.ucsal.commands.rotas;

import java.io.IOException;

import br.com.ucsal.commands.Command;
import br.com.ucsal.controller.InicializadorListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RotaCommand implements Command {
    
    private final InicializadorListener inicializadorListener;

    // Construtor que inicializa o listener
    public RotaCommand(InicializadorListener listener) {
        this.inicializadorListener = listener;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém o path da requisição e normaliza sua estrutura
        String caminho = request.getRequestURI().replaceAll("//", "/");
        System.out.println("Caminho recebido: " + caminho);

        // Busca o comando correspondente ao path
        Command comandoCorrespondente = inicializadorListener.getCommand(caminho);

        if (comandoCorrespondente != null) {
            comandoCorrespondente.execute(request, response);
        } else {
            // Retorna erro 404 caso a rota não seja encontrada
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Rota não encontrada.");
        }
    }

    // Getter para o InicializadorListener
    public InicializadorListener getInicializadorListener() {
        return inicializadorListener;
    }
}
