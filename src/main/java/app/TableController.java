package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    
    Model model = new Model();
    
    
    public ObservableList<Toiduaine> queryToiduained() throws SQLException {
        String sql = "SELECT * FROM toiduained";
        List<Toiduaine> toiduainedAndmebaasist = model.selectToiduainedMakeObjects(sql);
        return FXCollections.observableArrayList(toiduainedAndmebaasist);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Toiduaine, Integer>("id"));
        toit.setCellValueFactory(new PropertyValueFactory<Toiduaine, String>("toit"));
        tihedus.setCellValueFactory(new PropertyValueFactory<Toiduaine, Double>("tihedus"));
        try {
            table.setItems(queryToiduained());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}
