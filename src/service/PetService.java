package service;

import model.Pet;
import model.Consulta;
import model.Vacina;
import model.Castracao;
import model.Tutor;
import repository.PetRepository;
import repository.TutorRepository;
import enums.StatusAdocao;
import enums.StatusSaude;

import java.util.List;

public class PetService {

    private PetRepository repository = new PetRepository();
    private TutorRepository tutorRepository = new TutorRepository();

    // CRUD existentes
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

    // ---------- Novos métodos do Sprint 2 ----------

    public String adotarPet(int petId, int tutorId) {
        Pet pet = repository.buscarPorId(petId);
        if (pet == null) return "Pet não encontrado.";

        Tutor tutor = tutorRepository.buscarPorId(tutorId);
        if (tutor == null) return "Tutor não encontrado.";

        // validações
        if (pet.getStatusSaude() == StatusSaude.DOENTE) {
            return "Pet não pode ser adotado: está doente.";
        }
        if (pet.getStatusAdocao() == StatusAdocao.ADOTADO) {
            return "Pet já está adotado.";
        }
        if (tutor.isRestricaoAdocao()) {
            return "Tutor possui restrição para adoção: " + tutor.getTipoRestricao();
        }

        // aplicar adoção
        pet.setTutor(tutor);
        pet.setStatusAdocao(StatusAdocao.ADOTADO);
        repository.atualizar(pet);

        return "Sucesso: pet " + pet.getNome() + " adotado por " + tutor.getNome();
    }

    public String registrarConsulta(int petId, Consulta consulta) {
        Pet pet = repository.buscarPorId(petId);
        if (pet == null) return "Pet não encontrado.";
        if (consulta == null) return "Consulta inválida.";

        pet.registrarConsulta(consulta);
        repository.atualizar(pet);
        return "Consulta registrada com sucesso.";
    }

    public String registrarVacina(int petId, Vacina vacina) {
        Pet pet = repository.buscarPorId(petId);
        if (pet == null) return "Pet não encontrado.";
        if (vacina == null) return "Vacina inválida.";

        pet.registrarVacina(vacina);
        repository.atualizar(pet);
        return "Vacina registrada com sucesso.";
    }

    public String registrarCastracao(int petId, Castracao castracao) {
        Pet pet = repository.buscarPorId(petId);
        if (pet == null) return "Pet não encontrado.";
        if (castracao == null) return "Castração inválida.";

        pet.registrarCastracao(castracao);
        repository.atualizar(pet);
        return "Castração registrada com sucesso.";
    }
}
