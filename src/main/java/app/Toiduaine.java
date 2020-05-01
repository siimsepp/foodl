package app;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

// Klassi kasutatakse, et teha toiduaine objekt.
public class Toiduaine {
    
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty toit;
    private final SimpleDoubleProperty tihedus;
    
    public Toiduaine(Integer id, String toit, Double tihedus) {
        this.id = new SimpleIntegerProperty(id);
        this.toit = new SimpleStringProperty(toit);
        this.tihedus = new SimpleDoubleProperty(tihedus);
    }
    
    public Integer getId() {
        return id.get();
    }
    
    public String getToit() {
        return toit.get();
    }
    
    public Double getTihedus() {
        return tihedus.get();
    }
}
