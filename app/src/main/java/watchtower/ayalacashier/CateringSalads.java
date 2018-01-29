package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CateringSalads extends AppCompatActivity {

    static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_salads);
        context = this;
        Catering.children = false;
        initButtons();
    }

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

    public void openHugeSalad(View v)
    {
        //final String s1 = getString(R.string.hugeSaladCatering1);
        //final String s2 = getString(R.string.hugeSaladCatering2);
        //final String s3 = getString(R.string.hugeSaladCatering3);
        //final String s4 = getString(R.string.hugeSaladCatering4);
        Log.d("TKT_CateringSalads","openHugeSalad================");
        final String [] saladType = {getString(R.string.hugeSaladCatering1), getString(R.string.hugeSaladCatering2),getString(R.string.hugeSaladCatering3),getString(R.string.hugeSaladCatering4)};
        final boolean [] isCheckedArr = new boolean[4];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.SALAD_HUGE];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Cashier.dialog.setContentView(R.layout.dialog_catering_huge_salad);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final TextView salad1txt = (TextView)Cashier.dialog.findViewById(R.id.hugeSalad1txt);
        final TextView salad2txt = (TextView)Cashier.dialog.findViewById(R.id.hugeSalad2txt);
        final TextView salad3txt = (TextView)Cashier.dialog.findViewById(R.id.hugeSalad3txt);
        final TextView salad4txt = (TextView)Cashier.dialog.findViewById(R.id.hugeSalad4txt);

        if(Cashier.cateringOrder.containsKey(saladType[0])) {
           Catering.setBackgroundCateringTxt(salad1txt, context,isCheckedArr, 0);
        }
        if(Cashier.cateringOrder.containsKey(saladType[1])) {
            Catering.setBackgroundCateringTxt(salad2txt, context,isCheckedArr,1 );            //isCheckedArr[1] = true;
        }
        if(Cashier.cateringOrder.containsKey(saladType[2])) {
            Catering.setBackgroundCateringTxt(salad3txt, context,isCheckedArr,2 );            //isCheckedArr[2] = true;
        }
        if(Cashier.cateringOrder.containsKey(saladType[3])) {
            Catering.setBackgroundCateringTxt(salad4txt, context,isCheckedArr,3 );            //isCheckedArr[3] = true;
        }



        salad1txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(salad1txt, context,isCheckedArr,0 );            }
        });
        salad2txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(salad2txt, context,isCheckedArr,1 );             }
        });
        salad3txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(salad3txt, context,isCheckedArr,2 );            //isCheckedArr[2] = true;
            }
        });
        salad4txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(salad4txt, context,isCheckedArr,3 );            //isCheckedArr[3] = true;
            }
        });


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(saladType,isCheckedArr, Catering.NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void openLentilSalad(View v)
    {
        //final String stringLentil = getString(R.string.lentilSaladCateringIngre);
        Log.d("TKT_CateringSalads","openLentilSalad================");
        final String [] stringLentil = {getString(R.string.lentilSaladCateringIngre)};
        final boolean [] isCheckedArr = new boolean[1];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.SALAD_LENTIL];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Cashier.dialog.setContentView(R.layout.dialog_catering_lentil_salad);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final TextView lentilTxt = (TextView)Cashier.dialog.findViewById(R.id.lentilSalad1txt);

        if(Cashier.cateringOrder.containsKey(stringLentil[0])) {
            Catering.setBackgroundCateringTxt(lentilTxt,context,isCheckedArr,0);        }
        lentilTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(lentilTxt,context,isCheckedArr,0);
            }
        });


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(stringLentil,isCheckedArr, Catering.NO_FLAG,Cashier.ONE);
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
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Cashier.dialog.setContentView(R.layout.dialog_catering_quinoa_salad);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final TextView quinoaTxt = (TextView)Cashier.dialog.findViewById(R.id.quinoaSaladtxt);


        if(Cashier.cateringOrder.containsKey(stringQuinoa[0]))
            Catering.setBackgroundCateringTxt(quinoaTxt,context,isCheckedArr,0);

        quinoaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(quinoaTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(stringQuinoa,isCheckedArr, Catering.NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();

    }

    public void openTunaSalad(View v)
    {
        //final String stringTuna = getString(R.string.tunaSaladCateringIngre);
        Log.d("TKT_CateringSalads","openTunaSalad================");
        final String [] stringTuna = {getString(R.string.tunaSaladCateringIngre)};
        final boolean [] isCheckedArr = new boolean[1];
        Catering.PRICE = Cashier.CATERING_PRICES[Cashier.SALAD_TUNA];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Cashier.dialog.setContentView(R.layout.dialog_catering_tuna_salad);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
       final  TextView tunaTxt = (TextView)Cashier.dialog.findViewById(R.id.tunaSaladtxt);

        if(Cashier.cateringOrder.containsKey(stringTuna[0]))
            Catering.setBackgroundCateringTxt(tunaTxt,context,isCheckedArr,0);

        tunaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(tunaTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.checkHandler(stringTuna,isCheckedArr, Catering.NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });
        Cashier.dialog.show();
    }

    public void openEggSalad(View v)
    {
        //PRICE = Cashier.CATERING_PRICES[4];
        Log.d("TKT_CateringSalads","openEggSalad================");
        Cashier.cateringButtonBackgroundChange(v,this,Cashier.CATERING_PRICES[Cashier.SALAD_EGG]);
    }

    public void openEggplantSalad(View v)
    {
        //PRICE = Cashier.CATERING_PRICES[5];
        Log.d("TKT_CateringSalads","openEggplantSalad================");
        Cashier.cateringButtonBackgroundChange(v, this, Cashier.CATERING_PRICES[Cashier.SALAD_EGGPLANT]);
    }

    public void openThiniSalad(View v)
    {
        //PRICE = Cashier.CATERING_PRICES[6];
        Log.d("TKT_CateringSalads","openThiniSalad================");
        Cashier.cateringButtonBackgroundChange(v, this, Cashier.CATERING_PRICES[Cashier.SALAD_THINI]);
    }

    public void openAvocadoSalad(View v)
    {
        //PRICE = Cashier.CATERING_PRICES[7];
        Log.d("TKT_CateringSalads","openAvocadoSalad================");
        Cashier.cateringButtonBackgroundChange(v, this, Cashier.CATERING_PRICES[7]);
    }




}
