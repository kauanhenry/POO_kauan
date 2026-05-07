import java.util.ArrayList;
import java.util.List;

public class Academia {
    private final List<Atleta> alunos = new ArrayList<>();

    public void matricularAtleta(Atleta a) throws Exception {
        if (a.getIdade() < 16) {
            throw new Exception("Vixe! Atleta muito novo. Só aceitamos acima de 16 anos.");
        }
        alunos.add(a);
        System.out.println("Atleta " + a.getNome() + " matriculado com sucesso!");
    }

    public void listarAlunos() {
        System.out.println("\n--- Lista de Alunos da Academia ---");
        for (Atleta a : alunos) {
            System.out.println(a);
        }
    }
}