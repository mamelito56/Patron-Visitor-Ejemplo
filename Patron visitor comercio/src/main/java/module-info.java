module com.ecommerce {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens app to javafx.fxml, javafx.graphics;
    opens viewController to javafx.fxml;
    opens model to javafx.base;

    exports app;
    exports model;
    exports visitor;
    exports viewController;
}