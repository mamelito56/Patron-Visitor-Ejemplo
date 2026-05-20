package model;

import visitor.ProductoVisitor;

/**
 * Producto electronico. Aporta marca, peso y voltaje a los visitadores.
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class Electronico extends Producto {

    private final String marca;

    private final double pesoKg;

    private final double voltaje;

    /**
     * @param nombre  nombre del producto.
     * @param precio  precio base en pesos colombianos.
     * @param marca   fabricante del equipo.
     * @param pesoKg  peso en kilogramos.
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
     * @return nombre de la marca.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * retorna el peso del producto en kilogramos.
     * @return peso en kg.
     */
    public double getPesoKg() {
        return pesoKg;
    }

    /**
     * retorna el voltaje de operacion del producto.
     * @return voltaje en voltios.
     */
    public double getVoltaje() {
        return voltaje;
    }

    /**
     * acepta un visitador y delega la operacion.
     * @param visitor visitador que ejecutara la operacion.
     * @return resultado de la operacion del visitador.
     */
    @Override
    public String aceptar(ProductoVisitor visitor) {
        return visitor.visitar(this);
    }
}
