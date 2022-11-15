package ui.buttons;

import ui.frames.RemoveDishFrame;
import model.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//Representation of the Remove Button in the application
public class RemoveButton {

    private RemoveDishFrame frame;
    private JButton button;
    private Container container;
    private SpringLayout layout;

    //EFFECTS: constructs the remove button with its parents, container, layout
    public RemoveButton(RemoveDishFrame parent, Container container, SpringLayout layout) {
        this.frame = parent;
        this.container = container;
        this.layout = layout;
    }

    //MODIFIES: this
    //EFFECTS: creates the remove button
    public void createButton() {
        button = new JButton("Remove");
        container.add(button);
        layout.putConstraint(SpringLayout.SOUTH, button,
                -20,
                SpringLayout.SOUTH, container);
        layout.putConstraint(SpringLayout.WEST, button,
                25,
                SpringLayout.WEST, container);

        addListener();
    }

    //MODIFIES: this
    //EFFECTS: adds listener to the remove button
    private void addListener() {
        button.addActionListener(new RemoveButtonClickHandler());
    }

    //Click handler for remove button
    private class RemoveButtonClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: Removes all the user-selected dishes from the menu
        @Override
        public void actionPerformed(ActionEvent e) {
            Menu menu = frame.getMenu();
            List<String> foodCodes = frame.getFoodCodes();

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(frame,"Selected dish(es) have been removed!");

            for (String foodCode : foodCodes) {
                menu.remove(foodCode);
            }

        }
    }
}
