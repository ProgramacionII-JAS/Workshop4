package co.edu.unbosque.workshop4.services;

import co.edu.unbosque.workshop4.dtos.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Santiago Flórez
 * Clase encargada de ejecutar query para posgreSQL para obtener los datos de la base de datos de la veterinaria
 */
public class VetService {
    /**
     * Se inicializa la conexion a la base de datos
     */
    private Connection conn;
    /**
     * Constructor de la clase
     * @param conn recive la conexion a la base de datos establecida en donde se instancie la clase
     */
    public VetService(Connection conn) {
        this.conn = conn;
    }
    /**
     * Metodo que tiene la funcionalidad para obtener los datos de la base de datos de la tabla Vet
     */
    public void listVet() {
        Statement stmt = null;

        ArrayList<User> users = new ArrayList<>();

        try {
            System.out.println("Enviando Lista Veterinarias...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Vet";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String neighborhood = rs.getString("neighborhood");
                users.add(new User(username, name, address, neighborhood));
            }
            System.out.println("Usuario\tName\tAddress\tNeighborhood");
            for (User user : users) {
                System.out.println(user.getUserName() + "\t" + user.getPassword() + "\t" + user.getEmail() + "\t" + user.getRole());
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
