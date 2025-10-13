package tp8.excepciones;

// Para crear nuestra propia excepción, la clase debe HEREDAR de Exception
// o de alguna de sus clases hijas.
public class EdadInvalidaException extends Exception {

    /**
     * Este es el constructor. Recibe un mensaje de error y se lo pasa
     * al constructor de la clase padre (Exception) usando "super()".
     */
    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
}