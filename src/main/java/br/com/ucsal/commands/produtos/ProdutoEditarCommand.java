package br.com.ucsal.commands.produtos;

import java.io.IOException;

import br.com.ucsal.annotations.Inject;
import br.com.ucsal.annotations.Rota;
import br.com.ucsal.annotations.Singleton;
import br.com.ucsal.commands.Command;
import br.com.ucsal.model.Produto;
import br.com.ucsal.service.ProdutoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Rota(path = "/editarProduto")
@Singleton
public class ProdutoEditarCommand implements Command {
    private static final long serialVersionUID = 1L;

    @Inject
    private ProdutoService produtoService;

    // Construtor padrão
    public ProdutoEditarCommand() {
        super();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String metodoHttp = request.getMethod();

        switch (metodoHttp.toUpperCase()) {
            case "GET":
                tratarRequisicaoGet(request, response);
                break;
            case "POST":
                tratarRequisicaoPost(request, response);
                break;
            default:
                throw new ServletException("Método HTTP não suportado: " + metodoHttp);
        }
    }

    // Trata requisições GET
    private void tratarRequisicaoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProduto = Integer.parseInt(request.getParameter("id"));
            Produto produto = produtoService.obterProdutoPorId(idProduto);
            request.setAttribute("produto", produto);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/produtoformulario.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("ID inválido fornecido.", e);
        }
    }

    // Trata requisições POST
    private void tratarRequisicaoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProduto = Integer.parseInt(request.getParameter("id"));
            String nomeProduto = request.getParameter("nome");
            double precoProduto = Double.parseDouble(request.getParameter("preco"));

            Produto produtoAtualizado = new Produto(idProduto, nomeProduto, precoProduto);
            produtoService.atualizarProduto(produtoAtualizado);

            response.sendRedirect("listarProdutos");
        } catch (NumberFormatException e) {
            throw new ServletException("Erro ao processar os dados do produto.", e);
        }
    }
}
