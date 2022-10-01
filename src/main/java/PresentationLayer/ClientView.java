package PresentationLayer;

import BusinessLayer.MenuuItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClientView {
    private JFrame frame;
    private JButton viewProducts;
    private JButton searchForProducts;
    private JButton makeOrderButton;
    private JButton backButton;
    private JTable table;
    private JTable searchTable;
    private JScrollPane searchScrollPane;
    private   JScrollPane scrollPane;
    private String logedUsername;

    private JPanel panel;
    private JLabel keyWordLabel;
    private JLabel ratingLabel;
    private JLabel nrCaloriesLabel;
    private JLabel proteinsLabel;
    private JLabel fatsLabel;
    private JLabel sodiumLabel;
    private JLabel priceLabel;

    private JTextField keyWordField;
    private  JTextField ratingField;
    private  JTextField nrCaloriesField;
    private  JTextField proteinsField;
    private JTextField fatsField;
    private  JTextField sodiumField;
    private  JTextField priceField;
    Object[][] objSearch = new Object[10000][7];
    Object[][] objView = new Object[10000][7];
    private JButton searchButton;
    private JButton addProduct;
    Object[][] objOrder= new Object[100][2];
    private JTable orderTable;
    private JScrollPane orderTableScroll;
    private ArrayList<MenuuItem> orderProducts;
    private int totalPrice = 0;
    private JLabel orderInformation;
    private JTextArea orderText;
    private JScrollPane scrollTextArea;
    private JButton delProduct;


    public ClientView(){
        delProduct = new JButton("DELETE PRODUCT");
        orderInformation = new JLabel("ORDER INFORMATIONS:");
        orderText = new JTextArea();
        scrollTextArea = new JScrollPane(orderText);
        orderProducts = new ArrayList<>();
        addProduct = new JButton("ADD PRODUCT");
        frame = new JFrame("Client");
        viewProducts = new JButton("VIEW PRODUCTS");
        searchForProducts = new JButton("SEARCH FOR PRODUCT");
        backButton = new JButton("LOG OUT");
        makeOrderButton = new JButton("MAKE ORDER");

        keyWordLabel = new JLabel("KEYWORD");
        ratingLabel = new JLabel("RATING");
        nrCaloriesLabel = new JLabel("CALORIES");
        proteinsLabel = new JLabel("PROTEINS");
        fatsLabel = new JLabel("FATS");
        sodiumLabel = new JLabel("SODIUM");
        priceLabel = new JLabel("PRICE");
        keyWordField = new JTextField();
        ratingField = new JTextField();
        nrCaloriesField = new JTextField();
        proteinsField = new JTextField();
        fatsField = new JTextField();
        sodiumField = new JTextField();
        priceField = new JTextField();
        panel = new JPanel();
        searchButton = new JButton("SEARCH");


    }
    public void paintClientView()
    {

        frame.setLayout(null);
        frame.setBounds(80, 50, 1400, 800);
        frame.getContentPane().setBackground(Color.ORANGE);




        viewProducts.setBounds(50, 20, 350, 40);
        viewProducts.setForeground(Color.black);
        viewProducts.setFont(new Font("Verdana", Font.BOLD, 30));

        searchForProducts.setBounds(450, 20, 500, 40);
        searchForProducts.setForeground(Color.black);
        searchForProducts.setFont(new Font("Verdana", Font.BOLD, 30));

        backButton.setBounds(1225, 710, 150, 40);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Verdana", Font.BOLD, 20));

        makeOrderButton.setBounds(1000, 20, 300, 40);
        makeOrderButton.setForeground(Color.black);
        makeOrderButton.setFont(new Font("Verdana", Font.BOLD, 30));

        keyWordLabel.setBounds(50, 100, 200, 40);
        keyWordLabel.setForeground(Color.black);
        keyWordLabel.setFont(new Font("Verdana", Font.BOLD, 30));

        ratingLabel.setBounds(270, 100, 150, 40);
        ratingLabel.setForeground(Color.black);
        ratingLabel.setFont(new Font("Verdana", Font.BOLD, 30));

        nrCaloriesLabel.setBounds(440, 100, 200, 40);
        nrCaloriesLabel.setForeground(Color.black);
        nrCaloriesLabel.setFont(new Font("Verdana", Font.BOLD, 30));

        proteinsLabel.setBounds(660, 100, 200, 40);
        proteinsLabel.setForeground(Color.BLACK);
        proteinsLabel.setFont(new Font("Verdana", Font.BOLD, 30));

        fatsLabel.setBounds(880, 100, 100, 40);
        fatsLabel.setForeground(Color.black);
        fatsLabel.setFont(new Font("Verdana", Font.BOLD, 30));

        sodiumLabel.setBounds(1000, 100, 150, 40);
        sodiumLabel.setForeground(Color.black);
        sodiumLabel.setFont(new Font("Verdana", Font.BOLD, 30));

        priceLabel.setBounds(1170, 100, 150, 40);
        priceLabel.setForeground(Color.black);
        priceLabel.setFont(new Font("Verdana", Font.BOLD, 30));

        keyWordField.setBounds(40, 150, 200, 40);
        keyWordField.setForeground(Color.black);
        keyWordField.setFont(new Font("Verdana", Font.BOLD, 30));


        ratingField.setBounds(260, 150, 150, 40);
        ratingField.setForeground(Color.black);
        ratingField.setFont(new Font("Verdana", Font.BOLD, 30));
        ratingField.setText("0");

        nrCaloriesField.setBounds(450, 150, 150, 40);
        nrCaloriesField.setForeground(Color.black);
        nrCaloriesField.setFont(new Font("Verdana", Font.BOLD, 30));
        nrCaloriesField.setText("0");

        proteinsField.setBounds(670, 150, 160, 40);
        proteinsField.setForeground(Color.BLACK);
        proteinsField.setFont(new Font("Verdana", Font.BOLD, 30));
        proteinsField.setText("0");

        fatsField.setBounds(870, 150, 100, 40);
        fatsField.setForeground(Color.black);
        fatsField.setFont(new Font("Verdana", Font.BOLD, 30));
        fatsField.setText("0");

        sodiumField.setBounds(1020, 150, 100, 40);
        sodiumField.setForeground(Color.black);
        sodiumField.setFont(new Font("Verdana", Font.BOLD, 30));
        sodiumField.setText("0");

        priceField.setBounds(1170, 150, 100, 40);
        priceField.setForeground(Color.black);
        priceField.setFont(new Font("Verdana", Font.BOLD, 30));
        priceField.setText("0");

        searchButton.setBounds(800, 200, 200, 50);
        searchButton.setForeground(Color.black);
        searchButton.setFont(new Font("Verdana", Font.BOLD, 30));

        addProduct.setBounds(650, 460, 200, 40);
        addProduct.setForeground(Color.black);
        addProduct.setFont(new Font("Verdana", Font.BOLD, 20));

        delProduct.setBounds(880, 460, 250, 40);
        delProduct.setForeground(Color.black);
        delProduct.setFont(new Font("Verdana", Font.BOLD, 20));

        orderInformation.setBounds(300, 460, 300, 40);
        orderInformation.setForeground(Color.black);
        orderInformation.setFont(new Font("Verdana", Font.BOLD, 20));

        orderText.setForeground(Color.black);
        orderText.setLineWrap(true);
        orderText.setFont(new Font("Verdana", Font.BOLD, 10));
        scrollTextArea = new JScrollPane(orderText);
        scrollTextArea.setBounds(200, 505, 400, 200);



        frame.add(backButton);
        frame.add(makeOrderButton);
        frame.add(searchForProducts);
        frame.add(viewProducts);
        frame.setVisible(true);
    }

    public JButton getDelProduct() {
        return delProduct;
    }

    public void setDelProduct(JButton delProduct) {
        this.delProduct = delProduct;
    }

    public JScrollPane getScrollTextArea() {
        return scrollTextArea;
    }

    public void setScrollTextArea(JScrollPane scrollTextArea) {
        this.scrollTextArea = scrollTextArea;
    }

    public JLabel getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(JLabel orderInformation) {
        this.orderInformation = orderInformation;
    }

    public JTextArea getOrderText() {
        return orderText;
    }

    public void setOrderText(JTextArea orderText) {
        this.orderText = orderText;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<MenuuItem> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<MenuuItem> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public JScrollPane getOrderTableScroll() {
        return orderTableScroll;
    }

    public void setOrderTableScroll(JScrollPane orderTableScroll) {
        this.orderTableScroll = orderTableScroll;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton getViewProducts() {
        return viewProducts;
    }

    public void setViewProducts(JButton viewProducts) {
        this.viewProducts = viewProducts;
    }

    public JButton getSearchForProducts() {
        return searchForProducts;
    }

    public void setSearchForProducts(JButton searchForProducts) {
        this.searchForProducts = searchForProducts;
    }

    public JButton getMakeOrderButton() {
        return makeOrderButton;
    }

    public void setMakeOrderButton(JButton makeOrderButton) {
        this.makeOrderButton = makeOrderButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getKeyWordLabel() {
        return keyWordLabel;
    }

    public void setKeyWordLabel(JLabel keyWordLabel) {
        this.keyWordLabel = keyWordLabel;
    }

    public JLabel getRatingLabel() {
        return ratingLabel;
    }

    public void setRatingLabel(JLabel ratingLabel) {
        this.ratingLabel = ratingLabel;
    }

    public JLabel getNrCaloriesLabel() {
        return nrCaloriesLabel;
    }

    public void setNrCaloriesLabel(JLabel nrCaloriesLabel) {
        this.nrCaloriesLabel = nrCaloriesLabel;
    }

    public JLabel getProteinsLabel() {
        return proteinsLabel;
    }

    public void setProteinsLabel(JLabel proteinsLabel) {
        this.proteinsLabel = proteinsLabel;
    }

    public JLabel getFatsLabel() {
        return fatsLabel;
    }

    public void setFatsLabel(JLabel fatsLabel) {
        this.fatsLabel = fatsLabel;
    }

    public Object[][] getObjOrder() {
        return objOrder;
    }

    public void setObjOrder(Object[][] objOrder) {
        this.objOrder = objOrder;
    }

    public JTable getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(JTable orderTable) {
        this.orderTable = orderTable;
    }

    public JLabel getSodiumLabel() {
        return sodiumLabel;
    }

    public void setSodiumLabel(JLabel sodiumLabel) {
        this.sodiumLabel = sodiumLabel;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(JLabel priceLabel) {
        this.priceLabel = priceLabel;
    }

    public JTextField getKeyWordField() {
        return keyWordField;
    }

    public void setKeyWordField(JTextField keyWordField) {
        this.keyWordField = keyWordField;
    }

    public JTextField getRatingField() {
        return ratingField;
    }

    public void setRatingField(JTextField ratingField) {
        this.ratingField = ratingField;
    }

    public JTextField getNrCaloriesField() {
        return nrCaloriesField;
    }

    public void setNrCaloriesField(JTextField nrCaloriesField) {
        this.nrCaloriesField = nrCaloriesField;
    }

    public JTextField getProteinsField() {
        return proteinsField;
    }

    public void setProteinsField(JTextField proteinsField) {
        this.proteinsField = proteinsField;
    }

    public JTextField getFatsField() {
        return fatsField;
    }

    public void setFatsField(JTextField fatsField) {
        this.fatsField = fatsField;
    }

    public JTextField getSodiumField() {
        return sodiumField;
    }

    public void setSodiumField(JTextField sodiumField) {
        this.sodiumField = sodiumField;
    }

    public Object[][] getObjSearch() {
        return objSearch;
    }

    public void setObjSearch(Object[][] objSearch) {
        this.objSearch = objSearch;
    }

    public Object[][] getObjView() {
        return objView;
    }

    public void setObjView(Object[][] objView) {
        this.objView = objView;
    }

    public JButton getAddProduct() {
        return addProduct;
    }

    public void setAddProduct(JButton addProduct) {
        this.addProduct = addProduct;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public void setPriceField(JTextField priceField) {
        this.priceField = priceField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JTable getSearchTable() {
        return searchTable;
    }

    public void setSearchTable(JTable searchTable) {
        this.searchTable = searchTable;
    }

    public JScrollPane getSearchScrollPane() {
        return searchScrollPane;
    }

    public void setSearchScrollPane(JScrollPane searchScrollPane) {
        this.searchScrollPane = searchScrollPane;
    }

    public String getLogedUsername() {
        return logedUsername;
    }

    public void setLogedUsername(String logedUsername) {
        this.logedUsername = logedUsername;
    }
}
