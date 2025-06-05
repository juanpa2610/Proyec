import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que maneja la conexión a la base de datos.
 * Esta clase implementa el patrón Singleton para asegurar una única instancia de conexión.
 */
public class ConexionBD {
    // Configuración de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_estudiantes?serverTimezone=UTC";
    private static final String USER = "root"; // Cambia esto según tu configuración
    private static final String PASSWORD = "1234oliver"; // Cambia esto según tu configuración
    
    // Driver JDBC para MySQL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   
    /**
     * Constructor privado para evitar instanciación directa (patrón Singleton)
     */
    public ConexionBD() {
        try {
            // Cargar el driver JDBC
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se pudo cargar el driver JDBC: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene una conexión a la base de datos
     * @return Una conexión a la base de datos
     * @throws SQLException Si ocurre un error al conectar
     */
    public Connection obtenerConexion() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e; // Re-lanzar la excepción para que se maneje en las capas superiores
        }
        return connection;
    }
}