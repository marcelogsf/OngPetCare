package repository;

import model.Pet;
import java.util.ArrayList;
import java.util.List;

public class PetRepository {

    // Simula um banco de dados usando uma lista
    private List<Pet> pets = new ArrayList<>();

    // CREATE
    public void salvar(Pet pet) {
        pets.add(pet);
    }

    // READ (listar todos)
    public List<Pet> listar() {
        return pets;
    }

    // READ (buscar por ID)
    public Pet buscarPorId(int id) {
        for (Pet p : pets) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null; // caso não encontre
    }

    // UPDATE
    public void atualizar(Pet petAtualizado) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == petAtualizado.getId()) {
                pets.set(i, petAtualizado);
                return;
            }
        }
    }

    // DELETE
    public boolean remover(int id) {
        return pets.removeIf(p -> p.getId() == id);
    }
}

