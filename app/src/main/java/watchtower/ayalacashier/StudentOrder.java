package watchtower.ayalacashier;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.util.HashMap;

//// TODO: 11/2/2017 when changing an order to the same item but different quantity, the price isn't right - hot & dessert

public class StudentOrder extends AppCompatActivity {


    /*paypal
    PayPalConfiguration configuration;
    String  paypalClientId;
    Intent service;
    int paypalRequest = 999;
    */

    static String message = "";
    //==============
    Context context;
    HashMap<String, String>orderDetails = new HashMap<>();
    String ORDER_TIME = "orderTime";
    String ORDER_SAND = "orderSand";
    String ORDER_NOTES = "orderNotes";
    String ORDER_NAME = "orderName";
    String ORDER_DESSERT = "orderDessert";
    String ORDER_SALAD = "orderSalad";
    String ORDER_HOTS = "orderHots";
    final String ten = "10:00";
    final String twelve = "12:00";
    final String one = "13:30";
    final String three = "15:30";
    //sandwich
    String [] sandwich = new String [3];
    final int IND_BREAD_TYPE = 0;
    final int IND_SANDWICH_TYPE = 1;
    final int IND_SANDWICH_PRICE = 2;
    final String openDialogFlag = "openDialogFlag";
    final String baguetteSabih = 20+"";
    public static boolean PAID = false;

    //salad
    final String ONE = 1+"";
    final String ZERO = 0+"";
    final int IND_SALAD_ADDS = 0;
    final int IND_SALAD_BREAD = 1;
    final int IND_SALAD_ADD_PRICE = 2;
    final int IND_SALAD_BREAD_PRICE = 3;
    final String NO_BREAD = "ללא";
    final String YES_BREAD = "כן";
    final String ORDER_STRING_SALAD_ADDS = "תוספות: ";
    final String ORDER_STRING_SALAD_BREAD = "פרוסת לחם: ";
    String [] salad = {ZERO,NO_BREAD, ZERO, ZERO};


    //hots
    final int HOT_ITEM = 0;
    final int HOT_QUAN = 1;
    final int HOT_PRICE = 2;
    String [] hots = {null, HOT_QUAN+"", 0+""};

    //meusli
    int MEU_ITEM = 0;
    int MEU_QUAN = 1;
    int MEU_PRICE = 2;
    String [] dessert = {null, MEU_QUAN+"", 0+""};

    //shared
    public static final String CHOSEN_SAND = "chosenSand";
    public static final String CHOSEN_BREAD = "chosenBread";
    public static final String SALAD = "סלט";
    public static final String NUM_SALAD_ADDS = "numSaladAdds";
    public static final String CHOSEN_SALAD_BREAD = "chosenSaladBread";
    public static final String CHOSEN_HOT = "chosenHots";
    public static final String CHOSEN_DESSERT = "chosenDessert";

