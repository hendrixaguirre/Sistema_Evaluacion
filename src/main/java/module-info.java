module ni.edu.uam.sistema_evaluacion {
    requires javafx.controls;
    requires javafx.fxml;

    opens ni.edu.uam.sistema_evaluacion to javafx.fxml;
    opens ni.edu.uam.sistema_evaluacion.controller to javafx.fxml;
    opens ni.edu.uam.sistema_evaluacion.model to javafx.base;

    exports ni.edu.uam.sistema_evaluacion;
    exports ni.edu.uam.sistema_evaluacion.controller;
    exports ni.edu.uam.sistema_evaluacion.model;
}
