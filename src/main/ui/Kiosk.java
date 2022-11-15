package ui;

import model.Restaurant;
import model.menu.Food;
import model.menu.FoodCategory;
import model.table.Table;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//Kiosk application of the restaurant
public class Kiosk {

    private Restaurant restaurant; //Restaurant where the kiosk is operating
    private Scanner input;

    public Kiosk(Restaurant restaurant) {
        input = new Scanner(System.in);
        this.restaurant = restaurant;
    }


    //EFFECTS: Gets the number of guests that will be sitting in a table
    public void greetGuests() {
        String answer = "";
        System.out.println("Welcome to " + restaurant.getName() + "!\n");
        System.out.println("How many are you dining in with us today?");
        answer = input.nextLine();

        presentTables(Integer.valueOf(answer));

    }

    //REQUIRES: numOfGuests > 0
    //EFFECTS: presents available tables based of the number of guests
    private void presentTables(int numOfGuests) {
        List<Table> tableList = new LinkedList<>();
        String answer;
        int count = 0;

        for (Table table : restaurant.getTableList()) {
            if (numOfGuests <= table.getNumSeats() && !table.isOccupied()) {
                tableList.add(table);
                count++;
            }
        }

        if (count > 0) {
            System.out.println("Here are the available tables: ");
            for (Table table : tableList) {
                System.out.println(table.getLabel() + " : Sits " + table.getNumSeats() + " people");
            }
            System.out.println("\nWhere would you like to sit?");
            answer = input.nextLine();
            assignTable(answer.toLowerCase());
        } else {
            System.out.println("Sorry, there are no available tables at the moment! Please come back some other time.");
        }

    }

    //REQUIRES: tableLabel must exist in the restaurant
    //MODIFIES: this
    //EFFECTS: assigns table to the guests
    private void assignTable(String tableLabel) {
        String answer = "";
        Table table = restaurant.getTable(tableLabel);
        table.nowOccupied();

        while (!answer.equalsIgnoreCase("y")) {
            System.out.println("Perfect! Please follow the illuminated path to your table "
                    + ". Press \'Y/y\' once you are seated.");
            answer = input.nextLine();
        }

        presentMenu(table);
    }

    //REQUIRES: table is not null
    //EFFECTS: shows the menu to the table and getting ready to take the order
    private void presentMenu(Table table) {
        System.out.println("\nHere's the menu:" + "\n");
        System.out.println(restaurant.getMenu().toString());
        takeOrder(table);
    }


    //REQUIRES: table is not null
    //EFFECTS: takes the order of the table
    private void takeOrder(Table table) {
        String answer = "y";
        String foodCode = "";
        String quantity = "";
        String instructions = "";
        System.out.println("What can I get for you?\n");

        while (answer.equalsIgnoreCase("y")) {
            System.out.println("To select the dish, please use the code (e.g A1)");
            foodCode = input.nextLine();
            foodCode = foodCode.toUpperCase();
            Food food = restaurant.getMenu().getFood(foodCode);
            System.out.println("How many?");
            quantity = input.nextLine();
            int numOrder = Integer.parseInt(quantity);
            System.out.println("Special instructions? Press enter if you have none");
            instructions = input.nextLine();

            addOrder(table, food, numOrder, instructions);
            System.out.println("Order added!");

            System.out.println("Anything else? (y/n)");
            answer = input.nextLine();
        }
        reviewOrder(table);
        options(table);
    }

    //REQUIRES: table is not null
    //          food is not null
    //          numOrder > 0
    //MODIFIES: this
    //EFFECTS: adds order for the table
    private void addOrder(Table table, Food food, int numOrder, String instructions) {
        table.getOrder().addOrder(food, numOrder, instructions);
    }

    //REQUIRES: table is not null
    //EFFECTS: asks guests what to do next
    private void options(Table table) {
        String answer = "";
        System.out.println("Would you like to: ");
        System.out.println("1. Send your order to the kitchen ");
        System.out.println("2. Add dish to your order");
        System.out.println("3. Remove a dish to your order");
        System.out.println("4. Edit quantity of a dish");
        System.out.println("5. Edit instructions of a dish");
        answer = input.nextLine();

        decide(answer, table);
    }

    //REQUIRES: answer must be 1 - 5 and table is not null
    //EFFECTS: Takes action based on guests answer
    private void decide(String answer, Table table) {

        switch (answer) {
            case "1":
                sendOrder(table);
                break;
            case "2":
                presentMenu(table);
                break;
            case "3":
                askRemoveDish(table);
                break;
            case "4":
                askQuantity(table);
                break;
            case "5":
                askInstructions(table);
                break;
            default:
                System.out.println("Guest picked non-existing option. Closing...");
                break;
        }

    }

    //REQUIRES: table is not null
    //EFFECTS: finalizes the order
    private void sendOrder(Table table) {
        String answer = "";
        System.out.println("\nThe following order has been sent to the kitchen!");
        reviewOrder(table);

        while (!answer.equalsIgnoreCase("y")) {
            System.out.println("Press \'Y/y\' once you're ready to see your bill");
            answer = input.nextLine();
        }

        showBill(table);
    }

