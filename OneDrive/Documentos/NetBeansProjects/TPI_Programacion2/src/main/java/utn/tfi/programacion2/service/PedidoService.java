
package utn.tfi.programacion2.service;

import utn.tfi.programacion2.entities.Pedido;

/*
 * Interfaz específica para la entidad Pedido.

 * Extiende GenericService<Pedido> e incorpora métodos
 * que reflejan reglas de negocio más complejas relacionadas con los pedidos.
 */

public interface PedidoService extends GenericService<Pedido> {
    
     /*
     * Crea un pedido completo junto con su Envío asociado.

     * Este método debe manejar una transacción:
     *  - Se desactiva el autoCommit con setAutoCommit(false)
     *  - Se insertan los datos de Pedido y Envío usando la misma conexión
     *  - Si todo sale bien, se ejecuta commit()
     *  - Si ocurre una excepción, se ejecuta rollback()
     */
    void crearPedidoCompleto(Pedido pedido) throws Exception;
    
    /*
     * Busca un pedido por su número.
     * Este método es útil para validar la unicidad del número de pedido
     * antes de guardarlo en la base de datos.
     */
    Pedido findByNumero(String numero) throws Exception;
}
