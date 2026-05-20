import model.Libro;
import model.Electronico;
import model.Ropa;
import visitor.VisitadorImpuestos;
import visitor.VisitadorEnvio;
import visitor.VisitadorDescuento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias del patrón Visitor sobre el sistema E-Commerce.
 *
 * Cubre los tres visitors concretos (Impuestos, Envio, Descuento)
 * sobre cada tipo de producto y el doble despacho.
 */
@DisplayName("Patrón Visitor — Pruebas del sistema E-Commerce")
class VisitorTest {

    private Libro        libroCleanCode;
    private Electronico  electronicoMacBook;
    private Ropa         ropaChaqueta;

    private VisitadorImpuestos visitadorImpuestos;
    private VisitadorEnvio     visitadorEnvio;
    private VisitadorDescuento visitadorDescuento;

    /**
     * Inicializa productos y visitadores antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        libroCleanCode     = new Libro("Clean Code", 89_000, "Robert C. Martin", 431, 0.45);
        electronicoMacBook = new Electronico("MacBook Pro", 9_500_000, "Apple", 1.6, 110);
        ropaChaqueta       = new Ropa("Chaqueta de Cuero", 450_000, "L", "Cuero", 1.2);

        visitadorImpuestos = new VisitadorImpuestos();
        visitadorEnvio     = new VisitadorEnvio();
        visitadorDescuento = new VisitadorDescuento();
    }

    /**
     * Libro "Clean Code", $89.000 · IVA 5% = $4.450, total $93.450.
     */
    @Test
    @DisplayName("1.1 · VisitadorImpuestos → Libro: IVA 5% correcto")
    void assertEqualsImpuestoLibro() {
        String resultado = libroCleanCode.aceptar(visitadorImpuestos);

        assertTrue(resultado.contains("4.450"),  "El IVA del libro debe ser $4.450 (5% de $89.000)");
        assertTrue(resultado.contains("93.450"), "El total con IVA del libro debe ser $93.450");
    }

    /**
     * Electronico "MacBook Pro", $9.500.000 · IVA 19% = $1.805.000, total $11.305.000.
     */
    @Test
    @DisplayName("1.2 · VisitadorImpuestos → Electronico: IVA 19% correcto")
    void assertEqualsImpuestoElectronico() {
        String resultado = electronicoMacBook.aceptar(visitadorImpuestos);

        assertTrue(resultado.contains("1.805.000"),  "El IVA del electrónico debe ser $1.805.000 (19% de $9.500.000)");
        assertTrue(resultado.contains("11.305.000"), "El total con IVA del electrónico debe ser $11.305.000");
    }

    /**
     * Ropa "Chaqueta de Cuero", $450.000 · IVA 12% = $54.000, total $504.000.
     */
    @Test
    @DisplayName("1.3 · VisitadorImpuestos → Ropa: IVA 12% correcto")
    void assertEqualsImpuestoRopa() {
        String resultado = ropaChaqueta.aceptar(visitadorImpuestos);

        assertTrue(resultado.contains("54.000"),  "El IVA de la ropa debe ser $54.000 (12% de $450.000)");
        assertTrue(resultado.contains("504.000"), "El total con IVA de la ropa debe ser $504.000");
    }

    /**
     * Libro "Clean Code", 0,45 kg · flete = 0,45 × $3.000 = $1.350.
     */
    @Test
    @DisplayName("2.1 · VisitadorEnvio → Libro: tarifa $3.000/kg correcta")
    void assertEqualsEnvioLibro() {
        String resultado = libroCleanCode.aceptar(visitadorEnvio);

        assertTrue(resultado.contains("1.350"), "El flete del libro debe ser $1.350 (0,45 kg × $3.000)");
    }

    /**
     * Electronico "MacBook Pro", 1,6 kg · flete = (1,6 × $8.000) + $5.000 manejo = $17.800.
     */
    @Test
    @DisplayName("2.2 · VisitadorEnvio → Electronico: $8.000/kg + $5.000 manejo correctos")
    void assertEqualsEnvioElectronico() {
        String resultado = electronicoMacBook.aceptar(visitadorEnvio);

        assertTrue(resultado.contains("17.800"), "El flete del electrónico debe ser $17.800 (1,6 kg × $8.000 + $5.000 manejo)");
    }

