package model;

import visitor.ProductoVisitor;

/**
 * representa un articulo de ropa dentro del catalogo del comercio electronico.
 *
 * <p>extiende {@link Producto} aportando talla, material y peso como atributos
 * propios. los visitadores utilizan esta informacion para calcular impuestos,
 * costos de envio y descuentos de temporada sin necesidad de alterar esta clase.</p>
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class Ropa extends Producto {

    private final String talla;

    private final String material;

    private final double pesoKg;

    /**
     * construye un articulo de ropa con todos sus atributos.
     *
     * @param nombre   nombre del articulo de ropa.
     * @param precio   precio base en pesos colombianos.
     * @param talla    talla del articulo (XS, S, M, L, XL).
     * @param material composicion del tejido o material.
     * @param pesoKg   peso del articulo en kilogramos.
     */
    public Ropa(String nombre, double precio, String talla,
                String material, double pesoKg) {
        super(nombre, precio);
        this.talla    = talla;
        this.material = material;
        this.pesoKg   = pesoKg;
    }

    /**
     * retorna la talla del articulo de ropa.
     *
     * @return talla del articulo.
     */
    public String getTalla() {
        return talla;
    }

    /**
     * retorna el material o composicion del tejido.
     *
     * @return material del articulo.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * retorna el peso del articulo en kilogramos.
     *
     * @return peso en kg.
     */
    public double getPesoKg() {
        return pesoKg;
    }

    /**
     * acepta un visitador y delega la operacion invocando
     * {@code visitor.visitar(this)} con el tipo concreto {@link Ropa}.
     *
     * @param visitor visitador que ejecutara la operacion.
     * @return resultado de la operacion del visitador.
     */
    @Override
    public String aceptar(ProductoVisitor visitor) {
        return visitor.visitar(this);
    }
}
