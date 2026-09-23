package ni.edu.uam.sistema_evaluacion.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ni.edu.uam.sistema_evaluacion.model.Cliente;
import ni.edu.uam.sistema_evaluacion.model.TipoCliente;
import ni.edu.uam.sistema_evaluacion.model.TipoSolicitud;

import java.time.LocalDate;

/**
 * Almacén en memoria compartido por las ventanas del sistema.
 * La ventana de registro podrá agregar clientes a esta misma lista.
 */
public final class RepositorioClientes {
    private static final RepositorioClientes INSTANCIA = new RepositorioClientes();

    private final ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private int siguienteId = 1;

    private RepositorioClientes() {
        cargarDatosDeEjemplo();
    }

    public static RepositorioClientes getInstancia() {
        return INSTANCIA;
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public void agregar(Cliente cliente) {
        if (cliente.getId() <= 0) {
            cliente.setId(siguienteId++);
        } else {
            siguienteId = Math.max(siguienteId, cliente.getId() + 1);
        }
        clientes.add(cliente);
    }

    private void cargarDatosDeEjemplo() {
        agregar(new Cliente(0, "Ana María López Cano", TipoCliente.NATURAL, "Managua",
                LocalDate.of(1994, 3, 12), TipoSolicitud.CREDITO_PERSONAL,
                "001-120394-0001A", "8888-1122", "ana.lopez@correo.com"));
        agregar(new Cliente(0, "Comercial El Faro, S.A.", TipoCliente.JURIDICO, "León",
                LocalDate.of(2008, 7, 21), TipoSolicitud.CUENTA_CORRIENTE,
                "J0310000001234", "2266-3344", "contacto@elfaro.com.ni"));
        agregar(new Cliente(0, "Carlos Eduardo Méndez", TipoCliente.NATURAL, "Granada",
                LocalDate.of(1988, 11, 5), TipoSolicitud.TARJETA_CREDITO,
                "001-051188-0007B", "8777-5566", "carlos.mendez@correo.com"));
        agregar(new Cliente(0, "Lucía Fernanda Rivas", TipoCliente.NATURAL, "Masaya",
                LocalDate.of(1999, 1, 30), TipoSolicitud.CUENTA_AHORRO,
                "001-300199-0012C", "8555-7788", "lucia.rivas@correo.com"));
        agregar(new Cliente(0, "Inversiones del Norte, S.A.", TipoCliente.JURIDICO, "Estelí",
                LocalDate.of(2015, 9, 8), TipoSolicitud.PRESTAMO_HIPOTECARIO,
                "J0310000005678", "2713-9900", "gerencia@idn.com.ni"));
        agregar(new Cliente(0, "José Antonio Blandón", TipoCliente.NATURAL, "Chinandega",
                LocalDate.of(1976, 6, 18), TipoSolicitud.CREDITO_PERSONAL,
                "001-180676-0003D", "8666-2211", "jose.blandon@correo.com"));
    }
}
