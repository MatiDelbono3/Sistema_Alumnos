package org.example.Services;

import org.example.DTO.Alumnos;
import org.example.DTO.Cursos;
import org.example.Repositories.AlumnoDAO;
import org.example.Repositories.CursoDAO;
import org.example.Repositories.InscripcionDAO;

import java.sql.SQLException;
import java.util.List;

public class CursoService {
    private final CursoDAO cursoDAO = new CursoDAO();
    private final InscripcionDAO inscripcionDAO=new InscripcionDAO();
    public void registrarCurso(Cursos curso) {
        if (curso.getNombre_curso().isBlank()) {
            throw new IllegalArgumentException("El nombre de curso es obligatorio");
        }
        if (curso.getNivel().isBlank()){
            throw new IllegalArgumentException("El nivel del curso es obligatorio");
        }
        if (curso.getCupo_Maximo() <=0 ){
            throw new IllegalArgumentException("Cupo no válido");
        }
        cursoDAO.insertarCurso(curso);
    }
    public List<Cursos> listarCursos() throws SQLException {
        return cursoDAO.ListarCursos();
    }
    public void editarCurso(Cursos curso) throws SQLException {
        if (!cursoDAO.existeCurso(curso.getId())){
            throw new IllegalArgumentException("El curso NO EXISTE");
        }
        if (curso.getNombre_curso().isBlank()) {
            throw new IllegalArgumentException("El nombre de curso es obligatorio");
        }
        if (curso.getNivel().isBlank()){
            throw new IllegalArgumentException("El nivel del curso es obligatorio");
        }
        if (curso.getCupo_Maximo() <=0 ){
            throw new IllegalArgumentException("Cupo no válido");
        }
        cursoDAO.editarCurso(curso);
    }
    public void eliminarCurso(Cursos curso){
        if (inscripcionDAO.tieneAlumnosInscriptos(curso.getId())){
            throw new IllegalArgumentException("No se puede eliminar un curso con inscripciones");
        }
        if (!cursoDAO.existeCurso(curso.getId())){
            throw new IllegalArgumentException("El curso NO EXISTE");
        }
        cursoDAO.eliminarCurso(curso);
    }
    public void BuscarCurso(Cursos curso){
        if (!cursoDAO.existeCurso(curso.getId())){
            throw new IllegalArgumentException("El curso NO EXISTE");
        }
        cursoDAO.eliminarCurso(curso);
    }
}


