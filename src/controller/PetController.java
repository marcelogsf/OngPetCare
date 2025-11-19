package controller;

import model.Pet;
import service.PetService;
import enums.Tamanho;
import enums.StatusSaude;
import enums.StatusAdocao;

import java.util.List;
import java.util.Scanner;

public class PetController {

    private PetService service = new PetService();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;

        do {
            System.out.println("\n=== MENU PET ===");
            System.out.println("1 - Cadastrar Pet");
            System.out.println("2 - Listar Pets");
            System.out.println("3 - Atualizar Pet");
            System.out.println("4 - Remover Pet");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

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
        Pet pet = new Pet();

        System.out.print("ID: ");
        pet.setId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Nome: ");
        pet.setNome(scanner.nextLine());

        System.out.print("Idade: ");
        pet.setIdade(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Raça: ");
        pet.setRaca(scanner.nextLine());

        System.out.print("Cor: ");
        pet.setCor(scanner.nextLine());

        // ENUMS:
        try {
            System.out.print("Tamanho (PEQUENO, MEDIO, GRANDE): ");
            pet.setTamanho(Tamanho.valueOf(scanner.nextLine().trim().toUpperCase()));

            System.out.print("Status de Saúde (SAUDAVEL, DOENTE, EM_TRATAMENTO): ");
            pet.setStatusSaude(StatusSaude.valueOf(scanner.nextLine().trim().toUpperCase()));

            System.out.print("Status de Adoção (DISPONIVEL, ADOTADO, EM_PROCESSO): ");
            pet.setStatusAdocao(StatusAdocao.valueOf(scanner.nextLine().trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("Valor de enum inválido. Cadastro cancelado. Use exatamente os valores mostrados.");
            return;
        }

        service.cadastrar(pet);
        System.out.println("Pet cadastrado com sucesso!");
    }

    private void listar() {
        List<Pet> pets = service.listar();
        if (pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE PETS ===");
        for (Pet p : pets) {
            System.out.println("ID: " + p.getId() +
                    " | Nome: " + p.getNome() +
                    " | Idade: " + p.getIdade() +
                    " | Raça: " + p.getRaca() +
                    " | Cor: " + p.getCor() +
                    " | Tamanho: " + p.getTamanho() +
                    " | Saúde: " + p.getStatusSaude() +
                    " | Adoção: " + p.getStatusAdocao());
        }
    }

    private void atualizar() {
        System.out.print("ID do pet para atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Pet pet = service.buscarPorId(id);

        if (pet == null) {
            System.out.println("Pet não encontrado!");
            return;
        }

        System.out.print("Novo nome: ");
        pet.setNome(scanner.nextLine());

        System.out.print("Nova idade: ");
        pet.setIdade(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Nova raça: ");
        pet.setRaca(scanner.nextLine());

        System.out.print("Nova cor: ");
        pet.setCor(scanner.nextLine());

        service.atualizar(pet);
        System.out.println("Pet atualizado!");
    }

    private void remover() {
        System.out.print("ID do pet para remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (service.remover(id)) {
            System.out.println("Pet removido com sucesso!");
        } else {
            System.out.println("Pet não encontrado!");
        }
    }
}
