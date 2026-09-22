package org.example.View;

import org.example.DTO.Alumnos;
import org.example.DTO.Cursos;
import org.example.DTO.Docentes;
import org.example.Services.DocenteService;

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

public class RegistroDocentes extends javax.swing.JFrame {

    DocenteService DS = new DocenteService();
    DefaultTableModel ModeloDocente = new DefaultTableModel();
    Docentes docente = new Docentes();

    private JTextField IdTxt;
    private JTextField NombreTxt;
    private JTextField ApellidoTxt;
    private JTextField FechaNacimientoTxt;
    private JTextField FechaContratacionTxt;
    private JTextField CorreoTxt;
    private JCheckBox ActivoCheckBox;
    private JTextField NuevoCorreoTxt;
    private JTextField DocenteTxt;
    private JTable TablaDocentes;

    public RegistroDocentes() {
        setTitle("Registro Docentes");
        setSize(1100, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        // Panel para registro de Docentes
        JPanel PanelRegistroDocentes = new JPanel();
        PanelRegistroDocentes.setLayout(new GridLayout(12, 2, 10, 10));
        PanelRegistroDocentes.setBackground(Color.LIGHT_GRAY);

        // Labels
        JLabel labelNombre = new JLabel("Nombre");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelApellido = new JLabel("Apellido");
        labelApellido.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelNacimiento = new JLabel("Fecha de Nacimiento");
        labelNacimiento.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelContratacion = new JLabel("Fecha de Contratación");
        labelContratacion.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelCorreo = new JLabel("Correo Electrónico");
        labelCorreo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelActivo = new JLabel("Activo");
        labelActivo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelIdDocente = new JLabel("ID del docente");
        JLabel IdDocenteAEditar = new JLabel("");

        JLabel labelNuevoCorreo = new JLabel("Nuevo Correo Electrónico");
        labelNuevoCorreo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelDocenteAEliminar = new JLabel("Docente a eliminar");
        labelDocenteAEliminar.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel IdDocenteAEliminar = new JLabel("");

        JLabel labelDocenteABuscar = new JLabel("Docente a buscar");
        labelDocenteABuscar.setFont(new Font("Arial", Font.BOLD, 14));

        // Botones
        JButton botonRegistroDocentes = new JButton("Registrar docente");
        botonRegistroDocentes.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistroDocentes.setBackground(new Color(51, 153, 255));
        botonRegistroDocentes.setForeground(Color.WHITE);
        botonRegistroDocentes.setFocusPainted(false);
        botonRegistroDocentes.setBorderPainted(false);
        botonRegistroDocentes.setPreferredSize(new Dimension(120, 35));

        JButton botonEdicionDocentes = new JButton("Editar");
        botonEdicionDocentes.setFont(new Font("Arial", Font.BOLD, 14));
        botonEdicionDocentes.setBackground(new Color(51, 153, 255));
        botonEdicionDocentes.setForeground(Color.WHITE);
        botonEdicionDocentes.setFocusPainted(false);
        botonEdicionDocentes.setBorderPainted(false);
        botonEdicionDocentes.setPreferredSize(new Dimension(120, 35));

        JButton botonEliminacionDocentes = new JButton("Eliminar docente");
        botonEliminacionDocentes.setFont(new Font("Arial", Font.BOLD, 14));
        botonEliminacionDocentes.setBackground(new Color(51, 153, 255));
        botonEliminacionDocentes.setForeground(Color.WHITE);
        botonEliminacionDocentes.setFocusPainted(false);
        botonEliminacionDocentes.setBorderPainted(false);
        botonEliminacionDocentes.setPreferredSize(new Dimension(120, 35));

        JButton botonBusquedaDocentes = new JButton("Buscar docente");
        botonBusquedaDocentes.setFont(new Font("Arial", Font.BOLD, 14));
        botonBusquedaDocentes.setBackground(new Color(51, 153, 255));
        botonBusquedaDocentes.setForeground(Color.WHITE);
        botonBusquedaDocentes.setFocusPainted(false);
        botonBusquedaDocentes.setBorderPainted(false);
        botonBusquedaDocentes.setPreferredSize(new Dimension(120, 35));

        IdTxt = new JTextField(10);
        NombreTxt = new JTextField(10);
        ApellidoTxt = new JTextField(10);
        FechaNacimientoTxt = new JTextField(10);
        FechaContratacionTxt = new JTextField(10);
        CorreoTxt = new JTextField(10);
        ActivoCheckBox = new JCheckBox();
        NuevoCorreoTxt = new JTextField(10);
        DocenteTxt = new JTextField(10);
        labelNuevoCorreo.setVisible(false);
        NuevoCorreoTxt.setVisible(false);

        // Agregar elementos al panel
        PanelRegistroDocentes.add(labelNombre);
        PanelRegistroDocentes.add(NombreTxt);

        PanelRegistroDocentes.add(labelApellido);
        PanelRegistroDocentes.add(ApellidoTxt);

        PanelRegistroDocentes.add(labelNacimiento);
        PanelRegistroDocentes.add(FechaNacimientoTxt);

        PanelRegistroDocentes.add(labelContratacion);
        PanelRegistroDocentes.add(FechaContratacionTxt);

        PanelRegistroDocentes.add(labelCorreo);
        PanelRegistroDocentes.add(CorreoTxt);

        PanelRegistroDocentes.add(labelActivo);
        PanelRegistroDocentes.add(ActivoCheckBox);

        PanelRegistroDocentes.add(labelIdDocente);
        PanelRegistroDocentes.add(DocenteTxt);

        PanelRegistroDocentes.add(labelNuevoCorreo);
        PanelRegistroDocentes.add(NuevoCorreoTxt);

        PanelRegistroDocentes.add(botonEliminacionDocentes);
        PanelRegistroDocentes.add(botonEdicionDocentes);
        PanelRegistroDocentes.add(botonRegistroDocentes);
        PanelRegistroDocentes.add(botonBusquedaDocentes);

        // Panel para la tabla
        JPanel PanelTablaDocentes = new JPanel(new BorderLayout());
        PanelTablaDocentes.setBorder(BorderFactory.createTitledBorder("Lista de Docentes"));
        PanelTablaDocentes.setBackground(Color.LIGHT_GRAY);

        ModeloDocente = new DefaultTableModel(new String[]{"Id", "Nombre", "Apellido", "Fecha_Nacimiento", "Fecha_Contratacion", "Correo_electronico", "Activo"}, 0);
        TablaDocentes = new JTable(ModeloDocente);
        JScrollPane scrollTabla = new JScrollPane(TablaDocentes);
        PanelTablaDocentes.add(scrollTabla, BorderLayout.CENTER);
        add(PanelRegistroDocentes, BorderLayout.NORTH);
        add(PanelTablaDocentes, BorderLayout.CENTER);

        // Estilos
        IdTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NombreTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        ApellidoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        FechaNacimientoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        FechaContratacionTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        CorreoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        NuevoCorreoTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        DocenteTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    // Lógica de los botones

botonRegistroDocentes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);

            Date fechaNacimiento, fechaContratacion;
            if (NombreTxt.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ApellidoTxt.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Ingrese el apellido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (CorreoTxt.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Ingrese el correo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                fechaNacimiento = dateFormat.parse(FechaNacimientoTxt.getText().trim());
                fechaContratacion = dateFormat.parse(FechaContratacionTxt.getText().trim());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Las fechas deben tener el formato YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            java.sql.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());
            java.sql.Date sqlFechaContratacion = new java.sql.Date(fechaContratacion.getTime());
            Docentes docente = new Docentes();
            docente.setNombre(NombreTxt.getText());
            docente.setApellido(ApellidoTxt.getText());
            docente.setFecha_nacimiento(sqlFechaNacimiento);
            docente.setFecha_contratacion(sqlFechaContratacion);
            docente.setCorreo_electronico(CorreoTxt.getText());
            docente.setActivo(ActivoCheckBox.isSelected());
            try {
                if (DS.CrearDocente(docente)) {
                    JOptionPane.showMessageDialog(null, "Docente registrado con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar el docente, intente nuevamente");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ListarDocentes();
            limpiarDatosDocente();
        }
    });
botonEdicionDocentes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NombreTxt.setEnabled(false);
            ApellidoTxt.setEnabled(false);
            FechaNacimientoTxt.setEnabled(false);
            FechaContratacionTxt.setEnabled(false);
            CorreoTxt.setEnabled(false);
            int fila = TablaDocentes.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un docente");
                return;
            }
            String nuevoCorreo = NuevoCorreoTxt.getText();
            if (nuevoCorreo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el nuevo correo");
                return;
            }
            String idSeleccionado = (DocenteTxt.getText());
            boolean exito = false;
            try {
                exito = DS.editarCorreo(nuevoCorreo, idSeleccionado);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (exito) {
                JOptionPane.showMessageDialog(null, "Modificación realizada con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "Error al realizar modificación");
            }
            ListarDocentes();
            limpiarDatosDocente();
        }
    });
