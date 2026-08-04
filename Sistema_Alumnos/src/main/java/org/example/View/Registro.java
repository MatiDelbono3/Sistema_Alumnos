package org.example.View;
import org.example.DTO.Usuarios;
import org.example.Repositories.UsuarioDAO;
import org.example.Services.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Registro extends javax.swing.JFrame {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmarContrasena;
    Usuarios usuario=new Usuarios();
    UsuarioService usuarioService=new UsuarioService();
    public Registro() {
        setTitle("Crear cuenta");
        setSize(600,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    private void initComponents() {

        //Paneles

        // Panel para los campos del formulario (usamos GridLayout)
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 filas, 2 columnas, espacios de 10px

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        txtNombre=new JTextField(20);
        txtApellido=new JTextField(20);
        txtUsuario = new JTextField(20);
        txtContrasena = new JPasswordField(20);
        txtConfirmarContrasena = new JPasswordField(20);

        // labels

        JLabel labelNombre=new JLabel("nombre");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 10));

        JLabel labelApellido=new JLabel("apellido");
        labelApellido.setFont(new Font("Arial", Font.BOLD, 10));

        JLabel labelUsuario=new JLabel("nombre usuario");
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 10));

        JLabel labelPassword =new JLabel("contrasena");
        labelPassword.setFont(new Font("Arial", Font.BOLD, 10));

        JLabel labelConfirmPassword =new JLabel(" confirmar contrasena");
        labelConfirmPassword.setFont(new Font("Arial", Font.BOLD, 10));


        //agregar elementos al panel
        formPanel.add(labelNombre);
        formPanel.add(txtNombre);
        formPanel.add(labelApellido);
        formPanel.add(txtApellido);
        formPanel.add(labelUsuario);
        formPanel.add(txtUsuario);
        formPanel.add(labelPassword);
        formPanel.add(txtContrasena);
        formPanel.add(labelConfirmPassword);
        formPanel.add(txtConfirmarContrasena);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        this.add(mainPanel);


        //botones
        JButton botonRegistro=new JButton("Registrate");
        botonRegistro.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistro.setBackground(new Color(51, 153, 255));
        botonRegistro.setForeground(Color.WHITE);
        botonRegistro.setFocusPainted(false);
        botonRegistro.setBorderPainted(false);
        botonRegistro.setPreferredSize(new Dimension(120,35));

        formPanel.add(botonRegistro);
        botonRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre=txtNombre.getText();
                String Apellido=txtApellido.getText();
                String nombreUsuario=txtUsuario.getText();
                String contrasena=new String(txtContrasena.getPassword());
                String ConfirmacionContrasena=new String(txtConfirmarContrasena.getPassword());
                if(!contrasena.equals(ConfirmacionContrasena)){
                    JOptionPane.showMessageDialog(Registro.this, "Las contraseñas ingresadas no coinciden");
                    return;
                }
                usuario.setNombre(nombre);
                usuario.setApellido(Apellido);
                usuario.setUsuario(nombreUsuario);
                usuario.setContrasena(contrasena);

                boolean RegistroExitoso=usuarioService.RegistrarUsuario(usuario);
                if (RegistroExitoso){
                    JOptionPane.showConfirmDialog(null, "¡ Se ha registrado con éxito");
                    dispose();
                    // Vuelve a la pantalla de login (Opción 1: Reabrir)
                    SwingUtilities.invokeLater(() -> {
                        new Login().setVisible(true);
                    });
                }



                }
            });




}
}