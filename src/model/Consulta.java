package model;

public class Consulta {

    private String data;
    private String descricao;
    private String veterinario;

    public Consulta(String data, String descricao, String veterinario) {
        this.data = data;
        this.descricao = descricao;
        this.veterinario = veterinario;
    }

    public String getData() { return data; }
    public String getDescricao() { return descricao; }
    public String getVeterinario() { return veterinario; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Consulta:\n");
        sb.append("  Data: ").append(data != null ? data : "-").append("\n");
        sb.append("  Descrição: ").append(descricao != null ? descricao : "-").append("\n");
        sb.append("  Veterinário: ").append(veterinario != null ? veterinario : "-").append("\n");
        return sb.toString();
    }

}
