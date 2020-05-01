package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

public class ArvutaController implements Initializable {
    
    @FXML
    private TextField sisend = new TextField();
    @FXML
    private Label tulemus;
    @FXML
    private ComboBox<String> valik;
    
    private String valitud;
    ObservableList<String> toiduained;
    Model model = new Model();
    
    @FXML
    public void processSisend(ActionEvent event) throws SQLException {
        if (valitud == null) {
            tulemus.setText("Vali toiduaine");
        } else {
            String sql = "SELECT tihedus FROM toiduained WHERE toit = '" + valitud + "'";
            double tihedus = model.selectDensity(sql);
            double detsiliitrid = Double.parseDouble(sisend.getText()) / tihedus / 100;
            DecimalFormat df = new DecimalFormat("#.##");
            String dl = df.format(detsiliitrid);
            String tulemusVormistatuna = dl + " detsiliitrit";
            tulemus.setText(tulemusVormistatuna);
        }
    }
    
    
    @Override
    // algväärtustab comboboxi sisu.
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sql = "SELECT toit FROM toiduained";
        List<String> toiduainedAndmebaasist = null;
        try {
            toiduainedAndmebaasist = model.select(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        toiduained = FXCollections.observableArrayList(toiduainedAndmebaasist);
        valik.setItems(toiduained);
    }
    
    
    // "valitud" on valitud väärtus comboboxis.
    public void valikVäärtus(ActionEvent event) {
        valitud = valik.getValue();
    }
}