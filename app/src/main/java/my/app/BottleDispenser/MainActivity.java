package my.app.BottleDispenser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private BottleDispenser dispenser;
    private SeekBar seekBar;
    private TextView money;
    private Spinner products;
    private Spinner sizes;
    private int moneyAmount;
    private String receipt = "";
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        textView = (TextView) findViewById(R.id.textView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        money = (TextView) findViewById(R.id.textView3);
        products = (Spinner) findViewById(R.id.spinner);
        sizes = (Spinner) findViewById(R.id.spinner2);
        dispenser = BottleDispenser.getBottleDispenser(textView);
        money.setText("Amount of money to add: "+seekBar.getProgress());
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Products));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Sizes));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        products.setAdapter(myAdapter1);
        sizes.setAdapter(myAdapter2);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        moneyAmount = progress;
                        money.setText("Amount of money to add: "+progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        money.setText("Amount of money to add: "+moneyAmount);
                    }
                }
        );
    }

    public void buyBottle(View v) {
        String type = products.getSelectedItem().toString();
        float size = Float.valueOf(sizes.getSelectedItem().toString());
        receipt = dispenser.buyBottle(type, size);
    }

    public void addMoney(View v) {
        float amount = (float) moneyAmount;
        dispenser.addMoney(amount);
        seekBar.setProgress(0);
    }

    public void returnMoney(View v) {
        dispenser.returnMoney();
    }

    public void printReceipt(View v) {
        int number;
        ReceiptPrinter printer = ReceiptPrinter.getReceiptPrinter(context);
        if (receipt.matches("")) {
            textView.setText("Last Purchase not found, receipt not printed!");
            return;
        }
        number = printer.saveReceipt(receipt);
        if (number == 0) {
            textView.setText("Printing error, receipt not printed!");
        }
        else if (number == 1) {
            textView.setText("Receipt printed succesfully.");
        }
    }
}
