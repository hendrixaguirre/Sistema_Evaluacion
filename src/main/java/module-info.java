module ni.edu.uam.sistema_evaluacion {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.sistema_evaluacion to javafx.fxml;
    exports ni.edu.uam.sistema_evaluacion;
}