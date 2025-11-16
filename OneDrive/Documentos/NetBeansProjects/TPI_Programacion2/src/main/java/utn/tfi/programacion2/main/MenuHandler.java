package utn.tfi.programacion2.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import utn.tfi.programacion2.entities.Envio;
import utn.tfi.programacion2.entities.Pedido;
import utn.tfi.programacion2.service.EnvioServiceImpl;
import utn.tfi.programacion2.service.PedidoServiceImpl;

public class MenuHandler {

    private final Scanner scanner;
    private final PedidoServiceImpl pedidoService;
    private final EnvioServiceImpl envioService;

    public MenuHandler(Scanner scanner,
                       PedidoServiceImpl pedidoService,
                       EnvioServiceImpl envioService) {
        this.scanner = scanner;
        this.pedidoService = pedidoService;
        this.envioService = envioService;
    }

    // ============================================================
    //                       PEDIDOS
    // ============================================================

    public void gestionarPedidos() {
        int opcion;
        do {
            MenuDisplay.mostrarSubMenu("Pedidos");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> crearPedido();
                case 2 -> buscarPedidoPorId();
                case 3 -> listarPedidos();
                case 4 -> actualizarPedido();
                case 5 -> eliminarPedido();
                case 6 -> buscarPedidoPorNumero();
                case 7 -> crearPedidoCompletoTx();
                case 0 -> {}
                default -> System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);
    }

