package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/JDBC_Clase";
    private static final String USER = "postgres";
    private static final String PASW = "1234";

    public static Connection getConnection()  {
        Connection connection = null;

        try{ connection=  DriverManager.getConnection(URL, USER, PASW); }
        catch (SQLException e) { System.out.println("Error en la conexión a la base de datos: " + e.getMessage()); }

        return connection;
    }
}