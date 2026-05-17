import java.util.Collection;

public class Main2 {
    public static void main(String[] args) {

        SistemaPadaria padaria = new SistemaPadariaDeKauan();

        System.out.println("--- Bem-vindo ao Sistema de Padaria da Aula de POO ---");

        Produto p1 = new Produto("Pao Doce", "001", true);
        Produto p2 = new Produto("Bolo de Rolo", "002", true);
        Produto p3 = new Produto("Cuscuz com Ovo", "003", true);
        Produto p4 = new Produto("Pao de Sal", "004", true);

        padaria.cadastrarProduto(p1);
        padaria.cadastrarProduto(p2);
        padaria.cadastrarProduto(p3);
        padaria.cadastrarProduto(p4);

        int total = padaria.contarTiposDeProduto();
        System.out.println("Vixe! Já temos " + total + " tipos de produtos cadastrados.");

        System.out.println("\n--- Procurando o Bolo de Rolo pelo código 002 ---");
        Produto achado = padaria.pesquisarProdutoPeloCodigo("002");
        if (achado != null) {
            System.out.println("Achei! É um: " + achado.getNome());
        } else {
            System.out.println("Eita, esse aí se perdeu no caminho.");
        }

        System.out.println("\n--- O que tem de 'Pao' aí? ---");
        Collection<Produto> paes = padaria.pesquisarProdutosComNomeComecandoCom("Pao");
        for (Produto p : paes) {
            System.out.println("-> " + p.getNome());
        }

        System.out.println("\n--- Vitrine Completa ---");
        padaria.obterTodosOsProdutos().forEach(System.out::println);

        System.out.println("\n--- Tudo certo! Pode botar o café pra coar. ---");
    }
}