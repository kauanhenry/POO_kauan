package dcx.ufpb.kauan.amigosecreto;

public class MensagemParaAlguem extends Mensagem {
    private final String emailDestinatario;

    public MensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean anonima) {
        super(texto, emailRemetente, anonima);
        this.emailDestinatario = emailDestinatario;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    @Override
    public String getFormatadaParaEnvio() {
        String remetente = ehAnonima() ? "Anônimo" : getEmailRemetente();
        return "dcx.ufpb.kauan.amigosecreto.Mensagem de " + remetente + " para " + emailDestinatario + ": " + getTexto();
    }
}