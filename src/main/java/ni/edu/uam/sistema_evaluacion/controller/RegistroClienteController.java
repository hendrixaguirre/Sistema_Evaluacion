package ni.edu.uam.sistema_evaluacion.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ni.edu.uam.sistema_evaluacion.data.RepositorioClientes;
import ni.edu.uam.sistema_evaluacion.model.Cliente;
import ni.edu.uam.sistema_evaluacion.util.Vistas;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class RegistroClienteController {
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private ComboBox<String> cmbTipoCliente;
    @FXML
    private ComboBox<String> cmbCiudad;
    @FXML
    private DatePicker dtpFechaNacimiento;
    @FXML
    private RadioButton rbtnCreditoPersonal;
    @FXML
    private RadioButton rbtnCuentaAhorro;
    @FXML
    private RadioButton rbtnTarjetaCredito;
    @FXML
    private RadioButton rbtnPrestamoHipotecario;
    @FXML
    private RadioButton rbtnCuentaCorriente;
    @FXML
    private CheckBox chkBancaLinea;
    @FXML
    private CheckBox chkTransferenciasInternacionales;
    @FXML
    private CheckBox chkSeguroFinanciero;
    @FXML
    private ImageView imgFotografia;
    @FXML
    private Button btnSeleccionarImagen;
    @FXML
    private Button btnCancelar;

    private ToggleGroup grupoSolicitud;

    private String rutaFotografia;

    @FXML
    private void initialize() {
        cmbTipoCliente.getItems().setAll("Natural", "Jurídico");

        cmbCiudad.getItems().setAll("Managua", "León", "Chinandega", "Masaya", "Matagalpa");

        grupoSolicitud = new ToggleGroup();
        rbtnCreditoPersonal.setToggleGroup(grupoSolicitud);
        rbtnCuentaAhorro.setToggleGroup(grupoSolicitud);
        rbtnTarjetaCredito.setToggleGroup(grupoSolicitud);
        rbtnPrestamoHipotecario.setToggleGroup(grupoSolicitud);
        rbtnCuentaCorriente.setToggleGroup(grupoSolicitud);
    }

    @FXML
    private void seleccionarImagen() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar fotografía");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos de imagen", "*.jpg", "*.jpeg", "*.png"));
        File f = fc.showOpenDialog(btnSeleccionarImagen.getScene().getWindow());

        if (f != null) {
            rutaFotografia = f.getAbsolutePath();
            Image imagen = new Image(f.toURI().toString());
            imgFotografia.setImage(imagen);
        }
    }


    @FXML
    private void guardarRegistro() {
        if (!validarFormulario()) {
            return;
        }
        Cliente cliente = construirCliente();

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación");
        confirmacion.setHeaderText(
                "¿Seguro que desea registrar al cliente?"
        );
        confirmacion.setContentText("Cliente: " + txtNombres.getText().trim() + " " + txtApellidos.getText().trim());
        Optional<ButtonType> respuesta = confirmacion.showAndWait();

        if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            RepositorioClientes.getInstancia().agregar(cliente);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cliente registrado");
            alert.setHeaderText("Cliente registrado correctamente");
            alert.setContentText("Nombre: " + cliente.getNombres() + " " + cliente.getApellidos() + "\nTipo de cliente: " + cliente.getTipoCliente() + "\nCiudad: " + cliente.getCiudad() + "\nFecha de nacimiento: " + cliente.getFechaNacimiento() + "\nTipo de solicitud: " + cliente.getTipoSolicitud() + "\nServicios de interés: " + cliente.getServicioDeInteres());
            alert.showAndWait();
            limpiarControles();
        }
    }

    private Cliente construirCliente() {
        return new Cliente(txtNombres.getText().trim(), txtApellidos.getText().trim(), cmbTipoCliente.getValue(), cmbCiudad.getValue(), dtpFechaNacimiento.getValue(), obtenerTipoSolicitud(), obtenerServiciosInteres(), rutaFotografia);
    }


    private String obtenerTipoSolicitud() {

        if (rbtnCreditoPersonal.isSelected()) {
            return rbtnCreditoPersonal.getText();
        } else if (rbtnCuentaAhorro.isSelected()) {
            return rbtnCuentaAhorro.getText();
        } else if (rbtnTarjetaCredito.isSelected()) {
            return rbtnTarjetaCredito.getText();
        } else if (rbtnPrestamoHipotecario.isSelected()) {
            return rbtnPrestamoHipotecario.getText();
        } else if (rbtnCuentaCorriente.isSelected()) {
            return rbtnCuentaCorriente.getText();
        }
        return "";
    }


    private String obtenerServiciosInteres() {
        String servicios = "";
        if (chkBancaLinea.isSelected()) {
            servicios += "Banca en línea";
        }
        if (chkTransferenciasInternacionales.isSelected()) {
            if (!servicios.isEmpty()) {
                servicios += ", ";
            }
            servicios += "Transferencias internacionales";
        }
        if (chkSeguroFinanciero.isSelected()) {

            if (!servicios.isEmpty()) {
                servicios += ", ";
            }
            servicios += "Seguro financiero";
        }
        return servicios;
    }


    private boolean validarFormulario() {

        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String tipoCliente = cmbTipoCliente.getValue();
        String ciudad = cmbCiudad.getValue();

        boolean solicitudSeleccionada = rbtnCreditoPersonal.isSelected() || rbtnCuentaAhorro.isSelected() || rbtnTarjetaCredito.isSelected() || rbtnPrestamoHipotecario.isSelected() || rbtnCuentaCorriente.isSelected();

        boolean servicioSeleccionado = chkBancaLinea.isSelected() || chkTransferenciasInternacionales.isSelected() || chkSeguroFinanciero.isSelected();

        if (nombres.isEmpty() || apellidos.isEmpty() || tipoCliente == null || ciudad == null || dtpFechaNacimiento.getValue() == null || !solicitudSeleccionada || !servicioSeleccionado || rutaFotografia == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos vacíos o información incompleta");
            alert.setContentText("Por favor, complete todos los campos del formulario, " + "seleccione un tipo de solicitud, al menos un servicio " + "de interés y una fotografía.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    private void limpiarControles() {
        txtNombres.clear();
        txtApellidos.clear();
        cmbTipoCliente.getSelectionModel().clearSelection();
        cmbCiudad.getSelectionModel().clearSelection();
        dtpFechaNacimiento.setValue(null);
        grupoSolicitud.selectToggle(null);
        chkBancaLinea.setSelected(false);
        chkTransferenciasInternacionales.setSelected(false);
        chkSeguroFinanciero.setSelected(false);
        imgFotografia.setImage(null);
        rutaFotografia = null;
    }

    @FXML
    private void volverAlMenu() {
        try {
            Vistas.irAVentanaPrincipal(btnCancelar.getScene().getWindow());
        } catch (IOException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("No se pudo volver al menú principal.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void cerrarRegistro() {

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación");
        confirmacion.setHeaderText("¿Seguro que desea cancelar el registro?");
        confirmacion.setContentText("Los datos ingresados no se guardarán.");

        Optional<ButtonType> respuesta = confirmacion.showAndWait();

        if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            volverAlMenu();
        }
    }
}