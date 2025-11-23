package model;

import enums.Tamanho;
import enums.StatusSaude;
import enums.StatusAdocao;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Pet atualizada para o Sprint 2:
 * - Histórico: consultas, vacinas, castrações
 * - Métodos registrarConsulta/registrarVacina/registrarCastracao
 * - mostrarHistorico()
 * - campo tutor (para adoção) com getter/setter
 * - statusAdocao inicializado como DISPONIVEL
 */
public class Pet {

    private int id;
    private String nome;
    private int idade;
    private String raca;
    private String cor;
    private Tamanho tamanho;
    private StatusSaude statusSaude;
    private StatusAdocao statusAdocao = StatusAdocao.DISPONIVEL;

    // novo campo: Tutor do pet (poderá ser null enquanto não adotado)
    private Tutor tutor;

    // Histórico do pet
    private List<Consulta> consultas = new ArrayList<>();
    private List<Vacina> vacinas = new ArrayList<>();
    private List<Castracao> castracoes = new ArrayList<>();

    // Getters e Setters existentes
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

    // Tutor getter/setter (novo)
    public Tutor getTutor() { return tutor; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }

    // ---------- Métodos do histórico ----------
    public void registrarConsulta(Consulta c) {
        if (c != null) this.consultas.add(c);
    }

    public void registrarVacina(Vacina v) {
        if (v != null) this.vacinas.add(v);
    }

    public void registrarCastracao(Castracao c) {
        if (c != null) this.castracoes.add(c);
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

    /**
     * Mostra no console o histórico do pet (consultas, vacinas e castrações).
     * Use este método no menu de benefícios quando o usuário escolher "Ver Histórico".
     */
    public void mostrarHistorico() {
        System.out.println("\n=== HISTÓRICO DO PET: " + (nome != null ? nome : "SEM NOME") + " ===\n");

        // Consultas
        System.out.println("-- CONSULTAS --");
        if (consultas == null || consultas.isEmpty()) {
            System.out.println("Nenhuma consulta registrada.\n");
        } else {
            for (Consulta c : consultas) {
                System.out.println("Data: " + (c.getData() != null ? c.getData() : "-"));
                System.out.println("Descrição: " + (c.getDescricao() != null ? c.getDescricao() : "-"));
                System.out.println("Veterinário: " + (c.getVeterinario() != null ? c.getVeterinario() : "-"));
                System.out.println("--------------------------");
            }
        }

        // Vacinas
        System.out.println("\n-- VACINAS --");
        if (vacinas == null || vacinas.isEmpty()) {
            System.out.println("Nenhuma vacina registrada.\n");
        } else {
            for (Vacina v : vacinas) {
                System.out.println("Tipo: " + (v.getTipo() != null ? v.getTipo() : "-"));
                System.out.println("Data: " + (v.getData() != null ? v.getData() : "-"));
                System.out.println("--------------------------");
            }
        }

        // Castrações
        System.out.println("\n-- CASTRAÇÕES --");
        if (castracoes == null || castracoes.isEmpty()) {
            System.out.println("Nenhuma castração registrada.\n");
        } else {
            for (Castracao ca : castracoes) {
                System.out.println("Data: " + (ca.getData() != null ? ca.getData() : "-"));
                System.out.println("Veterinário: " + (ca.getVeterinario() != null ? ca.getVeterinario() : "-"));
                System.out.println("Observações: " + (ca.getObservacoes() != null ? ca.getObservacoes() : "-"));
                System.out.println("--------------------------");
            }
        }

        System.out.println("==============================\n");
    }


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
                "\nStatus de Saúde: " + statusSaude +
                "\nStatus de Adoção: " + statusAdocao +
                "\nTutor: " + tutorNome +
                "\n-----------------\n";
    }

}
