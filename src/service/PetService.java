package service;

import model.Pet;
import repository.PetRepository;

import java.util.List;

public class PetService {

    private PetRepository repository = new PetRepository();

    public void cadastrar(Pet pet) {
        repository.salvar(pet);
    }

    public List<Pet> listar() {
        return repository.listar();
    }

    public Pet buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public void atualizar(Pet pet) {
        repository.atualizar(pet);
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }
}
