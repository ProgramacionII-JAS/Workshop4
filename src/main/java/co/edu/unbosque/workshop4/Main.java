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

/**
 * @author Santiago Flórez, Ana Perez, Juan Sebastian Vargas
 * La clase main es la encargada de ejecutar el codigo y se realiza la funcionalidad del programa
 */
public class Main {

    /**
     * Se realizan las creaciones de los atributos, que son conexiones a posgreSQL, usuario y contraseña de posgre
     * Y para leer las entradas de datos del usuario en la consola
     */
    //Driver  de JDBC
    private static String jdbcDriver = "org.postgresql.Driver";

    private static String db = "jdbc:postgresql://localhost/workshop4";

    private static String user = "postgres";
    private static String password = "12345";

    private static Scanner sc;

    /**
     * Metodo main para ejecutar el programa
     * @param args
     */
    public static void main(String[] args) {

        /**
         * Se inicializan Connection, encargado de realizar la conexion a posgreSQL y
         * el Scanner para lectura de datos de la consola
         */
        sc = new Scanner(System.in);
        Connection con = null;
        /**
         * Funcionalidad por medio de try-catch y un switch
         */
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
                    /**
                     * Cases dependiendo de la entrada de datos del usuario
                     * Cada tiene una funcionalidad individual y se encargan de instanciar Clases necesarias
                     */

                    /**
                     * Case 1, se encarga de pedir el rol de un usuario:propietario,funcionario o veterinario.
                     * Dependiendo de lo que se escriba en la consola se realiza la conexion a la base de datos, se
                     * instancia a UserService y se llama al metodo listUsers con parametro rol, que es el dato ingresado
                     * en consola. Se cierra la conexion co la base de datos.
                     */
                    case 1:
                        System.out.println("Ingrese Rol a consultar");
                        String rol = sc.next();
                        con = DriverManager.getConnection(db, user, password);
                        UserService uService = new UserService(con);
                        uService.listUsers(rol);
                        con.close();
                        break;
                    /**
                     * Case 2, se encarga de mostrar en la consola la informacion de la tabla de la base de datos Vet.
                     * Se crea la conexion con la base de datos, se instancia a VetService y se llama al metodo listVet.
                     * Se cierra la conexion.
                     */
                    case 2:
                        con = DriverManager.getConnection(db, user, password);
                        VetService vService = new VetService(con);
                        vService.listVet();
                        con.close();
                        break;
                    /**
                     * Case 3, se encarga de mostrar la informacion de las visitas a la veterinaria de una mascota
                     * por medio de un ID ingresado en la consola.
                     * Se pide el ID de la mascota y se realiza la coñexion a la base de datos. Se inicializa a VisitService
                     * y se llama al metodo listVisits con el parametro del ID ingresado en la consola. Se cierra la conexion.
                     */
                    case 3:
                        System.out.println("Ingrese el ID de la mascota a consultar");
                        int idPet = sc.nextInt();
                        con = DriverManager.getConnection(db, user, password);
                        VisitService visService = new VisitService(con);
                        visService.listVisits(idPet);
                        con.close();
                        break;
                    /**
                     * Case 4, se encarga de crear un estado actual de la mascota, en este caso de robo.
                     * Se ingresa el ID de la mascota y la descripcion del caso. Se crea la conexion con la base de datos
                     * Se instancia a PetCase con los parametros necesarios para agregar el caso.
                     * Se instancia a PetCaseService y se llama al metodo InsertCase con parametro de tipo PetCase
                     * se cierra la conexion.
                     */
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
                    /**
                     * Acaba el ciclo while
                     */
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
