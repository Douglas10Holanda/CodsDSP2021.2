package DAO;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/funcionarios",
                "postgres", "Dpholanda120");
    }
}