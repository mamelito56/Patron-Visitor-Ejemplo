package model;

import visitor.ProductoVisitor;

/**
 * representa un producto electronico dentro del catalogo del comercio electronico.
 *
 * <p>extiende {@link Producto} aportando marca, peso y voltaje como atributos
 * propios que los visitadores de impuestos, envios y descuentos utilizan
 * en sus calculos de negocio. la clase permanece cerrada a modificaciones
 * ante nuevos comportamientos (principio abierto/cerrado - SOLID).</p>
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class Electronico extends Producto {

    /** fabricante o marca del producto electronico. */
    private final String marca;

    /** peso del equipo en kilogramos, requerido por el visitador de envios. */
    private final double pesoKg;

    /** voltaje de operacion en voltios (p. ej. 110 V o 220 V). */
    private final double voltaje;

    /**
     * construye un producto electronico con todos sus atributos.
     *
     * @param nombre  nombre del producto electronico.
     * @param precio  precio base en pesos colombianos.
     * @param marca   fabricante o marca del equipo.
     * @param pesoKg  peso del equipo en kilogramos.
     * @param voltaje voltaje de operacion en voltios.
     */
    public Electronico(String nombre, double precio, String marca,
                       double pesoKg, double voltaje) {
        super(nombre, precio);
        this.marca   = marca;
        this.pesoKg  = pesoKg;
        this.voltaje = voltaje;
    }

    /**
     * retorna la marca del fabricante del producto.
     *
     * @return nombre de la marca.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * retorna el peso del producto en kilogramos.
     *
     * @return peso en kg.
     */
    public double getPesoKg() {
        return pesoKg;
    }

    /**
     * retorna el voltaje de operacion del producto.
     *
     * @return voltaje en voltios.
     */
    public double getVoltaje() {
        return voltaje;
    }

    /**
     * acepta un visitador y delega la operacion invocando
     * {@code visitor.visitar(this)} con el tipo concreto {@link Electronico}.
     *
     * @param visitor visitador que ejecutara la operacion.
     * @return resultado de la operacion del visitador.
     */
    @Override
    public String aceptar(ProductoVisitor visitor) {
        return visitor.visitar(this);
    }
}
