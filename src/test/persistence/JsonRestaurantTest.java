package persistence;


import model.table.Table;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Test was taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonRestaurantTest {
    protected void checkTable(String label, int capacity, Table table) {
        assertEquals(label, table.getLabel());
        assertEquals(capacity, table.getNumSeats());
    }
}
