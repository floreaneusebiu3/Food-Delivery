package PresentationLayer;

import BusinessLayer.CompositeProduct;
import DataLayer.Serialization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;


public class AdminView implements Serializable {
    private JFrame frame;
    private JLabel[] labels;
    private JTextField[] addTextFields;
    private JTextField[] editTextFields;
    private JButton addButton;
    private JButton editButton;
    private JButton createComposedProduct;
    private JButton deleteButton;
    private JLabel menuTitle;
    private JLabel importedProductsTitle;
    private JButton importProducts;
    private JTextField[] selectProductsToBeImported;
    private JButton addProductToMenu ;
    private JButton generateReports;
    private JTable menuTable;
    private JTable importedProductsTable = new JTable();
    Object[][] objMenu;
    Object[][] objImported;
    private JButton backButton;
    private JButton deleteComposedButton;

    private JFrame reportsFrame;
    private JTextField[] tf = new JTextField[7];
    private JButton[] buttons = new JButton[4];
    private JLabel reportsTitle;

    public AdminView() {

        frame = new JFrame("ADMINISTRATOR");
        labels = new JLabel[8];
        addTextFields = new JTextField[7];
        editTextFields = new JTextField[7];
        addButton = new JButton("ADD");
        editButton = new JButton("EDIT");
        createComposedProduct = new JButton("CREATE COMPOSED PRODUCT");
        deleteButton = new JButton("DELETE PRODUCT");
        menuTitle = new JLabel("MENU");
        importedProductsTitle = new JLabel("IMPORTED PRODUCTS");
        importProducts = new JButton("IMPORT PRODUCTS");
        selectProductsToBeImported = new JTextField[2];
        addProductToMenu = new JButton("ADD PRODUCT TO MENU");
        generateReports = new JButton("GENERATE REPORTS");
        objMenu = new Object[10000][7];
        objImported = new Object[10000][7];
        backButton = new JButton("LOG OUT");
        deleteComposedButton = new JButton("DELETE COMPOSED");

        for(int i=0; i<7; i++)
        {
            editTextFields[i] = new JTextField();
            addTextFields[i] = new JTextField();
        }

        reportsFrame = new JFrame("REPORTS");
        reportsFrame.setBounds(300, 200, 600, 400);
        reportsFrame.setLayout(null);
        for(int i=0; i< 7; i++)
            tf[i] = new JTextField();
        buttons[0] = new JButton("GENERATE REPORT 1");
        buttons[1]= new JButton("GENERATE REPORT 2");
        buttons[2]= new JButton("GENERATE REPORT 3");
        buttons[3]= new JButton("GENERATE REPORT 4");
    }

