package tp8.ecommerce;

// La palabra "implements" conecta la clase con la interfaz.
// Obliga a Producto a tener todos los métodos definidos en Pagable.
public class Producto implements Pagable {

    // Atributos o propiedades de la clase
    private String nombre;
    private double precio;

    // Constructor: un método especial para crear objetos de tipo Producto.
    // Se le debe pasar un nombre y un precio al crearlo.
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Métodos "getter" para poder consultar los valores desde fuera de la clase.
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    // Implementación OBLIGATORIA del método de la interfaz Pagable.
    // Para un producto, su total es simplemente su precio.
    // La anotación @Override es una buena práctica, le dice al compilador
    // que estamos sobrescribiendo un método de una interfaz o clase padre.
    @Override
    public double calcularTotal() {
        return this.precio;
    }
}