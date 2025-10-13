package tp8.ecommerce;

public class MainECommerce {

    public static void main(String[] args) {

        // --- Parte 1: Creación del Cliente ---
        Cliente juanPerez = new Cliente("Juan Pérez");

        // --- Parte 2: Creación del Pedido ---
        // Al crear el pedido, ahora es obligatorio pasarle el cliente.
        Pedido miPedido = new Pedido(juanPerez);

        miPedido.agregarProducto(new Producto("Laptop Gamer XYZ", 1500.00));
        miPedido.agregarProducto(new Producto("Mouse Inalámbrico", 45.50));

        double totalPedido = miPedido.calcularTotal();
        System.out.println("El costo total del pedido es: $" + totalPedido);
        System.out.println("==============================================");

        // --- Parte 3: Simulación de cambio de estado y notificación ---

        // Cambiamos el estado del pedido. Esto debería disparar la notificación.
        miPedido.cambiarEstado("Enviado");

        System.out.println("----------------------------------------------");

        // Otro cambio de estado
        miPedido.cambiarEstado("Entregado");
    }
}