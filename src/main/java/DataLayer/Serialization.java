package DataLayer;

import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Serialization implements Serializable {
    public static void serializeUser(ArrayList<User> users)
    {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("users.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> deserializeUser()
    { ArrayList<User> users = new ArrayList<>();
        try {
            FileInputStream fileInputStream= new FileInputStream("users.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            users = (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void serializeMenuItems(ArrayList<MenuuItem> menuItems)
    {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("products.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(menuItems);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<MenuuItem> deserializeMenuItems()
    { ArrayList<MenuuItem> menuItems = new ArrayList<>();
        try {
            FileInputStream fileInputStream= new FileInputStream("products.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            menuItems = (ArrayList<MenuuItem>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public static void serializeImportedProducts(ArrayList<MenuuItem> menuItems)
    {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("importedProducts.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(menuItems);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<MenuuItem> desereliazeImportedProducts()
    { ArrayList<MenuuItem> menuItems = new ArrayList<>();
        try {
            FileInputStream fileInputStream= new FileInputStream("importedProducts.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            menuItems = (ArrayList<MenuuItem>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public static void serializeComposedProduct(ArrayList<CompositeProduct> compositeProducts)
    {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("compositeProducts.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(compositeProducts);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<CompositeProduct> deserealizeCompositeProduct()
    { ArrayList<CompositeProduct> compositeProducts = new ArrayList<>();
        try {
            FileInputStream fileInputStream= new FileInputStream("compositeProducts.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            compositeProducts = (ArrayList<CompositeProduct>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return compositeProducts;
    }

    public static void serializeOrders(Map<Order, ArrayList<MenuuItem>> orders)
    {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("orders.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(orders);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Order, ArrayList<MenuuItem>> desereliazeOrders()
    { Map<Order, ArrayList<MenuuItem>> orders = new HashMap<>();
        try {
            FileInputStream fileInputStream= new FileInputStream("orders.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            orders = (Map<Order, ArrayList<MenuuItem>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orders;
    }






}
