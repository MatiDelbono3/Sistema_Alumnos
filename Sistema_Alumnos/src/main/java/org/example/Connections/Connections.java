package org.example.Connections;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connections {
    Connection conexion = null;
    String  url="jdbc:mysql://127.0.0.1:3307/BdAlumnos";
    String user="root";
    String password="";
    public Connection Connect(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion=  DriverManager.getConnection(url, user, password);
            conexion.setAutoCommit(true);
        }catch (Exception e){
            JOptionPane.showConfirmDialog(null, e);
        }
        return conexion;
    }


    public  void closeConnection() {

            try {
                if (conexion != null && !conexion.isClosed()){
                conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                e.printStackTrace(); // Mostrar el error si ocurre al cerrar
            }

}
    public boolean probarConexion() {
        String url = "jdbc:mysql://localhost:3307/BdAlumnos"; // Ajusta según tu base de datos
        String usuario = "root";
        String contrasena = "123456";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena)) {
            if (connection != null) {
                JOptionPane.showMessageDialog(null, "Conexión exitosa");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar: " + e.getMessage());
            return false;
        }
    }
}



