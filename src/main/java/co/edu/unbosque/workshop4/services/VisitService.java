package co.edu.unbosque.workshop4.services;

import co.edu.unbosque.workshop4.dtos.User;
import co.edu.unbosque.workshop4.dtos.Visit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class    VisitService {

    private Connection conn;

    public VisitService(Connection conn) {
        this.conn = conn;
    }

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
