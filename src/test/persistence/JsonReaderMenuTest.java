package persistence;

import model.menu.Food;
import model.menu.FoodCategory;
import model.menu.Menu;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests were taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderMenuTest extends JsonMenuTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderMenu reader = new JsonReaderMenu("./data/notExist.json");
        try {
            Menu menu = reader.read();
            fail("Should have thrown IOException");
        } catch (IOException e) {
            //do nothing
        }

    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReaderMenu reader = new JsonReaderMenu("./data/menu/testReaderEmptyMenu.json");
        try {
            Menu menu = reader.read();
            assertEquals(0,menu.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMenu() {
        JsonReaderMenu reader = new JsonReaderMenu("./data/menu/testReaderGeneralMenu.json");

        try {
            Menu menu = reader.read();
            List<Food> foodList = menu.getFoodList();
            assertEquals(2, menu.length());
            checkFood("Lumpiang Shanghai","ground pork and vegetables spring roll", "A1",
                    8.5, FoodCategory.APPETIZER, true, foodList.get(0));
            checkFood("Liempo","Grilled pork belly", "G4",
                    12.5, FoodCategory.GRILLED, false, foodList.get(1));
        } catch (IOException e) {
            fail("Couldn't read the file");
        }
    }
}
