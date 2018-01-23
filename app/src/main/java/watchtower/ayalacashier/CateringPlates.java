package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Map;

public class CateringPlates extends AppCompatActivity {

    static Context context;
    static int pickedNum = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_plates);
        Log.d("TKT_cateringPlates","onCreate=================");
        context = this;


        //initButtons();
    }


    /*
    public void initButtons()
    {
        Button eggSaladButton = (Button)findViewById(R.id.eggSalad);
        Button eggplantSaladButton = (Button)findViewById(R.id.eggplantSalad);
        Button thiniSaladButton = (Button)findViewById(R.id.greedThiniSalad);
        Button avocadoSaladButton = (Button)findViewById(R.id.avocadoSalad);

        if(Cashier.cateringOrder.containsKey(eggSaladButton.getText())) {
            eggSaladButton.setBackground(getDrawable(R.drawable.circle_gray));
            eggSaladButton.setTag(R.string.TAGchecked);
        }
        if(Cashier.cateringOrder.containsKey(eggplantSaladButton.getText())) {
            eggplantSaladButton.setBackground(getDrawable(R.drawable.circle_gray));
            eggplantSaladButton.setTag(R.string.TAGchecked);
        }
        if(Cashier.cateringOrder.containsKey(thiniSaladButton.getText())) {
            thiniSaladButton.setBackground(getDrawable(R.drawable.circle_gray));
            thiniSaladButton.setTag(R.string.TAGchecked);
        }
        if(Cashier.cateringOrder.containsKey(avocadoSaladButton.getText())) {
            avocadoSaladButton.setBackground(getDrawable(R.drawable.circle_gray));
            avocadoSaladButton.setTag(R.string.TAGchecked);
        }
    }
    */

    public static String getTortillaEntry()
    {
        Log.d("TKT_CateringPlates","initTortillaCheckbox================");
        for(Map.Entry<String, CateringObjectInfo> entry : Cashier.cateringOrder.entrySet())
        {
            if(entry.getKey().contains(context.getString(R.string.tortillaFilledWith)))
            {
                Log.d("TKT_CateringPlates","initTortila.temp found! : "+entry.getKey());
                return entry.getKey();
            }
        }

        return null;


    }

    public void openTortillas(View v)
    {
        //final String s1 = getString(R.string.hugeSaladCatering1);
        //final String s2 = getString(R.string.hugeSaladCatering2);
        //final String s3 = getString(R.string.hugeSaladCatering3);
        //final String s4 = getString(R.string.hugeSaladCatering4);
        Log.d("TKT_CateringPlates","openTortillas================");
        final String [] tortillaFilling = {getString(R.string.tortillaFilling1), getString(R.string.tortillaFilling2),getString(R.string.tortillaFilling3)};
        final boolean [] isCheckedArr = new boolean[3];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.TORTILLA];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_tortilla);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);


        //this is stored in an array (different from the other functions because the format of tortilla string is different
        final TextView [] fillings = {(TextView)Cashier.dialog.findViewById(R.id.tortillaFilling1Txt),
                (TextView)Cashier.dialog.findViewById(R.id.tortillaFilling2Txt),
                (TextView)Cashier.dialog.findViewById(R.id.tortillaFilling3Txt)};

        String tortillaFromOrder = getTortillaEntry();

        if(tortillaFromOrder != null) {
            for (int i = 0; i < tortillaFilling.length; i++) {
                if (tortillaFromOrder.contains(tortillaFilling[i])) {
                    Catering.setBackgroundCateringTxt(fillings[i],context,isCheckedArr,i);
                }

            }
        }
        fillings[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(fillings[0],context,isCheckedArr,0);
            }
        });
        fillings[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(fillings[1],context,isCheckedArr,1);
            }
        });
        fillings[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(fillings[2],context,isCheckedArr,2);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(tortillaFilling,isCheckedArr, Catering.TORTILLA_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void openWhiteEggplant(View v)
    {
        final String [] stringWhiteEggplant = {getString(R.string.whiteEggplantCateringIngre)};
        final boolean [] isCheckedArr = new boolean[1];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.WHITE_EGGPLANT];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_white_eggplant);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
       final TextView whiteEggplantTxt = (TextView)Cashier.dialog.findViewById(R.id.whiteEggplanttxt);


        if(Cashier.cateringOrder.containsKey(stringWhiteEggplant[0]))
            Catering.setBackgroundCateringTxt(whiteEggplantTxt,context,isCheckedArr,0);

        whiteEggplantTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(whiteEggplantTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(stringWhiteEggplant,isCheckedArr, Catering.NO_FLAG, Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();
    }

    public void openHashBrowns(View v)
    {
        Log.d("TKT_catering","hashBrownDialog================");
        final String [] hashbrownType = {getString(R.string.quinoaHashBrownsCateringIngre),getString(R.string.lentilHashBrownsCateringIngre)};
        final boolean [] isCheckedArr = new boolean[2];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.HASHBROWNS];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_hashbrowns);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final TextView quinoaTxt = (TextView)Cashier.dialog.findViewById(R.id.quinoaHashTxt);
        final TextView lentilTxt = (TextView)Cashier.dialog.findViewById(R.id.lentilHashTxt);

        if(Cashier.cateringOrder.containsKey(hashbrownType[0]))
            Catering.setBackgroundCateringTxt(quinoaTxt,context,isCheckedArr,0);

        if(Cashier.cateringOrder.containsKey(hashbrownType[1]))
            Catering.setBackgroundCateringTxt(lentilTxt,context,isCheckedArr,1);





        quinoaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(quinoaTxt,context,isCheckedArr,0);
            }
        });
        lentilTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(lentilTxt,context,isCheckedArr,1);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(hashbrownType, isCheckedArr,Catering.NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();

    }

    public void openAntipasti(View v)
    {
        final String [] antipastiString = {getString(R.string.antipastiCateringIngre)};
        final boolean [] isCheckedArr = new boolean[1];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.ANTIPASTI];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_antipasti);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final TextView antipastiTxt = (TextView)Cashier.dialog.findViewById(R.id.antipastitxt);


        if(Cashier.cateringOrder.containsKey(antipastiString[0]))
            Catering.setBackgroundCateringTxt(antipastiTxt,context,isCheckedArr,0);


        antipastiTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(antipastiTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(antipastiString,isCheckedArr, Catering.NO_FLAG, Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();
    }

    public void openCheesePlate(View v)
    {
        final String [] cheeseString = {getString(R.string.cheesePlateCateringIngre)};
        final boolean [] isCheckedArr = new boolean[1];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.CHEESE_PLATE];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_cheese_plate);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final TextView cheeseTxt = (TextView)Cashier.dialog.findViewById(R.id.cheesePlatetxt);


        if(Cashier.cateringOrder.containsKey(cheeseString[0]))
            Catering.setBackgroundCateringTxt(cheeseTxt,context,isCheckedArr,0);


        cheeseTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(cheeseTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(cheeseString,isCheckedArr, Catering.NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();
    }

    public void openSand(View v)
    {
        final String [] sandString = {getString(R.string.sandPlateCateringIngre)};
        final boolean [] isCheckedArr = new boolean[1];

        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.SAND_PLATE];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_cheese_plate);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final TextView cheeseTxt = (TextView)Cashier.dialog.findViewById(R.id.cheesePlatetxt);
        NumberPicker numberPicker = (NumberPicker)Cashier.dialog.findViewById(R.id.sandPlatePickAmount);
        numberPicker.setMinValue(10);
        numberPicker.setMinValue(100);
        if(Cashier.cateringOrder.containsKey(sandString[0]))
            Catering.setBackgroundCateringTxt(cheeseTxt,context,isCheckedArr,0);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pickedNum = newVal;
            }
        });

        cheeseTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(cheeseTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(sandString,isCheckedArr, Catering.NO_FLAG, pickedNum+"");
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();
    }

    public void openVeggie(View v)
    {
        final String [] veggieString = {getString(R.string.veggiePlateCateringIngre)};
        final boolean [] isCheckedArr = new boolean[1];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.VEGGIE_PLATE];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_veggie_plate);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        //final CheckBox veggie = (CheckBox)Cashier.dialog.findViewById(R.id.veggiePlateCheckBox);

        final TextView veggieTxt = (TextView)Cashier.dialog.findViewById(R.id.veggiePlatetxt);

        if(Cashier.cateringOrder.containsKey(veggieString[0]))
        {
            Catering.setBackgroundCateringTxt(veggieTxt,context,isCheckedArr,0);
        }

        veggieTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Catering.textPressHandler(veggie);
                Catering.setBackgroundCateringTxt(veggieTxt,context,isCheckedArr,0);

            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(veggieString,isCheckedArr, Catering.NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();
    }

    public void openSmokedFish(View v)
    {
        final String [] fishString = {getString(R.string.smokedFishPlateCatering)};
        final boolean [] isCheckedArr = new boolean[1];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.SMOKED_FISH_PLATE];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_smoked_fish);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final TextView cheeseTxt = (TextView)Cashier.dialog.findViewById(R.id.smokedFishtxt);


        if(Cashier.cateringOrder.containsKey(fishString[0]))
            Catering.setBackgroundCateringTxt(cheeseTxt,context,isCheckedArr,0);


        cheeseTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(cheeseTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(fishString,isCheckedArr, Catering.NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();
    }
    /*
    public void openLentilSalad(View v)
    {
        //final String stringLentil = getString(R.string.lentilSaladCateringIngre);
        final String [] stringLentil = {getString(R.string.lentilSaladCateringIngre)};
        final boolean [] isCheckedArr = new boolean[1];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.SALAD_LENTIL];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_lentil_salad);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final CheckBox lentil = (CheckBox)Cashier.dialog.findViewById(R.id.lentilSaladCheckBox);

        if(Cashier.cateringOrder.containsKey(stringLentil[0]))
            lentil.setChecked(true);
       TextView lentilTxt = (TextView)Cashier.dialog.findViewById(R.id.lentilSalad1txt);
        //LinearLayout lentilField = (LinearLayout)Cashier.dialog.findViewById(R.id.lentilLayout);
        lentilTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.textPressHandler(lentil);
            }
        });

        lentil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //checkHandler(stringLentil, isChecked);
                isCheckedArr[0] = isChecked;
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(stringLentil,isCheckedArr);
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();
    }

    public void openQuinoaSalad(View v)
    {
        //final String stringQuinoa = getString(R.string.quinoaSaladCateringIngre);
        final String [] stringQuinoa = {getString(R.string.quinoaSaladCateringIngre)};
        final boolean [] isCheckedArr = new boolean[1];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.SALAD_QUINOA];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_quinoa_salad);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final CheckBox quinoa = (CheckBox)Cashier.dialog.findViewById(R.id.quinoaSaladCheckBox);

        if(Cashier.cateringOrder.containsKey(stringQuinoa[0]))
            quinoa.setChecked(true);
        TextView quinoaTxt = (TextView)Cashier.dialog.findViewById(R.id.quinoaSaladtxt);

        quinoaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.textPressHandler(quinoa);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(stringQuinoa,isCheckedArr);
                Cashier.dialog.dismiss();
            }
        });

        quinoa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //checkHandler(stringQuinoa, isChecked);
                isCheckedArr[0] = isChecked;
            }
        });

        Cashier.dialog.show();

    }




    public void openEggSalad(View v)
    {
        //PRICE = Cashier.CATERING_PRICES[4];
        Cashier.cateringButtonBackgroundChange(v,this,Cashier.CATERING_PRICES[Cashier.SALAD_EGG]);
    }

    public void openEggplantSalad(View v)
    {
        //PRICE = Cashier.CATERING_PRICES[5];
        Cashier.cateringButtonBackgroundChange(v, this, Cashier.CATERING_PRICES[Cashier.SALAD_EGGPLANT]);
    }

    public void openThiniSalad(View v)
    {
        //PRICE = Cashier.CATERING_PRICES[6];
        Cashier.cateringButtonBackgroundChange(v, this, Cashier.CATERING_PRICES[Cashier.SALAD_THINI]);
    }

    public void openAvocadoSalad(View v)
    {
        //PRICE = Cashier.CATERING_PRICES[7];
        Cashier.cateringButtonBackgroundChange(v, this, Cashier.CATERING_PRICES[7]);
    }
    */




}
