import java.util.Objects;

public class Atleta {
    private String nome;
    private int idade;
    private double peso;

    public Atleta(String nome, int idade, double peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public void treinar() {
        System.out.println(nome + " está treinando pesado! Arrocha!");
    }

    @Override
    public String toString() {
        return "ATLETA: " + nome + ", IDADE: " + idade + ", PESO: " + peso + "kg";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atleta atleta = (Atleta) o;
        return idade == atleta.idade && Double.compare(atleta.peso, peso) == 0 && Objects.equals(nome, atleta.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade, peso);
    }
}