package dao;

import entidad.Orden;

import java.sql.*;

public class OrdenDAO {
    private final String url = "jdbc:sqlite:ordenes.db";

    public OrdenDAO() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS orden (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "cliente_id INTEGER," +
                         "producto_id INTEGER," +
                         "cantidad INTEGER)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertar(Orden orden) {
        String sql = "INSERT INTO orden(cliente_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orden.getClienteId());
            pstmt.setInt(2, orden.getProductoId());
            pstmt.setInt(3, orden.getCantidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
