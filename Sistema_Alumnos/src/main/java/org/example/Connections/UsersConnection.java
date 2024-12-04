package org.example.Connections;

import org.example.DTO.Usuarios;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersConnection {

    private final Connections cn=new Connections();


public Usuarios login(String user, String password){
    Usuarios newUser=new Usuarios();
    String sql=("select * from Usuarios where usuario= ?  AND contrasena= ?" );
    try (Connection conexion = cn.Connect();
         PreparedStatement ps = ((Connection) conexion).prepareStatement(sql)) {
        ps.setString(1, user);
        ps.setString(2, password);

        try(ResultSet rs= ps.executeQuery()) {
            while (rs.next()) {
                newUser.setId(rs.getInt(1));
                newUser.setNombre(rs.getString(2));
                newUser.setApellido(rs.getString(3));
                newUser.setUsuario(rs.getString(4));
                newUser.setContrasena(rs.getString(5));
            }
        }
    } catch (Exception e) {
        JOptionPane.showConfirmDialog(null, e);
    }
    return newUser;
}
}