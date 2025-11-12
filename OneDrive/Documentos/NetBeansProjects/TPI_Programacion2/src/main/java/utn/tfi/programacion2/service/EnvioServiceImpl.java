package utn.tfi.programacion2.service;

import java.util.List;
import utn.tfi.programacion2.dao.EnvioDao;
import utn.tfi.programacion2.dao.EnvioDaoImpl;
import utn.tfi.programacion2.entities.Envio;

/*
 * Implementación de la interfaz EnvioService.
 * Esta clase actúa como intermediario entre la capa de negocio (servicios)
 * y la capa de acceso a datos (DAO). 
 * Aquí se pueden agregar validaciones antes de guardar o actualizar un Envío.
 */
public class EnvioServiceImpl implements EnvioService {

    private final EnvioDao envioDao;

    // Constructor que inicializa la implementación del DAO de Envíos.
    public EnvioServiceImpl() {
        this.envioDao = new EnvioDaoImpl();
    }

    // Guarda y valida un nuevo Envío en la base de datos.
    @Override
    public void save(Envio envio) throws Exception {
        validarEnvio(envio);
        envioDao.save(envio);
    }

    // Busca un Envío por su ID.
    @Override
    public Envio findById(Long id) throws Exception {
        return envioDao.findById(id);
    }

    // Devuelve una lista con todos los Envíos registrados.
    @Override
    public List<Envio> findAll() throws Exception {
        return envioDao.findAll();
    }

    // Actualiza y valida los datos de un Envío existente.
    @Override
    public void update(Envio envio) throws Exception {
        validarEnvio(envio);
        envioDao.update(envio);
    }

    // Elimina un Envío según su ID.
    @Override
    public void delete(Long id) throws Exception {
        envioDao.delete(id);
    }

    // Busca un Envío por su número de tracking (código de seguimiento)
    @Override
    public Envio findByTracking(String tracking) throws Exception {
        for (Envio e : envioDao.findAll()) {
            if (e.getTracking().equalsIgnoreCase(tracking)) {
                return e;
            }
        }
        return null;
    }

     /*
     * Valida que los datos del Envío sean correctos antes de guardar o actualizar.
     * Lanza una excepción con un mensaje descriptivo si algo no está bien.
     */
    private void validarEnvio(Envio envio) throws Exception {
        if (envio.getTracking() == null || envio.getTracking().isEmpty()) {
            throw new Exception("El tracking es obligatorio.");
        }
        if (envio.getEmpresa() == null) {
            throw new Exception("Debe especificar la empresa de envío.");
        }
        if (envio.getTipo() == null) {
            throw new Exception("Debe especificar el tipo de envío.");
        }
        if (envio.getCosto() <= 0) {
            throw new Exception("El costo debe ser mayor a 0.");
        }
    }
}

