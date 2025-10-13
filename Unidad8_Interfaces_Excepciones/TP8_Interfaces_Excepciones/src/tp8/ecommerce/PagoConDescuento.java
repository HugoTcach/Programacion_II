package tp8.ecommerce;

/**
 * Esta interfaz extiende a 'Pago'.
 * Hereda todos sus métodos y añade la capacidad de aplicar descuentos.
 */
public interface PagoConDescuento extends Pago {

    // Este es un nuevo método específico de esta interfaz.
    // Debe aplicar un descuento a un monto y devolver el nuevo total.
    double aplicarDescuento(double monto);

}