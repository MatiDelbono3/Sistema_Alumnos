package org.example.View;

import org.example.Connections.AlumnosConnection;
import org.example.Connections.UsersConnection;
import org.example.DTO.Alumnos;
import org.example.DTO.Cursos;
import org.example.DTO.Usuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class RegistroAlumnos extends javax.swing.JFrame {
    UsersConnection ConexionUsuario=new UsersConnection();
    Usuarios usuario=new Usuarios();
    AlumnosConnection AC=new AlumnosConnection();
    Alumnos alumno=new Alumnos();
    DefaultTableModel ModeloAlumno=new DefaultTableModel();

    private JTextField IdTxt;
    private JTextField NombreTxt;
    private JTextField ApellidoTxt;
    private JTextField FechaNacimientoTxt;
    private JTextField CorreoTxt;
    private JTextField FechaInscripcionTxt;
    private JTextField NuevoCorreoTxt;
    private JTable TablaAlumnos;

    public RegistroAlumnos(){
        setTitle("Registro Alumnos");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        ListarAlumnos();
    }
    private void initComponents(){
        setLayout(new BorderLayout());
        // Panel para registro de Alumnos
        JPanel PanelRegistroAlumnos=new JPanel();
        PanelRegistroAlumnos.setLayout(new GridLayout(4,2,10,10)); // 4 filas, 2 columnas
        PanelRegistroAlumnos.setBackground(Color.LIGHT_GRAY);

        // labels
        JLabel labelNombre=new JLabel("Nombre");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelApellido=new JLabel("Apellido");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelNacimiento=new JLabel("Fecha de Nacimiento");
        labelNacimiento.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelCorreo=new JLabel("Correo Electronico");
        labelCorreo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelFechaInscripcion=new JLabel("Fecha de Inscripcion");
        labelCorreo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelIdAlumno=new JLabel("ID del alumno");
        JLabel IdAlumnoseleccionado=new JLabel("");

        JLabel labelNuevocorreo=new JLabel("Nuevo Correo electrónico");
        labelNuevocorreo.setFont(new Font("Arial", Font.BOLD, 14));



        //botones
        JButton botonRegistroAlumnos=new JButton("Registrar alumno");
        botonRegistroAlumnos.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistroAlumnos.setBackground(new Color(51, 153, 255));
        botonRegistroAlumnos.setForeground(Color.WHITE);
        botonRegistroAlumnos.setFocusPainted(false);
        botonRegistroAlumnos.setBorderPainted(false);
        botonRegistroAlumnos.setPreferredSize(new Dimension(120,35));

        JButton botonEdicionAlumnos=new JButton("editar");
        botonEdicionAlumnos.setFont(new Font("Arial", Font.BOLD, 14));
        botonEdicionAlumnos.setBackground(new Color(51, 153, 255));
        botonEdicionAlumnos.setForeground(Color.WHITE);
        botonEdicionAlumnos.setFocusPainted(false);
        botonEdicionAlumnos.setBorderPainted(false);
        botonEdicionAlumnos.setPreferredSize(new Dimension(120,35));

        IdTxt=new JTextField(10);
        NombreTxt=new JTextField(10);
        ApellidoTxt=new JTextField(10);
        FechaNacimientoTxt=new JTextField(10);
        CorreoTxt=new JTextField(10);
        FechaInscripcionTxt=new JTextField(10);
        NuevoCorreoTxt=new JTextField(10);

        labelNuevocorreo.setVisible(false);
        NuevoCorreoTxt.setVisible(false);

        //Agregar elementos al panel
        PanelRegistroAlumnos.add(labelNombre);
        PanelRegistroAlumnos.add(NombreTxt);
        PanelRegistroAlumnos.add(labelApellido);
        PanelRegistroAlumnos.add(ApellidoTxt);
        PanelRegistroAlumnos.add(labelNacimiento);
        PanelRegistroAlumnos.add(FechaNacimientoTxt);
        PanelRegistroAlumnos.add(labelCorreo);
        PanelRegistroAlumnos.add(CorreoTxt);
        PanelRegistroAlumnos.add(labelFechaInscripcion);
        PanelRegistroAlumnos.add(FechaInscripcionTxt);
        PanelRegistroAlumnos.add(labelIdAlumno);
        PanelRegistroAlumnos.add(labelNuevocorreo);
        PanelRegistroAlumnos.add(NuevoCorreoTxt);
        PanelRegistroAlumnos.add(botonRegistroAlumnos);
        PanelRegistroAlumnos.add(botonEdicionAlumnos);
        // Panel para la tabla
        JPanel PanelTablaAlumnos = new JPanel(new BorderLayout());
        PanelTablaAlumnos.setBorder(BorderFactory.createTitledBorder("Lista de Alumnos"));
        PanelTablaAlumnos.setBackground(Color.LIGHT_GRAY);

        ModeloAlumno = new DefaultTableModel(new String[]{"Id", "Nombre", "Apellido", "Fecha_Nacimiento", "Correo_electronico", "Fecha_Inscripcion"}, 0);
        TablaAlumnos = new JTable(ModeloAlumno);
        JScrollPane scrollTabla = new JScrollPane(TablaAlumnos);
        PanelTablaAlumnos.add(scrollTabla, BorderLayout.CENTER);

        add(PanelRegistroAlumnos, BorderLayout.NORTH);
        add(PanelTablaAlumnos, BorderLayout.CENTER);

        // Estilos
        IdTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NombreTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        ApellidoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        FechaNacimientoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        CorreoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        FechaInscripcionTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NuevoCorreoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Lógica de los botones
        botonRegistroAlumnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Formato de fecha esperado
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false); // Validación estricta de fechas

                Date fechaNacimiento;
                Date fechaInscripcion;
                try {
                    fechaNacimiento = dateFormat.parse(FechaNacimientoTxt.getText().trim());
                    fechaInscripcion = dateFormat.parse(FechaInscripcionTxt.getText().trim());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Las fechas deben tener el formato YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir si las fechas no son válidas
                }

                alumno.setNombre(NombreTxt.getText());
                alumno.setApellido(ApellidoTxt.getText());
                alumno.setFecha_Nacimiento(fechaNacimiento);
                alumno.setCorreo_electronico(CorreoTxt.getText());
                alumno.setFecha_Inscripcion(fechaInscripcion);

                if (AC.insertarAlumnos(alumno) > 0){
                    JOptionPane.showMessageDialog(null, "Alumno registrado con exito");
                    ListarAlumnos();
                    limpiarDatosAlumno();
                }
                else {
                    JOptionPane.showMessageDialog(null, "error al registrar el alumno");
                }
            }
        });
        TablaAlumnos.getSelectionModel().addListSelectionListener(e ->{
            if (!e.getValueIsAdjusting()) {
                int FilaSeleccionada=TablaAlumnos.getSelectedRow();
                if (FilaSeleccionada != -1){
                    int IdAlumnoSeleccionado= (int) TablaAlumnos.getValueAt(FilaSeleccionada, 0);
                    IdAlumnoseleccionado.setText(String.valueOf(IdAlumnoSeleccionado));
                    if (IdAlumnoSeleccionado > 0){
                        alumno.setId(IdAlumnoSeleccionado);
                        labelNuevocorreo.setVisible(true);
                        NuevoCorreoTxt.setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "error al obtener el ID del alumno");
                    }
                }
            }
        });
        botonEdicionAlumnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NombreTxt.setEnabled(false);
                ApellidoTxt.setEnabled(false);
                FechaNacimientoTxt.setEnabled(false);
                CorreoTxt.setEnabled(false);
                FechaInscripcionTxt.setEnabled(false);
                int fila=TablaAlumnos.getSelectedRow();
                if (fila == -1){
                    JOptionPane.showMessageDialog(null, "seleccione un alumno");
                    return;
                }
                String nuevoCorreo= NuevoCorreoTxt.getText();
                try{
                    if (nuevoCorreo.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Error al obtener el ID del alumno");

                    }
                } catch (HeadlessException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                alumno.setCorreo_electronico(nuevoCorreo);
                if (AC.editarAlumno(alumno)){
                JOptionPane.showMessageDialog(null, "modificacion realizada con exito");
                ListarAlumnos();
            }
        } catch (SQLException exception){
                    throw new RuntimeException(exception);
                }

            }
        });
    }
        private void ListarAlumnos(){
            List<Alumnos>ListaAlumnos=null;
            try {
                ListaAlumnos = AC.ListarAlumnos();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ModeloAlumno.setRowCount(0);
            ModeloAlumno= (DefaultTableModel) TablaAlumnos.getModel();
            Object[] Objeto =new Object[6];
            for (int i=0; i<ListaAlumnos.size();i++){
                Objeto[0]=ListaAlumnos.get(i).getId();
                Objeto[1]=ListaAlumnos.get(i).getNombre();
                Objeto[2]=ListaAlumnos.get(i).getApellido();
                Objeto[3]=ListaAlumnos.get(i).getFecha_Nacimiento();
                Objeto[4]=ListaAlumnos.get(i).getCorreo_electronico();
                Objeto[5]=ListaAlumnos.get(i).getFecha_Inscripcion();
                ModeloAlumno.addRow(Objeto);
            }
            TablaAlumnos.setModel(ModeloAlumno);
        }
        public void limpiarDatosAlumno(){
            NombreTxt.setText("");
            ApellidoTxt.setText("");
            FechaNacimientoTxt.setText("");
            CorreoTxt.setText("");
            FechaInscripcionTxt.setText("");

            NombreTxt.setVisible(true);
            ApellidoTxt.setVisible(true);
            FechaNacimientoTxt.setVisible(true);
            CorreoTxt.setVisible(true);
            FechaInscripcionTxt.setVisible(true);
        }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new MenuPrincipal().setVisible(true));
    }
        }


