package view;

import model.*;
import vo.RelatorioImpactoVo;
import vo.RelatorioAuditoriaVo;
import util.ConfigManager;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class MenuView {
    private ConsoleView console;

    public MenuView() {
        this.console = new ConsoleView();
    }

    public void mostrarCabecalho() {
        console.mostrarMensagem("=== Sistema de Créditos de Carbono ===");
        console.mostrarMensagem("Sistema: " + ConfigManager.getSistemaNome());
        console.mostrarMensagem("Versão: 1.0");
        console.mostrarMensagem("=====================================\n");
    }

    public int mostrarMenuPrincipal() {
        console.mostrarMensagem("\n=== MENU PRINCIPAL ===");
        console.mostrarMensagem("1. Gerenciar Usuários");
        console.mostrarMensagem("2. Gerenciar Ações Sustentáveis");
        console.mostrarMensagem("3. Gerenciar Auditores");
        console.mostrarMensagem("4. Gerenciar Validações");
        console.mostrarMensagem("5. Gerenciar Créditos de Carbono");
        console.mostrarMensagem("6. Relatórios");
        console.mostrarMensagem("7. Configurações do Sistema");
        console.mostrarMensagem("99. Excluir todos os dados cadastrados");
        console.mostrarMensagem("0. Sair");
        return console.lerInteiro("Escolha uma opção: ");
    }

    public int mostrarMenuUsuarios() {
        console.mostrarMensagem("\n=== GERENCIAR USUÁRIOS ===");
        console.mostrarMensagem("1. Cadastrar Usuário");
        console.mostrarMensagem("2. Listar Usuários");
        console.mostrarMensagem("3. Buscar Usuário por ID");
        console.mostrarMensagem("4. Atualizar Usuário");
        console.mostrarMensagem("5. Remover Usuário");
        return console.lerInteiro("Escolha uma opção: ");
    }

    public int mostrarMenuAcoes() {
        console.mostrarMensagem("\n=== GERENCIAR AÇÕES SUSTENTÁVEIS ===");
        console.mostrarMensagem("1. Cadastrar Ação");
        console.mostrarMensagem("2. Listar Ações");
        console.mostrarMensagem("3. Buscar Ação por ID");
        console.mostrarMensagem("4. Validar Quantidade");
        console.mostrarMensagem("5. Calcular Créditos");
        return console.lerInteiro("Escolha uma opção: ");
    }

    public int mostrarMenuAuditores() {
        console.mostrarMensagem("\n=== GERENCIAR AUDITORES ===");
        console.mostrarMensagem("1. Cadastrar Auditor");
        console.mostrarMensagem("2. Listar Auditores");
        console.mostrarMensagem("3. Buscar Auditor por ID");
        return console.lerInteiro("Escolha uma opção: ");
    }

    public int mostrarMenuValidacoes() {
        console.mostrarMensagem("\n=== GERENCIAR VALIDAÇÕES ===");
        console.mostrarMensagem("1. Criar Validação");
        console.mostrarMensagem("2. Listar Validações");
        console.mostrarMensagem("3. Buscar Validação por ID");
        return console.lerInteiro("Escolha uma opção: ");
    }

    public int mostrarMenuCreditos() {
        console.mostrarMensagem("\n=== GERENCIAR CRÉDITOS DE CARBONO ===");
        console.mostrarMensagem("1. Listar Créditos");
        console.mostrarMensagem("2. Buscar Crédito por ID");
        console.mostrarMensagem("3. Atualizar Créditos de Usuário");
        return console.lerInteiro("Escolha uma opção: ");
    }

    public int mostrarMenuRelatorios() {
        console.mostrarMensagem("\n=== RELATÓRIOS ===");
        console.mostrarMensagem("1. Relatório de Impacto");
        console.mostrarMensagem("2. Relatório de Auditoria");
        console.mostrarMensagem("3. Relatório de Auditoria por Período");
        console.mostrarMensagem("4. Relatório de Auditoria por Auditor");
        console.mostrarMensagem("5. Carregar Relatório Salvo");
        return console.lerInteiro("Escolha uma opção: ");
    }

    public int mostrarMenuStatusValidacao() {
        console.mostrarMensagem("\nStatus da Validação:");
        console.mostrarMensagem("1. Aprovar");
        console.mostrarMensagem("2. Rejeitar");
        console.mostrarMensagem("3. Deixar Pendente");
        return console.lerInteiro("Escolha uma opção: ");
    }

    public void mostrarConfiguracoes() {
        console.mostrarMensagem("\n=== CONFIGURAÇÕES DO SISTEMA ===");
        console.mostrarMensagem("Sistema: " + ConfigManager.getSistemaNome());
        console.mostrarMensagem("Versão: 1.5");
        console.mostrarMensagem("Desenvolvedora: Giseli");
        console.mostrarMensagem("\n--- Taxas de Conversão ---");
        console.mostrarMensagem("Taxa Papel: " + ConfigManager.getTaxa("papel"));
        console.mostrarMensagem("Taxa Plástico: " + ConfigManager.getTaxa("plastico"));
        console.mostrarMensagem("Taxa Vidro: " + ConfigManager.getTaxa("vidro"));
        console.mostrarMensagem("Taxa Metal: " + ConfigManager.getTaxa("metal"));
        console.mostrarMensagem("Taxa Compostagem: " + ConfigManager.getTaxa("compostagem"));
        console.mostrarMensagem("Taxa Energia Solar: " + ConfigManager.getTaxa("energiaSolar"));
        console.mostrarMensagem("Taxa Transporte Sustentavel: " + ConfigManager.getTaxa("transporteSustentavel"));
        console.mostrarMensagem("\n--- Limites Mensais ---");
        console.mostrarMensagem("Limite Papel: " + ConfigManager.getLimiteMensal("papel"));
        console.mostrarMensagem("Limite Plástico: " + ConfigManager.getLimiteMensal("plastico"));
        console.mostrarMensagem("Limite Vidro: " + ConfigManager.getLimiteMensal("vidro"));
        console.mostrarMensagem("Limite Metal: " + ConfigManager.getLimiteMensal("metal"));
        console.mostrarMensagem("Limite Compostagem: " + ConfigManager.getLimiteMensal("compostagem"));
        console.mostrarMensagem("Limite Energia Solar: " + ConfigManager.getLimiteMensal("energiaSolar"));
        console.mostrarMensagem("Limite Transporte Sustentavel: " + ConfigManager.getLimiteMensal("transporteSustentavel"));
        console.mostrarMensagem("\n--- Valores Mínimos ---");
        console.mostrarMensagem("Mínimo Papel: " + ConfigManager.getValorMinimo("papel"));
        console.mostrarMensagem("Mínimo Plástico: " + ConfigManager.getValorMinimo("plastico"));
        console.mostrarMensagem("Mínimo Vidro: " + ConfigManager.getValorMinimo("vidro"));
        console.mostrarMensagem("Mínimo Metal: " + ConfigManager.getValorMinimo("metal"));
        console.mostrarMensagem("Mínimo Compostagem: " + ConfigManager.getValorMinimo("compostagem"));
        console.mostrarMensagem("Mínimo Energia Solar: " + ConfigManager.getValorMinimo("energiaSolar"));
        console.mostrarMensagem("Mínimo Transporte Sustentavel: " + ConfigManager.getValorMinimo("transporteSustentavel"));
    }

    public void mostrarUsuarios(List<User> usuarios) {
        console.mostrarMensagem("\n--- Lista de Usuários ---");
        if (usuarios.isEmpty()) {
            console.mostrarMensagem("Nenhum usuário encontrado.");
        } else {
            usuarios.forEach(System.out::println);
        }
    }

    public void mostrarAcoes(List<AcaoSustentavel> acoes) {
        console.mostrarMensagem("\n--- Lista de Ações Sustentáveis ---");
        if (acoes.isEmpty()) {
            console.mostrarMensagem("Nenhuma ação encontrada.");
        } else {
            acoes.forEach(System.out::println);
        }
    }

    public void mostrarAuditores(List<Auditor> auditores) {
        console.mostrarMensagem("\n--- Lista de Auditores ---");
        if (auditores.isEmpty()) {
            console.mostrarMensagem("Nenhum auditor encontrado.");
        } else {
            auditores.forEach(System.out::println);
        }
    }

    public void mostrarValidacoes(List<Validacao> validacoes) {
        console.mostrarMensagem("\n--- Lista de Validações ---");
        if (validacoes.isEmpty()) {
            console.mostrarMensagem("Nenhuma validação encontrada.");
        } else {
            validacoes.forEach(System.out::println);
        }
    }

    public void mostrarCreditos(List<CreditoCarbono> creditos) {
        console.mostrarMensagem("\n--- Lista de Créditos de Carbono ---");
        if (creditos.isEmpty()) {
            console.mostrarMensagem("Nenhum crédito encontrado.");
        } else {
            creditos.forEach(System.out::println);
        }
    }

    public void mostrarRelatorioImpacto(List<RelatorioImpactoVo> relatorio) {
        console.mostrarMensagem("\n--- Relatório de Impacto ---");
        if (relatorio.isEmpty()) {
            console.mostrarMensagem("Nenhum dado encontrado para o relatório.");
        } else {
            relatorio.forEach(System.out::println);
        }
    }

    public void mostrarRelatorioAuditoria(List<RelatorioAuditoriaVo> relatorio) {
        console.mostrarMensagem("\n--- Relatório de Auditoria ---");
        if (relatorio.isEmpty()) {
            console.mostrarMensagem("Nenhum dado encontrado para o relatório.");
        } else {
            relatorio.forEach(System.out::println);
        }
    }

    public void mostrarRelatorioAuditoriaIndividual(RelatorioAuditoriaVo relatorio) {
        console.mostrarMensagem("\n=== Relatório de Auditoria Individual ===");
        if (relatorio == null) {
            console.mostrarMensagem("Nenhum dado encontrado para o auditor especificado.");
        } else {
            console.mostrarMensagem(relatorio.toString());
        }
    }

    public void mostrarRelatoriosSalvos(File[] arquivos) {
        if (arquivos == null || arquivos.length == 0) {
            console.mostrarMensagem("Nenhum relatório salvo encontrado.");
            return;
        }

        console.mostrarMensagem("\n=== Relatórios Salvos ===");
        for (int i = 0; i < arquivos.length; i++) {
            console.mostrarMensagem((i + 1) + ". " + arquivos[i].getName());
        }
    }

    public User coletarDadosUsuario() {
        console.mostrarMensagem("\n--- Cadastrar Usuário ---");
        String nome = console.lerString("Nome: ");
        String email = console.lerString("Email: ");
        String cpfCnpj = console.lerString("CPF/CNPJ: ");
        String tipo = console.lerString("Tipo (PF/PJ): ");
        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setCpfCnpj(cpfCnpj);
        user.setTipo(tipo);
        return user;
    }

    public Auditor coletarDadosAuditor() {
        console.mostrarMensagem("\n--- Cadastrar Auditor ---");
        String nome = console.lerString("Nome: ");
        String email = console.lerString("Email: ");
        String registro = console.lerString("Registro Profissional: ");
        return new Auditor(nome, email, registro);
    }

    public Long lerIdUsuario() {
        return (long) console.lerInteiro("ID do usuário: ");
    }

    public Long lerIdAcao() {
        return (long) console.lerInteiro("ID da ação: ");
    }

    public Long lerIdAuditor() {
        return (long) console.lerInteiro("ID do auditor: ");
    }

    public Long lerIdValidacao() {
        return (long) console.lerInteiro("ID da validação: ");
    }

    public Long lerIdCredito() {
        return (long) console.lerInteiro("ID do crédito: ");
    }

    public String lerObservacao() {
        return console.lerString("Observação: ");
    }

    public String lerNovoNome(String nomeAtual) {
        return console.lerString("Novo nome (atual: " + nomeAtual + "): ");
    }

    public double lerNovosCreditos(double creditosAtuais) {
        return console.lerDouble("Novos créditos (atual: " + creditosAtuais + "): ");
    }

    public double lerTotalCreditos() {
        return console.lerDouble("Total de créditos: ");
    }

    public int lerEscolhaRelatorio(int maxOpcoes) {
        return console.lerInteiro("\nEscolha o número do relatório para carregar (0 para cancelar): ");
    }

    public String lerString(String prompt) {
        return console.lerString(prompt);
    }

    public int mostrarMenuTipoAcao() {
        System.out.println("\n=== Escolha o Tipo de Ação Sustentável ===");
        System.out.println("1. Papel");
        System.out.println("2. Plástico");
        System.out.println("3. Vidro");
        System.out.println("4. Metal");
        System.out.println("5. Compostagem");
        System.out.println("6. Energia Solar");
        System.out.println("7. Transporte Sustentável");
        System.out.println("8. Outro");
        return console.lerInteiro("Escolha uma opção: ");
    }

    public double lerDouble(String prompt) {
        return console.lerDouble(prompt);
    }

    public int lerInteiro(String prompt) {
        return console.lerInteiro(prompt);
    }

    public LocalDate lerData(String prompt) {
        String dataStr = console.lerString(prompt);
        try {
            return LocalDate.parse(dataStr);
        } catch (Exception e) {
            mostrarErro("Data inválida! Use o formato YYYY-MM-DD");
            return null;
        }
    }

    public void mostrarSucesso(String mensagem) {
        console.mostrarMensagem("✓ " + mensagem);
    }

    public void mostrarErro(String mensagem) {
        console.mostrarErro("✗ " + mensagem);
    }

    public void mostrarInfo(String mensagem) {
        console.mostrarMensagem("ℹ " + mensagem);
    }

    public void aguardarContinuar() {
        console.aguardarEnter();
    }

    public void fechar() {
        console.fechar();
    }
}
