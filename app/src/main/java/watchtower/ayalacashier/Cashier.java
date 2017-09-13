package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
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
    //=======================================================================
    //public static double I_ICE5 = 5;//DRINK_PRICES[IND_ICE5]
    //public static double I_ICE7 = 7;//DRINK_PRICES[IND_ICE7]
    //public static double I_SLURPEE5 = 5;//DRINK_PRICES[IND_SLURPEE5]
    //public static double I_SLURPEE7 = 7;//DRINK_PRICES[IND_SLURPEE7]
    //public static double I_LEMONADE5 = 5;//DRINK_PRICES[IND_LEMONADE5]
    //public static double I_LEMONADE7 = 7;//DRINK_PRICES[IND_LEMONADE7]
    //public static double I_ORANGE5 = 5;//DRINK_PRICES[IND_ORANGE5]
    //public static double I_ORANGE7 = 7;//DRINK_PRICES[IND_ORANGE7]
    //public static double I_HOT_CHOC = 5;//DRINK_PRICES[IND_HOT_CHOC]
    //public static double I_CHOC = 3;//DRINK_PRICES[IND_CHOC]
    //public static double I_CAPPUCCINO = 5;//DRINK_PRICES[IND_CAPPUCCINO]
    //public static double I_CAN5 = 5;//DRINK_PRICES[IND_CAN5]
    //public static double I_CAN6 = 6;//DRINK_PRICES[IND_CAN6]
    //public static double I_WATER = 5;//DRINK_PRICES[IND_WATER]

    public static final String [] DRINK_NAMES = {"אייס קטן", "אייס גדול", "ברד קטן", "ברד גדול", "לימונדה קטן","לימונדה גדול" ,"תפוזים קטן" ,"תפוזים גדול" ,"שוקו חם" ,"שוקו" ,"נס קפה" ,"פחית קטן" ,"פחית גדול" , "מים"};
    public static double [] DRINK_PRICES = {5,7,5,7,5,7,5,7,5,3,5,5,6,5};

    public static final int IND_ICE5 = 0;
    public static int IND_ICE7 = 1;
    public static int IND_SLURPEE5 = 2;
    public static int IND_SLURPEE7 = 3;
    public static int IND_LEMONADE5 = 4;
    public static int IND_LEMONADE7 = 5;
    public static int IND_ORANGE5 = 6;
    public static int IND_ORANGE7 = 7;
    public static int IND_HOT_CHOC = 8;
    public static int IND_CHOC = 9;
    public static int IND_CAPPUCCINO = 10;
    public static int IND_CAN5 = 11;
    public static int IND_CAN6 = 12;
    public static int IND_WATER = 13;

    public static final String ICE_SMALL = "אייס קטן";//DRINK_NAMES[IND_ICE5]
    public static final String ICE_LARGE = "אייס גדול";//DRINK_NAMES[IND_ICE7]
    public static final String SLURPEE_SMALL = "ברד קטן";//DRINK_NAMES[IND_SLURPEE5]
    public static final String SLURPEE_LARGE = "ברד גדול";//DRINK_NAMES[IND_SLURPEE5]
    public static final String LEMONADE_SMALL = "לימונדה קטן";//DRINK_NAMES[IND_LEMONADE5]
    public static final String LEMONADE_LARGE = "לימונדה גדול";//DRINK_NAMES[IND_LEMONADE7]
    public static final String ORANGE_SMALL = "תפוזים קטן";//DRINK_NAMES[IND_ORANGE5]
    public static final String ORANGE_LARGE = "תפוזים גדול";//DRINK_NAMES[IND_ORANGE7]
    public static final String HOT_CHOC = "שוקו חם";//DRINK_NAMES[IND_HOT_CHOC]
    public static final String CHOC = "שוקו";//DRINK_NAMES[IND_CHOC]
    public static final String CAPPUCCINO = "נס קפה";//DRINK_NAMES[IND_CAPPUCCINO]
    public static final String CAN_SMALL = "פחית קטן";//DRINK_NAMES[IND_CAN5]
    public static final String CAN_LARGE = "פחית גדול";//DRINK_NAMES[IND_CAN6]
    public static final String WATER = "מים";//DRINK_NAMES[IND_WATER]



    //Sandwiches============================================================
    public static double I_SPICY_EGGPLANT = 15;
    public static double I_SHAKSHUKA = 15;
    public static double I_SABIH = 15;
    public static double I_GREEN_OMLETTE = 15;
    public static double I_TUNA_SALAD = 15;
    public static double I_PESTO = 15;
    public static double I_EGGPLANT_CHEESE = 15;
    public static double I_TUNA = 10;
    public static double I_CREAM_CHEESE = 10;
    public static double I_BULGARIAN = 10;
    public static double I_YELLOW_CHEESE = 10;
    public static double I_EGG_SALAD = 10;
    public static double I_AVOCADO = 12;
    public static double I_OMLETTE = 12;
    public static double I_TIVOL = 12;

    public static final String SPICY_EGGPLANT = "חציל פיקנטי";
    public static final String SHAKSHUKA = "שקשוקה";
    public static final String SABIH = "סביח";
    public static final String GREEN_OMLETTE = "חביתת ירק";
    public static final String TUNA_SALAD = "סלט טונה";
    public static final String PESTO = "פסטו";
    public static final String EGGPLANT_CHEESE = "חציל + בולגרית";
    public static final String TUNA = "טונה";
    public static final String CREAM_CHEESE = "גבינת שמנת";
    public static final String BULGARIAN = "בולגרית";
    public static final String YELLOW_CHEESE = "גבינה צהובה";
    public static final String EGG_SALAD = "סלט ביצים";
    public static final String AVOCADO = "אבוקדו";
    public static final String OMLETTE = "חביתה";
    public static final String TIVOL = "כריך טבעול";

    //PastrieS===========================================================
    public static double I_CHOCO_CROI = 7;
    public static double I_CHEESE_CROI = 7;
    public static double I_BUTTER_CROI = 7;
    public static double I_CINNAMON_CROI = 7;
    public static double I_DONUT = 6;
    public static double I_CHEESE_BUREK = 7;
    public static double I_POTATO_BUREK = 7;
    public static double I_EGG_BUREK = 10;
    public static double I_MELAWAC = 10;
    public static double I_MELAWAC_ADDITION = 13;
    public static double I_PIZZA = 6;
    public static double I_PIZZA_PLUS = 7;
    public static double I_PIZZA_BULGARIAN = 3;
    public static String pizza_text = "פיצה ";
    public static String pizza_text_bulgarian = "בולגרית לפיצה";
    public static String croissant_text = "קרואסון ";
    public static String burk_text = "בורקס ";
    public static String melawac_text = "מלאווח ";

    public static final String CHOCO_CROI = "קרואסון שוקולד";
    public static final String CHEESE_CROI = "גבינית";
    public static final String BUTTER_CROI = "קרואסון חמאה";
    public static final String CINNAMON_CROI = "קרואסון קינמון";
    public static final String DONUT = "דונאט";
    public static final String CHEESE_BUREK = "בורקס גבינה";
    public static final String POTATO_BUREK = "בורקס תפו\"א";
    public static final String EGG_BUREK = "בורקס + ביצה";
    public static final String MELAWAC = "מלאווח";
    public static final String MELAWAC_ADDITION = "מלאווח + תוספות";
    public static final String PIZZA = "פיצה רגילה";
    public static final String PIZZA_PLUS = "פיצה + תוספות";
    public static final String PIZZA_BULGARIAN = "בולגרית לפיצה";

    //panini==============================================================
    public static double I_PANINI_FINGER = 10;
    public static double I_PANINI_BAGLE = 13;
    public static double I_PANINI_ADDITION1 = 1;
    public static double I_PANINI_ADDITION3 = 3;
    public static String panini="טוסט ";
    public static String panini_addition1=" 1 לטוסט ";
    public static String panini_addition3=" 3 לטוסט ";

    public static final String PANINI_FINGER = "טוסט לחמניית אצבע";
    public static final String PANINI_BAGLE = "טוסט בייגל";
    public static final String PANINI_ADDITION1 = "תוספת 1 לטוסט";
    public static final String PANINI_ADDITION3 = "תוספת 3 לטוסט";

    //salad===================================================================
    public static double I_SALAD = 13;
    public static double I_SALAD_BREAD = 1.5;
    public static double I_SALAD_ADDITION = 3;
    public static String salad_addition_text=" לסלט ";

    public static final String SALAD = "סלט";
    public static final String SALAD_BREAD = "פרוסת לחם";
    public static final String SALAD_ADDITION = "תוספות לסלט";

    //hots===================================================================
    public static double I_PASTA = 14;
    public static double I_KUSKUS = 14;
    public static double I_YAM_SOUP = 10;
    public static double I_LENTIL_SOUP = 10;
    public static double I_VEGGIE_SOUP = 10;
    public static double I_CORN_MILANESA = 5;
    public static String soup_text="מרק ";

    public static final String PASTA = "פסטה";
    public static final String KUSKUS = "קוסקוס";
    public static final String YAM_SOUP = "מרק בטטה";
    public static final String LENTIL_SOUP = "מרק עדשים";
    public static final String VEGGIE_SOUP = "מרק ירקות";
    public static final String CORN_MILANESA = "שניצל תירס";

    //meusli=======================================================
    public static double I_WATERMELON = 7;
    public static double I_FRUIT_MEUSLI = 15;
    public static double I_FROOP_MULLER = 5.5;
    public static double I_CLICK_MULLER = 6.6;

    public static final String WATERMELON = "אבטיח";
    public static final String FRUIT_MEUSLI = "מוזלי";
    public static final String FROOP_MULLER = "מולר קטן";
    public static final String CLICK_MULLER = "מולר גדול";

    //candy========================================================
    public static double I_RUG = 0.5;
    public static double I_SELZER = 1;
    public static double I_MARSHMELLOW = 1.5;
    public static double I_LOLLIES_DIP = 1.5;
    public static double I_TOFFEE_FIZZ = 2;
    public static double I_PIES_POLVO = 2;
    public static double I_FIZZ_NECKLACE = 2;
    public static double I_HEART = 2;
    public static double I_PARAGUA = 3;
    public static double I_PIPE = 3;
    public static double I_ZOOM = 3.5;
    public static double I_EXTREME = 3.5;
    public static double I_TICTAC = 4;
    public static double I_MENTOS = 4;
    public static double I_KINDER = 5;
    public static double I_SNEAKERS = 5;
    public static double I_MARS = 5.5;
    public static double I_SOUR_SPRAY = 5.5;
    public static double I_TWIX = 5.5;
    public static double I_TEAMI = 5.5;
    public static double I_NUTELLA = 6;
    public static double I_BUENO = 6.5;
    public static double I_TIME_OUT = 6.5;
    public static double I_CLICK_BAR = 6.5;
    public static double I_CLICK_BAG = 6.5;
    public static double I_CLICK_TABLET = 6.5;
    public static double I_BAMBA = 5;
    public static double I_DORITOS = 5;
    public static double I_BISLI = 5.5;
    public static double I_APROPOSITO = 5.5;
    public static double I_BEARS = 5.5;

    public static final String RUG = "שטיח";
    public static final String SELZER = "סוכריית סודה";
    public static final String MARSHMELLOW = "מרשמלו";
    public static final String LOLLIES_DIP = "Lollies dip";
    public static final String TOFFEE_FIZZ = "טופי פיז";
    public static final String PIES_POLVO = "Pies";
    public static final String FIZZ_NECKLACE = "שרשרת פיז";
    public static final String HEART = "לקקן לב";
    public static final String PARAGUA = "מטריה";
    public static final String PIPE = "צינור";
    public static final String ZOOM = "זום";
    public static final String EXTREME = "אקסטרים";
    public static final String TICTAC = "תיקתק";
    public static final String MENTOS = "מנטוס";
    public static final String KINDER = "אצבעות קינדר";
    public static final String SNEAKERS = "סניקרס";
    public static final String MARS = "מארס";
    public static final String SOUR_SPRAY = "ספריי חמוץ";
    public static final String TWIX = "טוויקס";
    public static final String TEAMI = "טעמי";
    public static final String NUTELLA = "נוטלה טו גו";
    public static final String BUENO = "בואנו";
    public static final String TIME_OUT = "פסק זמן";
    public static final String CLICK_BAR = "חטיף קליק";
    public static final String CLICK_BAG = "קליק שקית";
    public static final String CLICK_TABLET = "קליק טבלה";
    public static final String BAMBA = "במבה";
    public static final String DORITOS = "דוריטוס";
    public static final String BISLI = "ביסלי";
    public static final String APROPOSITO = "אפרופו";
    public static final String BEARS = "דובונים";

    //==========================================================



    public static double SMALL_DRINK_PRICE = 5;
    public static double LARGE_DRINK_PRICE = 7;
    //public static double SMALL_DRINK_CAPACITY = 0.250;
    //public static double LARGE_DRINK_CAPACITY = 0.300;
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

    public static void updatePayment(Button item, TextView change)
    {//update payment in textView, add item to list
        Log.d("TKT_cashier","updatePayment===================");

        String buttonText = item.getText().toString();
        String [] lines = buttonText.split("\n");
        double price = Double.parseDouble(lines[itemPrice]);
        change.setText("");

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
                if(!lines[itemName].equals(pizza_text_bulgarian))
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

    public static void check(EditText cash, TextView change)
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

                if(!cash.getText().toString().equals(""))
                {
                    double cashReceived = Double.parseDouble(cash.getText().toString());
                    double payment = Double.parseDouble(paymentText.getText().toString());
                    double changeToCustomer = cashReceived - payment;
                    change.setText(changeToCustomer+"");
                }


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
            cash.setText("");
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

    public static void displayReport(ListView listView, TextView totalSum)
    {
        Log.d("TKT_cashier","displayReport===================");
        String SavedShoppingList = openItemList();
        if(SavedShoppingList != null)
        {

            try {
                setAltogether(totalSum,SavedShoppingList);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("TKT_cashier","exception");
            }

            List<String> listOfItems = new ArrayList<String>(Arrays.asList(SavedShoppingList.split("\n")));// Arrays.asList(SavedShoppingList.split(System.getProperty("line.separator")));
            ArrayAdapter<String> adapter = new ArrayAdapter(Report.context, R.layout.custom_list_view, listOfItems);
            listView.setAdapter(adapter);
        }
    }

    public static void setAltogether(TextView totalSum, String SavedShoppingList) throws IOException {
        double total = 0;
            BufferedReader bufferedReader = new BufferedReader((new StringReader(SavedShoppingList)));
            String line = bufferedReader.readLine();
            while(line != null)
            {
                ItemList.Item temp = new ItemList().new Item(line);
                String itemName = temp.name;
                int itemAmount = temp.amount;
                switch (itemName)
                {
                    case DRINK_NAMES[IND_ICE5]:
                    {
                        total +=  itemAmount*DRINK_PRICES[IND_ICE5];
                        Log.d("TKT_cashier","iceSmall, name: " + itemName);
                        break;
                    }
                    case ICE_LARGE:
                    {
                        total += itemAmount*DRINK_PRICES[IND_ICE7];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SLURPEE_SMALL:
                    {
                        total += itemAmount*DRINK_PRICES[IND_SLURPEE5];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SLURPEE_LARGE:
                    {
                        total += itemAmount*DRINK_PRICES[IND_SLURPEE7];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case LEMONADE_SMALL:
                    {
                        total += itemAmount*DRINK_PRICES[IND_LEMONADE5];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case LEMONADE_LARGE:
                    {
                        total += itemAmount*DRINK_PRICES[IND_LEMONADE7];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case ORANGE_SMALL:
                    {
                        total += itemAmount*DRINK_PRICES[IND_ORANGE5];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case ORANGE_LARGE:
                    {
                        total += itemAmount*DRINK_PRICES[IND_ORANGE7];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case HOT_CHOC:
                    {
                        total += itemAmount*DRINK_PRICES[IND_HOT_CHOC];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CHOC:
                    {
                        total += itemAmount*DRINK_PRICES[IND_CHOC];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CAPPUCCINO:
                    {
                        total += itemAmount*DRINK_PRICES[IND_CAPPUCCINO];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CAN_SMALL:
                    {
                        total += itemAmount*DRINK_PRICES[IND_CAN5];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CAN_LARGE:
                    {
                        total += itemAmount*DRINK_PRICES[IND_CAN6];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case WATER:
                    {
                        total += itemAmount*DRINK_PRICES[IND_WATER];
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case GREEN_OMLETTE:
                    {
                        total += itemAmount*I_GREEN_OMLETTE;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case TUNA_SALAD:
                    {
                        total += itemAmount*I_TUNA_SALAD;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SHAKSHUKA:
                    {
                        total += itemAmount*I_SHAKSHUKA;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case EGGPLANT_CHEESE:
                    {
                        total += itemAmount*I_EGGPLANT_CHEESE;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PESTO:
                    {
                        total += itemAmount*I_PESTO;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SPICY_EGGPLANT:
                    {
                        total += itemAmount*I_SPICY_EGGPLANT;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SABIH:
                    {
                        total += itemAmount*I_SABIH;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case TUNA:
                    {
                        total += itemAmount*I_TUNA;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CREAM_CHEESE:
                    {
                        total += itemAmount*I_CREAM_CHEESE;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case BULGARIAN:
                    {
                        total += itemAmount*I_BULGARIAN;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case EGG_SALAD:
                    {
                        total += itemAmount*I_EGG_SALAD;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case AVOCADO:
                    {
                        total += itemAmount*I_AVOCADO;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case OMLETTE:
                    {
                        total += itemAmount*I_OMLETTE;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case TIVOL:
                    {
                        total += itemAmount*I_TIVOL;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CHOCO_CROI:
                    {
                        total += itemAmount*I_CHOCO_CROI;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CHEESE_CROI:
                    {
                        total += itemAmount*I_CHEESE_CROI;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case BUTTER_CROI:
                    {
                        total += itemAmount*I_BUTTER_CROI;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CINNAMON_CROI:
                    {
                        total += itemAmount*I_CINNAMON_CROI;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case POTATO_BUREK:
                    {
                        total += itemAmount*I_POTATO_BUREK;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CHEESE_BUREK:
                    {
                        total += itemAmount*I_CHEESE_BUREK;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case EGG_BUREK:
                    {
                        total += itemAmount*I_EGG_BUREK;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case DONUT:
                    {
                        total += itemAmount*I_DONUT;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PIZZA:
                    {
                        total += itemAmount*I_PIZZA;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PIZZA_PLUS:
                    {
                        total += itemAmount*I_PIZZA_PLUS;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PIZZA_BULGARIAN:
                    {
                        total += itemAmount*I_PIZZA_BULGARIAN;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case MELAWAC:
                    {
                        total += itemAmount*I_MELAWAC;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case MELAWAC_ADDITION:
                    {
                        total += itemAmount*I_MELAWAC_ADDITION;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PANINI_FINGER:
                    {
                        total += itemAmount*I_PANINI_FINGER;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PANINI_BAGLE:
                    {
                        total += itemAmount*I_PANINI_BAGLE;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PANINI_ADDITION1:
                    {
                        total += itemAmount*I_PANINI_ADDITION1;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PANINI_ADDITION3:
                    {
                        total += itemAmount*I_PANINI_ADDITION3;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SALAD:
                    {
                        total += itemAmount*I_SALAD;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SALAD_BREAD:
                    {
                        total += itemAmount*I_SALAD_BREAD;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SALAD_ADDITION:
                    {
                        total += itemAmount*I_SALAD_ADDITION;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PASTA:
                    {
                        total += itemAmount*I_PASTA;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case KUSKUS:
                    {
                        total += itemAmount*I_KUSKUS;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CORN_MILANESA:
                    {
                        total += itemAmount*I_CORN_MILANESA;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case YAM_SOUP:
                    {
                        total += itemAmount*I_YAM_SOUP;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case VEGGIE_SOUP:
                    {
                        total += itemAmount*I_VEGGIE_SOUP;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case LENTIL_SOUP:
                    {
                        total += itemAmount*I_LENTIL_SOUP;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case WATERMELON:
                    {
                        total += itemAmount*I_WATERMELON;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case FRUIT_MEUSLI:
                    {
                        total += itemAmount*I_FRUIT_MEUSLI;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case FROOP_MULLER:
                    {
                        total += itemAmount*I_FROOP_MULLER;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CLICK_MULLER:
                    {
                        total += itemAmount*I_CLICK_MULLER;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case RUG:
                    {
                        total += itemAmount*I_RUG;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SELZER:
                    {
                        total += itemAmount*I_SELZER;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case MARSHMELLOW:
                    {
                        total += itemAmount*I_MARSHMELLOW;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case LOLLIES_DIP:
                    {
                        total += itemAmount*I_LOLLIES_DIP;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case TOFFEE_FIZZ:
                    {
                        total += itemAmount*I_TOFFEE_FIZZ;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PIES_POLVO:
                    {
                        total += itemAmount*I_PIES_POLVO;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case FIZZ_NECKLACE:
                    {
                        total += itemAmount*I_FIZZ_NECKLACE;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case HEART:
                    {
                        total += itemAmount*I_HEART;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SOUR_SPRAY:
                    {
                        total += itemAmount*I_SOUR_SPRAY;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case KINDER:
                    {
                        total += itemAmount*I_KINDER;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case ZOOM:
                    {
                        total += itemAmount*I_ZOOM;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case EXTREME:
                    {
                        total += itemAmount*I_EXTREME;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case TICTAC:
                    {
                        total += itemAmount*I_TICTAC;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case MENTOS:
                    {
                        total += itemAmount*I_MENTOS;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PARAGUA:
                    {
                        total += itemAmount*I_PARAGUA;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case PIPE:
                    {
                        total += itemAmount*I_PIPE;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case SNEAKERS:
                    {
                        total += itemAmount*I_SNEAKERS;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case MARS:
                    {
                        total += itemAmount*I_MARS;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case TWIX:
                    {
                        total += itemAmount*I_TWIX;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case TEAMI:
                    {
                        total += itemAmount*I_TEAMI;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case BUENO:
                    {
                        total += itemAmount*I_BUENO;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case TIME_OUT:
                    {
                        total += itemAmount*I_TIME_OUT;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case NUTELLA:
                    {
                        total += itemAmount*I_NUTELLA;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CLICK_BAR:
                    {
                        total += itemAmount*I_CLICK_BAR;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CLICK_BAG:
                    {
                        total += itemAmount*I_CLICK_BAG;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case CLICK_TABLET:
                    {
                        total += itemAmount*I_CLICK_TABLET;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case BAMBA:
                    {
                        total += itemAmount*I_BAMBA;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case DORITOS:
                    {
                        total += itemAmount*I_DORITOS;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case BISLI:
                    {
                        total += itemAmount*I_BISLI;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case APROPOSITO:
                    {
                        total += itemAmount*I_APROPOSITO;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }
                    case BEARS:
                    {
                        total += itemAmount*I_BEARS;
                        Log.d("TKT_cashier","iceSmall, total: " + itemName);
                        break;
                    }


                }
                
                line = bufferedReader.readLine();
            }
            Log.d("TKT_cashier","iceSmall, total: " + total);
           totalSum.setText(total+"");

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
