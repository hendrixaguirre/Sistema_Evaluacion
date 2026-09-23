package ni.edu.uam.sistema_evaluacion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import ni.edu.uam.sistema_evaluacion.util.Vistas;

import java.io.IOException;

public class VentanaPrincipalController {

    @FXML
    private void abrirRegistro(ActionEvent event) {
        try {
            Vistas.irARegistroCliente(((Node) event.getSource()).getScene().getWindow());
        } catch (IOException e) {
            mostrarError("No se pudo abrir el registro de cliente:\n" + e.getMessage());
        }
    }

    @FXML
    private void abrirConsulta(ActionEvent event) {
        try {
            Vistas.irAConsultaClientes(((Node) event.getSource()).getScene().getWindow());
        } catch (IOException e) {
            mostrarError("No se pudo abrir la consulta de clientes:\n" + e.getMessage());
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        try {
            Vistas.irAInicioSesion(((Node) event.getSource()).getScene().getWindow());
        } catch (IOException e) {
            mostrarError("No se pudo volver al inicio de sesión:\n" + e.getMessage());
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
