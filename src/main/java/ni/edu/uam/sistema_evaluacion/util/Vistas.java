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

public final class Vistas {
    private Vistas() {
    }

    public static void abrirConsultaClientes(Window propietario) throws IOException {
        FXMLLoader loader = new FXMLLoader(InicioSesionApplication.class.getResource("consulta-clientes.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Consulta de clientes");
        stage.setScene(new Scene(loader.load(), 920, 560));
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
        stage.setScene(new Scene(raiz, 520, 580));
        stage.setResizable(false);
        stage.show();
    }
}
