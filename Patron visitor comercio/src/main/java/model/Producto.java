package model;

import visitor.ProductoVisitable;
import visitor.ProductoVisitor;

/**
 * clase base abstracta para todos los productos del comercio electronico.
 *
 * <p>centraliza los atributos comunes (nombre y precio) y obliga a cada
 * subclase a implementar el metodo {@code aceptar} del patron Visitor,
 * garantizando el principio de sustitucion de Liskov (SOLID).</p>
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public abstract class Producto implements ProductoVisitable {

    /** nombre descriptivo del producto. */
    private final String nombre;

    /** precio base en pesos colombianos. */
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
     *
     * @return nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * retorna el precio base del producto.
     *
     * @return precio en pesos colombianos.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * delega la ejecucion de la operacion al visitador recibido
     * invocando el metodo {@code visitar} con el tipo concreto correcto.
     *
     * @param visitor visitador que realizara la operacion.
     * @return resultado de la operacion del visitador.
     */
    @Override
    public abstract String aceptar(ProductoVisitor visitor);

    /**
     * retorna una representacion textual del producto con nombre y precio.
     *
     * @return cadena con formato "nombre - $precio".
     */
    @Override
    public String toString() {
        return nombre + " - $" + String.format("%.0f", precio);
    }
}
