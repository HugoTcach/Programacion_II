
/**
 * Trabajo Práctico - Programación Estructurada
 * Archivo único con las soluciones de los ejercicios 1 a 13.
 *
 * Pegar tal cual en NetBeans -> New Java Class (Main) y ejecutar.
 *
 * Todos los métodos son estáticos y se usa un único Scanner compartido para
 * facilitar la ejecución secuencial desde main().
 *
 * Autor: (completá con tu nombre si quieres)
 */

import java.util.Locale;
import java.util.Scanner;

public class Main {

    // Variable global para el ejercicio 11 (descuento especial del 10%)
    private static final double DESCUENTO_ESPECIAL = 0.10;

    public static void main(String[] args) {
        // Usamos Locale.US para que la entrada/salida decimal use punto si el usuario escribe así.
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Trabajo Práctico: Programación Estructurada - Soluciones ===\n");

        // 1. Verificación de Año Bisiesto
        ejercicio1_bisiesto(scanner);
        saltarLinea();

        // 2. Determinar el Mayor de Tres Números
        ejercicio2_mayorTres(scanner);
        saltarLinea();

        // 3. Clasificación de Edad
        ejercicio3_clasificacionEdad(scanner);
        saltarLinea();

        // 4. Calculadora de Descuento según categoría
        ejercicio4_descuentoCategoria(scanner);
        saltarLinea();

        // 5. Suma de Números Pares (while) hasta 0
        ejercicio5_sumaParesHastaCero(scanner);
        saltarLinea();

        // 6. Contador de Positivos, Negativos y Ceros (for, 10 números)
        ejercicio6_contadorPosNegCeros(scanner);
        saltarLinea();

        // 7. Validación de Nota entre 0 y 10 (do-while)
        ejercicio7_validarNota(scanner);
        saltarLinea();

        // 8. Cálculo del Precio Final con impuesto y descuento (función)
        ejercicio8_calcularPrecioFinal(scanner);
        saltarLinea();

        // 9. Composición de funciones para calcular costo de envío y total de compra
        ejercicio9_envioYTotal(scanner);
        saltarLinea();

        // 10. Actualización de stock a partir de venta y recepción de productos
        ejercicio10_actualizarStock(scanner);
        saltarLinea();

        // 11. Cálculo de descuento especial usando variable global
        ejercicio11_descuentoEspecial(scanner);
        saltarLinea();

        // 12. Modificación de un array de precios y visualización (uso de arrays y for-each)
        ejercicio12_arrayPrecios();
        saltarLinea();

        // 13. Impresión recursiva de arrays antes y después de modificar un elemento
        ejercicio13_impresionRecursiva();
        saltarLinea();

        System.out.println("=== Fin del TP. ¡Listo para entregar! ===");
        scanner.close();
    }

    private static void saltarLinea() {
        System.out.println();
    }

    private static void ejercicio1_bisiesto(Scanner sc) {
        System.out.println("1) Verificación de Año Bisiesto");
        System.out.print("Ingrese un año: ");
        int anio = safeNextInt(sc);

        boolean esBisiesto = esAnioBisiesto(anio);

        if (esBisiesto) {
            System.out.println("El año " + anio + " es bisiesto.");
        } else {
            System.out.println("El año " + anio + " no es bisiesto.");
        }
    }

