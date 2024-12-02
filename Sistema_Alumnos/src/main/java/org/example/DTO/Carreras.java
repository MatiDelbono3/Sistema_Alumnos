package org.example.DTO;

public class Carreras {
    private int id;
    private String nombre_carrera;
    private int longitud;


public Carreras(){

}
public Carreras(int id, String nombre_carrera, int longitud){
    this.setId(id);
    this.setNombre_carrera(nombre_carrera);
    this.setLongitud(longitud);
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}