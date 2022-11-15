package ui.buttons;


import ui.frames.AddDishFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Representation of the Add Dish button in the application
public class AddDishButton {

    private JComponent container;
    private JButton button;
    private JFrame frame;

    //EFFECTS: constructs add dish button with its parent, and container
    public AddDishButton(JFrame parent, JComponent container) {
        this.frame = parent;
        this.container = container;

    }

    //MODIFIES: this
    //EFFECTS: create the add dish button
    public void createButton() {
        button = new JButton("Add Dish to the Menu");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(Box.createRigidArea(new Dimension(0,20)));
        container.add(button);
        addListener();
    }

    //MODIFIES: this
    //EFFECTS: adds listener to the add dish button
    private void addListener() {
        button.addActionListener(new AddDishClickHandler());
    }

    //Click Handler for add dish button
    private class AddDishClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: Goes to the Add Dish Frame when add dish button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            AddDishFrame addDishFrame = new AddDishFrame("Add Dish");
            addDishFrame.drawFrame();
        }

    }
}
