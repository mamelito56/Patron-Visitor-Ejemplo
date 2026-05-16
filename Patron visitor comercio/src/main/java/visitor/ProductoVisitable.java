package visitor;

/**
 * contrato que deben implementar todos los elementos de la jerarquia
 * de productos para ser compatibles con el patron Visitor.
 *
 * <p>al separar la aceptacion del visitador en esta interfaz se garantiza
 * que los productos nunca necesitan conocer la logica de negocio de los
 * visitadores (principio de inversion de dependencias - SOLID).</p>
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public interface ProductoVisitable {

    /**
     * acepta un visitador y delega en el la ejecucion de la operacion
     * correspondiente segun el tipo concreto del producto (double-dispatch).
     *
     * @param visitor visitador que implementa la operacion a ejecutar.
     * @return resultado legible de la operacion realizada por el visitador.
     */
    String aceptar(ProductoVisitor visitor);
}
