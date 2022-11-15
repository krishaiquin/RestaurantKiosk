package persistence;

import model.menu.Food;
import model.menu.FoodCategory;
import model.menu.Menu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Majority of methods were taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//Represents a reader that reads JSON representation of menu from file
public class JsonReaderMenu {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReaderMenu(String source) {
        this.source = source;
    }

    //EFFECTS: reads menu from file and returns it
    //         throws IOException if an error occurs reading data from file
    public Menu read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMenu(jsonObject);
    }

    //EFFECTS: reads source file as string and returns
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses menu from JSON object and returns it
    private Menu parseMenu(JSONObject jsonObject) {
        Menu menu = new Menu();
        addFoods(menu, jsonObject);
        return menu;
    }

    //MODIFIES: menu
    //EFFECTS: parses list of food from JSON object and adds them to the menu
    private void addFoods(Menu menu, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foodList");

        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(menu, nextFood);

        }
    }

    //MODIFIES: menu
    //EFFECTS: parses food from JSONobject and adds it to the menu
    private void addFood(Menu menu, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        String code = jsonObject.getString("code");
        double price = jsonObject.getDouble("price");
        FoodCategory category = FoodCategory.valueOf(jsonObject.getString("category"));
        boolean isAvailable = jsonObject.getBoolean("availability");

        Food food = new Food(name,description,code,price, category);

        if (isAvailable) {
            food.isNowAvailable();
        } else {
            food.isNowOutOfStock();
        }

        menu.add(food);
    }
}
