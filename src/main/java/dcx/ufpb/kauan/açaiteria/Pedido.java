package dcx.ufpb.kauan.açaiteria;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idPedido;
    private String nomeCliente;
    private List<Produto> itens;
    private double valorTotal;
    private LocalDate data;
    private String status;

    public Pedido(String idPedido, String nomeCliente, List<Produto> itens, LocalDate data) {
        this.idPedido = idPedido;
        this.nomeCliente = nomeCliente;
        this.itens = itens;
        this.data = data;
        this.status = "PENDENTE";
        this.valorTotal = itens.stream().mapToDouble(Produto::getPreco).sum();
    }

    public Pedido(String trim) {

    }

    public String getIdPedido() {
        return idPedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public List<Produto> getItens() {
        return itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataFormatada() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(idPedido)
                .append(" | Cliente: ").append(nomeCliente)
                .append(" | Data: ").append(getDataFormatada())
                .append(" | Status: ").append(status)
                .append(" | Total: R$ ").append(String.format("%.2f", valorTotal))
                .append(" | Itens: ");

        for (int i = 0; i < itens.size(); i++) {
            sb.append(itens.get(i).getNome());
            if (i < itens.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    public void adicionarProduto(Produto produtoSite) {

    }
}