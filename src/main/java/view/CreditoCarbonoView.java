package view;

import model.CreditoCarbono;
import java.util.List;
import java.util.Scanner;

public class CreditoCarbonoView {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== GERENCIAR CRÉDITOS DE CARBONO ===");
        System.out.println("1. Cadastrar Crédito");
        System.out.println("2. Listar Créditos");
        System.out.println("3. Buscar Crédito por ID");
        System.out.println("4. Atualizar Créditos de Usuário");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public CreditoCarbono lerDadosCredito() {
        CreditoCarbono credito = new CreditoCarbono();

        System.out.print("ID do usuário: ");
        Long userId = Long.parseLong(scanner.nextLine());

        model.User user = new model.User();
        user.setId(userId);
        credito.setUser(user);

        System.out.print("Total de créditos: ");
        double totalCreditos = Double.parseDouble(scanner.nextLine());
        credito.setTotalCreditos(totalCreditos);

        credito.setUltimaAtualizacao(java.time.LocalDate.now());

        return credito;
    }

    public Long lerIdCredito() {
        System.out.print("ID do crédito: ");
        return Long.parseLong(scanner.nextLine());
    }

    public double lerNovosCreditos(double atual) {
        System.out.print("Novos créditos (atual: " + atual + "): ");
        return Double.parseDouble(scanner.nextLine());
    }

    public void mostrarCreditos(List<CreditoCarbono> creditos) {
        if (creditos.isEmpty()) {
            System.out.println("Nenhum crédito encontrado.");
        } else {
            creditos.forEach(System.out::println);
        }
    }

    public void mostrarCredito(CreditoCarbono credito) {
        if (credito != null) {
            System.out.println(credito);
        } else {
            System.out.println("Crédito não encontrado.");
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
