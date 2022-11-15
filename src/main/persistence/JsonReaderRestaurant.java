package persistence;

import model.Restaurant;
import model.table.Table;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Majority of methods were taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//Represents a reader that reads JSON representation of table list of restaurant from file
public class JsonReaderRestaurant {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReaderRestaurant(String source) {
        this.source = source;
    }

    //EFFECTS: reads menu from file and returns it
    //         throws IOException if an error occurs reading data from file
    public Restaurant read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRestaurant(jsonObject);
    }


    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses restaurant from JSON object and returns it
    private Restaurant parseRestaurant(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Restaurant restaurant = new Restaurant(name);
        addTables(restaurant, jsonObject);

        return restaurant;
    }

    //MODIFIES: restaurant
    //EFFECTS: parses tables from JSON object and adds them to restaurant
    private void addTables(Restaurant restaurant, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tableList");
        for (Object json : jsonArray) {
            JSONObject nextTable = (JSONObject) json;
            addTable(restaurant, nextTable);
        }
    }

    //MODIFIES: restaurant
    //EFFECTS: parses table from JSON object and adds it to restaurant
    private void addTable(Restaurant restaurant, JSONObject nextTable) {
        String label = nextTable.getString("label");
        int capacity = nextTable.getInt("capacity");

        Table table = new Table(label, capacity);
        restaurant.addTable(table);
    }

}
