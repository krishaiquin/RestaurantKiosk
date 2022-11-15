package model;

import model.menu.Food;
import model.menu.Menu;
import model.table.Table;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


//Represents a restaurant with name
public class Restaurant implements Writable {
    String name;             //Name of the restaurant
    List<Table> tableList;   //List of the tables in the restaurant
    Menu menu;               //Menu of the restaurant

    public Restaurant(String name) {
        this.name = name;
        this.tableList = new ArrayList<>();
        this.menu = new Menu();
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    //getters
    public String getName() {
        return this.name;
    }

    public List<Table> getTableList() {
        return this.tableList;
    }

    public Menu getMenu() {
        return this.menu;
    }

    //REQUIRES: table is not null
    //MODIFIES: this
    //EFFECTS: If table doesn't exist and label doesn't exist as well
    //            - add table to list
    //            - return true
    //         otherwise, return false
    public boolean addTable(Table table) {
        if (!this.tableList.contains(table)) {
            tableList.add(table);
            return true;
        }
        return false;
    }


    //MODIFIES: this
    //EFFECTS: If table exist in the list:
    //            - remove from the list
    //         otherwise, do nothing
    public void removeTable(String label) {
        label = label.toLowerCase();
        Table table = getTable(label);
        if (table != null) {
            getTableList().remove(table);
        }
    }

    //REQUIRES: label must be non-zero length, and must exist in the menu
    //EFFECTS: returns the table via table label
    public Table getTable(String label) {

        for (Table table : this.getTableList()) {
            if (table.getLabel().equalsIgnoreCase(label)) {
                return table;
            }
        }

        return null;
    }


    //EFFECTS: returns JSON representation of restaurant
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("tableList", foodToJson());
        return json;
    }

    //EFFECTS: returns the food in this menu as a JSON array
    private JSONArray foodToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Table table : tableList) {
            jsonArray.put(table.toJson());
        }

        return jsonArray;
    }




}
