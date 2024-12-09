package org.example.Connections;

import org.example.DTO.Cursos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CursoConnection {
    private final Connections cn=new Connections();

    public int insertar(Cursos curso){
        String sql="insert into cursos (nombre_curso, nivel, cupo_maximo) VALUES (?, ?, ?)";

        try (Connection conexion= cn.Connect();
            PreparedStatement ps = ((Connection) conexion).prepareStatement(sql)){
            ps.setString(1, curso.getNombre_curso());
            ps.setString(2, curso.getNivel());
            ps.setInt(3, curso.getCupo_Maximo());
            int n=ps.executeUpdate();
            System.out.println("Número de filas afectadas: " + n); // Depuración
            return n;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return 0;
        }
    }
    public boolean probarConexion() {
        String url = "jdbc:mysql://localhost:3306/BdAlumnos"; // Ajusta según tu base de datos
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


