package br.com.ucsal.commands.produtos;

import java.io.IOException;
import java.util.List;

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

@Rota(path = {"/", "/listarProdutos"})
@Singleton
public class ProdutoListarCommand implements Command {
    private static final long serialVersionUID = 1L;

    @Inject
    private ProdutoService produtoService;

    // Construtor padrão
    public ProdutoListarCommand() {
        super();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recupera a lista de produtos do serviço
            List<Produto> listaProdutos = produtoService.listarProdutos();

            // Adiciona a lista de produtos como atributo na requisição
            request.setAttribute("produtos", listaProdutos);

            // Encaminha a requisição para o JSP responsável por exibir a lista
            RequestDispatcher encaminhador = request.getRequestDispatcher("/WEB-INF/views/produtolista.jsp");
            encaminhador.forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erro ao listar produtos.", e);
        }
    }
}
