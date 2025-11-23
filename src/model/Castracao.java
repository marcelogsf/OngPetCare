package model;

public class Castracao {

    private String data;
    private String veterinario;
    private String observacoes;

    public Castracao(String data, String veterinario, String observacoes) {
        this.data = data;
        this.veterinario = veterinario;
        this.observacoes = observacoes;
    }

    public String getData() { return data; }
    public String getVeterinario() { return veterinario; }
    public String getObservacoes() { return observacoes; }

    @Override
    public String toString() {
        return "Castracao:\n" +
                "  Data: " + (data != null ? data : "-") + "\n" +
                "  Veterinário: " + (veterinario != null ? veterinario : "-") + "\n" +
                "  Observações: " + (observacoes != null ? observacoes : "-") + "\n";
    }

}
