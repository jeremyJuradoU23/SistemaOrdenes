package dao;

import entidad.Cliente;
import database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Ahora devuelve int, que es el id generado del cliente insertado
   public boolean insertar(Cliente cliente) {
    String sql = "INSERT INTO clientes (nombre, email, telefono) VALUES (?, ?, ?)";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
         
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getEmail());
        ps.setString(3, cliente.getTelefono());
        
        int filas = ps.executeUpdate();
        
        if (filas > 0) {
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                cliente.setId(keys.getInt(1));  // Asignamos el id generado al objeto
            }
            return true;
        } else {
            return false;
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, email = ?, telefono = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getId());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
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

    public Cliente obtenerPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
