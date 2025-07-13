package dao;

import exception.DataAccessException;
import jakarta.persistence.EntityManager;
import model.AcaoSustentavel;

import java.util.List;

public class AcaoSustentavelDao extends GenericDao<AcaoSustentavel> {
    EntityManager em;

    public AcaoSustentavelDao(EntityManager em) {
        super(em, AcaoSustentavel.class);
    }

    public List<AcaoSustentavel> buscarPorTipo(String tipo) {
        try{
            String jpql = "SELECT a FROM AcaoSustentavel a WHERE a.tipo LIKE :tipo";
            return em.createQuery(jpql, AcaoSustentavel.class)
                    .setParameter("tipo", "%" + tipo + "%")
                    .getResultList();
        }catch(Exception e){
            throw new DataAccessException("Erro ao buscar ação por tipo: " + tipo, e);
        }
    }
}
