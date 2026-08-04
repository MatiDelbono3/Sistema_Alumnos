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
    private final InscripcionDAO inscripcionDAO=new InscripcionDAO();
    AlumnoDAO alumnoAinscribir=new AlumnoDAO();
    CursoDAO cursoInscripcion=new CursoDAO();
    Alumnos alumno=new Alumnos();
    Cursos curso=new Cursos();
    public void insertarInscripciones(Inscripciones inscripcion){
        if (!alumnoAinscribir.existeAlumno(inscripcion.getId_Alumno())) {
            throw new IllegalArgumentException("El alumno no existe.");
        }
        if (!cursoInscripcion.existeCurso(inscripcion.getId_Curso())){
            throw new IllegalArgumentException("El curso no existe.");
        }
        if (inscripcionDAO.estaInscripto(alumno.getId(), curso.getId())){
            throw new IllegalArgumentException("El alumno ya está inscripto al curso.");
        }
        if (curso.getCupo_Maximo()<= inscripcionDAO.ContarAlumnosInscriptos(curso.getId())){
            throw new IllegalArgumentException("El curso NO tiene cupos disponibles.");
        }
        if (inscripcion.getFechaInscripcion().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inscripción es incorrecta.");
        }
        inscripcionDAO.insertarInscripciones(inscripcion);
    }
    public void ObtenerInscripcionesPorId(int id) throws SQLException {
        if (id > 0 ){
            throw new IllegalArgumentException("Inscripción NO encontrada.");
        }
        inscripcionDAO.ObtenerInscripcionesPorId(id);
    }
    public void editarestado(Inscripciones inscripcion) throws SQLException {
        if (!inscripcionDAO.existeInscripcion(inscripcion.getId())){
            throw new IllegalArgumentException("La inscripcion  NO EXISTE");
        }
        if (inscripcion.getId_Alumno() <0 ) {
            throw new IllegalArgumentException("El id del alumno es obligatorio");
        }
        if (inscripcion.getId_Curso() <0 ) {
            throw new IllegalArgumentException("El id del curso es obligatorio");
        }

        if (inscripcion.getEstado().isBlank() ){
            throw new IllegalArgumentException("Estado no válido");
        }
        inscripcionDAO.editarEstado(inscripcion);
    }
    public List<Inscripciones> BuscarInscripcionesPorAlumno(int idAlumno){
        AlumnoDAO alumnoABuscar=new AlumnoDAO();
        if (idAlumno > 0 ){
            throw new IllegalArgumentException("Id NO válido.");
        }
        if (!alumnoABuscar.existeAlumno(idAlumno)){
            throw new IllegalArgumentException("Alumno NO encontrado.");
        }
        inscripcionDAO.BuscarInscripcionesPorAlumno(idAlumno);
        return null;
    }
    public List<Inscripciones> BuscarInscripcionesPorCurso(int idCurso){
        CursoDAO cursoABuscar=new CursoDAO();
        if (idCurso > 0 ){
            throw new IllegalArgumentException("Id NO válido.");
        }
        if (!cursoABuscar.existeCurso(idCurso)){
            throw new IllegalArgumentException("Curso NO encontrado.");
        }
        inscripcionDAO.BuscarInscripcionesPorCurso(idCurso);
        return null;
    }
    public List<Cursos>BuscarCursosPorAlumno(int idAlumno) throws SQLException {
        AlumnoDAO alumnoABuscar=new AlumnoDAO();
        if (idAlumno > 0 ){
            throw new IllegalArgumentException("Id NO válido.");
        }
        if (!alumnoABuscar.existeAlumno(idAlumno)){
            throw new IllegalArgumentException("Alumno NO encontrado.");
        }
        inscripcionDAO.buscarCursosPorAlumno(idAlumno);
        return null;
    }
    public List<Alumnos>BuscarAlumnosPorCurso(int idCurso)
    {
        CursoDAO cursoABuscar=new CursoDAO();
        if (idCurso > 0 ){
            throw new IllegalArgumentException("Id NO válido.");
        }
        if (!cursoABuscar.existeCurso(idCurso)){
            throw new IllegalArgumentException("Curso NO encontrado.");
        }
        inscripcionDAO.BuscarInscripcionesPorCurso(idCurso);
        return null;
    }

    public List<Inscripciones> ListarInscripciones() throws SQLException {
            return inscripcionDAO.ListarInscripciones();
        }
        public void buscarAlumnosPorCurso(int idCurso) throws SQLException {
            if (idCurso > 0 ){
                throw new IllegalArgumentException("Curso NO encontrado.");
            }
            if (!cursoInscripcion.existeCurso(idCurso) ){
                throw new IllegalArgumentException("El curso NO existe.");
            }
            inscripcionDAO.buscarAlumnosPorCurso(idCurso);
        }
        public void buscarCursosPorAlumno(int idAlumno) throws SQLException {
                if (idAlumno > 0 ){
                    throw new IllegalArgumentException("Alumno NO encontrado.");
                }
                if (!alumnoAinscribir.existeAlumno(idAlumno) ){
                    throw new IllegalArgumentException("El alumno NO existe.");
                }
                inscripcionDAO.buscarCursosPorAlumno(idAlumno);
    }

    public void EliminarInscripcion(int id){
        if (!inscripcionDAO.existeInscripcion(id)){
            throw new IllegalArgumentException("La inscripción NO existe.");
        }
        if (!cursoInscripcion.existeCurso(curso.getId())){
            throw new IllegalArgumentException("El curso NO existe.");
        }
        if (!alumnoAinscribir.existeAlumno(alumno.getId())){
            throw new IllegalArgumentException("El alumno a inscribir NO existe.");
        }
        inscripcionDAO.eliminarInscripcion(id);
    }



}
