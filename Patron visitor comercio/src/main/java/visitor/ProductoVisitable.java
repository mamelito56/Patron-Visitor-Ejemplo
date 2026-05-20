package visitor;

/**
 * Contrato que deben cumplir los productos para aceptar un visitador (double-dispatch).
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public interface ProductoVisitable {

    /**
     * Acepta un visitador y le delega la operacion segun el tipo concreto del producto.
     * @param visitor visitador a ejecutar.
     * @return resultado de la operacion.
     */
    String aceptar(ProductoVisitor visitor);
}
