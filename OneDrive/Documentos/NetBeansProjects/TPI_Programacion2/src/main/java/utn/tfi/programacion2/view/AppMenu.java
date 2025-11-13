package utn.tfi.programacion2.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import utn.tfi.programacion2.entities.Envio;
import utn.tfi.programacion2.entities.Pedido;
import utn.tfi.programacion2.service.EnvioService;
import utn.tfi.programacion2.service.EnvioServiceImpl;
import utn.tfi.programacion2.service.PedidoService;
import utn.tfi.programacion2.service.PedidoServiceImpl;

/**
 * Clase AppMenu (consola)
 * Gestiona la interacción con el usuario, maneja entradas,
 * y orquesta las llamadas a los servicios de Pedido y Envio.
 */
public class AppMenu {

    private final Scanner scanner;
    private final PedidoService pedidoService;
    private final EnvioService envioService;

    public AppMenu() {
        this.scanner = new Scanner(System.in);
        this.pedidoService = new PedidoServiceImpl();
        this.envioService = new EnvioServiceImpl();
    }

    /**
     * Inicia el bucle principal del menú.
     */
    public void iniciar() {
        int opcion = 0;
        do {
            try {
                mostrarMenuPrincipal();
                opcion = leerEntero("");

                switch (opcion) {
                    case 1:
                        gestionarPedidos();
                        break;
                    case 2:
                        gestionarEnvios();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.err.println("Opción no valida. Intente de nuevo.");
                }
            } catch (Exception e) {
                // Captura genérica para errores inesperados en el flujo del menú
                System.err.println("Error inesperado en el menu: " + e.getMessage());
            }
        } while (opcion != 0);
        scanner.close();
    }

