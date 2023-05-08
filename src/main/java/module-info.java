module pl.jarrobots.totalcommander {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens pl.jarrobots.totalcommander to javafx.fxml;
    exports pl.jarrobots.totalcommander;
}