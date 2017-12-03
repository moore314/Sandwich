package watchtower.ayalacashier;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Moore on 9/5/2017.
 */

//// TODO: 11/28/2017 when update for version 1, disable launcer in main and enable launger for welcome 

public class Cashier {

    protected static SharedPreferences checkPrefs;
    public static SharedPreferences.Editor progressEdit;
    //====== sharedPref ======
    public static final String EMPLOYEE_NAME = "employeeName";
    public static final String PASSWORD = "sandwich";
    public static final String STUDENT_PASSWORD = "student";
    public static final String CHECK_PREFS = "cashier";
    public static final String PRICES = "prices";
    public static final String PRICE_CHANGED = "priceChanged"; //this is to determine if prices need to be loaded from shared or not
    public static ItemList boughtItems = new ItemList();
    static Calendar c = Calendar.getInstance();
    static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    public static final String FILE_NAME = "report";//date.format(c.getTime()).toString();
    public static final String REPORT_SENT = "report_sent";
    public static final String ALTOGETHER = "altogether";
    public static final String SHIFT = "shift";
    public static final String LOGIN = "login";
    public static final String NEXT = "next";
    public static final String CURR_DATE = "currDate";
    public static final String CURR_START = "currStart";
    public static final String CURR_END = "currEnd";
    public static final String ALTOGETHER_HOURS = "altogetherHours";
    public static final String VIEW_STATE = "viewState";
    public static final String CLASSIC_VIEW = "קופה בסיסית";
    public static final String DETAIL_VIEW = "קופה מפורטת";
    public static final String ALTOGETHER_HR_TXT = "סה\"כ שעות: ";
    public static final String IS_STUDENT = "isStudent";
    public static final String CATERING_ALTOGETHER = "cateringAltogether";


    //public static final String REPORT_HEB = "דוח מכירות"

    //======== Hours ========
    public static ArrayList<Day>[] days = new ArrayList[12];
    public static Day today;
    public static final int DAY = 0;
    public static final int MONTH = 1;
    public static final int YEAR = 2;
    public static final int DATE = 0;
    public static final int TIME = 1;

    public static final String EXIT_SHIFT = "לצאת מהמשמרת?";
    public static final String[] MONTHS = {"בחר/י חודש", "ינואר", "פברואר", "מרץ", "אפריל", "מאי", "יוני", "יולי", "אוגוסט", "ספטמבר", "אוקטובר", "נובמבר", "דצמבר"};
    //public static ArrayList<Day>[] DAY_ARR_LST = new ArrayList[12];
    public static Day[][] DAY_ARR_LST = new Day[31][12];
    public static final String DAY_MATRIX = "dayMatrix";


    //=======================================================================
    //PRICES
    //5;//DRINK_PRICES[IND_ICE5]
    //7;//DRINK_PRICES[IND_ICE7]
    //5;//DRINK_PRICES[IND_SLURPEE5]
    //7;//DRINK_PRICES[IND_SLURPEE7]
    //5;//DRINK_PRICES[IND_LEMONADE5]
    //7;//DRINK_PRICES[IND_LEMONADE7]
    //5;//DRINK_PRICES[IND_ORANGE5]
    //7;//DRINK_PRICES[IND_ORANGE7]
    //5;//DRINK_PRICES[IND_HOT_CHOC]
    //3;//DRINK_PRICES[IND_CHOC]
    //5;//DRINK_PRICES[IND_CAPPUCCINO]
    //5;//DRINK_PRICES[IND_CAN5]
    //6;//DRINK_PRICES[IND_CAN6]
    //5;//DRINK_PRICES[IND_WATER]

    //NAMES:
    //DRINK_NAMES[IND_ICE5] : "אייס קטן";
    ////DRINK_NAMES[IND_ICE7] : "אייס גדול";
    //DRINK_NAMES[IND_SLURPEE5] : "ברד קטן";
    //DRINK_NAMES[IND_SLURPEE7] :  "ברד גדול";
    //DRINK_NAMES[IND_LEMONADE5] : "לימונדה קטן";
    //DRINK_NAMES[IND_LEMONADE7] : "לימונדה גדול";
    //DRINK_NAMES[IND_ORANGE5] : "תפוזים קטן";
    //DRINK_NAMES[IND_ORANGE7] : "תפוזים גדול";
    //DRINK_NAMES[IND_HOT_CHOC] : "שוקו חם";
    //DRINK_NAMES[IND_CHOC] : "שוקו";
    //DRINK_NAMES[IND_CAPPUCCINO] : "נס קפה";
    //DRINK_NAMES[IND_CAN5] : "פחית קטן";
    ///DRINK_NAMES[IND_CAN6] : "פחית גדול";
    //DRINK_NAMES[IND_WATER] : "מים";
    public static final String[] DRINK_NAMES = {"אייס קטן", "אייס גדול", "ברד קטן", "ברד גדול", "לימונדה קטן", "לימונדה גדול", "תפוזים קטן", "תפוזים גדול", "שוקו חם", "שוקו", "נס קפה", "פחית קטן", "פחית גדול", "מים"};
    public static double[] DRINK_PRICES = {5, 7, 5, 7, 5, 7, 5, 7, 5, 3, 5, 5, 6, 5};
    public static int IND_ICE5 = 0;
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

