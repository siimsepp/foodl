package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbController implements Initializable {
    
    public Model dbModel = new Model();
    
    @FXML
    private Label isConnected;
    
    @FXML
    private Button nupp;
    
    public void dbKontroll(ActionEvent event) throws SQLException {
        
        dbModel.selectAll();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (dbModel.isDbConnected()) {
            isConnected.setText("Andmebaas on ühendatud");
        } else {
            isConnected.setText("Andmebaas ei ole ühendatud");
        }
    }
}
