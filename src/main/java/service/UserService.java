package service;

import dao.UserDao;
import jakarta.persistence.EntityManager;
import model.Auditor;
import model.User;

import java.util.List;

public class UserService extends GenericService<User> {
    private UserDao dao;

    public UserService(EntityManager em) {
        super(em, User.class);
        this.dao = new UserDao(em);
    }

    public List<User> buscarPorNome(String nome){
        return dao.buscarPorNome(nome);
    }
}
