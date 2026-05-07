import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Academia minhaAcademia = new Academia();
        Scanner sc = new Scanner(System.in);

        try {
            Atleta a1 = new Atleta("Tião", 25, 80.5);
            minhaAcademia.matricularAtleta(a1);

            System.out.println("\nTentando matricular um aluno novo demais...");
            Atleta a2 = new Atleta("Zezinho", 12, 40.0);
            minhaAcademia.matricularAtleta(a2);

        } catch (Exception e) {
            System.err.println("ERRO NO SISTEMA: " + e.getMessage());
        } finally {
            minhaAcademia.listarAlunos();
            sc.close();
        }
    }
}