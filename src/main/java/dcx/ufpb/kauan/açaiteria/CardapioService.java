package dcx.ufpb.kauan.açaiteria;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CardapioService implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Produto> cardapio = new HashMap<>();

    public void adicionarItem(String codigo, String nome, double preco) throws IllegalArgumentException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("O código do produto não pode ser vazio.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
        cardapio.put(codigo, new Produto(codigo, nome, preco));
    }

    public boolean removerItem(String codigo) {
        if (cardapio.containsKey(codigo)) {
            cardapio.remove(codigo);
            return true;
        }
        return false;
    }

    public Produto buscarItem(String codigo) {
        return cardapio.get(codigo);
    }

    public Map<String, Produto> getCardapio() {
        return cardapio;
    }

    public void setCardapio(Map<String, Produto> cardapio) {
        if (cardapio != null) {
            this.cardapio = cardapio;
        }
    }
}