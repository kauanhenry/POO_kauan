public abstract class Mensagem {
    private final String texto;
    private final String emailRemetente;
    private final boolean anonima;

    public Mensagem(String texto, String emailRemetente, boolean anonima) {
        this.texto = texto;
        this.emailRemetente = emailRemetente;
        this.anonima = anonima;
    }

    public String getTexto() {
        return texto;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public boolean ehAnonima() {
        return anonima;
    }

    public abstract String getFormatadaParaEnvio();
}