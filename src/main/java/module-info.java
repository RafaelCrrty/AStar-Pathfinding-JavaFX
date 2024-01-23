module com.example.graficaciondegrafos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.graficaciondegrafos to javafx.fxml;
    opens com.example.graficaciondegrafos.Models to javafx.base;

    exports com.example.graficaciondegrafos;
}