    private static boolean esAnioBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }

    private static void ejercicio2_mayorTres(Scanner sc) {
        System.out.println("2) Determinar el Mayor de Tres Números");
        System.out.print("Ingrese el primer número: ");
        int a = safeNextInt(sc);
        System.out.print("Ingrese el segundo número: ");
        int b = safeNextInt(sc);
        System.out.print("Ingrese el tercer número: ");
        int c = safeNextInt(sc);

        int mayor = mayorDeTres(a, b, c);
        System.out.println("El mayor es: " + mayor);
    }

    private static int mayorDeTres(int x, int y, int z) {
        int mayor = x;
        if (y > mayor) mayor = y;
        if (z > mayor) mayor = z;
        return mayor;
    }

    private static void ejercicio3_clasificacionEdad(Scanner sc) {
        System.out.println("3) Clasificación de Edad");
        System.out.print("Ingrese su edad: ");
        int edad = safeNextInt(sc);

        String categoria;
        if (edad < 12) {
            categoria = "Niño";
        } else if (edad >= 12 && edad <= 17) {
            categoria = "Adolescente";
        } else if (edad >= 18 && edad <= 59) {
            categoria = "Adulto";
        } else {
            categoria = "Adulto mayor";
        }

        System.out.println("Eres un " + categoria + ".");
    }

    private static void ejercicio4_descuentoCategoria(Scanner sc) {
        System.out.println("4) Calculadora de Descuento según categoría");
        System.out.print("Ingrese el precio del producto: ");
        double precio = safeNextDouble(sc);
        System.out.print("Ingrese la categoría del producto (A, B o C): ");
        String cat = sc.next().trim().toUpperCase();

        double descuentoPct;
        switch (cat) {
            case "A":
                descuentoPct = 10.0;
                break;
            case "B":
                descuentoPct = 15.0;
                break;
            case "C":
                descuentoPct = 20.0;
                break;
            default:
                descuentoPct = 0.0;
                System.out.println("Categoría no válida. Se aplicará 0% de descuento.");
        }

        double descuento = precio * (descuentoPct / 100.0);
        double precioFinal = precio - descuento;

        System.out.println("Precio original: " + precio);
        System.out.println("Descuento aplicado: " + ((int)descuentoPct) + "%");
        System.out.println("Precio final: " + precioFinal);
    }

    private static void ejercicio5_sumaParesHastaCero(Scanner sc) {
        System.out.println("5) Suma de Números Pares (ingrese 0 para terminar)");
        int sumaPares = 0;
        int num;
        do {
            System.out.print("Ingrese un número (0 para terminar): ");
            num = safeNextInt(sc);
            if (num != 0 && num % 2 == 0) {
                sumaPares += num;
            }
        } while (num != 0);

        System.out.println("La suma de los números pares es: " + sumaPares);
    }

    private static void ejercicio6_contadorPosNegCeros(Scanner sc) {
        System.out.println("6) Contador de Positivos, Negativos y Ceros (10 números)");
        int positivos = 0, negativos = 0, ceros = 0;

        for (int i = 1; i <= 10; i++) {
            System.out.print("Ingrese el número " + i + ": ");
            int n = safeNextInt(sc);
            if (n > 0) positivos++;
            else if (n < 0) negativos++;
            else ceros++;
        }

        System.out.println("Resultados:");
        System.out.println("Positivos: " + positivos);
        System.out.println("Negativos: " + negativos);
        System.out.println("Ceros: " + ceros);
    }

    private static void ejercicio7_validarNota(Scanner sc) {
        System.out.println("7) Validación de Nota entre 0 y 10");
        int nota;
        do {
            System.out.print("Ingrese una nota (0-10): ");
            nota = safeNextInt(sc);
            if (nota < 0 || nota > 10) {
                System.out.println("Error: Nota inválida. Ingrese una nota entre 0 y 10.");
            }
        } while (nota < 0 || nota > 10);

        System.out.println("Nota guardada correctamente: " + nota);
    }

    private static void ejercicio8_calcularPrecioFinal(Scanner sc) {
        System.out.println("8) Cálculo del Precio Final con impuesto y descuento");
        System.out.print("Ingrese el precio base del producto: ");
        double precioBase = safeNextDouble(sc);
        System.out.print("Ingrese el impuesto en porcentaje (Ejemplo: 10 para 10%): ");
        double impuestoPct = safeNextDouble(sc);
        System.out.print("Ingrese el descuento en porcentaje (Ejemplo: 5 para 5%): ");
        double descuentoPct = safeNextDouble(sc);

        double precioFinal = calcularPrecioFinal(precioBase, impuestoPct, descuentoPct);
        System.out.println("El precio final del producto es: " + precioFinal);
    }

    private static double calcularPrecioFinal(double precioBase, double impuestoPct, double descuentoPct) {
        double impuesto = precioBase * (impuestoPct / 100.0);
        double descuento = precioBase * (descuentoPct / 100.0);
        return precioBase + impuesto - descuento;
    }

    private static void ejercicio9_envioYTotal(Scanner sc) {
        System.out.println("9) Costo de envío y total de compra");
        System.out.print("Ingrese el precio del producto: ");
        double precioProducto = safeNextDouble(sc);
        System.out.print("Ingrese el peso del paquete en kg: ");
        double peso = safeNextDouble(sc);
        sc.nextLine();
        System.out.print("Ingrese la zona de envío (Nacional/Internacional): ");
        String zona = sc.nextLine().trim();

        double costoEnvio = calcularCostoEnvio(peso, zona);
        double total = calcularTotalCompra(precioProducto, costoEnvio);

        System.out.println("El costo de envío es: " + costoEnvio);
        System.out.println("El total a pagar es: " + total);
    }

    private static double calcularCostoEnvio(double peso, String zona) {
        String z = zona.trim().toLowerCase();
        if (z.startsWith("n")) {
            return 5.0 * peso;
        } else {
            return 10.0 * peso;
        }
    }

    private static double calcularTotalCompra(double precioProducto, double costoEnvio) {
        return precioProducto + costoEnvio;
    }

    private static void ejercicio10_actualizarStock(Scanner sc) {
        System.out.println("10) Actualización de stock");
        System.out.print("Ingrese el stock actual del producto: ");
        int stockActual = safeNextInt(sc);
        System.out.print("Ingrese la cantidad vendida: ");
        int cantidadVendida = safeNextInt(sc);
        System.out.print("Ingrese la cantidad recibida: ");
        int cantidadRecibida = safeNextInt(sc);

        int nuevoStock = actualizarStock(stockActual, cantidadVendida, cantidadRecibida);
        System.out.println("El nuevo stock del producto es: " + nuevoStock);
    }

    private static int actualizarStock(int stockActual, int cantidadVendida, int cantidadRecibida) {
        return stockActual - cantidadVendida + cantidadRecibida;
    }

    private static void ejercicio11_descuentoEspecial(Scanner sc) {
        System.out.println("11) Cálculo de descuento especial (variable global)");
        System.out.print("Ingrese el precio del producto: ");
        double precio = safeNextDouble(sc);

        calcularDescuentoEspecial(precio);
    }

    private static void calcularDescuentoEspecial(double precio) {
        double descuentoAplicado = precio * DESCUENTO_ESPECIAL;
        double precioFinal = precio - descuentoAplicado;
        System.out.println("El descuento especial aplicado es: " + descuentoAplicado);
        System.out.println("El precio final con descuento es: " + precioFinal);
    }

    private static void ejercicio12_arrayPrecios() {
        System.out.println("12) Modificación de un array de precios y visualización");
        double[] precios = {199.99, 299.5, 149.75, 399.0, 89.99};

        System.out.println("Precios originales:");
        for (double p : precios) {
            System.out.println("Precio: $" + p);
        }

        precios[2] = 129.99;

        System.out.println("\nPrecios modificados:");
        for (double p : precios) {
            System.out.println("Precio: $" + p);
        }
    }

    private static void ejercicio13_impresionRecursiva() {
        System.out.println("13) Impresión recursiva de arrays antes y después de modificar un elemento");
        double[] precios = {199.99, 299.5, 149.75, 399.0, 89.99};

        System.out.println("Precios originales:");
        imprimirPreciosRecursivo(precios, 0);

        precios[2] = 129.99;

        System.out.println("\nPrecios modificados:");
        imprimirPreciosRecursivo(precios, 0);
    }

    private static void imprimirPreciosRecursivo(double[] arr, int idx) {
        if (arr == null || idx >= arr.length) {
            return;
        }
        System.out.println("Precio: $" + arr[idx]);
        imprimirPreciosRecursivo(arr, idx + 1);
    }

    private static int safeNextInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Entrada inválida. Ingrese un número entero: ");
        }
        return sc.nextInt();
    }

    private static double safeNextDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            sc.next();
            System.out.print("Entrada inválida. Ingrese un número (decimal o entero): ");
        }
        return sc.nextDouble();
    }
}
