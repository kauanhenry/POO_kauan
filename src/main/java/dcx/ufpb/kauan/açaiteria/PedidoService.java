package dcx.ufpb.kauan.açaiteria;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PedidoService implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Pedido> pedidos = new HashMap<>();
    private int contadorId = 1;

    public Pedido cadastrarPedido(String nomeCliente, List<Produto> itens) throws IllegalArgumentException {
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um produto.");
        }

        String id = String.valueOf(contadorId++);
        while (pedidos.containsKey(id)) {
            contadorId++;
            id = String.valueOf(contadorId++);
        }

        Pedido pedido = new Pedido(id, nomeCliente, itens, LocalDate.now());
        pedidos.put(id, pedido);
        return pedido;
    }

    public boolean removerPedido(String idPedido) {
        if (pedidos.containsKey(idPedido)) {
            pedidos.remove(idPedido);
            return true;
        }
        return false;
    }

    public List<Pedido> pesquisarPorNome(String nome) {
        return pedidos.values().stream()
                .filter(p -> p.getNomeCliente().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Pedido> pesquisarPorData(LocalDate data) {
        return pedidos.values().stream()
                .filter(p -> p.getData().equals(data))
                .collect(Collectors.toList());
    }

    public List<Pedido> pesquisarPorStatus(String status) {
        return pedidos.values().stream()
                .filter(p -> p.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public boolean finalizarPedido(String idPedido) {
        Pedido pedido = pedidos.get(idPedido);
        if (pedido != null && "PENDENTE".equalsIgnoreCase(pedido.getStatus())) {
            pedido.setStatus("FINALIZADO");
            return true;
        }
        return false;
    }

    public Map<String, Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Map<String, Pedido> pedidos) {
        if (pedidos != null) {
            this.pedidos = pedidos;

            int maxId = 0;
            for (String key : pedidos.keySet()) {
                try {
                    int val = Integer.parseInt(key);
                    if (val > maxId) maxId = val;
                } catch (NumberFormatException ignored) {}
            }
            this.contadorId = maxId + 1;
        }
    }

    public void adicionarPedido(Pedido novoPedido) {

    }
}