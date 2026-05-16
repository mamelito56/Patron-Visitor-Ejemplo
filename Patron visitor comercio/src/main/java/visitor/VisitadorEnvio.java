package visitor;

import model.Electronico;
import model.Libro;
import model.Ropa;

/**
 * visitador del departamento de Logistica que determina el costo de envio
 * basandose en el peso y las caracteristicas propias de cada tipo de producto.
 *
 * <p>tarifas base por tipo:
 * <ul>
 *   <li>Libro       → $ 3.000 / kg  (envio estandar en sobre acolchado)</li>
 *   <li>Electronico → $ 8.000 / kg  + $ 5.000 cargo por manejo especial</li>
 *   <li>Ropa        → $ 2.500 / kg  (empaque en bolsa ligera)</li>
 * </ul>
 * </p>
 *
 * @author Samuel Marin Varon
 * @version 1.0
 */
public class VisitadorEnvio implements ProductoVisitor {

    private static final double TARIFA_LIBRO_KG          = 3_000.0;

    private static final double TARIFA_ELECTRONICO_KG    = 8_000.0;

    private static final double CARGO_MANEJO_ELECTRONICO = 5_000.0;

    private static final double TARIFA_ROPA_KG           = 2_500.0;

    /**
     * calcula el costo de envio de un libro segun su peso en kilogramos.
     *
     * @param libro libro del que se determina el flete.
     * @return cadena con peso, tarifa aplicada y costo total de envio.
     */
    @Override
    public String visitar(Libro libro) {
        double costo = libro.getPesoKg() * TARIFA_LIBRO_KG;
        return String.format(
                "%-30s | Peso: %4.2f kg | Tarifa: $3.000/kg | Flete: $%,8.0f",
                libro.getNombre(), libro.getPesoKg(), costo);
    }

    /**
     * calcula el costo de envio de un electronico incluyendo el cargo
     * fijo por manejo especial (embalaje antiestatico y seguro de transporte).
     *
     * @param electronico producto electronico del que se determina el flete.
     * @return cadena con peso, tarifas y costo total de envio con manejo especial.
     */
    @Override
    public String visitar(Electronico electronico) {
        double costo = (electronico.getPesoKg() * TARIFA_ELECTRONICO_KG) + CARGO_MANEJO_ELECTRONICO;
        return String.format(
                "%-30s | Peso: %4.2f kg | Tarifa: $8.000/kg + manejo | Flete: $%,8.0f",
                electronico.getNombre(), electronico.getPesoKg(), costo);
    }

    /**
     * calcula el costo de envio de una prenda de ropa segun su peso en kilogramos.
     *
     * @param ropa articulo de ropa del que se determina el flete.
     * @return cadena con peso, tarifa aplicada y costo total de envio.
     */
    @Override
    public String visitar(Ropa ropa) {
        double costo = ropa.getPesoKg() * TARIFA_ROPA_KG;
        return String.format(
                "%-30s | Peso: %4.2f kg | Tarifa: $2.500/kg | Flete: $%,8.0f",
                ropa.getNombre(), ropa.getPesoKg(), costo);
    }
}
