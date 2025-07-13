package service;

import dao.ValidacaoDao;
import jakarta.persistence.EntityManager;
import vo.RelatorioAuditoriaVo;

import java.time.LocalDate;
import java.util.List;

public class RelatorioAuditoriaService {
    private ValidacaoDao validacaoDao;

    public RelatorioAuditoriaService(EntityManager em) {
        this.validacaoDao = new ValidacaoDao(em);
    }

    public List<RelatorioAuditoriaVo> gerarRelatorioAuditoria() {
        return validacaoDao.relatorioAuditoria();
    }

    public RelatorioAuditoriaVo gerarRelatorioAuditorPorId(Long auditorId) {
        return validacaoDao.relatorioAuditoriaPorAuditor(auditorId);
    }

    public List<RelatorioAuditoriaVo> gerarRelatorioAuditoriaPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return validacaoDao.relatorioAuditoriaPorPeriodo(dataInicio, dataFim);
    }
}
