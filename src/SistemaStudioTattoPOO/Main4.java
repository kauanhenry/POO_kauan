import java.io.IOException;
import java.util.Collection;

public class Main4 {
    public static void main(String[] args) {
        StudioTatto studio = new GerenciadorStudio();

        System.out.println("--- 1. Carregando dados anteriores ---");
        try {
            studio.recuperarDados();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }

        Collection<TatuagemAgendada> checagem = studio.pesquisarAgendamentosDoDia(12, 6);

        if (checagem.isEmpty()) {
            System.out.println("\n--- [Arquivo Vazio] Cadastrando clientes pela primeira vez ---");
            studio.cadastrarAgendamento("Neymar", "Old School", 12, 6);
            studio.cadastrarAgendamento("Maradona", "Fine Line", 12, 6);
            studio.cadastrarAgendamento("Cristiano Ronaldo", "Cyberpunk", 25, 8);
        } else {
            System.out.println("\n--- [Aviso] Clientes já estavam salvos no arquivo, pulando cadastro ---");
        }

        System.out.println("\n--- 3. Pesquisando agendamentos do dia 12/06 ---");
        Collection<TatuagemAgendada> agendados = studio.pesquisarAgendamentosDoDia(12, 6);
        for (TatuagemAgendada t : agendados) {
            System.out.println(t);
        }

        System.out.println("\n--- 4. Tentando remover agendamento ---");
        try {
            studio.removerAgendamento("Cristiano Ronaldo");
            System.out.println("Agendamento de Ronaldo removido da memória!");
        } catch (AgendamentoInexistenteException e) {
            System.out.println("Aviso: " + e.getMessage());
        }

        System.out.println("\n--- 5. Salvando dados para a próxima rodada ---");
        try {
            studio.salvarDados();
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}