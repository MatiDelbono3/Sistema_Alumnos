package org.example.Repositories;
import org.example.Connections.Connections;
import org.example.DTO.Alumnos;
import org.example.DTO.Cursos;
import org.example.DTO.Inscripciones;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO {
    private final Connections cn=new Connections();

    public void insertarInscripciones(Inscripciones inscripcion){
        String Sql="insert into inscripciones ( estudiante_id, curso_id, fecha_inscripcion, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ((Connection) conexion).prepareStatement(Sql)){
            ps.setInt(1, inscripcion.getId_Alumno());
            ps.setInt(2, inscripcion.getId_Curso());
            ps.setDate(3, Date.valueOf((LocalDate) inscripcion.getFechaInscripcion()));
            ps.setString(4, inscripcion.getEstado());
            int n=ps.executeUpdate();
            System.out.println("Número de filas afectadas: " + n); // Depuración

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error al insertar la inscripcion");
        }
    }
    public List<Inscripciones> ListarInscripciones() throws SQLException {
        List<Inscripciones>ListaInscripciones=new ArrayList<>();
        String Sql="Select * from inscripciones ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                Inscripciones inscripcion=new Inscripciones();
                inscripcion.setId(rs.getInt(1));
                inscripcion.setId_Alumno(rs.getString(2));
                inscripcion.setId_Curso(rs.getString(3));
                inscripcion.setFechaInscripcion(rs.getDate(4).toLocalDate());
               inscripcion.setEstado(rs.getString(5));
                ListaInscripciones.add(inscripcion);

            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al listar alumnos");
        }
        return ListaInscripciones;
    }
