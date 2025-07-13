package controller;

import jakarta.persistence.EntityManager;
import model.CreditoCarbono;
import model.User;
import service.CreditoCarbonoService;
import service.UserService;
import view.MenuView;

import java.time.LocalDate;
import java.util.List;

public class CreditoCarbonoController {
    private CreditoCarbonoService creditoService;
    private UserService userService;
    private MenuView view;

    public CreditoCarbonoController(EntityManager em, MenuView view) {
        this.creditoService = new CreditoCarbonoService(em);
        this.userService = new UserService(em);
        this.view = view;
    }

    public void gerenciarCreditos() {
        int opcao = view.mostrarMenuCreditos();
        
        switch (opcao) {
            case 1:
                listarCreditos();
                break;
            case 2:
                buscarCredito();
                break;
            case 3:
                atualizarCreditosUsuario();
                break;
            default:
                view.mostrarErro("Opção inválida!");
        }
    }

    private void cadastrarCredito() {
        try {
            Long userId = view.lerIdUsuario();
            User user = userService.buscarPorId(userId);
            
            if (user == null) {
                view.mostrarErro("Usuário não encontrado.");
                return;
            }
            
            double totalCreditos = view.lerTotalCreditos();
            
            CreditoCarbono credito = new CreditoCarbono();
            credito.setUser(user);
            credito.setTotalCreditos(totalCreditos);
            credito.setUltimaAtualizacao(LocalDate.now());
            
            creditoService.inserir(credito);
            view.mostrarSucesso("Crédito cadastrado com sucesso!");
            view.mostrarInfo("ID do crédito criado: " + credito.getId());
        } catch (Exception e) {
            view.mostrarErro("Erro ao cadastrar crédito: " + e.getMessage());
        }
    }

    private void listarCreditos() {
        try {
            List<CreditoCarbono> creditos = creditoService.buscarTodos();
            view.mostrarCreditos(creditos);
        } catch (Exception e) {
            view.mostrarErro("Erro ao listar créditos: " + e.getMessage());
        }
    }

    private void buscarCredito() {
        try {
            Long id = view.lerIdCredito();
            CreditoCarbono credito = creditoService.buscarPorId(id);
            if (credito != null) {
                view.mostrarInfo(credito.toString());
            } else {
                view.mostrarErro("Crédito não encontrado.");
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao buscar crédito: " + e.getMessage());
        }
    }

    private void atualizarCreditosUsuario() {
        try {
            Long userId = view.lerIdUsuario();
            User user = userService.buscarPorId(userId);
            
            if (user == null) {
                view.mostrarErro("Usuário não encontrado.");
                return;
            }

            CreditoCarbono credito = creditoService.buscarPorUsuario(userId);
            
            if (credito == null) {
                view.mostrarErro("Usuário não possui créditos cadastrados.");
                return;
            }
            
            double novosCreditos = view.lerNovosCreditos(credito.getTotalCreditos());
            credito.setTotalCreditos(novosCreditos);
            credito.setUltimaAtualizacao(LocalDate.now());
            
            creditoService.atualizar(credito);
            view.mostrarSucesso("Créditos atualizados com sucesso!");
            
        } catch (Exception e) {
            view.mostrarErro("Erro ao atualizar créditos: " + e.getMessage());
        }
    }
}
