package DataLayer;

import BusinessLayer.MenuuItem;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
    private int id;
    private String clientUserName;
    transient private DateTimeFormatter dateTimeFormatter ; //fara transient eroare not serializable
    private LocalDateTime now;
   private int totalPrice;

    public Order(int id, String clientUserName) {
        this.id = id;
        this.clientUserName = clientUserName;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.now = LocalDateTime.now();
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    @Override
    public String toString() {
        return "Order{" + " id=" + id  + " clientUserName='" + clientUserName + ", time=" + now + " }";
    }
}
