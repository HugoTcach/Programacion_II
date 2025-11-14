package utn.tfi.programacion2.main;

import java.util.Scanner;
import utn.tfi.programacion2.service.PedidoServiceImpl;
import utn.tfi.programacion2.service.EnvioServiceImpl;

public class AppMenu {

    private final Scanner scanner;
    private final MenuHandler handler;
    private boolean running;

    public AppMenu() {
        this.scanner = new Scanner(System.in);

        // Crear servicios
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();
        EnvioServiceImpl envioService = new EnvioServiceImpl();

        // Inyección en el handler
        this.handler = new MenuHandler(scanner, pedidoService, envioService);

        this.running = true;
    }

    public void run() {
        while (running) {
            try {
                MenuDisplay.mostrarMenuPrincipal();
                int opcion = Integer.parseInt(scanner.nextLine());
                processOption(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
            }
        }
        scanner.close();
    }

    private void processOption(int opcion) {
        switch (opcion) {
            case 1 -> handler.gestionarPedidos();
            case 2 -> handler.gestionarEnvios();
            case 0 -> {
                System.out.println("Saliendo...");
                running = false;
            }
            default -> System.out.println("Opción no válida.");
        }
    }
}
