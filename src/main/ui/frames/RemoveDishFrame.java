package ui.frames;

import model.menu.Food;
import ui.buttons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

//Representation of the Remove Dish page in the application
public class RemoveDishFrame extends Frame implements ItemListener {

    private Container contentPane;
    private SpringLayout layout;
    private List<String> foodCodes = new ArrayList<>();

    //EFFECTS: constructs the remove dish page with a title
    public RemoveDishFrame(String title) {
        super(title);
    }

    //MODIFIES: this
    //EFFECTS: compiles all the components and add it to the container of the frame
    @Override
    protected void drawPanel() {
        contentPane = getContentPane();
        layout = new SpringLayout();
        contentPane.setLayout(layout);

        JLabel instructions = new JLabel("Load the menu to see the current data");
        contentPane.add(instructions);
        layoutComponents(5, 10, instructions);

        createButtons();

    }

    //EFFECTS: creates all the needed buttons in the frame
    private void createButtons() {
        RemoveButton removeButton = new RemoveButton(this,contentPane,layout);
        removeButton.createButton();
        LoadButton loadButton = new LoadButton(this, contentPane, layout);
        loadButton.createButton();
        SaveButton saveButton = new SaveButton(this,contentPane,layout);
        saveButton.createButton();
        BackButton backButton = new BackButton(this, contentPane, layout);
        backButton.createButton();
    }

    //MODIFIES: this
    //EFFECTS: display all the menu components
    public void drawMenuContents() {
        JLabel header = new JLabel("Please select the dish you want to remove");
        contentPane.add(header);
        layoutComponents(5, 30, header);

        int verticalGap = 50;
        for (Food food : this.menu.getFoodList()) {
            JCheckBox checkBox = new JCheckBox(food.getCode() + " " + food.getName());
            contentPane.add(checkBox);
            layoutComponents(5, verticalGap, checkBox);
            checkBox.addItemListener(this);
            verticalGap += 30;
        }
    }

    //EFFECTS: lay out all the components nicely in the frame
    private void layoutComponents(int x, int y, JComponent component) {

        layout.putConstraint(SpringLayout.WEST, component,
                x,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, component,
                y,
                SpringLayout.NORTH, contentPane);


    }

    //EFFECTS: returns the list of food codes that the user wants to remove
    public List<String> getFoodCodes() {
        return this.foodCodes;
    }

    //EFFECTS: records user's dish selection to remove
    @Override
    public void itemStateChanged(ItemEvent e) {


        if (e.getStateChange() == ItemEvent.SELECTED) {
            String checkboxText =  ((JCheckBox)e.getItemSelectable()).getText();
            String foodCode = checkboxText.split(" ")[0];
            foodCodes.add(foodCode);
        }

        if (e.getStateChange() == ItemEvent.DESELECTED) {
            String checkboxText =  ((JCheckBox)e.getItemSelectable()).getText();
            String foodCode = checkboxText.split(" ")[0];
            foodCodes.remove(foodCode);
        }

    }
}
