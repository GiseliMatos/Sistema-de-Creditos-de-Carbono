package controller;

import jakarta.persistence.EntityManager;
import model.*;
import service.*;
import view.MenuView;

import java.time.LocalDate;
import java.util.List;

public class ValidacaoController {
    private ValidacaoService validacaoService;
    private AcaoSustentavelService acaoService;
    private AuditorService auditorService;
    private MenuView view;

    public ValidacaoController(EntityManager em, MenuView view) {
        this.validacaoService = new ValidacaoService(em);
        this.acaoService = new AcaoSustentavelService(em);
        this.auditorService = new AuditorService(em);
        this.view = view;
    }

    public void gerenciarValidacoes() {
        int opcao = view.mostrarMenuValidacoes();
        
        switch (opcao) {
            case 1:
                criarValidacao();
                break;
            case 2:
                listarValidacoes();
                break;
            case 3:
                buscarValidacao();
                break;
            default:
                view.mostrarErro("Opção inválida!");
        }
    }

    private void criarValidacao() {
        try {
            Long acaoId = view.lerIdAcao();
            AcaoSustentavel acao = acaoService.buscarPorId(acaoId);
            
            if (acao == null) {
                view.mostrarErro("Ação não encontrada.");
                return;
            }
            
            Long auditorId = view.lerIdAuditor();
            Auditor auditor = auditorService.buscarPorId(auditorId);
            
            if (auditor == null) {
                view.mostrarErro("Auditor não encontrado.");
                return;
            }
            
            int statusOpcao = view.mostrarMenuStatusValidacao();
            Validacao validacao = new Validacao();
            validacao.setAcao(acao);
            validacao.setAuditor(auditor);
            
            switch (statusOpcao) {
                case 1:
                    validacao.aprovar();
                    acao.setValidado(true);
                    acaoService.atualizar(acao);
                    break;
                case 2:
                    validacao.rejeitar();
                    acao.setValidado(false);
                    acaoService.atualizar(acao);
                    break;
                case 3:
                    acao.setValidado(false);
                    acaoService.atualizar(acao);
                    break;
                default:
                    view.mostrarErro("Status inválido!");
                    return;
            }
            
            String observacao = view.lerObservacao();
            validacao.setObservacao(observacao);
            validacao.setDataValidacao(LocalDate.now());
            
            validacaoService.inserir(validacao);
            view.mostrarSucesso("Validação criada com sucesso!");
            view.mostrarInfo("ID da validação criada: " + validacao.getId());
        } catch (Exception e) {
            view.mostrarErro("Erro ao criar validação: " + e.getMessage());
        }
    }

    private void listarValidacoes() {
        try {
            List<Validacao> validacoes = validacaoService.buscarTodos();
            view.mostrarValidacoes(validacoes);
        } catch (Exception e) {
            view.mostrarErro("Erro ao listar validações: " + e.getMessage());
        }
    }

    private void buscarValidacao() {
        try {
            Long id = view.lerIdValidacao();
            Validacao validacao = validacaoService.buscarPorId(id);
            if (validacao != null) {
                view.mostrarInfo(validacao.toString());
            } else {
                view.mostrarErro("Validação não encontrada.");
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao buscar validação: " + e.getMessage());
        }
    }
}
