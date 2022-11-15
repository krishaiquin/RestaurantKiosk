package persistence;

import model.menu.Food;
import model.menu.FoodCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Test was taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonMenuTest {
    protected void checkFood(String name, String description, String code, double price, FoodCategory category,
                             boolean isAvailable, Food food) {
        assertEquals(name, food.getName());
        assertEquals(description, food.getDescription());
        assertEquals(code, food.getCode());
        assertEquals(price, food.getPrice());
        assertEquals(category, food.getFoodCategory());
        assertEquals(isAvailable,food.isAvailable());

    }
}
