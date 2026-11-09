package org.example.DTO;
import java.time.LocalDate;

public class Inscripciones {
    private String id;
    private String id_Alumno;
    private String id_Curso;
    private LocalDate fechaInscripcion;
    private String estado;

    public String getId() {
        return id;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public String getId_Alumno() {
        return id_Alumno;
    }

    public void setId_Alumno(String id_Alumno) {
        this.id_Alumno = String.valueOf(Integer.parseInt(id_Alumno));
    }

    public String getId_Curso() {
        return id_Curso;
    }

    public void setId_Curso(String id_Curso) {
        this.id_Curso = String.valueOf(Integer.parseInt(id_Curso));
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
