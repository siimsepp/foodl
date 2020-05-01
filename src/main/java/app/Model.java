package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    
    public String dbUuendamine;
    Connection conn = DatabaseHandler.Connector();
    
    public List<String> select(String sql) throws SQLException {
        List<String> toiduainedAndmebaasist = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                toiduainedAndmebaasist.add(rs.getString("toit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toiduainedAndmebaasist;
    }
    
    // Tõmbab kõik andmed andmebaasist ja teeb igast reast "Toiduaine" objekti ning tagastab neist objektidest koosneva listi.
    // Seda on vaja, et objekte saaks tabelis kuvada.
    public List<Toiduaine> selectToiduainedMakeObjects() throws SQLException {
        String sql = "SELECT * FROM toiduained";
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
    public void kustutaToiduaine(int id) {
        String sql = "DELETE FROM toiduained WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void lisaToiduaine(String toit, double tihedus) {
        String sql = "INSERT INTO toiduained(toit, tihedus) VALUES(?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, toit);
            pstmt.setDouble(2, tihedus);
            
            // Kontroll, kas õnnestus andmebaasi uuendada.
            int i = pstmt.executeUpdate();
            if (i > 0) {
                dbUuendamine = "Andmed lisatud";
            } else {
                dbUuendamine = "Andmeid ei lisatud";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    public void uuendaToiduaine(String toit, double tihedus) {
        String sql = "UPDATE toiduained SET tihedus = ? WHERE toit = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, tihedus);
            pstmt.setString(2, toit);
//            pstmt.executeUpdate();
            
            // Kontroll, kas õnnestus andmebaasi uuendada.
            int i = pstmt.executeUpdate();
            if (i > 0) {
                dbUuendamine = "Andmed uuendatud";
            } else {
                dbUuendamine = "Andmeid ei uuendatud";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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








        
        

