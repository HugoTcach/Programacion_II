package tp6;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // Crear productos
        Producto p1 = new Producto("P001", "Leche", 1200, 10, CategoriaProducto.ALIMENTOS);
        Producto p2 = new Producto("P002", "Notebook", 2500, 5, CategoriaProducto.ELECTRONICA);
        Producto p3 = new Producto("P003", "Remera", 1500, 20, CategoriaProducto.ROPA);
        Producto p4 = new Producto("P004", "Silla", 1800, 8, CategoriaProducto.HOGAR);
        Producto p5 = new Producto("P005", "Arroz", 800, 50, CategoriaProducto.ALIMENTOS);

        // Agregar productos al inventario
        inventario.agregarProducto(p1);
        inventario.agregarProducto(p2);
        inventario.agregarProducto(p3);
        inventario.agregarProducto(p4);
        inventario.agregarProducto(p5);

        // Listar todos los productos
        System.out.println("=== Todos los productos ===");
        inventario.listarProductos();

        // Buscar un producto por ID
        System.out.println("\n=== Buscar producto P002 ===");
        Producto buscado = inventario.buscarProductoPorId("P002");
        if (buscado != null) buscado.mostrarInfo();

        // Filtrar por categoría
        System.out.println("\n=== Productos ALIMENTOS ===");
        inventario.filtrarPorCategoria(CategoriaProducto.ALIMENTOS);

        // Eliminar un producto
        System.out.println("\n=== Eliminar P003 y listar ===");
        inventario.eliminarProducto("P003");
        inventario.listarProductos();

        // Actualizar stock
        System.out.println("\n=== Actualizar stock P004 ===");
        inventario.actualizarStock("P004", 15);
        inventario.listarProductos();

        // Total stock
        System.out.println("\n=== Total stock ===");
        System.out.println(inventario.obtenerTotalStock());

        // Producto con mayor stock
        System.out.println("\n=== Producto con mayor stock ===");
        Producto mayor = inventario.obtenerProductoConMayorStock();
        if (mayor != null) mayor.mostrarInfo();

        // Filtrar por precio
        System.out.println("\n=== Productos entre $1000 y $3000 ===");
        inventario.filtrarProductosPorPrecio(1000, 3000);

        // Mostrar categorías disponibles
        System.out.println("\n=== Categorías disponibles ===");
        inventario.mostrarCategoriasDisponibles();
    }
}
