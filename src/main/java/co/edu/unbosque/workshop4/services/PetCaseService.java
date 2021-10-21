package co.edu.unbosque.workshop4.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import co.edu.unbosque.workshop4.dtos.PetCase;
public class PetCaseService {

    private Connection conn;

    public PetCaseService(Connection conn) {
        this.conn = conn;
    }

    public void InsertCase(PetCase p ){
        Statement stm = null;
        try {
            stm = conn.createStatement();
            String sql2 = "SELECT COUNT(*) AS count FROM PetCase";
            ResultSet rs2 = stm.executeQuery(sql2);
            rs2.next();
            int numCases = rs2.getInt("count");
            String sql = "INSERT INTO PetCase (case_id, created_at, type, description, pet_id) "+
                    "VALUES ("+(numCases + 1)+", '"+p.getCreateAt()+"', '" +
                    p.getType()+"', '"+p.getDescription()+"', '"+ p.getPetId()+"')";
            stm.executeQuery(sql);
            System.out.println("Registro de caso exitoso");
            rs2.close();
            stm.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