    //Sandwiches============================================================
    public static String[] SANDWICH_NAMES = {"חציל פיקנטי", "שקשוקה", "סביח", "חביתת ירק", "סלט טונה", "פסטו", "חציל + בולגרית", "טונה", "גבינת שמנת", "בולגרית", "גבינה צהובה", "סלט ביצים", "אבוקדו", "חביתה", "כריך טבעול"};
    public static double[] SANDWICH_PRICES = {15, 15, 15, 15, 15, 15, 15, 10, 10, 10, 10, 10, 12, 12, 12};
    public static int IND_SPICY_EGGPLANT = 0;
    public static int IND_SHAKSHUKA = 1;
    public static int IND_SABIH = 2;
    public static int IND_GREEN_OMLETTE = 3;
    public static int IND_TUNA_SALAD = 4;
    public static int IND_PESTO = 5;
    public static int IND_EGGPLANT_CHEESE = 6;
    public static int IND_TUNA = 7;
    public static int IND_CREAM_CHEESE = 8;
    public static int IND_BULGARIAN = 9;
    public static int IND_YELLOW_CHEESE = 10;
    public static int IND_EGG_SALAD = 11;
    public static int IND_AVOCADO = 12;
    public static int IND_OMLETTE = 13;
    public static int IND_TIVOL = 14;
    //15;SANDWICH_PRICES[IND_SPICY_EGGPLANT]
    //15;SANDWICH_PRICES[IND_SHAKSHUKA]
    //15;SANDWICH_PRICES[IND_SABIH]
    //15;SANDWICH_PRICES[IND_GREEN_OMLETTE]
    //15;SANDWICH_PRICES[IND_TUNA_SALAD]
    //15;SANDWICH_PRICES[IND_PESTO]
    //15;SANDWICH_PRICES[IND_EGGPLANT_CHEESE]
    //10;SANDWICH_PRICES[IND_TUNA]
    //10;SANDWICH_PRICES[IND_CREAM_CHEESE]
    //10;SANDWICH_PRICES[IND_BULGARIAN]
    //10;SANDWICH_PRICES[IND_YELLOW_CHEESE]
    //10;SANDWICH_PRICES[IND_EGG_SALAD]
    //12;SANDWICH_PRICES[IND_AVOCADO]
    //12;SANDWICH_PRICES[IND_OMLETTE]
    //12;SANDWICH_PRICES[IND_TIVOL]
    //SANDWICH_NAMES[IND_SPICY_EGGPLANT] : "חציל פיקנטי";
    //SANDWICH_NAMES[IND_SHAKSHUKA] : "שקשוקה";
    //SANDWICH_NAMES[IND_SABIH] : "סביח";
    //SANDWICH_NAMES[IND_GREEN_OMLETTE] : "חביתת ירק";
    //SANDWICH_NAMES[IND_TUNA_SALAD] : "סלט טונה";
    //SANDWICH_NAMES[IND_PESTO] : "פסטו";
    //SANDWICH_NAMES[IND_EGGPLANT_CHEESE] : "חציל + בולגרית";
    //SANDWICH_NAMES[IND_TUNA] : "טונה";
    //SANDWICH_NAMES[IND_CREAM_CHEESE] : "גבינת שמנת";
    //SANDWICH_NAMES[IND_BULGARIAN] : "בולגרית";
    //SANDWICH_NAMES[IND_YELLOW_CHEESE] : "גבינה צהובה";
    //SANDWICH_NAMES[IND_EGG_SALAD] : "סלט ביצים";
    //SANDWICH_NAMES[IND_AVOCADO] : "אבוקדו";
    //SANDWICH_NAMES[IND_OMLETTE] : "חביתה";
    //SANDWICH_NAMES[IND_TIVOL] : "כריך טבעול";

    //PastrieS===========================================================
    public static String[] PASTRY_NAMES = {"קרואסון שוקולד", "גבינית", "קרואסון חמאה", "קרואסון קינמון", "דונאט", "בורקס גבינה", "בורקס תפו\"א", "בורקס + ביצה", "מלאווח", "מלאווח + תוספות", "פיצה רגילה", "פיצה + תוספות", "בולגרית לפיצה"};
    public static double[] PASTRY_PRICES = {7, 7, 7, 7, 6, 7, 7, 10, 9.5, 12.5, 6, 7, 3};
    public static int IND_CHOCO_CROI = 0;
    public static int IND_CHEESE_CROI = 1;
    public static int IND_BUTTER_CROI = 2;
    public static int IND_CINNAMON_CROI = 3;
    public static int IND_DONUT = 4;
    public static int IND_CHEESE_BUREK = 5;
    public static int IND_POTATO_BUREK = 6;
    public static int IND_EGG_BUREK = 7;
    public static int IND_MELAWAC = 8;
    public static int IND_MELAWAC_ADDITION = 9;
    public static int IND_PIZZA = 10;
    public static int IND_PIZZA_PLUS = 11;
    public static int IND_PIZZA_BULGARIAN = 12;
    public static String pizza_text = "פיצה ";
    public static String pizza_text_bulgarian = "בולגרית לפיצה";
    public static String croissant_text = "קרואסון ";
    public static String burk_text = "בורקס ";
    public static String melawac_text = "מלאווח ";
    //PASTRY_PRICES[IND_CHOCO_CROI] : 7;
    //PASTRY_PRICES[IND_CHEESE_CROI] : 7;
    //PASTRY_PRICES[IND_BUTTER_CROI] : 7;
    //PASTRY_PRICES[IND_CINNAMON_CROI] : 7;
    //PASTRY_PRICES[IND_DONUT] : 6;
    //PASTRY_PRICES[IND_CHEESE_BUREK] : 7;
    //PASTRY_PRICES[IND_POTATO_BUREK] : 7;
    //PASTRY_PRICES[IND_EGG_BUREK] :10;
    //PASTRY_PRICES[IND_MELAWAC] : 10;
    //PASTRY_PRICES[IND_MELAWAC_ADDITION] : 13;
    //PASTRY_PRICES[IND_PIZZA] : 6;
    //PASTRY_PRICES[IND_PIZZA_PLUS] : 7;
    //PASTRY_PRICES[IND_PIZZA_BULGARIAN] : 3;
    //PASTRY_NAMES[IND_CHOCO_CROI] :  "קרואסון שוקולד";
    //PASTRY_NAMES[IND_CHEESE_CROI] : "גבינית";
    //PASTRY_NAMES[IND_BUTTER_CROI] : "קרואסון חמאה";
    //PASTRY_NAMES[IND_CINNAMON_CROI] : "קרואסון קינמון";
    //PASTRY_NAMES[IND_DONUT] : "דונאט";
    //PASTRY_NAMES[IND_CHEESE_BUREK] : "בורקס גבינה";
    //PASTRY_NAMES[IND_POTATO_BUREK] : "בורקס תפו\"א";
    //PASTRY_NAMES[IND_EGG_BUREK] : "בורקס + ביצה";
    //PASTRY_NAMES[IND_MELAWAC] : "מלאווח";
    //PASTRY_NAMES[IND_MELAWAC_ADDITION] : "מלאווח + תוספות";
    //PASTRY_NAMES[IND_PIZZA] : "פיצה רגילה";
    // PASTRY_NAMES[IND_PIZZA_PLUS] : "פיצה + תוספות";
    //PASTRY_NAMES[IND_PIZZA_BULGARIAN] : "בולגרית לפיצה";

    //panini==============================================================
    public static String[] PANINI_NAMES = {"טוסט לחמניית אצבע", "טוסט בייגל", "תוספת 1 לטוסט", "תוספת 3 לטוסט"};
    public static double[] PANINI_PRICES = {10, 13, 1, 2};
    public static String panini = "טוסט ";
    public static String panini_addition1 = " 1 לטוסט ";
    public static String panini_addition3 = " 3 לטוסט ";

