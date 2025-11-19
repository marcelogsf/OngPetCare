package repository;

import model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {

    private List<Funcionario> funcionarios = new ArrayList<>();

    public void salvar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public List<Funcionario> listar() {
        return funcionarios;
    }

    public Funcionario buscarPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public void atualizar(Funcionario funcionarioAtualizado) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == funcionarioAtualizado.getId()) {
                funcionarios.set(i, funcionarioAtualizado);
                return;
            }
        }
    }

    public boolean remover(int id) {
        return funcionarios.removeIf(f -> f.getId() == id);
    }
}
