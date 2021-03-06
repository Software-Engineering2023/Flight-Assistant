module com.swe2023 {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.dbcp2;
    requires java.management;
    requires java.sql;
    requires junit;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;

    opens com.swe2023 to javafx.fxml;
    exports com.swe2023.gui;
    exports com.swe2023;
    exports com.swe2023.model.Planes_Data;
    exports com.swe2023.Admin;
    exports com.swe2023.Proxy;
    exports com.swe2023.model.signUpAndLogin;
    exports com.swe2023.model.Tickets_Data;
    exports Proxy;
}