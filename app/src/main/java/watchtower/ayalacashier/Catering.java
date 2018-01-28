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
import android.widget.LinearLayout;
import android.widget.TextView;

public class Catering extends AppCompatActivity {
    //// TODO: 11/27/2017 set on destroy and put order details in shared 

    static Context context;
    public static String PRICE = "0.0";
    public static String NO_FLAG = "noFlag";
    public static String TORTILLA_FLAG = "tortillaFlag";
    public static boolean children = false;



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
        Button children = (Button)findViewById(R.id.childrenCatering);
        Button delivery = (Button)findViewById(R.id.deliveryCatering);

        if(Cashier.cateringOrder.containsKey(getString(R.string.deliveryCatering)))
            delivery.setBackground(getDrawable(R.drawable.circle_gray));

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

        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pastaDialog();
            }
        });

        soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soupDialog();
            }
        });
        
        foucachas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foucachasDialog();
            }
        });

        bread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breadBasketDialog();
            }
        });

        desserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dessertDialog();
            }
        });

        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childrenDialog();
            }
        });

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDelivery(v);
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
            Cashier.openFacebook(context);
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
        children = false;
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

    public void pastaDialog()
    {
        Log.d("TKT_catering","pastaDialog================");
        children = false;
        final String [] pastaString = {getString(R.string.pastaType1Catering),getString(R.string.pastaType2Catering)};
        final boolean [] isCheckedArr = new boolean[2];
        PRICE = Cashier.CATERING_PRICES[Cashier.PASTA];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_pasta);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);

        final TextView creamNmushs = (TextView)Cashier.dialog.findViewById(R.id.creamNmushTxt);
        final TextView pomodoroTxt = (TextView)Cashier.dialog.findViewById(R.id.pomodoroTxt);

        if(Cashier.cateringOrder.containsKey(pastaString[0]))
            Catering.setBackgroundCateringTxt(creamNmushs,context,isCheckedArr,0);

        if(Cashier.cateringOrder.containsKey(pastaString[1]))
            Catering.setBackgroundCateringTxt(pomodoroTxt,context,isCheckedArr,1);



        creamNmushs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(creamNmushs,context,isCheckedArr,0);
            }
        });
        pomodoroTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(pomodoroTxt,context,isCheckedArr,1);
            }
        });


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(pastaString, isCheckedArr,NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void soupDialog()
    {
        Log.d("TKT_catering","soupDialog================");
        children = false;
        final String [] soupType = {getString(R.string.orangeSoupCatering),getString(R.string.onionSoupCatering),getString(R.string.lentilSoupCatering)};
        final boolean [] isCheckedArr = new boolean[3];
        PRICE = Cashier.CATERING_PRICES[Cashier.SOUP];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_soup);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);

        final TextView orangeSoupTxt = (TextView)Cashier.dialog.findViewById(R.id.orangeSoupTxt);
        final TextView onionSoupTxt = (TextView)Cashier.dialog.findViewById(R.id.onionSoupTxt);
        final TextView lentilSoupTxt = (TextView)Cashier.dialog.findViewById(R.id.lentilSoupTxt);

        if(Cashier.cateringOrder.containsKey(soupType[0]))
            Catering.setBackgroundCateringTxt(orangeSoupTxt,context,isCheckedArr,0);

        if(Cashier.cateringOrder.containsKey(soupType[1]))
            Catering.setBackgroundCateringTxt(onionSoupTxt,context,isCheckedArr,1);

        if(Cashier.cateringOrder.containsKey(soupType[2]))
            Catering.setBackgroundCateringTxt(lentilSoupTxt,context,isCheckedArr,2);





        orangeSoupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(orangeSoupTxt,context,isCheckedArr,0);
            }
        });
        onionSoupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(onionSoupTxt,context,isCheckedArr,1);
            }
        });
        lentilSoupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(lentilSoupTxt,context,isCheckedArr,2);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(soupType, isCheckedArr,NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void foucachasDialog()
    {
        Log.d("TKT_catering","foucachasialog================");
        children = false;
        final String [] foucachaString = {getString(R.string.foucachasCateringIng)};
        final boolean [] isCheckedArr = new boolean[1];
        PRICE = Cashier.CATERING_PRICES[Cashier.FOUCACHAS];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_foucachas);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);

        final TextView foucachaTxt = (TextView)Cashier.dialog.findViewById(R.id.foucachatxt);

        if(Cashier.cateringOrder.containsKey(foucachaString[0]))
            Catering.setBackgroundCateringTxt(foucachaTxt,context,isCheckedArr,0);

        foucachaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(foucachaTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(foucachaString, isCheckedArr,NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void breadBasketDialog()
    {
        //// TODO: 1/25/2018 consider change color of items not included in check 
        Log.d("TKT_catering","breadBasketDialog================");
        children = false;
        final String [] breadBasketString = {getString(R.string.breadBasketCateringIng)};
        final boolean [] isCheckedArr = new boolean[1];
        PRICE = Cashier.CATERING_PRICES[Cashier.BREAD_BASKET];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_bread_basket);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);

        final TextView breadBasketTxt = (TextView)Cashier.dialog.findViewById(R.id.breadBaskettxt);

        if(Cashier.cateringOrder.containsKey(breadBasketString[0]))
            Catering.setBackgroundCateringTxt(breadBasketTxt,context,isCheckedArr,0);

        breadBasketTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(breadBasketTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(breadBasketString, isCheckedArr,NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void dessertDialog()
    {
        //// TODO: 1/28/2018 consider change color of items not included in check
        Log.d("TKT_catering","dessertDialog================");
        children = false;
        final String [] dessertString = {getString(R.string.dessertCateringIng)};
        final boolean [] isCheckedArr = new boolean[1];
        PRICE = Cashier.CATERING_PRICES[Cashier.DESSERT_BASKET];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_desserts);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);

        final TextView dessertTxt = (TextView)Cashier.dialog.findViewById(R.id.desserttxt);

        if(Cashier.cateringOrder.containsKey(dessertString[0]))
            Catering.setBackgroundCateringTxt(dessertTxt,context,isCheckedArr,0);

        dessertTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(dessertTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(dessertString, isCheckedArr,NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void childrenDialog()
    {

        Log.d("TKT_catering","childrenDialog================");
        children = true;
        final String [] childrenString = {getString(R.string.veggiePlateCateringIngre),getString(R.string.pastaType2Catering)};
        final boolean [] isCheckedArr = new boolean[2];
        //PRICE = Cashier.CATERING_PRICES[Cashier.PASTA];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_children);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final LinearLayout veggieLayout = (LinearLayout)Cashier.dialog.findViewById(R.id.layoutChildrenVeggie);
        final LinearLayout pastaLayout = (LinearLayout)Cashier.dialog.findViewById(R.id.layoutChildrenPasta);

        final TextView veggieTxt = (TextView)Cashier.dialog.findViewById(R.id.veggiPlateChildrenTxt);
        final TextView pomodoroTxt = (TextView)Cashier.dialog.findViewById(R.id.pomodoroChildrenTxt);
        final TextView veggiePriceTxt = (TextView)Cashier.dialog.findViewById(R.id.veggiePriceTxt);
        final TextView pomodoroPriceTxt = (TextView)Cashier.dialog.findViewById(R.id.pastaPriceTxt);


        if(Cashier.cateringOrder.containsKey(childrenString[0]))
            setBackgroundChildren(veggieLayout,context,isCheckedArr,0);

        if(Cashier.cateringOrder.containsKey(childrenString[1]))
            setBackgroundChildren(pastaLayout,context,isCheckedArr,1);



        veggieTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundChildren(veggieLayout,context,isCheckedArr,0);
            }
        });
        pomodoroTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundChildren(pastaLayout,context,isCheckedArr,1);
            }
        });
        veggiePriceTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundChildren(veggieLayout,context,isCheckedArr,0);
            }
        });
        pomodoroPriceTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundChildren(pastaLayout,context,isCheckedArr,1);
            }
        });



        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(childrenString, isCheckedArr,NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void chooseDelivery(View v)
    {
        //PRICE = Cashier.CATERING_PRICES[6];
        Log.d("TKT_CateringSalads","openThiniSalad================");
        Cashier.cateringButtonBackgroundChange(v, this, Cashier.CATERING_PRICES[Cashier.DELIVERY]);
    }

    public void setBackgroundChildren(LinearLayout txt, Context context, boolean [] isCheckedArr, int pos)
    {
        if(txt.getTag().toString().equals(context.getString(R.string.TAGunchecked))) {
            Log.d("TKT_cateringPlate","equals unchecked");
            isCheckedArr[pos] = true;
            txt.setTag(context.getString(R.string.TAGchecked));
            txt.setBackgroundColor(ContextCompat.getColor(context,R.color.darkGray));
            Log.d("TKT_cateringPlate","tag1: " + txt.getTag().toString());
        }
        else
        {
            Log.d("TKT_cateringPlate","equals checked");
            isCheckedArr[pos] = false;
            txt.setTag(context.getString(R.string.TAGunchecked));
            txt.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
            Log.d("TKT_cateringPlate","tag2: " + txt.getTag().toString());
        }
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

    public static void checkHandler(String [] itemArr, boolean [] isChecked, String flag, String initAmount) {
        Log.d("TKT_catering", "checkHandler==============");
        if (children) {
            if(isChecked[0]) {
                Cashier.cateringOrder.put(itemArr[0], new CateringObjectInfo(Cashier.CATERING_PRICES[Cashier.VEGGIE_PLATE], initAmount));
            }
            if(isChecked[1]) {
                Cashier.cateringOrder.put(itemArr[1], new CateringObjectInfo(Cashier.CATERING_PRICES[Cashier.PASTA], initAmount));
            }

        } else {
            CateringObjectInfo c = new CateringObjectInfo(PRICE, initAmount);
            int counter = 0;
            if (flag.equals(TORTILLA_FLAG)) {//tortillas
                Log.d("TKT_catering", "flag = tortilla");
                String tortillaString = context.getString(R.string.tortillaFilledWith);
                for (int i = 0; i < itemArr.length; i++) {
                    if (isChecked[i]) {
                        Log.d("TKT_catering", "itemArr[" + i + "] is checked");
                        tortillaString += "\n- " + itemArr[i];
                        counter++;
                    }
                }


                if (!tortillaString.equals(context.getString(R.string.tortillaFilledWith))) {
                    String prevEntry = CateringPlates.getTortillaEntry();
                    if (prevEntry != null) {
                        Log.d("TKT_catering", "remove prev tortilla entry: " + prevEntry);
                        Log.d("TKT_catering", "new tortilla entry: " + tortillaString);
                        Cashier.cateringOrder.remove(prevEntry);
                    }
                    Cashier.cateringOrder.put(tortillaString, c);
                }
            } else {
                Log.d("TKT_catering", "flag = noFlag");
                for (int i = 0; i < itemArr.length; i++) {
                    if (isChecked[i])
                        Cashier.cateringOrder.put(itemArr[i], new CateringObjectInfo(Catering.PRICE, initAmount));
                }
            }
        }
    }

}
