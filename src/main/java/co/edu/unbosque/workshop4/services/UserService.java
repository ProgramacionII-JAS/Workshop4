package co.edu.unbosque.workshop4.services;

import co.edu.unbosque.workshop4.dtos.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Santiago Flórez
 * Clase encargada de ejecutar query para posgreSQL para obtener los datos de la base de datos dependiendo del rol
 */
public class UserService {
    /**
     * Se inicializa la conexion a la base de datos
     */
    private Connection conn;

    /**
     * Constructor de la clase
     * @param conn recive la conexion a la base de datos establecida en donde se instancie la clase
     */
    public UserService(Connection conn) {
        this.conn = conn;
    }

    /**
     * Metodo que tiene la funcionalidad para obtener los datos de la base de datos de la tabla UserApp
     * dependiendo del rol recibido como parametro.
     * @param roles
     */
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
