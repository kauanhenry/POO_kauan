package dcx.ufpb.kauan.sistemapadaria;

import java.util.Collection;

public interface SistemaPadaria {

    void cadastrarProduto(Produto produto);

    int contarTiposDeProduto();

    Produto pesquisarProdutoPeloCodigo(String codigo);

    Collection<Produto> pesquisarProdutosComNomeComecandoCom(String prefixoNome);

    Collection<Produto> obterTodosOsProdutos();
}