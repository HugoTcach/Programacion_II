package utn.tfi.programacion2.main;

public class MenuDisplay {

    public static void mostrarMenuPrincipal() {
        System.out.println("\n========= MENU PRINCIPAL =========");
        System.out.println("1. Gestionar Pedidos");
        System.out.println("2. Gestionar Envios");
        System.out.println("0. Salir");
        System.out.print("Ingrese una opcion: ");
    }

    public static void mostrarSubMenu(String titulo) {
        String tituloNormalizado = titulo.trim().toLowerCase();

        System.out.println("\n===== " + titulo.toUpperCase() + " =====");
        System.out.println("1. Crear");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar");
        System.out.println("4. Actualizar");
        System.out.println("5. Eliminar (baja logica)");
        System.out.println("6. Buscar por campo unico");

        // Solo para el menu de Pedidos
        if (tituloNormalizado.equals("pedidos")) {
            System.out.println("7. Crear Pedido Completo (Transaccion)");
        }

        System.out.println("0. Volver");
        System.out.print("Ingrese una opcion: ");
    }
}
