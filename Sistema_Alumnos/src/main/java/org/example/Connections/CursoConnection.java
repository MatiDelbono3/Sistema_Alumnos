package org.example.Connections;

import org.example.DTO.Cursos;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CursoConnection {
    private final Connections cn=new Connections();

    public int insertar(Cursos curso){
        String sql="insert into cursos (nombre_curso, nivel, cupo_maximo) VALUES (?, ?, ?)";

        try (Connection conexion= cn.Connect();
            PreparedStatement ps = ((Connection) conexion).prepareStatement(sql)){
            ps.setString(1, curso.getNombre_curso());
            ps.setString(2, curso.getNivel());
            ps.setInt(3, curso.getCupo_Maximo() );


            int n=ps.executeUpdate();
            System.out.println("Número de filas afectadas: " + n); // Depuración
            return n;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return 0;
        }
    }
    public List<Cursos> ListarCursos() throws SQLException {
        List<Cursos>ListaCursos=new ArrayList<>();
        String Sql="Select * from Cursos";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
             while (rs.next()){
                 Cursos curso=new Cursos();
                 curso.setId(rs.getInt(1));
                 curso.setNombre_curso(rs.getString(2));
                 curso.setNivel(rs.getString(3));
                 curso.setCupo_Maximo(rs.getInt(4));
                 ListaCursos.add(curso);
             }

        } catch (SQLException exc){
            JOptionPane.showMessageDialog(null, "Error al listar curso");
        }
        return ListaCursos;
    }
    public boolean editar(Cursos curso) throws SQLException {
        String Sql="update Cursos set Cupo_Maximo = ? where Id= ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(Sql))){
             ps.setInt(1, curso.getCupo_Maximo());
             ps.setInt(2, curso.getId());

             int filasAfectadas= ps.executeUpdate();
             if (filasAfectadas > 0 ){
                 System.out.println("Curso modificado con éxito");
                 return true;
             }
             else {
                 System.out.println("Error al modificar el curso");
             }
        } catch (SQLException exception) {
        JOptionPane.showMessageDialog(null, "Error al modificar curso");
        }
        return false;
    }
    public boolean eliminar(Cursos curso){
        String sql="delete from cursos where Id =?";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(sql))){
            ps.setInt(1, curso.getId());

            int filasAfectadas= ps.executeUpdate();
            if (filasAfectadas > 0 ){
                System.out.println("Curso eliminado con éxito");
                return true;
            }
            else {
                System.out.println("Error al eliminar el curso");

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el curso");
        }
        return false;
    }
    public boolean Buscar(Cursos curso){
        String Sql="select Id, nombre_curso, nivel, cupo_maximo from Cursos  where Id= ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(Sql))){
             ps.setInt(1, curso.getId());
             ResultSet rs=ps.executeQuery();
            if (rs. next() ){
                curso.setId(rs.getInt(1));
                curso.setNombre_curso(rs.getString(2));
                curso.setNivel(rs.getString(3));
                curso.setCupo_Maximo(rs.getInt(4));
                return true;
            }
            else {
                System.out.println("Curso no encontrado");
                return false;
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Curso no encontrado");
        }
        return false;
    }

        public boolean probarConexion() {
        String url = "jdbc:mysql://localhost:3307/BdAlumnos"; // Ajusta según tu base de datos
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


