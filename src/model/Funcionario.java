package model;

import enums.FuncaoFuncionario;

public class Funcionario {

    private int id;
    private String nome;
    private String cpf;
    private int idade; // 🔹 adicionar
    private FuncaoFuncionario funcao;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public int getIdade() { return idade; } // 🔹 adicionar
    public void setIdade(int idade) { this.idade = idade; } // 🔹 adicionar

    public FuncaoFuncionario getFuncao() { return funcao; }
    public void setFuncao(FuncaoFuncionario funcao) { this.funcao = funcao; }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- FUNCIONÁRIO ---\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nome: ").append(nome != null ? nome : "-").append("\n");
        sb.append("CPF: ").append(cpf != null ? cpf : "-").append("\n");
        sb.append("Idade: ").append(idade).append("\n");
        sb.append("Função: ").append(funcao != null ? funcao : "-").append("\n");
        sb.append("-------------------\n");
        return sb.toString();
    }

}

