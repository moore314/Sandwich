package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
        final CheckBox mushrooms = (CheckBox)Cashier.dialog.findViewById(R.id.mushroomQuicheBox);
        final CheckBox yam = (CheckBox)Cashier.dialog.findViewById(R.id.yamQuicheBox);
        final CheckBox broccoli = (CheckBox)Cashier.dialog.findViewById(R.id.broccoliQuicheBox);
        final CheckBox onion = (CheckBox)Cashier.dialog.findViewById(R.id.onionQuicheBox);

        if(Cashier.cateringOrder.containsKey(quicheType[0]))
            mushrooms.setChecked(true);
        if(Cashier.cateringOrder.containsKey(quicheType[1]))
            yam.setChecked(true);
        if(Cashier.cateringOrder.containsKey(quicheType[2]))
            broccoli.setChecked(true);
        if(Cashier.cateringOrder.containsKey(quicheType[3]))
            onion.setChecked(true);

        TextView mushroomTxt = (TextView)Cashier.dialog.findViewById(R.id.mushroomQuicheTxt);
        TextView yamTxt = (TextView)Cashier.dialog.findViewById(R.id.yamQuicheTxt);
        TextView broccoliTxt = (TextView)Cashier.dialog.findViewById(R.id.broccoliQuicheTxt);
        TextView onionTxt = (TextView)Cashier.dialog.findViewById(R.id.onionQuicheTxt);

        mushroomTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPressHandler(mushrooms);
            }
        });
        yamTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPressHandler(yam);
            }
        });
        broccoliTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPressHandler(broccoli);
            }
        });
        onionTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPressHandler(onion);
            }
        });

        //======checkboxes
        mushrooms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //checkHandler(s1, isChecked);
                isCheckedArr[0] = isChecked;
            }
        });
        yam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //checkHandler(s2, isChecked);
                isCheckedArr[1] = isChecked;
            }
        });
        broccoli.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkHandler(s3, isChecked);
                isCheckedArr[2] = isChecked;
            }
        });
        onion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //checkHandler(s4, isChecked);
                isCheckedArr[3] = isChecked;
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(quicheType, isCheckedArr,NO_FLAG);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();

    }

    public static void textPressHandler(CheckBox check)
    {
        Log.d("TKT_catering","textPressHandler================");
        if(check.isChecked())
            check.setChecked(false);
        else
            check.setChecked(true);
        //checkHandler(saladType, check.isChecked());
    }

    public static void checkHandler(String [] itemArr, boolean [] isChecked, String flag)
    {
        Log.d("TKT_catering","checkHandler==============");
        CateringObjectInfo c = new CateringObjectInfo(Catering.PRICE, "1");
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
