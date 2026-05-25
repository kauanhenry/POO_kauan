import java.util.List;

public class Main3 {
    public static void main(String[] args) {

        AmigoScretoMap sistema = new AmigoScretoMap();

        System.out.println("=== INICIANDO O SISTEMA DE AMIGO SECRETO DA AULA DE POO (MAP) ===");

        try {
            System.out.println("\n-> Cadastrando amigos...");
            sistema.cadastrarAmigo("Kauan Henry", "kauan.gomes@dcx.ufpb.br");
            sistema.cadastrarAmigo("João Grilo", "joao@sertoes.com");
            sistema.cadastrarAmigo("Rosinha", "rosinha@sertoes.com");
            System.out.println("Amigos cadastrados com sucesso!");

            System.out.println("\n-> Realizando o sorteio manual...");
            sistema.configAmigoSecretoDe("kauan.gomes@dcx.ufpb.br", "rosinha@sertoes.com");
            sistema.configAmigoSecretoDe("joao@sertoes.com", "kauan.gomes@dcx.ufpb.br");
            sistema.configAmigoSecretoDe("rosinha@sertoes.com", "joao@sertoes.com");
            System.out.println("Sorteio configurado!");

            System.out.println("\n-> Enviando mensagens pelo sistema...");
            sistema.enviarMensagemParaTodos("Ei povo? A festa vai ser um estouro ein!", "kauan.gomes@dcx.ufpb.br", false);
            sistema.enviarMensagemParaAlguem("Acho que tirei tu, viu?", "joao@sertoes.com", "kauan.gomes@dcx.ufpb.br", true);

            System.out.println("\n=== CONSULTANDO RESULTADOS ===");

            String secretoDoChico = sistema.pesquisaAmigoSecretoDe("kauan.gomes@dcx.ufpb.br");
            Amigao amigoSorteado = sistema.pesquisaAmigo(secretoDoChico);
            System.out.println("O amigo secreto de Kauan é: " + amigoSorteado.getNome() + " (" + secretoDoChico + ")");

            System.out.println("\n-> Lista de todas as mensagens:");
            List<Mensagem> todasAsMensagens = sistema.pesquisaTodasAsMensagens();
            for (Mensagem m : todasAsMensagens) {
                System.out.println("   " + m.getFormatadaParaEnvio());
            }

        } catch (AmigoJaExisteException | AmigoInexistenteException | AmigoNaoSorteadoException e) {

            System.out.println("\nVixe! Deu erro no sistema: " + e.getMessage());
        }

        System.out.println("\n=== FIM DA EXECUÇÃO ===");
    }
}