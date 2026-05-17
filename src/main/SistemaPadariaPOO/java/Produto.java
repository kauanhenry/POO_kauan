import java.util.Objects;

public class Produto {
    private String nome;
    private String codigo;
    private boolean ehPerecivel;

    public Produto() {}

    public Produto(String nome, String codigo, boolean ehPerecivel) {
        this.nome = nome;
        this.codigo = codigo;
        this.ehPerecivel = ehPerecivel;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public boolean ehPerecivel() { return ehPerecivel; }
    public void setPerecivel(boolean ehPerecivel) { this.ehPerecivel = ehPerecivel; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Produto{" + "nome: " + nome + '\'' + ", codigo: " + codigo + '\'' +
                ", perecivel: " + ehPerecivel + '}';
    }
}