
package utn.tfi.programacion2.service;

import utn.tfi.programacion2.entities.Envio;

/*
 * Interfaz específica para la entidad Envío.

 * Hereda los métodos CRUD de GenericService<Envio>
 * y agrega operaciones propias del negocio relacionadas con envíos.
 */

public interface EnvioService extends GenericService<Envio>{
    
     /*
     * Busca un envío por su número de tracking.
     * Este método es importante para validar la unicidad del tracking
     * antes de guardar un nuevo Envío en la base de datos.
     */
    Envio findByTracking(String tracking) throws Exception;
    

}
