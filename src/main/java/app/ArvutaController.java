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

public class ArvutaController implements Initializable {
    
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valik.setItems(toiduained);
    }
    
    public void valikVäärtus(ActionEvent event) {
        valitud = valik.getValue();
    }
}