package ui.buttons;

import ui.frames.ViewMenuFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Representation of the view menu button in the application
public class ViewMenuButton {

    private JComponent container;
    private JButton button;
    private JFrame frame;

    //EFFECTS: constructs the view menu button with its parent and container
    public ViewMenuButton(JFrame parent, JComponent container) {
        this.frame = parent;
        this.container = container;
    }

    //MODIFIES: this
    //EFFECTS: creates the view menu button
    public void createButton() {
        button = new JButton("View Menu");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(Box.createRigidArea(new Dimension(0,30)));
        container.add(button);
        addListener();
    }

    //MODIFIES: this
    //EFFECTS: adds listener to the view menu button
    private void addListener() {
        button.addActionListener(new ViewMenuClickHandler());
    }

    //Click handler for view menu button
    private class ViewMenuClickHandler implements ActionListener {

        //EFFECTS: goes the the view menu frame when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            ViewMenuFrame viewMenuFrame = new ViewMenuFrame("View Menu");
            viewMenuFrame.drawFrame();
        }
    }
}
