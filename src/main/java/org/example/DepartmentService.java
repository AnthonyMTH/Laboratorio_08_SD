package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentService {
    private Connection conn;

    public DepartmentService(Connection conn) {
        this.conn = conn;
    }

    public void insertDepartment(int depCod, String depNom, String depTel, String depFax) throws SQLException {
        String query = "INSERT INTO Departamentos (DepCod, DepNom, DepTel, DepFax) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, depCod);
            stmt.setString(2, depNom);
            stmt.setString(3, depTel);
            stmt.setString(4, depFax);
            stmt.executeUpdate();
        }
    }

    public void updateDepartment(int depCod, String depNom, String depTel, String depFax) throws SQLException {
        String query = "UPDATE Departamentos SET DepNom = ?, DepTel = ?, DepFax = ? WHERE DepCod = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, depNom);
            stmt.setString(2, depTel);
            stmt.setString(3, depFax);
            stmt.setInt(4, depCod);
            stmt.executeUpdate();
        }
    }

    public void deleteDepartment(int depCod) throws SQLException {
        String query = "DELETE FROM Departamentos WHERE DepCod = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, depCod);
            stmt.executeUpdate();
        }
    }

    public void getAllDepartments() throws SQLException {
        String query = "SELECT * FROM Departamentos";
        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("DepCod: " + rs.getInt("DepCod") +
                        ", DepNom: " + rs.getString("DepNom") +
                        ", DepTel: " + rs.getString("DepTel") +
                        ", DepFax: " + rs.getString("DepFax"));
            }
        }
    }
}
