package poo_hugotcach;

// Archivo: Empleado.java
public class Empleado {

    private int id;
    private String nombre;
    private String puesto;
    private double salario;

    private static int totalEmpleados = 0;
    private static int ultimoId = 0;

    public Empleado(int id, String nombre, String puesto, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        totalEmpleados++;
        if (id > ultimoId) {
            ultimoId = id;
        }
    }

    public Empleado(String nombre, String puesto) {
        this.id = ++ultimoId;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = 30000.0;
        totalEmpleados++;
    }

    public void actualizarSalario(double porcentaje) {
        this.salario += this.salario * (porcentaje / 100.0);
    }

    public void actualizarSalario(int monto) {
        this.salario += monto;
    }

    @Override
    public String toString() {
        return "Empleado ID: " + id +
               " | Nombre: " + nombre +
               " | Puesto: " + puesto +
               " | Salario: $" + salario;
    }

    public static int mostrarTotalEmpleados() {
        return totalEmpleados;
    }
}
