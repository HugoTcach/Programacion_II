package entidades;

public class Pasaporte {

    private String numero;
    private String fechaEmision;
    private Foto foto; // relación de composición con Foto
    private Titular titular; // relación bidireccional con Titular

    // Constructor
    public Pasaporte(String numero, String fechaEmision, String imagenFoto, String formatoFoto) {
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.foto = new Foto(imagenFoto, formatoFoto); // se crea la Foto dentro de Pasaporte
    }

    // Getters y setters
    public Foto getFoto() { return foto; }
    public Titular getTitular() { return titular; }

    public void setTitular(Titular titular) {
        this.titular = titular;
        if (titular.getPasaporte() != this) {
            titular.setPasaporte(this);
        }
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(String fechaEmision) { this.fechaEmision = fechaEmision; }
}
