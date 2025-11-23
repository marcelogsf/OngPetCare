package controller;

import model.Pet;
import model.Consulta;
import model.Vacina;
import model.Castracao;
import enums.TipoVacina;
import enums.Tamanho;
import enums.StatusSaude;
import service.PetService;

import java.util.List;
import java.util.Scanner;

public class PetController {

    private PetService service = new PetService();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n=== MENU PETS ===");
            System.out.println("1 - Cadastrar pet");
            System.out.println("2 - Listar pets");
            System.out.println("3 - Buscar pet por ID");
            System.out.println("4 - Atualizar pet");
            System.out.println("5 - Remover pet");
            System.out.println("6 - Registrar Consulta (benefício)");
            System.out.println("7 - Registrar Vacina (benefício)");
            System.out.println("8 - Registrar Castração (benefício)");
            System.out.println("9 - Ver Histórico do Pet (benefício)");
            System.out.println("10 - Adotar Pet");
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
                case 6 -> registrarConsultaMenu();
                case 7 -> registrarVacinaMenu();
                case 8 -> registrarCastracaoMenu();
                case 9 -> verHistoricoMenu();
                case 10 -> adotarPetMenu();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        Pet pet = new Pet();
        System.out.print("ID: ");
        pet.setId(Integer.parseInt(scanner.nextLine()));
        System.out.print("Nome: ");
        pet.setNome(scanner.nextLine());
        System.out.print("Idade: ");
        pet.setIdade(Integer.parseInt(scanner.nextLine()));
        System.out.print("Raça: ");
        pet.setRaca(scanner.nextLine());
        System.out.print("Cor: ");
        pet.setCor(scanner.nextLine());

        // Tamanho
        System.out.print("Tamanho (PEQUENO, MEDIO, GRANDE): ");
        String tam = scanner.nextLine().trim().toUpperCase();
        try {
            pet.setTamanho(Tamanho.valueOf(tam));
        } catch (Exception e) {
            System.out.println("Tamanho inválido. Usando MEDIO por padrão.");
            pet.setTamanho(Tamanho.MEDIO);
        }

        // Status de saúde
        System.out.print("Status de saúde (SAUDAVEL, DOENTE, EM_TRATAMENTO): ");
        String st = scanner.nextLine().trim().toUpperCase();
        try {
            pet.setStatusSaude(StatusSaude.valueOf(st));
        } catch (Exception e) {
            System.out.println("Status inválido. Usando SAUDAVEL por padrão.");
            pet.setStatusSaude(StatusSaude.SAUDAVEL);
        }

        service.cadastrar(pet);
        System.out.println("Pet cadastrado.");
    }

    private void listar() {
        List<Pet> pets = service.listar();

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE PETS ===\n");

        for (Pet p : pets) {
            System.out.println(p);  // usa o toString formatado
            System.out.println();   // linha em branco entre pets
        }
    }


    private void buscarPorId() {
        System.out.print("ID do pet: ");
        int id = Integer.parseInt(scanner.nextLine());
        Pet p = service.buscarPorId(id);
        if (p == null) System.out.println("Pet não encontrado.");
        else System.out.println(p);
    }

    private void atualizar() {
        System.out.print("ID do pet para atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Pet p = service.buscarPorId(id);
        if (p == null) {
            System.out.println("Pet não encontrado.");
            return;
        }
        System.out.print("Novo nome (enter para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) p.setNome(nome);
        System.out.print("Nova idade (enter para manter): ");
        String idStr = scanner.nextLine();
        if (!idStr.isBlank()) p.setIdade(Integer.parseInt(idStr));

        System.out.print("Novo tamanho (PEQUENO, MEDIO, GRANDE) (enter para manter): ");
        String tam = scanner.nextLine().trim().toUpperCase();
        if (!tam.isBlank()) {
            try { p.setTamanho(Tamanho.valueOf(tam)); }
            catch (Exception e) { System.out.println("Tamanho inválido, mantendo o anterior."); }
        }

        System.out.print("Novo status de saúde (SAUDAVEL, DOENTE, EM_TRATAMENTO) (enter para manter): ");
        String st = scanner.nextLine().trim().toUpperCase();
        if (!st.isBlank()) {
            try { p.setStatusSaude(StatusSaude.valueOf(st)); }
            catch (Exception e) { System.out.println("Status inválido, mantendo o anterior."); }
        }

        service.atualizar(p);
        System.out.println("Pet atualizado!");
    }

    private void remover() {
        System.out.print("ID do pet para remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (service.remover(id)) {
            System.out.println("Pet removido com sucesso!");
        } else {
            System.out.println("Pet não encontrado!");
        }
    }

    // ---------- Menus/ações de Benefícios ----------

    private void registrarConsultaMenu() {
        System.out.print("ID do pet para registrar consulta: ");
        int id = Integer.parseInt(scanner.nextLine());
        Pet pet = service.buscarPorId(id);
        if (pet == null) {
            System.out.println("Pet não encontrado.");
            return;
        }
        System.out.print("Data (ex: 2025-11-22): ");
        String data = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Veterinário: ");
        String vet = scanner.nextLine();

        Consulta c = new Consulta(data, descricao, vet);
        String res = service.registrarConsulta(id, c);
        System.out.println(res);
    }

    private void registrarVacinaMenu() {
        System.out.print("ID do pet para registrar vacina: ");
        int id = Integer.parseInt(scanner.nextLine());
        Pet pet = service.buscarPorId(id);
        if (pet == null) {
            System.out.println("Pet não encontrado.");
            return;
        }

        System.out.print("Tipo (RAIVA, MULTIPLA, GRIPE, OUTRA): ");
        String tipoS = scanner.nextLine().trim().toUpperCase();
        TipoVacina tipo;
        try {
            tipo = TipoVacina.valueOf(tipoS);
        } catch (Exception e) {
            System.out.println("Tipo inválido.");
            return;
        }

        System.out.print("Data (ex: 2025-11-22): ");
        String data = scanner.nextLine();

        Vacina v = new Vacina(tipo, data);
        String res = service.registrarVacina(id, v);
        System.out.println(res);
    }

    private void registrarCastracaoMenu() {
        System.out.print("ID do pet para registrar castração: ");
        int id = Integer.parseInt(scanner.nextLine());
        Pet pet = service.buscarPorId(id);
        if (pet == null) {
            System.out.println("Pet não encontrado.");
            return;
        }

        System.out.print("Data (ex: 2025-11-22): ");
        String data = scanner.nextLine();
        System.out.print("Veterinário: ");
        String vet = scanner.nextLine();
        System.out.print("Observações (opcional): ");
        String obs = scanner.nextLine();

        Castracao c = new Castracao(data, vet, obs);
        String res = service.registrarCastracao(id, c);
        System.out.println(res);
    }

    private void verHistoricoMenu() {
        System.out.print("ID do pet para ver histórico: ");
        int id = Integer.parseInt(scanner.nextLine());
        Pet pet = service.buscarPorId(id);
        if (pet == null) {
            System.out.println("Pet não encontrado.");
            return;
        }
        pet.mostrarHistorico();
    }

    private void adotarPetMenu() {
        System.out.print("ID do pet a ser adotado: ");
        int petId = Integer.parseInt(scanner.nextLine());
        System.out.print("ID do tutor que vai adotar: ");
        int tutorId = Integer.parseInt(scanner.nextLine());

        String res = service.adotarPet(petId, tutorId);
        System.out.println(res);
    }
}
