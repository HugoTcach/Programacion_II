package tp8.ecommerce;

/**
 * Define el contrato para cualquier método de pago.
 * Obliga a implementar un método para procesar el pago.
 */
public interface Pago {

    // Método para procesar un pago por un monto determinado.
    // Devuelve 'true' si el pago fue exitoso, 'false' en caso contrario.
    boolean procesarPago(double monto);

}