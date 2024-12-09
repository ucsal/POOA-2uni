package br.com.ucsal.controller;

import java.io.IOException;
<<<<<<< HEAD
import java.util.Map;

import br.com.ucsal.commands.Command;
=======
import java.util.HashMap;
import java.util.Map;

>>>>>>> ceb68ae84e6157a9094186ed145eebfc5d3be776
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

<<<<<<< HEAD
@WebServlet("/view/*")
public class ProdutoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Map<String, Command> rotaCommand;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Implementação futura para inicialização de rotas se necessário
        } catch (Exception e) {
            throw new ServletException("Erro ao inicializar rotas.", e);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        rotaCommand = (Map<String, Command>) request.getServletContext().getAttribute("command");
        String path = request.getPathInfo();

        try {
            rotaCommand.get(path).execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar a requisição.");
        }
    }
=======
@WebServlet("/view/*")  // Mapeia todas as requisições com "/view/*"
public class ProdutoController extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

	
    @Override
    public void init() {
        // Mapeia os comandos
        commands.put("/editarProduto", new ProdutoEditarServlet());
        commands.put("/adicionarProduto", new ProdutoAdicionarServlet());
        commands.put("/excluirProduto", new ProdutoExcluirServlet());
        commands.put("/listarProdutos", new ProdutoListarServlet());
        commands.put("/", new ProdutoListarServlet()); // Roteia também a raiz da aplicação para listar produtos
        // Adicione outros comandos conforme necessário
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);
        Command command = commands.get(path);

        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Página não encontrada");
        }
    }

	


>>>>>>> ceb68ae84e6157a9094186ed145eebfc5d3be776
}
