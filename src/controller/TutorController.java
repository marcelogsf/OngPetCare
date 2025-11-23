package controller;

import model.Tutor;
import service.TutorService;

import java.util.List;
import java.util.Scanner;

public class TutorController {

    private TutorService service = new TutorService();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n=== MENU TUTORES ===");
            System.out.println("1 - Cadastrar tutor");
            System.out.println("2 - Listar tutores");
            System.out.println("3 - Buscar tutor por ID");
            System.out.println("4 - Atualizar tutor");
            System.out.println("5 - Remover tutor");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            String line = scanner.nextLine();
            if (line.isEmpty()) { opcao = -1; }
            else { opcao = Integer.parseInt(line); }

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscarPorId();
                case 4 -> atualizar();
                case 5 -> remover();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        Tutor t = new Tutor();
        System.out.print("ID: ");
        t.setId(Integer.parseInt(scanner.nextLine()));
        System.out.print("Nome: ");
        t.setNome(scanner.nextLine());
        System.out.print("CPF: ");
        t.setCpf(scanner.nextLine());
        System.out.print("Idade: ");
        t.setIdade(Integer.parseInt(scanner.nextLine()));
        System.out.print("Email: ");
        t.setEmail(scanner.nextLine());
        System.out.print("Telefone: ");
        t.setTelefone(scanner.nextLine());
        System.out.print("Endereço: ");
        t.setEndereco(scanner.nextLine());

        System.out.print("Possui restrição para adoção? (s/n): ");
        String resp = scanner.nextLine().trim().toLowerCase();
        if (resp.equals("s") || resp.equals("sim")) {
            t.setRestricaoAdocao(true);
            System.out.print("Descreva o tipo de restrição: ");
            t.setTipoRestricao(scanner.nextLine());
        } else {
            t.setRestricaoAdocao(false);
            t.setTipoRestricao("");
        }

        service.cadastrar(t);
        System.out.println("Tutor cadastrado com sucesso.");
    }

    private void listar() {
        List<Tutor> tutores = service.listar();

        if (tutores.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE TUTORES ===\n");

        for (Tutor t : tutores) {
            System.out.println(t);  // usa toString formatado
            System.out.println();   // linha extra para separar
        }
    }


    private void buscarPorId() {
        System.out.print("ID do tutor: ");
        int id = Integer.parseInt(scanner.nextLine());
        Tutor t = service.buscarPorId(id);
        if (t == null) System.out.println("Tutor não encontrado.");
        else System.out.println(t);
    }

    private void atualizar() {
        System.out.print("ID do tutor para atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Tutor t = service.buscarPorId(id);
        if (t == null) {
            System.out.println("Tutor não encontrado.");
            return;
        }

        System.out.print("Novo nome (enter para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) t.setNome(nome);

        System.out.print("Novo CPF (enter para manter): ");
        String cpf = scanner.nextLine();
        if (!cpf.isBlank()) t.setCpf(cpf);

        System.out.print("Nova idade (enter para manter): ");
        String idade = scanner.nextLine();
        if (!idade.isBlank()) t.setIdade(Integer.parseInt(idade));

        System.out.print("Novo email (enter para manter): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) t.setEmail(email);

        System.out.print("Novo telefone (enter para manter): ");
        String tel = scanner.nextLine();
        if (!tel.isBlank()) t.setTelefone(tel);

        System.out.print("Novo endereço (enter para manter): ");
        String end = scanner.nextLine();
        if (!end.isBlank()) t.setEndereco(end);

        System.out.print("Possui restrição para adoção? (s/n, enter para manter): ");
        String resp = scanner.nextLine().trim().toLowerCase();
        if (resp.equals("s") || resp.equals("sim")) {
            t.setRestricaoAdocao(true);
            System.out.print("Descreva o tipo de restrição: ");
            t.setTipoRestricao(scanner.nextLine());
        } else if (resp.equals("n") || resp.equals("nao") || resp.equals("não")) {
            t.setRestricaoAdocao(false);
            t.setTipoRestricao("");
        }

        service.atualizar(t);
        System.out.println("Tutor atualizado!");
    }

    private void remover() {
        System.out.print("ID do tutor para remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (service.remover(id)) {
            System.out.println("Tutor removido com sucesso!");
        } else {
            System.out.println("Tutor não encontrado!");
        }
    }
}
