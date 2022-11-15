package ui.frames;


import ui.buttons.AddDishButton;
import ui.buttons.RemoveDishButton;
import ui.buttons.ViewMenuButton;

import javax.swing.*;
import java.awt.*;

//Representation of the Welcome page in the application
public class WelcomeFrame extends Frame {

    //EFFECTS: construct the Frame with a title
    public WelcomeFrame(String title) {
        super(title);

    }

    //EFFECTS: compiles the all the components and add it to the container of the frame
    protected void drawPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("How can I help you today?");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        AddDishButton addButton = new AddDishButton(this, contentPanel);
        RemoveDishButton removeDishButton = new RemoveDishButton(this, contentPanel);
        ViewMenuButton viewMenuButton = new ViewMenuButton(this,contentPanel);

        contentPanel.add(Box.createRigidArea(new Dimension(0,30)));
        contentPanel.add(label);
        addButton.createButton();
        removeDishButton.createButton();
        viewMenuButton.createButton();

        Container container = getContentPane();
        container.add(contentPanel);
    }



}
