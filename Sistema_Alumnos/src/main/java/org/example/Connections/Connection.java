package org.example.Connections;

import javax.swing.*;
import java.sql.DriverManager;

public class Connection {
    Connection conexion;
    String  url="jdbc:mysql://localhost:3306/BdAlumnos";
    String user="root";
    String password="123456";
    public Connection Connect(){
        try{
            Class.forName("org.git.mm.mysql.Driver");
            conexion= (Connection) DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            JOptionPane.showConfirmDialog(null, e);
        }
        return conexion;
    }
}
