package utn.tfi.programacion2.main;

public class MenuDisplay {

    public static void mostrarMenuPrincipal() {
        System.out.println("\n========= MENU PRINCIPAL =========");
        System.out.println("1. Gestionar Pedidos");
        System.out.println("2. Gestionar Envios");
        System.out.println("0. Salir");
        System.out.print("Ingrese una opción: ");
    }

    public static void mostrarSubMenu(String titulo) {
        System.out.println("\n===== " + titulo.toUpperCase() + " =====");
        System.out.println("1. Crear");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar");
        System.out.println("4. Actualizar");
        System.out.println("5. Eliminar (baja lógica)");
        System.out.println("6. Buscar por campo único");
        
        // Solo para el menú de Pedidos
    if (titulo.equalsIgnoreCase("Pedidos")) {
        System.out.println("7. Crear Pedido Completo (Transacción)");
    }

        
        System.out.println("0. Volver");
        System.out.print("Ingrese una opción: ");
    }
}
