package model.menu;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represent the menu of the restaurant
public class Menu implements Writable {
    private List<Food> menu; //List of food of the restaurant

    //EFFECTS: construct a new menu with empty list
    public Menu() {
        this.menu = new ArrayList<>();
    }

    //getters
    public List<Food> getFoodList() {
        return this.menu;
    }

    //REQUIRES: code must be non-zero length, exists in the menu, and not null
    //EFFECTS: returns the food via its name/code, returns null if not found
    public Food getFood(String code) {

        for (Food food : menu) {
            if (code.equalsIgnoreCase(food.getCode())) {
                return food;
            }
        }

        return null;
    }

    //REQUIRES: food must not be null
    //MODIFIES: this
    //EFFECTS: If food doesn't exist in the menu && name and code doesn't exist as well
    //            - add it to the menu
    //            - return true
    //        otherwise, false
    public boolean add(Food food) {

        if (!this.menu.contains(food)) {
            this.menu.add(food);
            return true;
        }

        return false;
    }


    //REQUIRES: code must be non-zero length, and not null
    //MODIFIES: this
    //EFFECTS: if food found in menu
    //            - remove from menu
    //        otherwise, do nothing
    public void remove(String code) {
        Food found = getFood(code);
        if (found != null) {
            this.menu.remove(found);
        }

    }

    //EFFECTS: returns a string that contains the menu contents
    public String toString() {
        String menuContent = "";

        for (FoodCategory category: FoodCategory.values()) {
            int count = 0;
            String content = "";
            String header = category.name() + "\n";
            for (int i = 0; i < category.name().length(); i++) {
                content += "-";
            }
            content += "\n";
            for (Food food : this.menu) {
                if (category == food.getFoodCategory()) {
                    content += food.getCode() + " " + food.getName();
                    content += displayAvailability(food);
                    content += "\t" + food.getPrice();

                    content += "\n\t" + food.getDescription() + "\n";
                    count++;
                }
            }
            if (count > 0) {
                menuContent += header + content + "\n";
            }

        }

        return menuContent;
    }

    //EFFECTS: marks the food if it is out of stock, else return empty string
    private String displayAvailability(Food food) {
        if (!food.isAvailable()) {
            return " [OUT OF STOCK] ";
        }
        return "";
    }

    //EFFECTS: returns the length of the menu
    public int length() {
        return getFoodList().size();
    }


    //EFFECTS: returns JSON representation of Menu
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("foodList", foodToJson());
        return json;
    }

    //EFFECTS: returns the food in this menu as a JSON array
    private JSONArray foodToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food food : menu) {
            jsonArray.put(food.toJson());
        }

        return jsonArray;
    }



}
