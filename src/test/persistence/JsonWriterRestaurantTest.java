package persistence;

import model.Restaurant;
import model.table.Table;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests were taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterRestaurantTest extends JsonRestaurantTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Restaurant restaurant = new Restaurant("My Restaurant");
            JsonWriterRestaurant writer = new JsonWriterRestaurant("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Should have thrown IOException");
        } catch (IOException e) {
            //do nothing
        }
    }

    @Test
    void testWriterEmptyRestaurant() {
        try {
            Restaurant restaurant = new Restaurant("My Restaurant");
            JsonWriterRestaurant writer = new JsonWriterRestaurant("./data/restaurant/testWriterEmptyRestaurant.json");
            writer.open();
            writer.write(restaurant);
            writer.close();

            JsonReaderRestaurant reader = new JsonReaderRestaurant("./data/restaurant/testWriterEmptyRestaurant.json");
            restaurant = reader.read();
            assertEquals("My Restaurant", restaurant.getName());
            assertEquals(0, restaurant.getTableList().size());

        } catch (IOException e) {
            fail("Should not have thrown IOException");
        }

    }

    @Test
    void testWriterGeneralRestaurant() {
        try {
            Restaurant restaurant = new Restaurant("My Restaurant");
            Table table1 = new Table("Table 1", 2);
            Table table2 = new Table("Table 2", 4);
            restaurant.addTable(table1);
            restaurant.addTable(table2);
            JsonWriterRestaurant writer = new JsonWriterRestaurant("./data/restaurant/testWriterGeneralRestaurant.json");
            writer.open();
            writer.write(restaurant);
            writer.close();

            JsonReaderRestaurant reader = new JsonReaderRestaurant("./data/restaurant/testWriterGeneralRestaurant.json");
            restaurant = reader.read();
            assertEquals("My Restaurant", restaurant.getName());
            List<Table> tableList = restaurant.getTableList();
            assertEquals(2, tableList.size());
            checkTable("Table 1", 2, tableList.get(0));
            checkTable("Table 2", 4, tableList.get(1));
        } catch (IOException e) {
            fail("Should not have thrown IOException");
        }
    }
}