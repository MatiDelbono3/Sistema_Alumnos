package org.example.Repositories;
import org.example.Connections.Connections;
import org.example.DTO.Asistencias;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDAO {
    private final Connections cn=new Connections();
public boolean CrearAsistencia(Asistencias asistencia) throws SQLException {
    String Sql="Insert into asistencias (Id, alumno_id, curso_id, fecha, estado) VALUES (? ? ? ?)";
    try (Connection conexion=cn.Connect();
         PreparedStatement ps=((Connection)(conexion)).prepareStatement(Sql)){
         ps.setString(1,asistencia.getIdAlumno());
         ps.setString(2, asistencia.getIdCurso());
         ps.setDate(3, Date.valueOf((LocalDate) asistencia.getFecha()));
         ps.setString(4, asistencia.getEstado());
        int n=ps.executeUpdate();
        System.out.println("Número de filas afectadas: " + n); // Depuración
        return true;

    } catch (SQLException e) {
        JOptionPane.showConfirmDialog(null, e);
        return false;
    }
    }

    public String BuscarAsistencia(String idAlumno, String idCurso, LocalDate fecha) throws SQLException {
    Asistencias asistenciaABuscar=new Asistencias();
    String sql="select estado from asistencias WHERE alumno_id= ? AND curso_id = ? AND fecha= ?";
    try (Connection conexion=cn.Connect();
         PreparedStatement ps= conexion.prepareStatement(sql)){
         ps.setString(1,idAlumno);
         ps.setString(2, idCurso);
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
    public Asistencias BuscarPorId(int id) throws SQLException {
        Asistencias asistencia = null;
        String sql = "SELECT * FROM asistencias WHERE Id = ?";
        try (Connection conexion = cn.Connect();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                asistencia = new Asistencias();
                asistencia.setId(rs.getString("Id"));
                asistencia.setIdAlumno(rs.getString("alumno_id"));
                asistencia.setIdCurso(rs.getString("curso_id"));
                asistencia.setFecha(rs.getDate("fecha"));
                asistencia.setEstado(rs.getString("estado"));
            } else {
                System.out.println("No se encontró la asistencia con el ID especificado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar la asistencia por ID.");
            throw e;
        }
        return asistencia;
    }
    public double calcularPorcentajeAsistencia(String idAlumno, String idCurso) throws SQLException {
        String sqlTotalDias = "SELECT COUNT(*) FROM asistencias WHERE alumno_id = ? AND curso_id = ?";
        String sqlDiasAsistidos = "SELECT COUNT(*) FROM asistencias WHERE alumno_id = ? AND curso_id = ? AND estado = 'Presente'";

        try (Connection conexion = cn.Connect()) {
            PreparedStatement psTotal = conexion.prepareStatement(sqlTotalDias);
            psTotal.setString(1, idAlumno);
            psTotal.setString(2, idCurso);
            ResultSet rsTotal = psTotal.executeQuery();
            int totalDias = rsTotal.next() ? rsTotal.getInt(1) : 0;

            // Contar los días asistidos
            PreparedStatement psAsistidos = conexion.prepareStatement(sqlDiasAsistidos);
            psAsistidos.setString(1, idAlumno);
            psAsistidos.setString(2, idCurso);
            ResultSet rsAsistidos = psAsistidos.executeQuery();
            int diasAsistidos = rsAsistidos.next() ? rsAsistidos.getInt(1) : 0;

            // Calcular el porcentaje de asistencia
            if (totalDias > 0) {
                return (diasAsistidos * 100.0) / totalDias;
            } else {
                System.out.println("No hay registros de asistencia para este curso.");
                return 0.0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al calcular el porcentaje de asistencia", e);
        }
    }
    public List<Asistencias>ListarAsistencias() throws SQLException {
        List<Asistencias>ListaAsistencias=new ArrayList<>();
        String Sql="Select * from asistencias ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                Asistencias asistencia=new Asistencias();
                asistencia.setId(rs.getString(1));
                asistencia.setIdAlumno(rs.getString(2));
                asistencia.setIdCurso(rs.getString(3));
                asistencia.setFecha(rs.getDate(4));
                asistencia.setEstado(rs.getString(5));
                ListaAsistencias.add(asistencia);

            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al listar asistencias");
        }
        return ListaAsistencias;
    }


}

