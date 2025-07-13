package controller;

import jakarta.persistence.EntityManager;
import service.RelatorioAuditoriaService;
import service.CreditoCarbonoService;
import util.RelatorioSerializer;
import view.MenuView;
import vo.RelatorioAuditoriaVo;
import vo.RelatorioImpactoVo;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class RelatorioController {
    private RelatorioAuditoriaService relatorioAuditoriaService;
    private CreditoCarbonoService creditoCarbonoService;
    private MenuView view;

    public RelatorioController(EntityManager em, MenuView view) {
        this.relatorioAuditoriaService = new RelatorioAuditoriaService(em);
        this.creditoCarbonoService = new CreditoCarbonoService(em);
        this.view = view;
    }

    public void gerenciarRelatorios() {
        int opcao = view.mostrarMenuRelatorios();
        
        switch (opcao) {
            case 1:
                gerarRelatorioImpacto();
                break;
            case 2:
                gerarRelatorioAuditoria();
                break;
            case 3:
                gerarRelatorioAuditoriaPorPeriodo();
                break;
            case 4:
                gerarRelatorioAuditoriaPorAuditor();
                break;
            case 5:
                carregarRelatorioSalvo();
                break;
            default:
                view.mostrarErro("Opção inválida!");
        }
    }

    private void gerarRelatorioImpacto() {
        try {
            List<RelatorioImpactoVo> relatorio = creditoCarbonoService.relatorioImpacto();
            view.mostrarRelatorioImpacto(relatorio);
            
            String arquivo = RelatorioSerializer.serializarRelatorioImpacto(relatorio);
            if (arquivo != null) {
                view.mostrarSucesso("Relatório salvo em: " + arquivo);
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao gerar relatório de impacto: " + e.getMessage());
        }
    }

    private void gerarRelatorioAuditoria() {
        try {
            List<RelatorioAuditoriaVo> relatorio = relatorioAuditoriaService.gerarRelatorioAuditoria();
            view.mostrarRelatorioAuditoria(relatorio);
            
            String arquivo = RelatorioSerializer.serializarRelatorioAuditoria(relatorio);
            if (arquivo != null) {
                view.mostrarSucesso("Relatório salvo em: " + arquivo);
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao gerar relatório de auditoria: " + e.getMessage());
        }
    }

    private void gerarRelatorioAuditoriaPorPeriodo() {
        try {
            LocalDate dataInicio = view.lerData("Data início (YYYY-MM-DD): ");
            LocalDate dataFim = view.lerData("Data fim (YYYY-MM-DD): ");
            
            if (dataInicio == null || dataFim == null) {
                view.mostrarErro("Data inválida!");
                return;
            }
            
            List<RelatorioAuditoriaVo> relatorio = relatorioAuditoriaService.gerarRelatorioAuditoriaPorPeriodo(dataInicio, dataFim);
            view.mostrarRelatorioAuditoria(relatorio);
            
            String arquivo = RelatorioSerializer.serializarRelatorioAuditoria(relatorio);
            if (arquivo != null) {
                view.mostrarSucesso("Relatório salvo em: " + arquivo);
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao gerar relatório por período: " + e.getMessage());
        }
    }

    private void gerarRelatorioAuditoriaPorAuditor() {
        try {
            Long auditorId = view.lerIdAuditor();
            RelatorioAuditoriaVo relatorio = relatorioAuditoriaService.gerarRelatorioAuditorPorId(auditorId);
            
            if (relatorio != null) {
                view.mostrarRelatorioAuditoriaIndividual(relatorio);
                
                String arquivo = RelatorioSerializer.serializarRelatorioAuditoriaIndividual(relatorio);
                if (arquivo != null) {
                    view.mostrarSucesso("Relatório salvo em: " + arquivo);
                }
            } else {
                view.mostrarErro("Nenhum dado encontrado para o auditor especificado.");
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao gerar relatório por auditor: " + e.getMessage());
        }
    }

    private void carregarRelatorioSalvo() {
        try {
            File[] arquivos = RelatorioSerializer.obterArquivosRelatorios();
            view.mostrarRelatoriosSalvos(arquivos);
            
            if (arquivos != null && arquivos.length > 0) {
                int escolha = view.lerEscolhaRelatorio(arquivos.length);
                
                if (escolha > 0 && escolha <= arquivos.length) {
                    String arquivo = arquivos[escolha - 1].getPath();
                    
                    if (arquivo.contains("impacto")) {
                        List<RelatorioImpactoVo> relatorio = RelatorioSerializer.deserializarRelatorioImpacto(arquivo);
                        if (relatorio != null) {
                            view.mostrarRelatorioImpacto(relatorio);
                        }
                    } else if (arquivo.contains("auditoria_individual")) {
                        RelatorioAuditoriaVo relatorio = RelatorioSerializer.deserializarRelatorioAuditoriaIndividual(arquivo);
                        if (relatorio != null) {
                            view.mostrarRelatorioAuditoriaIndividual(relatorio);
                        }
                    } else if (arquivo.contains("auditoria")) {
                        List<RelatorioAuditoriaVo> relatorio = RelatorioSerializer.deserializarRelatorioAuditoria(arquivo);
                        if (relatorio != null) {
                            view.mostrarRelatorioAuditoria(relatorio);
                        }
                    }
                }
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao carregar relatório: " + e.getMessage());
        }
    }
}
