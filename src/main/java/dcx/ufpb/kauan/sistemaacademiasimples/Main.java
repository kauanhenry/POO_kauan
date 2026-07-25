package dcx.ufpb.kauan.sistemaacademiasimples;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Academia minhaAcademia = new Academia();
        Scanner sc = new Scanner(System.in);

        try {
            Atleta a1 = new Atleta("Ayla", 30, 66.5);
            minhaAcademia.matricularAtleta(a1);

            System.out.println("\nTentando matricular um novo aluno...");
            Atleta a2 = new Atleta("Kauan", 12, 86.6);
            minhaAcademia.matricularAtleta(a2);

        } catch (Exception e) {
            System.err.println("ERRO NO SISTEMA: " + e.getMessage());
        } finally {
            minhaAcademia.listarAlunos();
            sc.close();
        }
    }
}