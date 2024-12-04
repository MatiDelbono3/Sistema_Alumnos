package org.example.View;

import org.example.Connections.UsersConnection;
import org.example.DTO.Usuarios;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends javax.swing.JFrame {
    UsersConnection conexionUsuario=new UsersConnection();
    Usuarios usuario=new Usuarios();


    public MenuPrincipal() {
        setTitle("Login");
        setSize(600,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    private void initComponents(){

    // botones
    JButton botonCarreras=new JButton("Registrate");
        botonCarreras.setFont(new Font("Arial",Font.BOLD, 14));
        botonCarreras.setBackground(new Color(51, 153, 255));
        botonCarreras.setForeground(Color.WHITE);
        botonCarreras.setFocusPainted(false);
        botonCarreras.setBorderPainted(false);
        botonCarreras.setPreferredSize(new Dimension(120,35));

}
}