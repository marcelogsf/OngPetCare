package model;

import enums.Tamanho;
import enums.StatusSaude;
import enums.StatusAdocao;

public class Pet {

    private int id;
    private String nome;
    private int idade;
    private String raca;
    private String cor;
    private Tamanho tamanho;
    private StatusSaude statusSaude;
    private StatusAdocao statusAdocao;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public Tamanho getTamanho() { return tamanho; }
    public void setTamanho(Tamanho tamanho) { this.tamanho = tamanho; }

    public StatusSaude getStatusSaude() { return statusSaude; }
    public void setStatusSaude(StatusSaude statusSaude) { this.statusSaude = statusSaude; }

    public StatusAdocao getStatusAdocao() { return statusAdocao; }
    public void setStatusAdocao(StatusAdocao statusAdocao) { this.statusAdocao = statusAdocao; }
}
