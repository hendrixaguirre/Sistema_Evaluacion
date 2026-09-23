package ni.edu.uam.sistema_evaluacion.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import ni.edu.uam.sistema_evaluacion.InicioSesionApplication;
import ni.edu.uam.sistema_evaluacion.controller.DetalleClienteController;
import ni.edu.uam.sistema_evaluacion.model.Cliente;

import java.io.IOException;
import java.net.URL;

public final class Vistas {
    private Vistas() {
    }

    public static void irAInicioSesion(Window origen) throws IOException {
        cambiarEscena(origen, "inicio-sesion.fxml", "Inicio de sesión");
    }

    public static void irAVentanaPrincipal(Window origen) throws IOException {
        cambiarEscena(origen, "ventana-principal.fxml", "Ventana principal");
    }

    public static void irARegistroCliente(Window origen) throws IOException {
        cambiarEscena(origen, "registro-cliente.fxml", "Registro de cliente");
    }

    public static void irAConsultaClientes(Window origen) throws IOException {
        cambiarEscena(origen, "consulta-clientes.fxml", "Consulta de clientes");
    }

    public static void cambiarEscena(Window origen, String fxml, String titulo, double ancho, double alto) throws IOException {
        cambiarEscena(origen, fxml, titulo);
    }

    public static void cambiarEscena(Window origen, String fxml, String titulo) throws IOException {
        URL archivo = InicioSesionApplication.class.getResource(fxml);
        if (archivo == null) {
            throw new IOException("No se encontró el archivo FXML: " + fxml);
        }

        Parent vista = FXMLLoader.load(archivo);
        Stage ventana = (Stage) origen;
        ventana.setScene(new Scene(vista));
        ventana.setTitle(titulo);
        ventana.sizeToScene();
        ventana.centerOnScreen();
        ventana.show();
    }

    public static void abrirConsultaClientes(Window propietario) throws IOException {
        FXMLLoader loader = new FXMLLoader(InicioSesionApplication.class.getResource("consulta-clientes.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Consulta de clientes");
        stage.setScene(new Scene(loader.load()));
        stage.sizeToScene();
        stage.setMinWidth(760);
        stage.setMinHeight(480);
        if (propietario != null) {
            stage.initOwner(propietario);
        }
        stage.show();
    }

    public static void abrirDetalleCliente(Window propietario, Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader(InicioSesionApplication.class.getResource("detalle-cliente.fxml"));
        Parent raiz = loader.load();
        DetalleClienteController controlador = loader.getController();
        controlador.setCliente(cliente);

        Stage stage = new Stage();
        stage.setTitle("Detalle del cliente");
        stage.initModality(Modality.WINDOW_MODAL);
        if (propietario != null) {
            stage.initOwner(propietario);
        }
        stage.setScene(new Scene(raiz));
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }
}
