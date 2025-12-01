package model;

import enums.Tamanho;
import enums.StatusSaude;
import enums.StatusAdocao;

import java.util.ArrayList;
import java.util.List;

public class Pet {

    private int id;
    private String nome;
    private int idade;
    private String raca;
    private String cor;
    private Tamanho tamanho;
    private StatusSaude statusSaude;
    private StatusAdocao statusAdocao = StatusAdocao.DISPONIVEL;

    // Tutor do pet — pode ser null até a adoção
    private Tutor tutor;

    // Histórico (Sprint 2)
    private List<Consulta> consultas = new ArrayList<>();
    private List<Vacina> vacinas = new ArrayList<>();
    private List<Castracao> castracoes = new ArrayList<>();

    // Campos Sprint 3
    private String energia;     // BAIXA / MEDIA / ALTA
    private int idadeIdeal;     // idade ideal do adotante

    // ------------------ GETTERS E SETTERS ------------------

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

    public Tutor getTutor() { return tutor; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }

    // Sprint 3 getters/setters
    public String getEnergia() { return energia; }
    public void setEnergia(String energia) { this.energia = energia; }

    public int getIdadeIdeal() { return idadeIdeal; }
    public void setIdadeIdeal(int idadeIdeal) { this.idadeIdeal = idadeIdeal; }

    // ------------------ HISTÓRICO (Sprint 2) ------------------

    public void registrarConsulta(Consulta c) {
        if (c != null) consultas.add(c);
    }

    public void registrarVacina(Vacina v) {
        if (v != null) vacinas.add(v);
    }

    public void registrarCastracao(Castracao c) {
        if (c != null) castracoes.add(c);
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<Vacina> getVacinas() {
        return vacinas;
    }

    public List<Castracao> getCastracoes() {
        return castracoes;
    }

    // ------------------ EXIBIR HISTÓRICO ------------------

    public void mostrarHistorico() {
        System.out.println("\n=== HISTÓRICO DO PET: " + (nome != null ? nome : "SEM NOME") + " ===\n");

        // Consultas
        System.out.println("-- CONSULTAS --");
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta registrada.\n");
        } else {
            for (Consulta c : consultas) {
                System.out.println("Data: " + c.getData());
                System.out.println("Descrição: " + c.getDescricao());
                System.out.println("Veterinário: " + c.getVeterinario());
                System.out.println("--------------------------");
            }
        }

        // Vacinas
        System.out.println("\n-- VACINAS --");
        if (vacinas.isEmpty()) {
            System.out.println("Nenhuma vacina registrada.\n");
        } else {
            for (Vacina v : vacinas) {
                System.out.println("Tipo: " + v.getTipo());
                System.out.println("Data: " + v.getData());
                System.out.println("--------------------------");
            }
        }

        // Castrações
        System.out.println("\n-- CASTRAÇÕES --");
        if (castracoes.isEmpty()) {
            System.out.println("Nenhuma castração registrada.\n");
        } else {
            for (Castracao ca : castracoes) {
                System.out.println("Data: " + ca.getData());
                System.out.println("Veterinário: " + ca.getVeterinario());
                System.out.println("Observações: " + ca.getObservacoes());
                System.out.println("--------------------------");
            }
        }

        System.out.println("==============================\n");
    }

    // ------------------ TO STRING ------------------

    @Override
    public String toString() {
        String tutorNome = (tutor != null) ? tutor.getNome() : "Nenhum";

        return "\n----- PET -----" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nIdade: " + idade +
                "\nRaça: " + raca +
                "\nCor: " + cor +
                "\nTamanho: " + tamanho +
                "\nEnergia: " + energia +
                "\nIdade Ideal do Adotante: " + idadeIdeal +
                "\nStatus de Saúde: " + statusSaude +
                "\nStatus de Adoção: " + statusAdocao +
                "\nTutor: " + tutorNome +
                "\n-----------------\n";
    }

}
