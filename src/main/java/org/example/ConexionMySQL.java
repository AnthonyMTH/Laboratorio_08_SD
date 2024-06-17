package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    // Librería de MySQL
    private final String driver = "com.mysql.jdbc.Driver";

    // Nombre de la base datos
    private final String database = "proyecto_sd";

    // Servidor
    private final String hostname = "localhost";

    // Puerto
    private final String port = "3306";

    // Nombre de usuario
    private final String username = "root";

    // Clave de usuario
    private final String password = "nueva_Contraseña";

    public Connection conectarMySQL() {
        String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
        Connection conn;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return conn;
    }

    public void cerrarConexionMySQL(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println("Se ha cerrado la conexión MySQL");
    }
}
