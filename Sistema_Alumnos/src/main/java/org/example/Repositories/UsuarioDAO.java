package org.example.Repositories;

import org.example.Connections.Connections;
import org.example.DTO.Cursos;
import org.example.DTO.Usuarios;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private final Connections cn=new Connections();
    public boolean RegistrarUsuario(Usuarios nuevoUsuario){
        String hashedPassword = BCrypt.hashpw(nuevoUsuario.getContrasena(), BCrypt.gensalt());

        String sql = "INSERT INTO usuarios (Nombre, Apellido, Usuario, Contrasena) VALUES (?, ?, ?, ?)";
        try (Connection conn=cn.Connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nuevoUsuario.getNombre());
            pstmt.setString(2, nuevoUsuario.getApellido());
            pstmt.setString(3, nuevoUsuario.getUsuario());
            pstmt.setString(4, hashedPassword); // Usa la contraseña hasheada aquí

            int rowsAffected = pstmt.executeUpdate(); // Ejecuta el INSERT
            return rowsAffected > 0; // Si se insertó al menos una fila, fue exitoso

        }
        catch (SQLException e) {
            // Manejo de errores de la base de datos
            System.err.println("Error al registrar usuario en la BD: " + e.getMessage());

            // Si el error es por usuario duplicado (ej. MySQL error code 1062 para UNIQUE constraint)
            if (e.getErrorCode() == 1062) {
               System.out.println("El nombre de usuario ya existe. Por favor, elige otro, Registro Fallido");
            } else {
                JOptionPane.showMessageDialog(null, "Error interno al registrar usuario: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public Usuarios login(String user, String password){
        Usuarios newUser=new Usuarios();
        String sql=("select * from Usuarios where usuario= ? AND estado= 'activo' " );
        try (Connection conexion = cn.Connect();
             PreparedStatement ps = ((Connection) conexion).prepareStatement(sql)) {
            ps.setString(1, user);

            try(ResultSet rs= ps.executeQuery()) {
                if (rs.next()) {
                    newUser.setId(rs.getInt(1));
                    newUser.setNombre(rs.getString(2));
                    newUser.setUsuario(rs.getString(3));
                    newUser.setContrasena(rs.getString(4));
                }
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return newUser;
    }
    public boolean desactivarUsuario(int idUsuario) throws SQLException {
        String sql=("Update Usuarios SET estado= inactivo where id= ? " );
        try (Connection conexion = cn.Connect();
             PreparedStatement ps = ((Connection) conexion).prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            int filasAfectadas=ps.executeUpdate();
            if (filasAfectadas > 0 ){
                System.out.println("Usuario desactivado con éxito");
                return true;
            }
            else {
                System.out.println("Error al desactivar el usuario");
            }
        return true;
    }
    }
    public Usuarios BuscarUsuarioPorId(int id){
        String Sql="select Id, nombre, apellido, usuario, contraseña, estado from Usuarios where Id= ? ";
        Usuarios usuarioPorId=new Usuarios();
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(Sql))){
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if (rs. next() ){
                usuarioPorId.setId(rs.getInt(1));
                usuarioPorId.setNombre(rs.getString(2));
                usuarioPorId.setApellido(rs.getString(3));
                usuarioPorId.setUsuario(rs.getString(4));
                usuarioPorId.setContrasena(rs.getString(5));
                usuarioPorId.setEstado(rs.getString(6));
                return usuarioPorId;
            }
            else {
                System.out.println("Usuario no encontrado");
                return null;
            }
        } catch (SQLException exception) {
          System.out.println("Usuario no encontrado");
        }
        return null;
    }
    public boolean existeUsuario(String nombre){
        String Sql="Select * from usuarios WHERE Usuario= ? LIMIT 1 ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql))){
            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException exc){
           System.out.println("Error al verificar usuario");
        }
        return true;
    }
    public List<Usuarios> ListarUsuarios() throws SQLException {
        List<Usuarios>ListaUsuarios=new ArrayList<>();
        String Sql="Select * from Usuarios";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = ( conexion.prepareStatement(Sql));
             ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                Usuarios usuario=new Usuarios();
                usuario.setId(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellido(rs.getString(3));
                usuario.setUsuario(rs.getString(4));
                usuario.setContrasena(rs.getString(5));
                usuario.setEstado(rs.getString(6));
                ListaUsuarios.add(usuario);
            }

        } catch (SQLException exc){
            System.out.println("Error al modificar la contraseña");
        }
        return ListaUsuarios;
    }
    public boolean editarContrasena(int idUsuario, String nuevaContrasena) throws SQLException {
        String Sql="update Usuarios set contraseña = ? where Id= ? ";
        try (Connection conexion= cn.Connect();
             PreparedStatement ps = (conexion.prepareStatement(Sql))){
            ps.setString(1, nuevaContrasena);
            ps.setInt(2, idUsuario);
            int filasAfectadas= ps.executeUpdate();
            if (filasAfectadas > 0 ){
                System.out.println("Contraseña modificada con éxito");
                return true;
            }
            else {
                System.out.println("Error al modificar la contraseña");
            }
        } catch (SQLException exception) {
            System.out.println("Error al modificar la contraseña");
        }
        return false;
    }
}

