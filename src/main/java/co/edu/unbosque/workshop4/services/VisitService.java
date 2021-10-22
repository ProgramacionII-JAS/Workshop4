package co.edu.unbosque.workshop4.services;

import co.edu.unbosque.workshop4.dtos.Visit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Ana Perez
 * Clase encargada de ejecutar query para posgreSQL para obtener los datos de la base de datos de la veterinaria
 */
public class VisitService {
    /**
     * Se inicializa la conexion a la base de datos
     */
    private Connection conn;
    /**
     * Constructor de la clase
     * @param conn recive la conexion a la base de datos establecida en donde se instancie la clase
     */
    public VisitService(Connection conn) {
        this.conn = conn;
    }
    /**
     * Metodo que tiene la funcionalidad para obtener los datos de la base de datos de la tabla Visit
     * dependiendo del id de la mascota recibido como parametro.
     * @param idPett
     */
    public void listVisits(int idPett) throws SQLException {
        Statement stmt = null;

        ArrayList<Visit> visits = new ArrayList<>();

        try {
            System.out.println("Enviando lista de visitas....");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Visit WHERE pet_id = '" + idPett + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                 int idVisit = rs.getInt("visit_id");
                 String date = rs.getString("created_at");
                 String type = rs.getString("type");
                 String description = rs.getString("description");
                 String vetId = rs.getString("vet_id");
                int petId = rs.getInt("pet_id");

                 visits.add(new Visit(idVisit,date, type, description, vetId,petId));
            }
            System.out.println("idVisit\tdate\ttype\tdescription\tvetTd\tpetTd");
            for (Visit visit : visits) {
                System.out.println(visit.getIdVisit()+ "\t" + visit.getDate()+ "\t" + visit.getType()+ "\t" + visit.getDescription()+ "\t" + visit.getVetId()+ "\t" + visit.getPetId());
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
