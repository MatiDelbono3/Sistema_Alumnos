package org.example.View;

import jakarta.persistence.Id;
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

    private JTextField IdTxt;
    private JTextField NombreTxt;
    private JTextField NivelTxt;
    private JTextField CupoTxt;
    private JTextField CursoAEliminarTxt;
    private JTextField NuevoCupoTxt;
    private JTextField CursoABuscarTxt;
    private JTable TablaCursos;




    public RegistroCursos() {
        setTitle("Registro Cursos");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        ListarCursos();
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

        JLabel labelIdCurso = new JLabel("ID del Curso: ");
        JLabel idCursoSeleccionado = new JLabel(""); // Curso seleccionado para modificar

        JLabel labelnuevoCupo=new JLabel("Nuevo cupo");
        labelnuevoCupo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelCursoAEliminar=new JLabel("Curso a eliminar");
        labelnuevoCupo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelCursoABuscar=new JLabel("Id a buscar");
        labelnuevoCupo.setFont(new Font("Arial", Font.BOLD, 14));


        IdTxt=new JTextField(10);
        NombreTxt = new JTextField(10);
        NivelTxt = new JTextField(10);
        CupoTxt = new JTextField(10);
        NuevoCupoTxt=new JTextField(10);
        CursoAEliminarTxt=new JTextField(10);
        CursoABuscarTxt=new JTextField(10);

        labelnuevoCupo.setVisible(false);
        NuevoCupoTxt.setVisible(false);

        labelCursoABuscar.setVisible(true);
        CursoABuscarTxt.setVisible(true);
        // botones
        JButton botonRegistroCurso=new JButton("Registrar Curso");
        botonRegistroCurso.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistroCurso.setBackground(new Color(51, 153, 255));
        botonRegistroCurso.setForeground(Color.WHITE);
        botonRegistroCurso.setFocusPainted(false);
        botonRegistroCurso.setBorderPainted(false);
        botonRegistroCurso.setPreferredSize(new Dimension(120,35));

        JButton botonModificarCurso=new JButton("Modificar Curso");
        botonModificarCurso.setFont(new Font("Arial", Font.BOLD, 14));
        botonModificarCurso.setBackground(new Color(51, 153, 255));
        botonModificarCurso.setForeground(Color.WHITE);
        botonModificarCurso.setFocusPainted(false);
        botonModificarCurso.setBorderPainted(false);
        botonModificarCurso.setPreferredSize(new Dimension(120,35));

        JButton botonEliminarCurso=new JButton("Eliminar Curso");
        botonEliminarCurso.setFont(new Font("Arial", Font.BOLD, 14));
        botonEliminarCurso.setBackground(new Color(51, 153, 255));
        botonEliminarCurso.setForeground(Color.WHITE);
        botonEliminarCurso.setFocusPainted(false);
        botonEliminarCurso.setBorderPainted(false);
        botonEliminarCurso.setPreferredSize(new Dimension(120,35));

        JButton botonBuscarCurso=new JButton("Buscar Curso");
        botonBuscarCurso.setFont(new Font("Arial", Font.BOLD, 14));
        botonBuscarCurso.setBackground(new Color(51, 153, 255));
        botonBuscarCurso.setForeground(Color.WHITE);
        botonBuscarCurso.setFocusPainted(false);
        botonBuscarCurso.setBorderPainted(false);
        botonBuscarCurso.setPreferredSize(new Dimension(120,35));


        PanelRegistro.add(labelNombre);
        PanelRegistro.add(NombreTxt);
        PanelRegistro.add(labelNivel);
        PanelRegistro.add(NivelTxt);
        PanelRegistro.add(labelCupo);
        PanelRegistro.add(CupoTxt);
        PanelRegistro.add(labelIdCurso);
        PanelRegistro.add(idCursoSeleccionado);
        PanelRegistro.add(labelnuevoCupo);
        PanelRegistro.add(NuevoCupoTxt);
        PanelRegistro.add(labelCursoABuscar);
        PanelRegistro.add(CursoABuscarTxt);
        PanelRegistro.add(botonRegistroCurso);
        PanelRegistro.add(botonModificarCurso);
        PanelRegistro.add(botonEliminarCurso);
        PanelRegistro.add(botonBuscarCurso);

        // Panel para la tabla
        JPanel PanelTabla = new JPanel(new BorderLayout());
        PanelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Cursos"));
        PanelTabla.setBackground(Color.LIGHT_GRAY);


        ModeloCurso = new DefaultTableModel(new String[]{"id", "nombre_curso", "nivel", "cupo_maximo"}, 0);
        TablaCursos = new JTable(ModeloCurso);
        JScrollPane scrollTabla = new JScrollPane(TablaCursos);
        PanelTabla.add(scrollTabla, BorderLayout.CENTER);

        add(PanelRegistro, BorderLayout.NORTH);
        add(PanelTabla, BorderLayout.CENTER);

        // Estilos de texto
        IdTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NombreTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NivelTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        CupoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NuevoCupoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        CursoAEliminarTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //Logica de los botones
        botonRegistroCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curso.setNombre_curso(NombreTxt.getText());
                curso.setNivel(NivelTxt.getText());
                curso.setCupo_Maximo(Integer.parseInt(CupoTxt.getText()));
                // Validación para asegurar que el campo 'Cupo' sea un número entero


                if (CC.insertar(curso) >0 ){
                    JOptionPane.showMessageDialog(null, "curso registrado con éxito");
                    ListarCursos();
                    limpiarDatosCurso();
                }
                else {
                    JOptionPane.showConfirmDialog(null, "error al registrar el curso");
                }
            }
        });
        TablaCursos.getSelectionModel().addListSelectionListener(e ->{
            if (!e.getValueIsAdjusting()) {
                int FilaSeleccionada=TablaCursos.getSelectedRow();
            if (FilaSeleccionada != -1 ){
                int IdCursoSeleccionado= (int) TablaCursos.getValueAt(FilaSeleccionada, 0);
                idCursoSeleccionado.setText(String.valueOf(IdCursoSeleccionado));
                CursoAEliminarTxt.setText(String.valueOf(IdCursoSeleccionado));
                if (IdCursoSeleccionado > 0) {
                    curso.setId(IdCursoSeleccionado);
                    labelIdCurso.setVisible(true);
                    labelnuevoCupo.setVisible(true);
                    NuevoCupoTxt.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Error al obtener el ID del curso");
                }
            }
            }
        });
        botonModificarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NombreTxt.setEnabled(false);
                NivelTxt.setEnabled(false);
                CupoTxt.setEnabled(false);
                labelnuevoCupo.setVisible(true);
                NuevoCupoTxt.setVisible(true);
                int fila=TablaCursos.getSelectedRow();
                if (fila==-1 ){
                    JOptionPane.showMessageDialog(null, "Seleccione un curso");
                    return;
                }
                String nuevoCupo= NuevoCupoTxt.getText();
                try{
                    if (nuevoCupo.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Error al obtener el ID del curso");
                        return;
                    }
                } catch (HeadlessException ex) {
                    throw new RuntimeException(ex);
                }
                try{
                    curso.setCupo_Maximo(Integer.parseInt(nuevoCupo));

                        if (CC.editar(curso) ){
                            JOptionPane.showMessageDialog(null, "curso modificado con éxito");
                            ListarCursos();
                            limpiarDatosCurso();
                        }
                        else {
                            JOptionPane.showConfirmDialog(null, "error al modificar el curso");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                ListarCursos();
            }
        });
        botonEliminarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CursoAEliminarTxt.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error al obtener el ID del curso");
                }
                curso.setId(Integer.parseInt(CursoAEliminarTxt.getText()));
                if (CC.eliminar(curso) ){
                    JOptionPane.showMessageDialog(null, "curso eliminado con éxito");
                    ListarCursos();
                }
                else {
                    JOptionPane.showConfirmDialog(null, "error al eliminar el curso");
                }
            }
        });
        botonBuscarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CursoABuscarTxt.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error al obtener el ID del curso");
                    return;
                }
                curso.setId(Integer.parseInt(CursoABuscarTxt.getText()));
                if (CC.Buscar(curso)){
                    IdTxt.setText(String.valueOf(curso.getId()));
                    NombreTxt.setText(curso.getNombre_curso());
                    NivelTxt.setText(curso.getNivel());
                    CupoTxt.setText(String.valueOf(curso.getCupo_Maximo()));
                }
                else {
                    JOptionPane.showConfirmDialog(null, "curso no encontrado");
                    IdTxt.setText("");
                    NombreTxt.setText("");
                    NivelTxt.setText("");
                    CupoTxt.setText("");
                }
            }
        });


    }
    private void ListarCursos()  {
                        List<Cursos>ListaCursos= null;
                        try {
                            ListaCursos = CC.ListarCursos();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        ModeloCurso.setRowCount(0);
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

        NombreTxt.setEnabled(true);
        NivelTxt.setEnabled(true);
        CupoTxt.setEnabled(true);

        NuevoCupoTxt.setVisible(false);
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new RegistroCursos().setVisible(true));
    }



    }