    public static int IND_PANINI_FINGER = 0;
    public static int IND_PANINI_BAGLE = 1;
    public static int IND_PANINI_ADDITION1 = 2;
    public static int IND_PANINI_ADDITION3 = 3;
    public static final String PANINI_FINGER = "טוסט לחמניית אצבע";
    public static final String PANINI_BAGLE = "טוסט בייגל";
    public static final String PANINI_ADDITION1 = "תוספת 1 לטוסט";
    public static final String PANINI_ADDITION3 = "תוספת 3 לטוסט";
    //PANINI_PRICES[Cashier.IND_PANINI_FINGER] : 10;
    //PANINI_PRICES[Cashier.IND_PANINI_BAGLE] : 13;
    //PANINI_PRICES[Cashier.IND_PANINI_ADDITION1] : 1;
    //PANINI_PRICES[Cashier.IND_PANINI_ADDITION3] : 3;

    //salad===================================================================
    public static String[] SALAD_NAMES = {"סלט", "פרוסת לחם", "תוספות לסלט"};
    public static double[] SALAD_PRICES = {13, 1, 3};
    public static int IND_SALAD = 0;
    public static int IND_SALAD_BREAD = 1;
    public static int IND_SALAD_ADDITION = 2;
    public static String salad_addition_text = " לסלט ";

    //SALAD_PRICES[IND_SALAD] : 13;
    //SALAD_PRICES[Cashier.IND_SALAD_BREAD] : 1.5;
    //SALAD_PRICES[Cashier.IND_SALAD_ADDITION] : 3;
    //SALAD_NAMES[IND_SALAD] : "סלט";
    //SALAD_NAMES[Cashier.IND_SALAD_BREAD] : "פרוסת לחם";
    //SALAD_NAMES[Cashier.IND_SALAD_ADDITION] : "תוספות לסלט";

    //hots===================================================================
    public static String[] HOTS_NAMES = {"פסטה", "קוסקוס", "מרק בטטה", "מרק עדשים", "מרק ירקות", "שניצל תירס"};
    public static double[] HOTS_PRICES = {14, 14, 10, 10, 10, 5};
    public static int IND_PASTA = 0;
    public static int IND_KUSKUS = 1;
    public static int IND_YAM_SOUP = 2;
    public static int IND_LENTIL_SOUP = 3;
    public static int IND_VEGGIE_SOUP = 4;
    public static int IND_CORN_MILANESA = 5;
    public static String soup_text = "מרק ";
    //HOTS_PRICES[IND_PASTA] : 14;
    //HOTS_PRICES[IND_KUSKUS] : 14;
    //HOTS_PRICES[IND_YAM_SOUP] 10;
    //HOTS_PRICES[IND_LENTIL_SOUP] : 10;
    //HOTS_PRICES[IND_VEGGIE_SOUP] : 10;
    //HOTS_PRICES[IND_CORN_MILANESA] : 5;
    //HOTS_NAMES[IND_PASTA] : "פסטה";
    //HOTS_NAMES[IND_KUSKUS] : "קוסקוס";
    //HOTS_NAMES[IND_YAM_SOUP] : "מרק בטטה";
    //HOTS_NAMES[IND_LENTIL_SOUP] : "מרק עדשים";
    //HOTS_NAMES[IND_VEGGIE_SOUP] : "מרק ירקות";
    //HOTS_NAMES[IND_CORN_MILANESA] : "שניצל תירס";

    //meusli=======================================================
    public static String[] FRUIT_NAMES = {"אבטיח", "מוזלי", "מולר קטן", "מולר גדול"};
    public static double[] FRUIT_PRICES = {7, 15, 5.5, 6.5};
    public static int IND_WATERMELON = 0;
    public static int IND_FRUIT_MEUSLI = 1;
    public static int IND_FROOP_MULLER = 2;
    public static int IND_CLICK_MULLER = 3;
    //FRUIT_PRICES[IND_WATERMELON] : 7;
    //FRUIT_PRICES[IND_FRUIT_MEUSLI] : 15;
    //FRUIT_PRICES[IND_FROOP_MULLER ] : 5.5;
    //FRUIT_PRICES[IND_CLICK_MULLER] : 6.6;
    //FRUIT_NAMES[IND_WATERMELON] : "אבטיח";
    //FRUIT_NAMES[IND_FRUIT_MEUSLI] : "מוזלי";
    //FRUIT_NAMES[IND_FROOP_MULLER ] : "מולר קטן";
    //FRUIT_NAMES[IND_CLICK_MULLER] : "מולר גדול";

    //candy========================================================

    public static int IND_RUG = 0;
    public static int IND_SELZER = 1;
    public static int IND_MARSHMELLOW = 2;
    public static int IND_LOLLIES_DIP = 3;
    public static int IND_TOFFEE_FIZZ = 4;
    public static int IND_PIES_POLVO = 5;
    public static int IND_FIZZ_NECKLACE = 6;
    public static int IND_HEART = 7;
    public static int IND_PARAGUA = 8;
    public static int IND_PIPE = 9;
    public static int IND_ZOOM = 10;
    public static int IND_EXTREME = 11;
    public static int IND_TICTAC = 12;
    public static int IND_MENTOS = 13;
    public static int IND_KINDER = 14;
    public static int IND_SNEAKERS = 15;
    public static int IND_MARS = 16;
    public static int IND_SOUR_SPRAY = 17;
    public static int IND_TWIX = 18;
    public static int IND_TEAMI = 19;
    public static int IND_NUTELLA = 20;
    public static int IND_BUENO = 21;
    public static int IND_TIME_OUT = 22;
    public static int IND_CLICK_BAR = 23;
    public static int IND_CLICK_BAG = 24;
    public static int IND_CLICK_TABLET = 25;
    public static int IND_BAMBA = 26;
    public static int IND_DORITOS = 27;
    public static int IND_BISLI = 28;
    public static int IND_APROPOSITO = 29;
    public static int IND_BEARS = 30;
    //CANDY_PRICES[IND_RUG] : 0.5;
    //CANDY_PRICES[IND_SELZER] : 1;
    //CANDY_PRICES[IND_MARSHMELLOW] : 1.5;
    //CANDY_PRICES[IND_LOLLIES_DIP] : 1.5;
    //CANDY_PRICES[IND_TOFFEE_FIZZ] : 2;
    //CANDY_PRICES[IND_PIES_POLVO] : 2;
    //CANDY_PRICES[IND_FIZZ_NECKLACE] : 2;
    //CANDY_PRICES[IND_HEART] : 2;
    //CANDY_PRICES[IND_PARAGUA] : 3;
    //CANDY_PRICES[IND_PIPE] : 3;
    //CANDY_PRICES[IND_ZOOM] : 3.5;
    //CANDY_PRICES[IND_EXTREME] : 3.5;
    //CANDY_PRICES[IND_TICTAC] : 4;
    //CANDY_PRICES[IND_MENTOS] : 4;
    //CANDY_PRICES[IND_KINDER] : 5;
    //CANDY_PRICES[IND_SNEAKERS] : 5;
    //CANDY_PRICES[IND_SNEAKERS] 5.5;
    //CANDY_PRICES[IND_SOUR_SPRAY] :5.5;
    //CANDY_PRICES[IND_TWIX] : 5.5;
    public static double[] CANDY_PRICES = {0.5, 1, 1.5, 1.5, 2,
            2, 2, 2, 3, 3,
            3.5, 3.5, 4, 4, 5, 5,
            5.5, 5.5, 5.5, 5.5, 6,
            6.5, 6.5, 6.5, 6.5, 6.5,
            5, 5, 5.5, 5.5, 5.5};

