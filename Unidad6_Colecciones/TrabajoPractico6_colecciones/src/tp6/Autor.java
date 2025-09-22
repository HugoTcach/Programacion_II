public class Autor {
    private String id;
    private String nombre;
    private String nacionalidad;

    // Constructor
    public Autor(String id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getNacionalidad() { return nacionalidad; }

    // Método para mostrar información
    public void mostrarInfo() {
        System.out.println("ID: " + id + ", Nombre: " + nombre + ", Nacionalidad: " + nacionalidad);
    }
}
