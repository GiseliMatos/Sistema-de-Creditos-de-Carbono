package controller;

import jakarta.persistence.EntityManager;
import model.User;
import service.UserService;
import view.MenuView;

import java.util.List;

public class UserController {
    private UserService userService;
    private MenuView view;

    public UserController(EntityManager em, MenuView view) {
        this.userService = new UserService(em);
        this.view = view;
    }

    public void gerenciarUsuarios() {
        int opcao = view.mostrarMenuUsuarios();
        
        switch (opcao) {
            case 1:
                cadastrarUsuario();
                break;
            case 2:
                listarUsuarios();
                break;
            case 3:
                buscarUsuario();
                break;
            case 4:
                atualizarUsuario();
                break;
            case 5:
                removerUsuario();
                break;
            default:
                view.mostrarErro("Opção inválida!");
        }
    }

    private void cadastrarUsuario() {
        try {
            User user = view.coletarDadosUsuario();
            userService.inserir(user);
            view.mostrarSucesso("Usuário cadastrado com sucesso!");
            view.mostrarInfo("ID do usuário criado: " + user.getId());
        } catch (Exception e) {
            view.mostrarErro("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    private void listarUsuarios() {
        try {
            List<User> users = userService.buscarTodos();
            view.mostrarUsuarios(users);
        } catch (Exception e) {
            view.mostrarErro("Erro ao listar usuários: " + e.getMessage());
        }
    }

    private void buscarUsuario() {
        try {
            Long id = view.lerIdUsuario();
            User user = userService.buscarPorId(id);
            if (user != null) {
                view.mostrarInfo(user.toString());
            } else {
                view.mostrarErro("Usuário não encontrado.");
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    private void atualizarUsuario() {
        try {
            Long id = view.lerIdUsuario();
            User user = userService.buscarPorId(id);
            if (user != null) {
                String novoNome = view.lerNovoNome(user.getNome());
                if (!novoNome.trim().isEmpty()) {
                    user.setNome(novoNome);
                }
                userService.atualizar(user);
                view.mostrarSucesso("Usuário atualizado com sucesso!");
            } else {
                view.mostrarErro("Usuário não encontrado.");
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    private void removerUsuario() {
        try {
            Long id = view.lerIdUsuario();
            User user = userService.buscarPorId(id);
            if (user != null) {
                userService.remover(user);
                view.mostrarSucesso("Usuário removido com sucesso!");
            } else {
                view.mostrarErro("Usuário não encontrado.");
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao remover usuário: " + e.getMessage());
        }
    }
}
