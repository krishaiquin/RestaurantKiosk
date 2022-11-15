package ui.utilities;

import model.menu.Menu;
import persistence.JsonReaderMenu;

import java.io.File;
import java.io.IOException;

//Representation of a reader that reads the menu
public class MenuReader {

    private static final String MENU_JSON_FILE = "./data/menu.json";
    private JsonReaderMenu jsonReaderMenu;
    private Menu menu;

    //EFFECTS: constructs the reader with a menu
    public MenuReader() {
        jsonReaderMenu = new JsonReaderMenu(MENU_JSON_FILE);
        menu = new Menu();
    }

    //MODIFIES: this
    //EFFECTS: loads the menu from the file
    private void loadMenu() {
        File file = new File(MENU_JSON_FILE);
        try {
            if (file.length() == 0) {
                System.err.println("Menu is empty. Stopping the program...");
                System.exit(0);
            } else {
                menu = jsonReaderMenu.read();
            }

        } catch (IOException e) {
            System.err.println("Unable to read from file: " + MENU_JSON_FILE);
        }
    }

    //EFFECTS: returns the menu
    public Menu getMenu() {
        loadMenu();
        return this.menu;
    }
}
