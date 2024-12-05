package org.example.View;
import org.example.Connections.UsersConnection;
import org.example.DTO.Usuarios;


import javax.swing.*;
import java.awt.*;

public class Cursos extends javax.swing.JFrame  {
    UsersConnection conexionUsuario=new UsersConnection();
    Usuarios usuario=new Usuarios();
    private JButton cerrarButton;


    public Cursos () {
        setTitle("Cursos");
        setSize(600,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // labels
        JLabel labelNombre=new JLabel("usuario");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelNivel =new JLabel("password");
        labelNivel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelCupo =new JLabel("Cupo");
        labelNivel.setFont(new Font("Arial", Font.BOLD, 14));

        //panel
        JPanel panelCursos=new JPanel();
        panelCursos.setBackground(Color.blue);
        panelCursos.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));


        JTextField NombreTxt=new JTextField(10);
        JTextField NivelTxt=new JTextField(10);
        JTextField CupoTxt=new JTextField(10);


        // botones
        JButton botonRegistroCurso=new JButton("Registrar Curso");
        botonRegistroCurso.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistroCurso.setBackground(new Color(51, 153, 255));
        botonRegistroCurso.setForeground(Color.WHITE);
        botonRegistroCurso.setFocusPainted(false);
        botonRegistroCurso.setBorderPainted(false);
        botonRegistroCurso.setPreferredSize(new Dimension(150,35));

        // Estilos de texto
        NombreTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NivelTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        CupoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // agregar componentes al panel
        panelCursos.add(labelNombre);
        panelCursos.add(NombreTxt);
        panelCursos.add(labelNivel);
        panelCursos.add(NivelTxt);
        panelCursos.add(labelCupo);
        panelCursos.add(CupoTxt);
        panelCursos.add(botonRegistroCurso);




        // agregar panel
        add(panelCursos);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new Cursos().setVisible(true));
    };
}