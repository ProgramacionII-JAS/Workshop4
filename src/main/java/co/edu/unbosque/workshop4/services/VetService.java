package co.edu.unbosque.workshop4.services;

import co.edu.unbosque.workshop4.dtos.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VetService {

    private Connection conn;

    public VetService(Connection conn) {
        this.conn = conn;
    }

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
