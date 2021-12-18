module com.swe2023 {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.dbcp2;
    requires java.management;
    requires java.sql;

    opens com.swe2023 to javafx.fxml;
    exports com.swe2023.gui;
    exports com.swe2023;
}