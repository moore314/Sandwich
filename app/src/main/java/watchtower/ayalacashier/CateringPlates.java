package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Map;

public class CateringPlates extends AppCompatActivity {

    static Context context;


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
        //final CheckBox filling1 = (CheckBox)Cashier.dialog.findViewById(R.id.tortillaFilling1Box);
        //final CheckBox filling2 = (CheckBox)Cashier.dialog.findViewById(R.id.tortillaFilling2Box);
        //final CheckBox filling3 = (CheckBox)Cashier.dialog.findViewById(R.id.tortillaFilling3Box);

        final CheckBox [] fillings = {(CheckBox)Cashier.dialog.findViewById(R.id.tortillaFilling1Box),
                (CheckBox)Cashier.dialog.findViewById(R.id.tortillaFilling2Box),
                (CheckBox)Cashier.dialog.findViewById(R.id.tortillaFilling3Box)};

        String tortillaFromOrder = getTortillaEntry();

        if(tortillaFromOrder != null) {
            for (int i = 0; i < tortillaFilling.length; i++) {
                if (tortillaFromOrder.contains(tortillaFilling[i])) {
                    fillings[i].setChecked(true);
                    isCheckedArr[i] = true;
                }

            }
        }


        /*
        if(Cashier.cateringOrder.containsKey(tortillaFilling[0]))
            filling1.setChecked(true);
        if(Cashier.cateringOrder.containsKey(tortillaFilling[1]))
            filling2.setChecked(true);
        if(Cashier.cateringOrder.containsKey(tortillaFilling[2]))
            filling3.setChecked(true);
        */


        TextView filling1Txt = (TextView)Cashier.dialog.findViewById(R.id.tortillaFilling1Txt);
        TextView filling2Txt = (TextView)Cashier.dialog.findViewById(R.id.tortillaFilling2Txt);
        TextView filling3Txt = (TextView)Cashier.dialog.findViewById(R.id.tortillaFilling3Txt);

        filling1Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.textPressHandler(fillings[0]);
            }
        });
        filling2Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.textPressHandler(fillings[1]);
            }
        });
        filling3Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.textPressHandler(fillings[2]);
            }
        });


        fillings[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //checkHandler(s1, isChecked);
                isCheckedArr[0] = isChecked;
            }
        });
        fillings[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //checkHandler(s2, isChecked);
                isCheckedArr[1] = isChecked;
            }
        });
        fillings[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               // checkHandler(s3, isChecked);
                isCheckedArr[2] = isChecked;
            }
        });



        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(tortillaFilling,isCheckedArr, Catering.TORTILLA_FLAG);
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

    public void openTunaSalad(View v)
    {
        //final String stringTuna = getString(R.string.tunaSaladCateringIngre);
        final String [] stringTuna = {getString(R.string.tunaSaladCateringIngre)};
        final boolean [] isCheckedArr = new boolean[1];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.SALAD_TUNA];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_tuna_salad);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final CheckBox tuna = (CheckBox)Cashier.dialog.findViewById(R.id.tunaSaladCheckBox);

        if(Cashier.cateringOrder.containsKey(stringTuna[0]))
            tuna.setChecked(true);
        TextView tunaTxt = (TextView)Cashier.dialog.findViewById(R.id.tunaSaladtxt);

        tunaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.textPressHandler(tuna);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(stringTuna,isCheckedArr);
                Cashier.dialog.dismiss();
            }
        });

        tuna.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //checkHandler(stringTuna, isChecked);
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
