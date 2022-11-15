package model.menu;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    private Food testFood;

    @BeforeEach
    public void setup() {
        testFood = new Food("Lumpiang Shanghai", "Ground pork and vegetable spring roll","A1",
                8.50, FoodCategory.APPETIZER);
    }

    @Test
    public void testConstructor() {
        assertEquals("Lumpiang Shanghai", testFood.getName());
        assertEquals("Ground pork and vegetable spring roll", testFood.getDescription());
        assertEquals("A1", testFood.getCode());
        assertEquals(8.5, testFood.getPrice());
        assertEquals(FoodCategory.APPETIZER, testFood.getFoodCategory());
        assertTrue(testFood.isAvailable());
    }

    @Test
    public void testSetters() {
        testFood.setName("Fresh Lumpia");
        testFood.setDescription("Random Description");
        testFood.setCode("A6");
        testFood.setPrice(12.00);
        testFood.setFoodCategory(FoodCategory.BREAKFAST);

        assertEquals("Fresh Lumpia", testFood.getName());
        assertEquals("Random Description", testFood.getDescription());
        assertEquals("A6", testFood.getCode());
        assertEquals(12.00,testFood.getPrice());
        assertEquals(FoodCategory.BREAKFAST, testFood.getFoodCategory());
    }

    @Test
    public void testIsNowOutOfStock() {
        testFood.isNowOutOfStock();
        assertFalse(testFood.isAvailable());
    }

    @Test
    public void testIsNowAvailable() {
        testFood.isNowOutOfStock();
        assertFalse(testFood.isAvailable());
        testFood.isNowAvailable();
        assertTrue(testFood.isAvailable());
    }

    @Test
    public void testEqualityTrueFromName() {
        Food food1 = new Food("Shanghai", "Some description here", "A1",
                11.50, FoodCategory.APPETIZER);
        Food food2 = new Food("Shanghai", "Some description here", "A2",
                4.50, FoodCategory.GRILLED);

        assertTrue(food1.equals(food2));
    }

    @Test
    public void testEqualityTrueFromCode() {
        Food food1 = new Food("Longanissa", "Some description here", "A1",
                11.50, FoodCategory.APPETIZER);
        Food food2 = new Food("Shanghai", "Some description here", "A1",
                4.50, FoodCategory.GRILLED);

        assertTrue(food1.equals(food2));
    }

    @Test
    public void testEqualityFalse() {
        Food food1 = new Food("Longanissa", "Some description here", "B1",
                11.50, FoodCategory.APPETIZER);
        Food food2 = new Food("Shanghai", "Some description here", "A1",
                4.50, FoodCategory.GRILLED);

        assertFalse(food1.equals(food2));
    }

    @Test
    public void testEqualityFalseDiffObj() {
        Food food1 = new Food("Longanissa", "Some description here", "B1",
                11.50, FoodCategory.APPETIZER);
        String test = "a test";

        assertFalse(food1.equals(test));
    }

    @Test
    public void testEqualityObjNull() {
        assertFalse(testFood.equals(null));
    }


    @Test
    public void testEqualityTrueSameObj() {
        Food food1 = new Food("Longanissa", "Some description here", "B1",
                11.50, FoodCategory.APPETIZER);

        assertTrue(food1.equals(food1));

    }

    @Test
    public void testToJson() {
        JSONObject jsonObject = testFood.toJson();
        assertEquals(testFood.getName(), jsonObject.getString("name"));
        assertEquals(testFood.getDescription(), jsonObject.getString("description"));
        assertEquals(testFood.getCode(), jsonObject.getString("code"));
        assertEquals(testFood.getPrice(), jsonObject.getDouble("price"));
        assertEquals(testFood.getFoodCategory(),jsonObject.get("category"));
        assertEquals(testFood.isAvailable(), jsonObject.getBoolean("availability"));
    }



}
