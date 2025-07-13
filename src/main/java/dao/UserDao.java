package dao;

import exception.DataAccessException;
import jakarta.persistence.EntityManager;
import model.User;
import java.util.List;

public class UserDao extends GenericDao<User> {
    private EntityManager em;

    public UserDao(EntityManager em) {
        super(em, User.class);
    }

    public List<User> buscarPorNome(String nome) {
        try{
            String jpql = "SELECT u FROM User u WHERE u.nome LIKE :nome";
            return em.createQuery(jpql, User.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        }catch(Exception e){
            throw new DataAccessException("Erro ao buscar categorias por nome: " + nome, e);
        }
    }
}