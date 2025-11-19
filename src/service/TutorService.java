package service;

import model.Tutor;
import repository.TutorRepository;

import java.util.List;

public class TutorService {

    private TutorRepository repository = new TutorRepository();

    public void cadastrar(Tutor tutor) {
        repository.salvar(tutor);
    }

    public List<Tutor> listar() {
        return repository.listar();
    }

    public Tutor buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public void atualizar(Tutor tutor) {
        repository.atualizar(tutor);
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }
}
