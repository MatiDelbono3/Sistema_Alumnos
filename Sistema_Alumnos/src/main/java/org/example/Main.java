package org.example;

import org.example.Connections.CursoConnection;
import org.example.DTO.Cursos;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {

        CursoConnection cursoConnection = new CursoConnection();
        // Probar la conexión
       cursoConnection.probarConexion();
        Cursos curso = new Cursos();  // ejemplo de datos
        curso.setNombre_curso("Torino");
        curso.setNivel("B1");
        curso.setCupo_Maximo(16);
        int resultado = cursoConnection.insertar(curso);



    }


    }


