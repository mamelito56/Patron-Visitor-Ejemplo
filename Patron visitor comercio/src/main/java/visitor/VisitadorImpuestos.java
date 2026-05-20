package visitor;

import model.Electronico;
import model.Libro;
import model.Ropa;

/**
 * Calcula el IVA de cada producto segun su categoria: Libro 5%, Electronico 19%, Ropa 12%.
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
     * @param libro libro sobre el que se calcula el impuesto.
     * @return cadena con precio base, valor del IVA y total a pagar.
     */
    @Override
    public String visitar(Libro libro) {
        double impuesto = libro.getPrecio() * TASA_LIBRO;
        double total    = libro.getPrecio() + impuesto;
        return String.format("%-30s | Base: $%,10.0f | IVA  5%%: $%,8.0f | Total: $%,10.0f",
                libro.getNombre(), libro.getPrecio(), impuesto, total);
    }

    /**
     * calcula el impuesto de un electronico aplicando la tarifa general del 19 %.
     * @param electronico producto electronico sobre el que se calcula el impuesto.
     * @return cadena con precio base, valor del IVA y total a pagar.
     */
    @Override
    public String visitar(Electronico electronico) {
        double impuesto = electronico.getPrecio() * TASA_ELECTRONICO;
        double total    = electronico.getPrecio() + impuesto;
        return String.format("%-30s | Base: $%,10.0f | IVA 19%%: $%,8.0f | Total: $%,10.0f",
                electronico.getNombre(), electronico.getPrecio(), impuesto, total);
    }

    /**
     * calcula el impuesto de una prenda de ropa aplicando la tasa reducida del 12 %.
     * @param ropa articulo de ropa sobre el que se calcula el impuesto.
     * @return cadena con precio base, valor del IVA y total a pagar.
     */
    @Override
    public String visitar(Ropa ropa) {
        double impuesto = ropa.getPrecio() * TASA_ROPA;
        double total    = ropa.getPrecio() + impuesto;
        return String.format("%-30s | Base: $%,10.0f | IVA 12%%: $%,8.0f | Total: $%,10.0f",
                ropa.getNombre(), ropa.getPrecio(), impuesto, total);
    }
}
