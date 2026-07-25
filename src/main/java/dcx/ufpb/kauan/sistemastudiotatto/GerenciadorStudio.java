package dcx.ufpb.kauan.sistemastudiotatto;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class GerenciadorStudio implements StudioTatto {

    private Collection<TatuagemAgendada> agendamentos;
    private final String NOME_ARQUIVO = "agendamentos.dat";

    public GerenciadorStudio() {
        this.agendamentos = new ArrayList<>();
    }

    @Override
    public boolean cadastrarAgendamento(String nomeCliente, String estilo, int dia, int mes) {
        TatuagemAgendada novo = new TatuagemAgendada(nomeCliente, estilo, dia, mes);
        return this.agendamentos.add(novo);
    }

    @Override
    public Collection<TatuagemAgendada> pesquisarAgendamentosDoDia(int dia, int mes) {
        Collection<TatuagemAgendada> doDia = new ArrayList<>();
        for (TatuagemAgendada t : this.agendamentos) {
            if (t.getDia() == dia && t.getMes() == mes) {
                doDia.add(t);
            }
        }
        return doDia;
    }

    @Override
    public boolean removerAgendamento(String nomeCliente) throws AgendamentoInexistenteException {
        for (TatuagemAgendada t : this.agendamentos) {
            if (t.getNomeCliente().equalsIgnoreCase(nomeCliente)) {
                this.agendamentos.remove(t);
                return true;
            }
        }
        throw new AgendamentoInexistenteException("Eita! Não achei nenhum agendamento para o cliente: " + nomeCliente);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void salvarDados() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(this.agendamentos);
            System.out.println("Dados gravados com sucesso no arquivo!");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void recuperarDados() throws IOException {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de dados ainda não existe. Começando do zero.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            this.agendamentos = (Collection<TatuagemAgendada>) ois.readObject();
            System.out.println("Dados recuperados com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao converter os dados lidos do arquivo.");
        }
    }
}