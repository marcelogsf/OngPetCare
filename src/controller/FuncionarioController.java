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

            Integer entrada = lerIntOpcional();
            opcao = (entrada == null) ? -1 : entrada;

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
        f.setId(lerIntObrigatorio());
        System.out.print("Nome: ");
        f.setNome(scanner.nextLine());
        System.out.print("Idade: ");
        f.setIdade(lerIntObrigatorio());
        System.out.print("CPF: ");
        f.setCpf(scanner.nextLine());
        System.out.print("Função (RESGATADOR, VETERINARIO, ADMINISTRATIVO): ");
        try { f.setFuncao(FuncaoFuncionario.valueOf(scanner.nextLine().trim().toUpperCase())); }
        catch (Exception e) { System.out.println("Função inválida. Usando ADMINISTRATIVO por padrão."); f.setFuncao(FuncaoFuncionario.ADMINISTRATIVO); }
        service.cadastrar(f);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void listar() {
        List<Funcionario> funcionarios = service.listar();
        if (funcionarios.isEmpty()) { System.out.println("Nenhum funcionário cadastrado."); return; }
        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===\n");
        funcionarios.forEach(f -> { System.out.println(f); System.out.println(); });
    }

    private void atualizar() {
        System.out.print("ID do funcionário para atualizar: ");
        Integer id = lerIntOpcional();
        if (id == null) { System.out.println("ID inválido."); return; }
        Funcionario f = service.buscarPorId(id);
        if (f == null) { System.out.println("Funcionário não encontrado!"); return; }

        System.out.print("Novo nome (enter = mantém): ");
        String nome = scanner.nextLine(); if (!nome.isBlank()) f.setNome(nome);

        System.out.print("Nova idade (enter = mantém): ");
        Integer idade = lerIntOpcional(); if (idade != null) f.setIdade(idade);

        System.out.print("Novo CPF (enter = mantém): ");
        String cpf = scanner.nextLine(); if (!cpf.isBlank()) f.setCpf(cpf);

        System.out.print("Nova função (enter = mantém): ");
        String func = scanner.nextLine().trim().toUpperCase();
        if (!func.isBlank()) {
            try { f.setFuncao(FuncaoFuncionario.valueOf(func)); }
            catch (Exception ignored) { System.out.println("Função inválida. Mantendo a anterior."); }
        }

        service.atualizar(f);
        System.out.println("Funcionário atualizado!");
    }

    private void remover() {
        System.out.print("ID do funcionário para remover: ");
        Integer id = lerIntOpcional();
        if (id == null) { System.out.println("ID inválido."); return; }
        if (service.remover(id)) System.out.println("Funcionário removido com sucesso!");
        else System.out.println("Funcionário não encontrado!");
    }

    private int lerIntObrigatorio() {
        while (true) {
            String line = scanner.nextLine();
            try { return Integer.parseInt(line.trim()); }
            catch (Exception e) { System.out.print("Entrada inválida. Digite um número: "); }
        }
    }

    private Integer lerIntOpcional() {
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) return null;
            try { return Integer.parseInt(line.trim()); }
            catch (Exception e) { System.out.print("Entrada inválida. Digite um número (ou enter para cancelar): "); }
        }
    }
}