    static TextView sandTxtView, saladTxtView, hotsTxtView, dessertTxtView;
    static LinearLayout sandParent, saladParent, hotParent, dessertParent;
    static RelativeLayout bottomView;
    EditText nameFromET, notesFromET;
    TextView payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_order);
        context = this;
        sandTxtView = (TextView)findViewById(R.id.studentItem);
        saladTxtView = (TextView)findViewById(R.id.saladItem);
        hotsTxtView = (TextView)findViewById(R.id.hotItem);
        dessertTxtView = (TextView)findViewById(R.id.dessertItem);
        sandParent = (LinearLayout)findViewById(R.id.sand);
        hotParent = (LinearLayout)findViewById(R.id.itemHot);
        saladParent = (LinearLayout)findViewById(R.id.itemSalad);
        dessertParent = (LinearLayout)findViewById(R.id.itemDessert);
        bottomView = (RelativeLayout)findViewById(R.id.orderRelativeLayout);
        nameFromET = (EditText)findViewById(R.id.studentName);
        notesFromET = (EditText)findViewById(R.id.studentNotes);
        payment = (TextView)findViewById(R.id.paymentTextField);
        initBoxes();
        message = "";
        nameFromET.requestFocus();
        //Log.d("TKT_studentOrder","currDay: "+Cashier.c.get(Calendar.DAY_OF_YEAR));
        Log.d("TKT_studentOrder","krypt: "+Cashier.KRYPT.generateCrypto());



        //paypal
        Cashier.configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(Cashier.paypalClientId);
        Cashier.service = new Intent(this, PayPalService.class);
        Cashier.service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, Cashier.configuration);
        startService(Cashier.service);//paypal service listening to calls of payment
        //===============


        ImageButton deleteSandFromOrder = (ImageButton)findViewById(R.id.cancelSand);
        ImageButton deleteHotFromOrder = (ImageButton)findViewById(R.id.cancelHot);
        ImageButton deleteSaladFromOrder = (ImageButton)findViewById(R.id.cancelSalad);
        ImageButton deleteDessertFromOrder = (ImageButton)findViewById(R.id.cancelDessert);
        
        deleteSandFromOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","deleteItemFromOrder====================");
                reducePriceAfterRemoveItem(orderDetails.get(ORDER_SAND), sandwich, null);
                Log.d("TKT_studentOrder","itemRemoved: "+orderDetails.get(ORDER_SAND));
                Log.d("TKT_studentOrder","itemPrice: "+sandwich[IND_SANDWICH_PRICE]);
                sandwich[IND_SANDWICH_PRICE] = null;
                sandwich[IND_BREAD_TYPE] = null;
                sandwich[IND_SANDWICH_TYPE] = null;
                orderDetails.remove(ORDER_SAND);
                sandParent.setVisibility(View.GONE);
            }
        });
        deleteHotFromOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","deleteItemFromOrder");
                reducePriceAfterRemoveItem(orderDetails.get(ORDER_HOTS), hots, hots[HOT_QUAN]);
                Log.d("TKT_studentOrder","itemRemoved: "+orderDetails.get(ORDER_HOTS));
                Log.d("TKT_studentOrder","itemPrice: "+hots[HOT_PRICE]);

                hots[HOT_PRICE] = ZERO;
                hots[HOT_QUAN] = ONE;
                hots[HOT_ITEM] = null;

                orderDetails.remove(ORDER_HOTS);
                hotParent.setVisibility(View.GONE);
            }
        });
        deleteSaladFromOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","deleteItemFromOrder");
                reducePriceAfterRemoveItem(orderDetails.get(ORDER_SALAD), salad, null);
                Log.d("TKT_studentOrder","itemRemoved: "+orderDetails.get(ORDER_SALAD));
                Log.d("TKT_studentOrder","itemPrice: "+salad[IND_SALAD_ADD_PRICE]+", "+salad[IND_SALAD_BREAD_PRICE]);
                salad[IND_SALAD_ADD_PRICE] = ZERO;
                salad[IND_SALAD_BREAD_PRICE] = ZERO;
                salad[IND_SALAD_ADDS] = ZERO;
                salad[IND_SALAD_BREAD] = NO_BREAD;
                orderDetails.remove(ORDER_SALAD);
                saladParent.setVisibility(View.GONE);
            }
        });
        deleteDessertFromOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","deleteItemFromOrder");
                reducePriceAfterRemoveItem(orderDetails.get(ORDER_DESSERT), dessert, dessert[MEU_QUAN]);
                Log.d("TKT_studentOrder","itemRemoved: "+orderDetails.get(ORDER_DESSERT));
                Log.d("TKT_studentOrder","itemPrice: "+dessert[MEU_PRICE]);
                dessert[MEU_PRICE] = ZERO;
                dessert[MEU_QUAN] = ONE;
                dessert[MEU_ITEM] = null;
                orderDetails.remove(ORDER_DESSERT);
                dessertParent.setVisibility(View.GONE);
            }
        });
        //EditText notes = (EditText)findViewById(R.id.studentNotes);
    }

    public void reducePriceAfterRemoveItem(String itemRemoved, String [] itemArr, String quan)
    {
        Log.d("TKT_studentOrder","reducePrice..==================");
         //2 is the index of price in every array, in salads, there is another price

        double doublePrice = Double.parseDouble(itemArr[2]);//*Integer.parseInt(itemArr[1]);
        if(quan != null)
            doublePrice *= Double.parseDouble(quan);
        Log.d("TKT_studentOrder","doublePrice: "+doublePrice);
        if(itemArr.length > 3)
        {
            String stringSaladBreadPrice = itemArr[IND_SALAD_BREAD_PRICE];
            double doubleSaladBreadPrice = Double.parseDouble(stringSaladBreadPrice) + Cashier.SALAD_PRICES[Cashier.IND_SALAD];
            doublePrice += doubleSaladBreadPrice;
        }
        String stringPaymentTxt = payment.getText().toString();
        double doublePaymentTxt = Double.parseDouble(stringPaymentTxt);

        Log.d("TKT_studentOrder","-doublePrice + doublePaymentTxt: " + (-doublePrice + doublePaymentTxt));
        double difference = doublePaymentTxt - doublePrice;
        payment.setText(difference+"");

    }

    public void cancel(View v)
    {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setTime(View v)
    {
        hideKeyboard(v);
        Log.d("TKT_studentOrder","setTime================");
        String hour = orderDetails.get(ORDER_TIME);
        //Log.d("TKT_studentOrder","tag: "+v.getTag().toString());
        v.setBackground(getDrawable(R.drawable.shape_gray));
        orderDetails.put(ORDER_TIME, v.getTag().toString());
        if(hour != null)
            buttonHandler(hour, v.getTag().toString());//change others' color



    }

    public void buttonHandler(String tag,String currentTag)
    {//handles changing view of button from pressed to unpressed
        // - get prev pressed button using 'tag', then change its background
        Log.d("TKT_studentOrder","getChosenButton");
            Button button = null;
            switch (tag) {
                case ten: {
                    Log.d("TKT_studentOrder", "chosenTen");
                    button = (Button) findViewById(R.id.tenOrder);
                    break;
                }
                case twelve: {
                    Log.d("TKT_studentOrder", "chosenTwelve");
                    button = (Button) findViewById(R.id.twelveOrder);
                    break;
                }
                case one: {
                    Log.d("TKT_studentOrder", "chosenOne");
                    button = (Button) findViewById(R.id.oneOrder);
                    break;
                }
                case three: {
                    Log.d("TKT_studentOrder", "chosenThree");
                    button = (Button) findViewById(R.id.threeOrder);
                    break;
                }

                //sandwiches
                case 1+"":
                {
                    Log.d("TKT_studentOrder", "1");
                    button = (Button) Cashier.dialog.findViewById(R.id.shakshukaButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 2+"":
                {
                    Log.d("TKT_studentOrder", "2");
                    button = (Button) Cashier.dialog.findViewById(R.id.tunaSaladButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }case 3+"":
                {
                    Log.d("TKT_studentOrder", "3");
                    button = (Button) Cashier.dialog.findViewById(R.id.greenOmletteButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 4+"":
                {
                    Log.d("TKT_studentOrder", "4");
                    button = (Button) Cashier.dialog.findViewById(R.id.sabihButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 5+"":
                {
                    Log.d("TKT_studentOrder", "5");
                    button = (Button) Cashier.dialog.findViewById(R.id.spicyEggplantButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 6+"":
                {
                    Log.d("TKT_studentOrder", "6");
                    button = (Button) Cashier.dialog.findViewById(R.id.pestoButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 7+"":
                {
                    Log.d("TKT_studentOrder", "7");
                    button = (Button) Cashier.dialog.findViewById(R.id.cheeseEggplantBuutton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 8+"":
                {
                    Log.d("TKT_studentOrder", "8");
                    button = (Button) Cashier.dialog.findViewById(R.id.eggSaladbutton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 9+"":
                {
                    Log.d("TKT_studentOrder", "9");
                    button = (Button) Cashier.dialog.findViewById(R.id.bulgarianButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 10+"":
                {
                    Log.d("TKT_studentOrder", "10");
                    button = (Button) Cashier.dialog.findViewById(R.id.creamCheeseButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 11+"":
                {
                    Log.d("TKT_studentOrder", "11");
                    button = (Button) Cashier.dialog.findViewById(R.id.tunaButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 12+"":
                {
                    Log.d("TKT_studentOrder", "12");
                    button = (Button) Cashier.dialog.findViewById(R.id.yellowCheeseButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 13+"":
                {
                    Log.d("TKT_studentOrder", "13");
                    button = (Button) Cashier.dialog.findViewById(R.id.tivolButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 14+"":
                {
                    Log.d("TKT_studentOrder", "14");
                    button = (Button) Cashier.dialog.findViewById(R.id.omletteButton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 15+"":
                {
                    Log.d("TKT_studentOrder", "15");
                    button = (Button) Cashier.dialog.findViewById(R.id.avocadobutton);
                    String [] temp = button.getText().toString().split("\n");
                    sandwich[IND_SANDWICH_TYPE] = temp[0];
                    sandwich[IND_SANDWICH_PRICE] = temp[1];
                    break;
                }
                case 16+"":
                {//breadSlice of salads
                    button = (Button)Cashier.dialog.findViewById(R.id.breadButton);
                    break;
                }
                //hots===============
                case 17+"":
                {
                    button = (Button)Cashier.dialog.findViewById(R.id.milanesaButton);
                    String [] temp = button.getText().toString().split("\n");
                    hots[HOT_ITEM] = temp[0];
                    hots[HOT_PRICE] = temp[1];
                    break;
                }
                case 18+"":
                {
                    button = (Button)Cashier.dialog.findViewById(R.id.kuskusButton);
                    String [] temp = button.getText().toString().split("\n");
                    hots[HOT_ITEM] = temp[0];
                    hots[HOT_PRICE] = temp[1];
                    break;
                }
                case 19+"":
                {
                    button = (Button)Cashier.dialog.findViewById(R.id.pastaButton);
                    String [] temp = button.getText().toString().split("\n");
                    hots[HOT_ITEM] = temp[0];
                    hots[HOT_PRICE] = temp[1];
                    break;
                }
                //meuslis
                case 20+"":
                {
                    button = (Button)Cashier.dialog.findViewById(R.id.meusliButton);
                    String [] temp = button.getText().toString().split("\n");
                    dessert[MEU_ITEM] = temp[0];
                    dessert[MEU_PRICE] = temp[1];
                    break;
                }
                case 21+"":
                {
                    button = (Button)Cashier.dialog.findViewById(R.id.watermelonButton);
                    String [] temp = button.getText().toString().split("\n");
                    dessert[MEU_ITEM] = temp[0];
                    dessert[MEU_PRICE] = temp[1];
                    break;
                }
                //hots: soup
                case 22+"":
                {//largeSoup
                    button = (Button)Cashier.dialog.findViewById(R.id.largeSoup);
                    String [] temp = button.getText().toString().split("\n");
                    hots[HOT_ITEM] = temp[0];
                    hots[HOT_PRICE] = temp[1];
                    break;
                }
                case 23+"":
                {//small soup
                    button = (Button)Cashier.dialog.findViewById(R.id.smallSoup);
                    String [] temp = button.getText().toString().split("\n");
                    hots[HOT_ITEM] = temp[0];
                    hots[HOT_PRICE] = temp[1];
                    break;
                }
                /*
                case 24+"":
                {
                    button = (Button)Cashier.dialog.findViewById(R.id.smallSoup);
                    String [] temp = button.getText().toString().split("\n");
                    hots[HOT_ITEM] = temp[0];
                    hots[HOT_PRICE] = temp[1];
                    break;
                }
                */

            }

            if(currentTag == openDialogFlag)
            {
                Log.d("TKT_studentOrder","currTag = openDialogFlag");
                button.setBackground(getDrawable(R.drawable.shape_gray));
            }
            else{
                Log.d("TKT_studentOrder","(tag == currTag) ? true : false >>" + (tag == currentTag));
                if(!tag.equals(currentTag))
                    button.setBackground(getDrawable(R.drawable.shape));

                }
    }

    public void initSandButtons()
    {
        Button tempSand = (Button)Cashier.dialog.findViewById(R.id.shakshukaButton);
        String [] tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.tunaSaladButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.greenOmletteButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.tunaSaladButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.sabihButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.spicyEggplantButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.pestoButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.cheeseEggplantBuutton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.eggSaladbutton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.bulgarianButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.creamCheeseButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.tunaButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.yellowCheeseButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.tivolButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.omletteButton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

        tempSand = (Button)Cashier.dialog.findViewById(R.id.avocadobutton);
        tempTxt = tempSand.getText().toString().split("\n");
        tempSand.setText(tempTxt[0]);

    }

    public void sandwiches(View v)
    {
        Log.d("TKT_studentOrder","sandwiches=====================");
        hideKeyboard(v);
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.student_sandwich_dialog_layout);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button finish = (Button)Cashier.dialog.findViewById(R.id.check);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.cancelCheck);
        final RadioButton ww = (RadioButton) Cashier.dialog.findViewById(R.id.wholeWheatRadio);
        final RadioButton bg = (RadioButton) Cashier.dialog.findViewById(R.id.baguetteRadio);





        //initSandButtons();
        //get chosen data from before, if exists:
        String bread = Cashier.checkPrefs.getString(CHOSEN_BREAD,null);
        String sand = Cashier.checkPrefs.getString(CHOSEN_SAND, null);
        Log.d("TKT_studentOrder","sandwich[IND_SANDWICH_PRICE]: "+sandwich[IND_SANDWICH_PRICE]);
        final String tempPayment = sandwich[IND_SANDWICH_PRICE];
        /*
        //handles displaying prev chosen sandwich and bread type
        if(bread != null)
        {
            Log.d("TKT_studentOrder","bread != null");
            //redundant is that radio is sent to chooseBread and there savedAgain to shared -- consider change?
            if(bread.equals(getString(R.string.wholeWheat))) {
                (ww).setChecked(true);
                chooseBread(ww);
            }
            else {
                (bg).setChecked(true);
                chooseBread(bg);
            }
                //chooseBread(Cashier.dialog.findViewById(R.id.baguetteRadio))
        }


        if(sand != null)
        {
            buttonHandler(sand, openDialogFlag);
        }
        */
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !bg.isChecked() && !ww.isChecked())
                {
                    Log.d("TKT_studentOrder","no bread checked");
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(context);
                    mensaje.setMessage(R.string.pleaseChooseBreadType).create();
                    mensaje.show();
                    //Toast.makeText(StudentOrder.this, R.string.pleaseChooseBreadType, Toast.LENGTH_SHORT).show();
                }
                else
                    if(sandwich[IND_SANDWICH_TYPE] == null)
                    {
                        Log.d("TKT_studentOrder","no sand chosen");
                        AlertDialog.Builder mensaje = new AlertDialog.Builder(context);
                        mensaje.setMessage(R.string.pleaseChooseSand).create();
                        mensaje.show();
                        //Toast.makeText(StudentOrder.this, R.string.pleaseChooseSand, Toast.LENGTH_SHORT).show();
                    }
                else
                    {
                    if(tempPayment != null)
                        removeFromPayment(tempPayment,ONE);
                    orderDetails.put(ORDER_SAND, sandwich[IND_BREAD_TYPE] + ": " + sandwich[IND_SANDWICH_TYPE]);
                    Log.d("TKT_studentOrder", "orderDetail: " + orderDetails.get(ORDER_SAND));
                    setPayment(payment, sandwich[IND_SANDWICH_PRICE]);
                    orderDetailHandler(ORDER_SAND, sandTxtView, sandParent);
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

    public void removeFromPayment(String paymentToRemove, String quan)
    {
        double paymentDouble = Double.parseDouble(payment.getText().toString());
        double paymentToRemoveDouble = Double.parseDouble(paymentToRemove);
        double quanInt = Double.parseDouble(quan);
        paymentDouble -= (paymentToRemoveDouble*quanInt);
        Log.d("TKT_studentOrder","paymentDouble: "+paymentDouble);
        payment.setText(paymentDouble+"");
    }

    public void setPayment(TextView tv, String payment)
    {
        Log.d("TKT_studentOrder","setPayment=============");
        Log.d("TKT_studentOrder","payment: " + payment);
        String currPaymentString = tv.getText().toString();
        Log.d("TKT_studentOrder","currentPayment: " + currPaymentString);
        tv.setText("");
        double currPaymentDouble = Double.parseDouble(currPaymentString);
        if(currPaymentDouble != 0)
        {
            double newPayment = Double.parseDouble(payment);
            newPayment += currPaymentDouble;
            tv.setText(newPayment+"");
        }
        else {
            double temp = Double.parseDouble(payment);
            tv.setText(temp+"");
        }

    }

    public void chooseBread(View v)
    {
        Log.d("TKT_studentOrder","chooseBread");
        Button radio = (Button)v;
        if(radio.getText().toString().equals(getString(R.string.bagette)))
        {
            Button tempSabih = (Button) Cashier.dialog.findViewById(R.id.sabihButton);
            String [] temp = tempSabih.getText().toString().split("\n");
            String newName = temp[0]+"\n"+baguetteSabih;
            tempSabih.setText(newName);
            if(sandwich[IND_SANDWICH_TYPE] != null && sandwich[IND_SANDWICH_TYPE].contains("סביח"))
            {
                sandwich[IND_SANDWICH_PRICE] = baguetteSabih;
            }
            //sandwich[IND_SANDWICH_TYPE] = temp[0];
            //sandwich[IND_SANDWICH_PRICE] = baguetteSabih;
        }
        else
        {
            Button tempSabih = (Button) Cashier.dialog.findViewById(R.id.sabihButton);
            String [] temp = tempSabih.getText().toString().split("\n");
            String newName = temp[0]+"\n"+(int)Cashier.SANDWICH_PRICES[Cashier.IND_SABIH];
            tempSabih.setText(newName);
            if(sandwich[IND_SANDWICH_TYPE] != null && sandwich[IND_SANDWICH_TYPE].contains("סביח"))
            {
                sandwich[IND_SANDWICH_PRICE] = Cashier.SANDWICH_PRICES[Cashier.IND_SABIH]+"";
            }
            //sandwich[IND_SANDWICH_TYPE] = temp[0];

        }
        sandwich[IND_BREAD_TYPE] = v.getTag().toString();
        Cashier.sharedUpdateSandBread(v.getTag().toString());
    }

    public void chooseSandwich(View v)
    {
        //consider maybe redirect here from an OnClickListener of each button

        Button b = (Button)v;
        Log.d("TKT_studentOrder","chooseSandwich====================");
        String [] txt = b.getText().toString().split("\n");
        String prevSand = Cashier.checkPrefs.getString(CHOSEN_SAND,null);
        if(prevSand != null)
        {
            //find button and change color
            buttonHandler(prevSand, b.getTag().toString());
        }
        Cashier.sharedUpdateSand(v.getTag().toString());
        b.setBackground(getDrawable(R.drawable.shape_gray));
        //Log.d("TKT_studentOrder","txt[0]: "+txt[0]);
        sandwich[IND_SANDWICH_TYPE] = txt[0];
        sandwich[IND_SANDWICH_PRICE] = txt[1];
    }

    public void salads(View v)
    {
        Log.d("TKT_studentOrder","salads==============");
        hideKeyboard(v);
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.student_salad_dialog_layout);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button finish = (Button)Cashier.dialog.findViewById(R.id.check);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.cancelCheck);

        TextView addition = (TextView)Cashier.dialog.findViewById(R.id.saladAdditionBuutton);
        String [] tempTxt = (getString(R.string.saladAddition)).split("\n");
        addition.setText(tempTxt[0]);
        final Button breadSlice = (Button)Cashier.dialog.findViewById(R.id.breadButton);
        //tempTxt = (getString(R.string.bread)).split("\n");
        //breadSlice.setText(tempTxt[0]);

        final String tempAddPrice = salad[IND_SALAD_ADD_PRICE];
        final String tempBreadPrice = salad[IND_SALAD_BREAD_PRICE];
        salad[IND_SALAD_BREAD_PRICE] = ZERO;
        salad[IND_SALAD_ADD_PRICE] = ZERO;
        double priceToRemove = Cashier.SALAD_PRICES[Cashier.IND_SALAD];
        if(!tempAddPrice.equals(ZERO))
        {
            priceToRemove += Double.parseDouble(tempAddPrice);
        }
        if(!tempBreadPrice.equals(ZERO))
        {
            priceToRemove += Double.parseDouble(tempBreadPrice);
        }

        Log.d("TKT_studentOrder","priceToRemove: "+priceToRemove);
        Log.d("TKT_studentOrder","salad[ind_price_adds]: "+tempAddPrice);
        Log.d("TKT_studentOrder","salad[ind_price_bread]: "+tempBreadPrice);

        /*
        // show prev chosen state of breadSlice - I don't want this for now
        String breadSlicePrevState = Cashier.checkPrefs.getString(CHOSEN_SALAD_BREAD,NO_BREAD);
        if(breadSlicePrevState.equals(YES_BREAD)) {
            Log.d("TKT_studentOrder","breadSlicePrevState: "+breadSlicePrevState);
            //breadSlice.setBackground(getDrawable(R.drawable.shape_gray));
            buttonHandler(breadSlice.getTag().toString(),openDialogFlag);
            salad[IND_SALAD_BREAD] = YES_BREAD;
        }
        */
        //picker==============
        int addsPicked =  Integer.parseInt(Cashier.checkPrefs.getString(NUM_SALAD_ADDS,ZERO));
        NumberPicker picker = (NumberPicker)Cashier.dialog.findViewById(R.id.studentSaladNumPick);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setMinValue(0);
        picker.setMaxValue(6);
        picker.setWrapSelectorWheel(false);
        //picker.setValue(addsPicked);
        //salad[IND_SALAD_ADDS] = addsPicked+"";
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Log.d("TKT_studentOrder","picker.onValueChangeListener=============");
                salad[IND_SALAD_ADDS] = newVal+"";
                double price = newVal*Cashier.SALAD_PRICES[Cashier.IND_SALAD_ADDITION];
                salad[IND_SALAD_ADD_PRICE] = price+"";
            }
        });
        //===================
        breadSlice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(salad[IND_SALAD_BREAD] == NO_BREAD )//(Cashier.checkPrefs.getString(CHOSEN_SALAD_BREAD,NO_BREAD) == NO_BREAD)
                {
                    //Cashier.sharedUpdateSaladBread(1);
                    salad[IND_SALAD_BREAD] = YES_BREAD;
                    breadSlice.setBackground(getDrawable(R.drawable.shape_gray));
                    salad[IND_SALAD_BREAD_PRICE] = Cashier.SALAD_PRICES[IND_SALAD_BREAD]+"";
                }
                else
                {
                    //Cashier.sharedUpdateSaladBread(0);
                    salad[IND_SALAD_BREAD] = NO_BREAD;
                    breadSlice.setBackground(getDrawable(R.drawable.shape));
                    salad[IND_SALAD_BREAD_PRICE] = "0";
                }
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tempAddPrice != null)
                    removeFromPayment(tempAddPrice,ONE);
                if(tempBreadPrice != null)
                    removeFromPayment(tempBreadPrice,ONE);
                if(saladParent.getVisibility() == View.VISIBLE) {
                    Log.d("TKT_studentOrder","parent is visible");
                    removeFromPayment(Cashier.SALAD_PRICES[Cashier.IND_SALAD] + "",ONE);
                }

                orderDetails.put(ORDER_SALAD,SALAD+":\n"+
                        ORDER_STRING_SALAD_ADDS + salad[IND_SALAD_ADDS]+"\n"+
                        ORDER_STRING_SALAD_BREAD + salad[IND_SALAD_BREAD]);
                Cashier.sharedUpdateSaladAdds(salad[IND_SALAD_ADDS]);
                Cashier.sharedUpdateSaladBread(salad[IND_SALAD_BREAD]);
                double combinedPrice = Double.parseDouble(salad[IND_SALAD_ADD_PRICE]) + Double.parseDouble(salad[IND_SALAD_BREAD_PRICE]) + Cashier.SALAD_PRICES[Cashier.IND_SALAD];
                setPayment(payment, combinedPrice+"");
                Log.d("TKT_studentOrder","orderDetail: "+orderDetails.get(ORDER_SALAD));
                if(Integer.parseInt(salad[IND_SALAD_ADDS]) != 0) {
                    //Toast.makeText(StudentOrder.this, R.string.addsPick, Toast.LENGTH_LONG).show();
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(context);
                    mensaje.setMessage(R.string.addsPick).create();
                    mensaje.show();
                }
                salad[IND_SALAD_ADDS] = IND_SALAD_ADDS+"";
                salad[IND_SALAD_BREAD] = NO_BREAD;
                orderDetailHandler(ORDER_SALAD, saladTxtView, saladParent);
                Cashier.dialog.dismiss();
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

    public void hots(View v)
    {
        Log.d("TKT_studentOrder","hots===============");
        hideKeyboard(v);
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.student_hots_dialog_layout);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button finish = (Button)Cashier.dialog.findViewById(R.id.check);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.cancelCheck);
        NumberPicker picker = (NumberPicker)Cashier.dialog.findViewById(R.id.studentHotNumPick);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setMinValue(1);
        picker.setMaxValue(10);
        picker.setWrapSelectorWheel(false);


        //final Button milanesa = (Button)Cashier.dialog.findViewById(R.id.milanesaButton);
        //final Button kuskus = (Button)Cashier.dialog.findViewById(R.id.kuskusButton);
        //final Button pasta = (Button)Cashier.dialog.findViewById(R.id.pastaButton);

        //final Button lentil = (Button)Cashier.dialog.findViewById(R.id.lentilButton);
        final Button largeSoup = (Button)Cashier.dialog.findViewById(R.id.largeSoup);
        final Button smallSoup = (Button)Cashier.dialog.findViewById(R.id.smallSoup);

        String hotChosenPrev = Cashier.checkPrefs.getString(CHOSEN_HOT, null);
        final String tempPayment = hots[HOT_PRICE];
        final String tempQuan = hots[HOT_QUAN];
        hots[HOT_ITEM] = null;
        hots[HOT_QUAN] = ONE;
        Log.d("TKT_studentOrder","hots[HOT_ITEM]: "+tempPayment);
        Log.d("TKT_studentOrder","hots[HOT_QUAN]: "+tempQuan);

        //if(hotChosenPrev != null)
        {
            //buttonHandler(hotChosenPrev, openDialogFlag);
        }


        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                hots[HOT_QUAN] = newVal+"";
            }

        });


        /*
        String [] tempTxt = (getString(R.string.corn_milanesa)).split("\n");
        milanesa.setText(tempTxt[0]);
        tempTxt = (getString(R.string.kuskus)).split("\n");
        kuskus.setText(tempTxt[0]);
        tempTxt = (getString(R.string.pasta)).split("\n");
        pasta.setText(tempTxt[0]);

        tempTxt = (getString(R.string.lentil)).split("\n");
        lentil.setText(tempTxt[0]);
        tempTxt = (getString(R.string.veggie)).split("\n");
        largeSoup.setText(tempTxt[0]);
        tempTxt = (getString(R.string.smallSoup)).split("\n");
        smallSoup.setText(tempTxt[0]);
        */


        //// TODO: 10/31/2017 enable in time =======================vvvvvv
        //lentil.setEnabled(false);
        largeSoup.setEnabled(false);
        smallSoup.setEnabled(false);
        //========^^^^^^^^^^^============================================

        /*
        lentil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","lentilChosen");
                chooseHot(lentil);
            }
        });
        largeSoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","lentilChosen");
                chooseHot(largeSoup);
            }
        });
        smallSoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","lentilChosen");
                chooseHot(smallSoup);
            }
        });
*/

        //String hotChosenPrev = Cashier.checkPrefs.getString(CHOSEN_HOT,null);
        //Log.d("TKTK_studentOrder","hotChosenPrev: "+hotChosenPrev);
        //if(hotChosenPrev != null)
          ///  buttonHandler(hotChosenPrev,openDialogFlag);

        /*
        milanesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseHot(milanesa);
            }
        });
        kuskus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseHot(kuskus);
            }
        });
        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseHot(pasta);
            }
        });
        */

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //her update order
                if(hots[HOT_ITEM] == null)
                {
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(context);
                    mensaje.setMessage(R.string.pleaseChooseMeal).create();
                    mensaje.show();
                }
                else {
                    if(tempPayment != null)
                        removeFromPayment(tempPayment,tempQuan);
                    Log.d("TKT_studentOrder","hots[hot_item]: "+hots[HOT_ITEM]);
                    orderDetails.put(ORDER_HOTS, hots[HOT_QUAN] + " " + hots[HOT_ITEM]);
                    Log.d("TKT_studentOrder", "orderDetails: " + orderDetails.get(ORDER_HOTS));
                    double combinedPrice = Double.parseDouble(hots[HOT_QUAN]) * Double.parseDouble(hots[HOT_PRICE]);
                    setPayment(payment,combinedPrice+"");
                    orderDetailHandler(ORDER_HOTS, hotsTxtView, hotParent);
                    Cashier.dialog.dismiss();
                }
            }
        });
        Cashier.dialog.show();
    }

    public void chooseHot(View v)
    {//since there are 6 buttons, I decided to use an onClick rather than an onClickListener
        Button hot = (Button)v;
        hideKeyboard(v);
        String [] temp = hot.getText().toString().split("\n");
        Log.d("TKTK_studentOrder","temp[0]: "+temp[0]);

        String prevHot = Cashier.checkPrefs.getString(CHOSEN_HOT,null);
        if(prevHot != null)
        {
            Log.d("TKT_studentOrder","prevHot: "+prevHot);
            buttonHandler(prevHot, hot.getTag().toString());
        }
        hots[HOT_ITEM] = temp[0];
        Cashier.sharedUpdateHot(hot.getTag().toString());
        hots[HOT_PRICE] =temp[1];
        hot.setBackground(getDrawable(R.drawable.shape_gray));
    }

    public void fruitMeusli(View v)
    {
        Log.d("TKT_studentOrder","fruitMeusli==============");
        hideKeyboard(v);
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.student_meusli_dialog_layout);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button finish = (Button)Cashier.dialog.findViewById(R.id.check);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.cancelCheck);
        final Button meusli = (Button)Cashier.dialog.findViewById(R.id.meusliButton);
        final Button fruit = (Button)Cashier.dialog.findViewById(R.id.watermelonButton);
        //String [] tempTxt = (getString(R.string.fruitMuesli)).split("\n");
        //meusli.setText(tempTxt[0]);
        //tempTxt = (getString(R.string.watermelon)).split("\n");
        //fruit.setText(tempTxt[0]);


        final String tempQuan = dessert[MEU_QUAN];
        final String tempPayment = dessert[MEU_PRICE];
        dessert[MEU_QUAN] = ONE;
        dessert[MEU_ITEM] = null;
        //Log.d("TKT_studentOrder","tempQuan: "+tempQuan);
        //Log.d("TKT_studentOrder","tempPayment: "+tempPayment);
        String dessertChosenPrev = Cashier.checkPrefs.getString(CHOSEN_DESSERT,null);
        //if(dessertChosenPrev != null)
        {
            //buttonHandler(dessertChosenPrev, openDialogFlag);
        }

        NumberPicker picker = (NumberPicker)Cashier.dialog.findViewById(R.id.studentMeuNumPick);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setMinValue(1);
        picker.setMaxValue(10);
        picker.setWrapSelectorWheel(false);

        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                dessert[MEU_QUAN] = newVal+"";
            }

        });


        meusli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDessert(meusli);
            }
        });
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDessert(fruit);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dessert[MEU_ITEM] != null) {
                    if(tempPayment != null)
                        removeFromPayment(tempPayment,tempQuan);
                    orderDetails.put(ORDER_DESSERT, dessert[MEU_QUAN] + " " + dessert[MEU_ITEM]);
                    Log.d("TKT_studentPrder", "orderDessert: " + orderDetails.get(ORDER_DESSERT));
                    double combinedPrice = Double.parseDouble(dessert[MEU_QUAN]) * Double.parseDouble(dessert[MEU_PRICE]);
                    setPayment(payment,combinedPrice+"");
                    //dessert[MEU_ITEM] = null;
                    //dessert[MEU_QUAN] = 1+"";
                    orderDetailHandler(ORDER_DESSERT, dessertTxtView, dessertParent);
                    Cashier.dialog.dismiss();
                }
                else
                {
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(context);
                    mensaje.setMessage(R.string.pleaseChooseMeal).create();
                    mensaje.show();
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

    public void chooseDessert(Button dess)
    {//couldn't use this for both dessert and hots cuz of the Cashier.foo call - it's different for each object
        Log.d("TKT_studentOrder","chooseDessertOrHots===============");
        String [] temp = dess.getText().toString().split("\n");
        String prevDes = Cashier.checkPrefs.getString(CHOSEN_DESSERT,null);
        if(prevDes != null)
        {
            buttonHandler(prevDes, dess.getTag().toString());
        }
        Cashier.sharedUpdateDessert(dess.getTag().toString());
        double quan = Double.parseDouble(dessert[MEU_QUAN]);
        dessert[MEU_ITEM] = temp[0];
        dessert[MEU_PRICE] = (Double.parseDouble(temp[1])*quan)+"";
        Log.d("TKT_studentOrder","priceee: "+dessert[MEU_PRICE]);
        dess.setBackground(getDrawable(R.drawable.shape_gray));

    }


    public void orderDetailHandler(String key, TextView tv, LinearLayout parent)
    {
        Log.d("TKT_studentOrder","orderDetailHandler============");
        String order = orderDetails.get(key);
        parent.setVisibility(View.VISIBLE);
        Log.d("TKT_studentOrder","order: "+order);
        tv.setText(order);
    }

    @Override
    protected void onResume() {
        String studentName = Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null);
        Log.d("TKT_studentOrder","onResume, employeeName: "+studentName);
        EditText t = (EditText)findViewById(R.id.studentName);
        t.setText(studentName);
        orderDetails.put(ORDER_NAME,studentName);
        message = Cashier.checkPrefs.getString(Cashier.MESSAGE,null);
        super.onResume();
    }


    public void order(View v)
    {

        Log.d("TKT_studentOrder","order=================");

        String name = nameFromET.getText().toString();
         //String message = "";
        if(name.length() < 2)
        {
            AlertDialog.Builder mensaje = new AlertDialog.Builder(context);
            mensaje.setMessage(R.string.pleaseChooseName).create();
            mensaje.show();
        }
        else
            {
            orderDetails.put(ORDER_NAME, name);
            message = messageName() + System.getProperty("line.separator")
                    + messageTime() + System.getProperty("line.separator");


            String sandwich = orderDetails.get(ORDER_SAND);
            String hot = orderDetails.get(ORDER_HOTS);
            String dessert = orderDetails.get(ORDER_DESSERT);
            String salad = orderDetails.get(ORDER_SALAD);
            String time = orderDetails.get(ORDER_TIME);
            if(time == null)
            {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(context);
                mensaje.setMessage(R.string.pleaseChooseTime).create();
                mensaje.show();
            }
            else if (sandwich == null && hot == null && dessert == null && salad == null)//whats about salad?
            {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(context);
                mensaje.setMessage(R.string.pleaseChooseMeal).create();
                mensaje.show();
            }
            else
                {
                Log.d("TKT_studentOrder", "message: " + message);
                if (sandwich != null)
                    message += sandwich + System.getProperty("line.separator");
                if (hot != null)
                    message += hot + System.getProperty("line.separator");
                if (dessert != null)
                    message += dessert + System.getProperty("line.separator");
                if (salad != null)
                    message += salad + System.getProperty("line.separator");
                message =  messageNotes(message);
                    Cashier.putMessageInShared(message);

                    Cashier.pay(this, this, payment.getText().toString() );///bad practice!!! but works for now

                        //Cashier.sendOrderToA(message, this);


            }
        }
    }

    public String messageNotes(String m)
    {
        String message = "הערות: ";
        String fromET = notesFromET.getText().toString();
        if(fromET.length() > 3)
        {
            message += fromET;
            m += message;
        }

        Log.d("TKT_studentOrder","entire message: "+m );
        return m;
    }

    public String messageTime()
    {
        String message = "לשעה: ";
        String time = orderDetails.get(ORDER_TIME);
        /*
        if(time == null)
        {
            AlertDialog.Builder mensaje = new AlertDialog.Builder(context);
            mensaje.setMessage(R.string.pleaseChooseTime).create();
            mensaje.show();
            return  null;
        }
        else
            */
        {
            message += time;
            return message;
        }

    }

    public String messageName()
    {
        String message = "הזמנה ל: ";
        message += orderDetails.get(ORDER_NAME);
        return message;

    }

    public void initBoxes()
    {
        Log.d("TKT_studentOrder","intiBoxes was called=======================");
        nameFromET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        notesFromET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }

            }
        });
    }
    public void hideKeyboard(View view) {
        Log.d("TKT_studentOrder","hiding keyboard===================");

        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Cashier.paypalRequestCode)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation != null && confirmation.getProofOfPayment().getState().equals(Cashier.PAYMENT_APPROVED))
                {
                    Log.d("TKT_studentOrder","confirmation approved!");
                    if(message != null) {
                        //Cashier.KRYPT.generateCrypto();
                        message = Cashier.KRYPT.generateCrypto() + "\n"+message;
                        Log.d("TKT_studentOrder","messageWKRIPT: "+message);
                        Cashier.sendOrderToA(message, this);
                    }

                    else
                        Toast.makeText(context, "No order exists", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.d("TKT_studentOrder","confirmation is null");
                    Toast.makeText(context, "Confirmation failed", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}
