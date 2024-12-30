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
    private JTable TablaAlumnos;

    public RegistroAlumnos(){
        setTitle("Registro Alumnos");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
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

        //botones
        JButton botonRegistroAlumnos=new JButton("Registrar alumno");
        botonRegistroAlumnos.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistroAlumnos.setBackground(new Color(51, 153, 255));
        botonRegistroAlumnos.setForeground(Color.WHITE);
        botonRegistroAlumnos.setFocusPainted(false);
        botonRegistroAlumnos.setBorderPainted(false);
        botonRegistroAlumnos.setPreferredSize(new Dimension(120,35));

        IdTxt=new JTextField(10);
        NombreTxt=new JTextField(10);
        ApellidoTxt=new JTextField(10);
        FechaNacimientoTxt=new JTextField(10);
        CorreoTxt=new JTextField(10);
        FechaInscripcionTxt=new JTextField(10);

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
        PanelRegistroAlumnos.add(botonRegistroAlumnos);


        // Panel para la tabla
        JPanel PanelTablaAlumnos = new JPanel(new BorderLayout());
        PanelTablaAlumnos.setBorder(BorderFactory.createTitledBorder("Lista de Alumnos"));
        PanelTablaAlumnos.setBackground(Color.LIGHT_GRAY);

        ModeloAlumno = new DefaultTableModel(new String[]{"Id", "Nombre", "Apellido", "Fecha_Nacimiento, Correo_electronico, Fecha_Inscripcion"}, 0);
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
                }
                else {
                    JOptionPane.showMessageDialog(null, "error al registrar el alumno");
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

        }


