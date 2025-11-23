package controller;

import model.Funcionario;
import service.FuncionarioService;
import enums.FuncaoFuncionario;

import java.util.List;
import java.util.Scanner;

public class FuncionarioController {

    private FuncionarioService service = new FuncionarioService();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;

        do {
            System.out.println("\n=== MENU FUNCIONÁRIO ===");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("3 - Atualizar Funcionário");
            System.out.println("4 - Remover Funcionário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> remover();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrar() {
        Funcionario f = new Funcionario();

        System.out.print("ID: ");
        f.setId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Nome: ");
        f.setNome(scanner.nextLine());

        System.out.print("Idade: ");
        f.setIdade(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Função (RESGATADOR, VETERINARIO, ADMINISTRATIVO): ");
        f.setFuncao(FuncaoFuncionario.valueOf(scanner.nextLine().trim().toUpperCase()));

        service.cadastrar(f);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void listar() {
        List<Funcionario> funcionarios = service.listar();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===\n");

        for (Funcionario f : funcionarios) {
            System.out.println(f);  // usa toString bonito
            System.out.println();   // separação visual
        }
    }


    private void atualizar() {
        System.out.print("ID do funcionário para atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Funcionario f = service.buscarPorId(id);

        if (f == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        System.out.print("Novo nome: ");
        f.setNome(scanner.nextLine());

        System.out.print("Nova idade: ");
        f.setIdade(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Nova função (RESGATADOR, VETERINARIO, ADMINISTRATIVO): ");
        f.setFuncao(FuncaoFuncionario.valueOf(scanner.nextLine().trim().toUpperCase()));

        service.atualizar(f);
        System.out.println("Funcionário atualizado!");
    }

    private void remover() {
        System.out.print("ID do funcionário para remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (service.remover(id)) {
            System.out.println("Funcionário removido com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado!");
        }
    }
}

