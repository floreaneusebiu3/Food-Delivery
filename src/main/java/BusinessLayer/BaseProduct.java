package BusinessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuuItem implements Serializable {


    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }
    public BaseProduct(){}

    @Override
    public int computePrice() {
        return getPrice();
    }
}
