package ui.buttons;

import model.menu.Food;
import model.menu.FoodCategory;
import model.menu.Menu;
import ui.frames.AddDishFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Representation of Add button in the application
public class AddButton {

    private AddDishFrame frame;
    private JButton button;
    private Container container;
    private SpringLayout layout;
    private JLabel label = new JLabel();

    //EFFECTS: constructs the add button with its parent, container, layout
    public AddButton(AddDishFrame parent, Container container, SpringLayout layout) {
        this.frame = parent;
        this.container = container;
        this.layout = layout;
    }

    //MODIFIES: this
    //EFFECTS: creates the add button
    public void createButton() {
        button = new JButton("Add");
        container.add(button);
        layout.putConstraint(SpringLayout.SOUTH, button,
                -20,
                SpringLayout.SOUTH, container);
        layout.putConstraint(SpringLayout.WEST, button,
                50,
                SpringLayout.WEST, container);

        addListener();
    }

    //MODIFIES: this
    //EFFECTS: add listener to the add button
    private void addListener() {
        button.addActionListener(new AddClickHandler());
    }

    //Click Handler of the Add buttons
    private class AddClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: adds the dish to the menu when add button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            Menu menu = frame.getMenu();
            String name = frame.getNameTextField();
            String desc = frame.getDescTextField();
            String code = frame.getCodeTextField();
            String price = frame.getPriceTextField();
            String category = frame.getCategoryTextField().toUpperCase();

            Food food = new Food(name,desc,code,Double.parseDouble(price), FoodCategory.valueOf(category));
            menu.add(food);

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(frame, name + " has been added!");
            container.add(label);
            layout.putConstraint(SpringLayout.NORTH, label,
                    100,
                    SpringLayout.NORTH, container);
            layout.putConstraint(SpringLayout.EAST, label,
                    -70,
                    SpringLayout.EAST, container);

            frame.clearTextFields();
        }
    }
}
