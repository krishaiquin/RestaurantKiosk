package model.table;

import model.menu.Food;
import model.menu.FoodCategory;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {
    Table testTable;


    @BeforeEach
    public void setup() {
        testTable = new Table("Table 1", 6);
    }

    @Test
    public void testConstructor() {
        assertEquals("Table 1", testTable.getLabel());
        assertEquals(6, testTable.getNumSeats());
        //assertEquals(Collections.emptyList(),testTable.getOrder());
    }

    @Test
    public void testSetters() {
        Food food1 = new Food("Lumpiang Shanghai", "Ground pork and vegetables spring roll", "A1",
                8.50, FoodCategory.APPETIZER);
        Order order = new Order();
        order.addOrder(food1,1,"");

        testTable.setLabel("Table 3");
        testTable.setNumSeats(2);
        testTable.setOrder(order);

        assertEquals("Table 3", testTable.getLabel());
        assertEquals(2,testTable.getNumSeats());
        assertEquals(order, testTable.getOrder());
    }


    @Test
    public void testOccupiedAndAvailable() {
        assertFalse(testTable.isOccupied());
        testTable.nowOccupied();
        assertTrue(testTable.isOccupied());
        testTable.nowAvailable();
        assertFalse(testTable.isOccupied());
    }

    @Test
    public void testEqualityTrue() {
        Table table1 = new Table("Table 1", 2);

        //diff table obj
        assertTrue(testTable.equals(table1));

        //same table obj
        assertTrue(testTable.equals(testTable));
    }

    @Test
    public void testEqualityFalse() {
        Table table1 = new Table("Table 2", 2);

        assertFalse(testTable.equals(table1));
    }

    @Test
    public void testEqualityDiffObj() {
        String test = "some string";

        assertFalse(testTable.equals(test));
    }

    @Test
    public void testEqualityObjNull() {
        assertFalse(testTable.equals(null));
    }

    @Test
    public void testToJson() {
        JSONObject json = testTable.toJson();

        assertEquals(testTable.getLabel(), json.get("label"));
        assertEquals(testTable.getNumSeats(), json.get("capacity"));
    }

}
