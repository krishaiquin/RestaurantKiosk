package persistence;

import model.menu.Menu;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Majority of methods were taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//Represents a writer that writes JSON representation of menu to file
public class JsonWriterMenu {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write to destination file
    public JsonWriterMenu(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    //         be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of the menu to file
    public void write(Menu menu) {
        JSONObject json = menu.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    //MODIFIES:
    //EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }

}
