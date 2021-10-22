package co.edu.unbosque.workshop4.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import co.edu.unbosque.workshop4.dtos.PetCase;

/**
 * @author Jauan Sebastian VArgas
 * Clase encargada de ejecutar query para posgreSQL para obtener los datos de la base de datos
 */
public class PetCaseService {
    /**
     * Se inicializa la conexion a la base de datos
     */
    private Connection conn;
    /**
     * Constructor de la clase
     * @param conn recive la conexion a la base de datos establecida en donde se instancie la clase
     */
    public PetCaseService(Connection conn) {
        this.conn = conn;
    }
    /**
     * Metodo que tiene la funcionalidad para agregar datos a la base de datos de la tabla PetCase
     * recibido como parametro los datos que se van a agregar a la tabla.
     * @param p
     */
    public void InsertCase(PetCase p ){
        Statement stm = null;
        Statement stm2 = null;
        try {
            stm = conn.createStatement();
            stm2 = conn.createStatement();
            String sql2 = "SELECT COUNT(*) AS count FROM PetCase";
            ResultSet rs2 = stm.executeQuery(sql2);
            rs2.next();
            int numCases = rs2.getInt("count");
            rs2.close();
            stm.close();
            String sql = "INSERT INTO PetCase (case_id, created_at, type, description, pet_id) "+
                    "VALUES ("+(numCases + 1)+", '"+p.getCreateAt()+"', '" +
                    p.getType()+"', '"+p.getDescription()+"', '"+ p.getPetId()+"')";
            System.out.println("Registro de caso exitoso");
            stm2.executeUpdate(sql);
            stm2.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }else if(stm2 != null){
                    stm2.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
