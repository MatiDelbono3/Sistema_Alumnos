package org.example.Connections;

import org.example.DTO.Alumnos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnosConnection {
    private final Connections cn=new Connections();
    public int insertarAlumnos(Alumnos alumno){
        String Sql="insert into estudiantes (Nombre, Apellido, Fecha_Nacimiento, Correo_electronico, Fecha_Inscripcion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ((Connection) conexion).prepareStatement(Sql)){
             ps.setString(1, alumno.getNombre());
             ps.setString(2, alumno.getApellido());
             ps.setDate(3, alumno.getFecha_Nacimiento());
             ps.setString(4, alumno.getCorreo_electronico());
             ps.setDate(5, alumno.getFecha_Inscripcion());

            int n=ps.executeUpdate();
            System.out.println("Número de filas afectadas: " + n); // Depuración
            return n;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error al insertar el alumno");
        }
        return 0;
    }
    public List<Alumnos> ListarAlumnos() throws SQLException {
        List<Alumnos>ListaAlumnos=new ArrayList<>();
        String Sql="Select * from estudiantes ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
               Alumnos alumno=new Alumnos();
               alumno.setId(rs.getInt(1));
               alumno.setNombre(rs.getString(2));
               alumno.setApellido(rs.getString(3));
               alumno.setFecha_Nacimiento(rs.getDate(4));
               alumno.setCorreo_electronico(rs.getString(5));
               alumno.setFecha_Inscripcion(rs.getDate(6));
               ListaAlumnos.add(alumno);

            }
    } catch (SQLException exc){
        JOptionPane.showMessageDialog(null, "Error al listar alumnos");
    }
        return ListaAlumnos;
    }
    public boolean editarAlumno(Alumnos alumno) throws SQLException{
        String sql ="update estudiantes set Correo_electronico = ? where Id= ?";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(sql))){
            ps.setString(1, alumno.getCorreo_electronico());
            ps.setInt(2, alumno.getId());
            int filasAfectadas=ps.executeUpdate();
            if (filasAfectadas > 0){
                JOptionPane.showMessageDialog(null, "modificación realizada con exito");
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null, "error al realizar la modificación");
            }
        } catch (SQLException exception){
            JOptionPane.showMessageDialog(null, "error al realizar la modificación");
        }
        return false;
    }
}