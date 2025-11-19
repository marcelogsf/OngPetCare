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
}

