package controller;

import jakarta.persistence.EntityManager;
import model.Auditor;
import service.AuditorService;
import view.MenuView;

import java.util.List;

public class AuditorController {
    private AuditorService auditorService;
    private MenuView view;

    public AuditorController(EntityManager em, MenuView view) {
        this.auditorService = new AuditorService(em);
        this.view = view;
    }

    public void gerenciarAuditores() {
        int opcao = view.mostrarMenuAuditores();

        switch (opcao) {
            case 1:
                cadastrarAuditor();
                break;
            case 2:
                listarAuditores();
                break;
            case 3:
                buscarAuditor();
                break;
            default:
                view.mostrarErro("Opção inválida!");
        }
    }

    private void cadastrarAuditor() {
        try {
            Auditor auditor = view.coletarDadosAuditor();
            auditorService.inserir(auditor);
            view.mostrarSucesso("Auditor cadastrado com sucesso!");
            view.mostrarInfo("ID do auditor criado: " + auditor.getId());
        } catch (Exception e) {
            view.mostrarErro("Erro ao cadastrar auditor: " + e.getMessage());
        }
    }

    private void listarAuditores() {
        try {
            List<Auditor> auditores = auditorService.buscarTodos();
            view.mostrarAuditores(auditores);
        } catch (Exception e) {
            view.mostrarErro("Erro ao listar auditores: " + e.getMessage());
        }
    }

    private void buscarAuditor() {
        try {
            Long id = view.lerIdAuditor();
            Auditor auditor = auditorService.buscarPorId(id);
            if (auditor != null) {
                view.mostrarInfo(auditor.toString());
            } else {
                view.mostrarErro("Auditor não encontrado.");
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao buscar auditor: " + e.getMessage());
        }
    }
}
