package org.example.DTO;

public class Usuarios {
    private int id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasena;
    public Usuarios(){

    }
    public Usuarios(int id, String nombre, String apellido, String usuario, String contrasena){
        this.setId(id);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setUsuario(usuario);
        this.setContrasena(contrasena);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
