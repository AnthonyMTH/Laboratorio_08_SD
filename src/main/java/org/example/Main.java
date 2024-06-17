package org.example;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ConexionMySQL mySQLConnection = new ConexionMySQL();
        Connection connection = mySQLConnection.conectarMySQL();

        if (connection != null) {
            System.out.println("La conexión con MySql ha sido establecida!");

            try {
                ProjectService projectService = new ProjectService(connection);
                projectService.getProjectsByDepartment(2);

                EngineerService engineerService = new EngineerService(connection);
                engineerService.getEngineersByProject(1);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                mySQLConnection.cerrarConexionMySQL(connection);
            }
        } else {
            System.out.println("Error al establecer la conexión con MySql!");
        }
    }
}