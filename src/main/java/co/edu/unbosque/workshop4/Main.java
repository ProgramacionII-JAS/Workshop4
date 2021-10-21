package co.edu.unbosque.workshop4;

import co.edu.unbosque.workshop4.dtos.PetCase;
import co.edu.unbosque.workshop4.services.PetCaseService;
import co.edu.unbosque.workshop4.services.UserService;
import co.edu.unbosque.workshop4.services.VetService;
import co.edu.unbosque.workshop4.services.VisitService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {

    //Driver  de JDBC
    private static String jdbcDriver = "org.postgresql.Driver";

    private static String db = "jdbc:postgresql://localhost/workshop4";

    private static String user = "postgres";
    private static String password = "12345";

    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        Connection con = null;

        try {

            Class.forName(jdbcDriver);

            System.out.println("Conectando con el Servidor...");

            String menu = "===================================\n"+"Ciudadanos de 4 Patas \n" +
                    "1. Consultar la lista de usuarios registrados para un rol dado.\n" +
                    "2. Contar la lista de veterinarias registradas en la plataforma.\n" +
                    "3. Consultar las visitas que se han registrado para un ID de mascota dado.\n" +
                    "4. Registrar un nuevo caso de robo de una mascota dado su ID.\n" +
                    "5. Salir\n" +
                    "Ingrese la opción deseada:\n"+"===================================";
            boolean bandera = true;

            while (bandera) {
                System.out.println(menu);
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Ingrese Rol a consultar");
                        String rol = sc.next();
                        con = DriverManager.getConnection(db, user, password);
                        UserService uService = new UserService(con);
                        uService.listUsers(rol);
                        con.close();
                        break;
                    case 2:
                        con = DriverManager.getConnection(db, user, password);
                        VetService vService = new VetService(con);
                        vService.listVet();
                        con.close();
                        break;

                        case 3:
                        System.out.println("Ingrese el ID de la mascota a consultar");
                        int idPet = sc.nextInt();
                        con = DriverManager.getConnection(db, user, password);

                        VisitService visService = new VisitService(con);
                        visService.listVisits(idPet);
                        con.close();
                        break;

                    case 4:
                        Date fecha = new Date();
                        System.out.println("Ingrese Id de la mascota");
                        int id = sc.nextInt();
                        System.out.println("Ingrese la descripcion del caso");
                        String description = sc.next();
                        con = DriverManager.getConnection(db, user, password);
                        PetCase p = new PetCase(fecha.toString(),"robo",description, id);
                        PetCaseService ps = new PetCaseService(con);
                        ps.InsertCase(p);
                        con.close();
                        break;

                    case 5:
                        bandera = false;
                        System.out.println("Gracias por usar Ciudadanos de 4 Patas");
                        System.out.println("Desconectando del Servidor...");
                        break;
                    default:
                        System.out.println("Ingrese una opción correcta");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            // Cleaning-up environment
            try {
                if(con != null){
                    con.close();
                }
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }


    }

}
