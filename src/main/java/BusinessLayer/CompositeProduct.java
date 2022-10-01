package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuuItem  implements Serializable {
    ArrayList<MenuuItem> menuItems;

    public CompositeProduct( ArrayList<MenuuItem> menuItems) {
        this.menuItems = menuItems;
    }
    public CompositeProduct(){}

    @Override
    public String getTitle() {
        String rez = null;
   for(MenuuItem m : menuItems)
       rez += m.getTitle() + " ";
   return rez;
    }
    public double getRating()
    {
        double rez = 0;
        for(MenuuItem m : menuItems)
            rez+=m.getRating();
        return rez/menuItems.size();
    }

    public int getCalories()
    {
        int rez = 0;
        for(MenuuItem m : menuItems)
            rez+= m.getCalories();
        return rez;
    }

    @Override
    public int getProtein() {
        int rez = 0;
        for(MenuuItem m : menuItems)
            rez+= m.getProtein();
        return rez;
    }

    @Override
    public int getFat() {
        int rez = 0;
        for(MenuuItem m : menuItems)
            rez+= m.getFat();
        return rez;
    }

    @Override
    public int getSodium() {
        int rez = 0;
        for(MenuuItem m : menuItems)
            rez+= m.getSodium();
        return rez;
    }

    @Override
    public int getPrice() {
        int rez = 0;
        for(MenuuItem m : menuItems)
            rez+= m.getPrice();
        return rez;
    }

    @Override
    public int computePrice() {
        int price=0;
        for(MenuuItem menuItem : menuItems)
            price += menuItem.getPrice();
        return price;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "menuItems=" + menuItems +
                '}';
    }

    public ArrayList<MenuuItem> getMenuItems() {
        return menuItems;
    }
}
