package service;

import model.Pet;
import model.Tutor;
import enums.Tamanho;

import java.util.List;
import java.util.stream.Collectors;

public class SugestaoService {

    private PetService petService = new PetService();

    /**
     * Sugere pets com base nas preferências do tutor.
     */
    public List<Pet> sugerirPetsParaTutor(Tutor tutor) {

        return petService.listar().stream()
                .filter(p -> p.getStatusAdocao().name().equals("DISPONIVEL")) // só pets disponíveis
                .filter(p -> filtroPorte(p, tutor))                         // porte ideal
                .filter(p -> filtroEnergia(p, tutor))                       // energia ideal
                .filter(p -> filtroIdadeIdeal(p, tutor))                    // idade ideal
                .collect(Collectors.toList());
    }

    // ---------------------- Filtros individuais -----------------------

    private boolean filtroPorte(Pet pet, Tutor tutor) {
        if (tutor.getPorteDesejado() == null) return true;
        return pet.getTamanho() == tutor.getPorteDesejado();
    }

    private boolean filtroEnergia(Pet pet, Tutor tutor) {
        if (tutor.getTempoDisponivel() == 0) return true;

        // regra simples:
        // pouca disponibilidade → pet de baixa energia
        // média disponibilidade → energia média
        // muita disponibilidade → qualquer energia

        if (tutor.getTempoDisponivel() <= 1) {
            return pet.getEnergia().equalsIgnoreCase("BAIXA");
        }
        if (tutor.getTempoDisponivel() <= 3) {
            return pet.getEnergia().equalsIgnoreCase("MEDIA");
        }
        return true; // tempo alto → qualquer energia serve
    }

    private boolean filtroIdadeIdeal(Pet pet, Tutor tutor) {
        return tutor.getIdade() >= pet.getIdadeIdeal();
    }
}
