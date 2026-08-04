package org.example.View;

import org.example.DTO.Alumnos;
import org.example.DTO.Cursos;
import org.example.DTO.Inscripciones;
import org.example.DTO.Usuarios;
import org.example.Repositories.CursoDAO;
import org.example.Services.CursoService;
import org.example.Services.InscripcionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class RegistroInscripciones extends JFrame {

    Usuarios usuario=new Usuarios();
    CursoService CS=new CursoService();
    InscripcionService IS=new InscripcionService();
    CursoService CuS=new CursoService();
    Inscripciones inscripcion=new Inscripciones();
    DefaultTableModel ModeloInscripcion=new DefaultTableModel();

    private JTextField IdTxt;
    private JTextField IdAlumnoTxt;
    private JTextField IdCursoTxt;
    private JTextField FechaInscripcionTxt;
    private JTextField EstadoTxt;
    private JTextField InscripcionAEliminarTxt;
    private JTextField NuevoEstadoTxt;
    private JTextField IdCursoABuscarTxt;
    private JTextField IdAlumnoABuscarTxt;
    private JTable TablaInscripciones;




    public RegistroInscripciones() {
        setTitle("Registro Inscripciones");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        ListarInscripciones();
    }
    private void initComponents(){
        setLayout(new BorderLayout());
        // Panel para registro de curso
        JPanel PanelRegistro = new JPanel();
        PanelRegistro.setLayout(new GridLayout(4,2,10,10)); // 4 filas, 2 columnas
        PanelRegistro.setBackground(Color.LIGHT_GRAY);

        // labels


        JLabel labelIdAlumno = new JLabel("id Alumno");
        labelIdAlumno.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelIdCurso = new JLabel("id curso");
        labelIdCurso.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelFechaInscripcion = new JLabel("Fecha Inscripcion");
        labelFechaInscripcion.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelEstado = new JLabel("Estado");
        labelEstado.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelIdInscripcion = new JLabel("ID del Curso: ");
        JLabel idInsccripcionSeleccionada = new JLabel(""); // Inscripción seleccionado para modificar

        JLabel labelnuevoEstado=new JLabel("Nuevo estado");
        labelnuevoEstado.setFont(new Font("Arial", Font.BOLD, 14));
        labelnuevoEstado.setVisible(false);
        JLabel labelInscripcionAEliminar=new JLabel("Inscripción a eliminar");
        labelInscripcionAEliminar.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelCursoABuscar=new JLabel("Id  curso a buscar");
        labelCursoABuscar.setFont(new Font("Arial", Font.BOLD, 14));
        labelCursoABuscar.setVisible(false);

        JLabel labelAlumnoABuscar=new JLabel("Id  alumno a buscar");
        labelAlumnoABuscar.setFont(new Font("Arial", Font.BOLD, 14));
        labelAlumnoABuscar.setVisible(false);
        JComboBox<String> comboBuscar = new JComboBox<>();
        comboBuscar.addItem("Inscripciones por Alumno");
        comboBuscar.addItem("Inscripciones por Curso");
        comboBuscar.addItem("Cursos por Alumno");
        comboBuscar.addItem("Alumnos por curso");



        IdTxt=new JTextField(10);
        IdAlumnoTxt = new JTextField(10);
        IdCursoTxt = new JTextField(10);
        FechaInscripcionTxt=new JTextField(10);
        EstadoTxt = new JTextField(10);
        NuevoEstadoTxt=new JTextField(10);
        InscripcionAEliminarTxt=new JTextField(10);
        IdCursoABuscarTxt=new JTextField(10);
        IdAlumnoABuscarTxt=new JTextField(10);
        NuevoEstadoTxt.setVisible(false);
        IdCursoABuscarTxt.setVisible(false);



        // botones
        JButton botonRegistroInscripcion=new JButton("Registrar Inscripcion");
        botonRegistroInscripcion.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistroInscripcion.setBackground(new Color(51, 153, 255));
        botonRegistroInscripcion.setForeground(Color.WHITE);
        botonRegistroInscripcion.setFocusPainted(false);
        botonRegistroInscripcion.setBorderPainted(false);
        botonRegistroInscripcion.setPreferredSize(new Dimension(120,35));

        JButton botonModificarInscripcion=new JButton("Modificar Inscripcion");
        botonModificarInscripcion.setFont(new Font("Arial", Font.BOLD, 14));
        botonModificarInscripcion.setBackground(new Color(51, 153, 255));
        botonModificarInscripcion.setForeground(Color.WHITE);
        botonModificarInscripcion.setFocusPainted(false);
        botonModificarInscripcion.setBorderPainted(false);
        botonModificarInscripcion.setPreferredSize(new Dimension(120,35));

        JButton botonEliminarInscripcion=new JButton("Eliminar Inscripcion");
        botonEliminarInscripcion.setFont(new Font("Arial", Font.BOLD, 14));
        botonEliminarInscripcion.setBackground(new Color(51, 153, 255));
        botonEliminarInscripcion.setForeground(Color.WHITE);
        botonEliminarInscripcion.setFocusPainted(false);
        botonEliminarInscripcion.setBorderPainted(false);
        botonEliminarInscripcion.setPreferredSize(new Dimension(120,35));

        JButton botonBuscarInscripcion=new JButton("Buscar Inscripcion");
        botonBuscarInscripcion.setFont(new Font("Arial", Font.BOLD, 14));
        botonBuscarInscripcion.setBackground(new Color(51, 153, 255));
        botonBuscarInscripcion.setForeground(Color.WHITE);
        botonBuscarInscripcion.setFocusPainted(false);
        botonBuscarInscripcion.setBorderPainted(false);
        botonBuscarInscripcion.setPreferredSize(new Dimension(120,35));


        PanelRegistro.add(labelIdAlumno);
        PanelRegistro.add(IdAlumnoTxt);
        PanelRegistro.add(labelIdCurso);
        PanelRegistro.add(IdCursoTxt);
        PanelRegistro.add(labelFechaInscripcion);
        PanelRegistro.add(FechaInscripcionTxt);
        PanelRegistro.add(labelEstado);
        PanelRegistro.add(EstadoTxt);
        PanelRegistro.add(labelnuevoEstado);
        PanelRegistro.add(NuevoEstadoTxt);
        PanelRegistro.add(new JLabel("Buscar por"));
        PanelRegistro.add(comboBuscar);
        PanelRegistro.add(labelCursoABuscar);
        PanelRegistro.add(IdCursoABuscarTxt);
        PanelRegistro.add(labelAlumnoABuscar);
        PanelRegistro.add(IdAlumnoABuscarTxt);
        PanelRegistro.add(botonRegistroInscripcion);
        PanelRegistro.add(botonModificarInscripcion);
        PanelRegistro.add(botonEliminarInscripcion);
        PanelRegistro.add(botonBuscarInscripcion);

        // Panel para la tabla
        JPanel PanelTabla = new JPanel(new BorderLayout());
        PanelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Inscripciones"));
        PanelTabla.setBackground(Color.LIGHT_GRAY);


        ModeloInscripcion = new DefaultTableModel(new String[]{"id", "id_Alumno", "id_curso", "fecha_inscripcion", "estado"}, 0);
        TablaInscripciones= new JTable(ModeloInscripcion);
        JScrollPane scrollTabla = new JScrollPane(TablaInscripciones);
        PanelTabla.add(scrollTabla, BorderLayout.CENTER);

        add(PanelRegistro, BorderLayout.NORTH);
        add(PanelTabla, BorderLayout.CENTER);

        // Estilos de texto
        IdTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        IdAlumnoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        IdCursoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        FechaInscripcionTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        EstadoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NuevoEstadoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        InscripcionAEliminarTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        botonRegistroInscripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (IdAlumnoTxt.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Ingrese el id del alumno.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (IdCursoTxt.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Ingrese el id del curso.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (FechaInscripcionTxt.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Ingrese la fecha de inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (EstadoTxt.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Ingrese el estado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                inscripcion.setId_Alumno(IdAlumnoTxt.getText());
                inscripcion.setId_Curso(IdCursoTxt.getText());
                inscripcion.setFechaInscripcion(LocalDate.parse(FechaInscripcionTxt.getText()));
                inscripcion.setEstado(EstadoTxt.getText());





                IS.insertarInscripciones(inscripcion);

                JOptionPane.showMessageDialog(
                        null,
                        "Inscripcion registrada con éxito");

                ListarInscripciones();
                limpiarDatosInscripcion();

            }});
        TablaInscripciones.getSelectionModel().addListSelectionListener(e ->{
            if (!e.getValueIsAdjusting()) {
                int FilaSeleccionada=TablaInscripciones.getSelectedRow();
                if (FilaSeleccionada != -1 ){
                    int IdInscripcionSeleccionada= (int) TablaInscripciones.getValueAt(FilaSeleccionada, 0);
                    idInsccripcionSeleccionada.setText(String.valueOf(IdInscripcionSeleccionada));
                    InscripcionAEliminarTxt.setText(String.valueOf(IdInscripcionSeleccionada));
                    if (IdInscripcionSeleccionada > 0) {
                        inscripcion.setId(IdInscripcionSeleccionada);
                        idInsccripcionSeleccionada.setVisible(true);
                        labelIdInscripcion.setVisible(true);
                        labelnuevoEstado.setVisible(true);
                        NuevoEstadoTxt.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Error al obtener el ID del curso");
                    }
                }
            }
        });
        botonModificarInscripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IdAlumnoTxt.setEnabled(false);
                IdCursoTxt.setEnabled(false);
                FechaInscripcionTxt.setEnabled(false);
                labelnuevoEstado.setVisible(true);
                NuevoEstadoTxt.setVisible(true);
                int fila=TablaInscripciones.getSelectedRow();
                if (fila==-1 ){
                    JOptionPane.showMessageDialog(null, "Seleccione una inscripcion");
                    return;
                }
                String nuevoEstado= NuevoEstadoTxt.getText();
                try{
                    if (nuevoEstado.equalsIgnoreCase("")){
                        JOptionPane.showMessageDialog(null, "El nuevo estado debe ingresarse");
                        return;
                    }
                } catch (HeadlessException ex) {
                    throw new RuntimeException(ex);
                }
                inscripcion.setEstado((String.valueOf(nuevoEstado)));
                JOptionPane.showMessageDialog(null, "inscripcion modificada con éxito");
                try {
                    IS.editarestado(inscripcion);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ListarInscripciones();
                limpiarDatosInscripcion();

                labelIdInscripcion.setVisible(false);
                idInsccripcionSeleccionada.setVisible(false);
            }
        });

      botonBuscarInscripcion.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idCurso= Integer.parseInt(IdCursoABuscarTxt.getText());
            if (idCurso<=0){
                JOptionPane.showMessageDialog(null, "Error al obtener el ID del curso");
                return;
            }

            List<Inscripciones> lista=null;
            List<Alumnos>alumnos=null;
            List<Cursos> cursos=null;
            if (Objects.equals(comboBuscar.getSelectedItem(), "Inscripciones por Curso")) {
                lista=IS.BuscarInscripcionesPorCurso(idCurso);
            } else if (Objects.equals(comboBuscar.getSelectedItem(), "Inscripciones por Alumno")) {
                int idAlumno=Integer.parseInt(IdAlumnoABuscarTxt.getText());
                lista=IS.BuscarInscripcionesPorAlumno(idAlumno);
                cargarTabla(lista);
            }
            else if (Objects.equals(comboBuscar.getSelectedItem(), "Alumnos por Curso"))
            {
                alumnos=IS.BuscarAlumnosPorCurso(idCurso);
                cargarTablaAlumnos(alumnos);
            }
            else  {
                try {
                    int idAlumno=Integer.parseInt(IdAlumnoABuscarTxt.getText());
                    cursos=IS.BuscarCursosPorAlumno(idAlumno);
                    cargarTablaCursos(cursos);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            assert lista != null;
            cargarTabla(lista);
        }
      });
    }

        private void ListarInscripciones()  {
            List<Inscripciones>ListaInscripciones= null;
            try {
                ListaInscripciones = IS.ListarInscripciones();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ModeloInscripcion.setRowCount(0);
            ModeloInscripcion= (DefaultTableModel) TablaInscripciones.getModel();
            Object[] Objeto =new Object[5];
            for (int i=0; i<ListaInscripciones.size();i++){
                Objeto[0]=ListaInscripciones.get(i).getId();
                Objeto[1]=ListaInscripciones.get(i).getId_Alumno();
                Objeto[2]=ListaInscripciones.get(i).getId_Curso();
                Objeto[3]=ListaInscripciones.get(i).getFechaInscripcion();
                Objeto[4]=ListaInscripciones.get(i).getEstado();
                ModeloInscripcion.addRow(Objeto);
            }
            TablaInscripciones.setModel(ModeloInscripcion);
        }
private void cargarTabla(List<Inscripciones> lista) {
    ModeloInscripcion.setRowCount(0);

    for (Inscripciones i : lista) {
        ModeloInscripcion.addRow(new Object[]{
                i.getId(),
                i.getId_Alumno(),
                i.getId_Curso(),
                i.getFechaInscripcion(),
                i.getEstado()
        });
    }
}
    private void cargarTablaAlumnos(List<Alumnos> alumnos) {
        ModeloInscripcion.setColumnIdentifiers(
                new String[]{"ID", "Nombre", "Apellido", "Correo"}
        );

        ModeloInscripcion.setRowCount(0);

        for (Alumnos a : alumnos) {
            ModeloInscripcion.addRow(new Object[]{
                    a.getId(),
                    a.getNombre(),
                    a.getApellido(),
                    a.getCorreo_electronico()
            });
        }
    }
    private void cargarTablaCursos(List<Cursos> cursos) {
        ModeloInscripcion.setColumnIdentifiers(
                new String[]{"ID", "Curso", "Nivel", "CupoMaximo"}
        );

        ModeloInscripcion.setRowCount(0);

        for (Cursos c : cursos) {
            ModeloInscripcion.addRow(new Object[]{
                    c.getId(),
                    c.getNombre_curso(),
                    c.getNivel(),
                    c.getCupo_Maximo()
            });
        }
    }

        private void limpiarDatosInscripcion(){
            IdAlumnoTxt.setText("");
            IdCursoTxt.setText("");
            FechaInscripcionTxt.setText("");
            EstadoTxt.setText("");

            IdAlumnoTxt.setEnabled(true);
            IdCursoTxt.setEnabled(true);
            FechaInscripcionTxt.setEnabled(true);
            EstadoTxt.setEnabled(true);

            NuevoEstadoTxt.setVisible(false);
        }




        public static void main(String[] args) {
            SwingUtilities.invokeLater(() ->
                    new RegistroInscripciones().setVisible(true));
        }
      };




