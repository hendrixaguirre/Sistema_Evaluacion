package ni.edu.uam.sistema_evaluacion.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Cliente {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nombres = new SimpleStringProperty();
    private final StringProperty apellidos = new SimpleStringProperty();
    private final StringProperty nombreCompleto = new SimpleStringProperty();
    private final ObjectProperty<TipoCliente> tipoCliente = new SimpleObjectProperty<>();
    private final StringProperty ciudad = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> fechaNacimiento = new SimpleObjectProperty<>();
    private final ObjectProperty<TipoSolicitud> tipoSolicitud = new SimpleObjectProperty<>();
    private final StringProperty identificacion = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();
    private final StringProperty correo = new SimpleStringProperty();
    private final StringProperty servicioDeInteres = new SimpleStringProperty();
    private final StringProperty fotografia = new SimpleStringProperty();

    public Cliente() {
    }

    public Cliente(int id, String nombreCompleto, TipoCliente tipoCliente, String ciudad,
                   LocalDate fechaNacimiento, TipoSolicitud tipoSolicitud,
                   String identificacion, String telefono, String correo) {
        setId(id);
        setNombreCompleto(nombreCompleto);
        setTipoCliente(tipoCliente);
        setCiudad(ciudad);
        setFechaNacimiento(fechaNacimiento);
        setTipoSolicitud(tipoSolicitud);
        setIdentificacion(identificacion);
        setTelefono(telefono);
        setCorreo(correo);
    }

    public Cliente(String nombres, String apellidos, String tipoCliente, String ciudad,
                   LocalDate fechaNacimiento, String tipoSolicitud,
                   String servicioDeInteres, String fotografia) {
        setNombres(nombres);
        setApellidos(apellidos);
        setNombreCompleto((nuloAVacio(nombres) + " " + nuloAVacio(apellidos)).trim());
        setTipoCliente(tipoClienteDesdeTexto(tipoCliente));
        setCiudad(ciudad);
        setFechaNacimiento(fechaNacimiento);
        setTipoSolicitud(tipoSolicitudDesdeTexto(tipoSolicitud));
        setServicioDeInteres(servicioDeInteres);
        setFotografia(fotografia);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNombres() {
        return nombres.get();
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }

    public StringProperty nombresProperty() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public String getNombreCompleto() {
        return nombreCompleto.get();
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto.set(nombreCompleto);
    }

    public StringProperty nombreCompletoProperty() {
        return nombreCompleto;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente.get();
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente.set(tipoCliente);
    }

    public ObjectProperty<TipoCliente> tipoClienteProperty() {
        return tipoCliente;
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public StringProperty ciudadProperty() {
        return ciudad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento.get();
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento.set(fechaNacimiento);
    }

    public ObjectProperty<LocalDate> fechaNacimientoProperty() {
        return fechaNacimiento;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud.get();
    }

    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
        this.tipoSolicitud.set(tipoSolicitud);
    }

    public ObjectProperty<TipoSolicitud> tipoSolicitudProperty() {
        return tipoSolicitud;
    }

    public String getIdentificacion() {
        return identificacion.get();
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion.set(identificacion);
    }

    public StringProperty identificacionProperty() {
        return identificacion;
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public String getCorreo() {
        return correo.get();
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public StringProperty correoProperty() {
        return correo;
    }

    public String getServicioDeInteres() {
        return servicioDeInteres.get();
    }

    public void setServicioDeInteres(String servicioDeInteres) {
        this.servicioDeInteres.set(servicioDeInteres);
    }

    public StringProperty servicioDeInteresProperty() {
        return servicioDeInteres;
    }

    public String getFotografia() {
        return fotografia.get();
    }

    public void setFotografia(String fotografia) {
        this.fotografia.set(fotografia);
    }

    public StringProperty fotografiaProperty() {
        return fotografia;
    }

    private static TipoCliente tipoClienteDesdeTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        for (TipoCliente tipo : TipoCliente.values()) {
            if (tipo.getEtiqueta().equalsIgnoreCase(texto) || tipo.name().equalsIgnoreCase(texto)) {
                return tipo;
            }
        }
        return null;
    }

    private static TipoSolicitud tipoSolicitudDesdeTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        for (TipoSolicitud tipo : TipoSolicitud.values()) {
            if (tipo.getEtiqueta().equalsIgnoreCase(texto) || tipo.name().equalsIgnoreCase(texto)) {
                return tipo;
            }
        }
        return null;
    }

    private static String nuloAVacio(String valor) {
        return valor == null ? "" : valor;
    }
}
