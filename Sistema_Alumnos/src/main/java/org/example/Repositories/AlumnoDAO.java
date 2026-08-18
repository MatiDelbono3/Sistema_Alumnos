package org.example.Repositories;
import org.example.Connections.Connections;
import org.example.DTO.Alumnos;
import org.example.DTO.Cursos;
import org.example.DTO.Inscripciones;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
    private final Connections cn=new Connections();
    InscripcionDAO inscripcionDAO=new InscripcionDAO();
    Inscripciones inscripcion=new Inscripciones();
    public int insertarAlumnos(Alumnos alumno){
        String Sql="insert into estudiantes (Nombre, Apellido, Fecha_Nacimiento, Correo_electronico) VALUES (?, ?, ?, ?)";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ((Connection) conexion).prepareStatement(Sql)){
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, (Date) alumno.getFecha_Nacimiento());
            ps.setString(4, alumno.getCorreo_electronico());
            int n=ps.executeUpdate();
            System.out.println("Número de filas afectadas: " + n);
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
                ListaAlumnos.add(alumno);

            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al listar alumnos");
        }
        return ListaAlumnos;
    }
    public Alumnos ObtenerAlumnosPorId(int id) throws SQLException {
        Alumnos AlumnoPorId=new Alumnos();
        String Sql="Select Id, Nombre, Apellido, Fecha_Nacimiento, Correo_electronico from estudiantes WHERE Id= ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
             ps.setInt(1, id);
             ResultSet rs=ps.executeQuery();
            if (rs.next()){
                AlumnoPorId.setId(rs.getInt(1));
                AlumnoPorId.setNombre(rs.getString(2));
                AlumnoPorId.setApellido(rs.getString(3));
                AlumnoPorId.setFecha_Nacimiento(rs.getDate(4));
                AlumnoPorId.setCorreo_electronico(rs.getString(5));
                return AlumnoPorId;
            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al obtener alumno");

        }
        return null;
    }
    public boolean existeAlumno(int Id){
        String Sql="Select * from estudiantes WHERE Id= ? LIMIT 1 ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql)))
        {


            ps.setInt(1, Id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Error al verificar alumno: " + exc.getMessage());
            return false;
        }
    }
    public boolean existeCorreo(String correo_electronico){
        String Sql="Select * from estudiantes WHERE Correo_electronico= ? LIMIT 1 ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){


            ps.setString(1, correo_electronico.trim().toLowerCase());

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Error al verificar correo: " + exc.getMessage());
            return false;
        }
    }
    public boolean tieneCursosActivos(int idAlumno){
        String Sql="SELECT 1\n" +
                "FROM inscripciones i\n" +
                "INNER JOIN cursos c\n" +
                "    ON i.curso_id = c.id\n" +
                "WHERE i.estudiante_id = ?\n" +
                "  AND i.estado = 'ACTIVO'\n" +
                "LIMIT 1; ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
            ps.setString(1, String.valueOf(idAlumno));
            ResultSet rs=ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean editarCorreo(String nuevoCorreo, int idAlumno){
        String sql ="update estudiantes set Correo_electronico = ? where Id= ?";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(sql))){
            ps.setString(1, nuevoCorreo);
            ps.setInt(2, idAlumno);
            System.out.println("Intentando actualizar ID: " + idAlumno + " con Correo: " + nuevoCorreo);
            int filasAfectadas=ps.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("modificacion realizada con exito");
                return true;
            }
            else {
               System.out.println("Error al realizar la modificacion");
            }
        } catch (SQLException exception){
            System.err.println("Error SQL en editarCorreo:" + exception.getMessage());
        }
        return false;
    }

    public boolean eliminarAlumno(int id) throws SQLException {

       String sqlInsc="delete from inscripciones where estudiante_id=?";
        String sqlAlu="delete from estudiantes where Id=?";
        try (Connection conexion =cn.Connect()){
            PreparedStatement psI=(conexion.prepareStatement(sqlInsc));
            psI.setInt(1, id);
            psI.executeUpdate();

            PreparedStatement psA=(conexion.prepareStatement(sqlAlu));
            psA.setInt(1, id);
            int filasAfectadas= psA.executeUpdate();

            if (filasAfectadas > 0 ){
                System.out.println("Alumno eliminado con éxito");
                return true;
            }
            else {
                System.out.println("error al eliminar el alumno");
                return false;
            }
        } catch (SQLException excepcion) {
            throw new RuntimeException(excepcion);
        }
    }
}