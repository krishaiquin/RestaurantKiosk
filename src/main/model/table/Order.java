package model.table;

import model.menu.Food;

import java.util.ArrayList;
import java.util.List;

// Represent the order of the table in a restaurant
// INVARIANT: Synchronized order of items between foodList, quantityList and instructionList
public class Order {
    private List<Food> foodList;            //List of food ordered by the table
    private List<Integer> quantityList;     //List of the quantity of food ordered by the table
    private List<String> instructionList;   //List of the instructions on the food ordered by the table

    public Order() {
        foodList = new ArrayList<>();
        quantityList = new ArrayList<>();
        instructionList = new ArrayList<>();
    }

    //getters
    public List<Food> getFoodList() {
        return this.foodList;
    }

    public List<Integer> getQuantityList() {
        return this.quantityList;
    }

    public List<String> getInstructionList() {
        return this.instructionList;
    }

    //REQUIRES: food is not null
    //          quantity > 0
    //MODIFIES: this
    //EFFECTS:  - If food is not yet in the order
    //                - add Food, quantity, special Instructions in the order
    //          - Else if both orders have same instructions
    //                 - add their quantities together
    //          - else
    //                - add food, quantity, special instructions in the order
    public void addOrder(Food food, int quantity, String specialInstructions) {
        if (!foodList.contains(food)) {
            foodList.add(food);
            quantityList.add(quantity);
            instructionList.add(specialInstructions);
        } else {
            int index = foodList.indexOf(food);
            String prevInstruction = instructionList.get(index);

            if (prevInstruction.equals(specialInstructions)) {
                quantityList.set(index, quantityList.get(index) + quantity);
            } else {
                foodList.add(food);
                quantityList.add(quantity);
                instructionList.add(specialInstructions);
            }
        }
    }


    //REQUIRES: food must not be null
    //MODIFIES: this
    //EFFECTS: removes the food from the order
    public void removeOrder(Food food) {
        int index = foodList.indexOf(food);

        foodList.remove(index);
        quantityList.remove(index);
        instructionList.remove(index);

    }

    //REQUIRES: food must not be null
    //          quantity > 0
    //MODIFIES: this
    //EFFECTS: edits the quantity of the given food
    public void editQuantity(Food food, int quantity) {
        int index = foodList.indexOf(food);
        quantityList.set(index, quantity);
    }

    //REQUIRES: food must not be null
    //MODIFIES: this
    //EFFECTS: edits the special instructions of the given food
    public void editInstructions(Food food, String instructions) {
        int index = foodList.indexOf(food);
        instructionList.set(index, instructions);
    }


}
