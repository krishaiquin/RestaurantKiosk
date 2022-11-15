package ui.buttons;


import ui.frames.Frame;
import ui.utilities.MenuWriter;
import model.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Representation of Save Button in the application
public class SaveButton {
    private JButton button;
    private JFrame frame;
    private Container container;
    private SpringLayout layout;

    //EFFECTS: constructs the save button in the application
    public SaveButton(JFrame parent, Container container, SpringLayout layout) {
        this.frame = parent;
        this.container = container;
        this.layout = layout;

    }

    //MODIFIES: this
    //EFFECTS: creates the save button
    public void createButton() {
        button = new JButton("Save Menu");
        container.add(button);
        layout.putConstraint(SpringLayout.SOUTH, button,
                -20,
                SpringLayout.SOUTH, container);
        layout.putConstraint(SpringLayout.WEST, button,
                230,
                SpringLayout.WEST, container);

        addListener();
    }

    //MODIFIES: this
    //EFFECTS: adds listener to the save button
    private void addListener() {
        button.addActionListener(new LoadClickHandler());
    }

    //Click handler for load button
    private class LoadClickHandler implements ActionListener {

        //EFFECTS: Save the menu in a json file
        @Override
        public void actionPerformed(ActionEvent e) {
            Menu menu = ((Frame)frame).getMenu();
            MenuWriter menuWriter = new MenuWriter(menu);
            menuWriter.saveMenu();

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(frame, "Menu has been saved!");

        }
    }
}
