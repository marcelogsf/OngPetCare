package main;

import controller.PetController;
import controller.TutorController;
import controller.FuncionarioController;
import model.Pet;
import model.Tutor;
import enums.Tamanho;
import enums.StatusSaude;
import service.PetService;
import service.TutorService;
import service.SugestaoService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        PetController petController = new PetController();
        TutorController tutorController = new TutorController();
        FuncionarioController funcionarioController = new FuncionarioController();

        int opcao;

        do {
            System.out.println("\n=== SISTEMA ONG AMOR EM PATAS ===");
            System.out.println("1 - Gerenciar Pets");
            System.out.println("2 - Gerenciar Tutores");
            System.out.println("3 - Gerenciar Funcionários");
            System.out.println("4 - Simulação Final (Sprint 3)"); // NOVO
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String line = scanner.nextLine();
            opcao = line.isEmpty() ? -1 : Integer.parseInt(line);

            switch (opcao) {
                case 1 -> petController.menu();
                case 2 -> tutorController.menu();
                case 3 -> funcionarioController.menu();
                case 4 -> simulacaoFinal(); // NOVO
                case 0 -> System.out.println("Saindo do sistema. Até mais!");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // ============================================================
    // ===================== SIMULAÇÃO FINAL =======================
    // ============================================================

    private static void simulacaoFinal() {

        System.out.println("\n=== SIMULAÇÃO FINAL — SPRINT 3 ===");

        PetService petService = new PetService();
        TutorService tutorService = new TutorService();
        SugestaoService sugestaoService = new SugestaoService();

        // -------------------------------------------
        // Cadastrar Pets automaticamente
        // -------------------------------------------
        Pet p1 = new Pet();
        p1.setId(1);
        p1.setNome("Luna");
        p1.setIdade(2);
        p1.setRaca("Vira-lata");
        p1.setCor("Caramelo");
        p1.setTamanho(Tamanho.PEQUENO);
        p1.setStatusSaude(StatusSaude.SAUDAVEL);
        p1.setEnergia("ALTA");
        p1.setIdadeIdeal(18);
        petService.cadastrar(p1);

        Pet p2 = new Pet();
        p2.setId(2);
        p2.setNome("Thor");
        p2.setIdade(4);
        p2.setRaca("Pastor Alemão");
        p2.setCor("Preto e Marrom");
        p2.setTamanho(Tamanho.GRANDE);
        p2.setStatusSaude(StatusSaude.SAUDAVEL);
        p2.setEnergia("MEDIA");
        p2.setIdadeIdeal(25);
        petService.cadastrar(p2);

        Pet p3 = new Pet();
        p3.setId(3);
        p3.setNome("Mimi");
        p3.setIdade(1);
        p3.setRaca("Gato SRD");
        p3.setCor("Branco");
        p3.setTamanho(Tamanho.PEQUENO);
        p3.setStatusSaude(StatusSaude.SAUDAVEL);
        p3.setEnergia("BAIXA");
        p3.setIdadeIdeal(18);
        petService.cadastrar(p3);

        System.out.println("\n✔ Pets cadastrados automaticamente");

        // -------------------------------------------
        // Cadastrar Tutor automaticamente
        // -------------------------------------------
        Tutor t1 = new Tutor();
        t1.setId(1);
        t1.setNome("Gabriel");
        t1.setCpf("111.111.111-11");
        t1.setIdade(20);
        t1.setEmail("gabriel@email.com");
        t1.setTelefone("99999-9999");
        t1.setEndereco("Rua das Flores, 100");
        t1.setRestricaoAdocao(false);
        t1.setTipoResidencia("APARTAMENTO");
        t1.setTempoDisponivel(4);
        t1.setPorteDesejado(Tamanho.PEQUENO);
        tutorService.cadastrar(t1);

        System.out.println("✔ Tutor cadastrado automaticamente");

        // -------------------------------------------
        // Gerar sugestões
        // -------------------------------------------
        System.out.println("\n=== SUGESTÕES DE PETS PARA O TUTOR ===");
        List<Pet> sugestoes = sugestaoService.sugerirPetsParaTutor(t1);

        if (sugestoes.isEmpty()) {
            System.out.println("Nenhum pet ideal encontrado.");
        } else {
            sugestoes.forEach(System.out::println);
        }

        // -------------------------------------------
        // Adotar um pet automaticamente
        // -------------------------------------------
        System.out.println("\n=== ADOÇÃO AUTOMÁTICA ===");
        String resultado = petService.adotarPet(1, 1); // tutor 1 adota pet 1
        System.out.println(resultado);

        // -------------------------------------------
        // Mostrar pet adotado
        // -------------------------------------------
        System.out.println("\n=== PET APÓS ADOÇÃO ===");
        System.out.println(petService.buscarPorId(1));

        System.out.println("\n=== FIM DA SIMULAÇÃO ===\n");
    }
}
