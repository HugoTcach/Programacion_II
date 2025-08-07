// HolaMundo.java
public class HolaMundo {
    public static void main(String[] args) {
        System.out.println("¡Hola, Java!");
    }
}

// VariablesEjemplo.java
public class VariablesEjemplo {
    public static void main(String[] args) {
        String nombre = "Ana";
        int edad = 25;
        double altura = 1.65;
        boolean estudiante = true;

        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Altura: " + altura);
        System.out.println("Estudiante: " + estudiante);
    }
}

// EntradaUsuario.java
import java.util.Scanner;

public class EntradaUsuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingresa tu edad: ");
        int edad = scanner.nextInt();

        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
    }
}

// OperacionesBasicas.java
import java.util.Scanner;

public class OperacionesBasicas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa el primer número: ");
        int num1 = scanner.nextInt();

        System.out.print("Ingresa el segundo número: ");
        int num2 = scanner.nextInt();

        System.out.println("Suma: " + (num1 + num2));
        System.out.println("Resta: " + (num1 - num2));
        System.out.println("Multiplicación: " + (num1 * num2));
        if (num2 != 0) {
            System.out.println("División: " + ((double)num1 / num2));
        } else {
            System.out.println("No se puede dividir por cero.");
        }
    }
}

// MensajeFormato.java
public class MensajeFormato {
    public static void main(String[] args) {
        System.out.println("Nombre: Juan Pérez\nEdad: 30 años\nDirección: \"Calle Falsa 123\"");
    }
}

// ExpresionesInstrucciones.java
public class ExpresionesInstrucciones {
    public static void main(String[] args) {
        int x = 10;          // Instrucción (declaración y asignación)
        x = x + 5;           // Instrucción que contiene la expresión "x + 5"
        System.out.println(x); // Instrucción que llama a un método para imprimir

        // Expresión: combinación de variables y operadores que evalúa un valor
        // Ejemplo: x + 5 es una expresión que calcula un valor.

        // Instrucción: acción completa que realiza el programa
        // Ejemplo: int x = 10; es una instrucción.
    }
}

// DivisionEntera.java
import java.util.Scanner;

public class DivisionEntera {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa el primer número entero: ");
        int num1 = scanner.nextInt();

        System.out.print("Ingresa el segundo número entero: ");
        int num2 = scanner.nextInt();

        if (num2 != 0) {
            System.out.println("Resultado de la división entera: " + (num1 / num2));
        } else {
            System.out.println("No se puede dividir por cero.");
        }
    }
}

// DivisionDouble.java
import java.util.Scanner;

public class DivisionDouble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa el primer número: ");
        double num1 = scanner.nextDouble();

        System.out.print("Ingresa el segundo número: ");
        double num2 = scanner.nextDouble();

        if (num2 != 0) {
            System.out.println("Resultado de la división: " + (num1 / num2));
        } else {
            System.out.println("No se puede dividir por cero.");
        }
    }
}

// ErrorEjemplo.java
import java.util.Scanner;

public class ErrorEjemplo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Hola, " + nombre);
    }
}
