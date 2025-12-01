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

            System.out.println("\n=== BENEFÍCIOS ===");
            System.out.println("6 - Registrar Consulta");
            System.out.println("7 - Registrar Vacina");
            System.out.println("8 - Registrar Castração");
            System.out.println("9 - Ver Histórico do Pet");

            System.out.println("\n=== ADOÇÃO ===");
            System.out.println("10 - Adotar Pet");

            System.out.println("\n=== FILTROS ===");
            System.out.println("11 - Filtrar por Energia");
            System.out.println("12 - Filtrar por Idade Ideal do Adotante");
            System.out.println("13 - Filtrar por Tamanho");

            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            String line = scanner.nextLine();
            opcao = line.isEmpty() ? -1 : Integer.parseInt(line);

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
                case 11 -> filtrarPorEnergiaMenu();
                case 12 -> filtrarPorIdadeIdealMenu();
                case 13 -> filtrarPorTamanhoMenu();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // ============================================================
    // ====================== CADASTRAR ============================
    // ============================================================
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
        try {
            pet.setTamanho(Tamanho.valueOf(scanner.nextLine().trim().toUpperCase()));
        } catch (Exception e) {
            System.out.println("Tamanho inválido. Usando MEDIO por padrão.");
            pet.setTamanho(Tamanho.MEDIO);
        }

        // Status de saúde
        System.out.print("Status de saúde (SAUDAVEL, DOENTE, EM_TRATAMENTO): ");
        try {
            pet.setStatusSaude(StatusSaude.valueOf(scanner.nextLine().trim().toUpperCase()));
        } catch (Exception e) {
            System.out.println("Status inválido. Usando SAUDAVEL por padrão.");
            pet.setStatusSaude(StatusSaude.SAUDAVEL);
        }

        // Energia
        System.out.print("Energia (BAIXA, MEDIA, ALTA): ");
        pet.setEnergia(scanner.nextLine().trim().toUpperCase());

        // Idade Ideal
        System.out.print("Idade ideal do adotante: ");
        pet.setIdadeIdeal(Integer.parseInt(scanner.nextLine()));

        service.cadastrar(pet);
        System.out.println("Pet cadastrado!");
    }

    // ============================================================
    private void listar() {
        List<Pet> pets = service.listar();

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE PETS ===");
        for (Pet p : pets) {
            System.out.println(p);
        }
    }

    private void buscarPorId() {
        System.out.print("ID do pet: ");
        Pet p = service.buscarPorId(Integer.parseInt(scanner.nextLine()));

        if (p == null) System.out.println("Pet não encontrado.");
        else System.out.println(p);
    }

    // ============================================================
    private void atualizar() {
        System.out.print("ID do pet para atualizar: ");
        Pet p = service.buscarPorId(Integer.parseInt(scanner.nextLine()));

        if (p == null) {
            System.out.println("Pet não encontrado.");
            return;
        }

        System.out.print("Novo nome (enter = mantém): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) p.setNome(nome);

        System.out.print("Nova idade (enter = mantém): ");
        String idade = scanner.nextLine();
        if (!idade.isBlank()) p.setIdade(Integer.parseInt(idade));

        System.out.print("Novo tamanho (enter = mantém): ");
        String tam = scanner.nextLine().toUpperCase();
        if (!tam.isBlank()) {
            try { p.setTamanho(Tamanho.valueOf(tam)); }
            catch (Exception ignored) {}
        }

        System.out.print("Novo status de saúde (enter = mantém): ");
        String st = scanner.nextLine().toUpperCase();
        if (!st.isBlank()) {
            try { p.setStatusSaude(StatusSaude.valueOf(st)); }
            catch (Exception ignored) {}
        }

        System.out.print("Nova energia (enter = mantém): ");
        String energia = scanner.nextLine().toUpperCase();
        if (!energia.isBlank()) p.setEnergia(energia);

        System.out.print("Nova idade ideal do adotante (enter = mantém): ");
        String idadeIdeal = scanner.nextLine();
        if (!idadeIdeal.isBlank()) p.setIdadeIdeal(Integer.parseInt(idadeIdeal));

        service.atualizar(p);
        System.out.println("Pet atualizado!");
    }

    // ============================================================
    private void remover() {
        System.out.print("ID do pet para remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (service.remover(id)) System.out.println("Pet removido.");
        else System.out.println("Pet não encontrado.");
    }

    // ============================================================
    // ================= BENEFÍCIOS ===============================
    // ============================================================

    private void registrarConsultaMenu() {
        System.out.print("ID do pet: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Data: ");
        String data = scanner.nextLine();
        System.out.print("Descrição: ");
        String desc = scanner.nextLine();
        System.out.print("Veterinário: ");
        String vet = scanner.nextLine();

        Consulta c = new Consulta(data, desc, vet);
        System.out.println(service.registrarConsulta(id, c));
    }

    private void registrarVacinaMenu() {
        System.out.print("ID do pet: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Tipo (RAIVA, MULTIPLA, GRIPE, OUTRA): ");
        TipoVacina tipo;
        try { tipo = TipoVacina.valueOf(scanner.nextLine().toUpperCase()); }
        catch (Exception e) {
            System.out.println("Tipo inválido.");
            return;
        }

        System.out.print("Data: ");
        String data = scanner.nextLine();

        Vacina v = new Vacina(tipo, data);
        System.out.println(service.registrarVacina(id, v));
    }

    private void registrarCastracaoMenu() {
        System.out.print("ID do pet: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Data: ");
        String data = scanner.nextLine();
        System.out.print("Veterinário: ");
        String vet = scanner.nextLine();
        System.out.print("Observações: ");
        String obs = scanner.nextLine();

        Castracao c = new Castracao(data, vet, obs);
        System.out.println(service.registrarCastracao(id, c));
    }

    private void verHistoricoMenu() {
        System.out.print("ID do pet: ");
        Pet pet = service.buscarPorId(Integer.parseInt(scanner.nextLine()));

        if (pet == null) {
            System.out.println("Pet não encontrado.");
            return;
        }

        pet.mostrarHistorico();
    }

    private void adotarPetMenu() {
        System.out.print("ID do pet: ");
        int petId = Integer.parseInt(scanner.nextLine());

        System.out.print("ID do tutor: ");
        int tutorId = Integer.parseInt(scanner.nextLine());

        System.out.println(service.adotarPet(petId, tutorId));
    }

    // ============================================================
    // ================= FILTROS =================================
    // ============================================================

    private void filtrarPorEnergiaMenu() {
        System.out.print("Energia (BAIXA, MEDIA, ALTA): ");
        String energia = scanner.nextLine().toUpperCase();

        List<Pet> pets = service.filtrarPorEnergia(energia);

        System.out.println("\n=== RESULTADOS ===");
        pets.forEach(System.out::println);
    }

    private void filtrarPorIdadeIdealMenu() {
        System.out.print("Idade do adotante: ");
        int idade = Integer.parseInt(scanner.nextLine());

        List<Pet> pets = service.filtrarPorIdadeIdeal(idade);

        System.out.println("\n=== RESULTADOS ===");
        pets.forEach(System.out::println);
    }

    private void filtrarPorTamanhoMenu() {
        System.out.print("Tamanho (PEQUENO, MEDIO, GRANDE): ");
        String tam = scanner.nextLine().toUpperCase();

        List<Pet> pets = service.filtrarPorTamanho(tam);

        System.out.println("\n=== RESULTADOS ===");
        pets.forEach(System.out::println);
    }
}
