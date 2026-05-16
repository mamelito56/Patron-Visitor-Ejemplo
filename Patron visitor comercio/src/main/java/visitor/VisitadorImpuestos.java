package visitor;

import model.Electronico;
import model.Libro;
import model.Ropa;

/**
 * visitador del departamento de Contabilidad que calcula el impuesto
 * aplicable segun el tipo concreto de cada producto.
 *
 * <p>tarifas de IVA vigentes:
 * <ul>
 *   <li>Libro       →  5 % (bien cultural con exencion parcial)</li>
 *   <li>Electronico → 19 % (tarifa general de IVA)</li>
 *   <li>Ropa        → 12 % (tarifa reducida de IVA)</li>
 * </ul>
 * </p>
 *
 * <p>para agregar un nuevo tipo de impuesto basta con crear otra implementacion
 * de {@link ProductoVisitor}; esta clase no necesita modificarse (OCP - SOLID).</p>
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class VisitadorImpuestos implements ProductoVisitor {

    private static final double TASA_LIBRO       = 0.05;

    private static final double TASA_ELECTRONICO = 0.19;

    private static final double TASA_ROPA        = 0.12;

    /**
     * calcula el impuesto de un libro aplicando la tasa reducida del 5 %.
     *
     * @param libro libro sobre el que se calcula el impuesto.
     * @return cadena con precio base, valor del IVA y total a pagar.
     */
    @Override
    public String visitar(Libro libro) {
        double impuesto = libro.getPrecio() * TASA_LIBRO;
        double total    = libro.getPrecio() + impuesto;
        return String.format(
                "%-30s | Base: $%,10.0f | IVA  5%%: $%,8.0f | Total: $%,10.0f",
                libro.getNombre(), libro.getPrecio(), impuesto, total);
    }

    /**
     * calcula el impuesto de un electronico aplicando la tarifa general del 19 %.
     *
     * @param electronico producto electronico sobre el que se calcula el impuesto.
     * @return cadena con precio base, valor del IVA y total a pagar.
     */
    @Override
    public String visitar(Electronico electronico) {
        double impuesto = electronico.getPrecio() * TASA_ELECTRONICO;
        double total    = electronico.getPrecio() + impuesto;
        return String.format(
                "%-30s | Base: $%,10.0f | IVA 19%%: $%,8.0f | Total: $%,10.0f",
                electronico.getNombre(), electronico.getPrecio(), impuesto, total);
    }

    /**
     * calcula el impuesto de una prenda de ropa aplicando la tasa reducida del 12 %.
     *
     * @param ropa articulo de ropa sobre el que se calcula el impuesto.
     * @return cadena con precio base, valor del IVA y total a pagar.
     */
    @Override
    public String visitar(Ropa ropa) {
        double impuesto = ropa.getPrecio() * TASA_ROPA;
        double total    = ropa.getPrecio() + impuesto;
        return String.format(
                "%-30s | Base: $%,10.0f | IVA 12%%: $%,8.0f | Total: $%,10.0f",
                ropa.getNombre(), ropa.getPrecio(), impuesto, total);
    }
}
