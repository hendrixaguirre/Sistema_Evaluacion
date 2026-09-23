package ni.edu.uam.sistema_evaluacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InicioSesionApplication.class.getResource("inicio-sesion.fxml"));
        Parent raiz = fxmlLoader.load();
        Scene scene = new Scene(raiz);
        stage.setTitle("Inicio de sesión");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.show();
    }
}
