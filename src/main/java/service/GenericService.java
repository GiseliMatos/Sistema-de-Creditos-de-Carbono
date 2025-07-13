package service;

import dao.GenericDao;
import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class GenericService<T> {

    protected GenericDao<T> dao;

    public GenericService(EntityManager em, Class<T> entityClass) {
        this.dao = new GenericDao<T>(em, entityClass) {};
    }

    public void inserir(T entity) {
        dao.cadastrar(entity);
    }

    public void atualizar(T entity) {
        dao.atualizar(entity);
    }

    public void remover(T entity) {
        dao.remover(entity);
    }

    public T buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    public List<T> buscarTodos() {
        return dao.buscarTodos();
    }
}