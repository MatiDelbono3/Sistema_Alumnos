package org.example.DTO;
import java.sql.Date;
import java.time.LocalDate;

public class Asistencias {
    private String Id;
    private String idAlumno;
    private String idCurso;
    private LocalDate fecha;
    private String estado;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha.toLocalDate();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
