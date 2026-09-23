package ni.edu.uam.sistema_evaluacion.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ni.edu.uam.sistema_evaluacion.model.Cliente;
import ni.edu.uam.sistema_evaluacion.model.TipoCliente;
import ni.edu.uam.sistema_evaluacion.model.TipoSolicitud;

public class DetalleClienteController {
    @FXML
    private TextField txtNombreCompleto;
    @FXML
    private ComboBox<TipoCliente> cmbTipoCliente;
    @FXML
    private TextField txtCiudad;
    @FXML
    private DatePicker dtpFechaNacimiento;
    @FXML
    private ComboBox<TipoSolicitud> cmbTipoSolicitud;
    @FXML
    private TextField txtIdentificacion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;

    private Cliente cliente;

    @FXML
    private void initialize() {
        cmbTipoCliente.getItems().setAll(TipoCliente.values());
        cmbTipoSolicitud.getItems().setAll(TipoSolicitud.values());
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente == null) {
            return;
        }
        txtNombreCompleto.setText(nuloAVacio(cliente.getNombreCompleto()));
        cmbTipoCliente.setValue(cliente.getTipoCliente());
        txtCiudad.setText(nuloAVacio(cliente.getCiudad()));
        dtpFechaNacimiento.setValue(cliente.getFechaNacimiento());
        cmbTipoSolicitud.setValue(cliente.getTipoSolicitud());
        txtIdentificacion.setText(nuloAVacio(cliente.getIdentificacion()));
        txtTelefono.setText(nuloAVacio(cliente.getTelefono()));
        txtCorreo.setText(nuloAVacio(cliente.getCorreo()));
    }

    @FXML
    private void guardarCambios() {
        if (cliente == null) {
            return;
        }
        if (estaVacio(txtNombreCompleto.getText())
                || cmbTipoCliente.getValue() == null
                || estaVacio(txtCiudad.getText())
                || dtpFechaNacimiento.getValue() == null
                || cmbTipoSolicitud.getValue() == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Detalle del cliente");
            alerta.setHeaderText("Datos incompletos");
            alerta.setContentText("Complete nombre, tipo de cliente, ciudad, fecha de nacimiento y tipo de solicitud.");
            alerta.showAndWait();
            return;
        }

        cliente.setNombreCompleto(txtNombreCompleto.getText().trim());
        cliente.setTipoCliente(cmbTipoCliente.getValue());
        cliente.setCiudad(txtCiudad.getText().trim());
        cliente.setFechaNacimiento(dtpFechaNacimiento.getValue());
        cliente.setTipoSolicitud(cmbTipoSolicitud.getValue());
        cliente.setIdentificacion(txtIdentificacion.getText().trim());
        cliente.setTelefono(txtTelefono.getText().trim());
        cliente.setCorreo(txtCorreo.getText().trim());
        cerrar();
    }

    @FXML
    private void cancelar() {
        cerrar();
    }

    private void cerrar() {
        Stage stage = (Stage) txtNombreCompleto.getScene().getWindow();
        stage.close();
    }

    private static boolean estaVacio(String valor) {
        return valor == null || valor.isBlank();
    }

    private static String nuloAVacio(String valor) {
        return valor == null ? "" : valor;
    }
}
