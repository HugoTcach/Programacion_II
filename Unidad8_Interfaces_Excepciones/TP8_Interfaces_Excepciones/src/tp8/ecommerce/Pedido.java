package tp8.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements Pagable {

    private List<Producto> productos = new ArrayList<>();

    // --- NUEVOS ATRIBUTOS ---
    private Notificable cliente; // El pedido ahora tiene un cliente que puede ser notificado.
    private String estado;       // Para guardar el estado actual del pedido.

    // --- CONSTRUCTOR MODIFICADO ---
    // Ahora, para crear un pedido, es obligatorio asociarle un cliente.
    public Pedido(Notificable cliente) {
        this.cliente = cliente;
        this.estado = "Procesando"; // Estado inicial por defecto.
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    // --- NUEVO MÉTODO ---
    // Método para cambiar el estado del pedido y notificar al cliente.
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.println("El estado del pedido ha cambiado a: " + this.estado);

        // Si el cliente no es nulo, le enviamos la notificación.
        if (this.cliente != null) {
            this.cliente.notificar("El estado de su pedido fue actualizado a: " + this.estado);
        }
    }

    @Override
    public double calcularTotal() {
        double total = 0.0;
        for (Producto prod : this.productos) {
            total += prod.getPrecio();
        }
        return total;
    }
}