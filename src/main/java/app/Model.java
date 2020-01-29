package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    
    Connection conn = DatabaseHandler.Connector();
    
    public List<String> select(String sql) throws SQLException {
//        String sql = "SELECT * FROM toiduained";
//        String sql = "DELETE FROM pere WHERE nimi = 'Hüüp Sepp';";
        
        List<String> toiduainedAndmebaasist = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                toiduainedAndmebaasist.add(rs.getString("toit"));
//                System.out.println(rs.getString("toit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toiduainedAndmebaasist;
    }
    
    
    
    
    public List<Toiduaine> selectToiduainedMakeObjects(String sql) throws SQLException {
        
        List<Toiduaine> toiduainedAndmebaasist = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String toit = rs.getString("toit");
                Double tihedus = rs.getDouble("tihedus");
                Toiduaine toiduaine = new Toiduaine(id, toit, tihedus);
                toiduainedAndmebaasist.add(toiduaine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toiduainedAndmebaasist;
    }
    
    
    
    
    
    public double selectDensity(String sql) throws SQLException {
        
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            return rs.getDouble("tihedus");
//            while (rs.next()) {
//                toiduainedAndmebaasist.add(rs.getString("toit"));
//                System.out.println(rs.getString("toit"));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
//        return toiduainedAndmebaasist;
    }
    
    
    
//    public void selectAll() throws SQLException {
//        String sql = "SELECT * FROM toiduained";
////        String sql = "DELETE FROM pere WHERE nimi = 'Hüüp Sepp';";
//        try {
//            ResultSet rs = conn.createStatement().executeQuery(sql);
//            while (rs.next()) {
//                System.out.println(rs.getString("toit"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    

    
    
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








        
        

