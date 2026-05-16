package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * catalogo centralizado de productos del comercio electronico.
 *
 * <p>implementa el patron Singleton para garantizar una unica instancia
 * del catalogo durante toda la ejecucion. los productos de muestra se
 * cargan una sola vez en el constructor privado, siguiendo el principio
 * de responsabilidad unica (SOLID): esta clase solo gestiona el catalogo.</p>
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class CatalogoProductos {

    private static CatalogoProductos instancia;

    private final List<Producto> productos;

    /**
     * constructor privado que inicializa el catalogo con productos de muestra.
     * no puede instanciarse desde el exterior.
     */
    private CatalogoProductos() {
        productos = new ArrayList<>();
        cargarProductosDeMuestra();
    }

    /**
     * retorna la instancia compartida del catalogo de productos.
     *
     * <p>si no existe, la crea; de lo contrario reutiliza la existente.</p>
     *
     * @return instancia unica de {@link CatalogoProductos}.
     */
    public static CatalogoProductos getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoProductos();
        }
        return instancia;
    }

    /**
     * carga el conjunto de productos de muestra en el catalogo.
     *
     * <p>los datos estan fijos en codigo para facilitar la demostracion
     * del patron Visitor sin depender de una fuente de datos externa.</p>
     */
    private void cargarProductosDeMuestra() {
        productos.add(new Libro(
                "Clean Code", 89_000, "Robert C. Martin", 431, 0.45));
        productos.add(new Libro(
                "El Principito", 25_000, "Antoine de Saint-Exupery", 96, 0.15));
        productos.add(new Libro(
                "Design Patterns (GoF)", 145_000, "Gang of Four", 395, 0.60));

        productos.add(new Electronico(
                "MacBook Pro 14\"", 9_500_000, "Apple", 1.60, 110));
        productos.add(new Electronico(
                "Samsung Galaxy S24", 4_200_000, "Samsung", 0.167, 5));
        productos.add(new Electronico(
                "Sony WH-1000XM5", 1_350_000, "Sony", 0.250, 5));

        productos.add(new Ropa(
                "Camiseta Polo Classic", 75_000, "M", "Algodon 100%", 0.25));
        productos.add(new Ropa(
                "Chaqueta de Cuero", 450_000, "L", "Cuero genuino", 1.20));
        productos.add(new Ropa(
                "Zapatillas Running", 320_000, "42", "Malla sintetica", 0.55));
    }

    /**
     * retorna la lista de productos del catalogo de forma inmutable.
     *
     * <p>usar una lista no modificable protege la integridad del catalogo
     * frente a modificaciones externas accidentales.</p>
     *
     * @return lista no modificable de {@link Producto}.
     */
    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }
}
