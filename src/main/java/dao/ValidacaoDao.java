package dao;

import jakarta.persistence.EntityManager;
import model.Validacao;
import vo.RelatorioAuditoriaVo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidacaoDao extends GenericDao<Validacao> {

    public ValidacaoDao(EntityManager em) {
        super(em, Validacao.class);
    }

    public List<RelatorioAuditoriaVo> relatorioAuditoria() {
        try {
            String jpql = "SELECT v.auditor.nome, v.auditor.registroProfissional, " +
                    "SUM(CASE WHEN v.status = 'APROVADO' THEN 1 ELSE 0 END), " +
                    "SUM(CASE WHEN v.status = 'REJEITADO' THEN 1 ELSE 0 END), " +
                    "v.auditor.id " +
                    "FROM Validacao v " +
                    "GROUP BY v.auditor.id, v.auditor.nome, v.auditor.registroProfissional";

            List<Object[]> resultados = em.createQuery(jpql).getResultList();


            return resultados.stream().map(resultado -> {
                String nomeAuditor = (String) resultado[0];
                String registroAuditor = (String) resultado[1];
                int validadas = ((Number) resultado[2]).intValue();
                int rejeitadas = ((Number) resultado[3]).intValue();
                Long auditorId = (Long) resultado[4];

                Map<String, Double> taxasPorTipo = calcularTaxaAprovacaoPorTipo(auditorId);

                return new RelatorioAuditoriaVo(nomeAuditor, registroAuditor,
                        validadas, rejeitadas, taxasPorTipo, LocalDate.now());
            }).toList();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de auditoria", e);
        }
    }

    public RelatorioAuditoriaVo relatorioAuditoriaPorAuditor(Long auditorId) {
        try {
            String jpql = "SELECT v.auditor.nome, v.auditor.registroProfissional, " +
                    "SUM(CASE WHEN v.status = 'APROVADO' THEN 1 ELSE 0 END), " +
                    "SUM(CASE WHEN v.status = 'REJEITADO' THEN 1 ELSE 0 END) " +
                    "FROM Validacao v " +
                    "WHERE v.auditor.id = :auditorId " +
                    "GROUP BY v.auditor.nome, v.auditor.registroProfissional";

            Object[] resultado = (Object[]) em.createQuery(jpql)
                    .setParameter("auditorId", auditorId)
                    .getSingleResult();

            String nomeAuditor = (String) resultado[0];
            String registroAuditor = (String) resultado[1];
            int validadas = ((Number) resultado[2]).intValue();
            int rejeitadas = ((Number) resultado[3]).intValue();

            Map<String, Double> taxasPorTipo = calcularTaxaAprovacaoPorTipo(auditorId);

            return new RelatorioAuditoriaVo(nomeAuditor, registroAuditor,
                    validadas, rejeitadas, taxasPorTipo, LocalDate.now());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de auditoria por auditor", e);
        }
    }

    public List<RelatorioAuditoriaVo> relatorioAuditoriaPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT v.auditor.nome, v.auditor.registroProfissional, " +
                    "SUM(CASE WHEN v.status = 'APROVADO' THEN 1 ELSE 0 END), " +
                    "SUM(CASE WHEN v.status = 'REJEITADO' THEN 1 ELSE 0 END), " +
                    "v.auditor.id " +
                    "FROM Validacao v " +
                    "WHERE v.dataValidacao BETWEEN :dataInicio AND :dataFim " +
                    "GROUP BY v.auditor.id, v.auditor.nome, v.auditor.registroProfissional";

            List<Object[]> resultados = em.createQuery(jpql)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();

            return resultados.stream().map(resultado -> {
                String nomeAuditor = (String) resultado[0];
                String registroAuditor = (String) resultado[1];
                int validadas = ((Number) resultado[2]).intValue();
                int rejeitadas = ((Number) resultado[3]).intValue();
                Long auditorId = (Long) resultado[4];

                Map<String, Double> taxasPorTipo = calcularTaxaAprovacaoPorTipo(auditorId);

                return new RelatorioAuditoriaVo(nomeAuditor, registroAuditor,
                        validadas, rejeitadas, taxasPorTipo, LocalDate.now());
            }).toList();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de auditoria por período", e);
        }
    }

    private Map<String, Double> calcularTaxaAprovacaoPorTipo(Long auditorId) {
        try {
            String jpql = "SELECT v.acao.tipo, " +
                    "SUM(CASE WHEN v.status = 'APROVADO' THEN 1 ELSE 0 END) * 1.0 / COUNT(*) " +
                    "FROM Validacao v " +
                    "WHERE v.auditor.id = :auditorId " +
                    "GROUP BY v.acao.tipo";

            List<Object[]> resultados = em.createQuery(jpql)
                    .setParameter("auditorId", auditorId)
                    .getResultList();

            Map<String, Double> taxas = new HashMap<>();
            for (Object[] resultado : resultados) {
                String tipo = (String) resultado[0];
                Double taxa = ((Number) resultado[1]).doubleValue();
                taxas.put(tipo, taxa);
            }

            return taxas;
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}