package br.com.ucsal.commands.produtos;

import java.io.IOException;

import br.com.ucsal.annotations.Inject;
import br.com.ucsal.annotations.Rota;
import br.com.ucsal.annotations.Singleton;
import br.com.ucsal.commands.Command;
import br.com.ucsal.service.ProdutoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Rota(path = "/adicionarProduto")
@Singleton
public class ProdutoAdicionarCommand implements Command {
	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService produtoService;

	// Construtor padrão
	public ProdutoAdicionarCommand() {
		super();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String metodoHttp = request.getMethod();

		switch (metodoHttp.toUpperCase()) {
			case "GET":
				processarGet(request, response);
				break;
			case "POST":
				processarPost(request, response);
				break;
			default:
				throw new ServletException("Método HTTP não suportado: " + metodoHttp);
		}
	}

	// Processa requisições GET
	private void processarGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher encaminhador = request.getRequestDispatcher("/WEB-INF/views/produtoformulario.jsp");
		encaminhador.forward(request, response);
	}

	// Processa requisições POST
	private void processarPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeProduto = request.getParameter("nome");
		double precoProduto = Double.parseDouble(request.getParameter("preco"));
		
		produtoService.adicionarProduto(nomeProduto, precoProduto);
		response.sendRedirect("listarProdutos");
	}
}
