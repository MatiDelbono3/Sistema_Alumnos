package org.example.Services;

import org.example.DTO.Cursos;
import org.example.DTO.Docentes;
import org.example.Repositories.CursoDAO;
import org.example.Repositories.DocenteDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
public class DocenteService {
    private final DocenteDAO docenteDAO = new DocenteDAO();
    private final CursoDAO cursoDAO = new CursoDAO();
    public boolean CrearDocente(Docentes docente) throws SQLException {
        validarDocente(docente);
        if (docenteDAO.existeCorreo(docente.getCorreo_electronico())) {
            throw new IllegalArgumentException("Ya existe un docente con ese correo.");
        }
        return docenteDAO.insertarDocentes(docente);
    }
    public boolean editarCorreo(String nuevoCorreo, String idDocente) throws SQLException {
        validarCorreo(nuevoCorreo);
        if (!docenteDAO.existeDocente(String.valueOf(idDocente))) {
            throw new IllegalArgumentException("El docente no existe.");
        }
        return docenteDAO.editarCorreo(nuevoCorreo, idDocente);
    }
    public List<Docentes> listarDocentes() throws SQLException {
        return docenteDAO.ListarDocentes();
    }
public Docentes ObtenerDocentePorId(int id) throws SQLException {
        Docentes docente = docenteDAO.ObtenerDocentePorId(id);
        if (docente == null) {
            throw new IllegalArgumentException("Docente inexistente.");
        }
        return docente;
    }
    public List<Docentes> buscarDocentesPorNombre(String nombre) throws SQLException {
        return docenteDAO.DocentesPorNombre(nombre);
    }
    public List<Cursos> listarCursosPorDocente(String idDocente) throws SQLException {
        if (!docenteDAO.existeDocente((idDocente))) {
            throw new IllegalArgumentException("El docente no existe.");
        }
        return docenteDAO.CursosAsignados(String.valueOf(idDocente));
    }
    private void validarDocente(Docentes docente) {

        validarNombre(docente.getNombre());

        validarApellido(docente.getApellido());

        validarCorreo(docente.getCorreo_electronico());

        validarFechas(
                docente.getFecha_nacimiento(),
                 docente.getFecha_contratacion()

        );
    }
    public boolean eliminarDocente(String idDocente) throws SQLException {
        if (!docenteDAO.existeDocente(idDocente)) {
            throw new IllegalArgumentException("El docente no existe.");
        }
        return docenteDAO.eliminarDocente(idDocente);
    }

    private void validarNombre(String nombre) {
        if (nombre==null || nombre.isEmpty()){
            System.out.println("El nombre es obligatorio");
        }
    }

    private void validarApellido(String apellido) {

        if (apellido==null || apellido.isEmpty()){
            System.out.println("El apellido es obligatorio");
        }
    }

    private void validarCorreo(String correo) {
        if (correo ==null || !correo.contains("@")) {
            System.out.println("El correo debe contener @");
        }
    }

    private void validarFechas(Date nacimiento, Date contratacion) {
        if (nacimiento==null){
            System.out.println("La fecha de nacimiento es obligatoria");
        }
        if (contratacion==null){
            System.out.println("La fecha de contratacion es obligatoria");


    }
}

}
