package app;

import java.sql.*;

public class DatabaseHandler {
    
    public static Connection Connector() {
        String url = "jdbc:sqlite:foodl";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
