package org.example.DTO;

public class Cursos {
    private int id;
    private String nombre_curso;
    private String nivel;
    private int Cupo_Maximo;

    public Cursos(){

    }
    public Cursos(int id, String nombre_curso, String nivel, int Cupo_Maximo){
        this.setId(id);
        this.setNombre_curso(nombre_curso);
        this.setNivel(nivel);
        this.setCupo_Maximo(Cupo_Maximo);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getCupo_Maximo() {
        return Cupo_Maximo;
    }

    public void setCupo_Maximo(int cupo_Maximo) {
        Cupo_Maximo = cupo_Maximo;
    }
}
