package org.example.DTO;

import java.util.Date;

public class Alumnos {
    private int Id;
    private String Nombre;
    private String Apellido;
    private Date Fecha_Nacimiento;
    private String Correo_electronico;
    private Date Fecha_Inscripcion;
    public Alumnos(){

    }
    public Alumnos(int Id, String Nombre, String Apellido, Date Fecha_Nacimiento, String Correo_electronico, Date Fecha_Inscripcion){
        this.setNombre(Nombre);
        this.setApellido(Apellido);
        this.setFecha_Nacimiento(Fecha_Nacimiento);
        this.setCorreo_electronico(Correo_electronico);
        this.setFecha_Inscripcion(Fecha_Inscripcion);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public Date getFecha_Nacimiento() {
        return  Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(Date fecha_Nacimiento) {
        Fecha_Nacimiento = fecha_Nacimiento;
    }

    public String getCorreo_electronico() {
        return Correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        Correo_electronico = correo_electronico;
    }

    public Date getFecha_Inscripcion() {
        return  Fecha_Inscripcion;
    }

    public void setFecha_Inscripcion(Date fecha_Inscripcion) {
        Fecha_Inscripcion = fecha_Inscripcion;
    }
}
