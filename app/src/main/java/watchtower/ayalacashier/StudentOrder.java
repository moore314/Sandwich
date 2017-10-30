package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

public class StudentOrder extends AppCompatActivity {

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
    String [] sandwich = new String [2];
    final int IND_BREAD_TYPE = 0;
    final int IND_SANDWICH_TYPE = 1;
    final String openDialogFlag = "openDialogFlag";

    //salad
    final int IND_SALAD_ADDS = 0;
    final int IND_SALAD_BREAD = 1;
    final String NO_BREAD = "ללא";
    final String YES_BREAD = "כן";
    final String ORDER_STRING_SALAD_ADDS = "תוספות: ";
    final String ORDER_STRING_SALAD_BREAD = "פרוסת לחם: ";
    String [] salad = {IND_SALAD_ADDS+"",NO_BREAD};

    //hots
    final int HOT_ITEM = 0;
    final int HOT_QUAN = 1;
    String [] hots = {null, HOT_QUAN+""};

    //meusli
    int MEU_ITEM = 0;
    int MEU_QUAN = 1;
    String [] dessert = {null, MEU_QUAN+""};



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


        ImageButton deleteSandFromOrder = (ImageButton)findViewById(R.id.cancelSand);
        ImageButton deleteHotFromOrder = (ImageButton)findViewById(R.id.cancelHot);
        ImageButton deleteSaladFromOrder = (ImageButton)findViewById(R.id.cancelSalad);
        ImageButton deleteDessertFromOrder = (ImageButton)findViewById(R.id.cancelDessert);
        deleteSandFromOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","deleteItemFromOrder");
                //// TODO: 10/30/2017 add r u sure
                sandParent.setVisibility(View.GONE);
            }
        });
        deleteHotFromOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","deleteItemFromOrder");
                //// TODO: 10/30/2017 add r u sure
                hotParent.setVisibility(View.GONE);
            }
        });
        deleteSaladFromOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","deleteItemFromOrder");
                //// TODO: 10/30/2017 add r u sure
                saladParent.setVisibility(View.GONE);
            }
        });
        deleteDessertFromOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_studentOrder","deleteItemFromOrder");
                //// TODO: 10/30/2017 add r u sure
                dessertParent.setVisibility(View.GONE);
            }
        });
        //EditText notes = (EditText)findViewById(R.id.studentNotes);
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
        Log.d("TKT_studentOrder","setTime================");
        String hour = orderDetails.get(ORDER_TIME);
        //Log.d("TKT_studentOrder","tag: "+v.getTag().toString());
        v.setBackground(getDrawable(R.drawable.shape_gray));
        orderDetails.put(ORDER_TIME, v.getTag().toString());
        if(hour != null)
            buttonHandler(hour, v.getTag().toString());//change others' color



    }

    public void buttonHandler(String tag,String currentTag)
    {
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
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 2+"":
                {
                    Log.d("TKT_studentOrder", "2");
                    button = (Button) Cashier.dialog.findViewById(R.id.tunaSaladButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }case 3+"":
                {
                    Log.d("TKT_studentOrder", "3");
                    button = (Button) Cashier.dialog.findViewById(R.id.greenOmletteButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 4+"":
                {
                    Log.d("TKT_studentOrder", "4");
                    button = (Button) Cashier.dialog.findViewById(R.id.sabihButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 5+"":
                {
                    Log.d("TKT_studentOrder", "5");
                    button = (Button) Cashier.dialog.findViewById(R.id.spicyEggplantButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 6+"":
                {
                    Log.d("TKT_studentOrder", "6");
                    button = (Button) Cashier.dialog.findViewById(R.id.pestoButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 7+"":
                {
                    Log.d("TKT_studentOrder", "7");
                    button = (Button) Cashier.dialog.findViewById(R.id.cheeseEggplantBuutton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 8+"":
                {
                    Log.d("TKT_studentOrder", "8");
                    button = (Button) Cashier.dialog.findViewById(R.id.eggSaladbutton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 9+"":
                {
                    Log.d("TKT_studentOrder", "9");
                    button = (Button) Cashier.dialog.findViewById(R.id.bulgarianButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 10+"":
                {
                    Log.d("TKT_studentOrder", "10");
                    button = (Button) Cashier.dialog.findViewById(R.id.creamCheeseButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 11+"":
                {
                    Log.d("TKT_studentOrder", "11");
                    button = (Button) Cashier.dialog.findViewById(R.id.tunaButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 12+"":
                {
                    Log.d("TKT_studentOrder", "12");
                    button = (Button) Cashier.dialog.findViewById(R.id.yellowCheeseButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 13+"":
                {
                    Log.d("TKT_studentOrder", "13");
                    button = (Button) Cashier.dialog.findViewById(R.id.tivolButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 14+"":
                {
                    Log.d("TKT_studentOrder", "14");
                    button = (Button) Cashier.dialog.findViewById(R.id.omletteButton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
                    break;
                }
                case 15+"":
                {
                    Log.d("TKT_studentOrder", "15");
                    button = (Button) Cashier.dialog.findViewById(R.id.avocadobutton);
                    sandwich[IND_SANDWICH_TYPE] = button.getText().toString();
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
                    hots[HOT_ITEM] = button.getText().toString();
                    break;
                }
                case 18+"":
                {
                    button = (Button)Cashier.dialog.findViewById(R.id.kuskusButton);
                    hots[HOT_ITEM] = button.getText().toString();
                    break;
                }
                case 19+"":
                {
                    button = (Button)Cashier.dialog.findViewById(R.id.pastaButton);
                    hots[HOT_ITEM] = button.getText().toString();
                    break;
                }
                //meuslis
                case 20+"":
                {
                    button = (Button)Cashier.dialog.findViewById(R.id.meusliButton);
                    break;
                }
                case 21+"":
                {
                    button = (Button)Cashier.dialog.findViewById(R.id.watermelonButton);
                    break;
                }
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
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.student_sandwich_dialog_layout);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button finish = (Button)Cashier.dialog.findViewById(R.id.check);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.cancelCheck);
        final RadioButton ww = (RadioButton) Cashier.dialog.findViewById(R.id.wholeWheatRadio);
        final RadioButton bg = (RadioButton) Cashier.dialog.findViewById(R.id.baguetteRadio);

        initSandButtons();
        //get chosen data from before, if exists:
        String bread = Cashier.checkPrefs.getString(CHOSEN_BREAD,null);
        String sand = Cashier.checkPrefs.getString(CHOSEN_SAND, null);

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
                //chooseBread(Cashier.dialog.findViewById(R.id.baguetteRadio));

        }
        if(sand != null) {
            buttonHandler(sand, openDialogFlag);
        }


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
                else {
                    orderDetails.put(ORDER_SAND, sandwich[IND_BREAD_TYPE] + ": " + sandwich[IND_SANDWICH_TYPE]);
                    Log.d("TKT_studentOrder", "orderDetail: " + orderDetails.get(ORDER_SAND));
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

    public void chooseBread(View v)
    {
        Log.d("TKT_studentOrder","chooseBread");
        sandwich[IND_BREAD_TYPE] = v.getTag().toString();
        Cashier.sharedUpdateSandBread(v.getTag().toString());
    }

    public void chooseSandwich(View v)
    {
        //consider maybe redirect here from an OnClickListener of each button
        Button b = (Button)v;
        Log.d("TKT_studentOrder","chooseSandwich");
        String [] txt = b.getText().toString().split("\n");
        String prevSand = Cashier.checkPrefs.getString(CHOSEN_SAND,null);
        if(prevSand != null)
        {
            //find button and change color
            buttonHandler(prevSand, b.getTag().toString());
        }
        Cashier.sharedUpdateSand(v.getTag().toString());
        b.setBackground(getDrawable(R.drawable.shape_gray));
        sandwich[IND_SANDWICH_TYPE] = txt[0];
    }

    public void salads(View v)
    {
        Log.d("TKT_studentOrder","salads");
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.student_salad_dialog_layout);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button finish = (Button)Cashier.dialog.findViewById(R.id.check);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.cancelCheck);

        TextView addition = (TextView)Cashier.dialog.findViewById(R.id.saladAdditionBuutton);
        String [] tempTxt = (getString(R.string.saladAddition)).split("\n");
        addition.setText(tempTxt[0]);
        final Button breadSlice = (Button)Cashier.dialog.findViewById(R.id.breadButton);
        tempTxt = (getString(R.string.bread)).split("\n");
        breadSlice.setText(tempTxt[0]);

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
        int addsPicked =  Integer.parseInt(Cashier.checkPrefs.getString(NUM_SALAD_ADDS,0+""));
        NumberPicker picker = (NumberPicker)Cashier.dialog.findViewById(R.id.studentSaladNumPick);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setMinValue(0);
        picker.setMaxValue(6);
        picker.setWrapSelectorWheel(false);
        picker.setValue(addsPicked);
        salad[IND_SALAD_ADDS] = addsPicked+"";
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Log.d("TKT_studentOrder","picker.onValueChangeListener=============");
                salad[IND_SALAD_ADDS] = newVal+"";
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
                }
                else
                {
                    //Cashier.sharedUpdateSaladBread(0);
                    salad[IND_SALAD_BREAD] = NO_BREAD;
                    breadSlice.setBackground(getDrawable(R.drawable.shape));
                }
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            //// TODO: 10/29/2017 clear data and try again! 
            @Override
            public void onClick(View v) {
                orderDetails.put(ORDER_SALAD,SALAD+":\n"+
                        ORDER_STRING_SALAD_ADDS + salad[IND_SALAD_ADDS]+"\n"+
                        ORDER_STRING_SALAD_BREAD + salad[IND_SALAD_BREAD]);
                Cashier.sharedUpdateSaladAdds(salad[IND_SALAD_ADDS]);
                Cashier.sharedUpdateSaladBread(salad[IND_SALAD_BREAD]);
                Log.d("TKT_studentOrder","orderDetail: "+orderDetails.get(ORDER_SALAD));
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
        Log.d("TKT_studentOrder","hots");
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

        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                hots[HOT_QUAN] = newVal+"";
            }

        });

        final Button milanesa = (Button)Cashier.dialog.findViewById(R.id.milanesaButton);
        final Button kuskus = (Button)Cashier.dialog.findViewById(R.id.kuskusButton);
        final Button pasta = (Button)Cashier.dialog.findViewById(R.id.pastaButton);
        String [] tempTxt = (getString(R.string.corn_milanesa)).split("\n");
        milanesa.setText(tempTxt[0]);
        tempTxt = (getString(R.string.kuskus)).split("\n");
        kuskus.setText(tempTxt[0]);
        tempTxt = (getString(R.string.pasta)).split("\n");
        pasta.setText(tempTxt[0]);

        String hotChosenPrev = Cashier.checkPrefs.getString(CHOSEN_HOT,null);
        if(hotChosenPrev != null)
            buttonHandler(hotChosenPrev,openDialogFlag);


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
                    orderDetails.put(ORDER_HOTS, hots[HOT_QUAN] + " " + hots[HOT_ITEM]);
                    Log.d("TKT_studentOrder", "orderDetails: " + orderDetails.get(ORDER_HOTS));
                    hots[HOT_ITEM] = null;
                    hots[HOT_QUAN] = 1 + "";
                    orderDetailHandler(ORDER_HOTS, hotsTxtView, hotParent);
                    Cashier.dialog.dismiss();
                }
            }
        });
        Cashier.dialog.show();
    }

    public void chooseHot(Button hot)
    {
        hots[HOT_ITEM] = hot.getText().toString();
        String prevHot = Cashier.checkPrefs.getString(CHOSEN_HOT,null);
        if(prevHot != null)
        {
            Log.d("TKT_studentOrder","prevHot: "+prevHot);
            buttonHandler(prevHot, hot.getTag().toString());
        }
        Cashier.sharedUpdateHot(hot.getTag().toString());
        hot.setBackground(getDrawable(R.drawable.shape_gray));
    }

    public void fruitMeusli(View v)
    {
        Log.d("TKT_studentOrder","fruitMeusli");
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.student_meusli_dialog_layout);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button finish = (Button)Cashier.dialog.findViewById(R.id.check);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.cancelCheck);
        final Button meusli = (Button)Cashier.dialog.findViewById(R.id.meusliButton);
        final Button fruit = (Button)Cashier.dialog.findViewById(R.id.watermelonButton);
        String [] tempTxt = (getString(R.string.fruitMuesli)).split("\n");
        meusli.setText(tempTxt[0]);
        tempTxt = (getString(R.string.watermelon)).split("\n");
        fruit.setText(tempTxt[0]);

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

        /*
        String dessertPrev = Cashier.checkPrefs.getString(CHOSEN_DESSERT,null);
        if(dessertPrev != null)
        {
            buttonHandler(dessertPrev,openDialogFlag);
        }
        */

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
                    orderDetails.put(ORDER_DESSERT, dessert[MEU_QUAN] + " " + dessert[MEU_ITEM]);
                    Log.d("TKT_studentPrder", "orderDessert: " + orderDetails.get(ORDER_DESSERT));
                    dessert[MEU_ITEM] = null;
                    dessert[MEU_QUAN] = 1+"";
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
        dessert[MEU_ITEM] = dess.getText().toString();
        String prevDes = Cashier.checkPrefs.getString(CHOSEN_DESSERT,null);
        if(prevDes != null)
        {
            buttonHandler(prevDes, dess.getTag().toString());
        }
        Cashier.sharedUpdateDessert(dess.getTag().toString());
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
        super.onResume();
    }

    public void order(View v)
    {
        Log.d("TKT_studentOrder","order=================");
        //// TODO: 10/30/2017 iterate through orderDetails, collect the none nulls and generate message
        Log.d("TKT_studentOrder","orderDetails.SAND: "+orderDetails.get(ORDER_SAND));
        Log.d("TKT_studentOrder","orderDetails.NOTES: "+orderDetails.get(ORDER_NOTES));
        Log.d("TKT_studentOrder","orderDetails.NAME: "+orderDetails.get(ORDER_NAME));
        Log.d("TKT_studentOrder","orderDetails.DESSERT: "+orderDetails.get(ORDER_DESSERT));
        Log.d("TKT_studentOrder","orderDetails.SALAD: "+orderDetails.get(ORDER_SALAD));
        Log.d("TKT_studentOrder","orderDetails.HOTS: "+orderDetails.get(ORDER_HOTS));
    }

    public String generateMessage()
    {
        //// TODO: 10/30/2017 generate message and return to order
        String message = "";

        return message;
    }

}
