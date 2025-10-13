package tp8.ecommerce;

// Cliente implementa la interfaz, por lo que debe tener el método notificar().
public class Cliente implements Notificable {

    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Implementación del método de la interfaz Notificable.
    // Simplemente imprime el mensaje en la consola.
    @Override
    public void notificar(String mensaje) {
        System.out.println("[NOTIFICACIÓN para " + this.nombre + "]: " + mensaje);
    }
}