botonEliminacionDocentes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int fila = TablaDocentes.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un docente");
                return;
            }
            String idTexto = DocenteTxt.getText().trim();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el ID del docente");
                return;
            }
            int idSeleccionado = Integer.parseInt(idTexto);
            try {
                if (DS.eliminarDocente(String.valueOf(idSeleccionado))) {
                    JOptionPane.showMessageDialog(null, "Docente eliminado con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el docente");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ListarDocentes();
            limpiarDatosDocente();
        }
});
        botonBusquedaDocentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBusqueda = DocenteTxt.getText().trim();
                if (nombreBusqueda.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre para buscar.");
                    return;
                }

                try {
                    // Buscar docentes por nombre
                    List<Docentes> docentesEncontrados =  DS.buscarDocentesPorNombre(nombreBusqueda);
                    if (docentesEncontrados.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No se encontraron docentes con el nombre proporcionado.");
                        return;
                    }

                    // Mostrar docentes encontrados en la tabla
                    ModeloDocente.setRowCount(0);
                    for (Docentes docente : docentesEncontrados) {
                        ModeloDocente.addRow(new Object[]{
                                docente.getId(),
                                docente.getNombre(),
                                docente.getApellido(),
                                docente.getFecha_nacimiento(),
                                docente.getFecha_contratacion(),
                                docente.getCorreo_electronico(),
                                docente.isActivo()
                        });

                        // Listar cursos asociados al docente
                        List<Cursos> cursosDocente = DS.listarCursosPorDocente(docente.getId());
                        StringBuilder cursosInfo = new StringBuilder("Cursos dictados por " + docente.getNombre() + ":\n");
                        for (Cursos curso : cursosDocente) {
                            cursosInfo.append("- ").append(curso.getNombre_curso()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, cursosInfo.toString());
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar docentes: " + ex.getMessage());
                }
            }
        });
    }

        private void ListarDocentes(){
            List<Docentes> ListaDocentes=null;
            try {
                ListaDocentes= DS.listarDocentes();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ModeloDocente.setRowCount(0);
            ModeloDocente= (DefaultTableModel) TablaDocentes.getModel();
            Object[] Objeto =new Object[7];
            for (Docentes listaDocente : ListaDocentes) {
                Objeto[0] = listaDocente.getId();
                Objeto[1] = listaDocente.getNombre();
                Objeto[2] = listaDocente.getApellido();
                Objeto[3] = listaDocente.getCorreo_electronico();
                Objeto[4] = listaDocente.getFecha_nacimiento();
                Objeto[5] = listaDocente.getFecha_contratacion();
                Objeto[6] = listaDocente.isActivo();
                ModeloDocente.addRow(Objeto);
            }
            TablaDocentes.setModel(ModeloDocente);
        }
        public void limpiarDatosDocente(){
            NombreTxt.setText("");
            ApellidoTxt.setText("");
            CorreoTxt.setText("");
            FechaNacimientoTxt.setText("");
            FechaContratacionTxt.setText("");
            ActivoCheckBox.setSelected(false);

            NombreTxt.setEnabled(true);
            ApellidoTxt.setEnabled(true);
            CorreoTxt.setEnabled(true);
            FechaNacimientoTxt.setEnabled(true);
            FechaContratacionTxt.setEnabled(true);
            ActivoCheckBox.setEnabled(true);


        }

    }