package org.example.View;

import org.example.Connections.CursoConnection;
import org.example.Connections.UsersConnection;
import org.example.DTO.Cursos;
import org.example.DTO.Usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends javax.swing.JFrame {
    UsersConnection conexionUsuario=new UsersConnection();
    Usuarios usuario=new Usuarios();
    Cursos curso=new Cursos();
    CursoConnection CursoC=new CursoConnection();
    public MenuPrincipal() {
        setTitle("Menu Principal");
        setSize(600,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    @SuppressWarnings("unchecked")
    private void initComponents(){
     //panel
     JPanel PanelMenu=new JPanel();
     PanelMenu.setBackground(Color.GREEN);
     PanelMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

    // botones
    JButton botonCursos=new JButton("Cursos");
        botonCursos.setFont(new Font("Arial",Font.BOLD, 14));
        botonCursos.setBackground(new Color(51, 153, 255));
        botonCursos.setForeground(Color.WHITE);
        botonCursos.setFocusPainted(false);
        botonCursos.setBorderPainted(false);
        botonCursos.setPreferredSize(new Dimension(120,35));


        JButton botonInscripciones=new JButton("Inscripciones");
        botonInscripciones.setFont(new Font("Arial",Font.BOLD, 14));
        botonInscripciones.setBackground(new Color(51, 153, 255));
        botonInscripciones.setForeground(Color.WHITE);
        botonInscripciones.setFocusPainted(false);
        botonInscripciones.setBorderPainted(false);
        botonInscripciones.setPreferredSize(new Dimension(150,35));

        JButton botonProfesores=new JButton("Docentes");
        botonProfesores.setFont(new Font("Arial",Font.BOLD, 14));
        botonProfesores.setBackground(new Color(51, 153, 255));
        botonProfesores.setForeground(Color.WHITE);
        botonProfesores.setFocusPainted(false);
        botonProfesores.setBorderPainted(false);
        botonProfesores.setPreferredSize(new Dimension(150,35));


        // imagen
        JLabel labelImagenCarreras=new JLabel();
        ImageIcon iconoCarreras=new ImageIcon("src/main/java/org/example/imagenes/educacion.png");
        Image imagen=iconoCarreras.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        labelImagenCarreras.setIcon(new ImageIcon(imagen));
        labelImagenCarreras.setHorizontalAlignment(JLabel.CENTER);

    // agregar componentes al panel
    PanelMenu.add(botonCursos);
    PanelMenu.add(botonInscripciones);
    PanelMenu.add(botonProfesores);
    PanelMenu.add(labelImagenCarreras, BorderLayout.NORTH);
    // agregar panel
    add(PanelMenu);
    botonCursos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    }
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() ->
                    new MenuPrincipal().setVisible(true));
        };
}
