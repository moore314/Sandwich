package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import java.util.List;
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
    public static final String REPORT_SENT = "report_sent";

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

    //Sandwiches
    public static double SPICY_EGGPLANT = 15;
    public static double SHAKSHUKA = 15;
    public static double SABIH = 15;
    public static double GREEN_OMLETTE = 15;
    public static double TUNA_SALAD = 15;
    public static double PESTO = 15;
    public static double EGGPLANT_CHEESE = 15;
    public static double TUNA = 10;
    public static double CREAM_CHEESE = 10;
    public static double BULGARIAN = 10;
    public static double YELLOW_CHEESE = 10;
    public static double EGG_SALAD = 10;
    public static double AVOCADO = 12;
    public static double OMLETTE = 12;
    public static double TIVOL = 12;

    //Pastries
    public static double CHOCO_CROI = 7;
    public static double CHEESE_CROI = 7;
    public static double BUTTER_CROI = 7;
    public static double CINNAMON_CROI = 7;
    public static double DONUT = 6;
    public static double CHEESE_BUREK = 7;
    public static double POTATO_BUREK = 7;
    public static double EGG_BUREK = 10;
    public static double MELAWAC = 10;
    public static double ADDITION = 3;
    public static double PIZZA = 6;
    public static double PIZZA_PLUS = 7;
    public static double PIZZA_BULGARIAN = 3;
    public static String pizza_text = "פיצה ";
    public static String croissant_text = "קוראסון ";
    public static String burk_text = "בורקס ";
    public static String melawac_text = "מלאווח ";

    //panini
    public static double PANINI_FINGER = 10;
    public static double PANINI_BAGLE = 13;
    public static double ADDITION1 = 1;
    public static double ADDITION3 = 3;
    public static String panini="טוסט ";
    public static String panini_addition1=" 1 לטוסט ";
    public static String panini_addition3=" 3 לטוסט ";

    //salad
    public static double SALAD = 13;
    public static double SALAD_BREAD = 1.5;
    public static double SALAD_ADDITION = 3;
    public static String salad_addition_text=" לסלט ";

    //hots
    public static double PASTA = 14;
    public static double KUSKUS = 14;
    public static double YAM_SOUP = 10;
    public static double LENTIL_SOUP = 10;
    public static double VEGGIE_SOUP = 10;
    public static double CORN_MILANESA = 5;
    public static String soup_text="מרק ";

    //meusli
    public static double WATERMELON = 7;
    public static double FRUIT_MEUSLI = 15;
    public static double FROOP_MULLER = 5.5;
    public static double CLICK_MULLER = 6.6;


    public static double SMALL_DRINK_PRICE = 5;
    public static double LARGE_DRINK_PRICE = 7;
    public static double SMALL_DRINK_CAPACITY = 0.250;
    public static double LARGE_DRINK_CAPACITY = 0.300;
    public static String smallTag = " קטן";
    public static String largeTag = " גדול";


    //hints:
    public static String NO_HINT = "";
    public static final String DRINK_HINT = "drink";
    public static final String SANDWICH_HINT = "sandwich";
    public static final String PASTRY_HINT = "pastry";
    public static final String PANINI_HINT = "panini";
    public static final String PANINI_ADDITION_HINT1 = "panini1";
    public static final String PANINI_ADDITION_HINT3 = "panini3";
    public static final String SALAD_HINT = "salad";
    public static final String CROISSANT_HINT = "croissant";
    public static final String BURK_HINT = "burk";
    public static final String PIZZA_HINT = "pizza";
    public static final String MELAWAC_HINT = "melawac";
    public static final String SOUP_HINT = "soup";





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


        switch (item.getHint().toString())
        {

            case DRINK_HINT:
            {
                if (price == SMALL_DRINK_PRICE) {
                    lines[itemName] += smallTag;
                    Log.d("TKT_cashier", "name: " + lines[itemName]);

                } else
                    lines[itemName] += largeTag;
                break;
            }
            case PANINI_HINT:
            {
                lines[itemName] = panini + lines[itemName];
                break;
            }
            case PANINI_ADDITION_HINT1:
            {
                lines[itemName] += panini_addition1;
                break;
            }
            case PANINI_ADDITION_HINT3:
            {
                lines[itemName] += panini_addition3;
                break;
            }
            case SALAD_HINT:
            {
                lines[itemName] += salad_addition_text;
                break;
            }
            case CROISSANT_HINT:
            {
                lines[itemName] = croissant_text + lines[itemName];
                break;
            }
            case BURK_HINT:
            {
                lines[itemName] = burk_text + lines[itemName];
                break;
            }
            case PIZZA_HINT:
            {
                lines[itemName] = pizza_text + lines[itemName];
                break;
            }
            case MELAWAC_HINT:
            {
                lines[itemName] = melawac_text + lines[itemName];
                break;
            }
            case SOUP_HINT:
            {
                lines[itemName] = soup_text + lines[itemName];
                break;
            }
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
        Log.d("TKT_cashier","items: "+listString);
        for(int i = 0; i < items.length; i++)
        {
            ItemList.Item newItem = new ItemList().new Item(items[i]);
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
                Log.d("TKT_cashier","saveShopListL "+SavedShoppingList.toString());
                Log.d("TKT_cahier","boughtItems: "+boughtItems.toString());
                SavedShoppingList.merge(boughtItems);


                //clear current file and update
                File oldFile = new File(ItemScreen.context.getFilesDir().toString(), FILE_NAME);//// TODO: 9/8/2017 check if redundant
                boolean deleted = oldFile.delete();
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
            List<String> listOfItems = new ArrayList<String>(Arrays.asList(SavedShoppingList.split("\n")));// Arrays.asList(SavedShoppingList.split(System.getProperty("line.separator")));
            ArrayAdapter<String> adapter = new ArrayAdapter(Report.context, R.layout.custom_list_view, listOfItems);
            listView.setAdapter(adapter);
        }
    }

    public static void endShift() throws IOException {//create a file with userName & report file
        //create a new file:
        String userName = Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null);
        //File dest = new File(ItemScreen.context.getFilesDir().toString(), userName);
        //File src = new File(ItemScreen.context.getFilesDir(), FILE_NAME);
        //copy(src, dest);

        //send email
        //// TODO: 9/10/2017 file is not being attached!! :/
        File src = new File(ItemScreen.context.getFilesDir().getAbsolutePath(), FILE_NAME);
        Log.d("TKT_cashier","file exists? "+src.exists());
        Uri path = Uri.fromFile(src);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // set the type to 'email'
        emailIntent.setType("text/plain");//("vnd.android.cursor.dir/email");
        String to[] = {"js777755@gmail.com"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        // the attachment
        emailIntent.putExtra(Intent.EXTRA_STREAM, path);
        // the mail subject
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, userName+": " +
                ItemScreen.context.getString(R.string.report)+ " - "+ date.format(c.getTime()).toString());
        Log.d("TKT_cashier","mailSybject: "+userName+": " +
                ItemScreen.context.getString(R.string.report)+ " - "+ date.format(c.getTime()).toString());
        ItemScreen.context.startActivity(Intent.createChooser(emailIntent , "Send email..."));




    }







}
