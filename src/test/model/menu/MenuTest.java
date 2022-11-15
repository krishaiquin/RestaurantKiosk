package model.menu;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    private Menu testMenu;
    private Food food1;
    private Food food2;
    private Food food3;
    private Food food4;
    private Food food5;


    @BeforeEach
    public void setup() {
        testMenu = new Menu();
        food1 = new Food("Lumpiang Shanghai", "Ground pork and vegetables spring roll", "A1",
                8.50, FoodCategory.APPETIZER);
        food2 = new Food("Longsilog", "Sweet pork sausages with eggs and garlic  rice", "B3",
                11.50, FoodCategory.BREAKFAST);
        food3 = new Food("Longsilog", "Flame grilled lemongrass milkfish", "G6",
                8.50, FoodCategory.GRILLED);
        food4 = new Food("Pork Sisig", "Diced pork marinated in soya and calamansi", "A1",
                8.50, FoodCategory.SIZZLING);
        food5 = new Food("Lechon sisig", "diced pork lechon belly marinated in soya and calamansi",
                "S3",13.95,FoodCategory.SIZZLING);

    }

    @Test
    public void testConstructor() {
        assertEquals(new LinkedList<Food>(), testMenu.getFoodList());
        assertEquals(0, testMenu.getFoodList().size());
    }

    @Test
    public void testAddOneMultipleTimes() {
        assertTrue(testMenu.add(food1));
        assertFalse(testMenu.add(food1));
        assertTrue(testMenu.add(food2));
        assertFalse(testMenu.add(food2));

        assertEquals(2, testMenu.length());
        assertEquals(food1, testMenu.getFoodList().get(0));
        assertEquals(food2, testMenu.getFoodList().get(1));

    }

    @Test
    public void testAddOnce() {
        assertTrue(testMenu.add(food1));
        assertTrue(testMenu.add(food2));

        assertEquals(2, testMenu.length());
        assertEquals(food1, testMenu.getFoodList().get(0));
        assertEquals(food2, testMenu.getFoodList().get(1));

    }

    @Test
    public void testAddSameName() {
        assertTrue(testMenu.add(food2));
        assertFalse(testMenu.add(food3));

        assertEquals(1, testMenu.length());
        assertEquals(food2, testMenu.getFoodList().get(0));
    }

    @Test
    public void testAddSameCode() {
        assertTrue(testMenu.add(food1));
        assertFalse(testMenu.add(food4));

        assertEquals(1, testMenu.length());
        assertEquals(food1, testMenu.getFoodList().get(0));

    }

    @Test
    public void testRemoveCodeExists() {
        testMenu.add(food1);
        testMenu.add(food2);

        testMenu.remove("B3");

        assertFalse(testMenu.getFoodList().contains(food2));
        assertEquals(1,testMenu.length());

    }

    @Test
    public void testRemoveCodeNotExists() {
        testMenu.add(food3);
        testMenu.add(food4);

        testMenu.remove("S3");


        assertFalse(testMenu.getFoodList().contains(food5));
        assertEquals(2,testMenu.length());
    }

    @Test
    public void testToString() {
        testMenu.add(food1);
        String expected = "";
        String content = "---------";
        String header = "APPETIZER\n";
        content += "\n";
        content += "A1 Lumpiang Shanghai\t8.5";
        content += "\n\tGround pork and vegetables spring roll\n";

        expected += header + content + "\n";

        assertEquals(expected, testMenu.toString());

    }

    @Test
    public void testToStringWithOOS() {
        food1.isNowOutOfStock();
        testMenu.add(food1);
        String expected = "";
        String content = "---------";
        String header = "APPETIZER\n";
        content += "\n";
        content += "A1 Lumpiang Shanghai [OUT OF STOCK] \t8.5";
        content += "\n\tGround pork and vegetables spring roll\n";

        expected += header + content + "\n";

        assertEquals(expected, testMenu.toString());
    }


    @Test
    public void testToJson() {
        testMenu.add(food1);
        testMenu.add(food5);
        JSONObject json = testMenu.toJson();

        assertEquals(2, json.getJSONArray("foodList").length());
    }
}
