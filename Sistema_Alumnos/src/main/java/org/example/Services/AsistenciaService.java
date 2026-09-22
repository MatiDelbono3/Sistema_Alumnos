package org.example.Services;
import org.example.DTO.Asistencias;
import org.example.Repositories.AlumnoDAO;
import org.example.Repositories.AsistenciaDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AsistenciaService {
    private final AsistenciaDAO asistenciaDAO = new AsistenciaDAO();
    private final AlumnoDAO alumnoDAO = new AlumnoDAO();
    public boolean CrearAsistencia(Asistencias asistencia) throws SQLException {
        validarAsistencia(asistencia);
        String estadoExistente = asistenciaDAO.BuscarAsistencia(
                asistencia.getIdAlumno(),
                asistencia.getIdCurso(),
                asistencia.getFecha()
        );
        if (estadoExistente != null) {
            throw new IllegalArgumentException("Ya existe una asistencia registrada para este alumno, curso y fecha.");
        }
        return asistenciaDAO.CrearAsistencia(asistencia);
    }
    public Asistencias BuscarAsistenciaPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        return asistenciaDAO.BuscarPorId(id);
    }
    public String BuscarAsistencia(String idAlumno, String idCurso, Date fecha) throws SQLException {
      validarIds(idAlumno, idCurso);
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de asistencia no puede ser nula.");
        }
        return asistenciaDAO.BuscarAsistencia(idAlumno, String.valueOf(idCurso), fecha.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
    }
    public double calcularPorcentajeAsistencia(String idAlumno, String idCurso) throws SQLException {
        validarIds(idAlumno, idCurso);
        if (!alumnoDAO.existeAlumno(Integer.parseInt(idAlumno))) {
            throw new IllegalArgumentException("El alumno no existe.");
        }

        return asistenciaDAO.calcularPorcentajeAsistencia(idAlumno, String.valueOf(idCurso));

    }
    public List<Asistencias>ListarAsistencias() throws SQLException {
        return asistenciaDAO.ListarAsistencias();
    }
    public Asistencias BuscarPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        return asistenciaDAO.BuscarPorId(id);
    }
    private void validarAsistencia(Asistencias asistencia) {
        if (asistencia.getIdAlumno() == "" || asistencia.getIdCurso() == "") {
            throw new IllegalArgumentException("El ID del alumno y del curso deben ser mayores que cero.");
        }

        if (asistencia.getFecha() == null) {
            throw new IllegalArgumentException("La fecha de asistencia no puede ser nula.");
        }
    }
    private void validarIds(String idAlumno, String idCurso) {
        if (idAlumno == null || idAlumno.isBlank() || idCurso == null || idCurso.isBlank()) {
            throw new IllegalArgumentException("El ID del alumno y del curso no pueden ser nulos o vacíos.");
        }
        try {
            Integer.parseInt(idAlumno);
            Integer.parseInt(idCurso);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID del alumno y del curso deben ser números válidos.");
        }
    }
}
