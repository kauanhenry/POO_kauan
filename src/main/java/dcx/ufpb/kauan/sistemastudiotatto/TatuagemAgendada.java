package dcx.ufpb.kauan.sistemastudiotatto;

import java.io.Serializable;

/**
 * Representa um agendamento de tatuagem em um estúdio.
 * Feito em aula de POO - Ciẽncia da Computação
 */

public class TatuagemAgendada implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeCliente;
    private String estiloTatuagem;
    private int dia;
    private int mes;

    public TatuagemAgendada(String nomeCliente, String estiloTatuagem, int dia, int mes) {
        this.nomeCliente = nomeCliente;
        this.estiloTatuagem = estiloTatuagem;
        this.dia = dia;
        this.mes = mes;
    }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public String getEstiloTatuagem() { return estiloTatuagem; }
    public void setEstiloTatuagem(String estiloTatuagem) { this.estiloTatuagem = estiloTatuagem; }

    public int getDia() { return dia; }
    public void setDia(int dia) { this.dia = dia; }

    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }

    @Override
    public String toString() {
        return "Cliente: " + nomeCliente + " | Estilo: " + estiloTatuagem + " | Data: " + dia + "/" + mes;
    }
}