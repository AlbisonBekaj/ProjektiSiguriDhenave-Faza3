package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;
    private static final String DB_URL = "jdbc:postgresql://localhost/DSfaza3";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Grupi14KNK";

    public static Connection getConnection() {
        if (DBConnection.connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
