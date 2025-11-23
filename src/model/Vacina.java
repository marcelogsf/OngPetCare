package model;

import enums.TipoVacina;

public class Vacina {

    private TipoVacina tipo;
    private String data;

    public Vacina(TipoVacina tipo, String data) {
        this.tipo = tipo;
        this.data = data;
    }

    public TipoVacina getTipo() { return tipo; }
    public String getData() { return data; }

    @Override
    public String toString() {
        return "Vacina:\n" +
                "  Tipo: " + (tipo != null ? tipo : "-") + "\n" +
                "  Data: " + (data != null ? data : "-") + "\n";
    }

}
