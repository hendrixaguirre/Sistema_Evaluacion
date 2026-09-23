package ni.edu.uam.sistema_evaluacion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class VentanaPrincipalController {

    private static final String RUTA_INICIO = "/ni/edu/uam/sistema_evaluacion/inicio-sesion.fxml";
    private static final String RUTA_REGISTRO = "/ni/edu/uam/sistema_evaluacion/registro-cliente.fxml";
    private static final String RUTA_CONSULTA = "/ni/edu/uam/sistema_evaluacion/consulta-clientes.fxml";

    @FXML
    private void abrirRegistro(ActionEvent event) {
        cambiarVentana(event, RUTA_REGISTRO, "Registro de cliente");
    }

    @FXML
    private void abrirConsulta(ActionEvent event) {
        cambiarVentana(event, RUTA_CONSULTA, "Consulta de clientes");
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        cambiarVentana(event, RUTA_INICIO, "Inicio de sesión");
    }

    private void cambiarVentana(ActionEvent event, String ruta, String titulo) {
        URL archivo = getClass().getResource(ruta);

        if (archivo == null) {
            mostrarError("No se encontró el archivo FXML:\n" + ruta);
            return;
        }

        try {
            Parent vista = FXMLLoader.load(archivo);
            Stage ventana = (Stage) ((Node) event.getSource()).getScene().getWindow();

            ventana.setScene(new Scene(vista));
            ventana.setTitle(titulo);
            ventana.show();

        } catch (IOException e) {
            mostrarError("No se pudo abrir la ventana:\n" + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error al abrir ventana");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}