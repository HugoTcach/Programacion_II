/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package utn.tfi.programacion2.dao;
import java.sql.Connection;
import java.util.List;
/**
 *
 * @author marco
 * @param <T>
 */
public interface GenericDao<T> {
    void save(T entity) throws Exception;

    T findById(Long id) throws Exception;

    List<T> findAll() throws Exception;

    void update(T entity) throws Exception;

    void delete(Long id) throws Exception;

    // 🔹 Permite guardar dentro de una transacción ya iniciada
    void saveTx(T entity, Connection conn) throws Exception;
}
