package view;

import model.Auditor;
import java.util.List;
import java.util.Scanner;

public class AuditorView {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== GERENCIAR AUDITORES ===");
        System.out.println("1. Cadastrar Auditor");
        System.out.println("2. Listar Auditores");
        System.out.println("3. Buscar Auditor por ID");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Auditor lerDadosAuditor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Registro Profissional: ");
        String registro = scanner.nextLine();

        return new Auditor(nome, email, registro);
    }

    public Long lerIdAuditor() {
        System.out.print("ID do auditor: ");
        return Long.parseLong(scanner.nextLine());
    }

    public void mostrarAuditores(List<Auditor> auditores) {
        if (auditores.isEmpty()) {
            System.out.println("Nenhum auditor encontrado.");
        } else {
            auditores.forEach(System.out::println);
        }
    }

    public void mostrarAuditor(Auditor auditor) {
        if (auditor != null) {
            System.out.println(auditor);
        } else {
            System.out.println("Auditor não encontrado.");
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
