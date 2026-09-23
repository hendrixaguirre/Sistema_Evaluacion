package ni.edu.uam.sistema_evaluacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InicioSesionApplication.class.getResource("inicio-sesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 495, 400);
        stage.setTitle("Inicio de sesión");
        stage.setScene(scene);
        stage.setWidth(495);
        stage.setHeight(400);
        stage.centerOnScreen();
        stage.show();
    }
}
