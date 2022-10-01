package DataLayer;

import BusinessLayer.MenuuItem;
import DataLayer.Writter;

import javax.naming.directory.SearchResult;
import java.awt.*;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.MarshalledObject;
import java.util.*;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;

public class DeliveryService implements IDeliveryServiceProcessing, Serializable {

    private ArrayList<MenuuItem> productsWithQuantity = new ArrayList<>();
    private Map<Order, ArrayList<MenuuItem>> orders = new HashMap<>();
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Order, ArrayList<MenuuItem>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Order, ArrayList<MenuuItem>> orders) {
        this.orders = orders;
    }

    public ArrayList<MenuuItem> getProductsWithQuantity() {
        return productsWithQuantity;
    }

    public void setProductsWithQuantity(ArrayList<MenuuItem> productsWithQuantity) {
        this.productsWithQuantity = productsWithQuantity;
    }

    public void createOrder(Order o, ArrayList<MenuuItem> menuuItems) {
        assert menuuItems.size() >= 1 : "The order must contain at least one product";
        assert  o != null : "The order can not be null";
        orders = Serialization.desereliazeOrders();
        productsWithQuantity = Serialization.deserializeMenuItems();
        ArrayList<User> users = new ArrayList<>();
        users = Serialization.deserializeUser();
        users.forEach(user -> {
            if (user.getUsername().compareTo(username) == 0)
                user.setNr_orders(user.getNr_orders() + 1);
        });
        Serialization.serializeUser(users);
        for (MenuuItem comandate : menuuItems) {
            for (MenuuItem product : productsWithQuantity) {
                if (product.getTitle().compareTo(comandate.getTitle()) == 0) {
                    product.setQuantity(product.getQuantity() + 1);
                    System.out.println(product.getTitle() + "   " + product.getQuantity());
                }
            }
        }
        ArrayList<MenuuItem> deInserat= new ArrayList<>();
        Serialization.serializeMenuItems(productsWithQuantity);
        for (int i = 0; i < productsWithQuantity.size(); i++)
            System.out.println(productsWithQuantity.get(i));
        productsWithQuantity.forEach(p -> {
            menuuItems.forEach(menuuItem -> {
                if(menuuItem.getTitle().compareTo(p.getTitle()) == 0)
                    deInserat.add(p);
            });
        });
        orders.put(o, deInserat);
        Serialization.serializeOrders(orders);
    }





    @Override
    public ArrayList<MenuuItem> importProducts() {
        List<MenuuItem> menuItems = new ArrayList<>();
        BufferedReader buf = null;
        try {
            buf = new BufferedReader(new FileReader("C:\\Users\\flore\\Desktop\\Faculta\\AN2_SEM2\\TP\\PT2022_30223_FLOREAN_EUSEBIU_ASSIGMENT_4\\products.csv"));
            String currentLine = buf.readLine();
            while ((currentLine = buf.readLine()) != null)  //returns a boolean value
            {
                String[] parts = currentLine.split(",");
                MenuuItem menuuItem = new MenuuItem(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6])) {
                    @Override
                    public int computePrice() {
                        return 0;
                    }
                };
                if(containsProduct(menuItems, menuuItem) == false)
                menuItems.add(menuuItem);
            }
            buf.close();  //closes the buffer
            Serialization.serializeMenuItems((ArrayList<MenuuItem>) menuItems);
            // menuItems = new ArrayList<>();
            //menuItems = producstList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (ArrayList<MenuuItem>) menuItems;
    }

    public void insertIn() {
        MenuuItem menuuItem = new MenuuItem("da", 1, 1, 1, 1, 1, 1) {
            @Override
            public int computePrice() {
                return 0;
            }
        };
        ArrayList<MenuuItem> menuuItems = new ArrayList<>();
        menuuItems.add(menuuItem);
        Serialization.serializeMenuItems(menuuItems);
    }


    public ArrayList<MenuuItem> importProductInImportedList(int x, int y) {
        assert x>=0 : "x must be greater then 0";
        assert y< 10000 : "y must be less then 10000";
        List<MenuuItem> menuItems = new ArrayList<>();
        BufferedReader buf = null;
        ArrayList<MenuuItem> importate = null;
        try {
            buf = new BufferedReader(new FileReader("C:\\Users\\flore\\Desktop\\Faculta\\AN2_SEM2\\TP\\PT2022_30223_FLOREAN_EUSEBIU_ASSIGMENT_4\\products.csv"));
            String currentLine = buf.readLine();
            while ((currentLine = buf.readLine()) != null)  //returns a boolean value
            {
                String[] parts = currentLine.split(",");
                MenuuItem menuuItem = new MenuuItem(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6])) {
                    @Override
                    public int computePrice() {
                        return 0;
                    }
                };
                if(containsProduct(menuItems, menuuItem) == false)
                menuItems.add(menuuItem);
            }
            buf.close();  //closes the buffer
            importate = new ArrayList<>();
            for (int i = x; i <= y; i++)
                importate.add(menuItems.get(i));
            Serialization.serializeImportedProducts((ArrayList<MenuuItem>) importate);
            // menuItems = new ArrayList<>();
            //menuItems = producstList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (ArrayList<MenuuItem>) importate;
    }

    public ArrayList<MenuuItem> searchProduct(ArrayList<MenuuItem> products, String a, double b, int c, int d, int e, int f, int g) {
        assert products != null : "The list of products can not be empty";
        //assert field != null && value !=null : "The fields can not be null";
        ArrayList<MenuuItem> foundProducts = new ArrayList<>();
    /*    products.forEach(p -> {
            int k = 0;
            switch (field) {
                case "title":
                    if (p.getTitle().contains(value) == true)
                        k++;
                    break;
                case "rating":
                    if (Double.parseDouble(value) == p.getRating())
                        k++;
                    break;
                case "calories":
                    if (Integer.parseInt(value) == p.getCalories())
                        k++;
                    break;
                case "protein":
                    if (Integer.parseInt(value) == p.getProtein())
                        k++;
                    break;
                case "fat":
                    if (Integer.parseInt(value) == p.getFat())
                        k++;
                    break;
                case "sodium":
                    if (Integer.parseInt(value) == p.getSodium())
                        k++;
                    break;
                case "price":
                    if (Integer.parseInt(value) == p.getPrice())
                        k++;
                    break;
            }
        if (k > 0)
                foundProducts.add(p);
        });
        */
     foundProducts = (ArrayList<MenuuItem>) products.stream().filter(p -> p.getTitle().contains(a)&& p.getRating() >= b
             && p.getCalories() >= c && p.getProtein() >= d && p.getFat() >= e && p.getSodium() >= d && p.getPrice() >= e).collect(Collectors.toList());

        return foundProducts;
    }

    @Override
    public void generateReport1( int day, int startHour, int endHour) {

        assert day <= 31 : "The day must be smaller then 31";
        assert startHour < endHour :"The end hour must be greater or equall then start hour";
        Map<Order, ArrayList<MenuuItem>> selected = new HashMap<>();
        /*orders.forEach((o, m) -> {
                    int currentHour = o.getNow().getHour();
                    if (currentHour <= endHour && currentHour >= startHour && o.getNow().getDayOfMonth() == day) {
                        selected.put(o, m);
                    }
                }
        );*/
        selected = (Map<Order, ArrayList<MenuuItem>>) orders.entrySet().stream().filter(
                o-> o.getKey().getNow().getHour() < endHour && o.getKey().getNow().getHour() > startHour &&
                        o.getKey().getNow().getDayOfMonth() == day).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        Writter.writeRep1(selected, day, startHour, endHour);
    }


    @Override
    public void generateReport2(ArrayList<MenuuItem> ordered, int nr) {
        assert ordered!= null :"You must have at least one order";
        assert nr >=0 : "The number must be greater then 0";
        ArrayList<MenuuItem> selected = new ArrayList<>();
       /* ordered.forEach(menuuItem ->
        {
            if (menuuItem.getQuantity() >= nr)
                selected.add(menuuItem);
        });*/
        selected = (ArrayList<MenuuItem>) ordered.stream().filter(o-> o.getQuantity() >= nr).collect(Collectors.toList());
        Writter.writeRep2(selected, nr);
    }


    @Override
    public void generateReport3( int getPrice, int nr) {
        assert getPrice>0 : "Price must be greater then 0";
        assert nr>0 : "The number must be greater then 0";
        Map<Order, ArrayList<MenuuItem>> selectedOrders = new HashMap<>();
        orders.forEach((order, menuuItems) -> {
            if (order.getTotalPrice() >= getPrice)
                selectedOrders.put(order, menuuItems);
            else
                System.out.println("da");
        }); //selectam comenzile cu pretul mai mare decat pretul dat

        ArrayList<User> selectedUsers = new ArrayList<>();
        ArrayList<User> allUsers = new ArrayList<>();
        ArrayList<User> finalUsers = new ArrayList<>();
        allUsers = Serialization.deserializeUser();
        allUsers.forEach(user -> {
            if (user.getNr_orders() >= nr) {
                if (selectedUsers.contains(user) == false)
                    selectedUsers.add(user); //selectam userii cu nr_comenzi mai mare ca nr dat
            }
        });
        selectedOrders.forEach((order, menuuItems) -> {

            selectedUsers.forEach(user -> {
                if (user.getUsername().compareTo(order.getClientUserName()) == 0 && finalUsers.contains(user) == false )
                    finalUsers.add(user);
            });
        });


        Writter.writeRep3(finalUsers, getPrice, nr);
    }
    @Override
    public void generateReport4(int day) {
        assert day < 31 : "The day must be less then 31";
        assert day > 0 : "The day must be greater then 0";
        Map<Order, MenuuItem> selected = new HashMap<>();
      /*  orders.forEach((order, menuuItems) -> {
            if (order.getNow().getDayOfMonth() == day) {
                menuuItems.forEach(menuuItem -> {
                    MenuuItem m = exist(selected, menuuItem);
                    if (m == null)
                        selected.put(menuuItem, menuuItem.getQuantity());
                    else {
                        if (m.getQuantity() < menuuItem.getQuantity())
                            m.setQuantity(menuuItem.getQuantity());
                    }

                });*/
              //  selected = (Map<Order, MenuuItem>) orders.entrySet().stream().filter(o-> o.getKey().getNow().getDayOfMonth() == day).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
              Map<MenuuItem, Integer> selected1 = new HashMap<>();
                selected.forEach((order, menuuItem) -> {
                    MenuuItem m = exist(selected1, menuuItem);
                    if(m == null)
                        selected1.put(menuuItem, menuuItem.getQuantity());
                    else {
                        if (m.getQuantity() < menuuItem.getQuantity())
                            m.setQuantity(menuuItem.getQuantity());
                    }
                });
        Writter.writeRep4(selected1);
    }

    MenuuItem exist(Map<MenuuItem, Integer> menuuItems, MenuuItem menuuItem) {
        MenuuItem m = new MenuuItem() {
            @Override
            public int computePrice() {
                return 0;
            }
        };
        for (Map.Entry<MenuuItem, Integer> entry : menuuItems.entrySet()) {
            m = entry.getKey();
            if (m.getTitle().compareTo(menuuItem.getTitle()) == 0)
                return m;
        }
        return null;
    }

    boolean containsProduct(List<MenuuItem> products, MenuuItem m)
    {
        for(MenuuItem mm : products)
            if(mm.getTitle().compareTo(m.getTitle()) == 0)
                return true;
        return false;
    }

}
