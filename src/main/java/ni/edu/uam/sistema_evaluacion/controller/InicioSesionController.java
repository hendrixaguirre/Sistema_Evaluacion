package ni.edu.uam.sistema_evaluacion.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ni.edu.uam.sistema_evaluacion.util.Vistas;

import java.io.IOException;

public class InicioSesionController {

    private static final String USUARIO_VALIDO = "admin";
    private static final String CONTRASENA_VALIDA = "admin";

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContraseña.getText().trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            Alert alerta = new Alert(
                    Alert.AlertType.WARNING,
                    "Ingresa el usuario y la contraseña."
            );
            alerta.setTitle("Datos incompletos");
            alerta.setHeaderText(null);
            alerta.showAndWait();
            return;
        }

        if (!USUARIO_VALIDO.equals(usuario) || !CONTRASENA_VALIDA.equals(contrasena)) {
            Alert alerta = new Alert(
                    Alert.AlertType.ERROR,
                    "Usuario o contraseña incorrectos."
            );
            alerta.setTitle("Acceso denegado");
            alerta.setHeaderText(null);
            alerta.showAndWait();
            return;
        }

        try {
            Vistas.irAVentanaPrincipal(txtUsuario.getScene().getWindow());
        } catch (IOException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "No se pudo abrir la ventana principal.");
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.showAndWait();
        }
    }

    @FXML
    private void salir() {
        Alert confirmacion = new Alert(
                Alert.AlertType.CONFIRMATION,
                "¿Deseas salir de la aplicación?",
                ButtonType.YES,
                ButtonType.NO
        );
        confirmacion.setTitle("Confirmar salida");
        confirmacion.setHeaderText(null);

        if (confirmacion.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            Platform.exit();
        }
    }
}
