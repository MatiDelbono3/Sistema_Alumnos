package org.example.View;

import org.example.DTO.Alumnos;
import org.example.DTO.Cursos;
import org.example.DTO.Usuarios;
import org.example.Services.AlumnoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RegistroAlumnos extends javax.swing.JFrame {

    AlumnoService AS=new AlumnoService();
    DefaultTableModel ModeloAlumno=new DefaultTableModel();
    Alumnos alumno=new Alumnos();


    private JTextField IdTxt;
    private JTextField NombreTxt;
    private JTextField ApellidoTxt;
    private JTextField FechaNacimientoTxt;
    private JTextField CorreoTxt;
    private JTextField NuevoCorreoTxt;
    private JTextField AlumnoTxt;
    private JTable TablaAlumnos;

    public RegistroAlumnos(){
        setTitle("Registro Alumnos");
        setSize(1100,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        ListarAlumnos();
    }
    private void initComponents(){
        setLayout(new BorderLayout());
        // Panel para registro de Alumnos
        JPanel PanelRegistroAlumnos=new JPanel();
        PanelRegistroAlumnos.setLayout(new GridLayout(10,2,10,10)); // 4 filas, 2 columnas
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

        JLabel labelIdAlumno=new JLabel("ID del alumno");
        JLabel IdAlumnoaeditar=new JLabel("");


        JLabel labelNuevocorreo=new JLabel("Nuevo Correo electrónico");
        labelNuevocorreo.setFont(new Font("Arial", Font.BOLD, 14));



        JLabel labelAlumnoAEliminar=new JLabel("Alumno a eliminar");

        labelAlumnoAEliminar.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel IdAlumnoaeliminar=new JLabel("");
        JLabel labelAlumnoABuscar=new JLabel("Alumno a buscar");
        labelAlumnoABuscar.setFont(new Font("Arial", Font.BOLD, 14));


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

        JButton botonEliminacionAlumnos=new JButton("eliminar Alumno");
        botonEliminacionAlumnos.setFont(new Font("Arial", Font.BOLD, 14));
        botonEliminacionAlumnos.setBackground(new Color(51, 153, 255));
        botonEliminacionAlumnos.setForeground(Color.WHITE);
        botonEliminacionAlumnos.setFocusPainted(false);
        botonEliminacionAlumnos.setBorderPainted(false);
        botonEliminacionAlumnos.setPreferredSize(new Dimension(120,35));

        JButton botonBusquedaAlumnos=new JButton("buscar Alumno");
        botonBusquedaAlumnos.setFont(new Font("Arial", Font.BOLD, 14));
        botonBusquedaAlumnos.setBackground(new Color(51, 153, 255));
        botonBusquedaAlumnos.setForeground(Color.WHITE);
        botonBusquedaAlumnos.setFocusPainted(false);
        botonBusquedaAlumnos.setBorderPainted(false);
        botonBusquedaAlumnos.setPreferredSize(new Dimension(120,35));

        IdTxt=new JTextField(10);
        NombreTxt=new JTextField(10);
        ApellidoTxt=new JTextField(10);
        FechaNacimientoTxt=new JTextField(10);
        CorreoTxt=new JTextField(10);
        NuevoCorreoTxt=new JTextField(10);
        AlumnoTxt=new JTextField(10);
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


        PanelRegistroAlumnos.add(labelIdAlumno); // "ID del alumno" (Edición)
        PanelRegistroAlumnos.add(AlumnoTxt); // Campo de texto para ID de Edición
        PanelRegistroAlumnos.add(labelNuevocorreo);
        PanelRegistroAlumnos.add(NuevoCorreoTxt);

        PanelRegistroAlumnos.add(botonEliminacionAlumnos);


        PanelRegistroAlumnos.add(botonEdicionAlumnos);
        PanelRegistroAlumnos.add(botonRegistroAlumnos);
        PanelRegistroAlumnos.add(botonBusquedaAlumnos);
        // Panel para la tabla
        JPanel PanelTablaAlumnos = new JPanel(new BorderLayout());
        PanelTablaAlumnos.setBorder(BorderFactory.createTitledBorder("Lista de Alumnos"));
        PanelTablaAlumnos.setBackground(Color.LIGHT_GRAY);

        ModeloAlumno = new DefaultTableModel(new String[]{"Id", "Nombre", "Apellido", "Fecha_Nacimiento", "Correo_electronico"}, 0);
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
        NuevoCorreoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        AlumnoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        // Lógica de los botones
        botonRegistroAlumnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Formato de fecha esperado
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false); // Validación estricta de fechas

                Date fechaNacimiento;
                if (NombreTxt.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Ingrese el nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (ApellidoTxt.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Ingrese el apellido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (CorreoTxt.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Ingrese el correo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    fechaNacimiento = dateFormat.parse(FechaNacimientoTxt.getText().trim());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Las fechas deben tener el formato YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir si las fechas no son válidas
                }
                java.sql.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());
                Alumnos alumno=new Alumnos();
                alumno.setNombre(NombreTxt.getText());
                alumno.setApellido(ApellidoTxt.getText());
                alumno.setFecha_Nacimiento(sqlFechaNacimiento);
                alumno.setCorreo_electronico(CorreoTxt.getText());
                if (AS.registrarAlumno(alumno)){
                JOptionPane.showMessageDialog(null, "Alumno registrado con éxito");
                }
                else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar el alumno, intente nuevamente");
                }
                ListarAlumnos();
                limpiarDatosAlumno();
            }
        });
        TablaAlumnos.getSelectionModel().addListSelectionListener(e ->{
            if (!e.getValueIsAdjusting()) {
                int FilaSeleccionada=TablaAlumnos.getSelectedRow();
                if (FilaSeleccionada != -1){
                    int IdAlumnoSeleccionado= (int) TablaAlumnos.getValueAt(FilaSeleccionada, 0);
                    AlumnoTxt.setText(String.valueOf(IdAlumnoSeleccionado));
                    if (IdAlumnoSeleccionado > 0){
                        IdAlumnoaeditar.setVisible(true);
                        labelIdAlumno.setVisible(true);
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
                labelIdAlumno.setVisible(true);
                IdAlumnoaeditar.setVisible(true);
                int fila=TablaAlumnos.getSelectedRow();
                if (fila == -1){
                    JOptionPane.showMessageDialog(null, "seleccione un alumno");
                    return;
                }
                String nuevoCorreo= NuevoCorreoTxt.getText();

                try{
                    if (nuevoCorreo.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Ingrese el nuevo correo");
                        return;
                    }
                } catch (HeadlessException ex) {
                    throw new RuntimeException(ex);
                }
                int idSeleccionado=Integer.parseInt(AlumnoTxt.getText());
                boolean exito=AS.editarCorreo(nuevoCorreo, idSeleccionado);
                System.out.println("Valor exacto de exito en la View: " + exito);
                if (exito) {
                    JOptionPane.showMessageDialog(null, "modificacion realizada con exito");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error al realizar modificacion");
                }
                ListarAlumnos();
                limpiarDatosAlumno();
            }
        });
        botonEliminacionAlumnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NombreTxt.setEnabled(false);
                ApellidoTxt.setEnabled(false);
                FechaNacimientoTxt.setEnabled(false);
                CorreoTxt.setEnabled(false);
                labelIdAlumno.setVisible(true);
                IdAlumnoaeliminar.setVisible(true);
                int fila=TablaAlumnos.getSelectedRow();
                if (fila == -1){
                    JOptionPane.showMessageDialog(null, "seleccione un alumno");
                    return;
                }
                String idTexto = AlumnoTxt.getText().trim();

                // 3. Validamos que no esté vacío antes del parseInt
                if (idTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el ID del alumno");
                    return;
                }


                int idSeleccionado = Integer.parseInt(idTexto);
                try {
                    if (AS.eliminarAlumno(idSeleccionado)){
                         JOptionPane.showMessageDialog(null, "alumno eliminado con exito");
                     }
                     else{
                         JOptionPane.showMessageDialog(null, "error al eliminar el alumno");
                     }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ListarAlumnos();
                limpiarDatosAlumno();

            }
        });

     botonBusquedaAlumnos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (AlumnoTxt.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Error al obtener el ID del alumno");
                return;
            }
            AlumnoTxt.setVisible(true);
            int id=Integer.parseInt(AlumnoTxt.getText());
            alumno.setId(id);
            labelAlumnoABuscar.setVisible(true);

            AlumnoTxt.setText(String.valueOf(alumno.getId()));
            Alumnos Alumnoencontrado = null;
            try {
                Alumnoencontrado = AS.ObtenerAlumnosPorId(id);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (Alumnoencontrado !=null){
                NombreTxt.setText(Alumnoencontrado.getNombre());
                ApellidoTxt.setText(Alumnoencontrado.getApellido());
                FechaNacimientoTxt.setText(String.valueOf(Alumnoencontrado.getFecha_Nacimiento()));
                CorreoTxt.setText(Alumnoencontrado.getCorreo_electronico());
                JOptionPane.showMessageDialog(null, "alumno encontrado correctamente");

            }
            else {
                JOptionPane.showMessageDialog(null, "alumno NO encontrado");
            }
        }
    }
        );
}
        private void ListarAlumnos(){
            List<Alumnos>ListaAlumnos=null;
            try {
                ListaAlumnos = AS.listarAlumnos();
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
                ModeloAlumno.addRow(Objeto);
            }
            TablaAlumnos.setModel(ModeloAlumno);
        }
        public void limpiarDatosAlumno(){
            NombreTxt.setText("");
            ApellidoTxt.setText("");
            FechaNacimientoTxt.setText("");
            CorreoTxt.setText("");


            NombreTxt.setEnabled(true);
            ApellidoTxt.setEnabled(true);
            FechaNacimientoTxt.setEnabled(true);
            CorreoTxt.setEnabled(true);

        }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new RegistroAlumnos().setVisible(true));
    }
        }

