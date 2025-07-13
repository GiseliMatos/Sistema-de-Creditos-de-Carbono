package view;

import java.util.Scanner;

public class RelatorioView {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== RELATÓRIOS ===");
        System.out.println("1. Relatório de Impacto");
        System.out.println("2. Relatório de Auditoria");
        System.out.println("3. Relatório de Auditoria por Período");
        System.out.println("4. Relatório de Auditoria por Auditor");
        System.out.println("5. Carregar Relatório Salvo");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String lerData(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public Long lerIdAuditor() {
        System.out.print("ID do Auditor: ");
        return Long.parseLong(scanner.nextLine());
    }

    public int lerEscolhaRelatorio(int max) {
        System.out.print("\nEscolha o número do relatório para carregar (0 para cancelar): ");
        int escolha = Integer.parseInt(scanner.nextLine());
        if (escolha < 0 || escolha > max) {
            System.out.println("Opção inválida.");
            return -1;
        }
        return escolha;
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
