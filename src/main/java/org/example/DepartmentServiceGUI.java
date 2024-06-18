package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DepartmentServiceGUI extends JFrame {
    private JList consultarDep;
    private JTextField codDep;
    private JTextField nomDep;
    private JTextField telDep;
    private JTextField faxDep;
    private JButton crearButton;
    private JTextField telActDep;
    private JButton actualizarButton;
    private JTextField codEliDep;
    private JButton eliminarButton;
    private JButton consultarButton;
    private JTextField codActDep;
    private JPanel CRUD;
    private JTextField faxActDep;
    private JTextField nomActDep;
    private ConexionMySQL mySQLConnection;
    private Connection connection;

    DefaultListModel mod = new DefaultListModel();

    public DepartmentServiceGUI() {
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mySQLConnection = new ConexionMySQL();
                connection = mySQLConnection.conectarMySQL();
                DepartmentService departmentService = new DepartmentService(connection);
                try {
                    departmentService.insertDepartment(Integer.parseInt(codDep.getText()), nomDep.getText(), telDep.getText(), faxDep.getText());
                    consultarDep.setModel(mod);
                    mod.removeAllElements();
                    mod.addElement("Inserción exitosa");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mySQLConnection = new ConexionMySQL();
                connection = mySQLConnection.conectarMySQL();
                consultarDep.setModel(mod);
                mod.removeAllElements();

                try {
                    String query = "SELECT * FROM Departamentos";
                    try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            mod.addElement(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mySQLConnection = new ConexionMySQL();
                connection = mySQLConnection.conectarMySQL();
                DepartmentService departmentService = new DepartmentService(connection);
                try {
                    departmentService.updateDepartment(Integer.parseInt(codActDep.getText()), nomActDep.getText(), telActDep.getText(), faxActDep.getText());
                    consultarDep.setModel(mod);
                    mod.removeAllElements();
                    mod.addElement("Actualización exitosa");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mySQLConnection = new ConexionMySQL();
                connection = mySQLConnection.conectarMySQL();
                DepartmentService departmentService = new DepartmentService(connection);
                try {
                    departmentService.deleteDepartment(Integer.parseInt(codEliDep.getText()));
                    consultarDep.setModel(mod);
                    mod.removeAllElements();
                    mod.addElement("Eliminación exitosa");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public static void main(String[] args) {
        DepartmentServiceGUI dp = new DepartmentServiceGUI();
        dp.setContentPane(new DepartmentServiceGUI().CRUD);
        dp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dp.setVisible(true);
        dp.pack();
    }
}

