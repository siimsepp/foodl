package app;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    
    @FXML
    private TableView<Toiduaine> table;
    @FXML
    private TableColumn<Toiduaine, Integer> id;
    @FXML
    private TableColumn<Toiduaine, String> toit;
    @FXML
    private TableColumn<Toiduaine, Double> tihedus;
    @FXML
    private TextField updateToitTextField = new TextField();
    @FXML
    private TextField updateTihedusTextField = new TextField();
    @FXML
    private TextField insertToitTextField = new TextField();
    @FXML
    private TextField insertTihedusTextField = new TextField();
    @FXML
    private Label muudaVeateade, muudaEduteade, lisaVeateade, lisaEduteade;
    
    
    Model model = new Model();
    
    @Override
    // Algväärtustab tabeli.
    public void initialize(URL url, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Toiduaine, Integer>("id"));
        toit.setCellValueFactory(new PropertyValueFactory<Toiduaine, String>("toit"));
        tihedus.setCellValueFactory(new PropertyValueFactory<Toiduaine, Double>("tihedus"));
        try {
            List<Toiduaine> toiduainedAndmebaasist = model.selectToiduainedMakeObjects(); // List "Toiduaine" objektidega tõmmatakse andmebaasist.
            table.setItems(FXCollections.observableArrayList(toiduainedAndmebaasist));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eemaldaValitudRida(ActionEvent event) {
        Toiduaine valitudToiduaine = table.getSelectionModel().getSelectedItem(); // annab sellel real oleva objekti, mis rida on parajasti valitud
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem()); // eemaldab rea tabeli vaatest
        model.kustutaToiduaine(valitudToiduaine.getId()); // kustutab rea andmebaasist
    }
    
    
    public void lisaRida(ActionEvent event) {
        String insertToit = insertToitTextField.getText();
        try {
            double insertTihedus = Double.parseDouble(insertTihedusTextField.getText());
            model.lisaToiduaine(insertToit, insertTihedus);
            // Kui andmete uuendamine õnnestus, siis kuva vastav teade rohelise värviga tekstina.
            if (model.dbUuendamine.equals("Andmed lisatud")) {
                lisaEduteade.setText(model.dbUuendamine);
                lisaVeateade.setText("");
            } else {
                // Kui andmete uuendamine ei õnnestunud, siis kuva vastav teade punase värviga tekstina.
                lisaVeateade.setText(model.dbUuendamine);
                lisaEduteade.setText("");
            }
            
            
            // Uuendame andmeid tabelis ehk tõmbame andmebaasist uued.
            try {
                List<Toiduaine> toiduainedAndmebaasist = model.selectToiduainedMakeObjects(); // List "Toiduaine" objektidega tõmmatakse andmebaasist.
                table.setItems(FXCollections.observableArrayList(toiduainedAndmebaasist));
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            lisaVeateade.setText("Ei saa andmeid lisada.");
        }
        
        
    }
    
    
    public void muudaAndmeid(ActionEvent event) {
        String updateToit = updateToitTextField.getText();
        String updateTihedus = updateTihedusTextField.getText();
        System.out.println(updateToit);
        System.out.println(updateTihedus);
        double uusTihedus = 0.0;
        
        try {
            uusTihedus = Double.parseDouble(updateTihedus);
        } catch (Exception e) {
            muudaVeateade.setText("Tihedus peab olema ujukomaarv.");
        }
        try {
            model.uuendaToiduaine(updateToit, uusTihedus);
            // Kui andmete uuendamine õnnestus, siis kuva vastav teade rohelise värviga tekstina.
            if (model.dbUuendamine.equals("Andmed uuendatud")) {
                muudaEduteade.setText(model.dbUuendamine);
                muudaVeateade.setText("");
            } else {
                // Kui andmete uuendamine ei õnnestunud, siis kuva vastav teade punase värviga tekstina.
                muudaVeateade.setText(model.dbUuendamine);
                muudaEduteade.setText("");
            }
            
            
            // Uuendame andmeid tabelis ehk tõmbame andmebaasist uued.
            try {
                List<Toiduaine> toiduainedAndmebaasist = model.selectToiduainedMakeObjects(); // List "Toiduaine" objektidega tõmmatakse andmebaasist.
                table.setItems(FXCollections.observableArrayList(toiduainedAndmebaasist));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            muudaVeateade.setText("Ei saa andmeid uuendada.");
        }
    }
    
    public void kustutaRida(ActionEvent event) {
        Toiduaine valitudToiduaine = table.getSelectionModel().getSelectedItem(); // annab sellel real oleva objekti, mis rida on parajasti valitud
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem()); // eemaldab rea tabeli vaatest
        model.kustutaToiduaine(valitudToiduaine.getId()); // kustutab rea andmebaasist
    }
    
    
}
