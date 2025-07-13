package service;

import dao.ValidacaoDao;
import jakarta.persistence.EntityManager;
import model.Validacao;

public class ValidacaoService extends GenericService<Validacao> {
    private ValidacaoDao dao;

    public ValidacaoService(EntityManager em) {
        super(em, Validacao.class);
        this.dao = new ValidacaoDao(em);
    }
}
