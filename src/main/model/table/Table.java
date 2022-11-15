package model.table;

import model.menu.Food;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

//Represent a table with label and number of seats in the restaurant
public class Table implements Writable {
    private String label;       //Label of the table of the restaurant
    private int numSeats;       //Number of seats of the table in the restaurant
    private Order order;        //Order of the table in the restaurant
    private boolean isOccupied; //State of the occupancy of the table

    public Table(String label, int numSeats) {
        this.label = label;
        this.numSeats = numSeats;
        this.order = new Order();
        this.isOccupied = false;
    }

    //setters
    public void setLabel(String label) {
        this.label = label;
    }

    public void setNumSeats(int num) {
        this.numSeats = num;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    //getters
    public String getLabel() {
        return this.label;
    }

    public int getNumSeats() {
        return this.numSeats;
    }

    public Order getOrder() {
        return this.order;
    }

    //MODIFIES: this
    //EFFECTS: sets occupancy to true;
    public void nowOccupied() {
        this.isOccupied = true;
    }

    //MODIFIES: this
    //EFFECTS: sets occupancy to false
    public void nowAvailable() {
        this.isOccupied = false;
    }

    //EFFECTS: returns status of occupancy of table
    public boolean isOccupied() {
        return this.isOccupied;
    }

    //TODO: add reviewOrder method and bill method here

    //EFFECTS: returns true if tables' labels are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Table table = (Table) o;
        return label.equals(table.label);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(label);
//    }

    //EFFECTS: returns the JSON representation of table
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("label", this.label);
        json.put("capacity",this.numSeats);

        return json;
    }
}
