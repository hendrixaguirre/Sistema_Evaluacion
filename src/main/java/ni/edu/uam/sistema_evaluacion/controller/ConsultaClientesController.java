package ni.edu.uam.sistema_evaluacion.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ni.edu.uam.sistema_evaluacion.data.RepositorioClientes;
import ni.edu.uam.sistema_evaluacion.model.Cliente;
import ni.edu.uam.sistema_evaluacion.model.TipoCliente;
import ni.edu.uam.sistema_evaluacion.model.TipoSolicitud;
import ni.edu.uam.sistema_evaluacion.util.Vistas;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConsultaClientesController {
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colTipoCliente;
    @FXML
    private TableColumn<Cliente, String> colCiudad;
    @FXML
    private TableColumn<Cliente, LocalDate> colFechaNacimiento;
    @FXML
    private TableColumn<Cliente, String> colTipoSolicitud;
    @FXML
    private Label lblSeleccion;

    @FXML
    private void initialize() {
        colNombre.setCellValueFactory(celda -> celda.getValue().nombreCompletoProperty());
        colTipoCliente.setCellValueFactory(celda -> Bindings.createStringBinding(() -> {
            TipoCliente tipo = celda.getValue().getTipoCliente();
            return tipo == null ? "" : tipo.getEtiqueta();
        }, celda.getValue().tipoClienteProperty()));
        colCiudad.setCellValueFactory(celda -> celda.getValue().ciudadProperty());
        colFechaNacimiento.setCellValueFactory(celda -> celda.getValue().fechaNacimientoProperty());
        colFechaNacimiento.setCellFactory(columna -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate fecha, boolean vacio) {
                super.updateItem(fecha, vacio);
                setText(vacio || fecha == null ? null : fecha.format(FORMATO_FECHA));
            }
        });
        colTipoSolicitud.setCellValueFactory(celda -> Bindings.createStringBinding(() -> {
            TipoSolicitud tipo = celda.getValue().getTipoSolicitud();
            return tipo == null ? "" : tipo.getEtiqueta();
        }, celda.getValue().tipoSolicitudProperty()));

        tablaClientes.setItems(RepositorioClientes.getInstancia().getClientes());
        tablaClientes.setPlaceholder(new Label("No hay clientes registrados"));
        actualizarEtiquetaSeleccion(null);
    }

    @FXML
    private void volverAlMenu() {
        try {
            Vistas.irAVentanaPrincipal(tablaClientes.getScene().getWindow());
        } catch (IOException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Consulta de clientes");
            alerta.setHeaderText(null);
            alerta.setContentText("No se pudo volver al menú principal.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void alHacerClicEnTabla(MouseEvent evento) {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        actualizarEtiquetaSeleccion(seleccionado);

        if (seleccionado == null
                || evento.getButton() != MouseButton.PRIMARY
                || evento.getClickCount() != 2) {
            return;
        }

        try {
            Vistas.abrirDetalleCliente(tablaClientes.getScene().getWindow(), seleccionado);
        } catch (IOException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Consulta de clientes");
            alerta.setHeaderText("No se pudo abrir el detalle");
            alerta.setContentText("Ocurrió un error al cargar la ventana de detalle o edición.");
            alerta.showAndWait();
        }
    }

    private void actualizarEtiquetaSeleccion(Cliente cliente) {
        if (cliente == null) {
            lblSeleccion.setText("Seleccione un cliente. Doble clic para ver o editar el detalle.");
            return;
        }
        lblSeleccion.setText("Seleccionado: " + cliente.getNombreCompleto());
    }
}
