package controller;

import jakarta.persistence.EntityManager;
import model.AcaoSustentavel;
import model.User;
import service.AcaoSustentavelService;
import service.UserService;
import service.CreditoCarbonoService;
import model.CreditoCarbono;
import view.MenuView;
import java.time.LocalDate;
import java.util.List;
import enumpac.TipoAcao;

public class AcaoSustentavelController {
    private AcaoSustentavelService acaoService;
    private UserService userService;
    private CreditoCarbonoService creditoService;
    private MenuView view;

    public AcaoSustentavelController(EntityManager em, MenuView view) {
        this.acaoService = new AcaoSustentavelService(em);
        this.userService = new UserService(em);
        this.creditoService = new CreditoCarbonoService(em);
        this.view = view;
    }

    public void gerenciarAcoes() {
        int opcao = view.mostrarMenuAcoes();
        
        switch (opcao) {
            case 1:
                cadastrarAcao();
                break;
            case 2:
                listarAcoes();
                break;
            case 3:
                buscarAcao();
                break;
            case 4:
                validarQuantidadeAcao();
                break;
            case 5:
                calcularCreditosAcao();
                break;
            default:
                view.mostrarErro("Opção inválida!");
        }
    }

    private void cadastrarAcao() {
        try {
            Long userId = view.lerIdUsuario();
            User user = userService.buscarPorId(userId);

            if (user == null) {
                view.mostrarErro("Usuário não encontrado.");
                return;
            }

            int opcaoTipo = view.mostrarMenuTipoAcao();
            TipoAcao tipoAcao;
            try {
                tipoAcao = TipoAcao.fromInt(opcaoTipo);
            } catch (IllegalArgumentException e) {
                view.mostrarErro("Opção inválida para tipo de ação.");
                return;
            }

            String tipo;
            if (tipoAcao == TipoAcao.OUTRO) {
                tipo = view.lerString("Digite o tipo da ação sustentável: ");
            } else {
                tipo = tipoAcao.getTipo();
            }

            double quantidade = view.lerDouble("Quantidade: ");
            String descricao = view.lerString("Descrição: ");

            AcaoSustentavel acao = new AcaoSustentavel();
            acao.setUser(user);
            acao.setTipo(tipo);
            acao.setQuantidade(quantidade);
            acao.setDescricao(descricao);
            acao.setData(LocalDate.now());

            if (acaoService.validarQuantidade(acao)) {
                double creditos = acaoService.calcularCreditos(acao);
                acao.setCreditosGerados(creditos);
                acaoService.inserir(acao);

                CreditoCarbono credito = creditoService.buscarPorUsuario(userId);
                if (credito == null) {
                    credito = new CreditoCarbono();
                    credito.setUser(user);
                    credito.setTotalCreditos(creditos);
                    credito.setUltimaAtualizacao(LocalDate.now());
                    creditoService.inserir(credito);
                } else {
                    double novosCreditos = credito.getTotalCreditos() + creditos;
                    credito.setTotalCreditos(novosCreditos);
                    credito.setUltimaAtualizacao(LocalDate.now());
                    creditoService.atualizar(credito);
                }

                view.mostrarSucesso("Ação cadastrada com sucesso! Créditos gerados: " + creditos);
                view.mostrarInfo("ID da ação criada: " + acao.getId());
            } else {
                view.mostrarErro("Quantidade inválida para o tipo de ação.");
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao cadastrar ação: " + e.getMessage());
        }
    }

    private void listarAcoes() {
        try {
            List<AcaoSustentavel> acoes = acaoService.buscarTodos();
            view.mostrarAcoes(acoes);
        } catch (Exception e) {
            view.mostrarErro("Erro ao listar ações: " + e.getMessage());
        }
    }

    private void buscarAcao() {
        try {
            Long id = view.lerIdAcao();
            AcaoSustentavel acao = acaoService.buscarPorId(id);
            if (acao != null) {
                view.mostrarInfo(acao.toString());
            } else {
                view.mostrarErro("Ação não encontrada.");
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao buscar ação: " + e.getMessage());
        }
    }

    private void validarQuantidadeAcao() {
        try {
            String tipo = view.lerString("Tipo da ação: ");
            double quantidade = view.lerDouble("Quantidade: ");
            
            AcaoSustentavel acao = new AcaoSustentavel();
            acao.setTipo(tipo);
            acao.setQuantidade(quantidade);
            
            if (acaoService.validarQuantidade(acao)) {
                view.mostrarSucesso("Quantidade válida");
            } else {
                view.mostrarErro("Quantidade inválida (abaixo do mínimo)");
            }
        } catch (Exception e) {
            view.mostrarErro("Erro ao validar quantidade: " + e.getMessage());
        }
    }

    private void calcularCreditosAcao() {
        try {
            String tipo = view.lerString("Tipo da ação: ");
            double quantidade = view.lerDouble("Quantidade: ");
            
            AcaoSustentavel acao = new AcaoSustentavel();
            acao.setTipo(tipo);
            acao.setQuantidade(quantidade);
            
            double creditos = acaoService.calcularCreditos(acao);
            view.mostrarInfo("Créditos que seriam gerados: " + creditos);
        } catch (Exception e) {
            view.mostrarErro("Erro ao calcular créditos: " + e.getMessage());
        }
    }
}
