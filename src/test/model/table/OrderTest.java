package model.table;

import model.menu.Food;
import model.menu.FoodCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    Order testOrder;
    Food food1;
    Food food2;
    Food food3;
    Food food4;

    @BeforeEach
    public void setup() {
        testOrder = new Order();
        food1 = new Food("Lumpiang Shanghai", "Ground pork and vegetables spring roll", "A1",
                8.50, FoodCategory.APPETIZER);
        food2 = new Food("Longsilog", "Sweet pork sausages with eggs and garlic  rice", "B3",
                11.50, FoodCategory.BREAKFAST);
        food3 = new Food("Calamares", "Fried breaded squid served with garlic sauce", "A3",
                10.95, FoodCategory.APPETIZER);
        food4 = new Food("Kaldereta", "Beef shank, potatoes, carrots in tomato stew", "F6",
                10.95, FoodCategory.BEEF);
    }

    @Test
    public void testConstructor() {
        assertEquals(new LinkedList<Food>(), testOrder.getFoodList());
        assertEquals(new LinkedList<Integer>(), testOrder.getQuantityList());
        assertEquals(new LinkedList<String>(), testOrder.getInstructionList());

    }

    @Test
    public void testAddOrderOnce() {
        testOrder.addOrder(food1, 1, "");
        testOrder.addOrder(food2, 5, "Over easy eggs pls");

        // check if getting right values
        assertEquals(food1,testOrder.getFoodList().get(0));
        assertEquals(1,testOrder.getQuantityList().get(0));
        assertEquals("",testOrder.getInstructionList().get(0));

        assertEquals(food2,testOrder.getFoodList().get(1));
        assertEquals(5,testOrder.getQuantityList().get(1));
        assertEquals("Over easy eggs pls",testOrder.getInstructionList().get(1));

        //check if sizes are correct too
        assertEquals(2,testOrder.getFoodList().size());
        assertEquals(2,testOrder.getQuantityList().size());
        assertEquals(2,testOrder.getInstructionList().size());
    }

    @Test
    public void testAddOrderMultiplesWithDiffInstructions() {
        testOrder.addOrder(food2,3,"Over Easy eggs pls");
        testOrder.addOrder(food2, 1, "");

        //check if getting right values
        assertEquals(food2, testOrder.getFoodList().get(0));
        assertEquals(3, testOrder.getQuantityList().get(0));
        assertEquals("Over Easy eggs pls", testOrder.getInstructionList().get(0));

        assertEquals(food2,testOrder.getFoodList().get(1));
        assertEquals(1, testOrder.getQuantityList().get(1));
        assertEquals("",testOrder.getInstructionList().get(1));

        //check if sizes are correct too
        assertEquals(2,testOrder.getFoodList().size());
        assertEquals(2,testOrder.getQuantityList().size());
        assertEquals(2,testOrder.getInstructionList().size());

    }

    @Test
    public void testAddOrderMultiplesWithSameInstructions() {
        testOrder.addOrder(food1, 5, "No green onions");
        testOrder.addOrder(food1, 1, "No green onions");

        //check if getting right values
        assertEquals(food1, testOrder.getFoodList().get(0));
        assertEquals(6, testOrder.getQuantityList().get(0));
        assertEquals("No green onions", testOrder.getInstructionList().get(0));


        //check if sizes are correct too
        assertEquals(1,testOrder.getFoodList().size());
        assertEquals(1,testOrder.getQuantityList().size());
        assertEquals(1,testOrder.getInstructionList().size());
    }


    @Test
    public void testRemoveOrder() {
        testOrder.addOrder(food1, 1, "More sauce!");
        testOrder.addOrder(food2, 1, "Over easy eggs pls");

        testOrder.removeOrder(food1);

        assertFalse(testOrder.getFoodList().contains(food1));
        assertEquals(food2, testOrder.getFoodList().get(0));
        assertEquals(1,testOrder.getFoodList().size());
        assertEquals(1,testOrder.getQuantityList().size());
        assertEquals(1,testOrder.getInstructionList().size());
    }

    @Test
    public void testEditQuantity() {
        testOrder.addOrder(food1, 1, "");
        testOrder.addOrder(food2, 1, "");

        testOrder.editQuantity(food1, 5);

        assertEquals(5,testOrder.getQuantityList().get(0));
    }

    @Test
    public void testEditInstructions() {
        testOrder.addOrder(food1, 6, "");
        testOrder.addOrder(food2, 2, "");

        testOrder.editInstructions(food2, "with plain rice");

        assertEquals("with plain rice", testOrder.getInstructionList().get(1));
    }



}
