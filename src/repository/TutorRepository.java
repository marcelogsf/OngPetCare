package repository;

import model.Tutor;
import java.util.ArrayList;
import java.util.List;

public class TutorRepository {

    private List<Tutor> tutores = new ArrayList<>();

    public void salvar(Tutor tutor) {
        tutores.add(tutor);
    }

    public List<Tutor> listar() {
        return tutores;
    }

    public Tutor buscarPorId(int id) {
        for (Tutor t : tutores) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public void atualizar(Tutor tutorAtualizado) {
        for (int i = 0; i < tutores.size(); i++) {
            if (tutores.get(i).getId() == tutorAtualizado.getId()) {
                tutores.set(i, tutorAtualizado);
                return;
            }
        }
    }

    public boolean remover(int id) {
        return tutores.removeIf(t -> t.getId() == id);
    }
}
