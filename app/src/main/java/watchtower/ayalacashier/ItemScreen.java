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
import android.widget.EditText;
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

    //// TODO: 9/17/2017 display correct itemPrice on button 
    
    public void drinks(View v)
    {
        //Cashier.type = getString(R.string.drink);
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.drinks_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button ice5 = (Button)Cashier.dialog.findViewById(R.id.ice5button);
        //ice5.setTag(Cashier.DRINK_PRICES[Cashier.IND_ICE5]);
        Log.d("TKT_cashier","ice5Name: "+ice5.getText());
        Log.d("TKT_cashier","ice5Name: "+Cashier.DRINK_NAMES[Cashier.IND_ICE5]);
        Log.d("TKT_itemScreen","drinks: ice5.Tag: "+Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_ICE5], (float)Cashier.DRINK_PRICES[Cashier.IND_ICE5]));
        ice5.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_ICE5], (float)Cashier.DRINK_PRICES[Cashier.IND_ICE5]));

        Button ice7 = (Button)Cashier.dialog.findViewById(R.id.ice7button);
        //ice7.setTag(Cashier.DRINK_PRICES[Cashier.IND_ICE7]);
        ice7.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_ICE7], (float)Cashier.DRINK_PRICES[Cashier.IND_ICE7]));

        Button slurpee5 = (Button)Cashier.dialog.findViewById(R.id.slurpee5button);
        //slurpee5.setTag(Cashier.DRINK_PRICES[Cashier.IND_SLURPEE5]);
        slurpee5.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_SLURPEE5], (float)Cashier.DRINK_PRICES[Cashier.IND_SLURPEE5]));

        Button slurpee7 = (Button)Cashier.dialog.findViewById(R.id.slurpee7button);
        //slurpee7.setTag(Cashier.DRINK_PRICES[Cashier.IND_SLURPEE7]);
        slurpee7.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_SLURPEE7], (float)Cashier.DRINK_PRICES[Cashier.IND_SLURPEE7]));


        Button lemonade5 = (Button)Cashier.dialog.findViewById(R.id.lemonade5button);
        //lemonade5.setTag(Cashier.DRINK_PRICES[Cashier.IND_LEMONADE5]);
        lemonade5.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_LEMONADE5], (float)Cashier.DRINK_PRICES[Cashier.IND_LEMONADE5]));


        Button lemonade7 = (Button)Cashier.dialog.findViewById(R.id.lemonade7button);
        //lemonade7.setTag(Cashier.DRINK_PRICES[Cashier.IND_LEMONADE7]);
        lemonade7.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_LEMONADE7], (float)Cashier.DRINK_PRICES[Cashier.IND_LEMONADE7]));

        Button orange5 = (Button)Cashier.dialog.findViewById(R.id.orange5button);
        //orange5.setTag(Cashier.DRINK_PRICES[Cashier.IND_ORANGE5]);
        orange5.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_ORANGE5], (float)Cashier.DRINK_PRICES[Cashier.IND_ORANGE5]));

        Button orange7 = (Button)Cashier.dialog.findViewById(R.id.orange7button);
        //orange7.setTag(Cashier.DRINK_PRICES[Cashier.IND_ORANGE7]);
        orange7.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_ORANGE7], (float)Cashier.DRINK_PRICES[Cashier.IND_ORANGE7]));

        Button hotChoc = (Button)Cashier.dialog.findViewById(R.id.hotChocoButton);
        //hotChoc.setTag(Cashier.DRINK_PRICES[Cashier.IND_HOT_CHOC]);
        hotChoc.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_HOT_CHOC], (float)Cashier.DRINK_PRICES[Cashier.IND_HOT_CHOC]));

        Button choc = (Button)Cashier.dialog.findViewById(R.id.chocoButton);
        //choc.setTag(Cashier.DRINK_PRICES[Cashier.IND_CHOC]);
        choc.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_CHOC], (float)Cashier.DRINK_PRICES[Cashier.IND_CHOC]));

        Button cappuccino = (Button)Cashier.dialog.findViewById(R.id.cappuccinoButton);
        //cappuccino.setTag(Cashier.DRINK_PRICES[Cashier.IND_CAPPUCCINO]);
        cappuccino.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_CAPPUCCINO], (float)Cashier.DRINK_PRICES[Cashier.IND_CAPPUCCINO]));

        Button can5 = (Button)Cashier.dialog.findViewById(R.id.can5button);
        //can5.setTag(Cashier.DRINK_PRICES[Cashier.IND_CAN5]);
        can5.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_CAN5], (float)Cashier.DRINK_PRICES[Cashier.IND_CAN5]));

        Button can6 = (Button)Cashier.dialog.findViewById(R.id.can6button);
        //can6.setTag(Cashier.DRINK_PRICES[Cashier.IND_CAN6]);
        can6.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_CAN6], (float)Cashier.DRINK_PRICES[Cashier.IND_CAN6]));

        Button water = (Button)Cashier.dialog.findViewById(R.id.waterButton);
        //water.setTag(Cashier.DRINK_PRICES[Cashier.IND_WATER]);
        water.setTag(Cashier.checkPrefs.getFloat(Cashier.DRINK_NAMES[Cashier.IND_WATER], (float)Cashier.DRINK_PRICES[Cashier.IND_WATER]));


        Cashier.dialog.show();
    }

    public void sandwiches(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.sandwich_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button spicyEggplant = (Button)Cashier.dialog.findViewById(R.id.spicyEggplantButton);
        //spicyEggplant.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_SPICY_EGGPLANT]);
        spicyEggplant.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_SPICY_EGGPLANT], (float)Cashier.SANDWICH_PRICES[Cashier.IND_SPICY_EGGPLANT]));

        Button shakshuka = (Button)Cashier.dialog.findViewById(R.id.shakshukaButton);
        //shakshuka.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_SHAKSHUKA]);
        shakshuka.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_SHAKSHUKA], (float)Cashier.SANDWICH_PRICES[Cashier.IND_SHAKSHUKA]));

        Button sabiah = (Button)Cashier.dialog.findViewById(R.id.sabihButton);
        //sabiah.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_SABIH]);
        sabiah.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_SABIH], (float)Cashier.SANDWICH_PRICES[Cashier.IND_SABIH]));

        Button greenOmlette = (Button)Cashier.dialog.findViewById(R.id.greenOmletteButton);
        //greenOmlette.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_GREEN_OMLETTE]);
        greenOmlette.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_GREEN_OMLETTE], (float)Cashier.SANDWICH_PRICES[Cashier.IND_GREEN_OMLETTE]));

        Button tunaSalad = (Button)Cashier.dialog.findViewById(R.id.tunaSaladButton);
        //tunaSalad.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_TUNA_SALAD]);
        tunaSalad.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_TUNA_SALAD], (float)Cashier.SANDWICH_PRICES[Cashier.IND_TUNA_SALAD]));

        Button pesto = (Button)Cashier.dialog.findViewById(R.id.pestoButton);
        //pesto.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_PESTO]);
        pesto.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_PESTO], (float)Cashier.SANDWICH_PRICES[Cashier.IND_PESTO]));

        Button eggplantCheese = (Button)Cashier.dialog.findViewById(R.id.cheeseEggplantBuutton);
        //eggplantCheese.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_EGGPLANT_CHEESE]);
        eggplantCheese.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_EGGPLANT_CHEESE], (float)Cashier.SANDWICH_PRICES[Cashier.IND_EGGPLANT_CHEESE]));

        Button tuna = (Button)Cashier.dialog.findViewById(R.id.tunaButton);
        //tuna.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_TUNA]);
        tuna.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_TUNA], (float)Cashier.SANDWICH_PRICES[Cashier.IND_TUNA]));

        Button creamCheese = (Button)Cashier.dialog.findViewById(R.id.creamCheeseButton);
        //creamCheese.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_CREAM_CHEESE]);
        creamCheese.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_CREAM_CHEESE], (float)Cashier.SANDWICH_PRICES[Cashier.IND_CREAM_CHEESE]));

        Button bulgarian = (Button)Cashier.dialog.findViewById(R.id.bulgarianButton);
        //bulgarian.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_BULGARIAN]);
        bulgarian.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_BULGARIAN], (float)Cashier.SANDWICH_PRICES[Cashier.IND_BULGARIAN]));

        Button eggSalad = (Button)Cashier.dialog.findViewById(R.id.eggSaladbutton);
        //eggSalad.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_EGG_SALAD]);
        eggSalad.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_EGG_SALAD], (float)Cashier.SANDWICH_PRICES[Cashier.IND_EGG_SALAD]));

        Button avocado = (Button)Cashier.dialog.findViewById(R.id.avocadobutton);
        //avocado.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_AVOCADO]);
        avocado.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_AVOCADO], (float)Cashier.SANDWICH_PRICES[Cashier.IND_AVOCADO]));

        Button omlette = (Button)Cashier.dialog.findViewById(R.id.omletteButton);
        //omlette.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_OMLETTE]);
        omlette.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_OMLETTE], (float)Cashier.SANDWICH_PRICES[Cashier.IND_OMLETTE]));

        Button yellowCheese = (Button)Cashier.dialog.findViewById(R.id.yellowCheeseButton);
        //yellowCheese.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_YELLOW_CHEESE]);
        yellowCheese.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_YELLOW_CHEESE], (float)Cashier.SANDWICH_PRICES[Cashier.IND_YELLOW_CHEESE]));

        Button tivol = (Button)Cashier.dialog.findViewById(R.id.tivolButton);
        //tivol.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_TIVOL]);
        tivol.setTag(Cashier.checkPrefs.getFloat(Cashier.SANDWICH_NAMES[Cashier.IND_TIVOL], (float)Cashier.SANDWICH_PRICES[Cashier.IND_TIVOL]));

        Cashier.dialog.show();

    }

    public void pastries(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.pastry_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button chocCroi = (Button)Cashier.dialog.findViewById(R.id.chocCroissantButton);
        //chocCroi.setTag(Cashier.PASTRY_PRICES[Cashier.IND_CHOCO_CROI]);
        chocCroi.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_CHOCO_CROI], (float)Cashier.PASTRY_PRICES[Cashier.IND_CHOCO_CROI]));

        Button cheeseCroi = (Button)Cashier.dialog.findViewById(R.id.cheeseCroissantButton);
        //cheeseCroi.setTag(Cashier.PASTRY_PRICES[Cashier.IND_CHEESE_CROI]);
        cheeseCroi.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_CHEESE_CROI], (float)Cashier.PASTRY_PRICES[Cashier.IND_CHEESE_CROI]));

        Button butterCroi = (Button)Cashier.dialog.findViewById(R.id.butterCroissantButton);
        //butterCroi.setTag(Cashier.PASTRY_PRICES[Cashier.IND_BUTTER_CROI]);
        butterCroi.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_BUTTER_CROI], (float)Cashier.PASTRY_PRICES[Cashier.IND_BUTTER_CROI]));

        Button cinnamonCroi = (Button)Cashier.dialog.findViewById(R.id.cinnamonCroissantButton);
        //cinnamonCroi.setTag(Cashier.PASTRY_PRICES[Cashier.IND_CINNAMON_CROI]);
        cinnamonCroi.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_CINNAMON_CROI], (float)Cashier.PASTRY_PRICES[Cashier.IND_CINNAMON_CROI]));

        Button donut = (Button)Cashier.dialog.findViewById(R.id.donutButton);
        //donut.setTag(Cashier.PASTRY_PRICES[Cashier.IND_DONUT]);
        donut.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_DONUT], (float)Cashier.PASTRY_PRICES[Cashier.IND_DONUT]));

        Button cheeseBurek = (Button)Cashier.dialog.findViewById(R.id.cheeseBurekButton);
        //cheeseBurek.setTag(Cashier.PASTRY_PRICES[Cashier.IND_CHEESE_BUREK]);
        cheeseBurek.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_CHEESE_BUREK], (float)Cashier.PASTRY_PRICES[Cashier.IND_CHEESE_BUREK]));

        Button potatoBurek = (Button)Cashier.dialog.findViewById(R.id.poratoBurektBuutton);
        //potatoBurek.setTag(Cashier.PASTRY_PRICES[Cashier.IND_POTATO_BUREK]);
        potatoBurek.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_POTATO_BUREK], (float)Cashier.PASTRY_PRICES[Cashier.IND_POTATO_BUREK]));

        Button eggBurek = (Button)Cashier.dialog.findViewById(R.id.eggBurektButton);
        //eggBurek.setTag(Cashier.PASTRY_PRICES[Cashier.IND_EGG_BUREK]);
        eggBurek.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_EGG_BUREK], (float)Cashier.PASTRY_PRICES[Cashier.IND_EGG_BUREK]));

        Button melawac = (Button)Cashier.dialog.findViewById(R.id.melawacbutton);
        //melawac.setTag(Cashier.PASTRY_PRICES[Cashier.IND_MELAWAC]);
        melawac.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_MELAWAC], (float)Cashier.PASTRY_PRICES[Cashier.IND_MELAWAC]));

        Button addition = (Button)Cashier.dialog.findViewById(R.id.additionButton);
        //addition.setTag(Cashier.PASTRY_PRICES[Cashier.IND_MELAWAC_ADDITION]);
        addition.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_MELAWAC_ADDITION], (float)Cashier.PASTRY_PRICES[Cashier.IND_MELAWAC_ADDITION]));

        Button pizza = (Button)Cashier.dialog.findViewById(R.id.pizzaButton);
        //pizza.setTag(Cashier.PASTRY_PRICES[Cashier.IND_PIZZA]);
        pizza.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_PIZZA], (float)Cashier.PASTRY_PRICES[Cashier.IND_PIZZA]));

        Button pizzaPlus = (Button)Cashier.dialog.findViewById(R.id.pizzaPlusButton);
        //pizzaPlus.setTag(Cashier.PASTRY_PRICES[Cashier.IND_PIZZA_PLUS]);
        pizzaPlus.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_PIZZA_PLUS], (float)Cashier.PASTRY_PRICES[Cashier.IND_PIZZA_PLUS]));

        Button bulgarianPizza = (Button)Cashier.dialog.findViewById(R.id.bulgarianPizzaButton);
        //bulgarianPizza.setTag(Cashier.PASTRY_PRICES[Cashier.IND_PIZZA_BULGARIAN]);
        bulgarianPizza.setTag(Cashier.checkPrefs.getFloat(Cashier.PASTRY_NAMES[Cashier.IND_PIZZA_BULGARIAN], (float)Cashier.PASTRY_PRICES[Cashier.IND_PIZZA_BULGARIAN]));


        Cashier.dialog.show();
    }

    public void panini(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.panini_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button fingerPanini = (Button)Cashier.dialog.findViewById(R.id.fingerPaniniButton);
        //fingerPanini.setTag(Cashier.PANINI_PRICES[Cashier.IND_PANINI_FINGER]);
        fingerPanini.setTag(Cashier.checkPrefs.getFloat(Cashier.PANINI_NAMES[Cashier.IND_PANINI_FINGER], (float)Cashier.PANINI_PRICES[Cashier.IND_PANINI_FINGER]));
        
        Button baglePanini = (Button)Cashier.dialog.findViewById(R.id.baglePaniniButton);
        //baglePanini.setTag(Cashier.PANINI_PRICES[Cashier.IND_PANINI_BAGLE]);
        baglePanini.setTag(Cashier.checkPrefs.getFloat(Cashier.PANINI_NAMES[Cashier.IND_PANINI_BAGLE], (float)Cashier.PANINI_PRICES[Cashier.IND_PANINI_BAGLE]));

        Button addition1 = (Button)Cashier.dialog.findViewById(R.id.oneButton);
        //addition1.setTag(Cashier.PANINI_PRICES[Cashier.IND_PANINI_ADDITION1]);
        addition1.setTag(Cashier.checkPrefs.getFloat(Cashier.PANINI_NAMES[Cashier.IND_PANINI_ADDITION1], (float)Cashier.PANINI_PRICES[Cashier.IND_PANINI_ADDITION1]));

        Button addition3 = (Button)Cashier.dialog.findViewById(R.id.threeButton);
        //addition3.setTag(Cashier.PANINI_PRICES[Cashier.IND_PANINI_ADDITION3]);
        addition3.setTag(Cashier.checkPrefs.getFloat(Cashier.PANINI_NAMES[Cashier.IND_PANINI_ADDITION3], (float)Cashier.PANINI_PRICES[Cashier.IND_PANINI_ADDITION3]));

        Cashier.dialog.show();
    }

    public void salads(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.salad_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button salad = (Button)Cashier.dialog.findViewById(R.id.saladButton);
        //salad.setTag(Cashier.SALAD_PRICES[Cashier.IND_SALAD]);
        salad.setTag(Cashier.checkPrefs.getFloat(Cashier.SALAD_NAMES[Cashier.IND_SALAD], (float)Cashier.SALAD_PRICES[Cashier.IND_SALAD]));

        Button bread = (Button)Cashier.dialog.findViewById(R.id.breadButton);
        //bread.setTag(Cashier.SALAD_PRICES[Cashier.IND_SALAD_BREAD]);
        bread.setTag(Cashier.checkPrefs.getFloat(Cashier.SALAD_NAMES[Cashier.IND_SALAD_BREAD], (float)Cashier.SALAD_PRICES[Cashier.IND_SALAD_BREAD]));

        Button saladAddition = (Button)Cashier.dialog.findViewById(R.id.saladAdditionBuutton);
        //saladAddition.setTag(Cashier.SALAD_PRICES[Cashier.IND_SALAD_ADDITION]);
        saladAddition.setTag(Cashier.checkPrefs.getFloat(Cashier.SALAD_NAMES[Cashier.IND_SALAD_ADDITION], (float)Cashier.SALAD_PRICES[Cashier.IND_SALAD_ADDITION]));

        Cashier.dialog.show();
    }

    public void hots(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.hots_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button pasta = (Button)Cashier.dialog.findViewById(R.id.pastaButton);
        //pasta.setTag(Cashier.HOTS_PRICES[Cashier.IND_PASTA]);
        pasta.setTag(Cashier.checkPrefs.getFloat(Cashier.HOTS_NAMES[Cashier.IND_PASTA], (float)Cashier.HOTS_PRICES[Cashier.IND_PASTA]));

        Button kuskus = (Button)Cashier.dialog.findViewById(R.id.kuskusButton);
        //kuskus.setTag(Cashier.HOTS_PRICES[Cashier.IND_KUSKUS]);
        kuskus.setTag(Cashier.checkPrefs.getFloat(Cashier.HOTS_NAMES[Cashier.IND_KUSKUS], (float)Cashier.HOTS_PRICES[Cashier.IND_KUSKUS]));

        Button yamSoup = (Button)Cashier.dialog.findViewById(R.id.yamBuutton);
        //yamSoup.setTag(Cashier.HOTS_PRICES[Cashier.IND_YAM_SOUP]);
        yamSoup.setTag(Cashier.checkPrefs.getFloat(Cashier.HOTS_NAMES[Cashier.IND_YAM_SOUP], (float)Cashier.HOTS_PRICES[Cashier.IND_YAM_SOUP]));

        Button lentilSoup = (Button)Cashier.dialog.findViewById(R.id.lentilButton);
        //lentilSoup.setTag(Cashier.HOTS_PRICES[Cashier.IND_LENTIL_SOUP]);
        lentilSoup.setTag(Cashier.checkPrefs.getFloat(Cashier.HOTS_NAMES[Cashier.IND_LENTIL_SOUP], (float)Cashier.HOTS_PRICES[Cashier.IND_LENTIL_SOUP]));

        Button veggieSoup = (Button)Cashier.dialog.findViewById(R.id.veggiButton);
        //veggieSoup.setTag(Cashier.HOTS_PRICES[Cashier.IND_VEGGIE_SOUP]);
        veggieSoup.setTag(Cashier.checkPrefs.getFloat(Cashier.HOTS_NAMES[Cashier.IND_VEGGIE_SOUP], (float)Cashier.HOTS_PRICES[Cashier.IND_VEGGIE_SOUP]));

        Button cornMilanesa = (Button)Cashier.dialog.findViewById(R.id.milanesaButton);
        //cornMilanesa.setTag(Cashier.HOTS_PRICES[Cashier.IND_CORN_MILANESA]);
        cornMilanesa.setTag(Cashier.checkPrefs.getFloat(Cashier.HOTS_NAMES[Cashier.IND_CORN_MILANESA], (float)Cashier.HOTS_PRICES[Cashier.IND_CORN_MILANESA]));

        Cashier.dialog.show();
    }

    public void fruitMeusli(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.meusli_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button watermelon = (Button)Cashier.dialog.findViewById(R.id.watermelonButton);
        //watermelon.setTag(Cashier.FRUIT_PRICES[Cashier.IND_WATERMELON]);
        watermelon.setTag(Cashier.checkPrefs.getFloat(Cashier.FRUIT_NAMES[Cashier.IND_WATERMELON], (float)Cashier.FRUIT_PRICES[Cashier.IND_WATERMELON]));

        Button meusli = (Button)Cashier.dialog.findViewById(R.id.meusliButton);
        //meusli.setTag(Cashier.FRUIT_PRICES[Cashier.IND_FRUIT_MEUSLI]);
        meusli.setTag(Cashier.checkPrefs.getFloat(Cashier.FRUIT_NAMES[Cashier.IND_FRUIT_MEUSLI], (float)Cashier.FRUIT_PRICES[Cashier.IND_FRUIT_MEUSLI]));

        Button mullerFroop = (Button)Cashier.dialog.findViewById(R.id.froopBuutton);
        //mullerFroop.setTag(Cashier.FRUIT_PRICES[Cashier.IND_FROOP_MULLER ]);
        mullerFroop.setTag(Cashier.checkPrefs.getFloat(Cashier.FRUIT_NAMES[Cashier.IND_FROOP_MULLER], (float)Cashier.FRUIT_PRICES[Cashier.IND_FROOP_MULLER]));

        Button clickMuller = (Button)Cashier.dialog.findViewById(R.id.mullerClickButton);
        //clickMuller.setTag(Cashier.FRUIT_PRICES[Cashier.IND_CLICK_MULLER]);
        clickMuller.setTag(Cashier.checkPrefs.getFloat(Cashier.FRUIT_NAMES[Cashier.IND_CLICK_MULLER], (float)Cashier.FRUIT_PRICES[Cashier.IND_CLICK_MULLER]));


        Cashier.dialog.show();
    }

    public void candy(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.candy_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        //Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_RUG], (float)Cashier.CANDY_PRICES[Cashier.IND_RUG]);

        Button rug = (Button)Cashier.dialog.findViewById(R.id.rugButton);
        //rug.setTag(Cashier.CANDY_PRICES[Cashier.IND_RUG]);
        rug.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_RUG], (float)Cashier.CANDY_PRICES[Cashier.IND_RUG]));

        Button selzer = (Button)Cashier.dialog.findViewById(R.id.selzerButton);
        //selzer.setTag(Cashier.CANDY_PRICES[Cashier.IND_SELZER]);
        selzer.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_SELZER], (float)Cashier.CANDY_PRICES[Cashier.IND_SELZER]));

        Button marshmellow = (Button)Cashier.dialog.findViewById(R.id.marshmellowButton);
        //marshmellow.setTag(Cashier.CANDY_PRICES[Cashier.IND_MARSHMELLOW]);
        marshmellow.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_MARSHMELLOW], (float)Cashier.CANDY_PRICES[Cashier.IND_MARSHMELLOW]));

        Button lolliesDip = (Button)Cashier.dialog.findViewById(R.id.lolliesDipBbutton);
        //lolliesDip.setTag(Cashier.CANDY_PRICES[Cashier.IND_LOLLIES_DIP]);
        lolliesDip.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_LOLLIES_DIP], (float)Cashier.CANDY_PRICES[Cashier.IND_LOLLIES_DIP]));

        Button toffeeFizz = (Button)Cashier.dialog.findViewById(R.id.toffeeFizzButton);
        //toffeeFizz.setTag(Cashier.CANDY_PRICES[Cashier.IND_TOFFEE_FIZZ]);
        toffeeFizz.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_TOFFEE_FIZZ], (float)Cashier.CANDY_PRICES[Cashier.IND_TOFFEE_FIZZ]));

        Button piesPolvo = (Button)Cashier.dialog.findViewById(R.id.piesPolvoButton);
        //piesPolvo.setTag(Cashier.CANDY_PRICES[Cashier.IND_PIES_POLVO]);
        piesPolvo.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_PIES_POLVO], (float)Cashier.CANDY_PRICES[Cashier.IND_PIES_POLVO]));

        Button fizzNecklace = (Button)Cashier.dialog.findViewById(R.id.fizzButton);
        //fizzNecklace.setTag(Cashier.CANDY_PRICES[Cashier.IND_FIZZ_NECKLACE]);
        fizzNecklace.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_FIZZ_NECKLACE], (float)Cashier.CANDY_PRICES[Cashier.IND_FIZZ_NECKLACE]));

        Button heart = (Button)Cashier.dialog.findViewById(R.id.heartButton);
        //heart.setTag(Cashier.CANDY_PRICES[Cashier.IND_HEART]);
        heart.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_HEART], (float)Cashier.CANDY_PRICES[Cashier.IND_HEART]));

        Button paragua = (Button)Cashier.dialog.findViewById(R.id.paraguaButton);
        //paragua.setTag(Cashier.CANDY_PRICES[Cashier.IND_PARAGUA]);
        paragua.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_PARAGUA], (float)Cashier.CANDY_PRICES[Cashier.IND_PARAGUA]));

        Button pipe = (Button)Cashier.dialog.findViewById(R.id.pipeButton);
        //pipe.setTag(Cashier.CANDY_PRICES[Cashier.IND_PIPE]);
        pipe.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_PIPE], (float)Cashier.CANDY_PRICES[Cashier.IND_PIPE]));

        Button zoom = (Button)Cashier.dialog.findViewById(R.id.zoonButton);
        //zoom.setTag(Cashier.CANDY_PRICES[Cashier.IND_ZOOM]);
        zoom.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_ZOOM], (float)Cashier.CANDY_PRICES[Cashier.IND_ZOOM]));

        Button extreme = (Button)Cashier.dialog.findViewById(R.id.extremeButton);
        //extreme.setTag(Cashier.CANDY_PRICES[Cashier.IND_EXTREME]);
        extreme.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_EXTREME], (float)Cashier.CANDY_PRICES[Cashier.IND_EXTREME]));

        Button tictac = (Button)Cashier.dialog.findViewById(R.id.tictacButton);
        //tictac.setTag(Cashier.CANDY_PRICES[Cashier.IND_TICTAC]);
        tictac.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_TICTAC], (float)Cashier.CANDY_PRICES[Cashier.IND_TICTAC]));

        Button mentos = (Button)Cashier.dialog.findViewById(R.id.mentosButton);
        //mentos.setTag(Cashier.CANDY_PRICES[Cashier.IND_MENTOS]);
        mentos.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_MENTOS], (float)Cashier.CANDY_PRICES[Cashier.IND_MENTOS]));

        Button kinder = (Button)Cashier.dialog.findViewById(R.id.kinderButton);
        //kinder.setTag(Cashier.CANDY_PRICES[Cashier.IND_KINDER]);
        kinder.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_KINDER], (float)Cashier.CANDY_PRICES[Cashier.IND_KINDER]));

        Button sneakers = (Button)Cashier.dialog.findViewById(R.id.sneakersButton);
        //sneakers.setTag(Cashier.CANDY_PRICES[Cashier.IND_SNEAKERS]);
        sneakers.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_SNEAKERS], (float)Cashier.CANDY_PRICES[Cashier.IND_SNEAKERS]));

        Button mars = (Button)Cashier.dialog.findViewById(R.id.marsButton);
        //mars.setTag(Cashier.CANDY_PRICES[Cashier.IND_MARS]);
        mars.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_MARS], (float)Cashier.CANDY_PRICES[Cashier.IND_MARS]));

        Button sourSpray = (Button)Cashier.dialog.findViewById(R.id.sourSprayButton);
        //sourSpray.setTag(Cashier.CANDY_PRICES[Cashier.IND_SOUR_SPRAY]);
        sourSpray.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_SOUR_SPRAY], (float)Cashier.CANDY_PRICES[Cashier.IND_SOUR_SPRAY]));

        Button twix = (Button)Cashier.dialog.findViewById(R.id.twixButton);
        //twix.setTag(Cashier.CANDY_PRICES[Cashier.IND_TWIX]);
        twix.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_TWIX], (float)Cashier.CANDY_PRICES[Cashier.IND_TWIX]));

        Button teami = (Button)Cashier.dialog.findViewById(R.id.teamiButton);
        //teami.setTag(Cashier.CANDY_PRICES[Cashier.IND_TEAMI]);
        teami.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_TEAMI], (float)Cashier.CANDY_PRICES[Cashier.IND_TEAMI]));

        Button nutella = (Button)Cashier.dialog.findViewById(R.id.nutellaButton);
        //nutella.setTag(Cashier.CANDY_PRICES[Cashier.IND_NUTELLA]);
        nutella.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_NUTELLA], (float)Cashier.CANDY_PRICES[Cashier.IND_NUTELLA]));

        Button bueno = (Button)Cashier.dialog.findViewById(R.id.buenoButton);
        //bueno.setTag(Cashier.CANDY_PRICES[Cashier.IND_BUENO]);
        bueno.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_BUENO], (float)Cashier.CANDY_PRICES[Cashier.IND_BUENO]));

        Button timeOut = (Button)Cashier.dialog.findViewById(R.id.timeOutButton);
        //timeOut.setTag(Cashier.CANDY_PRICES[Cashier.IND_TIME_OUT]);
        timeOut.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_TIME_OUT], (float)Cashier.CANDY_PRICES[Cashier.IND_TIME_OUT]));

        Button clickBar = (Button)Cashier.dialog.findViewById(R.id.clickBarButton);
        //clickBar.setTag(Cashier.CANDY_PRICES[Cashier.IND_CLICK_BAR]);
        clickBar.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_CLICK_BAR], (float)Cashier.CANDY_PRICES[Cashier.IND_CLICK_BAR]));

        Button clickBag = (Button)Cashier.dialog.findViewById(R.id.clickBagButton);
        //clickBag.setTag(Cashier.CANDY_PRICES[Cashier.IND_CLICK_BAG]);
        clickBag.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_CLICK_BAG], (float)Cashier.CANDY_PRICES[Cashier.IND_CLICK_BAG]));

        Button clickTablet = (Button)Cashier.dialog.findViewById(R.id.clickTabletButton);
        //clickTablet.setTag(Cashier.CANDY_PRICES[Cashier.IND_CLICK_TABLET]);
        clickTablet.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_CLICK_TABLET], (float)Cashier.CANDY_PRICES[Cashier.IND_CLICK_TABLET]));

        Button bamba = (Button)Cashier.dialog.findViewById(R.id.bambaButton);
        //bamba.setTag(Cashier.CANDY_PRICES[Cashier.IND_BAMBA]);
        bamba.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_BAMBA], (float)Cashier.CANDY_PRICES[Cashier.IND_BAMBA]));

        Button doritos = (Button)Cashier.dialog.findViewById(R.id.doritosButton);
        //doritos.setTag(Cashier.CANDY_PRICES[Cashier.IND_DORITOS]);
        doritos.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_DORITOS], (float)Cashier.CANDY_PRICES[Cashier.IND_DORITOS]));

        Button bisli = (Button)Cashier.dialog.findViewById(R.id.bislyButton);
        //bisli.setTag(Cashier.CANDY_PRICES[Cashier.IND_BISLI]);
        bisli.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_BISLI], (float)Cashier.CANDY_PRICES[Cashier.IND_BISLI]));

        Button aproposito = (Button)Cashier.dialog.findViewById(R.id.apropositoButton);
        //aproposito.setTag(Cashier.CANDY_PRICES[Cashier.IND_APROPOSITO]);
        aproposito.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_APROPOSITO], (float)Cashier.CANDY_PRICES[Cashier.IND_APROPOSITO]));

        Button bears = (Button)Cashier.dialog.findViewById(R.id.bearsButton);
        //bears.setTag(Cashier.CANDY_PRICES[Cashier.IND_BEARS]);
        bears.setTag(Cashier.checkPrefs.getFloat(Cashier.CANDY_NAMES[Cashier.IND_BEARS], (float)Cashier.CANDY_PRICES[Cashier.IND_BEARS]));


        Cashier.dialog.show();
    }

    public void chooseItem(View v)
    {
        Log.d("TKT_itemScreen","chooseItem===================");
        Cashier.updatePayment((Button)v, (TextView)findViewById(R.id.changeText));


    }

    public void cancel(View v)
    {
        Cashier.cancel();
    }

    public void check(View v)
    {
        Cashier.check((EditText) findViewById(R.id.cashReceivedEditText), (TextView) findViewById(R.id.changeText));
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
            case R.id.updatePrices:
            {
                //employeeName = Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null);
                Intent intent = new Intent(this, UpdatePrices.class);
                startActivity(intent);
            }
        }
        return false;
    }

    @Override
    protected void onResume() {
        //// TODO: 9/14/2017 check for updates 
        super.onResume();
    }
}
