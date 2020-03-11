package my.app.BottleDispenser;

import android.widget.TextView;

import java.util.Scanner;
import java.util.ArrayList;

class BottleDispenser {
    static Scanner scan = new Scanner(System.in);
    private int bottles;
    private float money;
    private ArrayList<Bottle> bottle_array = new ArrayList<Bottle>();
    private static BottleDispenser btl = null;
    private TextView txtView;

    private BottleDispenser(TextView textView) {
        txtView =  textView;
        bottles = 9;
        money = 0;
        int k = 0;
        for(int i = 0;i<bottles;i++) {
            Bottle bottle = new Bottle(i, k);
            bottle_array.add(bottle);
            k++;
        }
    }

    public static BottleDispenser getBottleDispenser(TextView textView) {
        if (btl == null) {
            btl = new BottleDispenser(textView);
        }
        return(btl);
    }

    public void addMoney(float amount) {
        money = money + amount;
        txtView.setText("Klink! Added more money!");
    }

    public String buyBottle(String type, float size) {
        String receipt = "";
        float price = 0f;
        String name = "";
        String finalName = "";
        float bottlesize;
        int i = 0;
        if (bottles <= 0) {
            txtView.setText("No more bottles!");
            return(receipt);
        }
        Bottle temp = new Bottle(0, 0);
        for (i = 0; i < bottles; i++) {
            temp = bottle_array.get(i);
            price = temp.getPrice();
            name = temp.getName();
            bottlesize = temp.getSize();
            if ((type.matches(name)) && (size == bottlesize)) {
                finalName = name;
                break;
            }
        }
        if (finalName.matches("")) {
            txtView.setText("Chosen drink not available!");
            return(receipt);
        }
        if (money < price) {
            txtView.setText("Add money first!");
            return(receipt);
        }
        else {
            bottles--;
            money = money - price;
            txtView.setText(String.format("KACHUNK! %s; size: %.1f came out of the dispenser!", name, size));
            bottle_array.remove(i);
            receipt = String.format("Receipt:\nProduct purchased: %s\nPrice: %.1f", name, price);
            return(receipt);
        }
    }

    public void returnMoney() {
        String formattedMoney = String.format("%.02f", money);
        txtView.setText(String.format("Klink klink. Money came out! You got %s€ back", formattedMoney));
        money = 0;
    }
    /*public void displayBottles() {
        int number = 0;
        for (Bottle bottle : bottle_array) {
            number += 1;
            System.out.println(number+". Name: "+bottle.getName());
            System.out.println("	Size: "+bottle.getSize()+"	Price: "+bottle.getPrice());

        }
    }*/
}
