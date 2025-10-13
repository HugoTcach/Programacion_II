package tp8.ecommerce;

/**
 * Define el contrato para cualquier objeto que pueda recibir notificaciones.
 */
public interface Notificable {

    // El método recibirá un mensaje de tipo String con la notificación.
    void notificar(String mensaje);

}