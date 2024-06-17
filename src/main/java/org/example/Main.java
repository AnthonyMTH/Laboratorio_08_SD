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
                DepartmentService departmentService = new DepartmentService(connection);

                // Insertar registros
                departmentService.insertDepartment(4, "Finanzas", "456789012", "654321098");
                departmentService.insertDepartment(5, "Legal", "567890123", "543210987");

                // Actualizar un registro
                departmentService.updateDepartment(4, "Finanzas y Contabilidad", "456789012", "654321098");

                // Eliminar un registro
                departmentService.deleteDepartment(5);

                // Consultar todos los departamentos
                System.out.println("Todos los departamentos:");
                departmentService.getAllDepartments();

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