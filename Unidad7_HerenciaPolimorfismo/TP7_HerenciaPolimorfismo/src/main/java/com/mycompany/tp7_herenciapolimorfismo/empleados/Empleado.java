package com.mycompany.tp7_herenciapolimorfismo.empleados;

public abstract class Empleado {
    protected String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Método abstracto: cada subclase define su propio sueldo
    public abstract double calcularSueldo();
}
