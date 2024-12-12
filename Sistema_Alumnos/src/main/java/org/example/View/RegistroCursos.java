package org.example.View;

import org.example.Connections.CursoConnection;
import org.example.Connections.UsersConnection;
import org.example.DTO.Cursos;
import org.example.DTO.Usuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class RegistroCursos extends javax.swing.JFrame {
    UsersConnection conexionUsuario=new UsersConnection();
    Usuarios usuario=new Usuarios();
    CursoConnection CC=new CursoConnection();
    org.example.DTO.Cursos curso=new org.example.DTO.Cursos();
    DefaultTableModel ModeloCurso=new DefaultTableModel();

    private JTextField NombreTxt;
    private JTextField NivelTxt;
    private JTextField CupoTxt;
    private JTable TablaCursos;




    public RegistroCursos() {
        setTitle("Registro Cursos");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        try {
            ListarCursos();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
        }
    }
    private void initComponents(){
        setLayout(new BorderLayout());
        // Panel para registro de curso
        JPanel PanelRegistro = new JPanel();
        PanelRegistro.setLayout(new GridLayout(4,2,10,10)); // 4 filas, 2 columnas
        PanelRegistro.setBackground(Color.LIGHT_GRAY);

        // labels


        JLabel labelNombre = new JLabel("nombre");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelNivel = new JLabel("nivel");
        labelNivel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelCupo = new JLabel("Cupo");
        labelCupo.setFont(new Font("Arial", Font.BOLD, 14));


        NombreTxt = new JTextField(10);
        NivelTxt = new JTextField(10);
        CupoTxt = new JTextField(10);

        // botones
        JButton botonRegistroCurso=new JButton("Registrar Curso");
        botonRegistroCurso.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistroCurso.setBackground(new Color(51, 153, 255));
        botonRegistroCurso.setForeground(Color.WHITE);
        botonRegistroCurso.setFocusPainted(false);
        botonRegistroCurso.setBorderPainted(false);
        botonRegistroCurso.setPreferredSize(new Dimension(120,35));



        PanelRegistro.add(labelNombre);
        PanelRegistro.add(NombreTxt);
        PanelRegistro.add(labelNivel);
        PanelRegistro.add(NivelTxt);
        PanelRegistro.add(labelCupo);
        PanelRegistro.add(CupoTxt);
        PanelRegistro.add(botonRegistroCurso);
        // Panel para la tabla
        JPanel PanelTabla = new JPanel(new BorderLayout());
        PanelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Cursos"));

        ModeloCurso = new DefaultTableModel(new String[]{"id", "nombre_curso", "nivel", "cupo_maximo"}, 0);
        TablaCursos = new JTable(ModeloCurso);
        JScrollPane scrollTabla = new JScrollPane(TablaCursos);
        PanelTabla.add(scrollTabla, BorderLayout.CENTER);

        add(PanelRegistro, BorderLayout.NORTH);
        add(PanelTabla, BorderLayout.CENTER);

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
                // Validación para asegurar que el campo 'Cupo' sea un número entero


                if (CC.insertar(curso) >0 ){
                    JOptionPane.showMessageDialog(null, "curso registrado con éxito");
                    limpiarDatosCurso();
                }
                else {
                    JOptionPane.showConfirmDialog(null, "error al registrar el curso");
                }
            }
        });
    }
    private void ListarCursos() throws SQLException {
        List<Cursos>ListaCursos=CC.ListarCursos();
        ModeloCurso= (DefaultTableModel) TablaCursos.getModel();
        Object[] Objeto =new Object[4];
        for (int i=0; i<ListaCursos.size();i++){
            Objeto[0]=ListaCursos.get(i).getId();
            Objeto[1]=ListaCursos.get(i).getNombre_curso();
            Objeto[2]=ListaCursos.get(i).getNivel();
            Objeto[3]=ListaCursos.get(i).getCupo_Maximo();
            ModeloCurso.addRow(Objeto);
        }
        TablaCursos.setModel(ModeloCurso);
    }
    void limpiarDatosCurso(){
        NombreTxt.setText("");
        NivelTxt.setText("");
        CupoTxt.setText("");

    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new MenuPrincipal().setVisible(true));
    }
}


