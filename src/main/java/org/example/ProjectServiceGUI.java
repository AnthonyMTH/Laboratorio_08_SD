package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectServiceGUI extends JFrame {
    private JTextField codigoDep;
    private JButton visualizarButton;
    private JList lista;
    private JPanel consulta;
    private ConexionMySQL mySQLConnection;
    private Connection connection;

    DefaultListModel mod = new DefaultListModel();

    public ProjectServiceGUI() {
        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mySQLConnection = new ConexionMySQL();
                connection = mySQLConnection.conectarMySQL();
                lista.setModel(mod);
                mod.removeAllElements();

                String query = "SELECT * FROM Proyectos WHERE ProDepCod = ?";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setInt(1, Integer.parseInt(codigoDep.getText()));
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
        ProjectServiceGUI es = new ProjectServiceGUI();
        es.setContentPane(new ProjectServiceGUI().consulta);
        es.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        es.setVisible(true);
        es.pack();
    }
}
