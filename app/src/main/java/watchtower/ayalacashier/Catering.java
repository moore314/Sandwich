package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Catering extends AppCompatActivity {
    //// TODO: 11/27/2017 set on destroy and put order details in shared 

    static Context context;
    //public static String PRICE = "0.0";
    public static String NO_FLAG = "noFlag";
    public static String TORTILLA_FLAG = "tortillaFlag";
    public static boolean childrenFlag = false;
    static Button delivery, quiche, salad, plates, pasta, soup, bread, desserts, children, rent, newOrder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering);
        context = this;
        Log.d("TKT_catering","onCreate================");
        salad = (Button)findViewById(R.id.saladCatering);
        quiche = (Button)findViewById(R.id.quicheCatering);
        plates = (Button)findViewById(R.id.platesCatering);
        pasta = (Button)findViewById(R.id.pastaCatering);
        soup = (Button)findViewById(R.id.soupCatering);
        //Button foucachas = (Button)findViewById(R.id.foucachaCatering);
        bread = (Button)findViewById(R.id.breadCatering);
        desserts = (Button)findViewById(R.id.dessertCatering);
        children = (Button)findViewById(R.id.childrenCatering);
        delivery = (Button)findViewById(R.id.deliveryCatering);
        rent = (Button)findViewById(R.id.rentThePlace);
        newOrder = (Button)findViewById(R.id.newOrderButton);
        Cashier.getCateringOrderFromFile(context);
        if (Cashier.cateringOrder.containsKey(getString(R.string.deliveryCatering)))
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
        /*
        foucachas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foucachasDialog();
            }
        });
*/
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

            rent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rentDialog();
                }
            });
       // }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cashier.getCateringOrderFromFile(context);
        if(Cashier.cateringOrder.containsKey(getString(R.string.deliveryCatering)))
            delivery.setBackground(getDrawable(R.drawable.circle_gray));
        else
            delivery.setBackground(getDrawable(R.drawable.circle));
        if(Cashier.checkPrefs.getBoolean(Cashier.PAYPAL_PAID_CATERING,false))
        {
            AlertDialog.Builder message = new AlertDialog.Builder(context);
            message.setMessage(R.string.cantAddItemsCatering).create();
            message.show();
            initButtons(false);
        }
        else
            initButtons(true);
    }

    public static void initButtons(boolean flag)
    {
        Log.d("TKT_catering","initButtons================: "+flag);
        salad.setEnabled(flag);
        quiche.setEnabled(flag);
        plates.setEnabled(flag);
        pasta.setEnabled(flag);
        soup.setEnabled(flag);
        bread.setEnabled(flag);
        desserts.setEnabled(flag);
        children.setEnabled(flag);
        rent.setEnabled(flag);
        delivery.setEnabled(flag);
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
        childrenFlag = false;
        final String [] quicheType = {getString(R.string.mushroomQuicheCateringIngre), getString(R.string.yamQuicheCateringIngre),getString(R.string.broccoliQuicheCateringIngre),getString(R.string.onionQuicheCateringIngre)};
        final boolean [] isCheckedArr = new boolean[4];
        Cashier.PRICE = Cashier.CATERING_PRICES[Cashier.QUICHE];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
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
                checkHandler(quicheType, isCheckedArr,NO_FLAG,Cashier.ONE,Cashier.ZERO, Cashier.ZERO,quicheType);//// TODO: 2/11/2018 i know it's bad practice, fix before production
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();

    }

    public void pastaDialog()
    {
        Log.d("TKT_catering","pastaDialog================");
        childrenFlag = false;
        final String [] pastaString = {getString(R.string.pastaType1Catering),getString(R.string.pastaType2Catering)};
        final boolean [] isCheckedArr = new boolean[2];
        Cashier.PRICE = Cashier.CATERING_PRICES[Cashier.PASTA];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
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
                checkHandler(pastaString, isCheckedArr,NO_FLAG,Cashier.ONE,Cashier.ZERO,Cashier.ZERO,pastaString);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void soupDialog()
    {
        Log.d("TKT_catering","soupDialog================");
        childrenFlag = false;
        final String [] soupType = {getString(R.string.orangeSoupCatering),getString(R.string.onionSoupCatering),getString(R.string.lentilSoupCatering)};
        final boolean [] isCheckedArr = new boolean[3];
        Cashier.PRICE = Cashier.CATERING_PRICES[Cashier.SOUP];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
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
                checkHandler(soupType, isCheckedArr,NO_FLAG,Cashier.ONE,Cashier.ZERO,Cashier.ZERO, soupType);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    /*
    public void foucachasDialog()
    {
        Log.d("TKT_catering","foucachasialog================");
        childrenFlag = false;
        final String [] foucachaString = {getString(R.string.foucachasCateringIng)};
        final boolean [] isCheckedArr = new boolean[1];
        Cashier.PRICE = Cashier.CATERING_PRICES[Cashier.FOUCACHAS];
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
                checkHandler(foucachaString, isCheckedArr,NO_FLAG,Cashier.ONE,Cashier.ZERO,Cashier.ZERO);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }
    */

    public void breadBasketDialog()
    {
        //// TODO: 1/25/2018 consider change color of items not included in check 
        Log.d("TKT_catering","breadBasketDialog================");
        childrenFlag = false;
        final String [] breadBasketString = {getString(R.string.breadBasketCateringIng)};
        final String [] orderString = {getString(R.string.breadString)};
        final boolean [] isCheckedArr = new boolean[1];
        Cashier.PRICE = Cashier.CATERING_PRICES[Cashier.BREAD_BASKET];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
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
                checkHandler(breadBasketString, isCheckedArr,NO_FLAG,Cashier.ONE,Cashier.ZERO,Cashier.ZERO,orderString);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void dessertDialog()
    {
        Log.d("TKT_catering","dessertDialog================");
        childrenFlag = false;
        final String [] dessertType = {getString(R.string.chocolatePieCateringIng), getString(R.string.nutPieCateringIng),getString(R.string.cheesePieCateringIng),getString(R.string.berriesPieCateringIng)};
        final boolean [] isCheckedArr = new boolean[4];
        Cashier.PRICE = Cashier.CATERING_PRICES[Cashier.DESSERT];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Cashier.dialog.setContentView(R.layout.dialog_catering_desserts);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);

        final TextView chocolatePieTxt = (TextView)Cashier.dialog.findViewById(R.id.chocolatePieTxt);
        final TextView nutPieTxt = (TextView)Cashier.dialog.findViewById(R.id.nutPieTxt);
        final TextView cheesePieTxt = (TextView)Cashier.dialog.findViewById(R.id.cheesePieTxt);
        final TextView berriesPieTxt = (TextView)Cashier.dialog.findViewById(R.id.berriesPieTxt);

        if(Cashier.cateringOrder.containsKey(dessertType[0]))
            Catering.setBackgroundCateringTxt(chocolatePieTxt,context,isCheckedArr,0);

        if(Cashier.cateringOrder.containsKey(dessertType[1]))
            Catering.setBackgroundCateringTxt(nutPieTxt,context,isCheckedArr,1);

        if(Cashier.cateringOrder.containsKey(dessertType[2]))
            Catering.setBackgroundCateringTxt(cheesePieTxt,context,isCheckedArr,2);

        if(Cashier.cateringOrder.containsKey(dessertType[3]))
            Catering.setBackgroundCateringTxt(berriesPieTxt,context,isCheckedArr,3);




        chocolatePieTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(chocolatePieTxt,context,isCheckedArr,0);
            }
        });
        nutPieTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(nutPieTxt,context,isCheckedArr,1);
            }
        });
        cheesePieTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(cheesePieTxt,context,isCheckedArr,2);
            }
        });
        berriesPieTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(berriesPieTxt,context,isCheckedArr,3);
            }
        });


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(dessertType, isCheckedArr,NO_FLAG,Cashier.ONE,Cashier.ZERO, Cashier.ZERO, dessertType);//// TODO: 2/11/2018 i know it's bad practice, fix before production
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void childrenDialog()
    {

        Log.d("TKT_catering","childrenDialog================");
        childrenFlag = true;
        final String [] childrenString = {getString(R.string.veggiePlateCateringIngre),getString(R.string.pastaType2Catering)};
        final boolean [] isCheckedArr = new boolean[2];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
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
                checkHandler(childrenString, isCheckedArr,NO_FLAG,Cashier.ONE, Cashier.CATERING_PRICES[Cashier.VEGGIE_PLATE],Cashier.CATERING_PRICES[Cashier.PASTA],null);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void rentDialog()
    {
        Log.d("TKT_catering","rentDialog================");
        childrenFlag = false;
        final String [] rentString = {getString(R.string.rentThePlace)};
        final boolean [] isCheckedArr = new boolean[1];
        Cashier.PRICE = Cashier.CATERING_PRICES[Cashier.RENT];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Cashier.dialog.setContentView(R.layout.dialog_catering_rent);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);

        final TextView rentTxt = (TextView)Cashier.dialog.findViewById(R.id.rentThePlacetxt);

        if(Cashier.cateringOrder.containsKey(rentString[0]))
            Catering.setBackgroundCateringTxt(rentTxt,context,isCheckedArr,0);

        rentTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catering.setBackgroundCateringTxt(rentTxt,context,isCheckedArr,0);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(rentString, isCheckedArr,NO_FLAG,Cashier.ONE, Cashier.ZERO, Cashier.ZERO, rentString);
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void chooseDelivery(View v)
    {
        //PRICE = Cashier.CATERING_PRICES[6];
        Log.d("TKT_CateringSalads","openThiniSalad================");
        Cashier.cateringButtonBackgroundChange(v, this, Cashier.CATERING_PRICES[Cashier.DELIVERY],getString(R.string.deliveryString));
    }

    public void newOrderDialog(View v)
    {
        Log.d("TKT_catering","newOrderDialog================");
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Cashier.dialog.setContentView(R.layout.dialog_new_order);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button yes = (Button)Cashier.dialog.findViewById(R.id.proceedNewOrder);
        Button no = (Button)Cashier.dialog.findViewById(R.id.cancelNewOrder);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //erase all and init all
                Cashier.deleteCateringOrder(context);
                initButtons(true);
                Cashier.dialog.dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
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

    public static void checkHandler(String [] itemArr, boolean [] isChecked, String flag, String initAmount, String priceTag1, String priceTag2, String [] orderString) {
        Log.d("TKT_catering", "checkHandler==============");
        if (childrenFlag)
        {
            if(isChecked[0])
            {//veggie
                if(!Cashier.cateringOrder.containsKey(itemArr[0]))
                    Cashier.cateringOrder.put(itemArr[0], new CateringObjectInfo(priceTag1, initAmount,context.getString(R.string.orderStringVeggiePlate)));
            }
            else
                Cashier.cateringOrder.remove(itemArr[0]);

            if(isChecked[1])
            {//pasta
                if(!Cashier.cateringOrder.containsKey(itemArr[1]))
                    Cashier.cateringOrder.put(itemArr[1], new CateringObjectInfo(priceTag2, initAmount,itemArr[1]));
            }
            else
                Cashier.cateringOrder.remove(itemArr[1]);

        }
        else
            {
            CateringObjectInfo c = new CateringObjectInfo(Cashier.PRICE, initAmount,"");
            if (flag.equals(TORTILLA_FLAG))
            {//tortillas
                Log.d("TKT_catering", "flag = tortilla");
                String tortillaString = context.getString(R.string.tortillaFilledWith);
                for (int i = 0; i < itemArr.length; i++) {
                    if (isChecked[i]) {
                        Log.d("TKT_catering", "itemArr[" + i + "] is checked");
                        tortillaString += "\n- " + itemArr[i];
                    }
                    //else
                }
                c.setOrderString(tortillaString);
                if (!tortillaString.equals(context.getString(R.string.tortillaFilledWith))) {
                    String prevEntry = CateringPlates.getTortillaEntry();
                    if (prevEntry != null) {
                        Log.d("TKT_catering", "remove prev tortilla entry: " + prevEntry);
                        Log.d("TKT_catering", "new tortilla entry: " + tortillaString);
                        Cashier.cateringOrder.remove(prevEntry);
                    }
                    Cashier.cateringOrder.put(tortillaString, c);
                }
            }
            else
                {
                Log.d("TKT_catering", "flag = noFlag");
                for (int i = 0; i < itemArr.length; i++) {
                    if (isChecked[i]) {
                        if(!Cashier.cateringOrder.containsKey(itemArr[i]))
                            Cashier.cateringOrder.put(itemArr[i], new CateringObjectInfo(Cashier.PRICE, initAmount,orderString[i]));
                    }
                    else
                        Cashier.cateringOrder.remove(itemArr[i]);
                }
            }
        }

        Cashier.writeToFileCateringOrder(context);

    }

}
