package tp8.excepciones;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio1_DivisionSegura {

    public static void main(String[] args) {
        // Scanner nos permite leer la entrada del usuario desde la consola.
        Scanner scanner = new Scanner(System.in);

        try {
            // El bloque 'try' contiene el código que podría lanzar una excepción.
            System.out.print("Introduce el dividendo (número a dividir): ");
            int dividendo = scanner.nextInt();

            System.out.print("Introduce el divisor (número por el que dividir): ");
            int divisor = scanner.nextInt();

            // Esta es la operación que puede fallar si el divisor es 0.
            int resultado = dividendo / divisor;

            System.out.println("El resultado de la división es: " + resultado);

        } catch (ArithmeticException e) {
            // Este bloque 'catch' se ejecuta SOLAMENTE si se produce una ArithmeticException.
            System.out.println("Error: No se puede dividir por cero.");

        } catch (InputMismatchException e) {
            // Este bloque captura el error si el usuario no introduce un número entero.
            System.out.println("Error: Debes introducir números enteros.");

        } finally {
            // El bloque 'finally' se ejecuta siempre, haya o no un error.
            // Es ideal para cerrar recursos.
            scanner.close(); // Cerramos el scanner para liberar recursos.
            System.out.println("El programa ha finalizado.");
        }
    }
}