package dcx.ufpb.kauan.amigosecreto;

public class MensagemParaTodos extends Mensagem {

    public MensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
        super(texto, emailRemetente, anonima);
    }

    @Override
    public String getFormatadaParaEnvio() {
        String remetente = ehAnonima() ? "Anônimo" : getEmailRemetente();
        return "dcx.ufpb.kauan.amigosecreto.Mensagem de " + remetente + " para todos: " + getTexto();
    }
}