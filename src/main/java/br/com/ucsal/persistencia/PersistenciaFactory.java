package br.com.ucsal.persistencia;

<<<<<<< HEAD
import br.com.ucsal.controller.InicializadorListener;

public class PersistenciaFactory {

    public static final int PRODUTO_MEMORIA = 0;
    public static final int PRODUTO_HSQL = 1;
    
    // Método de fábrica para retornar o repositório de produtos
    public static ProdutoRepository<?, ?> getProdutoRepository(int type) {
        ProdutoRepository<?, ?> produtoRepository;
        
        // Seleção do repositório com base no tipo passado
        switch (type) {
        case PRODUTO_MEMORIA: {
            // Usando o InicializadorListener para obter uma instância do repositório em memória
            produtoRepository = InicializadorListener.getInstanceSingleton(MemoriaProdutoRepository.class);
            break;
        }
        case PRODUTO_HSQL: {
            // Criando uma instância do repositório HSQL
            produtoRepository = new HSQLProdutoRepository();
            break;
        }
        default:
            throw new IllegalArgumentException("Unexpected value: " + type); // Exceção em caso de tipo inválido
        }
        
        // Retorna o repositório criado
        return produtoRepository;
    }
=======
public class PersistenciaFactory {

	public static final int MEMORIA = 0;
	public static final int HSQL = 1;
	
	public static ProdutoRepository<?, ?> getProdutoRepository(int type) {
		ProdutoRepository<?, ?> produtoRepository;
		switch (type) {
		case 0: {
			produtoRepository = MemoriaProdutoRepository.getInstancia();
			break;
		}
		case 1: {
			produtoRepository = new HSQLProdutoRepository();

			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		return produtoRepository;
	}
	
	
>>>>>>> ceb68ae84e6157a9094186ed145eebfc5d3be776
}
