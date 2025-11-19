package main;

import controller.PetController;
import controller.TutorController;
import controller.FuncionarioController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PetController petController = new PetController();
        TutorController tutorController = new TutorController();
        FuncionarioController funcionarioController = new FuncionarioController();

        int opcao;

        do {
            System.out.println("\n=== SISTEMA ONG BEM-ESTAR ANIMAL ===");
            System.out.println("1 - Gerenciar Pets");
            System.out.println("2 - Gerenciar Tutores");
            System.out.println("3 - Gerenciar Funcionários");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> petController.menu();
                case 2 -> tutorController.menu();
                case 3 -> funcionarioController.menu();
                case 0 -> System.out.println("Saindo do sistema. Até mais!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
