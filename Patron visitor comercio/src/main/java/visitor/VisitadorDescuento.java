package visitor;

import model.Electronico;
import model.Libro;
import model.Ropa;

/**
 * visitador del departamento de Marketing que aplica descuentos promocionales
 * diferenciados segun el tipo de producto y la politica comercial vigente.
 *
 * <p>descuentos actuales por tipo:
 * <ul>
 *   <li>Libro       → 15 % (campana "Fomento a la Lectura")</li>
 *   <li>Electronico →  8 % (promocion "Tecnologia para Todos")</li>
 *   <li>Ropa        → 20 % (liquidacion de temporada)</li>
 * </ul>
 * </p>
 *
 * <p>modificar los porcentajes o agregar una nueva categoria no requiere
 * cambiar ningun producto existente (principio abierto/cerrado - SOLID).</p>
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class VisitadorDescuento implements ProductoVisitor {

    private static final double DESC_LIBRO       = 0.15;

    private static final double DESC_ELECTRONICO = 0.08;

    private static final double DESC_ROPA        = 0.20;

    /**
     * aplica el descuento del 15 % a un libro por la campana de lectura.
     *
     * @param libro libro sobre el que se aplica el descuento.
     * @return cadena con el ahorro obtenido y el precio final con descuento.
     */
    @Override
    public String visitar(Libro libro) {
        double ahorro      = libro.getPrecio() * DESC_LIBRO;
        double precioFinal = libro.getPrecio() - ahorro;
        return String.format(
                "%-30s | Precio: $%,8.0f | Desc. 15%%: -$%,7.0f | Final: $%,10.0f",
                libro.getNombre(), libro.getPrecio(), ahorro, precioFinal);
    }

    /**
     * aplica el descuento del 8 % a un electronico por la promocion tecnologica.
     *
     * @param electronico producto electronico sobre el que se aplica el descuento.
     * @return cadena con el ahorro obtenido y el precio final con descuento.
     */
    @Override
    public String visitar(Electronico electronico) {
        double ahorro      = electronico.getPrecio() * DESC_ELECTRONICO;
        double precioFinal = electronico.getPrecio() - ahorro;
        return String.format(
                "%-30s | Precio: $%,8.0f | Desc.  8%%: -$%,7.0f | Final: $%,10.0f",
                electronico.getNombre(), electronico.getPrecio(), ahorro, precioFinal);
    }

    /**
     * aplica el descuento del 20 % a una prenda de ropa por liquidacion de temporada.
     *
     * @param ropa articulo de ropa sobre el que se aplica el descuento.
     * @return cadena con el ahorro obtenido y el precio final con descuento.
     */
    @Override
    public String visitar(Ropa ropa) {
        double ahorro      = ropa.getPrecio() * DESC_ROPA;
        double precioFinal = ropa.getPrecio() - ahorro;
        return String.format(
                "%-30s | Precio: $%,8.0f | Desc. 20%%: -$%,7.0f | Final: $%,10.0f",
                ropa.getNombre(), ropa.getPrecio(), ahorro, precioFinal);
    }
}
