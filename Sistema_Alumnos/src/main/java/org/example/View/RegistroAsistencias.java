package org.example.View;

import org.example.DTO.Alumnos;
import org.example.DTO.Asistencias;
import org.example.DTO.Cursos;
import org.example.DTO.Usuarios;
import org.example.Services.AsistenciaService;
import org.example.Services.CursoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class RegistroAsistencias extends javax.swing.JFrame {

    Usuarios usuario = new Usuarios();
    AsistenciaService AS = new AsistenciaService();
    Cursos curso = new Cursos();
    Asistencias asistencia= new Asistencias();
    DefaultTableModel modeloAsistencias = new DefaultTableModel();

    private JTextField idTxt;
    private JTextField idCursoTxt;
    private JTextField idAlumnoTxt;
    private JTextField fechaTxt;
    private JTextField estadoTxt;
    private JTextField AsistenciaABuscarTxt;
    private JTable tablaAsistencias;

    public RegistroAsistencias() {
        setTitle("Registro de Asistencias");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel para registro de asistencias
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(new GridLayout(5, 2, 10, 10));
        panelRegistro.setBackground(Color.LIGHT_GRAY);

        // Labels
        JLabel labelIdCurso = new JLabel("ID del Curso:");
        labelIdCurso.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelIdAlumno = new JLabel("ID del Alumno:");
        labelIdAlumno.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelFecha = new JLabel("Fecha:");
        labelFecha.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelEstado = new JLabel("Estado:");
        labelEstado.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelId = new JLabel("ID Asistencia:");
        labelId.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelAsistenciaABuscar = new JLabel("ID Asistencia:");
        labelId.setFont(new Font("Arial", Font.BOLD, 14));
        // Text fields
        idCursoTxt = new JTextField(10);
        idAlumnoTxt = new JTextField(10);
        fechaTxt = new JTextField(10);
        estadoTxt = new JTextField(10);
        idTxt = new JTextField(10);


        // Botones
        JButton botonRegistrarAsistencia = new JButton("Registrar Asistencia");
        JButton botonBuscarAsistencia = new JButton("Buscar Asistencia");
        JButton botonEliminarAsistencia = new JButton("Eliminar Asistencia");

        // Agregar componentes al panel
        panelRegistro.add(labelId);
        panelRegistro.add(idTxt);
        panelRegistro.add(labelIdCurso);
        panelRegistro.add(idCursoTxt);
        panelRegistro.add(labelIdAlumno);
        panelRegistro.add(idAlumnoTxt);
        panelRegistro.add(labelFecha);
        panelRegistro.add(fechaTxt);
        panelRegistro.add(labelEstado);
        panelRegistro.add(estadoTxt);
        panelRegistro.add(labelAsistenciaABuscar);
        panelRegistro.add(AsistenciaABuscarTxt);

        // Panel para la tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Asistencias"));
        panelTabla.setBackground(Color.LIGHT_GRAY);

        modeloAsistencias = new DefaultTableModel(new String[]{"ID", "ID Curso", "ID Alumno", "Fecha", "Estado"}, 0);
        tablaAsistencias = new JTable(modeloAsistencias);
        JScrollPane scrollTabla = new JScrollPane(tablaAsistencias);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // Agregar paneles al frame
        add(panelRegistro, BorderLayout.NORTH);
        add(panelTabla, BorderLayout.CENTER);

        // Panel para botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonRegistrarAsistencia);
        panelBotones.add(botonBuscarAsistencia);
        panelBotones.add(botonEliminarAsistencia);
        add(panelBotones, BorderLayout.SOUTH);

    //Logica de los botones
        botonRegistrarAsistencia.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (idCursoTxt.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Ingrese el id del curso.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (idAlumnoTxt.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Ingrese el id del alumno.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (fechaTxt.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Ingrese la fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            asistencia.setIdCurso(idCursoTxt.getText());
            asistencia.setIdAlumno(idAlumnoTxt.getText());
            asistencia.setFecha(Date.valueOf(fechaTxt.getText()));
            asistencia.setEstado(String.valueOf(estadoTxt.getText()));


            try {
                AS.CrearAsistencia(asistencia);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Asistencia registrada con éxito");

            ListarAsistencias();
            limpiarDatosAsistencia();

        }});
    botonBuscarAsistencia.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (AsistenciaABuscarTxt.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Error al obtener la asistencia");
                return;
            }
            AsistenciaABuscarTxt.setVisible(true);
            int id=Integer.parseInt(AsistenciaABuscarTxt.getText());
            asistencia.setId(String.valueOf(id));
            labelAsistenciaABuscar.setVisible(true);

            AsistenciaABuscarTxt.setText(String.valueOf(asistencia.getId()));
            Asistencias AsistenciaEncontrada = null;
            try {
                AsistenciaEncontrada = AS.BuscarAsistenciaPorId(id);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (AsistenciaEncontrada !=null){
                idAlumnoTxt.setText(AsistenciaEncontrada.getIdAlumno());
                idCursoTxt.setText(AsistenciaEncontrada.getIdCurso());
                fechaTxt.setText(String.valueOf(AsistenciaEncontrada.getFecha()));
                estadoTxt.setText(AsistenciaEncontrada.getEstado());
                JOptionPane.showMessageDialog(null, "alumno encontrado correctamente");

            }
            else {
                JOptionPane.showMessageDialog(null, "alumno NO encontrado");
            }
        }
    }
        );
}
    private void ListarAsistencias()  {
        List<Asistencias> ListaAsistencias= null;
        try {
            ListaAsistencias = AS.ListarAsistencias();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        modeloAsistencias.setRowCount(0);
        modeloAsistencias= (DefaultTableModel) tablaAsistencias.getModel();
        Object[] Objeto =new Object[4];
        for (int i=0; i<ListaAsistencias.size();i++){
            Objeto[0]=ListaAsistencias.get(i).getId();
            Objeto[1]=ListaAsistencias.get(i).getIdAlumno();
            Objeto[2]=ListaAsistencias.get(i).getIdCurso();
            Objeto[3]=ListaAsistencias.get(i).getFecha();
            Objeto[4]=ListaAsistencias.get(i).getEstado();
            modeloAsistencias.addRow(Objeto);
        }
        tablaAsistencias.setModel(modeloAsistencias);
    }
    public void limpiarDatosAsistencia(){
        idAlumnoTxt.setText("");
        idCursoTxt.setText("");
        fechaTxt.setText("");
        estadoTxt.setText("");


        idAlumnoTxt.setEnabled(true);
        idCursoTxt.setEnabled(true);
        fechaTxt.setEnabled(true);
        estadoTxt.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroAsistencias().setVisible(true));
    }
}