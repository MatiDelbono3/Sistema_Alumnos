package org.example.Connections;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connections implements AutoCloseable{
    Connection conexion = null;
    String  url="jdbc:mysql://localhost:3306/BdAlumnos";
    String user="root";
    String password="123456";
    public Connection Connect(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion=  DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            JOptionPane.showConfirmDialog(null, e);
        }
        return conexion;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException  {
        if (conexion != null) {
            return conexion.prepareStatement(sql);
        }
        throw new SQLException("No hay conexión establecida");
    }

    @Override
    public void close() throws Exception {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
    }
}
}


