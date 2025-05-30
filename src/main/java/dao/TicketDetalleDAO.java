package dao;

import entidad.TicketDetalle;
import database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDetalleDAO {

    public boolean insertar(TicketDetalle detalle) {
        String sql = "INSERT INTO ticket_detalles (id_Ticket, id_Producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, detalle.getIdTicket());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getSubtotal());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(TicketDetalle detalle) {
        String sql = "UPDATE ticket_detalles SET id_Ticket = ?, id_Producto = ?, cantidad = ?, subtotal = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, detalle.getIdTicket());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getSubtotal());
            ps.setInt(5, detalle.getId());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM ticket_detalles WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public TicketDetalle obtenerPorId(int id) {
        String sql = "SELECT * FROM ticket_detalles WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TicketDetalle(
                    rs.getInt("id"),
                    rs.getInt("idTicket"),
                    rs.getInt("idProducto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("subtotal")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TicketDetalle> listar() {
        List<TicketDetalle> lista = new ArrayList<>();
        String sql = "SELECT * FROM ticket_detalles";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new TicketDetalle(
                    rs.getInt("id"),
                    rs.getInt("id_Ticket"),
                    rs.getInt("id_Producto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("subtotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
