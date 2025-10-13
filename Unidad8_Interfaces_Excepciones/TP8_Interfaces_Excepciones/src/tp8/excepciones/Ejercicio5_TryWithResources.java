package tp8.excepciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio5_TryWithResources {

    public static void main(String[] args) {
        String nombreArchivo = "texto_de_prueba.txt";

        // --- SINTAXIS TRY-WITH-RESOURCES ---
        // El recurso (BufferedReader) se declara DENTRO de los paréntesis del try.
        // Java se encargará de cerrarlo automáticamente al finalizar el bloque.
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {

            System.out.println("--- Leyendo archivo con try-with-resources ---");
            String linea;

            // Leemos el archivo línea por línea.
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.println("----------------------------------------------");

        } catch (IOException e) {
            // Atrapamos IOException, que es una excepción más general que
            // incluye FileNotFoundException y otros errores de lectura/escritura.
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        // --- NO NECESITAMOS BLOQUE 'finally' PARA CERRAR EL RECURSO ---
        // La magia del try-with-resources es que el reader.close() se llama
        // de forma implícita y automática.
    }
}