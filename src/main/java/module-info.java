module com.pbl.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.pbl.biblioteca.view to javafx.fxml;
    exports com.pbl.biblioteca.view;
    //exports com.pbl.biblioteca;
    opens com.pbl.biblioteca to javafx.fxml;
    exports com.pbl.biblioteca.controller;
    opens com.pbl.biblioteca.controller to javafx.fxml;

    opens com.pbl.biblioteca.model to javafx.graphics, javafx.fxml, javafx.base;
}