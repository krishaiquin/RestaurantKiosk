package ui.buttons;

import ui.frames.RemoveDishFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Representation of Remove Dish Button in the application
public class RemoveDishButton {

    private JComponent container;
    private JButton button;
    private JFrame frame;

    //EFFECTS: constructs Remove Dish button with its parent and container
    public RemoveDishButton(JFrame parent, JComponent container) {
        this.frame = parent;
        this.container = container;
    }

    //MODIFIES: this
    //EFFECTS: creates the remove dish button
    public void createButton() {
        button = new JButton("Remove Dish from the Menu");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(Box.createRigidArea(new Dimension(0,25)));
        container.add(button);
        addListener();
    }

    //MODIFIES: this
    //EFFECTS: adds listener to the remove dish button
    private void addListener() {
        button.addActionListener(new RemoveDishClickHandler());
    }

    //Click Handler for remove dish button
    private class RemoveDishClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: Goes to the Remove Dish Frame when button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            RemoveDishFrame removeDishFrame = new RemoveDishFrame("Remove Dish");
            removeDishFrame.drawFrame();
        }
    }
}
