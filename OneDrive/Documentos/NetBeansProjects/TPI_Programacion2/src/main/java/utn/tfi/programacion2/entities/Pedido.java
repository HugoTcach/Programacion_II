package utn.tfi.programacion2.entities;

import java.time.LocalDate;

/**
 * Clase A (Pedido)
 */
public class Pedido {

    // Enum según PDF
    public enum EstadoPedido { NUEVO, FACTURADO, ENVIADO }

    private Long id;
    private boolean eliminado; // Requisito de baja lógica
    private String numero;
    private LocalDate fecha;
    private String clienteNombre;
    private double total;
    private EstadoPedido estado;

    /**
     * RELACIÓN 1-1 UNIDIRECCIONAL (A -> B)
     * A (Pedido) contiene una referencia a B (Envio)
     [cite_start]* [cite: 204]
     */
    private Envio envio;

    // Constructor vacío (obligatorio)
    public Pedido() {}

    // Constructor completo (útil para pruebas)
    public Pedido(Long id, boolean eliminado, String numero, LocalDate fecha, String clienteNombre, double total, EstadoPedido estado, Envio envio) {
        this.id = id;
        this.eliminado = eliminado;
        this.numero = numero;
        this.fecha = fecha;
        this.clienteNombre = clienteNombre;
        this.total = total;
        this.estado = estado;
        this.envio = envio; // Asociación 1-a-1
    }

    // --- Getters y Setters ---
    // (Requeridos por las consignas)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    // Getter y Setter para la relación
    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    // toString() legible (requerido)
    @Override
    public String toString() {
        // Comprobamos si el envío es nulo para evitar NullPointerException
        String envioTracking = (this.envio != null) ? this.envio.getTracking() : "N/A";

        return "Pedido{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", clienteNombre='" + clienteNombre + '\'' +
                ", estado=" + estado +
                ", envioTracking=" + envioTracking +
                '}';
    }
}