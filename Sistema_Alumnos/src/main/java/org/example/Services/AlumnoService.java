package org.example.Services;

import org.example.DTO.Alumnos;
import org.example.Repositories.AlumnoDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AlumnoService {

    private final AlumnoDAO alumnoDAO = new AlumnoDAO();

    public boolean registrarAlumno(Alumnos alumno) {

        validarAlumno(alumno);

        if (alumnoDAO.existeCorreo(alumno.getCorreo_electronico())) {
            throw new IllegalArgumentException("Ya existe un alumno con ese correo.");
        }

        alumnoDAO.insertarAlumnos(alumno);
        return false;
    }

    public void editarCorreo(int idAlumno, String nuevoCorreo) {

        validarCorreo(nuevoCorreo);

        if (!alumnoDAO.existeAlumno(idAlumno)) {
            throw new IllegalArgumentException("El alumno no existe.");
        }

        alumnoDAO.editarCorreo(idAlumno, nuevoCorreo);
    }

    public List<Alumnos> listarAlumnos() throws SQLException {
        return alumnoDAO.ListarAlumnos();
    }

    public Alumnos ObtenerAlumnosPorId(int id) throws SQLException {

        Alumnos alumno = alumnoDAO.ObtenerAlumnosPorId(id);

        if (alumno == null) {
            throw new IllegalArgumentException("Alumno inexistente.");
        }

        return alumno;
    }


    public void eliminarAlumno(int idAlumno) {

        if (!alumnoDAO.existeAlumno(idAlumno)) {
            throw new IllegalArgumentException("El alumno no existe.");
        }

        if (alumnoDAO.tieneCursosActivos(idAlumno)) {
            throw new IllegalArgumentException(
                    "No puede eliminarse un alumno con cursos activos.");
        }

        alumnoDAO.eliminarAlumno(idAlumno);
    }

    private void validarAlumno(Alumnos alumno) {

        validarNombre(alumno.getNombre());

        validarApellido(alumno.getApellido());

        validarCorreo(alumno.getCorreo_electronico());

        validarFechas(
                alumno.getFecha_Nacimiento()
        );
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

    private void validarFechas(Date nacimiento) {
        if (nacimiento==null){
            System.out.println("La fecha de nacimiento es obligatoria");
        }

    }
}
