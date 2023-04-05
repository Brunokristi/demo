module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
            
                requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
            
    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}