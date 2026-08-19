package org.example.Repositories;
import org.example.Connections.Connections;
import org.example.DTO.Alumnos;
import org.example.DTO.Asistencias;
import org.example.DTO.Cursos;
import org.example.DTO.Inscripciones;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDAO {
    private final Connections cn=new Connections();
public int CrearAsistencia(Asistencias asistencia) throws SQLException {
    String Sql="Insert into asistencias (Id, alumno_id, curso_id, fecha, estado) VALUES (? ? ? ?)";
    try (Connection conexion=cn.Connect();
         PreparedStatement ps=((Connection)(conexion)).prepareStatement(Sql)){
         ps.setInt(1,asistencia.getIdAlumno());
         ps.setInt(2, asistencia.getIdCurso());
         ps.setDate(3, Date.valueOf((LocalDate) asistencia.getFecha()));
         ps.setString(4, asistencia.getEstado());
        int n=ps.executeUpdate();
        System.out.println("Número de filas afectadas: " + n); // Depuración
        return n;

    } catch (SQLException e) {
        JOptionPane.showConfirmDialog(null, e);
        return 0;
    }
    }
    public String BuscarAsistencia(int idAlumno, int idCurso, LocalDate fecha) throws SQLException {
    Asistencias asistenciaABuscar=new Asistencias();
    String sql="select estado from asistencias WHERE alumno_id= ? AND curso_id = ? AND fecha= ?";
    try (Connection conexion=cn.Connect();
         PreparedStatement ps= conexion.prepareStatement(sql)){
         ps.setInt(1,idAlumno);
         ps.setInt(2, idCurso);
         ps.setDate(3, Date.valueOf((LocalDate) fecha));
        ResultSet rs=ps.executeQuery();
        if (rs. next() ){
            asistenciaABuscar.setEstado(rs.getString(1));
            return asistenciaABuscar.getEstado();
        }
        else {
            System.out.println("Asistencia no encontrada");
            return null;
        }
    } catch (SQLException exception) {
        JOptionPane.showMessageDialog(null, "Asistencia no encontrada");
    }
        return null;
    }



}

