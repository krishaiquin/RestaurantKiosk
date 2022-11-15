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
public class JsonWriterMenuTest extends JsonMenuTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Menu menu = new Menu();
            JsonWriterMenu writer = new JsonWriterMenu("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Should have thrown IOException was expected");
        } catch (IOException e) {
            //do nothing
        }
    }

    @Test
    void testWriterEmptyMenu() {
        try {
            Menu menu = new Menu();
            JsonWriterMenu writer = new JsonWriterMenu("./data/menu/testWriterEmptyMenu.json");
            writer.open();
            writer.write(menu);
            writer.close();

            JsonReaderMenu reader = new JsonReaderMenu("./data/menu/testWriterEmptyMenu.json");
            menu = reader.read();
            assertEquals(0, menu.length());
        } catch (IOException e) {
            fail("Not expecting IOException. Failing test!");
        }
    }

    @Test
    void testWriterGeneralMenu() {
        try {
            Menu menu = new Menu();
            Food shanghai = new Food("Lumpiang Shanghai", "ground pork and vegetables spring roll",
                    "A1", 8.50, FoodCategory.APPETIZER);
            Food ensalada = new Food("Ensaladang Talong", "grilled eggplant, salted duck egg and tomato "
                    + "salsa served with shrimp paste", "A2", 7.50,FoodCategory.APPETIZER);
            ensalada.isNowOutOfStock();
            menu.add(shanghai);
            menu.add(ensalada);
            JsonWriterMenu writer = new JsonWriterMenu("./data/menu/testWriterGeneralMenu.json");
            writer.open();
            writer.write(menu);
            writer.close();

            JsonReaderMenu reader = new JsonReaderMenu("./data/menu/testWriterGeneralMenu.json");
            menu = reader.read();
            List<Food> foodList = menu.getFoodList();
            assertEquals(2,foodList.size());
            checkFood("Lumpiang Shanghai","ground pork and vegetables spring roll",
                    "A1", 8.50, FoodCategory.APPETIZER, true, foodList.get(0));
            checkFood("Ensaladang Talong","grilled eggplant, salted duck egg and tomato "
                            + "salsa served with shrimp paste", "A2", 7.50,
                    FoodCategory.APPETIZER, false, foodList.get(1));
        } catch (IOException e) {
            fail("Not expecting IOException here");
        }
    }
}
