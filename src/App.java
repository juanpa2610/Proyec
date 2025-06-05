import javax.swing.*;

/**
 * Clase principal que inicia la aplicación
 */
public class App {
    /**
     * Método principal que inicia la aplicación
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Establecer look and feel nativo del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error al establecer look and feel: " + e.getMessage());
        }
        
        // Inicializar componentes con SwingUtilities para evitar problemas de concurrencia
        SwingUtilities.invokeLater(() -> {
            try {
                // Crear instancias de los componentes principales
                EstudianteDAO dao = new EstudianteDAO();
                VentanaResultados resultados = new VentanaResultados();
                VentanaOpciones opciones = new VentanaOpciones(dao, resultados);
                
                // Configurar mensaje de bienvenida
                resultados.mostrarMensaje(
                    "BIENVENIDO AL SISTEMA DE GESTIÓN DE ESTUDIANTES\n\n" +
                    "Este sistema permite administrar registros de estudiantes mediante\n" +
                    "operaciones CRUD (Crear, Leer, Actualizar, Eliminar).\n\n" +
                    "Seleccione una opción en la ventana principal para comenzar."
                );

                // Mostrar ambas ventanas
                opciones.setVisible(true);
                resultados.setVisible(true);

                // Posicionar la ventana de resultados al lado de la ventana de opciones
                resultados.setLocation(opciones.getX() + opciones.getWidth() + 10, opciones.getY());
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al iniciar la aplicación: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                System.exit(1);
            }
        });
    }
}