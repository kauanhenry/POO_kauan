package dcx.ufpb.kauan.sistemapadaria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaPadariaDeKauan implements SistemaPadaria {
    private List<Produto> produtos;

    public SistemaPadariaDeKauan() {
        this.produtos = new ArrayList<>();
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    @Override
    public int contarTiposDeProduto() {
        return this.produtos.size();
    }

    @Override
    public Produto pesquisarProdutoPeloCodigo(String codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Collection<Produto> pesquisarProdutosComNomeComecandoCom(String prefixoNome) {
        return produtos.stream()
                .filter(p -> p.getNome().toLowerCase().startsWith(prefixoNome.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Produto> obterTodosOsProdutos() {
        return new ArrayList<>(this.produtos);
    }
}