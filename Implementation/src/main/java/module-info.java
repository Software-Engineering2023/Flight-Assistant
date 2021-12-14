module com.swe2023.flightsystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.swe2023.flightsystem to javafx.fxml;
    exports com.swe2023.flightsystem;
    exports com.swe2023.gui;
    exports com.swe2023;
    opens com.swe2023 to javafx.fxml;
}