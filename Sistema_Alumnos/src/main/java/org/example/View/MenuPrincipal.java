package org.example.View;

import org.example.Connections.CursoConnection;
import org.example.Connections.UsersConnection;
import org.example.DTO.Cursos;
import org.example.DTO.Usuarios;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MenuPrincipal extends javax.swing.JFrame {
    UsersConnection conexionUsuario=new UsersConnection();
    Usuarios usuario=new Usuarios();
    CursoConnection CC=new CursoConnection();

    private JTextField NombreTxt;
    private JTextField NivelTxt;
    private JTextField CupoTxt;


    public MenuPrincipal() {
        setTitle("Menu Principal");
        setSize(600,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {


        //panel
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BorderLayout());
        panelMenu.setBackground(Color.GREEN); // Color de fondo verde






    // botones
        JButton botonCursos=new JButton("Cursos");
        botonCursos.setFont(new Font("Arial",Font.BOLD, 14));
        botonCursos.setBackground(new Color(51, 153, 255));
        botonCursos.setForeground(Color.WHITE);
        botonCursos.setFocusPainted(false);
        botonCursos.setBorderPainted(false);
        botonCursos.setPreferredSize(new Dimension(150,35));



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
        botonProfesores.setPreferredSize(new Dimension(120,35));


        // imagen
        JLabel labelImagenCarreras=new JLabel();
        ImageIcon iconoCarreras=new ImageIcon("src/main/java/org/example/imagenes/educacion.png");
        Image imagen=iconoCarreras.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        labelImagenCarreras.setIcon(new ImageIcon(imagen));
        labelImagenCarreras.setHorizontalAlignment(JLabel.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Disposición vertical
        panelBotones.setBackground(Color.GREEN);
        panelBotones.add(botonCursos);
        panelBotones.add(botonInscripciones);
        panelBotones.add(botonProfesores);

        // Agregar la imagen al panel principal
        panelMenu.add(labelImagenCarreras, BorderLayout.NORTH);
        panelMenu.add(panelBotones, BorderLayout.CENTER); // Panel con los botones en el centro

    add(panelMenu);

        botonCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistroCursos Cur= null;
                Cur = new RegistroCursos();
                Cur.setVisible(true);

            }
    });

    }
    void limpiarDatosCurso(){
        NombreTxt.setText("");
        NivelTxt.setText("");
        CupoTxt.setText("");
    };
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new MenuPrincipal().setVisible(true));
    };
        }





