package dao;

import exception.DataAccessException;
import jakarta.persistence.EntityManager;
import model.CreditoCarbono;
import vo.RelatorioImpactoVo;

import java.util.List;

public class CreditoCarbonoDao extends GenericDao<CreditoCarbono> {

    public CreditoCarbonoDao(EntityManager em) {
        super(em, CreditoCarbono.class);
    }

    public CreditoCarbono buscarPorUsuario(Long userId) {
        try {
            String jpql = "SELECT c FROM CreditoCarbono c WHERE c.user.id = :userId";
            return em.createQuery(jpql, CreditoCarbono.class)
                     .setParameter("userId", userId)
                     .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<RelatorioImpactoVo> relatorioImpacto() {
        try {
            String jpql = "SELECT new vo.RelatorioImpactoVo(" +
                    "c.user.nome, " +
                    "COUNT(a.id), " +
                    "SUM(c.totalCreditos), " +
                    "MAX(c.ultimaAtualizacao)) " +
                    "FROM CreditoCarbono c " +
                    "JOIN AcaoSustentavel a ON a.user.id = c.user.id " +
                    "GROUP BY c.user.nome " +
                    "ORDER BY c.user.nome DESC";

            return em.createQuery(jpql, RelatorioImpactoVo.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao gerar relatório de impacto", e);
        }
    }
}
