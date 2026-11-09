package org.example.View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

final class MenuNavigation {
    private MenuNavigation() {
    }

    static void instalarVolverAlMenu(JFrame ventana) {
        JButton botonVolver = new JButton("Volver al menú principal");
        botonVolver.addActionListener(event -> ventana.dispose());

        JMenuBar barraMenu = new JMenuBar();
        barraMenu.add(botonVolver);
        ventana.setJMenuBar(barraMenu);
    }
}
