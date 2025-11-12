
package utn.tfi.programacion2.service;

import java.util.List;

// Interfaz genérica para la capa Service.

// Define las operaciones básicas de negocio (CRUD)
// que pueden aplicarse a cualquier entidad (Pedido, Envío, etc.).

public interface GenericService<T> {
    
     /*
     * Guarda una nueva entidad en la base de datos.
     * Antes de guardar, el Service debería validar los datos.
     */
    void save(T entity) throws Exception;
    
    // Busca una entidad por su identificador (ID).
    T findById(Long id) throws Exception;
    
    // Retorna todas las entidades del tipo T almacenadas en la base de datos.
    List<T> findAll() throws Exception;
    
    // Actualiza una entidad existente en la base de datos.
    void update(T entity) throws Exception;
    
    // Elimina una entidad por su ID.
    void delete(Long id) throws Exception;
}
