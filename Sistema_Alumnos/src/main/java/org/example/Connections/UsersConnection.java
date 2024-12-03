package org.example.Connections;

import org.example.DTO.Usuarios;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersConnection {
    Connection conexion;
    Connection cn=new Connection();
    PreparedStatement ps;
    ResultSet rs;

public Usuarios login(String user, String password){
    Usuarios newUser=new Usuarios();
    String sql=("select * from Usuarios where usuario=" + user + "and password=" + password);
    try {
        conexion=cn.Connect();
        rs= ps.executeQuery();
        while (rs.next()){
            newUser.setId(rs.getInt(1));
            newUser.setNombre(rs.getString(2));
            newUser.setApellido(rs.getString(3));
            newUser.setUsuario(rs.getString(4));
            newUser.setContrasena(rs.getString(5));
        }
    }catch (Exception e){
        JOptionPane.showConfirmDialog(null, e);
    }
    return newUser;
}
}