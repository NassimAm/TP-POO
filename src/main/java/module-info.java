module com.example.tppoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tppoo to javafx.fxml;
    exports com.example.tppoo;
}