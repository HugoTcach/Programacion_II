package tp8.ecommerce;

// Esta clase solo implementa la interfaz base 'Pago'.
// No está obligada a tener un método para aplicar descuentos.
public class PayPal implements Pago {

    // Implementación del método de la interfaz Pago.
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Iniciando sesión en PayPal para un pago de $" + monto + "...");
        // Simulación de un proceso de pago con PayPal.
        System.out.println("¡Pago con PayPal completado exitosamente!");
        return true;
    }
}