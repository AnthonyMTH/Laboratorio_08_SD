package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EngineerServiceGUI extends JFrame {
    private JTextField codigoPro;
    private JButton visualizarButton;
    private JList lista;
    private JPanel consulta;
    private ConexionMySQL mySQLConnection;
    private Connection connection;

    DefaultListModel mod = new DefaultListModel();

    public EngineerServiceGUI() {
        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mySQLConnection = new ConexionMySQL();
                connection = mySQLConnection.conectarMySQL();
                lista.setModel(mod);
                mod.removeAllElements();

                String query = "SELECT Ingenieros.* FROM Ingenieros " +
                        "JOIN Proyectos ON Ingenieros.IngCod = Proyectos.ProIngCod " +
                        "WHERE Proyectos.ProCod = ?";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setInt(1, Integer.parseInt(codigoPro.getText()));
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            mod.addElement(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public static void main(String[] args) {
        EngineerServiceGUI es = new EngineerServiceGUI();
        es.setContentPane(new EngineerServiceGUI().consulta);
        es.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        es.setVisible(true);
        es.pack();
    }
}
