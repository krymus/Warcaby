module com.example.warcaby {
    requires javafx.controls;
    requires javafx.fxml;


    opens Widok to javafx.fxml;
    exports Widok;
}