package org.example.View;

import org.example.DTO.Alumnos;
import org.example.DTO.Cursos;
import org.example.DTO.Inscripciones;
import org.example.DTO.Usuarios;
import org.example.Services.AlumnoService;
import org.example.Services.CursoService;
import org.example.Services.InscripcionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RegistroInscripciones extends JFrame {

    Usuarios usuario=new Usuarios();
    CursoService CS=new CursoService();
    InscripcionService IS=new InscripcionService();
    AlumnoService AS=new AlumnoService();
    Inscripciones inscripcion=new Inscripciones();
    DefaultTableModel ModeloInscripcion=new DefaultTableModel();

    private JTextField IdTxt;
    private JComboBox<Alumnos> IdAlumnoTxt;
    private JComboBox<Cursos> IdCursoTxt;
    private JTextField FechaInscripcionTxt;
    private JTextField EstadoTxt;
    private JTextField InscripcionAEliminarTxt;
    private JTextField NuevoEstadoTxt;
    private JTextField IdCursoABuscarTxt;
    private JTextField IdAlumnoABuscarTxt;
    private JTable TablaInscripciones;
    private final List<Alumnos> alumnosDisponibles = new ArrayList<>();
    private final List<Cursos> cursosDisponibles = new ArrayList<>();
    private final Map<String, Alumnos> alumnosPorId = new HashMap<>();
    private final Map<String, Cursos> cursosPorId = new HashMap<>();




    public RegistroInscripciones() {
        setTitle("Registro Inscripciones");
        setSize(1100,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cargarAlumnosYCursos();
        initComponents();
        MenuNavigation.instalarVolverAlMenu(this);
        ListarInscripciones();
    }

    private void cargarAlumnosYCursos() {
        try {
            for (Alumnos alumno : AS.listarAlumnos()) {
                alumnosDisponibles.add(alumno);
                alumnosPorId.put(alumno.getId(), alumno);
            }
            for (Cursos curso : CS.listarCursos()) {
                cursosDisponibles.add(curso);
                cursosPorId.put(curso.getId(), curso);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudieron cargar alumnos y cursos: " + ex.getMessage(),
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents(){
        setLayout(new BorderLayout());
        // Panel para registro de curso
        JPanel PanelRegistro = new JPanel();
        PanelRegistro.setLayout(new GridLayout(0,2,10,10));
        PanelRegistro.setBackground(Color.LIGHT_GRAY);

        // labels


        JLabel labelIdAlumno = new JLabel("Alumno");
        labelIdAlumno.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelIdCurso = new JLabel("Curso");
        labelIdCurso.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelFechaInscripcion = new JLabel("Fecha Inscripcion (dd/MM/aaaa o aaaa-MM-dd)");
        labelFechaInscripcion.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelEstado = new JLabel("Estado");
        labelEstado.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelIdInscripcion = new JLabel("ID de la inscripción:");
        JLabel idInsccripcionSeleccionada = new JLabel(""); // Inscripción seleccionado para modificar
        labelIdInscripcion.setVisible(false);
        idInsccripcionSeleccionada.setVisible(false);

        JLabel labelnuevoEstado=new JLabel("Nuevo estado");
        labelnuevoEstado.setFont(new Font("Arial", Font.BOLD, 14));
        labelnuevoEstado.setVisible(false);
        JLabel labelInscripcionAEliminar=new JLabel("Inscripción a eliminar");
        labelInscripcionAEliminar.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelCursoABuscar=new JLabel("Id  curso a buscar");
        labelCursoABuscar.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelAlumnoABuscar=new JLabel("Id  alumno a buscar");
        labelAlumnoABuscar.setFont(new Font("Arial", Font.BOLD, 14));
        JComboBox<String> comboBuscar = new JComboBox<>();
        comboBuscar.addItem("Inscripciones por Alumno");
        comboBuscar.addItem("Inscripciones por Curso");
        comboBuscar.addItem("Cursos por Alumno");
        comboBuscar.addItem("Alumnos por curso");



        IdTxt=new JTextField(10);
        IdAlumnoTxt = new JComboBox<>(alumnosDisponibles.toArray(new Alumnos[0]));
        IdCursoTxt = new JComboBox<>(cursosDisponibles.toArray(new Cursos[0]));
        configurarRenderizadores();
        FechaInscripcionTxt=new JTextField(10);
        EstadoTxt = new JTextField(10);
        NuevoEstadoTxt=new JTextField(10);
        InscripcionAEliminarTxt=new JTextField(10);
        IdCursoABuscarTxt=new JTextField(10);
        IdAlumnoABuscarTxt=new JTextField(10);
        NuevoEstadoTxt.setVisible(false);
        IdCursoABuscarTxt.setVisible(false);
        labelCursoABuscar.setVisible(false);
        IdAlumnoABuscarTxt.setVisible(true);
        labelAlumnoABuscar.setVisible(true);



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
        FechaInscripcionTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        EstadoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NuevoEstadoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        InscripcionAEliminarTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        IdCursoABuscarTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        IdAlumnoABuscarTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        comboBuscar.addActionListener(e -> {
            String opcion = String.valueOf(comboBuscar.getSelectedItem());
            boolean buscarPorAlumno = Objects.equals(opcion, "Inscripciones por Alumno")
                    || Objects.equals(opcion, "Cursos por Alumno");
            labelAlumnoABuscar.setVisible(buscarPorAlumno);
            IdAlumnoABuscarTxt.setVisible(buscarPorAlumno);
            labelCursoABuscar.setVisible(!buscarPorAlumno);
            IdCursoABuscarTxt.setVisible(!buscarPorAlumno);
            PanelRegistro.revalidate();
            PanelRegistro.repaint();
        });
        botonRegistroInscripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alumnos alumnoSeleccionado = (Alumnos) IdAlumnoTxt.getSelectedItem();
                if (alumnoSeleccionado == null){
                    JOptionPane.showMessageDialog(null, "Seleccione un alumno.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Cursos cursoSeleccionado = (Cursos) IdCursoTxt.getSelectedItem();
                if (cursoSeleccionado == null){
                    JOptionPane.showMessageDialog(null, "Seleccione un curso.", "Error", JOptionPane.ERROR_MESSAGE);
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
                LocalDate fechaInscripcion;
                try {
                    fechaInscripcion = parseFechaInscripcion(FechaInscripcionTxt.getText().trim());
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Ingrese una fecha válida en formato dd/MM/aaaa o aaaa-MM-dd.",
                            "Fecha inválida",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    inscripcion.setId_Alumno(alumnoSeleccionado.getId());
                    inscripcion.setId_Curso(cursoSeleccionado.getId());
                    inscripcion.setFechaInscripcion(fechaInscripcion);
                    inscripcion.setEstado(EstadoTxt.getText());
                    IS.insertarInscripciones(inscripcion);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            RegistroInscripciones.this,
                            "Ingrese un ID numérico válido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                            RegistroInscripciones.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                            RegistroInscripciones.this,
                            "No se pudo registrar la inscripción: " + ex.getMessage(),
                            "Error de base de datos",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(
                            RegistroInscripciones.this,
                            "No se pudo registrar la inscripción: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(
                        RegistroInscripciones.this,
                        "Inscripción registrada con éxito");

                ListarInscripciones();
                limpiarDatosInscripcion();

            }});
        TablaInscripciones.getSelectionModel().addListSelectionListener(e ->{
            if (!e.getValueIsAdjusting()) {
                int FilaSeleccionada=TablaInscripciones.getSelectedRow();
                if (FilaSeleccionada != -1 ){
                    int IdInscripcionSeleccionada = Integer.parseInt(
                            String.valueOf(TablaInscripciones.getValueAt(FilaSeleccionada, 0)));
                    idInsccripcionSeleccionada.setText(String.valueOf(IdInscripcionSeleccionada));
                    InscripcionAEliminarTxt.setText(String.valueOf(IdInscripcionSeleccionada));
                    if (IdInscripcionSeleccionada > 0) {
                        inscripcion.setId(IdInscripcionSeleccionada);
                        idInsccripcionSeleccionada.setVisible(true);
                        labelIdInscripcion.setVisible(true);
                        labelnuevoEstado.setVisible(true);
                        NuevoEstadoTxt.setVisible(true);
                        PanelRegistro.revalidate();
                        PanelRegistro.repaint();

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
            try {
                String opcion = String.valueOf(comboBuscar.getSelectedItem());
                if (Objects.equals(opcion, "Inscripciones por Curso")) {
                    cargarTabla(IS.BuscarInscripcionesPorCurso(IdCursoABuscarTxt.getText()));
                } else if (Objects.equals(opcion, "Inscripciones por Alumno")) {
                    cargarTabla(IS.BuscarInscripcionesPorAlumno(IdAlumnoABuscarTxt.getText()));
                } else if (Objects.equals(opcion, "Alumnos por curso")) {
                    cargarTablaAlumnos(IS.BuscarAlumnosPorCurso(IdCursoABuscarTxt.getText()));
                } else {
                    cargarTablaCursos(IS.BuscarCursosPorAlumno(IdAlumnoABuscarTxt.getText()));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
      });
    }

    private void configurarRenderizadores() {
        IdAlumnoTxt.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Alumnos) {
                    Alumnos alumno = (Alumnos) value;
                    setText(formatoAlumno(alumno));
                }
                return this;
            }
        });
        IdCursoTxt.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Cursos) {
                    Cursos curso = (Cursos) value;
                    setText(formatoCurso(curso));
                }
                return this;
            }
        });
    }

    private String formatoAlumno(Alumnos alumno) {
        return alumno.getNombre() + " " + alumno.getApellido() + " (ID: " + alumno.getId() + ")";
    }

    private String formatoCurso(Cursos curso) {
        return curso.getNombre_curso() + " - " + curso.getNivel() + " (ID: " + curso.getId() + ")";
    }

    static LocalDate parseFechaInscripcion(String fecha) {
            try {
                return LocalDate.parse(fecha);
            } catch (DateTimeParseException ex) {
                DateTimeFormatter formatoDiaMesAnio = DateTimeFormatter.ofPattern("d/M/uuuu")
                        .withResolverStyle(ResolverStyle.STRICT);
                return LocalDate.parse(fecha, formatoDiaMesAnio);
            }
        }

        private void ListarInscripciones()  {
            List<Inscripciones>ListaInscripciones= null;
            try {
                ListaInscripciones = IS.ListarInscripciones();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            cargarTabla(ListaInscripciones);
        }
private void cargarTabla(List<Inscripciones> lista) {
    ModeloInscripcion.setColumnIdentifiers(
            new String[]{"ID inscripción", "Alumno (ID)", "Curso (ID)", "Fecha inscripción", "Estado"});
    ModeloInscripcion.setRowCount(0);

    for (Inscripciones i : lista) {
        ModeloInscripcion.addRow(new Object[]{
                i.getId(),
                nombreAlumnoConId(i.getId_Alumno()),
                nombreCursoConId(i.getId_Curso()),
                i.getFechaInscripcion(),
                i.getEstado()
        });
    }
}

    private String nombreAlumnoConId(String idAlumno) {
        Alumnos alumno = alumnosPorId.get(idAlumno);
        return alumno == null ? "ID: " + idAlumno : formatoAlumno(alumno);
    }

    private String nombreCursoConId(String idCurso) {
        Cursos curso = cursosPorId.get(idCurso);
        return curso == null ? "ID: " + idCurso : formatoCurso(curso);
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
            IdAlumnoTxt.setSelectedIndex(IdAlumnoTxt.getItemCount() > 0 ? 0 : -1);
            IdCursoTxt.setSelectedIndex(IdCursoTxt.getItemCount() > 0 ? 0 : -1);
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
