package org.example.Repositories;
import org.example.Connections.Connections;
import org.example.DTO.Alumnos;
import org.example.DTO.Cursos;
import org.example.DTO.Docentes;
import org.example.DTO.Inscripciones;

import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
public class DocenteDAO {
    private final Connections cn=new Connections();
    InscripcionDAO cursoDAO=new InscripcionDAO();
    Inscripciones inscripcion=new Inscripciones();
    public boolean insertarDocentes(Docentes docente ){
        String Sql="insert into docentes (Nombre, Apellido, Correo_electronico, fecha_nacimiento, fecha_contratacion, estado ) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ((Connection) conexion).prepareStatement(Sql)){
            ps.setString(1, docente.getNombre());
            ps.setString(2, docente.getApellido());
            ps.setString(4, docente.getCorreo_electronico());
            ps.setDate(3, new java.sql.Date(docente.getFecha_nacimiento().getTime()));
            ps.setDate(4, new java.sql.Date(docente.getFecha_nacimiento().getTime()));
            int n=ps.executeUpdate();
            System.out.println("Número de filas afectadas: " + n);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error al insertar el alumno");
        }
        return false;
    }
    public List<Docentes> ListarDocentes() throws SQLException {
        List<Docentes>ListaDocentes=new ArrayList<>();
        String Sql="Select * from docentes ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                Docentes docente=new Docentes();
                docente.setId(rs.getString(1));
                docente.setNombre(rs.getString(2));
                docente.setApellido(rs.getString(3));
                docente.setCorreo_electronico(rs.getString(5));
                docente.setFecha_nacimiento(rs.getDate(4));
                docente.setFecha_contratacion(rs.getDate(6));
                docente.setActivo(rs.getBoolean(7));
                ListaDocentes.add(docente);

            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al listar docentes");
        }
        return ListaDocentes;
    }
    public Docentes ObtenerDocentePorId(int id) throws SQLException {
        Docentes docentePorId=new Docentes();
        String Sql="Select Id, Nombre, Apellido, Correo_electronico, fecha_nacimiento, fecha_contratacion, estado from docentes WHERE Id= ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                docentePorId.setId(rs.getString(1));
                docentePorId.setNombre(rs.getString(2));
                docentePorId.setApellido(rs.getString(3));
                docentePorId.setCorreo_electronico(rs.getString(4));
                docentePorId.setFecha_nacimiento(rs.getDate(5));
                docentePorId.setFecha_contratacion(rs.getDate(6));
                docentePorId.setActivo(rs.getBoolean(7));
            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al obtener el docente por ID");
        }
        return docentePorId;
    }
    public List<Docentes>DocentesPorNombre(String nombre) throws SQLException {
        List<Docentes>ListaDocentes=new ArrayList<>();
        String Sql="Select * from docentes WHERE Nombre LIKE ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Docentes docente=new Docentes();
                docente.setId(rs.getString(1));
                docente.setNombre(rs.getString(2));
                docente.setApellido(rs.getString(3));
                docente.setCorreo_electronico(rs.getString(5));
                docente.setFecha_nacimiento(rs.getDate(4));
                docente.setFecha_contratacion(rs.getDate(6));
                docente.setActivo(rs.getBoolean(7));
                ListaDocentes.add(docente);
            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al buscar docentes por nombre");
        }
        return ListaDocentes;
    }
    public boolean existeCorreo(String correo){
        String Sql="Select * from docentes WHERE Correo_electronico= ? LIMIT 1 ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
            ps.setString(1, correo);
            ResultSet rs=ps.executeQuery();
            return rs.next();
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al verificar la existencia del docente");
        }
        return false;
    }
    public boolean existeDocente(String Id){
        String Sql="Select * from docentes WHERE Id= ? LIMIT 1 ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql)))
        {


            ps.setString(1, Id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Error al verificar alumno: " + exc.getMessage());
            return false;
        }
    }
    public List<Cursos> CursosAsignados(String idDocente){
        List<Cursos>CursosAsignados=new ArrayList<>();
        String Sql="SELECT \n" +
                "FROM cursos\n" +
                "WHERE estado = 'ACTIVO'\n" +
                "  AND docente_id = ?\n";

        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
            ps.setString(1, String.valueOf(idDocente));
            ResultSet rs=ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return CursosAsignados;
    }

    public boolean editarCorreo(String nuevoCorreo, String idDocente){
        String sql ="update docentes set Correo_electronico = ? where Id= ?";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(sql))){
            ps.setString(1, nuevoCorreo);
            ps.setString(2, idDocente);
            System.out.println("Intentando actualizar Docente: " + idDocente + " con Correo: " + nuevoCorreo);
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
    public List<Docentes>ListarActivos() throws SQLException {
        List<Docentes>ListaDocentes=new ArrayList<>();
        String Sql="Select * from docentes WHERE estado='ACTIVO' ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                Docentes docente=new Docentes();
                docente.setId(rs.getString(1));
                docente.setNombre(rs.getString(2));
                docente.setApellido(rs.getString(3));
                docente.setCorreo_electronico(rs.getString(5));
                docente.setFecha_nacimiento(rs.getDate(4));
                docente.setFecha_contratacion(rs.getDate(6));
                docente.setActivo(rs.getBoolean(7));
                ListaDocentes.add(docente);

            }
        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al listar docentes activos");
        }
        return ListaDocentes;
    }
    public boolean eliminarDocente(String id) throws SQLException {

        String sqlCur="delete from cursos where docente_id=?";
        String sqlAlu="delete from docentes where Id=?";
        try (Connection conexion =cn.Connect()){
            PreparedStatement psI=(conexion.prepareStatement(sqlCur));
            psI.setString(1, id);
            psI.executeUpdate();

            PreparedStatement psA=(conexion.prepareStatement(sqlAlu));
            psA.setString(1, id);
            int filasAfectadas= psA.executeUpdate();

            if (filasAfectadas > 0 ){
                System.out.println("Docente eliminado con éxito");
                return true;
            }
            else {
                System.out.println("error al eliminar el docente");
                return false;
            }
        } catch (SQLException excepcion) {
            throw new RuntimeException(excepcion);
        }
    }

}
