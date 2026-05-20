package model;

import visitor.ProductoVisitor;

/**
 * Producto de tipo libro. Aporta autor, numero de paginas y peso a los visitadores.
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class Libro extends Producto {

    private final String autor;

    private final int numeroPaginas;

    private final double pesoKg;

    /**
     * construye un libro con todos sus atributos.
     * @param nombre        titulo del libro.
     * @param precio        precio base en pesos colombianos.
     * @param autor         nombre completo del autor.
     * @param numeroPaginas cantidad total de paginas.
     * @param pesoKg        peso del libro en kilogramos.
     */
    public Libro(String nombre, double precio, String autor,
                 int numeroPaginas, double pesoKg) {
        super(nombre, precio);
        this.autor        = autor;
        this.numeroPaginas = numeroPaginas;
        this.pesoKg       = pesoKg;
    }

    /**
     * retorna el nombre del autor del libro.
     * @return autor del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * retorna la cantidad de paginas del libro.
     * @return numero de paginas.
     */
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
     * retorna el peso del libro en kilogramos.
     * @return peso en kg.
     */
    public double getPesoKg() {
        return pesoKg;
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
