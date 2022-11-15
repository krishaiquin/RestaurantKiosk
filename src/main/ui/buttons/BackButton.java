package ui.buttons;


import ui.frames.WelcomeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Representation of the Back Button in the application
public class BackButton {

    private JFrame frame;
    private JButton button;
    private Container container;
    private SpringLayout layout;

    //EFFECTS: construct the back button with its parent, container and layout
    public BackButton(JFrame parent, Container container, SpringLayout layout) {
        this.frame = parent;
        this.container = container;
        this.layout = layout;
    }

    //MODIFIES: this
    //EFFECTS: create the back button
    public void createButton() {
        button = new JButton("Back");
        container.add(button);
        layout.putConstraint(SpringLayout.SOUTH, button,
                -20,
                SpringLayout.SOUTH, container);
        layout.putConstraint(SpringLayout.WEST, button,
                340,
                SpringLayout.WEST, container);

        addListener();
    }

    //MODIFIES: this
    //EFFECTS: adds listener to the button
    private void addListener() {
        button.addActionListener(new BackClickHandler());
    }

    //Click Handler for Back button
    private class BackClickHandler implements ActionListener {

        //EFFECTS: returns back to the main page
        @Override
        public void actionPerformed(ActionEvent e) {
            WelcomeFrame welcomeFrame = new WelcomeFrame("Restaurant Manager");
            frame.setVisible(false);
            welcomeFrame.drawFrame();

        }
    }
}
