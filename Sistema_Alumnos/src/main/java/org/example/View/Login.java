package org.example.View;

import org.example.Connections.UsersConnection;
import org.example.DTO.Usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends javax.swing.JFrame {

    UsersConnection conexionUsuario=new UsersConnection();
    Usuarios usuario=new Usuarios();


    public Login() {
        setTitle("Login");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // labels
        JLabel labelUsuario=new JLabel("usuario");
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelPassword =new JLabel("password");
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 14));



        //panel
        JPanel panel=new JPanel();
        panel.setBackground(Color.blue);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));


        JTextField UserTxt=new JTextField(10);
        JPasswordField PassTxt=new JPasswordField(10);

        // botón de registro
        JButton botonRegistro=new JButton("Registrate");
        botonRegistro.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistro.setBackground(new Color(51, 153, 255));
        botonRegistro.setForeground(Color.WHITE);
        botonRegistro.setFocusPainted(false);
        botonRegistro.setBorderPainted(false);
        botonRegistro.setPreferredSize(new Dimension(120,35));

        // Estilos de texto
        UserTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        PassTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // agregar componentes al panel
        panel.add(labelUsuario);
        panel.add(UserTxt);
        panel.add(labelPassword);
        panel.add(PassTxt);
        panel.add(botonRegistro);

        add(panel);

        botonRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               usuario=conexionUsuario.login(UserTxt.getText(), PassTxt.getText());
            }
        });


    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() ->
            new Login().setVisible(true));
    };
}
