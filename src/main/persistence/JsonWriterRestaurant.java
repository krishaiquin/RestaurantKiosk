package persistence;

import model.Restaurant;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Majority of methods were taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//Represents a writer that writes JSON representation of table list of the restaurant to file
public class JsonWriterRestaurant {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write to destination file
    public JsonWriterRestaurant(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer
    //         throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of the table list to file
    public void write(Restaurant restaurant) {
        JSONObject json = restaurant.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: closes the writer
    public  void close() {
        writer.close();
    }

    //EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
