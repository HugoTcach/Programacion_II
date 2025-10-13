package tp8.excepciones;

import java.util.Scanner;

public class Ejercicio4_UsoExcepcion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Por favor, introduce tu edad: ");
            int edad = scanner.nextInt();

            // Aquí está nuestra regla de negocio.
            if (edad < 0 || edad > 120) {
                // Si la edad no es válida, LANZAMOS nuestra excepción personalizada.
                throw new EdadInvalidaException("La edad debe estar entre 0 y 120 años.");
            }

            // Esta línea solo se ejecuta si no se lanza la excepción.
            System.out.println("Edad válida. Gracias por introducir tu edad: " + edad);

        } catch (EdadInvalidaException e) {
            // Atrapamos ESPECÍFICAMENTE el error que nosotros creamos.
            System.out.println("Error: " + e.getMessage());

        } catch (Exception e) {
            // Un catch genérico por si ocurre otro error (ej: si no introduce un número).
            System.out.println("Error inesperado: Debes introducir un número válido.");

        } finally {
            scanner.close();
        }
    }
}