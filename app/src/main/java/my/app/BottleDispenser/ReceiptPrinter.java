package my.app.BottleDispenser;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

class ReceiptPrinter {
    private static ReceiptPrinter print = null;
    private String filename = "Receipt.txt";
    Context context = null;

    private ReceiptPrinter(Context cntx) {
        context = cntx;
    }

    public static ReceiptPrinter getReceiptPrinter(Context cntx) {
        if (print == null) {
            print = new ReceiptPrinter(cntx);
        }
        return(print);
    }
    public int saveReceipt(String s) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            osw.write(s);
            osw.close();
            return(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return(0);
        } catch (IOException e) {
            e.printStackTrace();
            return(0);
        }

    }

}
