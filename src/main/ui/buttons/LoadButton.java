package ui.buttons;


import ui.frames.Frame;
import ui.frames.RemoveDishFrame;
import ui.frames.ViewMenuFrame;
import ui.utilities.MenuReader;
import model.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Representation of the Load Button in the application
public class LoadButton {
    private JFrame frame;
    private Container container;
    private SpringLayout layout;
    private Menu menu;
    private JButton button;
    private JLabel label;

    //EFFECTS: constructs the load button with its parent, container and layout
    public LoadButton(JFrame parent, Container container, SpringLayout layout) {
        this.frame = parent;
        this.container = container;
        this.layout = layout;
        menu = new Menu();
        label = new JLabel("");
    }

    //MODIFIES: this
    //EFFECTS: create the load button
    public void createButton() {
        button = new JButton("Load Menu");
        container.add(button);
        layout.putConstraint(SpringLayout.SOUTH, button,
                -20,
                SpringLayout.SOUTH, container);
        layout.putConstraint(SpringLayout.WEST, button,
                120,
                SpringLayout.WEST, container);

        addListener();
    }

    //MODIFIES: this
    //EFFECTS: adds listener to the load button
    private void addListener() {
        button.addActionListener(new LoadClickHandler());
    }

    //Click handler for load button
    private class LoadClickHandler implements ActionListener {

        //EFFECTS: loads the menu from json file and prints it ( for RemoveDishFrame and ViewMenuFrame )
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuReader menuReader = new MenuReader();
            menu = menuReader.getMenu();
            ((Frame)frame).setMenu(menu);

            JOptionPane.showMessageDialog(frame, "Menu has been loaded!");
            Toolkit.getDefaultToolkit().beep();

            container.add(label);
            layout.putConstraint(SpringLayout.NORTH, label,
                    100,
                    SpringLayout.NORTH, container);
            layout.putConstraint(SpringLayout.EAST, label,
                    -70,
                    SpringLayout.EAST, container);



            if (frame.getClass().toString().equals("class ui.frames.RemoveDishFrame")) {
                ((RemoveDishFrame)frame).drawMenuContents();
            }

            if (frame.getClass().toString().equals("class ui.frames.ViewMenuFrame")) {
                ((ViewMenuFrame)frame).drawMenuContents();
            }

            frame.setVisible(true);

        }

    }
}