    //CANDY_PRICES[IND_TEAMI] : 5.5;
    //CANDY_PRICES[IND_NUTELLA] : 6;
    //CANDY_PRICES[IND_BUENO] :6.5;
    //CANDY_PRICES[IND_TIME_OUT] : 6.5;
    //CANDY_PRICES[IND_CLICK_BAR] : 6.5;
    //CANDY_PRICES[IND_CLICK_BAG] : 6.5;
    //CANDY_PRICES[IND_CLICK_TABLET] : 6.5;
    //CANDY_PRICES[IND_BAMBA] : 5;
    //CANDY_PRICES[IND_DORITOS] : 5;
    //CANDY_PRICES[IND_BISLI] : 5.5;
    //CANDY_PRICES[IND_APROPOSITO] :5.5;
    //CANDY_PRICES[IND_BEARS] : 5.5;
    public static String[] CANDY_NAMES = {"שטיח", "סוכריית סודה", "מרשמלו", "Lollies dip", "טופי פיז", "Pies", "שרשרת פיז", "לקקן לב", "מטריה", "צינור", "זום", "אקסטרים", "תיקתק", "מנטוס",
            "אצבעות קינדר", "סניקרס", "מארס", "ספריי חמוץ", "טוויקס", "טעמי", "נוטלה טו גו", "בואנו", "פסק זמן", "חטיף קליק", "קליק שקית", "קליק טבלה", "במבה", "דוריטוס", "ביסלי", "אפרופו", "דובונים"};


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
    public static Locale il = new Locale("iw", "IL");
    public static final Currency ILS = Currency.getInstance(il);
    final static String number = "972547474764";

    static int itemName = 0;
    static int itemPrice = 1;
    static int MONTH_TO_SEND;
    static String type = "";


    //CATERING
    public static HashMap<String, CateringObjectInfo> cateringOrder = new HashMap<>();
    //public static String [] CATERING_ITEMS = {}
    
    public static String [] CATERING_PRICES = {"185", "160","160", "150", "110", "110", "110", "110"};//salads
    //hugeSalad, lentilSalad, quinoaSalad, tunaSalad, eggSalad, eggplantSalad, thiniSalad, avocadoSalad
    




    public static void sharedUpdateEmployee(String employeeName) {
        Log.d("TKT_cashier", "updateEmployee===================");

        progressEdit = checkPrefs.edit();
        progressEdit.putString(EMPLOYEE_NAME, employeeName);
        progressEdit.commit();
    }

    public static void updatePayment(Button item, TextView change) {//update payment in textView, add item to list
        Log.d("TKT_cashier", "updatePayment===================");

        String buttonText = item.getText().toString();
        String[] lines = buttonText.split("\n");
        //double price = Double.parseDouble(lines[itemPrice]);
        double price = Double.parseDouble(item.getTag().toString());
        change.setText("");

        switch (item.getHint().toString()) {

            case DRINK_HINT: {
                if (price == SMALL_DRINK_PRICE) {
                    lines[itemName] += smallTag;
                    //Log.d("TKT_cashier", "name: " + lines[itemName]);

                } else
                    lines[itemName] += largeTag;
                break;
            }
            case PANINI_HINT: {
                lines[itemName] = panini + lines[itemName];
                break;
            }
            case PANINI_ADDITION_HINT1: {
                lines[itemName] += panini_addition1;
                break;
            }
            case PANINI_ADDITION_HINT3: {
                lines[itemName] += panini_addition3;
                break;
            }
            case SALAD_HINT: {
                lines[itemName] += salad_addition_text;
                break;
            }
            case CROISSANT_HINT: {
                lines[itemName] = croissant_text + lines[itemName];
                break;
            }
            case BURK_HINT: {
                lines[itemName] = burk_text + lines[itemName];
                break;
            }
            case PIZZA_HINT: {
                if (!lines[itemName].equals(pizza_text_bulgarian))
                    lines[itemName] = pizza_text + lines[itemName];
                break;
            }
            case MELAWAC_HINT: {
                lines[itemName] = melawac_text + lines[itemName];
                break;
            }
            case SOUP_HINT: {
                lines[itemName] = soup_text + lines[itemName];
                break;
            }
        }


        //handle itemList
        ItemList.Item currItem = boughtItems.contains(lines[itemName]);
        if (currItem != null) {//item exists
            //Log.d("TKT_cashier", "contained in boughtItems");
            currItem.increase();
        } else {//create new item
            ItemList.Item newItem = new ItemList().new Item(lines[itemName]);
            boughtItems.addItem(newItem);
        }


        //handle price textView
        if (!paymentText.getText().toString().equals("")) {
            double currPayment = Double.parseDouble(paymentText.getText().toString());
            price += currPayment;
            //Log.d("TKT_cashier", "else");

        }
        paymentText.setText(price + "");


        dialog.dismiss();
    }

    public static void simplePayment(Button item, TextView change) {
        change.setText("");
        double price = Double.parseDouble(item.getTag().toString());
        if (!paymentText.getText().toString().equals("")) {
            double currPayment = Double.parseDouble(paymentText.getText().toString());
            price += currPayment;

        }
        paymentText.setText(price + "");

    }

