package br.com.ucsal.persistencia;

import java.util.List;
import br.com.ucsal.model.Produto;

public interface ProdutoRepository<T, I> {
    
    void adicionar(T entidade);           // Adiciona uma nova entidade no repositório
    
    void remover(I id);                   // Remove uma entidade do repositório com base no identificador
    
    void atualizar(T entidade);           // Atualiza uma entidade existente no repositório
    
    List<T> listar();                     // Retorna uma lista de todas as entidades do repositório
    
    Produto obterPorID(I id);              // Obtém uma entidade do repositório com base no identificador
}
