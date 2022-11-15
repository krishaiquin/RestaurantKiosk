package ui;


import java.io.FileNotFoundException;

public class AdminAppUI {

    public static void main(String[] args) {
        try {
            new AdminApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }
}
