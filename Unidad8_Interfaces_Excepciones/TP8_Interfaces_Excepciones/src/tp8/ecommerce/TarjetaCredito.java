package tp8.ecommerce;

// Esta clase implementa la interfaz más específica.
// Al hacerlo, se compromete a implementar los métodos de PagoConDescuento
// y también los métodos de la interfaz padre, Pago.
public class TarjetaCredito implements PagoConDescuento {

    // Atributo para el descuento (5% en este caso)
    private final double PORCENTAJE_DESCUENTO = 0.05;

    // Implementación del método heredado de la interfaz Pago.
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago de $" + monto + " con Tarjeta de Crédito...");
        // Aquí iría la lógica real para conectarse a un servicio de pago.
        // Para nuestro ejemplo, simulamos que siempre es exitoso.
        System.out.println("¡Pago con Tarjeta de Crédito aprobado!");
        return true;
    }

    // Implementación del método propio de la interfaz PagoConDescuento.
    @Override
    public double aplicarDescuento(double monto) {
        double descuento = monto * PORCENTAJE_DESCUENTO;
        double montoFinal = monto - descuento;
        System.out.println("Aplicando descuento del 5% (-$" + descuento + "). Nuevo total: $" + montoFinal);
        return montoFinal;
    }
}