    public void paintAdministratorView() {
        frame.setLayout(null);
        frame.setBounds(2, 10, 1600, 850);
        frame.getContentPane().setBackground(Color.ORANGE);

        menuTitle.setBounds(300, 5, 100, 40);
        menuTitle.setForeground(Color.BLACK);
        menuTitle.setFont(new Font("Verdana", Font.BOLD, 30));

        importedProductsTitle.setBounds(1000, 5, 400, 40);
        importedProductsTitle.setForeground(Color.BLACK);
        importedProductsTitle.setFont(new Font("Verdana", Font.BOLD, 30));

        deleteButton.setBounds(40, 755, 300, 50);
        deleteButton.setForeground(Color.black);
        deleteButton.setFont(new Font("Verdana", Font.BOLD, 20));

        deleteComposedButton.setBounds(360, 755, 300, 50);
        deleteComposedButton.setForeground(Color.black);
        deleteComposedButton.setFont(new Font("Verdana", Font.BOLD, 20));


        createComposedProduct.setBounds(100, 700, 400, 50);
        createComposedProduct.setForeground(Color.black);
        createComposedProduct.setFont(new Font("Verdana", Font.BOLD, 20));

        editTextFields[0].setBounds(10, 645, 200, 50);
        editTextFields[1].setBounds(215, 645, 50, 50);
        editTextFields[2].setBounds(270, 645, 50, 50);
        editTextFields[3].setBounds(325, 645, 50, 50);
        editTextFields[4].setBounds(380, 645, 50, 50);
        editTextFields[5].setBounds(435, 645, 50, 50);
        editTextFields[6].setBounds(490, 645, 50, 50);
        editButton.setBounds(545, 645, 100, 50);

        addTextFields[0].setBounds(10, 590, 200, 50);
        addTextFields[1].setBounds(215, 590, 50, 50);
        addTextFields[2].setBounds(270, 590, 50, 50);
        addTextFields[3].setBounds(325, 590, 50, 50);
        addTextFields[4].setBounds(380, 590, 50, 50);
        addTextFields[5].setBounds(435, 590, 50, 50);
        addTextFields[6].setBounds(490, 590, 50, 50);
        addButton.setBounds(545, 590, 100, 50);

        labels[0] = new JLabel("title");
        labels[0].setForeground(Color.black);
        labels[0].setFont(new Font("Verdaba", Font.BOLD, 13));
        labels[0].setBounds(40, 545, 100, 50);

        labels[1] = new JLabel("rating");
        labels[1].setForeground(Color.black);
        labels[1].setFont(new Font("Verdaba", Font.BOLD, 13));
        labels[1].setBounds(215, 545, 50, 50);

        labels[2] = new JLabel("calories");
        labels[2].setForeground(Color.black);
        labels[2].setFont(new Font("Verdaba", Font.BOLD, 13));
        labels[2].setBounds(270, 545, 50, 50);

        labels[3] = new JLabel("protein");
        labels[3].setForeground(Color.black);
        labels[3].setFont(new Font("Verdaba", Font.BOLD, 13));
        labels[3].setBounds(325, 545, 50, 50);

        labels[4] = new JLabel("fat");
        labels[4].setForeground(Color.black);
        labels[4].setFont(new Font("Verdaba", Font.BOLD, 13));
        labels[4].setBounds(390, 545, 50, 50);

        labels[5] = new JLabel("sodium");
        labels[5].setForeground(Color.black);
        labels[5].setFont(new Font("Verdaba", Font.BOLD, 13));
        labels[5].setBounds(435, 545, 50, 50);

        labels[6] = new JLabel("price");
        labels[6].setForeground(Color.black);
        labels[6].setFont(new Font("Verdaba", Font.BOLD, 13));
        labels[6].setBounds(495, 545, 50, 50);
        String[] head = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        menuTable = new JTable(objMenu, head);
        for (int i = 1; i <= 6; i++)
            menuTable.getColumnModel().getColumn(i).setPreferredWidth(20);
        JScrollPane jScrollPane = new JScrollPane(menuTable);
        jScrollPane.setBounds(10, 50, 750, 490);

        importedProductsTable = new JTable(objImported, head);
        for (int i = 1; i <= 6; i++)
            menuTable.getColumnModel().getColumn(i).setPreferredWidth(20);
        JScrollPane jScrollPane1 = new JScrollPane(importedProductsTable);
        jScrollPane1.setBounds(800, 50, 750, 490);

        importProducts.setBounds(900, 600, 400, 50);
        importProducts.setForeground(Color.black);
        importProducts.setFont(new Font("Verdana", Font.BOLD, 20));

        selectProductsToBeImported[0] = new JTextField();
        selectProductsToBeImported[0].setForeground(Color.BLACK);
        selectProductsToBeImported[0].setFont(new Font("Verdana", Font.BOLD, 20));
        selectProductsToBeImported[0].setBounds(1310, 600, 50, 50);


        selectProductsToBeImported[1] = new JTextField();
        selectProductsToBeImported[1].setBounds(1365, 600, 50, 50);
        selectProductsToBeImported[1].setForeground(Color.BLACK);
        selectProductsToBeImported[1].setFont(new Font("Verdana", Font.BOLD, 20));

        addProductToMenu.setBounds(900, 660, 400,50);
        addProductToMenu.setForeground(Color.black);
        addProductToMenu.setFont(new Font("Verdana", Font.BOLD, 20));

        generateReports.setBounds(900, 720, 400, 50);
        generateReports.setForeground(Color.black);
        generateReports.setFont(new Font("Verdana", Font.BOLD, 20));

        backButton.setBounds(1400, 750, 150, 40);
        backButton.setForeground(Color.black);
        backButton.setFont(new Font("Verdana", Font.BOLD, 20));

        frame.add(backButton);
        frame.add(generateReports);
        frame.add(addProductToMenu);
        frame.add(selectProductsToBeImported[0]);
        frame.add(selectProductsToBeImported[1]);
        frame.add(importProducts);
        frame.add(deleteComposedButton);
        frame.add(jScrollPane1);
        frame.add(jScrollPane);
        for (int i = 0; i < 7; i++)
        {
            frame.add(editTextFields[i]);
            frame.add(addTextFields[i]);
            frame.add(labels[i]);
        }
        frame.add(addButton);
        frame.add(editButton);
        frame.add(createComposedProduct);
        frame.add(deleteButton);
        frame.add(menuTitle);
        frame.add(importedProductsTitle);
        frame.setVisible(true);
    }

