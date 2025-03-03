package com.example.notitas.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/bloc_notas";
    private static final String PASSWORD = "fenrirpro";
    private static final String USER = "postgres";

    public static Connection getConnection () throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
