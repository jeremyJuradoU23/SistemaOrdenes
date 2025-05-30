package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ticketdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";         // Cambia esto si usas otro usuario
    private static final String PASSWORD = "";         // Cambia esto si usas contraseña

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