    // --- MENÚS ---
    private void mostrarMenuPrincipal() {
        System.out.println("\n--- SISTEMA DE GESTION (TFI) ---");
        System.out.println("1. Gestionar Pedidos (A)");
        System.out.println("2. Gestionar Envíos (B)");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void mostrarSubMenu(String entidad) {
        System.out.println("\n--- GESTION DE " + entidad.toUpperCase() + " ---");
        System.out.println("1. Crear " + entidad);
        System.out.println("2. Buscar " + entidad + " por ID");
        System.out.println("3. Listar todos los " + entidad + "s");
        System.out.println("4. Actualizar " + entidad);
        System.out.println("5. Eliminar " + entidad + " (Baja Logica)");
        if (entidad.equals("Pedido")) {
            System.out.println("6. Buscar Pedido por Numero");
        }
        if (entidad.equals("Envio")) {
            System.out.println("6. Buscar Envio por Tracking");
        }
        System.out.println("0. Volver al menu principal");
        System.out.print("Seleccione una opción: ");
    }

    // --- GESTIÓN DE PEDIDOS (A) ---
    private void gestionarPedidos() {
        int opcion = 0;
        do {
            try {
                mostrarSubMenu("Pedido");
                opcion = leerEntero("");

                switch (opcion) {
                    case 1: crearPedido(); break;
                    case 2: buscarPedidoPorId(); break;
                    case 3: listarPedidos(); break;
                    case 4: actualizarPedido(); break;
                    case 5: eliminarPedido(); break;
                    case 6: buscarPedidoPorNumero(); break;
                    case 0: break;
                    default: System.err.println("Opcion no valida.");
                }
            } catch (Exception e) {
                // Captura errores de negocio o BD
                System.err.println("ERROR: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void crearPedido() throws Exception {
        System.out.println("\n--- Creando Nuevo Pedido ---");
        String numero = leerString("Numero de Pedido: ");
        LocalDate fecha = leerLocalDate("Fecha (YYYY-MM-DD): ");
        String cliente = leerString("Nombre del Cliente: ");
        double total = leerDouble("Total (ej: 123.45): ");
        Pedido.EstadoPedido estado = leerEnum(Pedido.EstadoPedido.class, "Estado del Pedido: ");

        Pedido pedido = new Pedido(null, false, numero, fecha, cliente, total, estado, null);

        // Asociar Envío
        pedido.setEnvio(gestionarAsociacionEnvio(null));

        pedidoService.save(pedido);
        System.out.println("¡Pedido creado con exito! ID: " + pedido.getId());
    }

    private void buscarPedidoPorId() throws Exception {
        System.out.println("\n--- Buscar Pedido por ID ---");
        Long id = leerLong("Ingrese ID del Pedido: ");
        Pedido pedido = pedidoService.findById(id);
        
        if (pedido != null) {
            System.out.println("Pedido encontrado:");
            // Usamos el toString() legible
            System.out.println(pedido.toString()); 
        } else {
            System.err.println("Error: No se encontro ningun Pedido con ID: " + id);
        }
    }

    private void listarPedidos() throws Exception {
        System.out.println("\n--- Listado de Pedidos ---");
        List<Pedido> pedidos = pedidoService.findAll();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println(p.toString());
        }
    }

    private void actualizarPedido() throws Exception {
        System.out.println("\n--- Actualizar Pedido ---");
        Long id = leerLong("Ingrese ID del Pedido a actualizar: ");
        Pedido pedido = pedidoService.findById(id);

        if (pedido == null) {
            System.err.println("Error: No se encontro ningun Pedido con ID: " + id);
            return;
        }

        System.out.println("Datos actuales: " + pedido.toString());
        System.out.println("Ingrese los nuevos datos (deje en blanco para no modificar):");

        String numero = leerString("Numero [" + pedido.getNumero() + "]: ");
        if (!numero.isEmpty()) pedido.setNumero(numero);

        String cliente = leerString("Cliente [" + pedido.getClienteNombre() + "]: ");
        if (!cliente.isEmpty()) pedido.setClienteNombre(cliente);

        double total = leerDoubleOpcional("Total [" + pedido.getTotal() + "]: ");
        if (total > 0) pedido.setTotal(total);

        Pedido.EstadoPedido estado = leerEnumOpcional(Pedido.EstadoPedido.class, "Estado [" + pedido.getEstado() + "]: ");
        if (estado != null) pedido.setEstado(estado);

        // Gestionar asociación de Envío
        pedido.setEnvio(gestionarAsociacionEnvio(pedido.getEnvio()));

        pedidoService.update(pedido);
        System.out.println("¡Pedido actualizado con exito!");
    }

    private void eliminarPedido() throws Exception {
        System.out.println("\n--- Eliminar Pedido (Baja Logica) ---");
        Long id = leerLong("Ingrese ID del Pedido a eliminar: ");
        Pedido pedido = pedidoService.findById(id); // Verificar que existe

        if (pedido == null) {
            System.err.println("Error: No se encontro ningun Pedido con ID: " + id);
            return;
        }

        String confirm = leerString("¿Seguro que desea eliminar logicamente el Pedido " + id + "? (S/N): ");
        if (confirm.equalsIgnoreCase("S")) {
            pedidoService.delete(id);
            System.out.println("Pedido eliminado logicamente.");
        } else {
            System.out.println("Eliminacion cancelada.");
        }
    }

    private void buscarPedidoPorNumero() throws Exception {
        System.out.println("\n--- Buscar Pedido por Número ---");
        String numero = leerString("Ingrese el Numero de Pedido: ");
        Pedido pedido = pedidoService.findByNumero(numero);
        
        if (pedido != null) {
            System.out.println("Pedido encontrado:");
            System.out.println(pedido.toString());
        } else {
            System.err.println("Error: No se encontro ningun Pedido con el numero: " + numero);
        }
    }
    
    /**
     * Lógica auxiliar para asociar/desasociar un envío a un pedido.
     * @param envioActual El envío actualmente asociado (puede ser null).
     * @return El envío que debe quedar asociado (o null).
     */
    private Envio gestionarAsociacionEnvio(Envio envioActual) throws Exception {
        System.out.println("\n--- Gestión de Envío Asociado ---");
        String trackingActual = (envioActual != null) ? envioActual.getTracking() : "Ninguno";
        System.out.println("Envio actual: " + trackingActual);
        
        System.out.println("¿Desea modificar el envio asociado?");
        System.out.println("1. Asociar/Cambiar Envio (por ID)");
        System.out.println("2. Quitar Envio asociado");
        System.out.println("0. No modificar");
        System.out.print("Opción: ");
        int opcion = leerEntero("");

        switch (opcion) {
            case 1:
                Long envioId = leerLong("Ingrese el ID del Envío a asociar: ");
                Envio envio = envioService.findById(envioId);
                if (envio == null) {
                    System.err.println("Error: Envio con ID " + envioId + " no existe. No se asociará.");
                    return envioActual; // Devuelve el original
                }
                System.out.println("Envio " + envio.getTracking() + " asociado.");
                return envio; // Devuelve el nuevo
            case 2:
                System.out.println("Envio desasociado.");
                return null; // Devuelve null para quitarlo
            case 0:
            default:
                System.out.println("Envio sin modificar.");
                return envioActual; // Devuelve el original
        }
    }


    // --- GESTIÓN DE ENVÍOS (B) ---
    private void gestionarEnvios() {
        int opcion = 0;
        do {
            try {
                mostrarSubMenu("Envio");
                opcion = leerEntero("");

                switch (opcion) {
                    case 1: crearEnvio(); break;
                    case 2: buscarEnvioPorId(); break;
                    case 3: listarEnvios(); break;
                    case 4: actualizarEnvio(); break;
                    case 5: eliminarEnvio(); break;
                    case 6: buscarEnvioPorTracking(); break;
                    case 0: break;
                    default: System.err.println("Opcion no valida.");
                }
            } catch (Exception e) {
                // Captura errores de negocio o BD
                System.err.println("ERROR: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void crearEnvio() throws Exception {
        System.out.println("\n--- Creando Nuevo Envio ---");
        String tracking = leerString("Codigo de Tracking: ");
        Envio.Empresa empresa = leerEnum(Envio.Empresa.class, "Empresa: ");
        Envio.Tipo tipo = leerEnum(Envio.Tipo.class, "Tipo: ");
        double costo = leerDouble("Costo (ej: 123.45): ");
        LocalDate fDespacho = leerLocalDate("Fecha Despacho (YYYY-MM-DD): ");
        LocalDate fEstimada = leerLocalDate("Fecha Estimada (YYYY-MM-DD): ");
        Envio.EstadoEnvio estado = leerEnum(Envio.EstadoEnvio.class, "Estado: ");

        Envio envio = new Envio(null, false, tracking, empresa, tipo, costo, fDespacho, fEstimada, estado);
        envioService.save(envio);
        System.out.println("¡Envio creado con exito! ID: " + envio.getId());
    }

    private void buscarEnvioPorId() throws Exception {
        System.out.println("\n--- Buscar Envio por ID ---");
        Long id = leerLong("Ingrese ID del Envio: ");
        Envio envio = envioService.findById(id);
        
        if (envio != null) {
            System.out.println("Envio encontrado:");
            System.out.println(envio.toString());
        } else {
            System.err.println("Error: No se encontró ningún Envio con ID: " + id);
        }
    }

    private void listarEnvios() throws Exception {
        System.out.println("\n--- Listado de Envios ---");
        List<Envio> envios = envioService.findAll();
        if (envios.isEmpty()) {
            System.out.println("No hay envios registrados.");
            return;
        }
        for (Envio e : envios) {
            System.out.println(e.toString());
        }
    }

    private void actualizarEnvio() throws Exception {
        System.out.println("\n--- Actualizar Envio ---");
        Long id = leerLong("Ingrese ID del Envio a actualizar: ");
        Envio envio = envioService.findById(id);

        if (envio == null) {
            System.err.println("Error: No se encontro ningun Envio con ID: " + id);
            return;
        }

        System.out.println("Datos actuales: " + envio.toString());
        System.out.println("Ingrese los nuevos datos (deje en blanco para no modificar):");

        String tracking = leerString("Tracking [" + envio.getTracking() + "]: ");
        if (!tracking.isEmpty()) envio.setTracking(tracking);

        double costo = leerDoubleOpcional("Costo [" + envio.getCosto() + "]: ");
        if (costo > 0) envio.setCosto(costo);
        
        Envio.EstadoEnvio estado = leerEnumOpcional(Envio.EstadoEnvio.class, "Estado [" + envio.getEstado() + "]: ");
        if (estado != null) envio.setEstado(estado);
        

        envioService.update(envio);
        System.out.println("¡Envio actualizado con exito!");
    }

    private void eliminarEnvio() throws Exception {
        System.out.println("\n--- Eliminar Envio (Baja Logica) ---");
        Long id = leerLong("Ingrese ID del Envio a eliminar: ");
        Envio envio = envioService.findById(id); // Verificar que existe

        if (envio == null) {
            System.err.println("Error: No se encontro ningun Envio con ID: " + id);
            return;
        }
        
        // Validar que el envío no esté asociado a un Pedido activo

        String confirm = leerString("¿Seguro que desea eliminar logicamente el Envio " + id + "? (S/N): ");
        if (confirm.equalsIgnoreCase("S")) {
            envioService.delete(id);
            System.out.println("Envio eliminado lógicamente.");
        } else {
            System.out.println("Eliminacion cancelada.");
        }
    }

    private void buscarEnvioPorTracking() throws Exception {
        System.out.println("\n--- Buscar Envio por Tracking ---");
        String tracking = leerString("Ingrese el codigo de Tracking: ");
        Envio envio = envioService.findByTracking(tracking);
        
        if (envio != null) {
            System.out.println("Envio encontrado:");
            System.out.println(envio.toString());
        } else {
            System.err.println("Error: No se encontro ningun Envío con el tracking: " + tracking);
        }
    }

    // --- MÉTODOS AUXILIARES DE ENTRADA (Robustez) ---

    private String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                String input = leerString(mensaje);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("Error: Debe ingresar un numero entero. Intente de nuevo.");
            }
        }
    }
    
    private long leerLong(String mensaje) {
        while (true) {
            try {
                String input = leerString(mensaje);
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.err.println("Error: Debe ingresar un numero largo (ID). Intente de nuevo.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                String input = leerString(mensaje);
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.err.println("Error: Debe ingresar un numero decimal (ej: 123.45). Intente de nuevo.");
            }
        }
    }
    
    private double leerDoubleOpcional(String mensaje) {
        while (true) {
            try {
                String input = leerString(mensaje);
                if (input.isEmpty()) return -1; // Flag para "no modificar"
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.err.println("Error: Debe ingresar un numero decimal (ej: 123.45) o dejar en blanco.");
            }
        }
    }

    private LocalDate leerLocalDate(String mensaje) {
        while (true) {
            try {
                String input = leerString(mensaje + " (YYYY-MM-DD): ");
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.err.println("Error: Formato de fecha incorrecto. Use YYYY-MM-DD. Intente de nuevo.");
            }
        }
    }

    private <T extends Enum<T>> T leerEnum(Class<T> enumClass, String mensaje) {
        System.out.println(mensaje + " Opciones: " + Arrays.toString(enumClass.getEnumConstants()));
        while (true) {
            try {
                String input = leerString("Seleccione una: ").toUpperCase(); // Requisito de mayúsculas
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.err.println("Error: Valor no valido. Opciones: " + Arrays.toString(enumClass.getEnumConstants()));
            }
        }
    }
    
    private <T extends Enum<T>> T leerEnumOpcional(Class<T> enumClass, String mensaje) {
        System.out.println(mensaje + " Opciones: " + Arrays.toString(enumClass.getEnumConstants()));
        while (true) {
            try {
                String input = leerString("Seleccione (o deje en blanco): ").toUpperCase();
                if (input.isEmpty()) return null; // Flag "no modificar"
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.err.println("Error: Valor no valido. Opciones: " + Arrays.toString(enumClass.getEnumConstants()));
            }
        }
    }
}
