package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuuItem;
import DataLayer.*;

import javax.management.relation.Role;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Controller implements java.io.Serializable {
    private static final long serialVersionUID = -8613347217470826671L;
    ClientView clientView;
    LoginView loginView;
    AdminView adminView;
    DeliveryService deliveryService;
    int nrclicksSearch;
    int nrclicksViewProducts = 0;
    int nrclicksSearchForProduct = 0;
    JPanel panel = new JPanel();

    public Controller() {
        clientView = new ClientView();
        loginView = new LoginView();
        adminView = new AdminView();
        deliveryService = new DeliveryService();
        nrclicksSearch = 0;

                                                        //LOGIN VIEW
        loginView.getLoginButon().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // ArrayList<MenuuItem> mmm = deliveryService.importProducts();
                //Serialization.serializeMenuItemsWithQuantity(mmm); //fac doar o data asta*/
                ArrayList<User> users = new ArrayList<>();
                Boolean ok = false;
                users = Serialization.deserializeUser();
                User logUser = null;

                for (User user : users)
                    if (user.getUsername().compareTo(loginView.getUsernameTextFieldLogin().getText()) == 0 && user.getPassword().compareTo(loginView.getPasswordTextFieldLogin().getText()) == 0) {
                        {
                            ok = true;
                            logUser = new User(user.getUserId(), loginView.getUsernameTextFieldLogin().getText(), loginView.getPasswordTextFieldLogin().getText(), user.getUserRole());
                            break;
                        }
                    }
                if (ok == true) {
                    System.out.println("logat cu succes");
                    clientView.setLogedUsername(loginView.getUsernameTextFieldLogin().getText()) ;
                    deliveryService.setUsername(loginView.getUsernameTextFieldLogin().getText());
                    loginView.getUsernameTextFieldLogin().setText("");
                    loginView.getPasswordTextFieldLogin().setText("");
                   // loginView.getLoginFrame().dispose();
                    if (logUser.getUserRole() == UserRole.CLIENT)
                        clientView.paintClientView();
                    else {
                        adminView.paintAdministratorView();
                        adminView.paintReports();
                    }
                } else
                    JOptionPane.showMessageDialog(null, "username or password incorrect!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println(loginView.getUsernameTextFieldLogin().getText() + "*" + loginView.getPasswordTextFieldLogin().getText());
               /* usernameTextField.setText("");
                passwordTextField.setText("");*/
            }
        });

        loginView.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginView.paintRegister();
            }
        });

        loginView.getRegisterButtonNow().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> users = new ArrayList<>();
                User user;
                users = Serialization.deserializeUser();

                if (loginView.getUsernameTextField().getText().length() > 0 && loginView.getPasswordTextField().getText().length() > 0) {
                    if (loginView.getRole().getText().compareTo("client") == 0 || loginView.getRole().getText().length() == 0)

                        user = new User(users.size(), loginView.getUsernameTextField().getText(), loginView.getPasswordTextField().getText(), UserRole.CLIENT);
                    else if (loginView.getRole().getText().compareTo("admin") == 0)
                        user = new User(users.size(), loginView.getUsernameTextField().getText(), loginView.getPasswordTextField().getText(), UserRole.ADMINISTRATOR);
                    else
                        user = null;
                    if (user != null)
                        users.add(user);
                    for (User u : users)
                        System.out.println(u);
                    Serialization.serializeUser(users);

                }
            }
        });

        loginView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginView.getLoginFrame().getContentPane().removeAll();
                loginView.getLoginFrame().pack();
                loginView.paintLoginView();
            }
        });

                                                    //CLIENT VIEW
        clientView.getViewProducts().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nrclicksViewProducts++;
                if (nrclicksSearchForProduct > 0) {
                    clientView.getFrame().getContentPane().remove(clientView.getSearchButton());
                    clientView.getFrame().getContentPane().remove(clientView.getKeyWordLabel());
                    clientView.getFrame().getContentPane().remove(clientView.getKeyWordField());
                    clientView.getFrame().getContentPane().remove(clientView.getRatingField());
                    clientView.getFrame().getContentPane().remove(clientView.getRatingLabel());
                    clientView.getFrame().getContentPane().remove(clientView.getNrCaloriesLabel());
                    clientView.getFrame().getContentPane().remove(clientView.getNrCaloriesField());
                    clientView.getFrame().getContentPane().remove(clientView.getProteinsField());
                    clientView.getFrame().getContentPane().remove(clientView.getProteinsLabel());
                    clientView.getFrame().getContentPane().remove(clientView.getSodiumField());
                    clientView.getFrame().getContentPane().remove(clientView.getSodiumLabel());
                    clientView.getFrame().getContentPane().remove(clientView.getFatsField());
                    clientView.getFrame().getContentPane().remove(clientView.getFatsLabel());
                    clientView.getFrame().getContentPane().remove(clientView.getPriceField());
                    clientView.getFrame().getContentPane().remove(clientView.getPriceLabel());
                    clientView.getFrame().getContentPane().remove(clientView.getAddProduct());
                    clientView.getFrame().getContentPane().remove(clientView.getDelProduct());
                    clientView.getFrame().getContentPane().remove(clientView.getOrderTableScroll());
                    clientView.getFrame().getContentPane().remove(clientView.getOrderTable());
                    clientView.getFrame().getContentPane().remove(clientView.getOrderInformation());
                    clientView.getFrame().getContentPane().remove(clientView.getScrollTextArea());


                if (nrclicksSearch > 0) {
                    clientView.getFrame().getContentPane().remove(clientView.getSearchScrollPane());
                }
                    clientView.getFrame().repaint();
                    clientView.getFrame().setVisible(true);
                }

                ArrayList<MenuuItem> menuItems = new ArrayList<>();
                menuItems = Serialization.deserializeMenuItems();
                for (MenuuItem m : menuItems)
                    System.out.println(m);

                String[] head = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                //  Object[][] obj = new Object[menuItems.size()][7];
                for (int i = 0; i < menuItems.size(); i++) {
                    clientView.objView[i][0] = menuItems.get(i).getTitle();
                    clientView.objView[i][1] = menuItems.get(i).getRating();
                    clientView.objView[i][2] = menuItems.get(i).getCalories();
                    clientView.objView[i][3] = menuItems.get(i).getProtein();
                    clientView.objView[i][4] = menuItems.get(i).getFat();
                    clientView.objView[i][5] = menuItems.get(i).getSodium();
                    clientView.objView[i][6] = menuItems.get(i).getPrice();
                }
                if (nrclicksViewProducts == 1) {
                    clientView.setTable(new JTable(clientView.objView, head));
                    for (int i = 1; i <= 6; i++)
                        clientView.getTable().getColumnModel().getColumn(i).setPreferredWidth(20);
                    clientView.setScrollPane(new JScrollPane(clientView.getTable()));
                    clientView.getScrollPane().setBounds(47, 100, 1300, 600);
                }
                clientView.getFrame().add(clientView.getScrollPane());
                clientView.getFrame().repaint();
                clientView.getFrame().setVisible(true);
            }
        });
        clientView.getAddProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = clientView.getSearchTable().getSelectedRow();
                    String p0 = (String) clientView.getSearchTable().getValueAt(x, 0);
                    Double p1 = (Double) clientView.getSearchTable().getValueAt(x, 1);
                    int p2 = (Integer) clientView.getSearchTable().getValueAt(x, 2);
                    int p3 = (int) clientView.getSearchTable().getValueAt(x, 3);
                    int p4 = (int) clientView.getSearchTable().getValueAt(x, 4);
                    int p5 = (int) clientView.getSearchTable().getValueAt(x, 5);
                    int p6 = (int) clientView.getSearchTable().getValueAt(x, 6);
                    MenuuItem menuuItem = new BaseProduct(p0, p1, p2, p3, p4, p5, p6);

                    ArrayList<MenuuItem> menuuItems = new ArrayList<>();
                    menuuItems = Serialization.deserializeMenuItems();
                    for (MenuuItem m : menuuItems)
                        if (m.getTitle().compareTo(menuuItem.getTitle()) == 0 && m.getRating() == menuuItem.getRating() &&
                                m.getCalories() == menuuItem.getCalories() && m.getProtein() == menuuItem.getProtein() &&
                                m.getFat() == menuuItem.getFat() && m.getSodium() == menuuItem.getSodium() &&
                                m.getPrice() == menuuItem.getPrice())
                            clientView.getOrderProducts().add(m);
                        else
                            System.out.println(m.getTitle());
                    clientView.setTotalPrice(0);
                    for (int i = 0; i < clientView.getOrderProducts().size(); i++) {
                        clientView.getObjOrder()[i][0] = clientView.getOrderProducts().get(i).getTitle();
                        clientView.getObjOrder()[i][1] = clientView.getOrderProducts().get(i).getPrice();
                        clientView.setTotalPrice(clientView.getTotalPrice() + clientView.getOrderProducts().get(i).getPrice());
                    }
                clientView.getFrame().repaint();
                clientView.getFrame().setVisible(true);
            }
        });

        clientView.getDelProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = clientView.getOrderTable().getSelectedRow();
                String p0 = (String) clientView.getOrderTable().getValueAt(x, 0);
                int p6 = (int) clientView.getOrderTable().getValueAt(x, 1);
                    MenuuItem menuuItem = new MenuuItem() {
                        @Override
                        public int computePrice() {
                            return 0;
                        }
                    };
                    for(int i=0; i< clientView.getOrderProducts().size(); i++)
                        if(clientView.getOrderProducts().get(i).getPrice() == p6 &&
                           clientView.getOrderProducts().get(i).getTitle().compareTo(p0) == 0)
                            menuuItem = clientView.getOrderProducts().get(i);
                    clientView.getOrderProducts().remove(menuuItem);
                for(int i=0; i< 100; i++)
                {
                    clientView.getObjOrder()[i][0] = null;
                    clientView.getObjOrder()[i][1] = null;
                }
                for(int i=0; i< clientView.getOrderProducts().size(); i++)
                {
                    clientView.getObjOrder()[i][0] = clientView.getOrderProducts().get(i).getTitle();
                    clientView.getObjOrder()[i][1] = clientView.getOrderProducts().get(i).getPrice();
                    clientView.setTotalPrice(clientView.getTotalPrice()+clientView.getOrderProducts().get(i).getPrice());
                }
                clientView.getFrame().repaint();
                clientView.getFrame().setVisible(true);
            }
        });
        clientView.getMakeOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientView.setTotalPrice(0);
                Order order = new Order(deliveryService.getOrders().size(), clientView.getLogedUsername());
                clientView.getOrderText().setText("");
                clientView.getOrderText().append("\n        ORDER "+ order.getId() + "\n");
               clientView.getOrderText().append(order.toString());
               deliveryService.createOrder(order, clientView.getOrderProducts());
               int price=0;
               for(int i=0; i<clientView.getOrderProducts().size();i++){
                   clientView.getOrderText().append("\n"+clientView.getOrderProducts().get(i));
                   price += clientView.getOrderProducts().get(i).getPrice();
               }
               clientView.setTotalPrice(price);
               order.setTotalPrice(price);
               Serialization.serializeOrders(deliveryService.getOrders());
               clientView.getOrderText().append("\nTOTAL PRICE: " + clientView.getTotalPrice());
                Writter.createBill(deliveryService.getOrders(), order, clientView.getTotalPrice());
            }
        });

        clientView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientView.getFrame().dispose();
                loginView.paintLoginView();
            }
        });
        clientView.getSearchForProducts().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nrclicksViewProducts > 0) {
                    clientView.getFrame().getContentPane().remove(clientView.getScrollPane());
                    clientView.getFrame().repaint();
                    clientView.getFrame().setVisible(true);
                }
                nrclicksSearchForProduct++;
                clientView.getFrame().add(clientView.getKeyWordLabel());
                clientView.getFrame().add(clientView.getRatingLabel());
                clientView.getFrame().add(clientView.getNrCaloriesLabel());
                clientView.getFrame().add(clientView.getProteinsLabel());
                clientView.getFrame().add(clientView.getFatsLabel());
                clientView.getFrame().add(clientView.getSodiumLabel());
                clientView.getFrame().add(clientView.getPriceLabel());

                clientView.getFrame().add(clientView.getKeyWordField());
                clientView.getFrame().add(clientView.getRatingField());
                clientView.getFrame().add(clientView.getNrCaloriesField());
                clientView.getFrame().add(clientView.getProteinsField());
                clientView.getFrame().add(clientView.getFatsField());
                clientView.getFrame().add(clientView.getSodiumField());
                clientView.getFrame().add(clientView.getPriceField());
                clientView.getFrame().add(clientView.getSearchButton());
                clientView.getFrame().add(clientView.getAddProduct());
                clientView.getFrame().add(clientView.getDelProduct());
                clientView.getFrame().add(clientView.getOrderInformation());
                clientView.getFrame().add(clientView.getScrollTextArea());

                String[] head = {"NAME", "PRICE"};
                //  Object[][] obj = new Object[menuItems.size()][7];
                if(nrclicksSearch == 0) {
                    clientView.setOrderTable(new JTable(clientView.objOrder, head));
                    clientView.getOrderTable().getColumnModel().getColumn(1).setPreferredWidth(40);
                    clientView.setOrderTableScroll(new JScrollPane(clientView.getOrderTable()));
                    clientView.getOrderTableScroll().setBounds(670, 505, 400, 200);
                }
                clientView.getFrame().add(clientView.getOrderTableScroll());
                clientView.getFrame().repaint();
                clientView.getFrame().setVisible(true);
            }
        });

        clientView.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               ArrayList<MenuuItem> products = Serialization.deserializeMenuItems();
                ArrayList<MenuuItem> founded = new ArrayList<>();
                /* boolean allEmpty = true;
                int ok = 0; //inseamna ca trebuie sa cautam in toata lista de produse
                //daca ok e 1, trebuie sa cautam in lista de founded
                if (clientView.getKeyWordField().getText().length() > 0)
                // founded = deliveryService.searchProduct(products, "title", clientView.getKeyWordField().getText());
                {  allEmpty = false;
                    if (ok == 0)
                        founded = deliveryService.searchProduct(products, "title", clientView.getKeyWordField().getText());
                    else
                        founded = deliveryService.searchProduct(founded, "title", clientView.getKeyWordField().getText());
                    ok = 1;
                }

                if (clientView.getRatingField().getText().length() > 0)
                // founded =  deliveryService.searchProduct(products, "rating", clientView.getRatingField().getText());
                { allEmpty = false;
                    System.out.println(ok);
                    if (ok == 0)
                        founded = deliveryService.searchProduct(products, "rating", clientView.getRatingField().getText());
                    else
                        founded = deliveryService.searchProduct(founded, "rating", clientView.getRatingField().getText());
                    ok = 1;
                }
                if (clientView.getProteinsField().getText().length() > 0) {
                    allEmpty = false;
                    System.out.println(ok);
                    if (ok == 0)
                        founded = deliveryService.searchProduct(products, "protein", clientView.getProteinsField().getText());
                    else
                        founded = deliveryService.searchProduct(founded, "protein", clientView.getProteinsField().getText());
                    ok = 1;
                }
                if (clientView.getFatsField().getText().length() > 0) {
                    allEmpty = false;
                    if (ok == 0)
                        founded = deliveryService.searchProduct(products, "fat", clientView.getFatsField().getText());
                    else
                        founded = deliveryService.searchProduct(founded, "fat", clientView.getFatsField().getText());
                    ok = 1;
                }
                if (clientView.getNrCaloriesField().getText().length() > 0) {
                    allEmpty = false;
                    if (ok == 0)
                        founded = deliveryService.searchProduct(products, "calories", clientView.getNrCaloriesField().getText());
                    else
                        founded = deliveryService.searchProduct(founded, "calories", clientView.getNrCaloriesField().getText());
                    ok = 1;
                }
                if (clientView.getSodiumField().getText().length() > 0) {
                    allEmpty = false;
                    if (ok == 0)
                        founded = deliveryService.searchProduct(products, "sodium", clientView.getSodiumField().getText());
                    else
                        founded = deliveryService.searchProduct(founded, "sodium", clientView.getSodiumField().getText());
                    ok = 1;
                }
                if (clientView.getPriceField().getText().length() > 0) {
                    allEmpty = false;
                    if (ok == 0)
                        founded = deliveryService.searchProduct(products, "price", clientView.getPriceField().getText());
                    else
                        founded = deliveryService.searchProduct(founded, "price", clientView.getPriceField().getText());
                    ok = 1;
                }*/
              founded = deliveryService.searchProduct(products, clientView.getKeyWordField().getText(),
                      Double.parseDouble(clientView.getRatingField().getText()), Integer.parseInt(clientView.getNrCaloriesField().getText()),
                      Integer.parseInt(clientView.getProteinsField().getText()), Integer.parseInt(clientView.getFatsField().getText()),
                      Integer.parseInt(clientView.getSodiumField().getText()), Integer.parseInt(clientView.getPriceField().getText()));

                String[] head = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                //Object[][] obj = new Object[founded.size()][7];
                for (int i = 0; i < founded.size(); i++) {
                    clientView.objSearch[i][0] = founded.get(i).getTitle();
                    clientView.objSearch[i][1] = founded.get(i).getRating();
                    clientView.objSearch[i][2] = founded.get(i).getCalories();
                    clientView.objSearch[i][3] = founded.get(i).getProtein();
                    clientView.objSearch[i][4] = founded.get(i).getFat();
                    clientView.objSearch[i][5] = founded.get(i).getSodium();
                    clientView.objSearch[i][6] = founded.get(i).getPrice();
                }
                int ind = 9994;
                ArrayList<CompositeProduct> compositeProducts = new ArrayList<>();
                compositeProducts = Serialization.deserealizeCompositeProduct();
                for(int i=0; i < compositeProducts.size(); i++)
                {
                    clientView.getObjSearch()[ind][3] = "MENIU ";
                    clientView.getObjSearch()[ind][4] = i;
                    ind++;
                    System.out.println("_"+ compositeProducts.get(0));
                    for(int j=0; j<=3; j++)
                    {
                        clientView.getObjSearch()[ind][0] = compositeProducts.get(i).getMenuItems().get(j).getTitle();
                        clientView.getObjSearch()[ind][1] = compositeProducts.get(i).getMenuItems().get(j).getRating();
                        clientView.getObjSearch()[ind][2] = compositeProducts.get(i).getMenuItems().get(j).getCalories();
                        clientView.getObjSearch()[ind][3] = compositeProducts.get(i).getMenuItems().get(j).getProtein();
                        clientView.getObjSearch()[ind][4] = compositeProducts.get(i).getMenuItems().get(j).getFat();
                        clientView.getObjSearch()[ind][5] = compositeProducts.get(i).getMenuItems().get(j).getSodium();
                        clientView.getObjSearch()[ind][6] = compositeProducts.get(i).getMenuItems().get(j).getPrice();
                        ind++;
                    }
                    ind-=10;
                }
                for (int i = founded.size(); i < 5000; i++) {
                    clientView.objSearch[i][0] = null;
                    clientView.objSearch[i][1] = null;
                    clientView.objSearch[i][2] = null;
                    clientView.objSearch[i][3] = null;
                    clientView.objSearch[i][4] = null;
                    clientView.objSearch[i][5] = null;
                    clientView.objSearch[i][6] = null;
                }
                if (nrclicksSearch == 0) {
                    clientView.setSearchTable(new JTable(clientView.objSearch, head));
                    for (int i = 1; i <= 6; i++)
                        clientView.getSearchTable().getColumnModel().getColumn(i).setPreferredWidth(20);
                    clientView.setSearchScrollPane(new JScrollPane(clientView.getSearchTable()));
                    clientView.getSearchScrollPane().setBounds(47, 255, 1300, 200);
                }
                if (nrclicksSearch > 0) {
                    clientView.getFrame().remove(clientView.getSearchScrollPane());
                    clientView.getFrame().repaint();
                }
                clientView.getFrame().add(clientView.getSearchScrollPane());
                clientView.getFrame().setVisible(true);
                nrclicksSearch++;
            }
        });

        adminView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminView.getFrame().dispose();
                loginView.paintLoginView();
            }
        });

        adminView.getImportProducts().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x, y;
                x = Integer.parseInt(adminView.getSelectProductsToBeImported()[0].getText());
                y = Integer.parseInt(adminView.getSelectProductsToBeImported()[1].getText());
                ArrayList<MenuuItem> importate = new ArrayList<>();
                deliveryService.importProductInImportedList(x, y);
                importate = Serialization.desereliazeImportedProducts();
                for (int i = 0; i < 10000; i++) {
                    adminView.getObjImported()[i][0] = null;
                    adminView.getObjImported()[i][1] = null;
                    adminView.getObjImported()[i][2] = null;
                    adminView.getObjImported()[i][3] = null;
                    adminView.getObjImported()[i][4] = null;
                    adminView.getObjImported()[i][5] = null;
                    adminView.getObjImported()[i][6] = null;
                }
                for (int i = 0; i < importate.size(); i++) {
                    adminView.getObjImported()[i][0] = importate.get(i).getTitle();
                    adminView.getObjImported()[i][1] = importate.get(i).getRating();
                    adminView.getObjImported()[i][2] = importate.get(i).getCalories();
                    adminView.getObjImported()[i][3] = importate.get(i).getProtein();
                    adminView.getObjImported()[i][4] = importate.get(i).getFat();
                    adminView.getObjImported()[i][5] = importate.get(i).getSodium();
                    adminView.getObjImported()[i][6] = importate.get(i).getPrice();
                }
                adminView.getFrame().repaint();
                adminView.getFrame().setVisible(true);
            }
        });
        adminView.getImportedProductsTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(adminView.getImportedProductsTable().getValueAt(adminView.getImportedProductsTable().getSelectedRow(),
                        adminView.getImportedProductsTable().getSelectedColumn()));
            }
        });

        adminView.getAddProductToMenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = 0;
                x = adminView.getImportedProductsTable().getSelectedRow();
                ArrayList<MenuuItem> menuuItems = new ArrayList<>();
                menuuItems = Serialization.deserializeMenuItems();
                MenuuItem m = new BaseProduct(String.valueOf(adminView.getObjImported()[x][0]), Double.parseDouble(adminView.getObjImported()[x][1].toString()),
                        Integer.parseInt(adminView.getObjImported()[x][2].toString()), Integer.parseInt(adminView.getObjImported()[x][3].toString()),
                        Integer.parseInt(adminView.getObjImported()[x][4].toString()),
                        Integer.parseInt(adminView.getObjImported()[x][5].toString()), Integer.parseInt(adminView.getObjImported()[x][6].toString()));
                boolean ok = true;
                for (MenuuItem me : menuuItems)
                    if (me.getTitle().compareTo(m.getTitle()) == 0 && me.getRating() == m.getRating() &&
                            me.getCalories() == m.getCalories() && me.getProtein() == m.getProtein() &&
                            me.getFat() == m.getFat() && me.getSodium() == m.getSodium() && me.getPrice() == m.getPrice())
                        ok =false;
                if(ok == true)
                menuuItems.add(m);

                Serialization.serializeMenuItems(menuuItems);

                //deliveryService.importProducts();
                // Serialization.serializeMenuItems((ArrayList<MenuuItem>) menuuItems);
               paintMenu(menuuItems);
            }
        });

        adminView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuuItem> menuuItems = new ArrayList<MenuuItem>();
                MenuuItem deSters = null;
                int x = 0;
                x = adminView.getMenuTable().getSelectedRow();
                menuuItems = Serialization.deserializeMenuItems();
                MenuuItem m = new BaseProduct(String.valueOf(adminView.getObjMenu()[x][0]), Double.parseDouble(adminView.getObjMenu()[x][1].toString()),
                        Integer.parseInt(adminView.getObjMenu()[x][2].toString()), Integer.parseInt(adminView.getObjMenu()[x][3].toString()),
                        Integer.parseInt(adminView.getObjMenu()[x][4].toString()),
                        Integer.parseInt(adminView.getObjMenu()[x][5].toString()), Integer.parseInt(adminView.getObjMenu()[x][6].toString()));

                for (MenuuItem me : menuuItems)
                    if (me.getTitle().compareTo(m.getTitle()) == 0 && me.getRating() == m.getRating() &&
                            me.getCalories() == m.getCalories() && me.getProtein() == m.getProtein() &&
                            me.getFat() == m.getFat() && me.getSodium() == m.getSodium() && me.getPrice() == m.getPrice())
                        deSters = me;
                menuuItems.remove(deSters);
                Serialization.serializeMenuItems(menuuItems);
                paintMenu(menuuItems);

            }
        });
        adminView.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuuItem m = new BaseProduct(adminView.getAddTextFields()[0].getText(), Double.parseDouble(adminView.getAddTextFields()[1].getText()),
                        Integer.parseInt(adminView.getAddTextFields()[2].getText()), Integer.parseInt(adminView.getAddTextFields()[3].getText()),
                        Integer.parseInt(adminView.getAddTextFields()[4].getText()), Integer.parseInt(adminView.getAddTextFields()[5].getText()),
                        Integer.parseInt(adminView.getAddTextFields()[6].getText()));
                ArrayList<MenuuItem> menuuItems = new ArrayList<>();
                menuuItems = Serialization.deserializeMenuItems();
                menuuItems.add(m);
                Serialization.serializeMenuItems(menuuItems);
                paintMenu(menuuItems);
            }
        });
        adminView.getCreateComposedProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                ArrayList<MenuuItem> deAles = new ArrayList<>();
                deAles = Serialization.desereliazeImportedProducts();
                ArrayList<MenuuItem> alese = new ArrayList<>();
                for(int i=0; i<4; i++)
                {
                    int x = r.nextInt(Integer.parseInt(adminView.getSelectProductsToBeImported()[1].getText()));
                    alese.add(deAles.get(x));
                }
                CompositeProduct compositeProduct = new CompositeProduct(alese);
                ArrayList<CompositeProduct> compositeProducts = new ArrayList<>();
                compositeProducts = Serialization.deserealizeCompositeProduct();
                compositeProducts.add(compositeProduct);
                Serialization.serializeComposedProduct(compositeProducts);
                addAndPaintCompositeProducts(compositeProducts);
                ArrayList<MenuuItem> menuuItems = new ArrayList<>();
                menuuItems = Serialization.deserializeMenuItems();
                menuuItems.add(compositeProduct);
                paintMenu(menuuItems);
                Serialization.serializeMenuItems(menuuItems);
                adminView.getFrame().repaint();
                adminView.getFrame().setVisible(true);
            }
        });

        adminView.getDeleteComposedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<CompositeProduct> compositeProducts = new ArrayList<>();
                compositeProducts = Serialization.deserealizeCompositeProduct();
                CompositeProduct de= new CompositeProduct();
                int x = adminView.getMenuTable().getSelectedRow();
                int val = Integer.parseInt(adminView.getObjMenu()[x][4].toString());
                de = compositeProducts.get(val);
                compositeProducts.remove(de);
                Serialization.serializeComposedProduct(compositeProducts);
                int ind = 9994;
                for( int k=5000; k<10000; k++)
                {
                    adminView.getObjMenu()[k][0] = null;
                    adminView.getObjMenu()[k][1] = null;
                    adminView.getObjMenu()[k][2] = null;
                    adminView.getObjMenu()[k][3] = null;
                    adminView.getObjMenu()[k][4] = null;
                    adminView.getObjMenu()[k][5] = null;
                    adminView.getObjMenu()[k][6] = null;
                }
                for(int i=0; i < compositeProducts.size(); i++)
                {
                    adminView.getObjMenu()[ind][3] = "MENIU ";
                    adminView.getObjMenu()[ind][4] = i;
                    ind++;
                    System.out.println("_"+ compositeProducts.get(0));
                    for(int j=0; j<=3; j++)
                    {
                        adminView.getObjMenu()[ind][0] = compositeProducts.get(i).getMenuItems().get(j).getTitle();
                        adminView.getObjMenu()[ind][1] = compositeProducts.get(i).getMenuItems().get(j).getRating();
                        adminView.getObjMenu()[ind][2] = compositeProducts.get(i).getMenuItems().get(j).getCalories();
                        adminView.getObjMenu()[ind][3] = compositeProducts.get(i).getMenuItems().get(j).getProtein();
                        adminView.getObjMenu()[ind][4] = compositeProducts.get(i).getMenuItems().get(j).getFat();
                        adminView.getObjMenu()[ind][5] = compositeProducts.get(i).getMenuItems().get(j).getSodium();
                        adminView.getObjMenu()[ind][6] = compositeProducts.get(i).getMenuItems().get(j).getPrice();
                        ind++;
                    }
                    ind-=11;
                }
                adminView.getFrame().repaint();
                adminView.getFrame().setVisible(true);
            }

        });
        adminView.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = adminView.getMenuTable().getSelectedRow();
                ArrayList<MenuuItem> menuuItems = new ArrayList<>();
                menuuItems = Serialization.deserializeMenuItems();
                MenuuItem m = new BaseProduct();
                m = menuuItems.get(x);
                if(adminView.getEditTextFields()[0].getText().length() > 0)
                    menuuItems.get(x).setTitle(adminView.getEditTextFields()[0].getText());
                if(adminView.getEditTextFields()[1].getText().length() > 0)
                    menuuItems.get(x).setRating(Double.parseDouble(adminView.getEditTextFields()[1].getText()));
                if(adminView.getEditTextFields()[2].getText().length() > 0)
                    menuuItems.get(x).setCalories(Integer.parseInt(adminView.getEditTextFields()[2].getText()));
                if(adminView.getEditTextFields()[3].getText().length() > 0)
                    menuuItems.get(x).setProtein(Integer.parseInt(adminView.getEditTextFields()[3].getText()));
                if(adminView.getEditTextFields()[4].getText().length() > 0)
                    menuuItems.get(x).setFat(Integer.parseInt(adminView.getEditTextFields()[4].getText()));
                if(adminView.getEditTextFields()[5].getText().length() > 0)
                    menuuItems.get(x).setSodium(Integer.parseInt(adminView.getEditTextFields()[5].getText()));
                if(adminView.getEditTextFields()[6].getText().length() > 0)
                    menuuItems.get(x).setPrice(Integer.parseInt(adminView.getEditTextFields()[6].getText()));
              Serialization.serializeMenuItems(menuuItems);
                paintMenu(menuuItems);
            }
        });
        adminView.getButtons()[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( Integer.parseInt(adminView.getTf()[0].getText()) + "    " +
                        Integer.parseInt(adminView.getTf()[1].getText()));
                deliveryService.setOrders(Serialization.desereliazeOrders()) ;
                deliveryService.generateReport1(
                        Integer.parseInt(adminView.getTf()[6].getText()),
                        Integer.parseInt(adminView.getTf()[0].getText()),
                        Integer.parseInt(adminView.getTf()[1].getText()));

            }
        });

        adminView.getButtons()[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.generateReport2(deliveryService.getProductsWithQuantity(), Integer.parseInt(adminView.getTf()[2].getText()));
            }
        });
        adminView.getButtons()[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.setOrders(Serialization.desereliazeOrders());
                deliveryService.generateReport3(Integer.parseInt(adminView.getTf()[3].getText()), Integer.parseInt(adminView.getTf()[4].getText()));
            }
        });
        adminView.getButtons()[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.setOrders(Serialization.desereliazeOrders());
                deliveryService.generateReport4(Integer.parseInt(adminView.getTf()[5].getText()));
            }
        });

    }

    public void paintMenu(ArrayList<MenuuItem> menuuItems) {
        for (int i = 0; i < 5000; i++) {
            adminView.getObjMenu()[i][0] = null;
            adminView.getObjMenu()[i][1] = null;
            adminView.getObjMenu()[i][2] = null;
            adminView.getObjMenu()[i][3] = null;
            adminView.getObjMenu()[i][4] = null;
            adminView.getObjMenu()[i][5] = null;
            adminView.getObjMenu()[i][6] = null;
        }
        for (int i = 0; i < menuuItems.size(); i++) {
            adminView.getObjMenu()[i][0] = menuuItems.get(i).getTitle();
            adminView.getObjMenu()[i][1] = menuuItems.get(i).getRating();
            adminView.getObjMenu()[i][2] = menuuItems.get(i).getCalories();
            adminView.getObjMenu()[i][3] = menuuItems.get(i).getProtein();
            adminView.getObjMenu()[i][4] = menuuItems.get(i).getFat();
            adminView.getObjMenu()[i][5] = menuuItems.get(i).getSodium();
            adminView.getObjMenu()[i][6] = menuuItems.get(i).getPrice();
        }
        adminView.getFrame().repaint();
        adminView.getFrame().setVisible(true);
    }

    void addAndPaintCompositeProducts(ArrayList<CompositeProduct> compositeProducts)
    {
        int ind = 9994;
        for(int i=0; i < compositeProducts.size(); i++)
        {
            adminView.getObjMenu()[ind][3] = "MENIU ";
            adminView.getObjMenu()[ind][4] = i;
            ind++;
            System.out.println("_"+ compositeProducts.get(0));
            for(int j=0; j<=3; j++)
            {
                adminView.getObjMenu()[ind][0] = compositeProducts.get(i).getMenuItems().get(j).getTitle();
                adminView.getObjMenu()[ind][1] = compositeProducts.get(i).getMenuItems().get(j).getRating();
                adminView.getObjMenu()[ind][2] = compositeProducts.get(i).getMenuItems().get(j).getCalories();
                adminView.getObjMenu()[ind][3] = compositeProducts.get(i).getMenuItems().get(j).getProtein();
                adminView.getObjMenu()[ind][4] = compositeProducts.get(i).getMenuItems().get(j).getFat();
                adminView.getObjMenu()[ind][5] = compositeProducts.get(i).getMenuItems().get(j).getSodium();
                adminView.getObjMenu()[ind][6] = compositeProducts.get(i).getMenuItems().get(j).getPrice();
                ind++;
            }
            ind-=10;
        }

    }
}
