package service;

import dao.CreditoCarbonoDao;
import jakarta.persistence.EntityManager;
import model.CreditoCarbono;
import vo.RelatorioImpactoVo;

import java.util.List;

public class CreditoCarbonoService extends GenericService<CreditoCarbono> {
    private CreditoCarbonoDao dao;

    public CreditoCarbonoService(EntityManager em) {
        super(em, CreditoCarbono.class);
        this.dao = new CreditoCarbonoDao(em);
    }

    public List<RelatorioImpactoVo> relatorioImpacto() {
        return dao.relatorioImpacto();
    }

    public CreditoCarbono buscarPorUsuario(Long userId) {
        return dao.buscarPorUsuario(userId);
    }
}
