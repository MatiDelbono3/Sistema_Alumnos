package org.example.Services;

import org.example.DTO.Usuarios;
import org.example.Repositories.UsuarioDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO=new UsuarioDAO();
    public boolean RegistrarUsuario(Usuarios usuario) {
        if (usuario.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del usuario es obligatorio");
        }
        if (usuario.getApellido().isBlank()) {
            throw new IllegalArgumentException("El apellido del usuario es obligatorio");
        }
        if (usuario.getUsuario().isBlank()) {
            throw new IllegalArgumentException("El apellido del usuario es obligatorio");
        }
        if (usuario.getUsuario().length() < 4) {
            throw new IllegalArgumentException("El usuario debe tener un mínimo de 4 caracteres");
        }
        if (usuarioDAO.existeUsuario(usuario.getNombre())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre");
        }
        if (usuario.getContrasena().length() <=8){
            throw new IllegalArgumentException("La contraseña debe tener mínimo 8 caracteres");
        }
        String hash = BCrypt.hashpw(
                usuario.getContrasena(),
                BCrypt.gensalt());

        usuario.setContrasena(hash);
        usuarioDAO.RegistrarUsuario(usuario);
        return true;
    }
    public void  login(int id, String contrasena){
        Usuarios usuarioEncontrado=usuarioDAO.BuscarUsuarioPorId(id);
        if (!usuarioDAO.existeUsuario(usuarioEncontrado.getNombre())) {
            throw new IllegalArgumentException("El usuario buscado no existe");
        }
        if (usuarioEncontrado.getEstado().equalsIgnoreCase("Activo")){
            throw new IllegalArgumentException("El usuario está inactivo");
        }
        if (!BCrypt.checkpw(contrasena, usuarioEncontrado.getContrasena())) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos.");
        }
    }
    public void editarContraseña(int id, String contrasena, String nuevaContrasena){
        Usuarios usuarioEncontrado=usuarioDAO.BuscarUsuarioPorId(id);
        if (!usuarioDAO.existeUsuario(usuarioEncontrado.getNombre())) {
            throw new IllegalArgumentException("El usuario buscado no existe");
        }
        if (contrasena.equals(nuevaContrasena)){
            throw new IllegalArgumentException("La nueva contraseña es igual a la anterior");
        }
        if (usuarioEncontrado.getContrasena().length() <=8){
            throw new IllegalArgumentException("La contraseña debe tener mínimo 8 caracteres");
        }


    }
    public void desactivarUsuario(int id){
        Usuarios usuarioEncontrado=usuarioDAO.BuscarUsuarioPorId(id);
        if (!usuarioDAO.existeUsuario(usuarioEncontrado.getNombre())) {
            throw new IllegalArgumentException("El usuario buscado no existe");
        }
        if (!usuarioEncontrado.getEstado().equalsIgnoreCase("Activo")){
            throw new IllegalArgumentException("El usuario ya se encuentra desactivado");
        }
}
public void BuscarUsuarioPorId(int id){
    Usuarios usuarioEncontrado=usuarioDAO.BuscarUsuarioPorId(id);
    if (!usuarioDAO.existeUsuario(usuarioEncontrado.getNombre())) {
        throw new IllegalArgumentException("El usuario buscado no existe");
    }
}
public List<Usuarios> ListarUsuarios() throws SQLException {
    return usuarioDAO.ListarUsuarios();
}
public void editarUsuario(int id){
    Usuarios usuarioEncontrado=usuarioDAO.BuscarUsuarioPorId(id);
    if (!usuarioDAO.existeUsuario(usuarioEncontrado.getNombre())) {
        throw new IllegalArgumentException("El usuario buscado no existe");
    }
    if (usuarioEncontrado.getApellido().isBlank()) {
        throw new IllegalArgumentException("El apellido del usuario es obligatorio");
    }
    if (usuarioEncontrado.getUsuario().isBlank()) {
        throw new IllegalArgumentException("El apellido del usuario es obligatorio");
    }


}

}
