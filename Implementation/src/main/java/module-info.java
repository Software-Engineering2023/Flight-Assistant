module com.swe2023 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.swe2023 to javafx.fxml;
    exports com.swe2023.gui;
    exports com.swe2023;
}