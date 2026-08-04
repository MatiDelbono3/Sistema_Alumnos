
package org.example.Repositories;
import org.example.Connections.Connections;
import org.example.DTO.Alumnos;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
    private final Connections cn=new Connections();

    public int insertarAlumnos(Alumnos alumno){
        String Sql="insert into estudiantes (Nombre, Apellido, Fecha_Nacimiento, Correo_electronico, Fecha_Inscripcion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ((Connection) conexion).prepareStatement(Sql)){
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, (Date) alumno.getFecha_Nacimiento());
            ps.setString(4, alumno.getCorreo_electronico());

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
                ListaAlumnos.add(alumno);

            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al listar alumnos");
        }
        return ListaAlumnos;
    }
    public Alumnos ObtenerAlumnosPorId(int id) throws SQLException {
        Alumnos AlumnoPorId=new Alumnos();
        String Sql="Select * from estudiantes WHERE Id= ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                AlumnoPorId.setId(rs.getInt(1));
                AlumnoPorId.setNombre(rs.getString(2));
                AlumnoPorId.setApellido(rs.getString(3));
                AlumnoPorId.setFecha_Nacimiento(rs.getDate(4));
                AlumnoPorId.setCorreo_electronico(rs.getString(5));

            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al listar alumnos");
        }
        return AlumnoPorId;
    }
    public boolean existeAlumno(int Id){
        Alumnos AlumnoPorId=new Alumnos();
        String Sql="Select * from estudiantes WHERE Id= ? LIMIT 1 ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                ps.setInt(1, Id);
            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al verificar alumno");
        }
        return true;
    }
    public boolean existeCorreo(String correo_electronico){
        String Sql="Select * from estudiantes WHERE Correo_electronico= ? LIMIT 1 ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
              ps.setString(5,correo_electronico);
            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al verificar correo");
        }
        return true;
    }
public boolean tieneCursosActivos(int idAlumno){
    String Sql="SELECT 1\n" +
            "FROM inscripciones i\n" +
            "INNER JOIN cursos c\n" +
            "    ON i.curso_id = c.id\n" +
            "WHERE i.alumno_id = ?\n" +
            "  AND c.estado = 'ACTIVO'\n" +
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

    public boolean editarCorreo(int idAlumno, String nuevoCorreo){
        String sql ="update estudiantes set Correo_electronico = ? where Id= ?";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(sql))){
            ps.setString(5, nuevoCorreo);
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
    public boolean eliminarAlumno(int id){
        String sql="delete from estudiantes where Id=?";
        try (Connection conexion =cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(sql))){
            ps.setInt( 1, id);

            int filasAfectadas= ps.executeUpdate();
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