    public void paintReports()
    {

        tf[6].setBounds(10, 20, 50, 40);

        tf[0].setBounds(60, 20, 50, 40);
        tf[1].setBounds(120, 20, 50, 40);
        buttons[0].setBounds(170, 20, 300, 40);

        tf[2].setBounds(80, 70, 50, 40);
        buttons[1].setBounds(170, 80, 300, 40);

        tf[3].setBounds(50, 140,50 ,40 );
        tf[4].setBounds(110, 140, 50, 40);
        buttons[2].setBounds(170, 140, 300, 40);

        tf[5].setBounds(80, 190, 50, 40);
        buttons[3].setBounds(170, 190, 300,  40);

        for(int i=0; i<7; i++)
            reportsFrame.add(tf[i]);
        for(int i=0; i<4; i++)
            reportsFrame.add(buttons[i]);
        reportsFrame.setVisible(true);
    }
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    public JTextField[] getAddTextFields() {
        return addTextFields;
    }

    public void setAddTextFields(JTextField[] addTextFields) {
        this.addTextFields = addTextFields;
    }

    public JTextField[] getEditTextFields() {
        return editTextFields;
    }

    public void setEditTextFields(JTextField[] editTextFields) {
        this.editTextFields = editTextFields;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JButton getCreateComposedProduct() {
        return createComposedProduct;
    }

    public void setCreateComposedProduct(JButton createComposedProduct) {
        this.createComposedProduct = createComposedProduct;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JLabel getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(JLabel menuTitle) {
        this.menuTitle = menuTitle;
    }

    public JLabel getImportedProductsTitle() {
        return importedProductsTitle;
    }

    public void setImportedProductsTitle(JLabel importedProductsTitle) {
        this.importedProductsTitle = importedProductsTitle;
    }

    public JButton getImportProducts() {
        return importProducts;
    }

    public void setImportProducts(JButton importProducts) {
        this.importProducts = importProducts;
    }

    public JTextField[] getSelectProductsToBeImported() {
        return selectProductsToBeImported;
    }

    public void setSelectProductsToBeImported(JTextField[] selectProductsToBeImported) {
        this.selectProductsToBeImported = selectProductsToBeImported;
    }

    public JButton getAddProductToMenu() {
        return addProductToMenu;
    }

    public void setAddProductToMenu(JButton addProductToMenu) {
        this.addProductToMenu = addProductToMenu;
    }

    public JButton getGenerateReports() {
        return generateReports;
    }

    public void setGenerateReports(JButton generateReports) {
        this.generateReports = generateReports;
    }

    public JTable getMenuTable() {
        return menuTable;
    }

    public void setMenuTable(JTable menuTable) {
        this.menuTable = menuTable;
    }

    public JTable getImportedProductsTable() {
        return importedProductsTable;
    }

    public void setImportedProductsTable(JTable importedProductsTable) {
        this.importedProductsTable = importedProductsTable;
    }

    public Object[][] getObjMenu() {
        return objMenu;
    }

    public void setObjMenu(Object[][] objMenu) {
        this.objMenu = objMenu;
    }

    public Object[][] getObjImported() {
        return objImported;
    }

    public void setObjImported(Object[][] objImported) {
        this.objImported = objImported;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JButton getDeleteComposedButton() {
        return deleteComposedButton;
    }

    public void setDeleteComposedButton(JButton deleteComposedButton) {
        this.deleteComposedButton = deleteComposedButton;
    }

    public JFrame getReportsFrame() {
        return reportsFrame;
    }

    public void setReportsFrame(JFrame reportsFrame) {
        this.reportsFrame = reportsFrame;
    }

    public JTextField[] getTf() {
        return tf;
    }

    public void setTf(JTextField[] tf) {
        this.tf = tf;
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[] buttons) {
        this.buttons = buttons;
    }

    public JLabel getReportsTitle() {
        return reportsTitle;
    }

    public void setReportsTitle(JLabel reportsTitle) {
        this.reportsTitle = reportsTitle;
    }
}
