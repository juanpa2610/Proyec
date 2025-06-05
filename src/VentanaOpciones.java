import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Ventana que contiene los botones para administrar estudiantes
 */
public class VentanaOpciones extends JFrame {
    private EstudianteDAO dao;
    private VentanaResultados ventanaResultados;

    private JButton btnAgregar;
    private JButton btnListar;
    private JButton btnEliminar;
    private JButton btnActualizar;

    public VentanaOpciones(EstudianteDAO dao, VentanaResultados ventanaResultados) {
        this.dao = dao;
        this.ventanaResultados = ventanaResultados;

        setTitle("Opciones - Gestión de Estudiantes");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));
        setLocationRelativeTo(null);

        // Inicializar botones
        btnAgregar = new JButton("Agregar Estudiante");
        btnListar = new JButton("Listar Estudiantes");
        btnEliminar = new JButton("Eliminar Estudiante");
        btnActualizar = new JButton("Actualizar Estudiante");

        // Estilos de colores de fondo
        btnAgregar.setBackground(new Color(46, 204, 113)); // verde
        btnAgregar.setFocusPainted(false);

        btnListar.setBackground(new Color(52, 152, 219)); // azul
        btnListar.setFocusPainted(false);

        btnEliminar.setBackground(new Color(231, 76, 60)); // rojo
        btnEliminar.setFocusPainted(false);

        btnActualizar.setBackground(new Color(241, 196, 15)); // amarillo
        btnActualizar.setFocusPainted(false);

        // Agregar componentes
        add(new JLabel("Seleccione una opción:", SwingConstants.CENTER));
        add(btnAgregar);
        add(btnListar);
        add(btnEliminar);
        add(btnActualizar);

        // Eventos
        btnAgregar.addActionListener(e -> abrirDialogoAgregar());
        btnListar.addActionListener(e -> listarEstudiantes());
        btnEliminar.addActionListener(e -> abrirDialogoEliminar());
        btnActualizar.addActionListener(e -> abrirDialogoActualizar());
    }

    private void abrirDialogoAgregar() {
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtEdad = new JTextField();
        JTextField txtCarrera = new JTextField();

        Object[] mensaje = {
            "ID:", txtId,
            "Nombre:", txtNombre,
            "Edad:", txtEdad,
            "Carrera:", txtCarrera
        };

        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Agregar Estudiante", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                int edad = Integer.parseInt(txtEdad.getText());
                String carrera = txtCarrera.getText();

                Estudiante nuevo = new Estudiante(id, nombre, edad, carrera);
                dao.agregarEstudiante(nuevo);
                ventanaResultados.mostrarMensaje("Estudiante agregado correctamente:\n" +
                        nombre + " - " + carrera);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID y Edad deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar estudiante: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void listarEstudiantes() {
        try {
            List<Estudiante> lista = dao.obtenerTodosLosEstudiantes();
            if (lista.isEmpty()) {
                ventanaResultados.mostrarMensaje("No hay estudiantes registrados.");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("LISTA DE ESTUDIANTES:\n\n");
                for (Estudiante e : lista) {
                    sb.append("ID: ").append(e.getId())
                      .append(", Nombre: ").append(e.getNombre())
                      .append(", Edad: ").append(e.getEdad())
                      .append(", Carrera: ").append(e.getCarrera())
                      .append("\n");
                }
                ventanaResultados.mostrarMensaje(sb.toString());
            }
        } catch (SQLException ex) {
            ventanaResultados.mostrarMensaje("Error al listar estudiantes: " + ex.getMessage());
        }
    }

    private void abrirDialogoEliminar() {
        String input = JOptionPane.showInputDialog(this, "Ingrese el ID del estudiante a eliminar:", "Eliminar Estudiante", JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            try {
                int id = Integer.parseInt(input);
                dao.eliminarEstudiante(id);
                ventanaResultados.mostrarMensaje("Estudiante con ID " + id + " eliminado (si existía).");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar estudiante: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void abrirDialogoActualizar() {
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtEdad = new JTextField();
        JTextField txtCarrera = new JTextField();

        Object[] mensaje = {
            "ID del estudiante a actualizar:", txtId,
            "Nuevo Nombre:", txtNombre,
            "Nueva Edad:", txtEdad,
            "Nueva Carrera:", txtCarrera
        };

        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Actualizar Estudiante", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                int edad = Integer.parseInt(txtEdad.getText());
                String carrera = txtCarrera.getText();

                Estudiante actualizado = new Estudiante(id, nombre, edad, carrera);
                dao.actualizarEstudiante(actualizado);
                ventanaResultados.mostrarMensaje("Estudiante actualizado correctamente:\n" +
                        nombre + " - " + carrera);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID y Edad deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar estudiante: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
