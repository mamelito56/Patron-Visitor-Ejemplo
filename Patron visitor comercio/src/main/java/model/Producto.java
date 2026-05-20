package model;

import visitor.ProductoVisitable;
import visitor.ProductoVisitor;

/**
 * Clase base abstracta para todos los productos del catalogo.
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public abstract class Producto implements ProductoVisitable {

    private final String nombre;

    private final double precio;

    /**
     * inicializa los atributos comunes de todos los productos.
     *
     * @param nombre nombre descriptivo del producto.
     * @param precio precio base en pesos colombianos.
     */
    protected Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * retorna el nombre del producto.
     * @return nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * retorna el precio base del producto.
     * @return precio en pesos colombianos.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * delega la ejecucion de la operacion al visitador recibido.
     *
     * @param visitor visitador que realizara la operacion.
     * @return resultado de la operacion del visitador.
     */
    @Override
    public abstract String aceptar(ProductoVisitor visitor);

    /**
     * retorna una representacion textual del producto con nombre y precio.
     * @return cadena con formato "nombre - $precio".
     */
    @Override
    public String toString() {
        return nombre + " - $" + String.format("%.0f", precio);
    }
}
