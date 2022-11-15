package ui.frames;

import model.menu.Menu;

import javax.swing.*;
import java.awt.*;

//Superclass of all the Frames in the application
public abstract class Frame extends JFrame {
    protected static final int WIDTH = 500;
    protected static final int HEIGHT = 300;
    protected Menu menu;


    public Frame(String title) {
        super(title);
    }

    //REQUIRES: menu is not null
    //MODIFIES: this
    //EFFECTS: sets this.menu to the given menu
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    //EFFECTS: returns the menu
    public Menu getMenu() {
        return this.menu;
    }

    //EFFECTS: draws the frame of the window
    public void drawFrame() {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawPanel();
        pack();
        setVisible(true);
    }

    //EFFECTS: draws the unique panel for different frames
    protected abstract void drawPanel();



}
