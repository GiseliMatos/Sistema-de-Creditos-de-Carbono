package service;

import dao.AuditorDao;
import jakarta.persistence.EntityManager;
import model.Auditor;

import java.util.List;

public class AuditorService extends GenericService<Auditor>{
    private AuditorDao dao;

    public AuditorService(EntityManager em) {
        super(em, Auditor.class);
        this.dao = new AuditorDao(em);
    }

    public List<Auditor> buscarPorNome(String nome){
        return dao.buscarPorNome(nome);
    }
}