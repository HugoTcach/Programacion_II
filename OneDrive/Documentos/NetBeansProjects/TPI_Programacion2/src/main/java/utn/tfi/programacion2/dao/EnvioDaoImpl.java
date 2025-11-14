/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utn.tfi.programacion2.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utn.tfi.programacion2.config.DatabaseConnection;
import utn.tfi.programacion2.entities.Envio;

/**
 *
 * @author marco
 */
public class EnvioDaoImpl implements EnvioDao{

    @Override
    public void save(Envio envio) throws Exception {
        String sql = "INSERT INTO envio (tracking, empresa, tipo, costo, fecha_despacho, fecha_estimada, estado, eliminado)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, envio.getTracking());
            pstmt.setString(2, envio.getEmpresa().name());
            pstmt.setString(3, envio.getTipo().name());
            pstmt.setDouble(4, envio.getCosto());
            pstmt.setDate(5, Date.valueOf(envio.getFechaDespacho()));
            pstmt.setDate(6, Date.valueOf(envio.getFechaEstimada()));
            pstmt.setString(7, envio.getEstado().name());
            pstmt.setBoolean(8, envio.isEliminado());
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public Envio findById(Long id) throws Exception {
        String sql = "SELECT * FROM envio WHERE id =?";
        Envio envio = null;
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    envio = mapResultSetToEnvio(rs);
                }
            }
        }
        return envio;
    }

    @Override
    public List<Envio> findAll() throws Exception {
        String sql = "SELECT * FROM envio";
        List<Envio> envios = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql); 
                ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()){
                envios.add(mapResultSetToEnvio(rs));
            }
        }
        return envios;
    }

    @Override
    public void update(Envio envio) throws Exception {
        String sql = "UPDATE envio SET tracking = ?, empresa =?, tipo=?, costo=?, fecha_despacho=?, fecha_estimada=?, estado=?, eliminado =?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, envio.getTracking());
            pstmt.setString(2, envio.getEmpresa().name());
            pstmt.setString(3, envio.getTipo().name());
            pstmt.setDouble(4, envio.getCosto());
            pstmt.setDate(5, Date.valueOf(envio.getFechaDespacho()));
            pstmt.setDate(6, Date.valueOf(envio.getFechaEstimada()));
            pstmt.setString(7, envio.getEstado().name());
            pstmt.setBoolean(8, envio.isEliminado());
            pstmt.setLong(9, envio.getId());
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        String sql = "DELETE FROM envio WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeQuery();
        }
    }

    @Override
    public void saveTx(Envio envio, Connection conn) throws Exception {
        String sql = "INSERT INTO envio (tracking, empresa, tipo, costo, fecha_despacho, fecha_estimada, estado, eliminado)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, envio.getTracking());
            pstmt.setString(2, envio.getEmpresa().name());
            pstmt.setString(3, envio.getTipo().name());
            pstmt.setDouble(4, envio.getCosto());
            pstmt.setDate(5, Date.valueOf(envio.getFechaDespacho()));
            pstmt.setDate(6, Date.valueOf(envio.getFechaEstimada()));
            pstmt.setString(7, envio.getEstado().name());
            pstmt.setBoolean(8, envio.isEliminado());
            
            pstmt.executeUpdate();
        }
    }
    
    // Método auxiliar para mapear un ResultSet a un objeto Envio
    private Envio mapResultSetToEnvio(ResultSet rs) throws SQLException {
        Envio envio = new Envio();
        envio.setId(rs.getLong("id"));
        envio.setTracking(rs.getString("tracking"));
        envio.setEmpresa(Envio.Empresa.valueOf(rs.getString("empresa")));
        envio.setTipo(Envio.Tipo.valueOf(rs.getString("tipo")));
        envio.setCosto(rs.getDouble("costo"));
        envio.setFechaDespacho(rs.getDate("fechaDespacho").toLocalDate());
        envio.setFechaEstimada(rs.getDate("fechaEstimada").toLocalDate());
        envio.setEstado(Envio.EstadoEnvio.valueOf(rs.getString("estado")));
        envio.setEliminado(rs.getBoolean("eliminado"));
        return envio;
    }
}
