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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Currency;


public class ItemScreen extends AppCompatActivity {

    Currency ILS = Currency.getInstance(Cashier.il);
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_screen);
        context = this;
        Log.d("TKT_itemScreen","fileNameb4change: " + Cashier.FILE_NAME);
        //Cashier.FILE_NAME = getString(R.string.report) + ": " + Cashier.date.format(Cashier.c.getTime());
        Cashier.paymentText = (TextView)findViewById(R.id.paymentText);
        TextView NIS = (TextView)findViewById(R.id.NIS);
        NIS.setText(ILS.getSymbol(Cashier.il));
        Log.d("TKT_itemScreen",Cashier.FILE_NAME);

    }

    public void drinks(View v)
    {
        //Cashier.type = getString(R.string.drink);
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.drinks_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button ice5 = (Button)Cashier.dialog.findViewById(R.id.ice5button);
        ice5.setTag(Cashier.I_ICE5);
        Button ice7 = (Button)Cashier.dialog.findViewById(R.id.ice7button);
        ice7.setTag(Cashier.I_ICE7);
        Button slurpee5 = (Button)Cashier.dialog.findViewById(R.id.slurpee5button);
        slurpee5.setTag(Cashier.I_SLURPEE5);
        Button slurpee7 = (Button)Cashier.dialog.findViewById(R.id.slurpee7button);
        slurpee7.setTag(Cashier.I_SLURPEE7);
        Button lemonade5 = (Button)Cashier.dialog.findViewById(R.id.lemonade5button);
        lemonade5.setTag(Cashier.I_LEMONADE5);
        Button lemonade7 = (Button)Cashier.dialog.findViewById(R.id.lemonade7button);
        lemonade7.setTag(Cashier.I_LEMONADE7);
        Button orange5 = (Button)Cashier.dialog.findViewById(R.id.orange5button);
        orange5.setTag(Cashier.I_ORANGE5);
        Button orange7 = (Button)Cashier.dialog.findViewById(R.id.orange7button);
        orange7.setTag(Cashier.I_ORANGE7);
        Button hotChoc = (Button)Cashier.dialog.findViewById(R.id.hotChocoButton);
        hotChoc.setTag(Cashier.I_HOT_CHOC);
        Button choc = (Button)Cashier.dialog.findViewById(R.id.chocoButton);
        choc.setTag(Cashier.I_CHOC);
        Button cappuccino = (Button)Cashier.dialog.findViewById(R.id.cappuccinoButton);
        cappuccino.setTag(Cashier.I_CAPPUCCINO);
        Button can5 = (Button)Cashier.dialog.findViewById(R.id.can5button);
        can5.setTag(Cashier.I_CAN5);
        Button can6 = (Button)Cashier.dialog.findViewById(R.id.can6button);
        can6.setTag(Cashier.I_CAN6);
        Button water = (Button)Cashier.dialog.findViewById(R.id.waterButton);
        water.setTag(Cashier.I_WATER);


        Cashier.dialog.show();
    }

    public void sandwiches(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.sandwich_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button greenOmlette = (Button)Cashier.dialog.findViewById(R.id.greenOmletteButton);
        greenOmlette.setTag(Cashier.GREEN_OMLETTE);
        Button tunaSalad = (Button)Cashier.dialog.findViewById(R.id.tunaSaladButton);
        tunaSalad.setTag(Cashier.TUNA_SALAD);
        Button shakshuka = (Button)Cashier.dialog.findViewById(R.id.shakshukaButton);
        shakshuka.setTag(Cashier.SHAKSHUKA);

        Button eggplantCheese = (Button)Cashier.dialog.findViewById(R.id.cheeseEggplantBuutton);
        eggplantCheese.setTag(Cashier.EGGPLANT_CHEESE);
        Button pesto = (Button)Cashier.dialog.findViewById(R.id.pestoButton);
        pesto.setTag(Cashier.PESTO);
        Button spicyEggplant = (Button)Cashier.dialog.findViewById(R.id.spicyEggplantButton);
        spicyEggplant.setTag(Cashier.SPICY_EGGPLANT);


        Button sabiah = (Button)Cashier.dialog.findViewById(R.id.sabihButton);
        sabiah.setTag(Cashier.SABIH);
        Button tuna = (Button)Cashier.dialog.findViewById(R.id.tunaButton);
        tuna.setTag(Cashier.TUNA);
        Button creamCheese = (Button)Cashier.dialog.findViewById(R.id.creamCheeseButton);
        creamCheese.setTag(Cashier.CREAM_CHEESE);
        Button bulgarian = (Button)Cashier.dialog.findViewById(R.id.bulgarianButton);
        bulgarian.setTag(Cashier.BULGARIAN);
        Button eggSalad = (Button)Cashier.dialog.findViewById(R.id.eggSaladbutton);
        eggSalad.setTag(Cashier.EGG_SALAD);


        Button avocado = (Button)Cashier.dialog.findViewById(R.id.avocadobutton);
        avocado.setTag(Cashier.AVOCADO);
        Button omlette = (Button)Cashier.dialog.findViewById(R.id.omletteButton);
        omlette.setTag(Cashier.OMLETTE);
        Button tivol = (Button)Cashier.dialog.findViewById(R.id.tivolButton);
        tivol.setTag(Cashier.TIVOL);
        Button yellowCheese = (Button)Cashier.dialog.findViewById(R.id.yellowCheeseButton);
        yellowCheese.setTag(Cashier.YELLOW_CHEESE);

        Cashier.dialog.show();

    }

    public void pastries(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.pastry_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button chocCroi = (Button)Cashier.dialog.findViewById(R.id.chocCroissantButton);
        chocCroi.setTag(Cashier.CHOCO_CROI);
        Button cheeseCroi = (Button)Cashier.dialog.findViewById(R.id.cheeseCroissantButton);
        cheeseCroi.setTag(Cashier.CHEESE_CROI);
        Button butterCroi = (Button)Cashier.dialog.findViewById(R.id.butterCroissantButton);
        butterCroi.setTag(Cashier.BUTTER_CROI);
        Button cinnamonCroi = (Button)Cashier.dialog.findViewById(R.id.cinnamonCroissantButton);
        cinnamonCroi.setTag(Cashier.CINNAMON_CROI);
        Button donut = (Button)Cashier.dialog.findViewById(R.id.donutButton);
        donut.setTag(Cashier.DONUT);
        Button potatoBurek = (Button)Cashier.dialog.findViewById(R.id.poratoBurektBuutton);
        potatoBurek.setTag(Cashier.POTATO_BUREK);
        Button cheeseBurek = (Button)Cashier.dialog.findViewById(R.id.cheeseBurekButton);
        cheeseBurek.setTag(Cashier.CHEESE_BUREK);
        Button eggBurek = (Button)Cashier.dialog.findViewById(R.id.eggBurektButton);
        eggBurek.setTag(Cashier.EGG_BUREK);
        Button melawac = (Button)Cashier.dialog.findViewById(R.id.melawacbutton);
        melawac.setTag(Cashier.MELAWAC);
        Button addition = (Button)Cashier.dialog.findViewById(R.id.additionButton);
        addition.setTag(Cashier.ADDITION);
        Button pizza = (Button)Cashier.dialog.findViewById(R.id.pizzaButton);
        pizza.setTag(Cashier.PIZZA);
        Button pizzaPlus = (Button)Cashier.dialog.findViewById(R.id.pizzaPlusButton);
        pizzaPlus.setTag(Cashier.PIZZA_PLUS);
        Button bulgarianPizza = (Button)Cashier.dialog.findViewById(R.id.bulgarianPizzaButton);
        bulgarianPizza.setTag(Cashier.PIZZA_BULGARIAN);


        Cashier.dialog.show();
    }

    public void panini(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.panini_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button fingerPanini = (Button)Cashier.dialog.findViewById(R.id.fingerPaniniButton);
        fingerPanini.setTag(Cashier.PANINI_FINGER);
        Button baglePanini = (Button)Cashier.dialog.findViewById(R.id.baglePaniniButton);
        baglePanini.setTag(Cashier.PANINI_BAGLE);
        Button addition1 = (Button)Cashier.dialog.findViewById(R.id.oneButton);
        addition1.setTag(Cashier.ADDITION1);
        Button addition3 = (Button)Cashier.dialog.findViewById(R.id.threeButton);
        addition3.setTag(Cashier.ADDITION3);

        Cashier.dialog.show();
    }

    public void salads(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.salad_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button salad = (Button)Cashier.dialog.findViewById(R.id.saladButton);
        salad.setTag(Cashier.SALAD);
        Button bread = (Button)Cashier.dialog.findViewById(R.id.breadButton);
        bread.setTag(Cashier.SALAD_BREAD);
        Button saladAddition = (Button)Cashier.dialog.findViewById(R.id.saladAdditionBuutton);
        saladAddition.setTag(Cashier.SALAD_ADDITION);

        Cashier.dialog.show();
    }

    public void hots(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.hots_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button pasta = (Button)Cashier.dialog.findViewById(R.id.pastaButton);
        pasta.setTag(Cashier.PASTA);
        Button kuskus = (Button)Cashier.dialog.findViewById(R.id.kuskusButton);
        kuskus.setTag(Cashier.KUSKUS);
        Button cornMilanesa = (Button)Cashier.dialog.findViewById(R.id.milanesaButton);
        cornMilanesa.setTag(Cashier.CORN_MILANESA);
        Button yamSoup = (Button)Cashier.dialog.findViewById(R.id.yamBuutton);
        yamSoup.setTag(Cashier.YAM_SOUP);
        Button lentilSoup = (Button)Cashier.dialog.findViewById(R.id.lentilButton);
        lentilSoup.setTag(Cashier.LENTIL_SOUP);
        Button veggieSoup = (Button)Cashier.dialog.findViewById(R.id.veggiButton);
        veggieSoup.setTag(Cashier.VEGGIE_SOUP);

        Cashier.dialog.show();
    }

    public void fruitMeusli(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.meusli_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button watermelon = (Button)Cashier.dialog.findViewById(R.id.watermelonButton);
        watermelon.setTag(Cashier.WATERMELON);
        Button meusli = (Button)Cashier.dialog.findViewById(R.id.meusliButton);
        meusli.setTag(Cashier.FRUIT_MEUSLI);
        Button mullerFroop = (Button)Cashier.dialog.findViewById(R.id.froopBuutton);
        mullerFroop.setTag(Cashier.FROOP_MULLER);
        Button clickMuller = (Button)Cashier.dialog.findViewById(R.id.mullerClickButton);
        clickMuller.setTag(Cashier.CLICK_MULLER);

        Cashier.dialog.show();
    }

    public void candy(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.candy_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        //// TODO: 9/11/2017 finish this 
        
        Cashier.dialog.show();
    }

    public void chooseItem(View v)
    {
        Log.d("TKT_itemScreen","chooseItem===================");
        Cashier.updatePayment((Button)v);


    }

    public void cancel(View v)
    {
        Cashier.cancel();
    }

    public void check(View v)
    {
        Cashier.check();
    }

    @Override
    public void onBackPressed() {
        //// TODO: 9/6/2017 display 'are u sure' message
        //super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case android.R.id.home:
            {
                onBackPressed();
                return true;
            }
            case R.id.reportMenu:
            {
                Log.d("TKT_itemScreen","reportMenu===================");
                Intent intent  = new Intent(this, Report.class);
                startActivity(intent);
                return true;
            }
            case R.id.endShiftMenu:
            {
                Log.d("TKT_itemScreen","endShift===================");
                //// TODO: 9/10/2017 dialog to generate file as pdf and send through whatsapp or mail
                try {
                    Cashier.endShift();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("TKT_itemScreen","exception happened");
                }
                return true;
            }
        }
        return false;
    }
}
