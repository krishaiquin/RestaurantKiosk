package model.menu;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;



// Represents a food with name, description, code, price and category on the menu
public class Food implements Writable {
    private String name;                //Name of the food on the menu
    private String description;         //Description of the food on the menu
    private String code;                //Code of the food on the menu
    private double price;               //Price of the food on the menu
    private FoodCategory foodCategory;  //Category of the food on the menu
    private boolean isAvailable;       //State of availability of the food


    //EFFECTS: construct a new food with name, description, price and category
    public Food(String name, String description, String code, double price, FoodCategory foodCategory) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.price = price;
        this.foodCategory = foodCategory;
        this.isAvailable = true;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFoodCategory(FoodCategory category) {
        this.foodCategory = category;
    }

    //getters
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCode() {
        return this.code;
    }

    public double getPrice() {
        return this.price;
    }

    public FoodCategory getFoodCategory() {
        return this.foodCategory;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }


    //MODIFIES: this
    //EFFECTS: marks the food as out of stock
    public void isNowOutOfStock() {
        this.isAvailable = false;
    }

    //MODIFIES: this
    //EFFECTS: marks the food as available
    public void isNowAvailable() {
        this.isAvailable = true;
    }


    //EFFECTS: returns JSON representation of food
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("description",this.description);
        json.put("code", this.code);
        json.put("price", this.price);
        json.put("category", this.foodCategory);
        json.put("availability", this.isAvailable);
        return json;
    }

    //EFFECTS: returns true if foods' names or codes are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return name.equals(food.name) || code.equals(food.code);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, code);
//    }
}
