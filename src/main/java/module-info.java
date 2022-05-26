module com.example.tppoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tppoo to javafx.fxml;
    exports com.example.tppoo;
    exports com.example.tppoo.Models;
    opens com.example.tppoo.Models to javafx.fxml;
}