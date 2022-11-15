package ui;

import model.Restaurant;
import model.menu.Food;
import model.menu.FoodCategory;
import model.menu.Menu;
import model.table.Table;
import persistence.JsonReaderMenu;
import persistence.JsonReaderRestaurant;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RestaurantKiosk {

    private Restaurant restaurant; //Restaurant where the kiosk is operating
    private static final String MENU_JSON_FILE = "./data/menu.json";
    private static final String REST_JSON_FILE = "./data/restaurant.json";
    private JsonReaderRestaurant jsonReaderRestaurant;
    private JsonReaderMenu jsonReaderMenu;


    public static void main(String[] args) {
        RestaurantKiosk restaurantKiosk = new RestaurantKiosk();
        Kiosk kiosk = new Kiosk(restaurantKiosk.getRestaurant());

        kiosk.greetGuests();
    }

    // Kiosk of the restaurant
    public RestaurantKiosk() {
        jsonReaderRestaurant = new JsonReaderRestaurant(REST_JSON_FILE);
        jsonReaderMenu = new JsonReaderMenu(MENU_JSON_FILE);
        this.restaurant = new Restaurant("La Meza Grill");
        loadRestaurant();
        loadMenu();

    }

    //getter
    //EFFECTS: returns the restaurant
    public Restaurant getRestaurant() {
        return this.restaurant;
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
                Menu menu = jsonReaderMenu.read();
                restaurant.setMenu(menu);
            }

        } catch (IOException e) {
            System.err.println("Unable to read from file: " + MENU_JSON_FILE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the restaurant from the file
    private void loadRestaurant() {
        File file = new File(REST_JSON_FILE);
        try {
            if (file.length() == 0) {
                System.err.println("Table list is empty. Stopping the program...");
                System.exit(0);
            } else {
                restaurant = jsonReaderRestaurant.read();
            }

        } catch (IOException e) {
            System.err.println("Unable to read from file " + REST_JSON_FILE);
        }
    }



}


