package ui.frames;


import ui.buttons.AddButton;
import model.menu.Menu;
import ui.buttons.BackButton;
import ui.buttons.LoadButton;
import ui.buttons.SaveButton;

import javax.swing.*;
import java.awt.*;

//Representation of the Add Dish Page in the application
public class AddDishFrame extends Frame {

    private JLabel header;
    private JLabel name;
    private JLabel desc;
    private JLabel code;
    private JLabel price;
    private JLabel category;
    private JTextField nameTextField;
    private JTextField descTextField;
    private JTextField codeTextField;
    private JTextField priceTextField;
    private JTextField categoryTextField;
    private Container contentPane;
    private SpringLayout layout;
    private Menu menu;


    //EFFECTS: constructs the Add Dish Frame with a title
    public AddDishFrame(String title) {
        super(title);
        menu = new Menu();

    }

    //EFFECTS: compiles all the components and put it in the container of the frame
    protected void drawPanel() {
        createContentPane();
        createLabels();
        createTextFields();
        createButtons();
        addToContentPane();
        layOutComponents();


    }

    //getters
    public String getNameTextField() {
        return this.nameTextField.getText();
    }

    public String getDescTextField() {
        return this.descTextField.getText();
    }

    public String getCodeTextField() {
        return this.codeTextField.getText();
    }

    public String getPriceTextField() {
        return this.priceTextField.getText();
    }

    public String getCategoryTextField() {
        return this.categoryTextField.getText();
    }

    //MODIFIES: this
    //EFFECTS: clear all the text fields in the frame
    public void clearTextFields() {
        nameTextField.setText("");
        descTextField.setText("");
        codeTextField.setText("");
        priceTextField.setText("");
        categoryTextField.setText("");

    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    //EFFECTS: creates all the needed buttons in the frame
    private void createButtons() {
        AddButton addButton = new AddButton(this, contentPane, layout);
        addButton.createButton();
        LoadButton loadButton = new LoadButton(this, contentPane, layout);
        loadButton.createButton();
        SaveButton saveButton = new SaveButton(this,contentPane,layout);
        saveButton.createButton();
        BackButton backButton = new BackButton(this, contentPane, layout);
        backButton.createButton();
    }

    //MODIFIES: this
    //EFFECTS: creates all the needed textfields in the frame
    private void createTextFields() {
        nameTextField = new JTextField(15);
        descTextField = new JTextField(15);
        codeTextField = new JTextField(15);
        priceTextField = new JTextField(15);
        categoryTextField = new JTextField(15);
    }

    //MODIFIES: this
    //EFFECTS: creates all the needed labels in the frame
    private void createLabels() {
        header = new JLabel("Please enter the following information:");
        name = new JLabel("Name: ");
        desc = new JLabel("Description: ");
        code = new JLabel("Code: ");
        price = new JLabel("Price: ");
        category = new JLabel("Category: ");
    }

    //MODIFIES: this
    //EFFECTS: initializing the container of the frame
    private void createContentPane() {
        contentPane = getContentPane();
        layout = new SpringLayout();
        contentPane.setLayout(layout);
    }


    public Menu getMenu() {
        return this.menu;
    }

    //MODIFIES: this
    //EFFECTS: adds all the components to the container
    private void addToContentPane() {
        contentPane.add(header);
        contentPane.add(name);
        contentPane.add(nameTextField);
        contentPane.add(desc);
        contentPane.add(descTextField);
        contentPane.add(code);
        contentPane.add(codeTextField);
        contentPane.add(price);
        contentPane.add(priceTextField);
        contentPane.add(category);
        contentPane.add(categoryTextField);

    }


    //EFFECTS: lay out the components nicely
    private void layOutComponents() {
        layoutLabel(layout,contentPane,header,5,10);
        layoutLabel(layout, contentPane,name,5,40);
        layoutTextField(layout, contentPane,name, nameTextField,37,40);
        layoutLabel(layout, contentPane,desc,5,70);
        layoutTextField(layout, contentPane,desc, descTextField,5,70);
        layoutLabel(layout, contentPane,code,5,100);
        layoutTextField(layout, contentPane,code, codeTextField,40,100);
        layoutLabel(layout, contentPane,price,5,130);
        layoutTextField(layout, contentPane, price, priceTextField,40,130);
        layoutLabel(layout, contentPane,category,5,160);
        layoutTextField(layout, contentPane, category, categoryTextField,20,160);
    }


    //MODIFIES: this
    //EFFECTS: lay out the labels nicely
    private void layoutLabel(SpringLayout layout, Container contentPane, JLabel label, int x, int y) {
        //Adjust constraints for the label so it's at (5,5).
        layout.putConstraint(SpringLayout.WEST, label,
                x,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label,
                y,
                SpringLayout.NORTH, contentPane);

    }

    //MODIFIES: this
    //EFFECTS: lay out the text fiends nicely
    private void layoutTextField(SpringLayout layout, Container contentPane, JLabel label, JTextField textField,
                                 int x, int y) {
        //Adjust constraints for the text field so it's at
        //(<label's right edge> + 5, 5).
        layout.putConstraint(SpringLayout.WEST, textField,
                x,
                SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.NORTH, textField,
                y,
                SpringLayout.NORTH, contentPane);
    }


}
