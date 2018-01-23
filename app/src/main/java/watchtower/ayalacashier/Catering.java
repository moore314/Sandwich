package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Catering extends AppCompatActivity {
    //// TODO: 11/27/2017 set on destroy and put order details in shared 

    static Context context;
    public static String PRICE = "0.0";
    public static String NO_FLAG = "noFlag";
    public static String TORTILLA_FLAG = "tortillaFlag";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering);
        context = this;
        Log.d("TKT_catering","onCreate================");
        Button salad = (Button)findViewById(R.id.saladCatering);
        final Button quiche = (Button)findViewById(R.id.quicheCatering);
        Button plates = (Button)findViewById(R.id.platesCatering);
        Button pasta = (Button)findViewById(R.id.pastaCatering);
        Button soup = (Button)findViewById(R.id.soupCatering);
        Button foucachas = (Button)findViewById(R.id.foucachaCatering);
        Button bread = (Button)findViewById(R.id.breadCatering);
        Button desserts = (Button)findViewById(R.id.dessertCatering);

        salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CateringSalads.class);
                startActivity(intent);

            }
        });

        quiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quicheDialog();
            }
        });

        plates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CateringPlates.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.catering_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if(id == R.id.facebook)
        {
            //open facebook
            Log.d("TKT_catering","opening facebook");
            return true;
        }
        if(id == R.id.cart)
        {
            //open cart
            Log.d("TKT_catering","opening cart");
            Intent intent = new Intent(this, Cart.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    public void quicheDialog()
    {
        Log.d("TKT_catering","quicheDialog================");
        final String [] quicheType = {getString(R.string.mushroomQuicheCateringIngre), getString(R.string.yamQuicheCateringIngre),getString(R.string.broccoliQuicheCateringIngre),getString(R.string.onionQuicheCateringIngre)};
        final boolean [] isCheckedArr = new boolean[4];
        PRICE = Cashier.CATERING_PRICES[Cashier.QUICHE];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_quiche);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);

        final TextView mushroomTxt = (TextView)Cashier.dialog.findViewById(R.id.mushroomQuicheTxt);
        final TextView yamTxt = (TextView)Cashier.dialog.findViewById(R.id.yamQuicheTxt);
        final TextView broccoliTxt = (TextView)Cashier.dialog.findViewById(R.id.broccoliQuicheTxt);
        final TextView onionTxt = (TextView)Cashier.dialog.findViewById(R.id.onionQuicheTxt);

        if(Cashier.cateringOrder.containsKey(quicheType[0]))
            Catering.setBackgroundCateringTxt(mushroomTxt,context,isCheckedArr,0);

        if(Cashier.cateringOrder.containsKey(quicheType[1]))
            Catering.setBackgroundCateringTxt(yamTxt,context,isCheckedArr,1);

        if(Cashier.cateringOrder.containsKey(quicheType[2]))
            Catering.setBackgroundCateringTxt(broccoliTxt,context,isCheckedArr,2);

        if(Cashier.cateringOrder.containsKey(quicheType[3]))
            Catering.setBackgroundCateringTxt(onionTxt,context,isCheckedArr,3);




        mushroomTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(mushroomTxt,context,isCheckedArr,0);
            }
        });
        yamTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(yamTxt,context,isCheckedArr,1);
            }
        });
        broccoliTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(broccoliTxt,context,isCheckedArr,2);
            }
        });
        onionTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(onionTxt,context,isCheckedArr,3);
            }
        });


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(quicheType, isCheckedArr,NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();

    }


    public static void setBackgroundCateringTxt(TextView txt, Context context, boolean [] isCheckedArr, int pos)
    {
        if(txt.getTag().toString().equals(context.getString(R.string.TAGunchecked))) {
            Log.d("TKT_cateringPlate","equals unchecked");
            isCheckedArr[pos] = true;
            txt.setTag(context.getString(R.string.TAGchecked));
            txt.setBackgroundColor(ContextCompat.getColor(context,R.color.darkGray));
            Log.d("TKT_cateringPlate","tag1: " + txt.getTag().toString());
            //v.setBackground(getDrawable(R.drawable.shape_contour));
        }
        else
        {
            Log.d("TKT_cateringPlate","equals checked");
            isCheckedArr[pos] = false;
            txt.setTag(context.getString(R.string.TAGunchecked));
            txt.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
            Log.d("TKT_cateringPlate","tag2: " + txt.getTag().toString());
            //v.setBackground(getDrawable(R.drawable.shape_trans_contour));
        }
    }

    public static void checkHandler(String [] itemArr, boolean [] isChecked, String flag, String initAmount)
    {
        Log.d("TKT_catering","checkHandler==============");
        CateringObjectInfo c = new CateringObjectInfo(Catering.PRICE, initAmount);
        int counter = 0;
        if(flag.equals(TORTILLA_FLAG))
        {//tortillas
            Log.d("TKT_catering","flag = tortilla");
            String tortillaString = context.getString(R.string.tortillaFilledWith);
            for (int i = 0; i < itemArr.length; i++)
            {
                if (isChecked[i]) {
                    Log.d("TKT_catering","itemArr["+i+"] is checked");
                    tortillaString += "\n- " + itemArr[i];
                    counter++;
                }
            }


            if(!tortillaString.equals(context.getString(R.string.tortillaFilledWith)))
            {
                String  prevEntry = CateringPlates.getTortillaEntry();
                if(prevEntry != null)
                {
                    Log.d("TKT_catering","remove prev tortilla entry: "+prevEntry);
                    Log.d("TKT_catering","new tortilla entry: "+tortillaString);
                    Cashier.cateringOrder.remove(prevEntry);
                }
                Cashier.cateringOrder.put(tortillaString, c);
            }
        }
        else {
            Log.d("TKT_catering","flag = noFlag");
            for (int i = 0; i < itemArr.length; i++) {
                if (isChecked[i])
                    Cashier.cateringOrder.put(itemArr[i], c);
            }
        }
    }

}