    /**
     * Ropa "Chaqueta de Cuero", 1,2 kg · flete = 1,2 × $2.500 = $3.000.
     */
    @Test
    @DisplayName("2.3 · VisitadorEnvio → Ropa: tarifa $2.500/kg correcta")
    void assertEqualsEnvioRopa() {
        String resultado = ropaChaqueta.aceptar(visitadorEnvio);

        assertTrue(resultado.contains("3.000"), "El flete de la ropa debe ser $3.000 (1,2 kg × $2.500)");
    }

    /**
     * Libro "Clean Code", $89.000 · descuento 15% = $13.350, precio final $75.650.
     */
    @Test
    @DisplayName("3.1 · VisitadorDescuento → Libro: 15% correcto")
    void assertEqualsDescuentoLibro() {
        String resultado = libroCleanCode.aceptar(visitadorDescuento);

        assertTrue(resultado.contains("13.350"), "El ahorro del libro debe ser $13.350 (15% de $89.000)");
        assertTrue(resultado.contains("75.650"), "El precio final del libro debe ser $75.650");
    }

    /**
     * Electronico "MacBook Pro", $9.500.000 · descuento 8% = $760.000, precio final $8.740.000.
     */
    @Test
    @DisplayName("3.2 · VisitadorDescuento → Electronico: 8% correcto")
    void assertEqualsDescuentoElectronico() {
        String resultado = electronicoMacBook.aceptar(visitadorDescuento);

        assertTrue(resultado.contains("760.000"),   "El ahorro del electrónico debe ser $760.000 (8% de $9.500.000)");
        assertTrue(resultado.contains("8.740.000"), "El precio final del electrónico debe ser $8.740.000");
    }

    /**
     * Ropa "Chaqueta de Cuero", $450.000 · descuento 20% = $90.000, precio final $360.000.
     */
    @Test
    @DisplayName("3.3 · VisitadorDescuento → Ropa: 20% correcto")
    void assertEqualsDescuentoRopa() {
        String resultado = ropaChaqueta.aceptar(visitadorDescuento);

        assertTrue(resultado.contains("90.000"),  "El ahorro de la ropa debe ser $90.000 (20% de $450.000)");
        assertTrue(resultado.contains("360.000"), "El precio final de la ropa debe ser $360.000");
    }

    /**
     * El mismo visitor aplicado a tres tipos distintos retorna resultados no nulos y diferentes entre sí.
     */
    @Test
    @DisplayName("4.1 · Doble despacho → tres tipos, tres resultados distintos")
    void assertDobleDespachoInvocarMetodoCorrecto() {
        String resLibro       = libroCleanCode.aceptar(visitadorImpuestos);
        String resElectronico = electronicoMacBook.aceptar(visitadorImpuestos);
        String resRopa        = ropaChaqueta.aceptar(visitadorImpuestos);

        assertNotNull(resLibro);
        assertNotNull(resElectronico);
        assertNotNull(resRopa);
        assertNotEquals(resLibro,       resElectronico, "Libro y Electrónico deben producir resultados distintos");
        assertNotEquals(resElectronico, resRopa,        "Electrónico y Ropa deben producir resultados distintos");
    }

    /**
     * El mismo producto visitado por los tres visitors retorna resultados independientes entre sí.
     */
    @Test
    @DisplayName("4.2 · Independencia de Visitors → mismo producto, Visitors distintos")
    void assertVisitorsIndependientes() {
        String resImpuesto  = libroCleanCode.aceptar(visitadorImpuestos);
        String resEnvio     = libroCleanCode.aceptar(visitadorEnvio);
        String resDescuento = libroCleanCode.aceptar(visitadorDescuento);

        assertNotNull(resImpuesto);
        assertNotNull(resEnvio);
        assertNotNull(resDescuento);
        assertNotEquals(resImpuesto, resEnvio,     "Impuestos y Envio deben producir resultados distintos");
        assertNotEquals(resImpuesto, resDescuento, "Impuestos y Descuento deben producir resultados distintos");
    }
}