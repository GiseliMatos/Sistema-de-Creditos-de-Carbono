package view;

import model.User;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== GERENCIAR USUÁRIOS ===");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Listar Usuários");
        System.out.println("3. Buscar Usuário por ID");
        System.out.println("4. Atualizar Usuário");
        System.out.println("5. Remover Usuário");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public User lerDadosUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF/CNPJ: ");
        String cpfCnpj = scanner.nextLine();
        System.out.print("Tipo (PF/PJ): ");
        String tipo = scanner.nextLine();

        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setCpfCnpj(cpfCnpj);
        user.setTipo(tipo);
        return user;
    }

    public Long lerIdUsuario() {
        System.out.print("ID do usuário: ");
        return Long.parseLong(scanner.nextLine());
    }

    public void mostrarUsuarios(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            users.forEach(System.out::println);
        }
    }

    public void mostrarUsuario(User user) {
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public String lerNovoNome(String nomeAtual) {
        System.out.print("Novo nome (atual: " + nomeAtual + "): ");
        return scanner.nextLine();
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