    //REQUIRES: table is not null
    //EFFECTS: prints the bill for the guests
    private void showBill(Table table) {
        int size = table.getOrder().getFoodList().size();
        double totalBill = 0.0;
        String billContents  = "";
        System.out.println("\nHere's your bill:");
        System.out.println("-------------------");

        for (int i = 0; i < size; i++) {
            Food food = table.getOrder().getFoodList().get(i);
            double price = food.getPrice();
            int quantity = table.getOrder().getQuantityList().get(i);
            double total = price * quantity;
            totalBill += total;

            billContents += quantity + " x " + food.getName() + "\t" + String.format("%.02f", total) + "\n";
        }

        System.out.println(billContents);
        System.out.println("-------------------");
        System.out.println("Total: \t" + String.format("%.02f", totalBill));

        System.out.println("\nA server is on their way to hand you the debit/credit machine.");
        System.out.println("\nThank you for dining in with us today! Have a great day!");
        table.nowAvailable();

    }

    //REQUIRES: table is not null
    //MODIFIES: this
    //EFFECTS: ask the quantity of the dish guests wants to update and updates it
    private void askQuantity(Table table) {

        String index = "";
        String answer = "";
        reviewOrder(table);

        System.out.println("Please use the option numbers to select the dish you want to edit the quantity in "
                + "your order.");
        index = input.nextLine();
        Food food = table.getOrder().getFoodList().get(Integer.parseInt(index) - 1);
        int quantity = table.getOrder().getQuantityList().get(Integer.parseInt(index) - 1);

        System.out.println("Editing: " + food.getName());
        System.out.println("Current quantity: " + quantity);
        System.out.println("New quantity: ");
        answer = input.nextLine();

        updateQuantity(table, food,Integer.parseInt(answer));
        System.out.println("Quantity of " + food.getName() + "has been updated!");

        reviewOrder(table);
        options(table);
    }

    //REQUIRES: table is not null
    //          food is not null
    //          newQuantity > 0
    //MODIFIES: this
    //EFFECTS: updates the quantity of the specified dish
    private void updateQuantity(Table table, Food food, int newQuantity) {
        table.getOrder().editQuantity(food, newQuantity);
    }


    //REQUIRES: table is not null
    //MODIFIES: this
    //EFFECTS: ask for the instructions of the food the guests want to update and updates it
    private void askInstructions(Table table) {
        String index = "";
        String answer = "";
        reviewOrder(table);

        System.out.println("Please use the option numbers to select the dish you want to edit the "
                + "instructions in your order. ");
        index = input.nextLine();
        Food food = table.getOrder().getFoodList().get(Integer.parseInt(index) - 1);
        String instructions = table.getOrder().getInstructionList().get(Integer.parseInt(index) - 1);

        System.out.println("Editing: " + food.getName());
        System.out.println("Current special instructions: " + instructions);
        System.out.println("New special instructions: ");
        answer = input.nextLine();

        updateInstructions(table, food, answer);
        System.out.println("Instructions of " + food.getName() + "has been updated!");

        reviewOrder(table);
        options(table);
    }

    //REQUIRES: table is not null
    //          food is not null
    //MODIFIES: this
    //EFFECTS: updates the instructions for the food selected by guests
    private void updateInstructions(Table table, Food food, String instructions) {
        table.getOrder().editInstructions(food, instructions);
    }

    //REQUIRES: table is not null
    //MODIFIES: this
    //EFFECTS: asks which dish to remove and removes it
    private void askRemoveDish(Table table) {
        String index = "";
        reviewOrder(table);

        System.out.println("Please use the option numbers to select the dish you want to remove from your order.");
        index = input.nextLine();
        Food food = table.getOrder().getFoodList().get(Integer.parseInt(index) - 1);

        removeDish(table, food);
        System.out.println(food.getName() + "has been removed!");

        reviewOrder(table);
        options(table);
    }

    //REQUIRES: table is not null
    //          food is not null
    //MODIFIES: this
    //EFFECTS: removes the dish selected by the guest
    private void removeDish(Table table, Food food) {
        table.getOrder().removeOrder(food);
    }

    //REQUIRES: table is not null
    //EFFECTS: prints the current order
    private void reviewOrder(Table table) {
        int size = table.getOrder().getFoodList().size();
        System.out.println("\nReview order:");
        System.out.println("-------------");
        for (int i = 0; i < size; i++) {
            System.out.println(new StringBuilder().append(String.valueOf(i + 1) + ". ")
                    .append(table.getOrder().getQuantityList().get(i)).append(" x ")
                    .append(table.getOrder().getFoodList().get(i).getName())
                    .append(" (")
                    .append(table.getOrder().getFoodList().get(i).getCode())
                    .append(") ")
                    .append("\n\tSpecial Instructions: ")
                    .append(table.getOrder().getInstructionList().get(i)));
        }
        System.out.println("\n");
    }

}
