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
}


