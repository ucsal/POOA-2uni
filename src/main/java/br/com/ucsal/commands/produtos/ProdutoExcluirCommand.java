package br.com.ucsal.commands.produtos;

import java.io.IOException;

import br.com.ucsal.annotations.Inject;
import br.com.ucsal.annotations.Rota;
import br.com.ucsal.annotations.Singleton;
import br.com.ucsal.commands.Command;
import br.com.ucsal.service.ProdutoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Rota(path = "/excluirProduto")
@Singleton
public class ProdutoExcluirCommand implements Command {
    private static final long serialVersionUID = 1L;

    @Inject
    private ProdutoService produtoService;

    // Construtor padrão
    public ProdutoExcluirCommand() {
        super();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProduto = Integer.parseInt(request.getParameter("id"));
            produtoService.removerProduto(idProduto);
            response.sendRedirect("listarProdutos");
        } catch (NumberFormatException e) {
            throw new ServletException("ID inválido fornecido para exclusão do produto.", e);
        }
    }
}
