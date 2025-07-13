package view;

import model.Validacao;
import model.AcaoSustentavel;
import model.Auditor;
import java.util.List;
import java.util.Scanner;

public class ValidacaoView {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== GERENCIAR VALIDAÇÕES ===");
        System.out.println("1. Criar Validação");
        System.out.println("2. Listar Validações");
        System.out.println("3. Buscar Validação por ID");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Validacao lerDadosValidacao() {
        Validacao validacao = new Validacao();

        System.out.print("ID da ação: ");
        Long acaoId = Long.parseLong(scanner.nextLine());
        AcaoSustentavel acao = new AcaoSustentavel();
        acao.setId(acaoId);
        validacao.setAcao(acao);

        System.out.print("ID do auditor: ");
        Long auditorId = Long.parseLong(scanner.nextLine());
        Auditor auditor = new Auditor();
        auditor.setId(auditorId);
        validacao.setAuditor(auditor);

        System.out.print("Observação: ");
        String observacao = scanner.nextLine();
        validacao.setObservacao(observacao);

        System.out.println("Status da Validação:");
        System.out.println("1. Aprovar");
        System.out.println("2. Rejeitar");
        System.out.println("3. Deixar Pendente");
        System.out.print("Escolha uma opção: ");
        int statusOpcao = Integer.parseInt(scanner.nextLine());

        switch (statusOpcao) {
            case 1:
                validacao.aprovar();
                break;
            case 2:
                validacao.rejeitar();
                break;
            default:
                break;
        }

        return validacao;
    }

    public Long lerIdValidacao() {
        System.out.print("ID da validação: ");
        return Long.parseLong(scanner.nextLine());
    }

    public void mostrarValidacoes(List<Validacao> validacoes) {
        if (validacoes.isEmpty()) {
            System.out.println("Nenhuma validação encontrada.");
        } else {
            validacoes.forEach(System.out::println);
        }
    }

    public void mostrarValidacao(Validacao validacao) {
        if (validacao != null) {
            System.out.println(validacao);
        } else {
            System.out.println("Validação não encontrada.");
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