    private void crearPedido() {
        try {
            System.out.println("\n--- Crear Pedido ---");
            String numero = leerString("Numero: ");
            LocalDate fecha = leerFecha("Fecha (YYYY-MM-DD)");
            String cliente = leerString("Cliente: ");
            double total = leerDouble("Total: ");

            Pedido.EstadoPedido estado = leerEnum(Pedido.EstadoPedido.class, "Estado");

            Pedido pedido = new Pedido(null, false, numero, fecha, cliente, total, estado, null);

            Envio envio = gestionarAsociacionEnvio(null);
            pedido.setEnvio(envio);

            pedidoService.save(pedido);
            System.out.println("Pedido creado con exito.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buscarPedidoPorId() {
        try {
            Long id = leerLong("ID: ");
            Pedido p = pedidoService.findById(id);

            if (p != null) System.out.println(p);
            else System.out.println("No se encontro.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarPedidos() {
        try {
            pedidoService.findAll().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void actualizarPedido() {
        try {
            Long id = leerLong("ID: ");
            Pedido pedido = pedidoService.findById(id);

            if (pedido == null) {
                System.out.println("No encontrado.");
                return;
            }

            System.out.println("Actual: " + pedido);

            String numero = leerStringOptional("Numero [" + pedido.getNumero() + "]: ");
            if (!numero.isEmpty()) pedido.setNumero(numero);

            String cliente = leerStringOptional("Cliente [" + pedido.getClienteNombre() + "]: ");
            if (!cliente.isEmpty()) pedido.setClienteNombre(cliente);

            double total = leerDoubleOptional("Total [" + pedido.getTotal() + "]: ");
            if (total > 0) pedido.setTotal(total);

            Pedido.EstadoPedido estado =
                    leerEnumOptional(Pedido.EstadoPedido.class, "Estado [" + pedido.getEstado() + "]");
            if (estado != null) pedido.setEstado(estado);

            pedido.setEnvio(gestionarAsociacionEnvio(pedido.getEnvio()));

            pedidoService.update(pedido);
            System.out.println("Actualizado.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarPedido() {
        try {
            Long id = leerLong("ID: ");
            pedidoService.delete(id);
            System.out.println("Eliminado.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarPedidoPorNumero() {
        try {
            String numero = leerString("Numero: ");
            Pedido p = pedidoService.findByNumero(numero);

            if (p != null) System.out.println(p);
            else System.out.println("No encontrado.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // ============================================================
    //   CREAR PEDIDO COMPLETO (Transaccion Pedido + Envio)
    // ============================================================

    private void crearPedidoCompletoTx() {
        try {
            System.out.println("\n--- Crear Pedido COMPLETO (con Transaccion) ---");

            String numero = leerString("Numero: ");
            LocalDate fecha = leerFecha("Fecha (YYYY-MM-DD)");
            String cliente = leerString("Cliente: ");
            double total = leerDouble("Total: ");
            Pedido.EstadoPedido estado = leerEnum(Pedido.EstadoPedido.class, "Estado");

            Pedido pedido = new Pedido(null, false, numero, fecha, cliente, total, estado, null);

            System.out.println("\n--- Datos del Envio ---");
            String tracking = leerString("Tracking: ");
            Envio.Empresa empresa = leerEnum(Envio.Empresa.class, "Empresa");
            Envio.Tipo tipo = leerEnum(Envio.Tipo.class, "Tipo");
            double costo = leerDouble("Costo: ");
            LocalDate despacho = leerFecha("Fecha despacho");
            LocalDate estimada = leerFecha("Fecha estimada");
            Envio.EstadoEnvio estadoEnvio = leerEnum(Envio.EstadoEnvio.class, "Estado");

            Envio envio = new Envio(null, false, tracking, empresa, tipo, costo, despacho, estimada, estadoEnvio);
            pedido.setEnvio(envio);

            pedidoService.crearPedidoCompleto(pedido);

            System.out.println("Pedido completo creado con exito.");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // ============================================================
    //                       ENVIOS
    // ============================================================

    public void gestionarEnvios() {
        int opcion;
        do {
            MenuDisplay.mostrarSubMenu("Envios");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> crearEnvio();
                case 2 -> buscarEnvioPorId();
                case 3 -> listarEnvios();
                case 4 -> actualizarEnvio();
                case 5 -> eliminarEnvio();
                case 6 -> buscarEnvioPorTracking();
                case 0 -> {}
                default -> System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);
    }

    private void crearEnvio() {
        try {
            System.out.println("\n--- Crear Envio ---");

            String tracking = leerString("Tracking: ");
            Envio.Empresa empresa = leerEnum(Envio.Empresa.class, "Empresa");
            Envio.Tipo tipo = leerEnum(Envio.Tipo.class, "Tipo");
            double costo = leerDouble("Costo: ");
            LocalDate despacho = leerFecha("Fecha despacho");
            LocalDate estimada = leerFecha("Fecha estimada");
            Envio.EstadoEnvio estado = leerEnum(Envio.EstadoEnvio.class, "Estado");

            Envio envio = new Envio(null, false, tracking, empresa, tipo, costo, despacho, estimada, estado);

            envioService.save(envio);
            System.out.println("Envio creado.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarEnvioPorId() {
        try {
            Long id = leerLong("ID: ");
            Envio e = envioService.findById(id);

            if (e != null) System.out.println(e);
            else System.out.println("No encontrado.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarEnvios() {
        try {
            envioService.findAll().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void actualizarEnvio() {
        try {
            Long id = leerLong("ID: ");
            Envio envio = envioService.findById(id);

            if (envio == null) {
                System.out.println("No encontrado.");
                return;
            }

            String tracking = leerStringOptional("Tracking [" + envio.getTracking() + "]: ");
            if (!tracking.isEmpty()) envio.setTracking(tracking);

            double costo = leerDoubleOptional("Costo [" + envio.getCosto() + "]: ");
            if (costo > 0) envio.setCosto(costo);

            Envio.EstadoEnvio estado =
                    leerEnumOptional(Envio.EstadoEnvio.class, "Estado [" + envio.getEstado() + "]");
            if (estado != null) envio.setEstado(estado);

            envioService.update(envio);
            System.out.println("Actualizado.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarEnvio() {
        try {
            Long id = leerLong("ID: ");
            envioService.delete(id);
            System.out.println("Eliminado.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    private void buscarEnvioPorTracking() {
        try {
            String tracking = leerString("Tracking: ");
            Envio e = envioService.findByTracking(tracking);

            if (e != null) System.out.println(e);
            else System.out.println("No encontrado.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // ============================================================
    //      GESTION ASOCIACION PEDIDO ↔ ENVIO
    // ============================================================

    private Envio gestionarAsociacionEnvio(Envio actual) throws Exception {
        System.out.println("\n--- Asociacion de Envio ---");
        System.out.println("Actual: " + (actual != null ? actual.getTracking() : "Ninguno"));

        System.out.println("1. Asociar/Cambiar envio");
        System.out.println("2. Quitar envio");
        System.out.println("0. Mantener");

        int op = leerEntero();

        return switch (op) {
            case 1 -> {
                Long id = leerLong("ID del envio: ");
                Envio env = envioService.findById(id);
                if (env == null) {
                    System.out.println("No existe.");
                    yield actual;
                }
                yield env;
            }
            case 2 -> null;
            default -> actual;
        };
    }

    // ============================================================
    //                METODOS AUXILIARES
    // ============================================================

    private String leerString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private String leerStringOptional(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido.");
            }
        }
    }

    private Long leerLong(String msg) {
        System.out.print(msg);
        while (true) {
            try {
                return Long.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID invalido.");
            }
        }
    }

    private double leerDouble(String msg) {
        System.out.print(msg);
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido.");
            }
        }
    }

    private double leerDoubleOptional(String msg) {
        System.out.print(msg);
        try {
            String in = scanner.nextLine();
            if (in.isEmpty())
                return -1;
            return Double.parseDouble(in);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private LocalDate leerFecha(String msg) {
        while (true) {
            try {
                System.out.print(msg + ": ");
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto.");
            }
        }
    }

    private <T extends Enum<T>> T leerEnum(Class<T> clazz, String msg) {
        System.out.println(msg + " " + Arrays.toString(clazz.getEnumConstants()));
        while (true) {
            try {
                return Enum.valueOf(clazz, scanner.nextLine().toUpperCase());
            } catch (Exception e) {
                System.out.println("Valor invalido.");
            }
        }
    }

    private <T extends Enum<T>> T leerEnumOptional(Class<T> clazz, String msg) {
        System.out.println(msg + " " + Arrays.toString(clazz.getEnumConstants()));
        String in = scanner.nextLine().toUpperCase();
        if (in.isEmpty()) return null;
        try {
            return Enum.valueOf(clazz, in);
        } catch (Exception e) {
            return null;
        }
    }
}
