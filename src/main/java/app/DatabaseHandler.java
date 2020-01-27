package app;

import java.sql.*;

public class DatabaseHandler {
    
    public Connection DBConn() throws SQLException {
        
        String host = "jdbc:mysql://localhost:3306/foodl";
        String kasutaja = "root";
        String parool = "parool";
        return DriverManager.getConnection(host, kasutaja, parool);
    }
}
