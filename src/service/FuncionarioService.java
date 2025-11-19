package service;

import model.Funcionario;
import repository.FuncionarioRepository;

import java.util.List;

public class FuncionarioService {

    private FuncionarioRepository repository = new FuncionarioRepository();

    public void cadastrar(Funcionario funcionario) {
        repository.salvar(funcionario);
    }

    public List<Funcionario> listar() {
        return repository.listar();
    }

    public Funcionario buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public void atualizar(Funcionario funcionario) {
        repository.atualizar(funcionario);
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }
}
