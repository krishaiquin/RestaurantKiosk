package ui;


import ui.frames.Frame;
import ui.frames.WelcomeFrame;


public class RestaurantManagerGUI {

    public static void main(String[] args) {
        Frame welcome = new WelcomeFrame("Restaurant Manager");
        welcome.drawFrame();

    }

}
