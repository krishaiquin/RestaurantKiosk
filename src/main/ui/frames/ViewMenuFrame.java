package ui.frames;


import ui.buttons.BackButton;
import ui.buttons.LoadButton;


import javax.swing.*;
import java.awt.*;

//Representation of the View Menu page in the application
public class ViewMenuFrame extends Frame {

    private Container contentPane;
    private SpringLayout layout;

    //EFFECTS: constructs the view menu with a title
    public ViewMenuFrame(String title) {
        super(title);
    }

    //MODIFIES: this
    //EFFECTS: compiles all the components into the container of the frame
    @Override
    protected void drawPanel() {
        createContentPane();

        JLabel instructions = new JLabel("Load the menu to see the current data");
        contentPane.add(instructions);
        layoutComponents(5, 10, instructions);

        createButtons();
    }

    //MODIFIES: this
    //EFFECTS: displays the menu
    public void drawMenuContents() {
        String menuContents = getMenu().toString();
        JLabel label = new JLabel("<html>" + menuContents.replaceAll("\n","<br>")
                .replaceAll("\t", "&emsp;") + "</html>");
        contentPane.add(label);
        layoutComponents(5, 30, label);

    }

    //MODIFIES: this
    //EFFECTS: initializes the container of the frame
    private void createContentPane() {
        contentPane = getContentPane();
        layout = new SpringLayout();
        contentPane.setLayout(layout);

    }

    //MODIFIES: this
    //EFFECTS: lay out the components nicely in the container
    private void layoutComponents(int x, int y, JComponent component) {

        layout.putConstraint(SpringLayout.WEST, component,
                x,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, component,
                y,
                SpringLayout.NORTH, contentPane);


    }

    //EFFECTS: creates all the buttons in the frame
    private void createButtons() {

        LoadButton loadButton = new LoadButton(this, contentPane, layout);
        loadButton.createButton();
        BackButton backButton = new BackButton(this, contentPane, layout);
        backButton.createButton();
    }
}
