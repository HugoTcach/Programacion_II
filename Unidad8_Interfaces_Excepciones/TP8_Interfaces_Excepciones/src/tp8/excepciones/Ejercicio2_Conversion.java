package tp8.excepciones;

import java.util.Scanner;

public class Ejercicio2_Conversion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce un texto para convertir a número entero: ");
        String textoUsuario = scanner.nextLine(); // Leemos la línea completa como texto.

        try {
            // Integer.parseInt() es el método que intenta la conversión.
            // Esta es la línea que puede lanzar la NumberFormatException.
            int numero = Integer.parseInt(textoUsuario);

            System.out.println("¡Conversión exitosa! El número es: " + numero);

        } catch (NumberFormatException e) {
            // Este bloque se ejecuta si Integer.parseInt() falla.
            System.out.println("Error: El texto '" + textoUsuario + "' no se puede convertir a un número entero válido.");

        } finally {
            // Cerramos el scanner y finalizamos.
            scanner.close();
            System.out.println("El programa ha finalizado.");
        }
    }
}