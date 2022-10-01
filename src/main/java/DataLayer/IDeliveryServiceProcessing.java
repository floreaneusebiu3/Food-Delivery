package DataLayer;

import BusinessLayer.MenuuItem;

import java.util.ArrayList;

public interface IDeliveryServiceProcessing {
    /***
     *
     * @return returneaza produsule importate din fisierul csv sub forma unui arraylist de MenuuItem
     */
    public ArrayList<MenuuItem> importProducts();

    /***
     *
     * @param day = reprezinta ziua pentru care se creeaza raportul  >0
     * @param startHour = parametrul prin care se specifica ora de inceput a comenzilor de luat in evidenta >0 && <endHour
     * @param endHour =parametrul prin care se specifica ora de final a comenzilor de luat in evidenta  >startHour & <0
     */
    public void generateReport1(int day, int startHour, int endHour);

    /***
     *
     * @param ordered un array care cuprinde toate comenzile efectuate != NULL
     * @param nr >0
     */
    public void generateReport2(ArrayList<MenuuItem> ordered, int nr);

    /***
     *
     * @param getPrice = PRETUL DE PRAG, >0
     * @param nr = numarul minim de produse, >0
     */
    public void generateReport3(int getPrice, int nr);

    /***
     *
     * @param a reprezinta ziua pentru care se face raportul, a<31, a>0
     */
    public void generateReport4(int a);

    /***
     *
     * @param products
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     * @return
     */
    public ArrayList<MenuuItem> searchProduct(ArrayList<MenuuItem> products, String a, double b, int c, int d, int e, int f, int g);
    /***
     *
     * @param x INCEPUTUL INTERVALULUI >0
     * @param y FINALUL INTERVALULUI <10000
     * @return
     */
    public ArrayList<MenuuItem> importProductInImportedList(int x, int y);
    public void createOrder(Order o, ArrayList<MenuuItem> menuuItems);

    }
