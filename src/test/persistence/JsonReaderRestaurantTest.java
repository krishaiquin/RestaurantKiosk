package persistence;

import model.Restaurant;
import model.table.Table;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests were taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderRestaurantTest extends JsonRestaurantTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderRestaurant reader = new JsonReaderRestaurant("./data/noFile.json");
        try {
            Restaurant restaurant = reader.read();
            fail("Should have thrown IOException e");
        } catch (IOException e) {
            //do nothing;
        }
    }

    @Test
    void testReaderEmptyRestaurant() {
        JsonReaderRestaurant reader = new JsonReaderRestaurant("./data/restaurant/testReaderEmptyRestaurant.json");
        try {
            Restaurant restaurant = reader.read();
            assertEquals("My Restaurant", restaurant.getName());
            assertEquals(0, restaurant.getTableList().size());
        } catch (IOException e) {
            fail("Should not have thrown IOException");
        }
    }

    @Test
    void testReaderGeneralRestaurant() {
        JsonReaderRestaurant reader = new JsonReaderRestaurant("./data/restaurant/testReaderGeneralRestaurant.json");

        try {
            Restaurant restaurant = reader.read();
            assertEquals("My Restaurant", restaurant.getName());
            List<Table> tableList = restaurant.getTableList();
            assertEquals(2,tableList.size());
            checkTable("Table 2", 4, tableList.get(0));
            checkTable("Table 3", 6, tableList.get(1));
        } catch (IOException e) {
            fail("Should not have thrown IOException");
        }
    }
}
