package br.com.ucsal.service;

import java.util.List;

import br.com.ucsal.model.Produto;
import br.com.ucsal.persistencia.PersistenciaFactory;
import br.com.ucsal.persistencia.ProdutoRepository;

public class ProdutoService {

    private ProdutoRepository<Produto, Integer> produtoRepository;

    // Construtor com injeção de dependência
    public ProdutoService(ProdutoRepository<?, ?> produtoRepository) {
        this.produtoRepository = (ProdutoRepository<Produto, Integer>) produtoRepository;
    }

    // Construtor padrão
    public ProdutoService() {
        super();
    }

    // Método para adicionar um produto
    public void adicionarProduto(String nome, double preco) {
        Produto produto = new Produto(null, nome, preco);
        produtoRepository.adicionar(produto);
    }

    // Método para remover um produto pelo ID
    public void removerProduto(Integer id) {
        produtoRepository.remover(id);
    }

    // Método para obter um produto pelo ID
    public Produto obterProdutoPorId(Integer id) {
        return produtoRepository.obterPorID(id);
    }

    // Método para atualizar um produto
    public void atualizarProduto(Produto produto) {
        produtoRepository.atualizar(produto);
    }

    // Método para listar todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.listar();
    }
}
