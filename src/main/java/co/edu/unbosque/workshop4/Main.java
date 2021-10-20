package co.edu.unbosque.workshop4;

import java.sql.Connection;

public class Main {

    //Driver  de JDBC
    private static String jdbcDriver = "org.postgresql.Driver";

    private static String db = "jdbc:postgresql://localhost/WorkShop4";

    private static String user = "postgres";
    private static String password = "12345";


    public static void main(String[] args) {
        Connection con = null;
    }

}