public int ContarAlumnosInscriptos(int idCurso){
    List<Inscripciones>ListaInscripciones=new ArrayList<>();
    String Sql="SELECT COUNT(*)\n" +
            "FROM inscripciones\n" +
            "WHERE curso_id = ?;";
        try (Connection conexion= cn.Connect();
    PreparedStatement ps = ( conexion.prepareStatement(Sql));
    ResultSet rs=ps.executeQuery()){
        if (rs.next()){
           return rs.getInt(1);

        }
    } catch (SQLException exc){
        JOptionPane.showMessageDialog(null, "Error al listar alumnos del curso");
    }
        return 0;
}
    public void ObtenerInscripcionesPorId(int id) throws SQLException {
        Inscripciones inscripcionPorId=new Inscripciones();
        String Sql="Select * from inscripciones WHERE Id= ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                inscripcionPorId.setId(rs.getInt(1));
                inscripcionPorId.setId_Alumno(rs.getString(2));
                inscripcionPorId.setId_Curso(rs.getString(3));
                inscripcionPorId.setFechaInscripcion(rs.getDate(4).toLocalDate());
              inscripcionPorId.setEstado(rs.getString(5));
            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al listar inscripciones");
        }
    }
    public boolean estaInscripto(int idAlumno, int idCurso){
        String Sql="Select * from inscripciones WHERE estudiante_id= ? AND curso_id = ? LIMIT 1 ";
        try (Connection conexion = cn.Connect();
             PreparedStatement ps = conexion.prepareStatement(Sql)) {

            ps.setInt(1, idAlumno);
            ps.setInt(2, idCurso);

            ResultSet rs = ps.executeQuery();

            boolean existe = rs.next();

            rs.close();

            return existe;

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar la inscripción.", e);
        }
    }
    public boolean tieneAlumnosInscriptos( int idCurso){
        String Sql="SELECT 1\n" +
                "FROM inscripciones\n" +
                "WHERE curso_id = ?\n" +
                "LIMIT 1; ";
        try (Connection conexion = cn.Connect();
             PreparedStatement ps = conexion.prepareStatement(Sql)) {

            ps.setInt(1, idCurso);

            ResultSet rs = ps.executeQuery();

            boolean tieneInscripciones = rs.next();

            rs.close();

            return tieneInscripciones;

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar la inscripción.", e);
        }
    }
    public List<Alumnos> buscarAlumnosPorCurso(int idCurso) throws SQLException {
        List<Alumnos>alumnosPorCurso=new ArrayList<>();
        String Sql=" SELECT e.*\n" +
                "        FROM estudiantes e\n" +
                "        INNER JOIN inscripciones i\n" +
                "            ON e.id = i.estudiante_id\n" +
                "        WHERE i.curso_id = ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
             ps.setInt(1, idCurso);
             ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Alumnos alumno=new Alumnos();
                alumno.setId(rs.getInt(rs.getInt("id")));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setFecha_Nacimiento(rs.getDate("fecha_nacimento"));
                alumno.setCorreo_electronico(rs.getString("Correo_electronico"));
                alumnosPorCurso.add(alumno);
            }
            rs.close();
            } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al buscar alumnos por curso");
        }
        return alumnosPorCurso ;
    }
    public List<Inscripciones>BuscarInscripcionesPorAlumno(int idAlumno){
        List<Inscripciones>inscripcionesPorAlumno=new ArrayList<>();
        String Sql=" SELECT i.*\n" +
                "        FROM inscripciones i\n" +
                "        INNER JOIN estudiantes e\n" +
                "            ON e.id = i.estudiante_id\n" +
                "        WHERE i.estudiante_id = ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Inscripciones inscripcion =new Inscripciones();
                inscripcion.setId(rs.getInt(rs.getInt("id")));
                inscripcion.setId_Alumno(rs.getString("estudiante_id"));
                inscripcion.setId_Curso(rs.getString("curso_id"));
                inscripcion.setFechaInscripcion(rs.getDate("fecha_inscripcion").toLocalDate());
                inscripcion.setEstado(rs.getString("estado"));
                inscripcionesPorAlumno.add(inscripcion);
            }
            rs.close();
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al buscar inscripciones por alumno");
        }
        return inscripcionesPorAlumno ;
    }
    public List<Inscripciones>BuscarInscripcionesPorCurso(int idCurso){
        List<Inscripciones>inscripcionesPorAlumno=new ArrayList<>();
        String Sql=" SELECT i.*\n" +
                "        FROM inscripciones i\n" +
                "        INNER JOIN cursos c\n" +
                "            ON c.id = i.curso_id\n" +
                "        WHERE i.curso_id = ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
            ps.setInt(1, idCurso);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Inscripciones inscripcion =new Inscripciones();
                inscripcion.setId(rs.getInt(rs.getInt("id")));
                inscripcion.setId_Alumno(rs.getString("estudiante_id"));
                inscripcion.setId_Curso(rs.getString("curso_id"));
                inscripcion.setFechaInscripcion(rs.getDate("fecha_inscripcion").toLocalDate());
                inscripcion.setEstado(rs.getString("estado"));
                inscripcionesPorAlumno.add(inscripcion);
            }
            rs.close();
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al buscar inscripciones por alumno");
        }
        return inscripcionesPorAlumno ;
    }
    public List<Cursos> buscarCursosPorAlumno(int idAlumno) throws SQLException {
        List<Cursos>cursosPorAlumno=new ArrayList<>();
        String Sql=" SELECT c.*\n" +
                "        FROM cursos a\n" +
                "        INNER JOIN inscripciones i\n" +
                "            ON c.id = i.curso_id\n" +
                "        WHERE i.estudiante_id = ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Cursos curso=new Cursos();
                curso.setId(rs.getInt(rs.getInt("id")));
                curso.setNombre_curso(rs.getString("nombre_curso"));
               curso.setNivel(rs.getString("nivel"));
                curso.setCupo_Maximo(rs.getInt("cupo_maximo"));
                cursosPorAlumno.add(curso);
            }
            rs.close();
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al buscar cursos por alumno");
        }
        return cursosPorAlumno ;
    }
    public boolean existeInscripcion(int idInscripcion){
        String Sql2="Select * from inscripciones WHERE inscripcion_id= ? LIMIT 1 ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql2))){
             ps.setInt(1,idInscripcion);
             ResultSet rs=ps.executeQuery();
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al verificar inscripción");
        }
        return true;
    }
    public boolean aproboNivel(int idAlumno, String nivel){
        String Sql3="SELECT 1\n" +
                "FROM inscripciones i\n" +
                "JOIN cursos c ON i.curso_id = c.id\n" +
                "WHERE i.estudiante_id = ?\n" +
                "  AND c.nivel = '?'\n" +
                "  AND i.estado = 'APROBADO'\n" +
                "LIMIT 1; ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql3))){
             ps.setInt(1,idAlumno);
             ResultSet rs=ps.executeQuery();

        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al verificar aprobación");
        }
        return true;
    }


    public boolean editarEstado(Inscripciones inscripcion){
        String sql ="update inscripciones set estado = INACTIVO where Id= ?";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(sql))){
            ps.setString(4, String.valueOf(inscripcion));
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

    public boolean eliminarInscripcion(int id){
        String sql="delete from inscripciones where Id=?";
        try (Connection conexion =cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(sql))){
            ps.setInt( 1, id);

            int filasAfectadas= ps.executeUpdate();
            if (filasAfectadas > 0 ){
                System.out.println("Inscripción eliminada con éxito");
                return true;
            }
            else {
                System.out.println("error al eliminar la inscripción");
                return false;
            }
        } catch (SQLException excepcion) {
            throw new RuntimeException(excepcion);
        }
    }
}

