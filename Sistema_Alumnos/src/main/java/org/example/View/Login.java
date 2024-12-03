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


        JTextField textField=new JTextField(10);
        JPasswordField passwordField=new JPasswordField(10);

        // botón de registro
        JButton botonRegistro=new JButton("Registrate");
        botonRegistro.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistro.setBackground(new Color(51, 153, 255));
        botonRegistro.setForeground(Color.WHITE);
        botonRegistro.setFocusPainted(false);
        botonRegistro.setBorderPainted(false);
        botonRegistro.setPreferredSize(new Dimension(120,35));

        // Estilos de texto
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        botonRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Login.this, "Registrado Correctamente!");
            }
        });

        // agregar componentes al panel
        panel.add(labelUsuario);
        panel.add(textField);
        panel.add(labelPassword);
        panel.add(passwordField);
        panel.add(botonRegistro);

        add(panel);
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() ->
            new Login().setVisible(true));
    };
}
