package model;

import model.menu.Food;
import model.menu.FoodCategory;
import model.menu.Menu;
import model.table.Table;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant testRestaurant;


    @BeforeEach
    public void setup() {
        testRestaurant = new Restaurant("My Restaurant");
    }

    @Test
    public void testConstructor() {
        assertEquals("My Restaurant", testRestaurant.getName());
        assertEquals(new LinkedList<Table>(),testRestaurant.getTableList());

    }

    @Test
    public void testSetName() {
        testRestaurant.setName("Our Restaurant");
        assertEquals("Our Restaurant", testRestaurant.getName());
    }

    @Test
    public void testSetMenu() {
        Menu testMenu = new Menu();
        Food food1 = new Food("Lumpiang Shanghai", "Ground pork and vegetables spring roll", "A1",
                8.50, FoodCategory.APPETIZER);
        Food food2 = new Food("Longsilog", "Sweet pork sausages with eggs and garlic  rice", "B3",
                11.50, FoodCategory.BREAKFAST);
        Food food3 = new Food("Leche Flan", "","D3",12.00,FoodCategory.DESSERT);
        Food food4 = new Food("Pinakbet","Mixed vegetables, diced pork and shrimp sauteed in shrimp " +
                "paste", "V4",10.95, FoodCategory.VEGETABLE);

        testMenu.add(food1);
        testMenu.add(food2);
        testMenu.add(food3);
        testMenu.add(food4);

        testRestaurant.setMenu(testMenu);

        assertEquals(testMenu,testRestaurant.getMenu());
    }

    @Test
    public void testAddTableNoDuplicateLabel() {
        Table table1 = new Table("Table 1", 2);
        Table table2 = new Table("Table 2", 4);
        Table table3 = new Table("Table 3", 4);

        assertTrue(testRestaurant.addTable(table1));
        assertTrue(testRestaurant.addTable(table2));
        assertTrue(testRestaurant.addTable(table3));

        assertEquals(table1,testRestaurant.getTableList().get(0));
        assertEquals(table2,testRestaurant.getTableList().get(1));
        assertEquals(table3,testRestaurant.getTableList().get(2));

        assertEquals(3, testRestaurant.getTableList().size());

    }

    @Test
    public void testAddTableDuplicateLabel() {
        Table table1 = new Table("Table 1", 2);
        Table table2 = new Table("Table 1", 5);

        assertTrue(testRestaurant.addTable(table1));
        assertFalse(testRestaurant.addTable(table2));

        assertEquals(table1,testRestaurant.getTableList().get(0));

        assertEquals(1,testRestaurant.getTableList().size());
    }

    @Test
    public void testAddTableAlreadyExist() {
        Table table1 = new Table("Table 1", 6);

        assertTrue(testRestaurant.addTable(table1));
        assertFalse(testRestaurant.addTable(table1));

        assertEquals(1,testRestaurant.getTableList().size());
    }


    @Test
    public void testRemoveTableFound() {
        Table table1 = new Table("Table 1", 2);
        Table table2 = new Table("Table 2", 4);
        Table table3 = new Table("Table 3", 4);

        testRestaurant.addTable(table1);
        testRestaurant.addTable(table2);
        testRestaurant.addTable(table3);

        testRestaurant.removeTable("Table 1");

        assertFalse(testRestaurant.getTableList().contains(table1));
        assertEquals(2, testRestaurant.getTableList().size());

    }

    @Test
    public void testRemoveTableNotFound() {
        Table table1 = new Table("Table 1", 2);
        Table table2 = new Table("Table 2", 6);

        testRestaurant.addTable(table1);
        testRestaurant.addTable(table2);

        testRestaurant.removeTable("Table 3");

        assertEquals(2, testRestaurant.getTableList().size());

    }

    @Test
    public void testGetTableFound() {
        Table table1 = new Table("Table 1", 2);
        Table table2 = new Table("Table 2", 4);
        Table table3 = new Table("Table 3", 4);

        testRestaurant.addTable(table1);
        testRestaurant.addTable(table2);
        testRestaurant.addTable(table3);

        assertEquals(table2, testRestaurant.getTable("Table 2"));

    }

    @Test
    public void testGetTableNotFound() {
        Table table1 = new Table("Table 1", 2);
        Table table2 = new Table("Table 2", 8);


        testRestaurant.addTable(table1);
        testRestaurant.addTable(table2);

        assertNull(testRestaurant.getTable("Table 3"));

    }

    @Test
    public void testToJson() {
        Table table1 = new Table("Table 1", 2);
        testRestaurant.addTable(table1);
        JSONObject json = testRestaurant.toJson();

        assertEquals("My Restaurant", json.get("name"));
        assertEquals(1, json.getJSONArray("tableList").length());

    }
}