package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    
    @FXML
    private TextField sisend = new TextField();
    @FXML
    private Button nupp = new Button();
    @FXML
    private Label tulemus;
    @FXML
    private ComboBox<String> valik;
    
    @FXML
    private Label dblabel;
    
    private String valitud;
    ObservableList<String> toiduained = FXCollections.observableArrayList("nisujahu", "suhkur (valge)", "suhkur (pruun)", "piim");
//    ObservableList<Double> tihedused = FXCollections.observableArrayList(0.59, 1.03, 0.85, 0.72);
    
    
    @FXML
    public void processSisend(ActionEvent event) {
        
        Map<String, Double> toiduaineTihedused = new HashMap<>();
        toiduaineTihedused.put("nisujahu", 0.59);
        toiduaineTihedused.put("piim", 1.03);
        toiduaineTihedused.put("suhkur (valge)", 0.85);
        toiduaineTihedused.put("suhkur (pruun)", 0.72);
        
        if (valitud == null) {
            tulemus.setText("Vali toiduaine");
        } else {
            double tihedus = toiduaineTihedused.get(valitud);
            double detsiliitrid = Double.parseDouble(sisend.getText()) / tihedus / 100;
            DecimalFormat df = new DecimalFormat("#.##");
            String dl = df.format(detsiliitrid);
            String tulemusVormistatuna = dl + " detsiliitrit";
            tulemus.setText(tulemusVormistatuna);
        }
    }
    
    @FXML
    public void processDB(ActionEvent event) throws SQLException {
        
        DatabaseHandler dbh = new DatabaseHandler();
        Connection conn = dbh.DBConn();
        Statement stat = conn.createStatement();


//        String sql = "SELECT * FROM toiduained";
        String sql = "INSERT INTO toiduained (toit, tihedus) VALUES ('piim', 1.03);";
//        $sql = "INSERT INTO users (user_first, user_last, user_email, user_uid, user_pwd) VALUES ('Säde', 'Sepp', 'sade.sepp@email.com', 'sade', 'sade123');";
        ResultSet rs = stat.executeQuery(sql);

//        rs.next();
//        String toiduaine = rs.getString("toit");
//        double tihedus = rs.getDouble("tihedus");
//
//        System.out.println(toiduaine);
//        System.out.println(tihedus);
//        dblabel.setText(tulemusVormistatuna);
    }

//    $conn = mysqli_connect($dbServername, $dbUsername, $dbPassword, $dbName);
//
//    mysqli_query($conn, $sql)
    
    
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valik.setItems(toiduained);
    }
    
    public void valikVäärtus(ActionEvent event) {
        valitud = valik.getValue();
    }
}