package com.mycompany.tp7_herenciapolimorfismo.animales;

public class Animal {
    protected String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Método que será sobrescrito
    public void hacerSonido() {
        System.out.println("Sonido genérico de animal");
    }

    // Método común
    public void describirAnimal() {
        System.out.println("Soy un animal llamado " + nombre);
    }
}
