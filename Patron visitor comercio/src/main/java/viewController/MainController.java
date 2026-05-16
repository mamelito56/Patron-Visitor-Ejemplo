package viewController;

import model.CatalogoProductos;
import model.Electronico;
import model.Libro;
import model.Producto;
import model.Ropa;
import visitor.ProductoVisitor;
import visitor.VisitadorDescuento;
import visitor.VisitadorEnvio;
import visitor.VisitadorImpuestos;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * controlador principal de la interfaz grafica del sistema de comercio electronico.
 *
 * <p>orquesta la interaccion entre la vista FXML y el patron Visitor:
 * carga el catalogo de productos en una {@link TableView}, permite elegir
 * el visitador activo mediante un grupo de RadioButtons y muestra el reporte
 * generado al presionar el boton "Aplicar Visitador".</p>
 *
 * <p>sigue el principio de responsabilidad unica (SOLID): su unica razon
 * de cambio es la evolucion de la interfaz de usuario, no la logica
 * de los visitadores.</p>
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class MainController implements Initializable {

    @FXML private TableView<Producto> tablaProductos;

    @FXML private TableColumn<Producto, String> colTipo;

    @FXML private TableColumn<Producto, String> colNombre;

    @FXML private TableColumn<Producto, String> colPrecio;

    @FXML private TableColumn<Producto, String> colDetalle;

    @FXML private ToggleGroup grupoVisitador;

    @FXML private RadioButton rbImpuestos;

    @FXML private RadioButton rbEnvio;

    @FXML private RadioButton rbDescuento;

    @FXML private Button btnAplicar;

    @FXML private TextArea areaResultados;

    @FXML private Label lblResumen;

    private List<Producto> productos;

    /**
     * inicializa el controlador configurando las columnas de la tabla y
     * cargando los productos del catalogo; se invoca automaticamente por
     * el cargador FXML despues de inyectar todos los campos.
     *
     * @param url            url de localizacion del recurso FXML (no utilizado).
     * @param resourceBundle paquete de recursos para internacionalizacion (no utilizado).
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rbImpuestos.setSelected(true);   // seleccion predeterminada
        configurarColumnas();
        cargarProductos();
    }

    /**
     * configura las celdas de las cuatro columnas de la tabla usando
     * expresiones lambda que extraen el valor de cada campo del producto.
     */
    private void configurarColumnas() {
        colTipo.setCellValueFactory(data ->
                new SimpleStringProperty(resolverTipo(data.getValue())));

        colNombre.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNombre()));

        colPrecio.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.format("$%,.0f", data.getValue().getPrecio())));

        colDetalle.setCellValueFactory(data ->
                new SimpleStringProperty(resolverDetalle(data.getValue())));
    }

    /**
     * retorna el tipo legible del producto segun su clase concreta.
     *
     * @param producto producto cuyo tipo se desea conocer.
     * @return cadena con icono y nombre del tipo (p. ej. "📚 Libro").
     */
    private String resolverTipo(Producto producto) {
        if (producto instanceof Libro)        return "📚 Libro";
        if (producto instanceof Electronico)  return "💻 Electronico";
        if (producto instanceof Ropa)         return "👕 Ropa";
        return "Desconocido";
    }

    /**
     * retorna el atributo mas representativo de cada tipo de producto
     * para mostrarlo en la columna de detalle de la tabla.
     *
     * @param producto producto del que se extrae el detalle.
     * @return cadena con el atributo clave del producto.
     */
    private String resolverDetalle(Producto producto) {
        if (producto instanceof Libro l)       return "Autor: "  + l.getAutor();
        if (producto instanceof Electronico e) return "Marca: "  + e.getMarca()
                + " · " + (int) e.getVoltaje() + "V";
        if (producto instanceof Ropa r)        return "Talla: "  + r.getTalla()
                + " · " + r.getMaterial();
        return "";
    }

    /**
     * obtiene los productos del catalogo Singleton y los carga en la tabla.
     */
    private void cargarProductos() {
        productos = CatalogoProductos.getInstancia().getProductos();
        tablaProductos.setItems(FXCollections.observableArrayList(productos));
    }

    /**
     * aplica el visitador seleccionado a todos los productos del catalogo,
     * construye el reporte linea a linea y lo muestra en el area de resultados.
     *
     * <p>este metodo es invocado por el evento {@code onAction} del boton
     * "Aplicar Visitador" definido en el archivo FXML.</p>
     */
    @FXML
    private void aplicarVisitador() {
        ProductoVisitor visitor = resolverVisitadorSeleccionado();

        if (visitor == null) {
            areaResultados.setText("⚠  Seleccione una operacion antes de continuar.");
            lblResumen.setText("");
            return;
        }

        String encabezado = resolverEncabezado(visitor);
        StringBuilder sb  = new StringBuilder();

        sb.append(encabezado).append("\n");
        sb.append("═".repeat(90)).append("\n");

        for (Producto producto : productos) {
            sb.append("  ").append(producto.aceptar(visitor)).append("\n");
        }

        sb.append("═".repeat(90)).append("\n");
        sb.append("  Total de productos procesados: ").append(productos.size());

        areaResultados.setText(sb.toString());
        lblResumen.setText("✔  Visitador aplicado correctamente a "
                + productos.size() + " productos.");
    }

    /**
     * retorna la instancia del visitador que corresponde al RadioButton
     * actualmente seleccionado en el ToggleGroup.
     *
     * @return visitador activo, o {@code null} si ningun boton esta seleccionado.
     */
    private ProductoVisitor resolverVisitadorSeleccionado() {
        Toggle seleccionado = grupoVisitador.getSelectedToggle();
        if (seleccionado == rbImpuestos) return new VisitadorImpuestos();
        if (seleccionado == rbEnvio)     return new VisitadorEnvio();
        if (seleccionado == rbDescuento) return new VisitadorDescuento();
        return null;
    }

    /**
     * retorna el titulo del reporte segun el tipo concreto del visitador recibido.
     *
     * @param visitor visitador cuyo encabezado se desea generar.
     * @return cadena con el titulo descriptivo del reporte.
     */
    private String resolverEncabezado(ProductoVisitor visitor) {
        if (visitor instanceof VisitadorImpuestos) return "📊  REPORTE DE IMPUESTOS — Depto. Contabilidad";
        if (visitor instanceof VisitadorEnvio)     return "🚚  REPORTE DE COSTOS DE ENVIO — Depto. Logistica";
        if (visitor instanceof VisitadorDescuento) return "🏷   REPORTE DE DESCUENTOS — Depto. Marketing";
        return "REPORTE";
    }
}