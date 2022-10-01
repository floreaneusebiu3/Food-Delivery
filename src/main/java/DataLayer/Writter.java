package DataLayer;

import BusinessLayer.MenuuItem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Writter {


    public static void createBill(Map<Order, ArrayList<MenuuItem>> orders,Order o,  int price) {
        File f;
        FileWriter fstream;
        BufferedWriter buf;
        Random r = new Random();
        f = new File("bill_" + "_" + r.nextInt() + ".txt");
        try {
            fstream = new FileWriter(f, true);
            buf = new BufferedWriter(fstream);
            buf.write("____________________________________________________________________________________________");
            buf.newLine();
            buf.write("                            BILL: " );
            buf.newLine();
            buf.newLine();
           for (Map.Entry<Order, ArrayList<MenuuItem>> entry: orders.entrySet()) {
               try {
                   if(o.getId() == entry.getKey().getId())
                       for(int i=0; i< entry.getValue().size(); i++)
                   buf.write(String.valueOf(entry.getValue().get(i)) + " \n");
                   buf.newLine();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }


            buf.write("Price: " + price + " lei;");
            buf.newLine();
            buf.write("____________________________________________________________________________________________");
            buf.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeRep1(Map<Order, ArrayList<MenuuItem>> orders,int day,  int startHour, int endHour) {
        File f;
        FileWriter fstream;
        BufferedWriter buf;
        f = new File("report1.txt");
        try {
            fstream = new FileWriter(f, true);
            buf = new BufferedWriter(fstream);
            buf.write("____________________________________________________________________________________________");
            buf.newLine();
            buf.write("                            REPORT 1: " );
            buf.newLine();
            buf.write(startHour + "--------"+ endHour + "    DAY: "+ day);
            buf.newLine();
           orders.forEach((order, menuuItems) -> {
               try {
                   buf.write("ORDER\n");

               } catch (IOException e) {
                   e.printStackTrace();
               }
               menuuItems.forEach(menuuItem -> {
                   try {
                       buf.write(menuuItem.getTitle()+"\n");
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               });

           });
            buf.newLine();
            buf.write("____________________________________________________________________________________________");
            buf.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeRep2(ArrayList<MenuuItem> selected, int nr) {
        File f;
        FileWriter fstream;
        BufferedWriter buf;
        f = new File("report2.txt");
        try {
            fstream = new FileWriter(f, true);
            buf = new BufferedWriter(fstream);
            buf.write("____________________________________________________________________________________________");
            buf.newLine();
            buf.write("                            REPORT 1: " );
            buf.newLine();
            buf.write("Products ordered more than " + nr + " times:" );
            buf.newLine();
         selected.forEach(menuuItem -> {
             try {
                 buf.write(menuuItem.getTitle() + "------"+menuuItem.getQuantity()+ "\n");
             } catch (IOException e) {
                 e.printStackTrace();
             }

         });
            buf.newLine();
            buf.write("____________________________________________________________________________________________");
            buf.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeRep3(ArrayList<User> selected, int getPrice, int nr) {
        File f;
        FileWriter fstream;
        BufferedWriter buf;
        f = new File("report3.txt");
        try {
            fstream = new FileWriter(f, true);
            buf = new BufferedWriter(fstream);
            buf.write("____________________________________________________________________________________________");
            buf.newLine();
            buf.write("                            REPORT 3: " );
            buf.newLine();
            buf.write(" Clients:" );
            buf.newLine();
            selected.forEach(user -> {
                try {
                    buf.write(user.getUsername() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
            buf.newLine();
            buf.write("____________________________________________________________________________________________");
            buf.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeRep4(Map<MenuuItem, Integer> selected) {
        File f;
        FileWriter fstream;
        BufferedWriter buf;
        f = new File("report4.txt");
        try {
            fstream = new FileWriter(f, true);
            buf = new BufferedWriter(fstream);
            buf.write("____________________________________________________________________________________________");
            buf.newLine();
            buf.write("                            REPORT 4: " );
            buf.newLine();
            buf.write(" Products:" );
            buf.newLine();
            selected.forEach((menuuItem, nr) -> {
                try {
                    buf.write(menuuItem.getTitle() + "   " + nr + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            buf.newLine();
            buf.write("____________________________________________________________________________________________");
            buf.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}