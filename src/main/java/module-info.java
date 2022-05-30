module com.parkinggaragev3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.parkinggaragev3 to javafx.fxml;
    exports com.parkinggaragev3;
}