package org.example;

import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session sesion= HibernateUtil.getSession();
        if (sesion !=null){
            System.out.println("Conexión realizada correctamente");
        }
        else {
            System.out.println("No se ha podido realizar la conexión");
    }
}
}