    public static void cancel() {
        Log.d("TKT_cashier", "cancel===================");

        if (dialog.isShowing())
            dialog.dismiss();
        else//meaning this was called from cancelCheck
        {
            if (!paymentText.getText().toString().equals("")) {
                paymentText.setText("");
                boughtItems.clearAll();
                AlertDialog.Builder message = new AlertDialog.Builder(ItemScreen.context);
                message.setMessage(R.string.cancelled).create();
                message.show();
            } else
                emptyCheck();
        }

    }

    public static void simpleCancel(EditText cashRec, TextView change) {
        if (!paymentText.getText().toString().equals("")) {
            paymentText.setText("");
            cashRec.setText("");
            change.setText("");
        } else
            emptyCheck();
    }

    public static String openItemList(Context context) {
        Log.d("TKT_cashier", "openItemList===================");
        return fileToString(context, FILE_NAME);

    }

    public static String fileToString(Context context, String fileName) {
        Log.d("TKT_cashier", "fileToString===================");
        ObjectInputStream objectInputStream;
        File file = new File(context.getFilesDir(), fileName);
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            return (String) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TKT_cashier", "file does not exist");
            return null;
        }
    }

    public static void saveHash(ItemList hash, Context context) {
        Log.d("TKT_cashier", "saveHash===================");

        File file = new File(context.getFilesDir().toString(), FILE_NAME);
        Log.d("TKT_cashier", "is file null: " + file.exists());
        try {
            Log.d("TKT_cashier", "try");
            file.createNewFile();
            Log.d("TKT_cashier", "file is created: " + file.exists());
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            Log.d("TKT_cashier", "shopList: " + hash.toString());
            outputStream.writeObject(hash.toString());
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            Log.d("TKT_cashier", "catch me if you can1");
            e.printStackTrace();
        }
    }

    public static ItemList stringToItemList(String listString) {
        Log.d("TKT_cashier", "strignToItemList===================");
        ItemList SavedShoppingList = new ItemList();
        String[] items = listString.split(System.getProperty("line.separator"));
        Log.d("TKT_cashier", "items: " + listString);
        for (int i = 0; i < items.length; i++) {
            ItemList.Item newItem = new ItemList().new Item(items[i]);
            SavedShoppingList.addItem(newItem);
        }
        return SavedShoppingList;
    }

    public static void updateSubTotal(TextView paymentText) {

        double currentSubTotal = checkPrefs.getFloat(ALTOGETHER, 0);
        Log.d("TKT_cashier", "currentSubTotal: " + currentSubTotal);
        currentSubTotal += Double.parseDouble(paymentText.getText().toString());

        Log.d("TKT_cashier", "updateSubTotal: currTotal:  " + currentSubTotal);

        progressEdit = checkPrefs.edit();
        progressEdit.putFloat(ALTOGETHER, (float) currentSubTotal);
        progressEdit.commit();

    }

    public static void check(EditText cash, TextView change, Context context) {
        Log.d("TKT_cashier", "check===================");

        if (!paymentText.getText().toString().equals("")) {


            if (!cash.getText().toString().equals("")) {
                //Log.d("TKT_cashier","inIf");
                double cashReceived = Double.parseDouble(cash.getText().toString());
                double payment = Double.parseDouble(paymentText.getText().toString());
                double changeToCustomer = cashReceived - payment;
                change.setText(changeToCustomer + "");
            }

            //Log.d("TKT_cashier","savedShopList: "+SavedShoppingList.toString());
            String listString = openItemList(context);
            //Log.d("TKT_cashier","listString: "+listString);
            if (listString == null) {
                saveHash(boughtItems, context);
            } else {//merging both hashMaps

                //Log.d("TKT_cashier","merging");
                ItemList SavedShoppingList = stringToItemList(listString);//openItemList();
                //Log.d("TKT_cashier","saveShopListL "+SavedShoppingList.toString());
                //Log.d("TKT_cahier","boughtItems: "+boughtItems.toString());
                SavedShoppingList.merge(boughtItems);


                //clear current file and update
                File oldFile = new File(context.getFilesDir().toString(), FILE_NAME);//// TODO: 9/8/2017 check if redundant
                boolean deleted = oldFile.delete();
                saveHash(SavedShoppingList, context);

            }
            updateSubTotal(paymentText);
            boughtItems.clearAll();


            AlertDialog.Builder message = new AlertDialog.Builder(context);
            message.setMessage(R.string.done).create();
            message.show();
            paymentText.setText("");
            cash.setText("");
            Log.d("TKT_cashier", "OrderSubmitted $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");
        } else {
            emptyCheck();
        }


    }

    public static void simpleCheck(EditText cash, TextView change, Context context) {
        if (!paymentText.getText().toString().equals("")) {
            if (!cash.getText().toString().equals("")) {//if paid exact amount
                double cashReceived = Double.parseDouble(cash.getText().toString());
                double payment = Double.parseDouble(paymentText.getText().toString());
                double changeToCustomer = cashReceived - payment;
                change.setText(changeToCustomer + "");
            }

            //Log.d("TKT_cashier","paymentText: "+paymentText.getText().toString());
            updateSubTotal(paymentText);
            AlertDialog.Builder message = new AlertDialog.Builder(context);
            message.setMessage(R.string.done).create();
            message.show();
            paymentText.setText("");
            cash.setText("");

        } else
            emptyCheck();
    }

    public static void emptyCheck() {
        AlertDialog.Builder message = new AlertDialog.Builder(ItemScreen.context);
        message.setMessage(R.string.emptyCheck).create();
        message.show();
    }

    public static void displayReport(ListView listView, TextView totalSum, Context context) {
        Log.d("TKT_cashier", "displayReport===================");
        String SavedShoppingList = openItemList(context);
        List<String> listOfItems = new ArrayList<String>();
        if (SavedShoppingList != null) {

            listOfItems = new ArrayList<String>(Arrays.asList(SavedShoppingList.split("\n")));// Arrays.asList(SavedShoppingList.split(System.getProperty("line.separator")));
            ArrayAdapter<String> adapter = new ArrayAdapter(Report.context, R.layout.custom_list_view, listOfItems);
            listView.setAdapter(adapter);
        }
        totalSum.setText((checkPrefs.getFloat(ALTOGETHER, 0)) + "");
        //return SavedShoppingList;

    }

    public static void displayHoursFromFile(ListView listView, int month) {
        Log.d("TKT_cashier", "displayHoursFromFile===================");
        List<String> hourList = new ArrayList<String>();

        Day[][] dayMat = getMatrixFromDB();
        String fileString = "";
        for (int i = 0; i < DAY_ARR_LST.length; i++) {
            if (dayMat[i][month - 1] != null)
                fileString += dayMat[i][month - 1].toString() + "\n";
        }
        if (fileString.equals(""))
            listView.setAdapter(null);
        else {
            //Log.d("TKT_cashier", "fileString: " + fileString);
            hourList = organizeList(fileString);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Hours.context, R.layout.custom_list_view, hourList);
            listView.setAdapter(adapter);
        }
    }

    public static List organizeList(String hours) {
        /* a string entry format is the following:
update        */
        Log.d("TKT_cashier", "organizeList");
        String day, hrs, alt;
        List<String> list = new ArrayList<String>();
        String[] entries = hours.split("\n");
        for (int i = 0; i < entries.length; i++) {
            String[] date = entries[i].split(">>");
            String[] dateEntries = date[0].split("/");
            // יי/חח/שש  //
            day = dateEntries[0];
            //Log.d("TKT_cashier","day: "+day);
            String[] hoursADay = date[1].split("=");//hours
            //  שעת_התחלה - שעת_סיום  //
            hrs = hoursADay[0];
            //Log.d("TKT_cashier","hrs: "+hrs);
            String[] altogether = hoursADay[1].split(" ");
            //  דד/שש  //
            alt = altogether[2];
            //Log.d("TKT_cashier","alt: "+alt);
            list.add(alt + "   ::   " + hrs + "   ::   " + day);

        }
        return list;
    }

    public static void sendReportToA(Context context, ListView listView, TextView textView) throws IOException {
        //create a file with userName & report file
        //create a new file:
        String userName = Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null);

        String SavedShoppingList = openItemList(context);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // set the type to 'email'
        emailIntent.setType("text/plain");//("vnd.android.cursor.dir/email");
        String to[] = {"ayalam530@walla.com"};//
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        // the attachment
        emailIntent.putExtra(Intent.EXTRA_TEXT, SavedShoppingList);
        // the mail subject
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, userName + ": " +
                context.getString(R.string.report) + " - " + dateFormat.format(c.getTime()).toString());
        context.startActivity(Intent.createChooser(emailIntent, "Send email..."));

        //clear all
        clearAllReport(context, listView, textView);

    }

    public static int itemIndexInArray(String itemName, String[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(itemName))
                return i;
        }
        return -1;
    }

    public static void finishUpdate(double newPrice, String itemName) {
        Log.d("TKT_cashier", "finishUpdate");
        progressEdit = checkPrefs.edit();
        progressEdit.putFloat(itemName, (float) newPrice);
        progressEdit.commit();
    }

    public static void updateShiftState(boolean state) {
        progressEdit = checkPrefs.edit();
        progressEdit.putBoolean(SHIFT, state);
        progressEdit.commit();
    }

    public static void updateToday(String date, String start, String end) {
        Log.d("TKT_cashier", "updateToday======================");
        String[] month = date.split("/");
        String hours = "";
        progressEdit = checkPrefs.edit();
        if (end == null) {
            progressEdit.putString(CURR_DATE, date);
            progressEdit.putString(CURR_START, start);
        } else {
            progressEdit.putString(CURR_END, end);
            String st = Cashier.checkPrefs.getString(Cashier.CURR_START, null);
            hours = hourDifference(st, end);
            Day today = new Day(date, st, end, hours);//create new day
            String prevHours = checkPrefs.getString(ALTOGETHER_HOURS + month[MONTH], null);
            if (prevHours != null) {//combine hours
                hours = hourAddition(hours, prevHours);
            }

            progressEdit.putString(ALTOGETHER_HOURS + month[MONTH], hours);
            saveTodayToFile(today);


        }
        progressEdit.commit();
    }

    public static void updateAltogetherHours(String hoursMinus, String hoursPlus, int month) {
        //display it again
        Log.d("TKT_cashir", "updateAltogetherHours================");
        String alt = checkPrefs.getString(ALTOGETHER_HOURS + month, null);
        progressEdit = checkPrefs.edit();
        Log.d("TKT_cashier", "alt: " + alt);
        Log.d("TKT_cashier", "hoursMinus: " + hoursMinus);
        Log.d("TKT_cashier", "hoursPlus: " + hoursPlus);
        Log.d("TKT_cashier", "month: " + month);
        if (alt == null) {
            Log.d("TKT_cashier", "alt: " + alt);
            progressEdit.putString(ALTOGETHER_HOURS + month, hoursPlus);
        } else {
            String temp = Hours.emptyHour;
            if (hoursMinus != null) {
                temp = hourDifference(alt, hoursMinus);
                temp = hourAddition(temp, hoursPlus);
                Log.d("TKT_cashier", "temp0: " + temp);

            } else {
                temp = hourAddition(alt, hoursPlus);
                Log.d("TKT_cashier", "temp1: " + temp);
            }

            progressEdit.putString(ALTOGETHER_HOURS + month, temp);


        }
        progressEdit.commit();
        //Log.d("TKT_cashier","monthCausesProb: "+month);
        Hours.altogetherHours.setText(setAltogetherHours(month));
    }

    public static Day[][] getMatrixFromDB() {
        File file = new File(Welcome.context.getFilesDir(), DAY_MATRIX);
        ObjectInputStream objectInputStream = null;
        //Day[][] fromDB = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (objectInputStream != null) {

            try {
                DAY_ARR_LST = (Day[][]) objectInputStream.readObject();
            } catch (Exception e1) {
                e1.printStackTrace();
                Log.d("TKT_cashier", "couldn't cast to matrix");

            }
        }
        return DAY_ARR_LST;


    }

    public static void writeMatToDB(Day[][] mat) {
        Log.d("TKT_cashier", "writeMatToFile");
        File file = new File(Welcome.context.getFilesDir(), DAY_MATRIX);
        try {
            file.createNewFile();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(mat);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TKT_cashier", "exception in saving day");
        }

    }

    public static void saveTodayToFile(Day day) {//called by updateToday
        Log.d("TKT_cashier", "saveTodayToFile");
        String[] month = day.date.split("/");
        int i = Integer.parseInt(month[DAY]);
        int j = Integer.parseInt(month[MONTH]);
        //fetch mat from shared
        Day[][] fromDB = getMatrixFromDB();//(month[MONTH]);
        //if(fromDB == null)
        {
            fromDB[i - 1][j - 1] = new Day(day);
            writeMatToDB(fromDB);
        }
        //else
        //writeMatToDB(fromDB);


    }

    public static String setAltogetherHours(int month) {
        //get hours from shared;
        Log.d("TKT_cashier", "setAltogetherHours month: " + month);
        Log.d("TKT_cashier", "inShared: " + checkPrefs.getString(ALTOGETHER_HOURS + month, null));
        return checkPrefs.getString(ALTOGETHER_HOURS + month, null);
    }

    public static String hourDifference(String strt, String end) {
        Log.d("TKT_cashier", "hourDifference==============");
        String diffString = "";
        //SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        if (strt.equals(end)) {
            return Hours.emptyHour;
        } else {
            String[] startArr = strt.split(":");
            String[] endArr = end.split(":");
            String s, e;
            if (Integer.parseInt(startArr[0]) > Integer.parseInt(endArr[0])) {
                e = strt;
                s = end;
            } else if (Integer.parseInt(startArr[0]) < Integer.parseInt(endArr[0])) {
                s = strt;
                e = end;
            } else {
                if (Integer.parseInt(startArr[1]) > Integer.parseInt(endArr[1])) {
                    e = strt;
                    s = end;
                } else {
                    s = strt;
                    e = end;
                }
                //// TODO: 10/23/2017 problemmmm!!!! 
            }
            Log.d("TKT_cashier", "s: " + s);
            Log.d("TKT_cashier", "e: " + e);

            try {
                Date st = timeFormat.parse(s);
                Date ed = timeFormat.parse(e);
                long difference = ed.getTime() - st.getTime();
                //Date diff = new Date(difference);
                //diffString = format.format(diff);
                //Log.d("TKT_cashier","difference: "+diffString);
                diffString = String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(difference),
                        TimeUnit.MILLISECONDS.toMinutes(difference) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(difference)));
                //TimeUnit.MILLISECONDS.toSeconds(difference) -
                //TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference)));
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            Log.d("TKT_cashier", e + " - " + s + " = " + diffString);
            return diffString;
        }
    }

    public static String hourAddition(String h1, String h2) {
        Log.d("TKT_cashier", "hourAddition==============");
        String sumString = "";
        //SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date d1 = timeFormat.parse(h1);
            Date d2 = timeFormat.parse(h2);
            long sum = d1.getTime() + d2.getTime();
            sumString = timeFormat.format(new Date(sum));


        } catch (ParseException e) {
            Log.d("TKT_cashier", "can't parse" + sumString);
            e.printStackTrace();

        }

        //String [] finalSum = sumString.split("-");
        return sumString;//finalSum[0];//meaning, only hours and minutes
    }

    public static void setCashierView(MenuItem item, String state) {//0: classic, 1: detail
        //int state = checkPrefs.getInt(VIEW_STATE, 0);
        Log.d("TKT_cashier", "changedHere");
        if (!state.equals(CLASSIC_VIEW)) {
            //set to classic view and change text to detail
            //Log.d("TKT_cashier","!= classicView");
            progressEdit = checkPrefs.edit();
            progressEdit.putInt(VIEW_STATE, 0);
            item.setTitle(DETAIL_VIEW);
        } else {
            //Log.d("TKT_cashier","== classicView");
            progressEdit = checkPrefs.edit();
            progressEdit.putInt(VIEW_STATE, 1);
            item.setTitle(CLASSIC_VIEW);
        }
        progressEdit.commit();

    }

    public static void general(Context context) {//paymentTextView is already set - refer to onCreate of ClassicView || ItemView
        Cashier.dialog = new Dialog(context);
        Cashier.dialog.setContentView(R.layout.general_payment);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        final EditText cashText = (EditText) dialog.findViewById(R.id.generalEditText);
        Button proceed = (Button) dialog.findViewById(R.id.generalProceedPayment);
        Button cancel = (Button) dialog.findViewById(R.id.generalCancelPayment);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(cashText.equals(""))
                Log.d("TKT_cashier", "cashText: " + cashText);
                if (cashText.getText().toString().length() == 0) {
                    //Log.d("TKT_cashier","cashTextIsMT");
                    emptyCheck();
                } else {
                    double price = Double.parseDouble(cashText.getText().toString());
                    if (!paymentText.getText().toString().equals("")) {
                        double currPayment = Double.parseDouble(paymentText.getText().toString());
                        price += currPayment;

                    }
                    paymentText.setText(price + "");
                    Cashier.dialog.dismiss();
                }


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();
    }


    public static void sendHoursToA(final Context context) {
        //choose month dialog
        Log.d("TKT_cashier", "sendHoursToA");
        MONTH_TO_SEND = 0;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_send_hours);
        dialog.setCanceledOnTouchOutside(false);
        NumberPicker monthPicker = (NumberPicker) dialog.findViewById(R.id.monthPickerSend);
        Button send = (Button) dialog.findViewById(R.id.sendButton);
        Button cancel = (Button) dialog.findViewById(R.id.cancelButtonHours);

        monthPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        monthPicker.setMinValue(0);
        monthPicker.setMaxValue(12);
        monthPicker.setWrapSelectorWheel(false);
        monthPicker.setDisplayedValues(MONTHS);

        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                MONTH_TO_SEND = newVal;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //whatsApp instance
                if (MONTH_TO_SEND != 0) {
                    sendThisMonthToA(MONTH_TO_SEND, context);
                    //dialog.dismiss();
                }
            }
        });

        dialog.show();

    }


    @SuppressLint("NewApi")
    public static void sendThisMonthToA(int month, Context context) {
        Log.d("TKT_cashier", "sendThisMonthToA==========");
        //Log.d("TKT_cashier","month is: "+(month));
        //Log.d("TKT_cashier","month[]: "+MONTHS[(month)]);
        //generate string to send

        //String formatedNumber = PhoneNumberUtils.format(number);


        String hourMessage = generateHourMessage(month);
        String userName = Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        String [] to = {"ayalam530@walla.com"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        // the attachment
        emailIntent.putExtra(Intent.EXTRA_TEXT, hourMessage);
        // the mail subject
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, userName + ": " +
                context.getString(R.string.hours) + " - " + MONTHS[month]);
        context.startActivity(Intent.createChooser(emailIntent, "Send email..."));

/*
sending through whatsapp
        try {
            
            Intent intent = new Intent(Intent.ACTION_SEND);
            //intent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, hourMessage);
            intent.putExtra("jid", number + "@s.whatsapp.net");
            intent.setPackage("com.whatsapp");
            //go here if u want to change send to not only whatsapp  vvv
            //https://www.codeproject.com/Tips/1097808/Custom-App-Chooser-in-Android
            context.startActivity(intent);//context.startActivity(Intent.createChooser(intent,"Choose application:"));
        }
        catch (Exception x)
        {
            Log.d("TKT_cashier","whatsapp is not installed on this device");
            Toast.makeText(context,"WhatsApp is not Installed" , Toast.LENGTH_SHORT).show();
        }
        */


        /*
        Intent sendToA = new Intent(Intent.ACTION_SEND);
        sendToA.putExtra(Intent.EXTRA_TEXT, hourMessage);
        sendToA.setType("text/plain");
        context.startActivity(Intent.createChooser(sendToA, "שליחת דו\"ח שעות"));
        */


    }

    public static String generateHourMessage(int month) {
        Log.d("TKT_cashier", "generateHourMessage==========");
        String message = "", title = "דו\"ח שעות לחודש ", alt = "סה\"כ שעות: ";
        title += MONTHS[month];
        //Log.d("TKT_cashier","title: "+title);
        Day[][] mat = getMatrixFromDB();
        message += title + "\n";
        for (int i = 0; i < mat.length; i++) {//run through days of month
            if (mat[i][month - 1] != null) {

                message += generateSendEntry(mat[i][month - 1].toString()) + "\n";
            }
        }
        alt += getAltFromDb(month);
        message += alt;
        //Log.d("TKT_cashier","messageToA "+message);
        return message;
    }

    public static String generateSendEntry(String entry) {
        String day, hrs, alt;
        String[] date = entry.split(">>");
        String[] dateEntries = date[0].split("/");
        // יי/חח/שש  //
        day = dateEntries[0];
        //Log.d("TKT_cashier","day: "+day);
        String[] hoursADay = date[1].split("=");//hours
        //  שעת_התחלה - שעת_סיום  //
        hrs = hoursADay[0];
        //Log.d("TKT_cashier","hrs: "+hrs);
        String[] altogether = hoursADay[1].split(" ");
        //  דד/שש  //
        alt = altogether[2];

        //Log.d("TKT_cashier","newEntry: " + (alt + " :: "+"hrs"+" :: "+day));
        return alt + " :: " + hrs + " :: " + day;

    }

    public static String getAltFromDb(int month) {
        return checkPrefs.getString(ALTOGETHER_HOURS + month, null);
    }

    public static void clearAllReport(Context context, ListView listView, TextView totalSum) {
        Log.d("TKT_cashier", "displayReport===================");

        //erase that file from DB
        File file = new File(context.getFilesDir(), FILE_NAME);
        file.delete();
        listView.setAdapter(null);
        progressEdit = checkPrefs.edit();
        progressEdit.putFloat(ALTOGETHER, 0);
        progressEdit.commit();
        totalSum.setText("");
        Toast.makeText(context, R.string.clearedSuccessfully, Toast.LENGTH_SHORT).show();
        //return SavedShoppingList;

    }

    public static void sharedUpdateStudentState()
    {
        progressEdit = checkPrefs.edit();
        progressEdit.putBoolean(IS_STUDENT,true);
        progressEdit.commit();
    }

    public static void sharedUpdateSand(String sand) {
        Log.d("TKT_cashier", "sharedUpdateSand===================");

        progressEdit = checkPrefs.edit();
        progressEdit.putString(StudentOrder.CHOSEN_SAND, sand);
        progressEdit.commit();
    }
    public static void sharedUpdateSandBread(String bread) {
        Log.d("TKT_cashier", "sharedUpdateSandBread===================");

        progressEdit = checkPrefs.edit();
        progressEdit.putString(StudentOrder.CHOSEN_BREAD, bread);
        progressEdit.commit();
    }
    
    //// TODO: 10/29/2017 update shared about these selections 
    public static void sharedUpdateSaladBread(String bread) {
        Log.d("TKT_cashier", "sharedUpdateSandBread===================");

        progressEdit = checkPrefs.edit();
        progressEdit.putString(StudentOrder.CHOSEN_SALAD_BREAD, bread);
        progressEdit.commit();
    }
    public static void sharedUpdateSaladAdds(String adds) {
        Log.d("TKT_cashier", "sharedUpdateSandBread===================");

        progressEdit = checkPrefs.edit();
        progressEdit.putString(StudentOrder.NUM_SALAD_ADDS, adds);
        progressEdit.commit();
    }
    public static void sharedUpdateHot(String hot)
    {
        Log.d("TKT_cashier", "sharedUpdateHot===================");
        Log.d("TKT_cashoer","hot to be in shared: "+hot);
        progressEdit = checkPrefs.edit();
        progressEdit.putString(StudentOrder.CHOSEN_HOT, hot);
        progressEdit.commit();
    }
    public static void sharedUpdateDessert(String dessert)
    {
        Log.d("TKT_cashier", "sharedUpdateHot===================");

        progressEdit = checkPrefs.edit();
        progressEdit.putString(StudentOrder.CHOSEN_DESSERT, dessert);
        progressEdit.commit();
    }

    public static void sendOrderToA(String message, Context context)
    {

        Log.d("TKT_cashier","sendOrderToA========================");
        //String formatedNumber = PhoneNumberUtils.format(number);
        try {

            Intent intent = new Intent(Intent.ACTION_SEND);
            //intent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.putExtra("jid", number + "@s.whatsapp.net");
            intent.setPackage("com.whatsapp");
            context.startActivity(intent);//(Intent.createChooser(intent,"share with:"));
        }
        catch (Exception x)
        {
            Toast.makeText(context, "WhatsApp is not Installed", Toast.LENGTH_SHORT).show();
            Log.d("TKT_cashier","cannot send whatsapp");
        }

    }

    public static void displayOrderCatering(ListView listView, TextView totalSum, Context context)
    {
        Log.d("TKT_cashier","displayOrderCatering========");
        List<String>listOfItem = new ArrayList<>();
        if(!cateringOrder.isEmpty())
        {
            for(Map.Entry<String, CateringObjectInfo> entry : cateringOrder.entrySet())
            {
                //// TODO: 12/3/2017 check this; changed Map's val to CatObjInf, haven't checked if it works 
                listOfItem.add(entry.getValue() + " :: " + entry.getKey().toString());
            }
            Log.d("TKT_cashier","listOfItems: "+listOfItem.toString());
            ArrayAdapter<String>adapter = new ArrayAdapter(context, R.layout.catering_custom_list_view, listOfItem);
            listView.setAdapter(adapter);
        }
        else
            Log.d("TKT_cashier","nothing to see here");


    }
    
    public static void sharedUpdateCatering(String price)
    {
        //// TODO: 12/3/2017 make sure to update price if item is removed or added!! 
        progressEdit = checkPrefs.edit();
        progressEdit.putString(CATERING_ALTOGETHER,price);
        progressEdit.commit();
    }
}
