package com.mycompany.tp7_herenciapolimorfismo;

import com.mycompany.tp7_herenciapolimorfismo.vehiculos.*;
import com.mycompany.tp7_herenciapolimorfismo.figuras.*;
import com.mycompany.tp7_herenciapolimorfismo.empleados.*;
import com.mycompany.tp7_herenciapolimorfismo.animales.*;
import com.mycompany.tp7_herenciapolimorfismo.pagos.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // --- KATA 1: Vehículos ---
                System.out.println("=== KATA 1: Vehículos ===");
Auto auto1 = new Auto("Toyota", "Corolla", 4);
auto1.mostrarInfo();

// Ejemplo de Upcasting: tratamos el Auto como Vehiculo (upcasting implícito)
Vehiculo vehiculoUpcast = auto1;
System.out.println("\n--- Upcasting: Vehiculo general ---");
vehiculoUpcast.mostrarInfo();

// Ejemplo de Downcasting: comprobamos con instanceof y volvemos a Auto
if (vehiculoUpcast instanceof Auto) {
    Auto autoDowncast = (Auto) vehiculoUpcast; // downcasting explícito
    System.out.println("--- Downcasting: Auto específico ---");
    System.out.println("Cantidad de puertas (accedida desde getter): " + autoDowncast.getCantidadPuertas());
} else {
    System.out.println("No es un Auto, no se puede downcastear.");
}

// Ejemplo adicional: colección de Vehiculos (upcasting dentro de una colección)
System.out.println("\n--- Flota (array de Vehiculo) ---");
Vehiculo[] flota = new Vehiculo[2];
flota[0] = new Auto("Ford", "Focus", 5);
flota[1] = new Vehiculo("Honda", "CG125");

for (Vehiculo v : flota) {
    v.mostrarInfo();
    if (v instanceof Auto) {
        // downcasting para acceder al atributo específico
        System.out.println("=> Es un Auto y tiene puertas: " + ((Auto) v).getCantidadPuertas());
    } else {
        System.out.println("=> No es un Auto (vehículo genérico).");
    }
    System.out.println();
}
// --- KATA 2: Figuras geométricas ---
System.out.println("=== KATA 2: Figuras geométricas ===");

// Creamos un array de figuras (polimorfismo)
Figura[] figuras = new Figura[3];
figuras[0] = new Circulo("Círculo A", 5);
figuras[1] = new Rectangulo("Rectángulo B", 4, 6);
figuras[2] = new Circulo("Círculo C", 2.5);

// Recorremos el array y mostramos info (llama dinámicamente a calcularArea())
for (Figura f : figuras) {
    f.mostrarInfo();
}

        // --- KATA 3: Empleados y polimorfismo ---
System.out.println("\n=== KATA 3: Empleados y polimorfismo ===");

Empleado[] empleados = new Empleado[3];
empleados[0] = new EmpleadoPlanta("Ana", 120000);
empleados[1] = new EmpleadoTemporal("Luis", 80, 1500);
empleados[2] = new EmpleadoPlanta("Carlos", 150000);

// Polimorfismo: todos responden a calcularSueldo()
for (Empleado e : empleados) {
    System.out.println(e.getNombre() + " cobra $" + e.calcularSueldo());

    // Uso de instanceof para clasificar empleados
    if (e instanceof EmpleadoPlanta) {
        System.out.println(" -> Es un empleado de planta.");
    } else if (e instanceof EmpleadoTemporal) {
        System.out.println(" -> Es un empleado temporal.");
    }
}


       // --- KATA 4: Animales y comportamiento sobrescrito ---
System.out.println("\n=== KATA 4: Animales y polimorfismo ===");

Animal[] animales = {
    new Perro("Firulais"),
    new Gato("Michi"),
    new Vaca("Lola")
};

// Polimorfismo: cada animal hace su propio sonido
for (Animal a : animales) {
    a.describirAnimal();
    a.hacerSonido();
}

        // --- KATA 5: Sistema de pagos ---
System.out.println("\n=== KATA 5: Sistema de pagos ===");

Pagable efectivo = new Efectivo();
Pagable tarjeta = new TarjetaCredito("1234-5678-9012-3456");
Pagable transferencia = new Transferencia("Banco Nación");

double monto = 5000;

procesarPago(efectivo, monto);
procesarPago(tarjeta, monto);
procesarPago(transferencia, monto);

    }

    public static void procesarPago(Pagable medio, double monto) {
    medio.pagar(monto);

    }
}
