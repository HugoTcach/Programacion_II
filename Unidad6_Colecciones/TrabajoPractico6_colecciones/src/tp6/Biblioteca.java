import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private List<Libro> libros;

    // Constructor
    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
    }

    // Método para agregar libro
    public void agregarLibro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        Libro libro = new Libro(isbn, titulo, anioPublicacion, autor);
        libros.add(libro);
    }

    // Listar todos los libros
    public void listarLibros() {
        for (Libro l : libros) {
            l.mostrarInfo();
        }
    }

    // Buscar libro por ISBN
    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    // Eliminar libro por ISBN
    public void eliminarLibro(String isbn) {
        libros.removeIf(l -> l.getIsbn().equals(isbn));
    }

    // Cantidad total de libros
    public int obtenerCantidadLibros() {
        return libros.size();
    }

    // Filtrar libros por año
    public void filtrarLibrosPorAnio(int anio) {
        for (Libro l : libros) {
            if (l.getAnioPublicacion() == anio) {
                l.mostrarInfo();
            }
        }
    }

    // Mostrar autores disponibles (sin repetir)
    public void mostrarAutoresDisponibles() {
        List<Autor> autoresMostrados = new ArrayList<>();
        for (Libro l : libros) {
            Autor autor = l.getAutor();
            if (!autoresMostrados.contains(autor)) {
                autor.mostrarInfo();
                autoresMostrados.add(autor);
            }
        }
    }
}
