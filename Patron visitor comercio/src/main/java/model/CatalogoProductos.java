package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Catalogo centralizado de productos. Singleton: una sola instancia durante toda la ejecucion.
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class CatalogoProductos {

    private static CatalogoProductos instancia;

    private final List<Producto> productos;

    /** Constructor privado. Carga los productos de muestra al inicializar. */
    private CatalogoProductos() {
        productos = new ArrayList<>();
        cargarProductosDeMuestra();
    }
    /** Agrega al catalogo los productos fijos usados para la demostracion del patron Visitor. */
    private void cargarProductosDeMuestra() {
        productos.add(new Libro("Clean Code", 89_000, "Robert C. Martin", 431, 0.45));
        productos.add(new Libro("El Principito", 25_000, "Antoine de Saint-Exupery", 96, 0.15));
        productos.add(new Libro("Design Patterns (GoF)", 145_000, "Gang of Four", 395, 0.60));

        productos.add(new Electronico("MacBook Pro 14\"", 9_500_000, "Apple", 1.60, 110));
        productos.add(new Electronico("Samsung Galaxy S24", 4_200_000, "Samsung", 0.167, 5));
        productos.add(new Electronico("Sony WH-1000XM5", 1_350_000, "Sony", 0.250, 5));

        productos.add(new Ropa("Camiseta Polo Classic", 75_000, "M", "Algodon 100%", 0.25));
        productos.add(new Ropa("Chaqueta de Cuero", 450_000, "L", "Cuero genuino", 1.20));
        productos.add(new Ropa("Zapatillas Running", 320_000, "42", "Malla sintetica", 0.55));
    }
    /**
     * Retorna la unica instancia del catalogo, creandola si aun no existe.
     *
     * @return instancia compartida de {@link CatalogoProductos}.
     */
    public static CatalogoProductos getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoProductos();
        }
        return instancia;
    }

    /**
     * Retorna la lista de productos como coleccion no modificable.
     *
     * @return lista inmutable de {@link Producto}.
     */
    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }
}
