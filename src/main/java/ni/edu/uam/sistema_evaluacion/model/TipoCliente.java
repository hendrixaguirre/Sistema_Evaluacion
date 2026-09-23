package ni.edu.uam.sistema_evaluacion.model;

public enum TipoCliente {
    NATURAL("Natural"),
    JURIDICO("Jurídico");

    private final String etiqueta;

    TipoCliente(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
