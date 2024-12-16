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
    private JButton cerrarButton;


    public Login() {
        setTitle("Login");
        setSize(600,450);
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
        labelPassword.setFont(new Font("Arial", Font.BOLD, 14));



        //paneles
        JPanel panel=new JPanel();
        panel.setBackground(Color.blue);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JPanel panel2=new JPanel();
        panel2.setBackground(Color.RED);
        panel2.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        panel2.add(panel, BorderLayout.CENTER);

        add(panel2, BorderLayout.CENTER);


        JTextField UserTxt=new JTextField(10);
        JPasswordField PassTxt=new JPasswordField(10);

        //botones
        JButton botonRegistro=new JButton("Registrate");
        botonRegistro.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistro.setBackground(new Color(51, 153, 255));
        botonRegistro.setForeground(Color.WHITE);
        botonRegistro.setFocusPainted(false);
        botonRegistro.setBorderPainted(false);
        botonRegistro.setPreferredSize(new Dimension(120,35));

        JButton botonCierre=new JButton("Cerrar");
        botonCierre.setFont(new Font("Arial", Font.BOLD, 14));
        botonCierre.setBackground(new Color(51, 153, 255));
        botonCierre.setForeground(Color.WHITE);
        botonCierre.setFocusPainted(false);
        botonCierre.setBorderPainted(false);
        botonCierre.setPreferredSize(new Dimension(120,35));



        // imagen
        JLabel labelImagen=new JLabel();
        ImageIcon icono=new ImageIcon("src/main/java/org/example/imagenes/graduado.png");
        Image imagen=icono.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        labelImagen.setIcon(new ImageIcon(imagen));
        labelImagen.setHorizontalAlignment(JLabel.CENTER);



        // Estilos de texto
        UserTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        PassTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // agregar componentes al panel
        panel.add(labelUsuario);
        panel.add(UserTxt);
        panel.add(labelPassword);
        panel.add(PassTxt);
        panel.add(botonRegistro);
        panel.add(botonCierre);
        panel.add(labelImagen, BorderLayout.NORTH);


        botonRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password=new String(PassTxt.getPassword());
               usuario=conexionUsuario.login(UserTxt.getText(), password);
               if (usuario.getUsuario() != null && usuario.getContrasena() != null){
                   JOptionPane.showConfirmDialog(null, "Bienvenido al sistema");
                   MenuPrincipal menu=new MenuPrincipal();
                   menu.setVisible(true);
                   dispose();
               }
               else {
                   JOptionPane.showMessageDialog( null, "no es posible registrarte");
               }
            }

        });
        botonCierre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() ->
            new Login().setVisible(true));
    };
}
