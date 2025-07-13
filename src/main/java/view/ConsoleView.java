package view;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleView {
    private static Scanner scanner = new Scanner(System.in);

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void mostrarErro(String erro) {
        System.err.println(erro);
    }

    public String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int lerInteiro(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public double lerDouble(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public LocalDate lerData(String prompt) {
        System.out.print(prompt);
        try {
            return LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            return null;
        }
    }

    public void aguardarEnter() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    public void fechar() {
        scanner.close();
    }
}
