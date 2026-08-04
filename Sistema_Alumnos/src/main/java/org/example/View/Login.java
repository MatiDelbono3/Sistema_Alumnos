package org.example.View;

import org.example.Connections.Connections;
import org.example.DTO.Usuarios;
import org.example.Repositories.UsuarioDAO;
import org.example.Services.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends javax.swing.JFrame {
    UsuarioService usuarioService=new UsuarioService();
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
        JButton botonIngreso=new JButton("Ingresa");
        botonIngreso.setFont(new Font("Arial", Font.BOLD, 14));
        botonIngreso.setBackground(new Color(51, 153, 255));
        botonIngreso.setForeground(Color.WHITE);
        botonIngreso.setFocusPainted(false);
        botonIngreso.setBorderPainted(false);
        botonIngreso.setPreferredSize(new Dimension(120,35));

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
        panel.add(botonIngreso);
        panel.add(botonCierre);
        panel.add(labelImagen, BorderLayout.NORTH);

        // Crear el hipervínculo
        JLabel LabelRegistro=new JLabel("<html>¿No tienes una cuenta? <font color=\"blue\"><u>Regístrate aquí</u></font></html>");
        LabelRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        LabelRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Cuando se hace clic en el JLabel, abrimos la ventana de registro
                JOptionPane.showMessageDialog(Login.this, "Abriendo ventana de registro..."); // Mensaje de prueba
                Registro registro=new Registro();
                registro.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                LabelRegistro.setForeground(Color.RED); // Cambia el color al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                LabelRegistro.setForeground(Color.BLUE); // Vuelve al color original
            }
        });
        panel.add(new JLabel("")); // Espaciador
        panel.add(LabelRegistro); // Añade el JLabel al panel

        add(panel); // Añadir el panel a la ventana
        setVisible(true); // Hacer la ventana visible
        botonIngreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password=new String(PassTxt.getPassword());
               usuarioService.login(Integer.parseInt(UserTxt.getText()), password);
               if (usuario.getUsuario() != null && usuario.getContrasena() != null){
                   JOptionPane.showConfirmDialog(null, "Bienvenido al sistema");
                   MenuPrincipal menu=new MenuPrincipal();
                   menu.setVisible(true);
                   dispose();
               }
               else {
                   JOptionPane.showMessageDialog( null, "no es posible ingresar al sistema");
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