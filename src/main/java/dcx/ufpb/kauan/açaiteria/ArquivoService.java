package dcx.ufpb.kauan.açaiteria;

import java.io.*;

public class ArquivoService {

    public static void salvarDados(PedidoService pedidoService, CardapioService cardapioService, String caminho) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(cardapioService.getCardapio());
            oos.writeObject(pedidoService.getPedidos());
        }
    }

    @SuppressWarnings("unchecked")
    public static void recuperarDados(PedidoService pedidoService, CardapioService cardapioService, String caminho) throws IOException, ClassNotFoundException {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            Object cardapioObj = ois.readObject();
            Object pedidosObj = ois.readObject();

            if (cardapioObj instanceof java.util.Map) {
                cardapioService.setCardapio((java.util.Map<String, Produto>) cardapioObj);
            }
            if (pedidosObj instanceof java.util.Map) {
                pedidoService.setPedidos((java.util.Map<String, Pedido>) pedidosObj);
            }
        }
    }
}