package org.example.DTO;
import java.time.LocalDate;
import java.util.Date;
public class Inscripciones {
    private int id;
    private int id_Alumno;
    private int id_Curso;
    private LocalDate fechaInscripcion;
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Alumno() {
        return id_Alumno;
    }

    public void setId_Alumno(String id_Alumno) {
        this.id_Alumno = Integer.parseInt(id_Alumno);
    }

    public int getId_Curso() {
        return id_Curso;
    }

    public void setId_Curso(String id_Curso) {
        this.id_Curso = Integer.parseInt(id_Curso);
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
