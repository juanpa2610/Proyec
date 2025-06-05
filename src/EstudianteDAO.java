import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa las operaciones CRUD para la entidad Estudiante
 * utilizando una base de datos MySQL
 */
public class EstudianteDAO {
    private ConexionBD conexion;
    
    /**
     * Constructor que inicializa la conexión a la base de datos
     */
    public EstudianteDAO() {
        this.conexion = new ConexionBD();
    }
    
    /**
     * Agrega un nuevo estudiante a la base de datos
     * @param estudiante El estudiante a agregar
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public void agregarEstudiante(Estudiante estudiante) throws SQLException {
        String sql = "INSERT INTO estudiantes (id, nombre, edad, carrera) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, estudiante.getId());
            pstmt.setString(2, estudiante.getNombre());
            pstmt.setInt(3, estudiante.getEdad());
            pstmt.setString(4, estudiante.getCarrera());
            
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Obtiene todos los estudiantes de la base de datos
     * @return Lista de todos los estudiantes
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public List<Estudiante> obtenerTodosLosEstudiantes() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT id, nombre, edad, carrera FROM estudiantes";
        
        try (Connection conn = conexion.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    rs.getString("carrera")
                );
                estudiantes.add(estudiante);
            }
        }
        
        return estudiantes;
    }
    
    /**
     * Obtiene un estudiante específico por su ID
     * @param id El ID del estudiante a buscar
     * @return El estudiante encontrado o null si no existe
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public Estudiante obtenerEstudiantePorId(int id) throws SQLException {
        String sql = "SELECT id, nombre, edad, carrera FROM estudiantes WHERE id = ?";
        
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("carrera")
                    );
                }
            }
        }
        
        return null;
    }
    
    /**
     * Actualiza la información de un estudiante existente
     * @param estudiante El estudiante con la información actualizada
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public void actualizarEstudiante(Estudiante estudiante) throws SQLException {
        String sql = "UPDATE estudiantes SET nombre = ?, edad = ?, carrera = ? WHERE id = ?";
        
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, estudiante.getNombre());
            pstmt.setInt(2, estudiante.getEdad());
            pstmt.setString(3, estudiante.getCarrera());
            pstmt.setInt(4, estudiante.getId());
            
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Elimina un estudiante de la base de datos por su ID
     * @param id El ID del estudiante a eliminar
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public void eliminarEstudiante(int id) throws SQLException {
        String sql = "DELETE FROM estudiantes WHERE id = ?";
        
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}