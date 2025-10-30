package utn.tfi.programacion2.entities;

import java.time.LocalDate;

/**
 * Clase B (Envio)
 */
public class Envio {
    
    // Enums según PDF
    public enum Empresa { ANDREANI, OCA, CORREO_ARG }
    public enum Tipo { ESTANDAR, EXPRES }
    public enum EstadoEnvio { EN_PREPARACION, EN_TRANSITO, ENTREGADO }

    private Long id;
    private boolean eliminado; // Requisito de baja lógica
    private String tracking;
    private Empresa empresa;
    private Tipo tipo;
    private double costo;
    private LocalDate fechaDespacho;
    private LocalDate fechaEstimada;
    private EstadoEnvio estado;

    // Constructor vacío (obligatorio)
    public Envio() {}

    // Constructor completo (útil para pruebas)
    public Envio(Long id, boolean eliminado, String tracking, Empresa empresa, Tipo tipo, double costo, LocalDate fechaDespacho, LocalDate fechaEstimada, EstadoEnvio estado) {
        this.id = id;
        this.eliminado = eliminado;
        this.tracking = tracking;
        this.empresa = empresa;
        this.tipo = tipo;
        this.costo = costo;
        this.fechaDespacho = fechaDespacho;
        this.fechaEstimada = fechaEstimada;
        this.estado = estado;
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

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public LocalDate getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(LocalDate fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public LocalDate getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(LocalDate fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

    // toString() legible (requerido)
    @Override
    public String toString() {
        return "Envio{" +
                "id=" + id +
                ", tracking='" + tracking + '\'' +
                ", empresa=" + empresa +
                ", estado=" + estado +
                ", eliminado=" + eliminado +
                '}';
    }
}