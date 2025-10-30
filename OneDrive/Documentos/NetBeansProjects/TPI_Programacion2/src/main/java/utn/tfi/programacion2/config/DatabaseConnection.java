package utn.tfi.programacion2.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de Conexión.
 * Gestiona la conexión JDBC a la base de datos.
 * (Requerido por las consignas)
 */
public class DatabaseConnection {

    // --- Credenciales de tu BD (Paso 5.1) ---
    private static final String HOST = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DATABASE = "tfi_programacion2_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Summer348!"; // Tu contraseña
    // ----------------------------------------

    // URL de conexión completa
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

    /**
     * Método estático que retorna una conexión a la BD.
     * La consigna pide que esta conexión sea usada por la capa de Service
     * para manejar las transacciones (commit/rollback).
     * * @return java.sql.Connection
     * @throws SQLException Si falla la conexión o no encuentra el driver.
     */
    public static Connection getConnection() throws SQLException {

        // 1. Opcional pero recomendado: Registrar el driver de MySQL
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
             System.err.println("Error: No se encontró el Driver JDBC de MySQL.");
             throw new SQLException("Driver no encontrado", e);
        }

        // 2. Retornar la conexión
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}