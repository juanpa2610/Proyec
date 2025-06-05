import javax.swing.*;
import java.awt.*;

/**
 * Ventana que muestra los resultados de las operaciones realizadas en el sistema
 */
public class VentanaResultados extends JFrame {
    private JTextArea areaTexto;

    /**
     * Constructor que inicializa la ventana con sus componentes
     */
    public VentanaResultados() {
        // Configuración básica de la ventana
        setTitle("Resultados - Sistema de Gestión de Estudiantes");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel de título
        JPanel panelTitulo = new JPanel();
        JLabel labelTitulo = new JLabel("RESULTADOS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitulo.add(labelTitulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Área de texto para mostrar resultados
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaTexto.setMargin(new Insets(10, 10, 10, 10));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        
        // Mensaje inicial
        areaTexto.setText("Bienvenido al Sistema de Gestión de Estudiantes\n\n" +
                          "Seleccione una opción en la ventana principal para comenzar.");
        
        // Agregar área de texto con scroll
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior con información
        JPanel panelInfo = new JPanel();
        JLabel labelInfo = new JLabel("Aquí se mostrarán los resultados de las operaciones");
        panelInfo.add(labelInfo);
        add(panelInfo, BorderLayout.SOUTH);
    }

    /**
     * Muestra un mensaje en el área de texto
     * @param mensaje El mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        areaTexto.setText(mensaje);
        // Desplazar al inicio del texto
        areaTexto.setCaretPosition(0);
    }
    
    /**
     * Agrega un mensaje al área de texto (sin borrar el contenido actual)
     * @param mensaje El mensaje a agregar
     */
    public void agregarMensaje(String mensaje) {
        areaTexto.append("\n\n" + mensaje);
        // Desplazar al final del texto
        areaTexto.setCaretPosition(areaTexto.getText().length());
    }
    
    /**
     * Limpia el área de texto
     */
    public void limpiar() {
        areaTexto.setText("");
    }
}