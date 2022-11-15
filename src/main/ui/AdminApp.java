package ui;


import model.Restaurant;
import model.menu.Food;
import model.menu.FoodCategory;
import model.menu.Menu;
import model.table.Table;
import persistence.JsonReaderMenu;
import persistence.JsonReaderRestaurant;
import persistence.JsonWriterMenu;
import persistence.JsonWriterRestaurant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


//Admin Application of the Admin UI
public class AdminApp {
    private static final String MENU_JSON_FILE = "./data/menu.json";
    private static final String REST_JSON_FILE = "./data/restaurant.json";
    private static final String restaurantName = "La Meza Grill";
    private Scanner input;
    private Menu menu;
    private Restaurant restaurant;
    private JsonWriterMenu jsonWriterMenu;
    private JsonReaderMenu jsonReaderMenu;
    private JsonWriterRestaurant jsonWriterRestaurant;
    private JsonReaderRestaurant jsonReaderRestaurant;


    //EFFECTS: constructs AdminApp and runs application
    public AdminApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        setupMenu();
        setupRestaurant();
        runAdmin();
    }



    //EFFECTS: initializing menu and its associated JSONObjects
    private void setupMenu() {
        menu = new Menu();
        jsonWriterMenu = new JsonWriterMenu(MENU_JSON_FILE);
        jsonReaderMenu = new JsonReaderMenu(MENU_JSON_FILE);
        loadMenu();
    }

    //EFFECTS: initializing restaurant and its associated JSONObjects
    private void setupRestaurant() {
        restaurant = new Restaurant(restaurantName);
        jsonWriterRestaurant = new JsonWriterRestaurant(REST_JSON_FILE);
        jsonReaderRestaurant = new JsonReaderRestaurant(REST_JSON_FILE);
        loadRestaurant();
    }


    //MODIFIES: this
    //EFFECTS: process user input
    private void runAdmin() {
        System.out.println("Welcome to the Restaurant Kiosk Admin Page!");
        System.out.println("\n\nHello, admin!");

        getOptions();


    }

    //EFFECTS: prints out the options to admin
    private void getOptions() {
        String answer = null;
        System.out.println("\nWhat would you like to update today?");
        System.out.println("1. Menu");
        System.out.println("2. Tables");
        System.out.println("3. Quit");
        System.out.print(">>> ");

        answer = input.next();
        processInput(answer);
    }

    //REQUIRES: answer must be one of the options
    private void processInput(String answer) {
        switch (answer) {
            case "1":
                menuOptions();
                break;
            case "2":
                tableOptions();
                break;
            case "3":
                saveInfo();
                System.out.println("Exiting application...");
                break;
            default:
                getOptions();
                break;
        }

    }

    //EFFECTS: displays different actions you can do on menu
    private void menuOptions() {
        String answer = null;

        System.out.println("\nWhat action do you want to do?");
        System.out.println("1. Add dish to the Menu");
        System.out.println("2. Remove dish from the Menu");
        System.out.println("3. Update info on a dish");
        System.out.println("4. Toggle Out of Stock label on a dish");
        System.out.println("5. Back");
        System.out.print(">>> ");

        answer = input.next();

        processMenuInput(answer);
    }

    //REQUIRES: answer must be one of the menu options
    //EFFECTS: processes the admin's input from menu options
    private void processMenuInput(String answer) {
        switch (answer) {
            case "1":
                addThisFood();
                break;
            case "2":
                removeThisFood();
                break;
            case "3":
                updateThisFood();
                break;
            case "4":
                toggleOutOfStock();
                break;
            case "5":
                getOptions();
                break;
            default:
                menuOptions();
                break;

        }
    }




    //EFFECTS: displays the actions available for tables
    private void tableOptions() {
        String answer = "";

        System.out.println("\nWhat action do you want to do?");
        System.out.println("1. Add a table in the restaurant");
        System.out.println("2. Remove a table in the restaurant");
        System.out.println("3. Update info on a table in a restaurant");
        System.out.println("4. Back");
        System.out.print(">>> ");

        answer = input.next();

        processTableInput(answer);
    }

    //REQUIRES: answer must be one of the table options
    //EFFECTS: processes the admin's input from table options
    private void processTableInput(String answer) {
        switch (answer) {
            case "1":
                addTable();
                break;
            case "2":
                removeTable();
                break;
            case "3":
                updateThisTable();
                break;
            case "4":
                getOptions();
                break;
            default:
                tableOptions();
                break;
        }
    }




    //MODIFIES: this
    //EFFECTS: ask the user what table to add and adds it
    private void addTable() {
        String label;
        String capacity;

        printTableList();
        System.out.println("\nPlease enter the following information on table:");
        input.nextLine();
        System.out.println("Table label: ");
        label = input.nextLine();
        System.out.println("Capacity: ");
        capacity = input.nextLine();

        System.out.println(label);
        System.out.println(capacity);

        Table table = new Table(label, Integer.parseInt(capacity));
        restaurant.addTable(table);

        System.out.println("Added!");
        printTableList();

        tableOptions();

    }

    //MODIFIES: this
    //EFFECTS: ask the user which table to remove and removes it
    private void removeTable() {
        String answer;
        printTableList();

        System.out.println("\nWhich table you want to remove?");
        input.nextLine();
        answer = input.nextLine();

        restaurant.removeTable(answer);

        System.out.println("Removed!");

        printTableList();

        tableOptions();
    }

    //EFFECTS: asks the user which table to update and updates it:
    private void updateThisTable() {
        String answer;
        printTableList();

        System.out.println("\nWhich table do you want to update?");
        input.nextLine();
        answer = input.nextLine();

        updateTable(answer);
    }

    //EFFECTS: updates the info on table label
    private void updateTable(String tableLabel) {
        String answer;
        Table table = restaurant.getTable(tableLabel);
        System.out.println("Here are the current information on " + table.getLabel());
        System.out.println("1. Label: " + table.getLabel());
        System.out.println("2. Capacity: " + table.getNumSeats());
        System.out.println("\nEnter the option number to select the info you want to update. Press 0 to go back");
        answer = input.nextLine();

        if (answer.equals("0")) {
            tableOptions();
        }

        String newInfo = getTableNewInfo(answer);
        processUpdateTableInput(table, answer, newInfo);
        tableOptions();



    }

    //MODIFIES: table
    //EFFECTS: sets the info selected by user to the new info provided by user
    private void processUpdateTableInput(Table table, String answer, String newInfo) {
        switch (answer) {
            case "1":
                table.setLabel(newInfo);
                break;
            case "2":
                table.setNumSeats(Integer.parseInt(newInfo));
                break;
            default:
                updateTable(table.getLabel());
        }

        printTableList();

    }

    //EFFECTS: gets new table info from the suer
    private String getTableNewInfo(String answer) {
        String info = "";
        String userAnswer;

        switch (answer) {
            case "1":
                info = "label";
                break;
            case "2":
                info = "capacity";
                break;
            default:
                updateTable(answer);
        }

        System.out.println("New " + info + ":");
        userAnswer = input.nextLine();

        return userAnswer;

    }

    //EFFECTS: prints the list of table in the restaurant:
    private void printTableList() {
        System.out.println("\nList of tables in the restaurant:");
        System.out.println("-----------------------------------\n");
        for (Table table : restaurant.getTableList()) {
            System.out.println(table.getLabel() + ": Sits " + table.getNumSeats() + " people");
        }
    }


    //MODIFIES: this
    //EFFECTS: ask user for what food to add and adds it
    private void addThisFood() {
        String name;
        String description;
        String code;
        String price;
        String category;

        System.out.println(menu.toString());
        System.out.println("\nPlease enter the following information on the dish:");
        input.nextLine();
        System.out.println("Name: ");
        name = input.nextLine();
        System.out.println("Description: ");
        description = input.nextLine();
        System.out.println("Code: ");
        code = input.nextLine();
        System.out.println("Price: ");
        price = input.nextLine();
        System.out.println("Category: ");
        category = input.nextLine();


        addFood(name, description, code, Double.parseDouble(price), FoodCategory.valueOf(category.toUpperCase()));

        System.out.println("\n" + name + " has been added to the menu!");
        System.out.println(menu.toString());

        menuOptions();

    }

    //REQUIRES: name is not null/zero-length
    //          code is not null/zero-length
    //MODIFIES: this
    //EFFECTS: adds the food the menu
    private void addFood(String name, String description, String code, double price, FoodCategory category) {
        Food food = new Food(name, description, code, price, category);
        menu.add(food);
    }

    //MODIFIES: this
    //EFFECTS: ask the user which food to remove and removes it
    private void removeThisFood() {
        String code;
        System.out.println(menu.toString());

        System.out.println("Please use the code to select the dish you want to remove (e.g A1)");
        input.nextLine();
        code = input.nextLine();

        menu.remove(code);
        System.out.println("It has been removed!\n");
        System.out.println(menu.toString());
        menuOptions();
    }


    //MODIFIES: this
    //EFFECTS: ask the user which food to update and updates it
    private void updateThisFood() {
        String answer;
        System.out.println(menu.toString());

        System.out.println("Please use the code to select the dish you want to update (e.g A1)");
        input.nextLine();
        answer = input.nextLine();

        updateFood(answer);

    }

    //REQUIRES: code exists in menu and not null
    //MODIFIES: this
    //EFFECTS: updates the food
    private void updateFood(String code) {
        String answer;
        Food food = menu.getFood(code);

        System.out.println("Here's the current info on " + food.getName());
        System.out.println("1. Name: " + food.getName());
        System.out.println("2. Description: " + food.getDescription());
        System.out.println("3. Code: " + food.getCode());
        System.out.println("4. Price: " + food.getPrice());
        System.out.println("5. Category: " + food.getFoodCategory());

        System.out.println("Enter the option number to select the info you want to update. Press 0 to go back");
        answer = input.nextLine();

        if (answer.equals("0")) {
            menuOptions();
        }

        String newInfo = getFoodNewInfo(answer);
        processUpdateFoodInput(food, answer, newInfo);
        menuOptions();

    }

    //EFFECTS: process the user selection
    private void processUpdateFoodInput(Food food, String answer, String newInfo) {

        switch (answer) {
            case "1":
                food.setName(newInfo);
                break;
            case "2":
                food.setDescription(newInfo);
                break;
            case "3":
                food.setCode(newInfo);
                break;
            case "4":
                food.setPrice(Double.parseDouble(newInfo));
                break;
            case "5":
                food.setFoodCategory(FoodCategory.valueOf(newInfo.toUpperCase()));
                break;
            default:
                updateFood(food.getCode());
                break;
        }

        System.out.println(menu.toString());

    }

    //EFFECTS: returns the new info about the food
    private String getFoodNewInfo(String answer) {
        String info = "";
        String userAnswer;

        switch (answer) {
            case "1":
                info = "name";
                break;
            case "2":
                info = "description";
                break;
            case "3":
                info = "code";
                break;
            case "4":
                info = "price";
                break;
            case "5":
                info = "category";
                break;
        }

        System.out.println("New " + info + ":");
        userAnswer = input.nextLine();

        return userAnswer;
    }

    //MODIFIES: this
    //EFFECTS: ask the user what dish they want to mark as out of stock
    private void toggleOutOfStock() {
        String code;
        System.out.println(menu.toString());

        System.out.println("Please use the code to select the dish you want to switch its availability (e.g A1)");
        input.nextLine();
        code = input.nextLine();
        Food food = menu.getFood(code);

        if (food.isAvailable()) {
            food.isNowOutOfStock();
        } else {
            food.isNowAvailable();
        }

        System.out.println(menu.toString());
        menuOptions();

    }

    //EFFECTS: saves info on menu and restaurant
    private void saveInfo() {
        saveMenu();
        saveRestaurant();
    }

    //EFFECTS: saves the menu to file
    private void saveMenu() {
        try {
            jsonWriterMenu.open();
            jsonWriterMenu.write(menu);
            jsonWriterMenu.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to write to file: " + MENU_JSON_FILE);
        }
    }

    //EFFECTS: saves the restaurant to file
    private void saveRestaurant() {
        try {
            jsonWriterRestaurant.open();
            jsonWriterRestaurant.write(restaurant);
            jsonWriterRestaurant.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to write to file: " + REST_JSON_FILE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the menu from the file
    private void loadMenu() {
        File file = new File(MENU_JSON_FILE);
        try {
            if (file.length() == 0) {
                saveMenu();
            }
            menu = jsonReaderMenu.read();
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
                saveRestaurant();
            }
            restaurant = jsonReaderRestaurant.read();
        } catch (IOException e) {
            System.err.println("Unable to read from file " + REST_JSON_FILE);
        }
    }

}
