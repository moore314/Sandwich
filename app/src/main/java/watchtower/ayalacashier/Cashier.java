package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Locale;

/**
 * Created by Moore on 9/5/2017.
 */

public class Cashier {

    protected static SharedPreferences checkPrefs;
    public static SharedPreferences.Editor progressEdit;
    //====== sharedPref ======
    public static final String EMPLOYEE_NAME = "employeeName";
    public static final String PASSWORD = "sandwich";
    public static final String CHECK_PREFS = "cashier";
    public static final String PRICES = "prices";
    public static final String PRICE_CHANGED = "priceChanged"; //this is to determine if prices need to be loaded from shared or not
    public static LinkedList<String> shoppingList = new LinkedList<String>();

    //Drinks - // TODO: 9/6/2017 add option in menu to update prices, for that, i need to update it in itemScreen too!!!
    public static double I_ICE5 = 5;
    public static double I_ICE7 = 7;
    public static double I_SLURPEE5 = 5;
    public static double I_SLURPEE7 = 7;
    public static double I_LEMONADE5 = 5;
    public static double I_LEMONADE7 = 7;
    public static double I_ORANGE5 = 5;
    public static double I_ORANGE7 = 7;
    public static double I_HOT_CHOC = 5;
    public static double I_CHOC = 3;
    public static double I_CAPPUCCINO = 5;
    public static double I_CAN5 = 5;
    public static double I_CAN6 = 6;
    public static double I_WATER = 5;

    public static double SMALL_DRINK_PRICE = 5;
    public static double LARGE_DRINK_PRICE = 7;
    public static double SMALL_DRINK_CAPACITY = 0.250;
    public static double LARGE_DRINK_CAPACITY = 0.300;



    public static String S_ICE5 = "אייס\n"  +I_ICE5;
    public static String S_ICE7 = "אייס\n"  +I_ICE7;
    public static String S_SLURPEE5 = "ברד\n"  +I_SLURPEE5;
    public static String S_SLURPEE7 =  "ברד\n"  + I_SLURPEE7;
    public static String S_LEMONADE5 = "לימונדה\n"  +I_LEMONADE5;
    public static String S_LEMONADE7 = "לימונדה\n"  +I_LEMONADE7;
    public static String S_ORANGE5 = "תפוזים\n"  +I_ORANGE5;
    public static String S_ORANGE7 = "תפוזים\n" +I_ORANGE7;
    public static String S_HOT_CHOC = I_HOT_CHOC+"שוקו חם\n";
    public static String S_CHOC = "שוקו\n"  +I_CHOC;
    public static String S_CAPPUCCINO = "נס קפה\n"  +I_CAPPUCCINO;
    public static String S_CAN5 = "פחית\n"  +I_CAN5;
    public static String S_CAN6 = "פחית\n"  +I_CAN6;
    public static String S_WATER = "מים\n"  +I_WATER;


    public static TextView paymentText;
    public static Dialog dialog;
    public static Locale il = new Locale("iw","IL");

    static int firstLine = 0;
    static String type = "";




    public static void sharedUpdateEmployee(String employeeName)
    {
        Log.d("TKT_cashier","updateEmployee");

        progressEdit = checkPrefs.edit();
        progressEdit.putString(EMPLOYEE_NAME, employeeName);
        progressEdit.commit();
    }


    public static void updatePayment(Button item)
    {//update payment in textView, add item to list
        Log.d("TKT_cashier","updatePayment");

        String name = item.getText().toString();
        String [] lines = name.split("\n");
        Cashier.shoppingList.add(lines[firstLine]);
        Log.d("TKT_cashier","item name: "+lines[firstLine]);

        double price = getPrice(item);
        Log.d("TKT_cashier","price: "+price);
        if(paymentText.getText().toString().equals(""))
            paymentText.setText(price+"");
        else {
            double currPayment = Double.parseDouble(paymentText.getText().toString());
            price+=currPayment;
            Log.d("TKT_cashier", "else");
            paymentText.setText(price+"");
        }
        dialog.dismiss();
    }

    public static double getPrice(Button item)
    {
        Log.d("TKT_cashier","getPrice");

        String tag = item.getTag().toString();
        Log.d("TKT_cashier","tag: "+tag);
        return Double.parseDouble(tag);
    }

    public static void cancel()
    {
        Log.d("TKT_cashier","cancel");

        if(dialog.isShowing())
            dialog.dismiss();
        else//meaning this was called from cancelCheck
        {
            if(!paymentText.getText().toString().equals("")) {
                paymentText.setText("");
                AlertDialog.Builder message = new AlertDialog.Builder(ItemScreen.context);
                message.setMessage(R.string.cancelled).create();
                message.show();
            }
            else
                emptyCheck();
        }
            
    }
    
    public static void check()
    {//// TODO: 9/6/2017 add hint of small large and none for size reference 
        Log.d("TKT_cashier","check");

        if(!paymentText.getText().toString().equals("")) {
            progressEdit = checkPrefs.edit();

            while (!shoppingList.isEmpty()) {
                String currItem = shoppingList.poll();
                Log.d("TKT_cashier", "currentItem: " + currItem);
                int currAmount = checkPrefs.getInt(currItem, 0);
                Log.d("TKT_cashier", "currentAmountB4: " + currAmount);
                currAmount++;
                progressEdit.putInt(currItem, currAmount);
                progressEdit.commit();
            }

            AlertDialog.Builder message = new AlertDialog.Builder(ItemScreen.context);
            message.setMessage(R.string.done).create();
            message.show();
            paymentText.setText("");
        }
        else
        {
            emptyCheck();
        }


    }


    public static void emptyCheck()
    {
        AlertDialog.Builder message = new AlertDialog.Builder(ItemScreen.context);
        message.setMessage(R.string.emptyCheck).create();
        message.show();
    }









}
