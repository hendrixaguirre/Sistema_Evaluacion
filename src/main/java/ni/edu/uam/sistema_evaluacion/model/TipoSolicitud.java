package ni.edu.uam.sistema_evaluacion.model;

public enum TipoSolicitud {
    CREDITO_PERSONAL("Crédito personal"),
    CUENTA_AHORRO("Cuenta de ahorro"),
    TARJETA_CREDITO("Tarjeta de crédito"),
    PRESTAMO_HIPOTECARIO("Préstamo hipotecario"),
    CUENTA_CORRIENTE("Cuenta corriente");

    private final String etiqueta;

    TipoSolicitud(String etiqueta) {
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
