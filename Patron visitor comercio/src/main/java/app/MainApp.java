package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto de entrada de la aplicacion JavaFX de comercio electronico con patron Visitor.
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class MainApp extends Application {

    /**
     * Carga el FXML principal y muestra la ventana de la aplicacion.
     *
     * @param stage ventana principal de JavaFX.
     * @throws Exception si el archivo FXML no se encuentra o falla al cargarse.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                MainApp.class.getResource("/com/ecommerce/main-view.fxml"));

        Scene scene = new Scene(loader.load(), 1_060, 680);

        stage.setTitle("E-Commerce — Patron Visitor");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.show();
    }

    /** @param args argumentos de linea de comandos (no utilizados). */
    public static void main(String[] args) {
        launch(args);
    }
}