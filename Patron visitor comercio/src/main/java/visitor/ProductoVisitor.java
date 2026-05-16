package visitor;

import model.Electronico;
import model.Libro;
import model.Ropa;

/**
 * contrato base del patron Visitor para la jerarquia de productos
 * del sistema de comercio electronico.
 *
 * <p>cada metodo {@code visitar} corresponde a un tipo concreto de producto.
 * agregar un nuevo comportamiento significa crear una nueva implementacion
 * de esta interfaz sin tocar ningun producto existente
 * (principio abierto/cerrado - SOLID).</p>
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public interface ProductoVisitor {

    /**
     * ejecuta la operacion definida por el visitador sobre un {@link Libro}.
     *
     * @param libro producto de tipo libro a procesar.
     * @return cadena con el resultado legible de la operacion.
     */
    String visitar(Libro libro);

    /**
     * ejecuta la operacion definida por el visitador sobre un {@link Electronico}.
     *
     * @param electronico producto electronico a procesar.
     * @return cadena con el resultado legible de la operacion.
     */
    String visitar(Electronico electronico);

    /**
     * ejecuta la operacion definida por el visitador sobre una {@link Ropa}.
     *
     * @param ropa articulo de ropa a procesar.
     * @return cadena con el resultado legible de la operacion.
     */
    String visitar(Ropa ropa);
}
