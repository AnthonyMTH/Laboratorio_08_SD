package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EngineerService {
    private Connection conn;

    public EngineerService(Connection conn) {
        this.conn = conn;
    }

    public void getEngineersByProject(int proCod) throws SQLException {
        String query = "SELECT Ingenieros.* FROM Ingenieros " +
                "JOIN Proyectos ON Ingenieros.IngCod = Proyectos.ProIngCod " +
                "WHERE Proyectos.ProCod = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, proCod);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("IngCod: " + rs.getInt("IngCod") +
                            ", IngNom: " + rs.getString("IngNom") +
                            ", IngEsp: " + rs.getString("IngEsp") +
                            ", IngCargo: " + rs.getString("IngCargo"));
                }
            }
        }
    }
}
