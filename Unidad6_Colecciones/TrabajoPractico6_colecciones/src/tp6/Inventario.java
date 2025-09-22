package tp6;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void listarProductos() {
        for (Producto p : productos) {
            p.mostrarInfo();
        }
    }

    public Producto buscarProductoPorId(String id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    public void eliminarProducto(String id) {
        productos.removeIf(p -> p.getId().equals(id));
    }

    public void actualizarStock(String id, int nuevaCantidad) {
        Producto p = buscarProductoPorId(id);
        if (p != null) {
            p.setCantidad(nuevaCantidad);
        }
    }

    public void filtrarPorCategoria(CategoriaProducto categoria) {
        for (Producto p : productos) {
            if (p.getCategoria() == categoria) p.mostrarInfo();
        }
    }

    public int obtenerTotalStock() {
        int total = 0;
        for (Producto p : productos) {
            total += p.getCantidad();
        }
        return total;
    }

    public Producto obtenerProductoConMayorStock() {
        if (productos.isEmpty()) return null;
        Producto mayor = productos.get(0);
        for (Producto p : productos) {
            if (p.getCantidad() > mayor.getCantidad()) mayor = p;
        }
        return mayor;
    }

    public void filtrarProductosPorPrecio(double min, double max) {
        for (Producto p : productos) {
            if (p.getPrecio() >= min && p.getPrecio() <= max) p.mostrarInfo();
        }
    }

    public void mostrarCategoriasDisponibles() {
        for (Producto p : productos) {
            System.out.println(p.getCategoria() + " - " + p.getCategoria().getDescripcion());
        }
    }
}
