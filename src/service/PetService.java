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

    // ======================================================
    // =============== CRUD BÁSICO (SPRINT 1) ===============
    // ======================================================

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

    // ======================================================
    // =============== REGRAS DE ADOÇÃO (SPRINT 2 + 3) ======
    // ======================================================

    public String adotarPet(int petId, int tutorId) {

        Pet pet = repository.buscarPorId(petId);
        if (pet == null) return "Pet não encontrado.";

        Tutor tutor = tutorRepository.buscarPorId(tutorId);
        if (tutor == null) return "Tutor não encontrado.";

        // -------- REGRAS DA SPRINT 2 --------

        if (pet.getStatusSaude() == StatusSaude.DOENTE) {
            return "Pet não pode ser adotado: está doente.";
        }

        if (pet.getStatusAdocao() == StatusAdocao.ADOTADO) {
            return "Este pet já foi adotado.";
        }

        if (tutor.isRestricaoAdocao()) {
            return "Tutor possui restrição: " + tutor.getTipoRestricao();
        }

        // -------- REGRAS DA SPRINT 3 --------

        // 1. Idade do tutor deve ser >= idadeIdeal do pet
        if (tutor.getIdade() < pet.getIdadeIdeal()) {
            return "Adoção negada: tutor não atende à idade recomendada para este pet.";
        }

        // 2. Tutor idoso não pode adotar pets com energia ALTA
        if (tutor.getIdade() >= 60 &&
                pet.getEnergia() != null &&
                pet.getEnergia().equalsIgnoreCase("ALTA")) {

            return "Adoção negada: pets de ALTA energia não são recomendados para tutores idosos.";
        }

        // -------- FINALIZA A ADOÇÃO --------

        pet.setTutor(tutor);
        pet.setStatusAdocao(StatusAdocao.ADOTADO);
        repository.atualizar(pet);

        return "Sucesso: o pet " + pet.getNome() + " agora pertence a " + tutor.getNome();
    }

    // ======================================================
    // ======== HISTÓRICO DE CONSULTAS / VACINAS (S2) =======
    // ======================================================

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

    // ======================================================
    // ================= FILTROS (SPRINT 3) =================
    // ======================================================

    public List<Pet> filtrarPorEnergia(String energia) {
        return repository.listar().stream()
                .filter(p -> p.getEnergia() != null &&
                        p.getEnergia().equalsIgnoreCase(energia))
                .toList();
    }

    public List<Pet> filtrarPorIdadeIdeal(int idadeAdotante) {
        return repository.listar().stream()
                .filter(p -> p.getIdadeIdeal() <= idadeAdotante)
                .toList();
    }

    public List<Pet> filtrarPorTamanho(String tamanho) {
        return repository.listar().stream()
                .filter(p -> p.getTamanho() != null &&
                        p.getTamanho().name().equalsIgnoreCase(tamanho))
                .toList();
    }
}
