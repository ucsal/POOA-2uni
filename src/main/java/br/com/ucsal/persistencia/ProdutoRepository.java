package br.com.ucsal.persistencia;

import java.util.List;
<<<<<<< HEAD
import br.com.ucsal.model.Produto;

public interface ProdutoRepository<T, I> {
    
    void adicionar(T entidade);           // Adiciona uma nova entidade no repositório
    
    void remover(I id);                   // Remove uma entidade do repositório com base no identificador
    
    void atualizar(T entidade);           // Atualiza uma entidade existente no repositório
    
    List<T> listar();                     // Retorna uma lista de todas as entidades do repositório
    
    Produto obterPorID(I id);              // Obtém uma entidade do repositório com base no identificador
=======

import br.com.ucsal.model.Produto;

public interface ProdutoRepository<T,I> {
	
    void adicionar(T entidade);
    
    void remover(I id);
    
    void atualizar(T entidade);
    
    List<T> listar();
    
    Produto obterPorID(I id);

>>>>>>> ceb68ae84e6157a9094186ed145eebfc5d3be776
}
