package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
    public static ItemList boughtItems = new ItemList();
    static Calendar c = Calendar.getInstance();
    static SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    public static final String FILE_NAME = "report";//date.format(c.getTime()).toString();

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
    public static String smallTag = " קטן";
    public static String largeTag = " גדול";



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

    static int itemName = 0;
    static int itemPrice = 1;

    static String type = "";




    public static void sharedUpdateEmployee(String employeeName)
    {
        Log.d("TKT_cashier","updateEmployee===================");

        progressEdit = checkPrefs.edit();
        progressEdit.putString(EMPLOYEE_NAME, employeeName);
        progressEdit.commit();
    }


    public static void updatePayment(Button item)
    {//update payment in textView, add item to list
        Log.d("TKT_cashier","updatePayment===================");

        String buttonText = item.getText().toString();
        String [] lines = buttonText.split("\n");
        double price = Double.parseDouble(lines[itemPrice]);

        if(item.getHint().equals(ItemScreen.context.getString(R.string.drink))) {
            if (price == SMALL_DRINK_PRICE) {
                lines[itemName] += smallTag;
                Log.d("TKT_cashier", "name: " + lines[itemName]);

            } else
                lines[itemName] += largeTag;
        }


        //handle itemList
        ItemList.Item currItem = boughtItems.contains(lines[itemName]);
        if(currItem != null)
        {//item exists
            Log.d("TKT_cashier", "contained in boughtItems");
            currItem.increase();
        }
        else
        {//create new item
            ItemList.Item newItem = new ItemList().new Item(lines[itemName]);
            boughtItems.addItem(newItem);
        }


        //handle price textView
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


    public static void cancel()
    {
        Log.d("TKT_cashier","cancel===================");

        if(dialog.isShowing())
            dialog.dismiss();
        else//meaning this was called from cancelCheck
        {
            if(!paymentText.getText().toString().equals("")) {
                paymentText.setText("");
                boughtItems.clearAll();
                AlertDialog.Builder message = new AlertDialog.Builder(ItemScreen.context);
                message.setMessage(R.string.cancelled).create();
                message.show();
            }
            else
                emptyCheck();
        }
            
    }

    public static String openItemList()
    {
        Log.d("TKT_cashier","openItemList===================");
        ObjectInputStream objectInputStream;
        File file = new File(ItemScreen.context.getFilesDir(), FILE_NAME);
        try
        {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            return (String)objectInputStream.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.d("TKT_cashier","file does not exist");
            return null;
        }
    }

    public static void saveHash(ItemList hash)
    {
        Log.d("TKT_cashier","saveHash===================");

        File file = new File(ItemScreen.context.getFilesDir().toString(), FILE_NAME);
        Log.d("TKT_cashier","is file null: "+file.exists());
        try {
            Log.d("TKT_cashier","try");
            file.createNewFile();
            Log.d("TKT_cashier","file is created: "+file.exists());
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            Log.d("TKT_cashier","shopList: "+hash.toString());
            outputStream.writeObject(hash.toString());
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            Log.d("TKT_cashier","catch me if you can1");
            e.printStackTrace();
        }
    }

    public static ItemList stringToItemList(String listString)
    {
        Log.d("TKT_cashier","strignToItemList===================");
        ItemList SavedShoppingList = new ItemList();
        String [] items = listString.split(System.getProperty("line.separator"));
        for(int i = 0; i < items.length; i++)
        {
            Log.d("TKT_cashier","item[i]: "+items[i]);

            ItemList.Item newItem = new ItemList().new Item(items[i]);
            Log.d("TKT_cashier","newItem: "+newItem.toString());
            SavedShoppingList.addItem(newItem);
        }
        return SavedShoppingList;
    }

    public static void check()
    {
        Log.d("TKT_cashier","check===================");

        if(!paymentText.getText().toString().equals("")) {
            String listString = openItemList();
            Log.d("TKT_cashier","listString: "+listString);
            //Log.d("TKT_cashier","savedShopList: "+SavedShoppingList.toString());
            if(listString == null) {
                saveHash(boughtItems);
            }
            else
            {//merging both hashMaps
                Log.d("TKT_cashier","merging");
                ItemList SavedShoppingList = stringToItemList(listString);//openItemList();
                Log.d("TKT_cashier","file exists");
                SavedShoppingList.merge(boughtItems);


                //clear current file and update
                File oldFile = new File(ItemScreen.context.getFilesDir().toString(), FILE_NAME);//// TODO: 9/8/2017 check if redundant
                boolean deleted = oldFile.delete();
                Log.d("TKT_cashier","isDeleted: "+deleted);
                saveHash(SavedShoppingList);

            }
            boughtItems.clearAll();


            AlertDialog.Builder message = new AlertDialog.Builder(ItemScreen.context);
            message.setMessage(R.string.done).create();
            message.show();
            paymentText.setText("");
            Log.d("TKT_cashier","OrderSubmitted $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");
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

    public static void displayReport(ListView listView)
    {
        Log.d("TKT_cashier","displayReport===================");
        String SavedShoppingList = openItemList();
        if(SavedShoppingList != null)
        {
            // TODO: 9/8/2017 this cast vv is not good, look for an alternative 
            ArrayList<String> listOfItems = (ArrayList<String>) Arrays.asList(SavedShoppingList.split(System.getProperty("line.separator")));
            ArrayAdapter<String> adapter = new ArrayAdapter(Report.context, R.layout.custom_list_view, listOfItems);
            listView.setAdapter(adapter);
        }

        /*
        ItemList SavedShoppingList = openItemList();
        if(SavedShoppingList != null)
        {
            ArrayList<String> listOfItems = SavedShoppingList.getArrayList();
            ArrayAdapter<String> adapter = new ArrayAdapter(Report.context, R.layout.custom_list_view, listOfItems);
            listView.setAdapter(adapter);
        }
        */
    }








}
