package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * entrada a la aplicacion de comercio electronico con patron Visitor hecha en JavaFX.
 *
 * <p>extiende {@link Application} e inicia la interfaz grafica cargando el
 * archivo FXML principal. el catalogo de productos se inicializa de forma
 * diferida en el primer acceso al Singleton {@code CatalogoProductos},
 * manteniendo esta clase con una unica responsabilidad: arrancar la UI.</p>
 *
 * @author Samuel Marín Varón
 * @version 1.0
 */
public class MainApp extends Application {

    /**
     * inicializa y muestra la ventana principal de la aplicacion.
     *
     * @param stage ventana principal proporcionada por el entorno JavaFX.
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

    /**
     * punto de entrada de la maquina virtual Java.
     *
     * @param args argumentos de linea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        launch(args);
    }
}