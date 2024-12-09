package org.example.View;

import org.example.Connections.CursoConnection;
import org.example.Connections.UsersConnection;
import org.example.DTO.Cursos;
import org.example.DTO.Usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroCursos extends javax.swing.JFrame{
    UsersConnection conexionUsuario=new UsersConnection();
    Usuarios usuario=new Usuarios();
    CursoConnection CC=new CursoConnection();
    Cursos curso=new Cursos();

    private JTextField NombreTxt;
    private JTextField NivelTxt;
    private JTextField CupoTxt;




    public RegistroCursos() {
        setTitle("Registro Cursos");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    private void initComponents(){

        // Panel para registro de curso
        JPanel PanelRegistro = new JPanel();
        PanelRegistro.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // 4 filas, 2 columnas
        PanelRegistro.setBackground(Color.BLUE);

        // labels
        JLabel labelNombre = new JLabel("nombre");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelNivel = new JLabel("nivel");
        labelNivel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelCupo = new JLabel("Cupo");
        labelCupo.setFont(new Font("Arial", Font.BOLD, 14));

       JTextField NombreTxt = new JTextField(10);
        JTextField NivelTxt = new JTextField(10);
        JTextField CupoTxt = new JTextField(10);

        // botones
        JButton botonRegistroCurso=new JButton("Registrar Curso");
        botonRegistroCurso.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistroCurso.setBackground(new Color(51, 153, 255));
        botonRegistroCurso.setForeground(Color.WHITE);
        botonRegistroCurso.setFocusPainted(false);
        botonRegistroCurso.setBorderPainted(false);
        botonRegistroCurso.setPreferredSize(new Dimension(120,35));


        PanelRegistro.add(botonRegistroCurso);
        PanelRegistro.add(labelNombre);
        PanelRegistro.add(NombreTxt);
        PanelRegistro.add(labelNivel);
        PanelRegistro.add(NivelTxt);
        PanelRegistro.add(labelCupo);
        PanelRegistro.add(CupoTxt);


        add(PanelRegistro);

        // Estilos de texto
        NombreTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NivelTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        CupoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        //Crear
        botonRegistroCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               curso.setNombre_curso(NombreTxt.getText());
               curso.setNivel(NivelTxt.getText());
               curso.setCupo_Maximo(Integer.parseInt(CupoTxt.getText()));

               if (CC.insertar(curso) >0 ){
                   JOptionPane.showMessageDialog(null, "curso registrado con éxito");
               }
               else {
                   JOptionPane.showConfirmDialog(null, "error al registrar el curso");
               }
            }
        });
    }
        void limpiarDatosCurso(){
            NombreTxt.setText("");
            NivelTxt.setText("");
            CupoTxt.setText("");
            dispose();
        }



        public static void main(String[] args) {
            SwingUtilities.invokeLater(() ->
                    new MenuPrincipal().setVisible(true));
        };
    }


