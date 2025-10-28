package com.mycompany.tp7_herenciapolimorfismo.figuras;

public abstract class Figura {
    protected String nombre;

    public Figura(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Método abstracto (obliga a las subclases a implementarlo)
    public abstract double calcularArea();

    public void mostrarInfo() {
        System.out.println("Figura: " + nombre + " | Área: " + calcularArea());
    }
}
