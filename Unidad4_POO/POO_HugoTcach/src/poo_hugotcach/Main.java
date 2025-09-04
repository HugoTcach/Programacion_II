package poo_hugotcach;

// Archivo: Main.java
public class Main {
    public static void main(String[] args) {

        Empleado e1 = new Empleado(101, "Ana Pérez", "Analista", 50000);
        Empleado e2 = new Empleado("Luis Gómez", "Programador");
        Empleado e3 = new Empleado("Carla Díaz", "Diseñadora");
        Empleado e4 = new Empleado(105, "Jorge Ramírez", "Tester", 45000);

        e1.actualizarSalario(10.0);
        e2.actualizarSalario(5000);
        e3.actualizarSalario(15.0);
        e4.actualizarSalario(2000);

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4);

        System.out.println("Total de empleados creados: " + Empleado.mostrarTotalEmpleados());
    }
}
