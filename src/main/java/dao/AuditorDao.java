package dao;

import exception.DataAccessException;
import jakarta.persistence.EntityManager;
import model.Auditor;

import java.util.List;

public class AuditorDao extends GenericDao<Auditor> {
    private EntityManager em;

    public AuditorDao(EntityManager em) {
        super(em, Auditor.class);
    }

    public List<Auditor> buscarPorNome(String nome) {
        try{
            String jpql = "SELECT a FROM Auditor a WHERE a.nome LIKE :nome";
            return em.createQuery(jpql, Auditor.class)
                    .setParameter("nome", nome)
                    .getResultList();
        }catch(Exception e){
            throw new DataAccessException("Erro ao buscar auditor por nome: " + nome, e);
        }
    }
}
