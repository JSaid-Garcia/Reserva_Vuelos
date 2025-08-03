/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author jainer Said Garcia
 */

public class DataBaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=reservas_vuelo;encrypt=true;trustServerCertificate=true";
    private static final String USER = "Said"; // Cambia por tu usuario de SQL Server
    private static final String PASSWORD = "123456"; // Cambia por tu contraseña

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos SQL Server");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver de SQL Server no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a SQL Server: " + e.getMessage());
        }
        return connection;
    }
}
