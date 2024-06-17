package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectService {
    private Connection conn;

    public ProjectService(Connection conn) {
        this.conn = conn;
    }

    public void getProjectsByDepartment(int depCod) throws SQLException {
        String query = "SELECT * FROM Proyectos WHERE ProDepCod = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, depCod);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ProCod: " + rs.getInt("ProCod") +
                            ", ProNom: " + rs.getString("ProNom") +
                            ", ProFecIni: " + rs.getDate("ProFecIni") +
                            ", ProFecTer: " + rs.getDate("ProFecTer"));
                }
            }
        }
    }
}

