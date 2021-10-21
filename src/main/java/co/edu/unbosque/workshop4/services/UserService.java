package co.edu.unbosque.workshop4.services;

import co.edu.unbosque.workshop4.dtos.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserService {

    private Connection conn;

    public UserService(Connection conn) {
        this.conn = conn;
    }

    public void listUsers(String roles) {
        Statement stmt = null;

        ArrayList<User> users = new ArrayList<>();

        try {
            System.out.println("Enviando Lista Usuarios...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM UserApp WHERE role = '" + roles + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");
                users.add(new User(username, password, email, role));
            }
            System.out.println("User Name\tPassword\tEmail\tRole");
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
