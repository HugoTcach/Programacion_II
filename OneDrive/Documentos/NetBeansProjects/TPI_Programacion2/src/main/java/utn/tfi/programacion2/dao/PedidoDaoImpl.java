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
import utn.tfi.programacion2.entities.Pedido;

/**
 *
 * @author marco
 */
public class PedidoDaoImpl implements PedidoDao{

    @Override
    public void save(Pedido pedido) throws Exception {
        String sql = "INSERT INTO pedido (numero, fecha, cliente_nombre, total, estado, envio_id, eliminado) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setString(1, pedido.getNumero());
            pstmt.setDate(2, Date.valueOf(pedido.getFecha()));
            pstmt.setString(3, pedido.getClienteNombre());
            pstmt.setDouble(4, pedido.getTotal());
            pstmt.setString(5, pedido.getEstado().name());
            pstmt.setObject(6, pedido.getEnvio() != null ? pedido.getEnvio().getId() : null);
            pstmt.setBoolean(7, pedido.isEliminado());

            pstmt.executeUpdate();
        }       
    }

    @Override
    public Pedido findById(Long id) throws Exception {
        String sql = "SELECT * FROM pedido WHERE id = ?";
        Pedido pedido = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pedido = mapResultSetToPedido(rs);
                }
            }
        }

        return pedido;
    }

    @Override
    public List<Pedido> findAll() throws Exception {
        String sql = "SELECT * FROM pedido";
        List<Pedido> pedidos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                pedidos.add(mapResultSetToPedido(rs));
            }
        }
        
        return pedidos;
    }

    @Override
    public void update(Pedido pedido) throws Exception {
        String sql = "UPDATE pedido SET numero = ?, fecha = ?, cliente_nombre = ?, total = ?, estado = ?, envio = ?, eliminado = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, pedido.getNumero());
            pstmt.setDate(2, Date.valueOf(pedido.getFecha()));
            pstmt.setString(3, pedido.getClienteNombre());
            pstmt.setDouble(4, pedido.getTotal());
            pstmt.setString(5, pedido.getEstado().name());
            pstmt.setObject(6, pedido.getEnvio() != null ? pedido.getEnvio().getId() : null);
            pstmt.setBoolean(7, pedido.isEliminado());
            pstmt.setLong(8, pedido.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        String sql = "DELETE FROM pedido WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void saveTx(Pedido pedido, Connection conn) throws Exception {
        String sql = "INSERT INTO pedido (numero, fecha, cliente_nombre, total, estado, envio_id, eliminado) VALUES (?,?,?,?,?,?,?)";
        
        try ( PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1, pedido.getNumero());
            pstmt.setDate(2, Date.valueOf(pedido.getFecha()));
            pstmt.setString(3, pedido.getClienteNombre());
            pstmt.setDouble(4, pedido.getTotal());
            pstmt.setString(5, pedido.getEstado().name());
            pstmt.setObject(6, pedido.getEnvio() != null ? pedido.getEnvio().getId() : null);
            pstmt.setBoolean(7, pedido.isEliminado());
            
            pstmt.executeUpdate();
            
        }
    }

    private Pedido mapResultSetToPedido(ResultSet rs) throws SQLException {
    Pedido pedido = new Pedido();
    pedido.setId(rs.getLong("id"));
    pedido.setNumero(rs.getString("numero"));
    pedido.setFecha(rs.getDate("fecha").toLocalDate());
    pedido.setClienteNombre(rs.getString("cliente_nombre"));
    pedido.setTotal(rs.getDouble("total"));
    pedido.setEstado(Pedido.EstadoPedido.valueOf(rs.getString("estado")));
    pedido.setEliminado(rs.getBoolean("eliminado"));
    return pedido;
    }
    
}
