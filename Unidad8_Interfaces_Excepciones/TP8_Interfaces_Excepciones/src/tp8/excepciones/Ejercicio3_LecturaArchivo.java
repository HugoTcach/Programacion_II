package tp8.excepciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ejercicio3_LecturaArchivo {

    public static void main(String[] args) {
        // El nombre del archivo que queremos leer.
        String nombreArchivo = "texto_de_prueba.txt";

        try {
            // Creamos un objeto File que representa a nuestro archivo.
            File archivo = new File(nombreArchivo);

            // Usamos Scanner para leer el contenido del archivo.
            // Esta línea puede lanzar la FileNotFoundException.
            Scanner lector = new Scanner(archivo);

            System.out.println("--- Contenido del archivo '" + nombreArchivo + "' ---");

            // Leemos el archivo línea por línea hasta que no queden más.
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                System.out.println(linea);
            }

            System.out.println("------------------------------------------");

            lector.close(); // Cerramos el lector cuando terminamos.

        } catch (FileNotFoundException e) {
            // Este bloque se ejecuta si el archivo no se encuentra.
            System.out.println("Error: El archivo '" + nombreArchivo + "' no fue encontrado en la raíz del proyecto.");
        }
    }
}