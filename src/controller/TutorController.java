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
            System.out.println("\n=== MENU TUTOR ===");
            System.out.println("1 - Cadastrar Tutor");
            System.out.println("2 - Listar Tutores");
            System.out.println("3 - Atualizar Tutor");
            System.out.println("4 - Remover Tutor");
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
        Tutor tutor = new Tutor();

        System.out.print("ID: ");
        tutor.setId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Nome: ");
        tutor.setNome(scanner.nextLine());

        System.out.print("Idade: ");
        tutor.setIdade(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Telefone: ");
        tutor.setTelefone(scanner.nextLine());

        System.out.print("Email: ");
        tutor.setEmail(scanner.nextLine());

        service.cadastrar(tutor);
        System.out.println("Tutor cadastrado com sucesso!");
    }

    private void listar() {
        List<Tutor> tutores = service.listar();
        if (tutores.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE TUTORES ===");
        for (Tutor t : tutores) {
            System.out.println("ID: " + t.getId() +
                    " | Nome: " + t.getNome() +
                    " | Idade: " + t.getIdade() +
                    " | Telefone: " + t.getTelefone() +
                    " | Email: " + t.getEmail());
        }
    }

    private void atualizar() {
        System.out.print("ID do tutor para atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Tutor tutor = service.buscarPorId(id);

        if (tutor == null) {
            System.out.println("Tutor não encontrado!");
            return;
        }

        System.out.print("Novo nome: ");
        tutor.setNome(scanner.nextLine());

        System.out.print("Nova idade: ");
        tutor.setIdade(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Novo telefone: ");
        tutor.setTelefone(scanner.nextLine());

        System.out.print("Novo email: ");
        tutor.setEmail(scanner.nextLine());

        service.atualizar(tutor);
        System.out.println("Tutor atualizado!");
    }

    private void remover() {
        System.out.print("ID do tutor para remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (service.remover(id)) {
            System.out.println("Tutor removido com sucesso!");
        } else {
            System.out.println("Tutor não encontrado!");
        }
    }
}
