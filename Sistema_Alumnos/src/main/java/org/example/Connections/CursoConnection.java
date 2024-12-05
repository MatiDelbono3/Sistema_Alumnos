package org.example.Connections;

import org.example.DTO.Cursos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CursoConnection {
    private final Connections cn=new Connections();

    public boolean insertar(Cursos curso){
        String sql="insert into Cursos (nom_curso, nivel, cupo_maximo) VALUES ?, ?, ?";

        try (Connection conexion= cn.Connect();
            PreparedStatement ps = ((Connection) conexion).prepareStatement(sql)){
            ps.setString(1, curso.getNombre_curso());
            ps.setString(2, curso.getNivel());
            ps.setInt(3, curso.getCupo_Maximo());
            int n=ps.executeUpdate();
            if (n != 0){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}

