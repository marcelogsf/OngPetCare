package model;

public class Tutor {

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private boolean restricaoAdocao;
    private String tipoRestricao;

    // Novos atributos que o controller precisa
    private int idade;
    private String email;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public boolean isRestricaoAdocao() { return restricaoAdocao; }
    public void setRestricaoAdocao(boolean restricaoAdocao) { this.restricaoAdocao = restricaoAdocao; }

    public String getTipoRestricao() { return tipoRestricao; }
    public void setTipoRestricao(String tipoRestricao) { this.tipoRestricao = tipoRestricao; }

    // Getters e Setters dos novos atributos
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n----- TUTOR -----\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nome: ").append(nome != null ? nome : "-").append("\n");
        sb.append("CPF: ").append(cpf != null ? cpf : "-").append("\n");
        sb.append("Idade: ").append(idade).append("\n");
        sb.append("Email: ").append(email != null ? email : "-").append("\n");
        sb.append("Telefone: ").append(telefone != null ? telefone : "-").append("\n");
        sb.append("Endereço: ").append(endereco != null ? endereco : "-").append("\n");
        sb.append("Restrição de Adoção: ").append(restricaoAdocao ? "Sim" : "Não").append("\n");
        if (restricaoAdocao) {
            sb.append("Tipo de Restrição: ").append(tipoRestricao != null ? tipoRestricao : "-").append("\n");
        }
        sb.append("------------------\n");
        return sb.toString();
    }

}
