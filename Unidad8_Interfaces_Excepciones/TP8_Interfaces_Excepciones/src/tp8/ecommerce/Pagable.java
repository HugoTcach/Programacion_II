package tp8.ecommerce;

/**
 * Define el contrato para cualquier objeto que pueda ser "pagable",
 * es decir, que tenga un costo total calculable.
 */
public interface Pagable {

    // Este es el método que todas las clases que implementen Pagable
    // estarán obligadas a desarrollar.
    // No lleva llaves {} porque solo se declara, no se implementa aquí.
    public double calcularTotal();

}