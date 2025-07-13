package view;

import model.AcaoSustentavel;
import model.User;
import java.util.List;
import java.util.Scanner;

public class AcaoSustentavelView {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== GERENCIAR AÇÕES SUSTENTÁVEIS ===");
        System.out.println("1. Cadastrar Ação");
        System.out.println("2. Listar Ações");
        System.out.println("3. Buscar Ação por ID");
        System.out.println("4. Validar Quantidade");
        System.out.println("5. Calcular Créditos");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public AcaoSustentavel lerDadosAcao() {
        AcaoSustentavel acao = new AcaoSustentavel();

        System.out.print("ID do usuário: ");
        Long userId = Long.parseLong(scanner.nextLine());

        User user = new User();
        user.setId(userId);
        acao.setUser(user);

        System.out.print("Tipo (Papel/Plástico/Vidro/Metal): ");
        String tipo = scanner.nextLine();
        acao.setTipo(tipo);

        System.out.print("Quantidade: ");
        double quantidade = Double.parseDouble(scanner.nextLine());
        acao.setQuantidade(quantidade);

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        acao.setDescricao(descricao);

        return acao;
    }

    public Long lerIdAcao() {
        System.out.print("ID da ação: ");
        return Long.parseLong(scanner.nextLine());
    }

    public void mostrarAcoes(List<AcaoSustentavel> acoes) {
        if (acoes.isEmpty()) {
            System.out.println("Nenhuma ação encontrada.");
        } else {
            for (AcaoSustentavel acao : acoes) {
                String statusValidacao = acao.isValidado() ? "Aprovado" : "Pendente";
                System.out.println(acao.toString() + " - Status Validação: " + statusValidacao);
            }
        }
    }

    public void mostrarAcao(AcaoSustentavel acao) {
        if (acao != null) {
            System.out.println(acao);
        } else {
            System.out.println("Ação não encontrada.");
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public String lerTipo() {
        System.out.print("Tipo da ação: ");
        return scanner.nextLine();
    }

    public double lerQuantidade() {
        System.out.print("Quantidade: ");
        return Double.parseDouble(scanner.nextLine());
    }
}
