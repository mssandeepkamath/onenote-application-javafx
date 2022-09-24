module com.onenote.onenote {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;

    opens com.onenote.onenote to javafx.fxml;
    exports com.onenote.onenote;
}