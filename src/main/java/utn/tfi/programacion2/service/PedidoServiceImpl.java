
package utn.tfi.programacion2.service;

import java.sql.Connection;
import java.util.List;
import utn.tfi.programacion2.config.DatabaseConnection;
import utn.tfi.programacion2.dao.EnvioDao;
import utn.tfi.programacion2.dao.EnvioDaoImpl;
import utn.tfi.programacion2.dao.PedidoDao;
import utn.tfi.programacion2.dao.PedidoDaoImpl;
import utn.tfi.programacion2.entities.Envio;
import utn.tfi.programacion2.entities.Pedido;

/*
 * Implementación de la capa de negocio para la entidad Pedido.

 * Aplica reglas de negocio, validaciones y maneja transacciones.
 * 
 * - Utiliza DAOs (PedidoDao y EnvioDao) para interactuar con la base de datos.
 * - Contiene lógica para crear un pedido completo (Pedido + Envío) dentro de una transacción.
 */

public class PedidoServiceImpl implements PedidoService {
    private final PedidoDao pedidoDao;
    private final EnvioDao envioDao;

    // Constructor: inicializa los DAOs que maneja este servicio.
    public PedidoServiceImpl() {
        this.pedidoDao = new PedidoDaoImpl();
        this.envioDao = new EnvioDaoImpl();
    }
    
    // Guarda y valida un nuevo pedido.
    @Override
    public void save(Pedido pedido) throws Exception {
        validarPedido(pedido);
        pedidoDao.save(pedido);
    }
    
    //Busca un pedido por su ID.
    @Override
    public Pedido findById(Long id) throws Exception {
        return pedidoDao.findById(id);
    }

    // Devuelve todos los pedidos existentes.
    @Override
    public List<Pedido> findAll() throws Exception {
        return pedidoDao.findAll();
    }

    // Actualiza un pedido existente, previa validación de los datos.
    @Override
    public void update(Pedido pedido) throws Exception {
        validarPedido(pedido);
        pedidoDao.update(pedido);
    }
    
    // Elimina un pedido según su ID.
    @Override
    public void delete(Long id) throws Exception {
        pedidoDao.delete(id);
    }

    // Busca un pedido por su número.
    @Override
    public Pedido findByNumero(String numero) throws Exception {
        for (Pedido p : pedidoDao.findAll()) {
            if (p.getNumero().equalsIgnoreCase(numero)) {
                return p;
            }
        }
        return null;
    }

    /*
     * Crea un Pedido completo con su Envío asociado dentro de una transacción.
     * ------------------------------------------------------------------------
     * - Usa la misma conexión para ambos DAOs.
     * - Si alguno de los insert falla, se hace rollback (se deshacen los cambios).
     * - Si todo va bien, se hace commit.
     */
    @Override
    public void crearPedidoCompleto(Pedido pedido) throws Exception {
        validarPedido(pedido);
        validarEnvio(pedido.getEnvio());

        Connection conn = null;
        try {
            // 1) Obtener conexión y desactivar autoCommit
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // 2) Guardar primero el envío (tiene relación con el pedido)
            envioDao.saveTx(pedido.getEnvio(), conn);

            // ️3) Asociar el envío al pedido antes de guardarlo
            pedido.setEnvio(pedido.getEnvio());

            // 4) Guardar el pedido con la misma conexión
            pedidoDao.saveTx(pedido, conn);

            // 5) Confirmar la transacción si todo salió bien
            conn.commit();
            
        } catch (Exception e) {
            // 6) Si hay error, deshacer los cambios
            if (conn != null) conn.rollback();
            throw new Exception("Error al crear pedido completo: " + e.getMessage(), e);
            
            // 7) Se restaural al estado original de la conexión
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    /*
     * Valida los datos del pedido antes de guardarlo o actualizarlo.
     * Aplica las reglas de negocio básicas.
     */
    private void validarPedido(Pedido pedido) throws Exception {
        if (pedido.getNumero() == null || pedido.getNumero().isEmpty())
            throw new Exception("El número de pedido es obligatorio.");
        if (pedido.getClienteNombre() == null || pedido.getClienteNombre().isEmpty())
            throw new Exception("El nombre del cliente es obligatorio.");
        if (pedido.getTotal() <= 0)
            throw new Exception("El total debe ser mayor que cero.");
        if (pedido.getEstado() == null)
            throw new Exception("Debe indicar el estado del pedido.");
    }

    private void validarEnvio(Envio envio) throws Exception {
        if (envio == null)
            throw new Exception("Debe asociar un envío al pedido.");
        if (envio.getTracking() == null || envio.getTracking().isEmpty())
            throw new Exception("El tracking del envío es obligatorio.");
    }
    

}
