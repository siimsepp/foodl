package app;

import java.sql.*;

public class Model {
    
    Connection conn = DatabaseHandler.Connector();
    
    public void selectAll() throws SQLException {
        String sql = "SELECT * FROM pere";
//        String sql = "DELETE FROM pere WHERE nimi = 'Hüüp Sepp';";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("nimi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public Model() {
        if (conn == null) {
            System.out.println("Andmebaasiga ei saanud ühendust");
            System.exit(1);
        }
    }
    
    public boolean isDbConnected() {
        try {
            return !conn.isClosed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}








        
        

