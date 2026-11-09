package org.example.Services;

import org.example.DTO.Alumnos;
import org.example.DTO.Cursos;
import org.example.DTO.Inscripciones;
import org.example.Repositories.AlumnoDAO;
import org.example.Repositories.CursoDAO;
import org.example.Repositories.InscripcionDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class InscripcionService {
    private final InscripcionDAO inscripcionDAO = new InscripcionDAO();
    AlumnoDAO alumnoAinscribir = new AlumnoDAO();
    CursoDAO cursoInscripcion = new CursoDAO();

    public void insertarInscripciones(Inscripciones inscripcion) throws SQLException {
        int idAlumno = Integer.parseInt(inscripcion.getId_Alumno());
        int idCurso = Integer.parseInt(inscripcion.getId_Curso());
        if (!alumnoAinscribir.existeAlumno(idAlumno)) {
            throw new IllegalArgumentException("El alumno no existe.");
        }
        if (!cursoInscripcion.existeCurso(inscripcion.getId_Curso())) {
            throw new IllegalArgumentException("El curso no existe.");
        }
        if (inscripcionDAO.estaInscripto(inscripcion.getId_Alumno(), inscripcion.getId_Curso())) {
            throw new IllegalArgumentException("El alumno ya está inscripto al curso.");
        }
        Cursos curso = cursoInscripcion.ObtenerCursoPorId(idCurso);
        if (curso.getCupo_Maximo() <= inscripcionDAO.ContarAlumnosInscriptos(inscripcion.getId_Curso())) {
            throw new IllegalArgumentException("El curso NO tiene cupos disponibles.");
        }
        if (inscripcion.getFechaInscripcion().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inscripción es incorrecta.");
        }
        inscripcionDAO.insertarInscripciones(inscripcion);
    }

    public void ObtenerInscripcionesPorId(String id) throws SQLException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Inscripción NO encontrada.");
        }
        inscripcionDAO.ObtenerInscripcionesPorId(Integer.parseInt(id));
    }

    public void editarestado(Inscripciones inscripcion) throws SQLException {
        if (!inscripcionDAO.existeInscripcion(inscripcion.getId())) {
            throw new IllegalArgumentException("La inscripción NO EXISTE");
        }
        if (inscripcion.getEstado() == null || inscripcion.getEstado().isBlank()) {
            throw new IllegalArgumentException("Estado no válido");
        }
        inscripcionDAO.editarEstado(inscripcion);
    }

    public List<Inscripciones> BuscarInscripcionesPorAlumno(String idAlumno) {
        if (idAlumno == null || idAlumno.isBlank()) {
            throw new IllegalArgumentException("Id NO válido.");
        }
        if (!alumnoAinscribir.existeAlumno(Integer.parseInt(idAlumno))) {
            throw new IllegalArgumentException("Alumno NO encontrado.");
        }
        return inscripcionDAO.BuscarInscripcionesPorAlumno(idAlumno);
    }

    public List<Inscripciones> BuscarInscripcionesPorCurso(String idCurso) {
        if (idCurso == null || idCurso.isBlank()) {
            throw new IllegalArgumentException("Id NO válido.");
        }
        if (!cursoInscripcion.existeCurso(idCurso)) {
            throw new IllegalArgumentException("Curso NO encontrado.");
        }
        return inscripcionDAO.BuscarInscripcionesPorCurso(idCurso);
    }

    public List<Cursos> BuscarCursosPorAlumno(String idAlumno) throws SQLException {
        if (idAlumno == null || idAlumno.isBlank()) {
            throw new IllegalArgumentException("Id NO válido.");
        }
        if (!alumnoAinscribir.existeAlumno(Integer.parseInt(idAlumno))) {
            throw new IllegalArgumentException("Alumno NO encontrado.");
        }
        return inscripcionDAO.buscarCursosPorAlumno(idAlumno);
    }

    public List<Alumnos> BuscarAlumnosPorCurso(String idCurso) {
        if (idCurso == null || idCurso.isBlank()) {
            throw new IllegalArgumentException("Id NO válido.");
        }
        if (!cursoInscripcion.existeCurso(idCurso)) {
            throw new IllegalArgumentException("Curso NO encontrado.");
        }
        try {
            return inscripcionDAO.buscarAlumnosPorCurso(Integer.parseInt(idCurso));
        } catch (SQLException e) {
            throw new IllegalStateException("Error al buscar alumnos por curso.", e);
        }
    }

    public List<Inscripciones> ListarInscripciones() throws SQLException {
        return inscripcionDAO.ListarInscripciones();
    }

    public void buscarAlumnosPorCurso(String idCurso) throws SQLException {
        if (idCurso == null || idCurso.isBlank()) {
            throw new IllegalArgumentException("Curso NO encontrado.");
        }
        if (!cursoInscripcion.existeCurso(idCurso)) {
            throw new IllegalArgumentException("El curso NO existe.");
        }
        inscripcionDAO.buscarAlumnosPorCurso(Integer.parseInt(idCurso));
    }

    public void buscarCursosPorAlumno(String idAlumno) throws SQLException {
        if (idAlumno == null || idAlumno.isBlank()) {
            throw new IllegalArgumentException("Alumno NO encontrado.");
        }
        if (!alumnoAinscribir.existeAlumno(Integer.parseInt(idAlumno))) {
            throw new IllegalArgumentException("El alumno NO existe.");
        }
        inscripcionDAO.buscarCursosPorAlumno(idAlumno);
    }

    public void EliminarInscripcion(String id) {
        if (!inscripcionDAO.existeInscripcion(id)) {
            throw new IllegalArgumentException("La inscripción NO existe.");
        }
        inscripcionDAO.eliminarInscripcion(Integer.parseInt(id));
    }
}