package ui.utilities;

import model.menu.Menu;
import persistence.JsonWriterMenu;

import java.io.FileNotFoundException;

//Representation of the writer that writes onto the menu
public class MenuWriter {
    private static final String MENU_JSON_FILE = "./data/menu.json";
    private JsonWriterMenu jsonWriterMenu;
    private Menu menu;

    //EFFECTS: constructs the writer with a menu
    public MenuWriter(Menu menu) {
        jsonWriterMenu = new JsonWriterMenu(MENU_JSON_FILE);
        this.menu = menu;
    }

    //EFFECTS: saves the menu to file
    public void saveMenu() {
        try {
            jsonWriterMenu.open();
            jsonWriterMenu.write(menu);
            jsonWriterMenu.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to write to file: " + MENU_JSON_FILE);
        }
    }
}
