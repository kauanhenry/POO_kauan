package dcx.ufpb.kauan.sistemastudiotatto;

import java.util.Collection;
import java.io.IOException;

/**
 * * @author Kauan Henry
 * @version 1.0
 */

public interface StudioTatto {

    /**
     * @param estilo;
     * @param dia;
     * @param mes;
     * @return ;
     */
    public boolean cadastrarAgendamento(String nomeCliente, String estilo, int dia, int mes);

    /**
     * @param mes;
     * @return ;
     */
    public Collection<TatuagemAgendada> pesquisarAgendamentosDoDia(int dia, int mes);

    /**
     * @param nomeCliente;
     * @return ;
     * @throws AgendamentoInexistenteException;
     */

    public boolean removerAgendamento(String nomeCliente) throws AgendamentoInexistenteException;

    public void salvarDados() throws IOException;

    public void recuperarDados() throws IOException;
}