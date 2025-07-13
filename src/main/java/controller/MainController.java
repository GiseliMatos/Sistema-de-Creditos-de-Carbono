package controller;

import jakarta.persistence.EntityManager;
import model.*;
import service.*;
import dao.*;
import util.ConfigManager;
import util.JPAUtil;
import view.MenuView;

public class MainController {
    private EntityManager em;
    private MenuView view;

    private UserController userController;
    private AcaoSustentavelController acaoController;
    private AuditorController auditorController;
    private ValidacaoController validacaoController;
    private CreditoCarbonoController creditoController;
    private RelatorioController relatorioController;

    public MainController() {
        this.em = JPAUtil.getEntityManager();
        this.view = new MenuView();

        this.userController = new UserController(em, view);
        this.acaoController = new AcaoSustentavelController(em, view);
        this.auditorController = new AuditorController(em, view);
        this.validacaoController = new ValidacaoController(em, view);
        this.creditoController = new CreditoCarbonoController(em, view);
        this.relatorioController = new RelatorioController(em, view);
    }

    public void iniciar() {
        ConfigManager.loadConfig();
        
        view.mostrarCabecalho();

        try {
            boolean continuar = true;
            while (continuar) {
                int opcao = view.mostrarMenuPrincipal();
                
                switch (opcao) {
                    case 1:
                        userController.gerenciarUsuarios();
                        break;
                    case 2:
                        acaoController.gerenciarAcoes();
                        break;
                    case 3:
                        auditorController.gerenciarAuditores();
                        break;
                    case 4:
                        validacaoController.gerenciarValidacoes();
                        break;
                    case 5:
                        creditoController.gerenciarCreditos();
                        break;
                    case 6:
                        relatorioController.gerenciarRelatorios();
                        break;
                    case 7:
                        view.mostrarConfiguracoes();
                        break;
                    case 99:
                        excluirTodosDados();
                        break;
                    case 0:
                        continuar = false;
                        view.mostrarInfo("Encerrando sistema...");
                        break;
                    default:
                        view.mostrarErro("Opção inválida!");
                }
                
                if (continuar) {
                    view.aguardarContinuar();
                }
            }
        } finally {
            em.close();
            view.fechar();
        }
    }

    private void excluirTodosDados() {
        try {
            em.getTransaction().begin();

            em.createQuery("DELETE FROM CreditoCarbono").executeUpdate();
            em.createQuery("DELETE FROM Validacao").executeUpdate();
            em.createQuery("DELETE FROM AcaoSustentavel").executeUpdate();
            em.createQuery("DELETE FROM Auditor").executeUpdate();
            em.createQuery("DELETE FROM User").executeUpdate();

            em.createNativeQuery("ALTER TABLE credito_carbono ALTER COLUMN id RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER TABLE validacao ALTER COLUMN id RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER TABLE acao_sustentavel ALTER COLUMN id RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER TABLE auditor ALTER COLUMN id RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER TABLE usuario ALTER COLUMN id RESTART WITH 1").executeUpdate();

            em.getTransaction().commit();

            view.mostrarSucesso("Todos os dados cadastrados foram limpos com sucesso e IDs reiniciados.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            view.mostrarErro("Erro ao limpar dados: " + e.getMessage());
        }
    }
}
