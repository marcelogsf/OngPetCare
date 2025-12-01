package controller;

import model.Tutor;
import model.Pet;
import enums.Tamanho;
import service.TutorService;
import service.SugestaoService;

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
            System.out.println("6 - Adotar Pet");
            System.out.println("7 - Ver dados do Tutor");
            System.out.println("8 - Sugerir Pets Ideais"); // NOVO
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
                case 6 -> System.out.println("A adoção é feita no menu PETS!");
                case 7 -> buscarPorId();
                case 8 -> sugerirPetsMenu(); // NOVO
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    // ======================================================
    // ====================== CADASTRAR ======================
    // ======================================================

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

        System.out.print("Possui restrição de adoção? (S/N): ");
        String r = scanner.nextLine().trim().toUpperCase();
        if (r.equals("S")) {
            t.setRestricaoAdocao(true);
            System.out.print("Tipo da restrição: ");
            t.setTipoRestricao(scanner.nextLine());
        } else {
            t.setRestricaoAdocao(false);
        }

        // ======================================================
        // PREFERÊNCIAS DO ADOTANTE (SPRINT 3)
        // ======================================================

        System.out.print("Tipo de residência (CASA, APARTAMENTO, CHACARA): ");
        t.setTipoResidencia(scanner.nextLine().trim().toUpperCase());

        System.out.print("Tempo disponível por dia (horas): ");
        t.setTempoDisponivel(Integer.parseInt(scanner.nextLine()));

        System.out.print("Porte desejado (PEQUENO, MEDIO, GRANDE): ");
        try {
            t.setPorteDesejado(Tamanho.valueOf(scanner.nextLine().trim().toUpperCase()));
        } catch (Exception e) {
            System.out.println("Porte inválido, usando MEDIO.");
            t.setPorteDesejado(Tamanho.MEDIO);
        }

        service.cadastrar(t);
        System.out.println("Tutor cadastrado com sucesso!");
    }

    // ======================================================
    private void listar() {
        List<Tutor> lista = service.listar();

        if (lista.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE TUTORES ===");
        lista.forEach(System.out::println);
    }

    private void buscarPorId() {
        System.out.print("ID do tutor: ");
        int id = Integer.parseInt(scanner.nextLine());

        Tutor t = service.buscarPorId(id);

        if (t == null) System.out.println("Tutor não encontrado.");
        else System.out.println(t);
    }

    // ======================================================
    private void atualizar() {
        System.out.print("ID do tutor para atualizar: ");
        Tutor t = service.buscarPorId(Integer.parseInt(scanner.nextLine()));

        if (t == null) {
            System.out.println("Tutor não encontrado!");
            return;
        }

        System.out.print("Novo nome (enter = mantém): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) t.setNome(nome);

        System.out.print("Nova idade (enter = mantém): ");
        String idade = scanner.nextLine();
        if (!idade.isBlank()) t.setIdade(Integer.parseInt(idade));

        System.out.print("Novo email (enter = mantém): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) t.setEmail(email);

        System.out.print("Novo telefone (enter = mantém): ");
        String tel = scanner.nextLine();
        if (!tel.isBlank()) t.setTelefone(tel);

        System.out.print("Novo endereço (enter = mantém): ");
        String end = scanner.nextLine();
        if (!end.isBlank()) t.setEndereco(end);

        // Preferências
        System.out.print("Novo tipo residência (enter = mantém): ");
        String res = scanner.nextLine().toUpperCase();
        if (!res.isBlank()) t.setTipoResidencia(res);

        System.out.print("Novo tempo disponível (enter = mantém): ");
        String tempo = scanner.nextLine();
        if (!tempo.isBlank()) t.setTempoDisponivel(Integer.parseInt(tempo));

        System.out.print("Novo porte desejado (enter = mantém): ");
        String porte = scanner.nextLine().trim().toUpperCase();
        if (!porte.isBlank()) {
            try { t.setPorteDesejado(Tamanho.valueOf(porte)); }
            catch (Exception ignored) {}
        }

        service.atualizar(t);
        System.out.println("Tutor atualizado!");
    }

    private void remover() {
        System.out.print("ID do tutor para remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (service.remover(id)) System.out.println("Tutor removido.");
        else System.out.println("Tutor não encontrado.");
    }

    // ======================================================
    // =============== SUGESTÃO DE PETS (SPRINT 3) ===========
    // ======================================================

    private void sugerirPetsMenu() {
        System.out.print("ID do tutor: ");
        int tutorId = Integer.parseInt(scanner.nextLine());

        Tutor tutor = service.buscarPorId(tutorId);
        if (tutor == null) {
            System.out.println("Tutor não encontrado.");
            return;
        }

        System.out.println("\n=== GERANDO SUGESTÕES... ===");

        SugestaoService sugestaoService = new SugestaoService();
        List<Pet> sugestoes = sugestaoService.sugerirPetsParaTutor(tutor);

        if (sugestoes.isEmpty()) {
            System.out.println("Nenhum pet ideal encontrado para este tutor.");
            return;
        }

        System.out.println("\n=== PETS RECOMENDADOS ===");
        for (Pet p : sugestoes) {
            System.out.println(p);
        }
    }
}
