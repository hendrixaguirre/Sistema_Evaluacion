package ni.edu.uam.sistema_evaluacion.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesionController {

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

        try {
            Parent vista = FXMLLoader.load(getClass().getResource("/ni/edu/uam/sistema_evaluacion/ventana-principal.fxml"));
            Stage ventana = (Stage) txtUsuario.getScene().getWindow();
            ventana.setScene(new Scene(vista));
            ventana.setTitle("Ventana principal");
            ventana.show();
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