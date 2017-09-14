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

    public void drinks(View v)
    {
        //Cashier.type = getString(R.string.drink);
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.drinks_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button ice5 = (Button)Cashier.dialog.findViewById(R.id.ice5button);
        ice5.setTag(Cashier.DRINK_PRICES[Cashier.IND_ICE5]);
        Button ice7 = (Button)Cashier.dialog.findViewById(R.id.ice7button);
        ice7.setTag(Cashier.DRINK_PRICES[Cashier.IND_ICE7]);
        Button slurpee5 = (Button)Cashier.dialog.findViewById(R.id.slurpee5button);
        slurpee5.setTag(Cashier.DRINK_PRICES[Cashier.IND_SLURPEE5]);
        Button slurpee7 = (Button)Cashier.dialog.findViewById(R.id.slurpee7button);
        slurpee7.setTag(Cashier.DRINK_PRICES[Cashier.IND_SLURPEE7]);
        Button lemonade5 = (Button)Cashier.dialog.findViewById(R.id.lemonade5button);
        lemonade5.setTag(Cashier.DRINK_PRICES[Cashier.IND_LEMONADE5]);
        Button lemonade7 = (Button)Cashier.dialog.findViewById(R.id.lemonade7button);
        lemonade7.setTag(Cashier.DRINK_PRICES[Cashier.IND_LEMONADE7]);
        Button orange5 = (Button)Cashier.dialog.findViewById(R.id.orange5button);
        orange5.setTag(Cashier.DRINK_PRICES[Cashier.IND_ORANGE5]);
        Button orange7 = (Button)Cashier.dialog.findViewById(R.id.orange7button);
        orange7.setTag(Cashier.DRINK_PRICES[Cashier.IND_ORANGE7]);
        Button hotChoc = (Button)Cashier.dialog.findViewById(R.id.hotChocoButton);
        hotChoc.setTag(Cashier.DRINK_PRICES[Cashier.IND_HOT_CHOC]);
        Button choc = (Button)Cashier.dialog.findViewById(R.id.chocoButton);
        choc.setTag(Cashier.DRINK_PRICES[Cashier.IND_CHOC]);
        Button cappuccino = (Button)Cashier.dialog.findViewById(R.id.cappuccinoButton);
        cappuccino.setTag(Cashier.DRINK_PRICES[Cashier.IND_CAPPUCCINO]);
        Button can5 = (Button)Cashier.dialog.findViewById(R.id.can5button);
        can5.setTag(Cashier.DRINK_PRICES[Cashier.IND_CAN5]);
        Button can6 = (Button)Cashier.dialog.findViewById(R.id.can6button);
        can6.setTag(Cashier.DRINK_PRICES[Cashier.IND_CAN6]);
        Button water = (Button)Cashier.dialog.findViewById(R.id.waterButton);
        water.setTag(Cashier.DRINK_PRICES[Cashier.IND_WATER]);


        Cashier.dialog.show();
    }

    public void sandwiches(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.sandwich_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button spicyEggplant = (Button)Cashier.dialog.findViewById(R.id.spicyEggplantButton);
        spicyEggplant.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_SPICY_EGGPLANT]);
        Button shakshuka = (Button)Cashier.dialog.findViewById(R.id.shakshukaButton);
        shakshuka.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_SHAKSHUKA]);
        Button sabiah = (Button)Cashier.dialog.findViewById(R.id.sabihButton);
        sabiah.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_SABIH]);
        Button greenOmlette = (Button)Cashier.dialog.findViewById(R.id.greenOmletteButton);
        greenOmlette.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_GREEN_OMLETTE]);
        Button tunaSalad = (Button)Cashier.dialog.findViewById(R.id.tunaSaladButton);
        tunaSalad.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_TUNA_SALAD]);
        Button pesto = (Button)Cashier.dialog.findViewById(R.id.pestoButton);
        pesto.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_PESTO]);
        Button eggplantCheese = (Button)Cashier.dialog.findViewById(R.id.cheeseEggplantBuutton);
        eggplantCheese.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_EGGPLANT_CHEESE]);
        Button tuna = (Button)Cashier.dialog.findViewById(R.id.tunaButton);
        tuna.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_TUNA]);
        Button creamCheese = (Button)Cashier.dialog.findViewById(R.id.creamCheeseButton);
        creamCheese.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_CREAM_CHEESE]);
        Button bulgarian = (Button)Cashier.dialog.findViewById(R.id.bulgarianButton);
        bulgarian.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_BULGARIAN]);
        Button eggSalad = (Button)Cashier.dialog.findViewById(R.id.eggSaladbutton);
        eggSalad.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_EGG_SALAD]);
        Button avocado = (Button)Cashier.dialog.findViewById(R.id.avocadobutton);
        avocado.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_AVOCADO]);
        Button omlette = (Button)Cashier.dialog.findViewById(R.id.omletteButton);
        omlette.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_OMLETTE]);
        Button yellowCheese = (Button)Cashier.dialog.findViewById(R.id.yellowCheeseButton);
        yellowCheese.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_YELLOW_CHEESE]);
        Button tivol = (Button)Cashier.dialog.findViewById(R.id.tivolButton);
        tivol.setTag(Cashier.SANDWICH_PRICES[Cashier.IND_TIVOL]);

        Cashier.dialog.show();

    }

    public void pastries(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.pastry_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button chocCroi = (Button)Cashier.dialog.findViewById(R.id.chocCroissantButton);
        chocCroi.setTag(Cashier.PASTRY_PRICES[Cashier.IND_CHOCO_CROI]);
        Button cheeseCroi = (Button)Cashier.dialog.findViewById(R.id.cheeseCroissantButton);
        cheeseCroi.setTag(Cashier.PASTRY_PRICES[Cashier.IND_CHEESE_CROI]);
        Button butterCroi = (Button)Cashier.dialog.findViewById(R.id.butterCroissantButton);
        butterCroi.setTag(Cashier.PASTRY_PRICES[Cashier.IND_BUTTER_CROI]);
        Button cinnamonCroi = (Button)Cashier.dialog.findViewById(R.id.cinnamonCroissantButton);
        cinnamonCroi.setTag(Cashier.PASTRY_PRICES[Cashier.IND_CINNAMON_CROI]);
        Button donut = (Button)Cashier.dialog.findViewById(R.id.donutButton);
        donut.setTag(Cashier.PASTRY_PRICES[Cashier.IND_DONUT]);
        Button cheeseBurek = (Button)Cashier.dialog.findViewById(R.id.cheeseBurekButton);
        cheeseBurek.setTag(Cashier.PASTRY_PRICES[Cashier.IND_CHEESE_BUREK]);
        Button potatoBurek = (Button)Cashier.dialog.findViewById(R.id.poratoBurektBuutton);
        potatoBurek.setTag(Cashier.PASTRY_PRICES[Cashier.IND_POTATO_BUREK]);
        Button eggBurek = (Button)Cashier.dialog.findViewById(R.id.eggBurektButton);
        eggBurek.setTag(Cashier.PASTRY_PRICES[Cashier.IND_EGG_BUREK]);
        Button melawac = (Button)Cashier.dialog.findViewById(R.id.melawacbutton);
        melawac.setTag(Cashier.PASTRY_PRICES[Cashier.IND_MELAWAC]);
        Button addition = (Button)Cashier.dialog.findViewById(R.id.additionButton);
        addition.setTag(Cashier.PASTRY_PRICES[Cashier.IND_MELAWAC_ADDITION]);
        Button pizza = (Button)Cashier.dialog.findViewById(R.id.pizzaButton);
        pizza.setTag(Cashier.PASTRY_PRICES[Cashier.IND_PIZZA]);
        Button pizzaPlus = (Button)Cashier.dialog.findViewById(R.id.pizzaPlusButton);
        pizzaPlus.setTag(Cashier.PASTRY_PRICES[Cashier.IND_PIZZA_PLUS]);
        Button bulgarianPizza = (Button)Cashier.dialog.findViewById(R.id.bulgarianPizzaButton);
        bulgarianPizza.setTag(Cashier.PASTRY_PRICES[Cashier.IND_PIZZA_BULGARIAN]);


        Cashier.dialog.show();
    }

    public void panini(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.panini_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button fingerPanini = (Button)Cashier.dialog.findViewById(R.id.fingerPaniniButton);
        fingerPanini.setTag(Cashier.PANINI_PRICES[Cashier.IND_PANINI_FINGER]);
        
        Button baglePanini = (Button)Cashier.dialog.findViewById(R.id.baglePaniniButton);
        baglePanini.setTag(Cashier.PANINI_PRICES[Cashier.IND_PANINI_BAGLE]);
        Button addition1 = (Button)Cashier.dialog.findViewById(R.id.oneButton);
        addition1.setTag(Cashier.PANINI_PRICES[Cashier.IND_PANINI_ADDITION1]);
        Button addition3 = (Button)Cashier.dialog.findViewById(R.id.threeButton);
        addition3.setTag(Cashier.PANINI_PRICES[Cashier.IND_PANINI_ADDITION3]);

        Cashier.dialog.show();
    }

    public void salads(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.salad_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button salad = (Button)Cashier.dialog.findViewById(R.id.saladButton);
        salad.setTag(Cashier.SALAD_PRICES[Cashier.IND_SALAD]);
        Button bread = (Button)Cashier.dialog.findViewById(R.id.breadButton);
        bread.setTag(Cashier.SALAD_PRICES[Cashier.IND_SALAD_BREAD]);
        Button saladAddition = (Button)Cashier.dialog.findViewById(R.id.saladAdditionBuutton);
        saladAddition.setTag(Cashier.SALAD_PRICES[Cashier.IND_SALAD_ADDITION]);

        Cashier.dialog.show();
    }

    public void hots(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.hots_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button pasta = (Button)Cashier.dialog.findViewById(R.id.pastaButton);
        pasta.setTag(Cashier.HOTS_PRICES[Cashier.IND_PASTA]);
        Button kuskus = (Button)Cashier.dialog.findViewById(R.id.kuskusButton);
        kuskus.setTag(Cashier.HOTS_PRICES[Cashier.IND_KUSKUS]);
        Button yamSoup = (Button)Cashier.dialog.findViewById(R.id.yamBuutton);
        yamSoup.setTag(Cashier.HOTS_PRICES[Cashier.IND_YAM_SOUP]);
        Button lentilSoup = (Button)Cashier.dialog.findViewById(R.id.lentilButton);
        lentilSoup.setTag(Cashier.HOTS_PRICES[Cashier.IND_LENTIL_SOUP]);
        Button veggieSoup = (Button)Cashier.dialog.findViewById(R.id.veggiButton);
        veggieSoup.setTag(Cashier.HOTS_PRICES[Cashier.IND_VEGGIE_SOUP]);
        Button cornMilanesa = (Button)Cashier.dialog.findViewById(R.id.milanesaButton);
        cornMilanesa.setTag(Cashier.HOTS_PRICES[Cashier.IND_CORN_MILANESA]);

        Cashier.dialog.show();
    }

    public void fruitMeusli(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.meusli_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button watermelon = (Button)Cashier.dialog.findViewById(R.id.watermelonButton);
        watermelon.setTag(Cashier.FRUIT_PRICES[Cashier.IND_WATERMELON]);
        Button meusli = (Button)Cashier.dialog.findViewById(R.id.meusliButton);
        meusli.setTag(Cashier.FRUIT_PRICES[Cashier.IND_FRUIT_MEUSLI]);
        Button mullerFroop = (Button)Cashier.dialog.findViewById(R.id.froopBuutton);
        mullerFroop.setTag(Cashier.FRUIT_PRICES[Cashier.IND_FROOP_MULLER ]);
        Button clickMuller = (Button)Cashier.dialog.findViewById(R.id.mullerClickButton);
        clickMuller.setTag(Cashier.FRUIT_PRICES[Cashier.IND_CLICK_MULLER]);

        Cashier.dialog.show();
    }

    public void candy(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.candy_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button rug = (Button)Cashier.dialog.findViewById(R.id.rugButton);
        rug.setTag(Cashier.CANDY_PRICES[Cashier.IND_RUG]);
        Button selzer = (Button)Cashier.dialog.findViewById(R.id.selzerButton);
        selzer.setTag(Cashier.CANDY_PRICES[Cashier.IND_SELZER]);
        Button marshmellow = (Button)Cashier.dialog.findViewById(R.id.marshmellowButton);
        marshmellow.setTag(Cashier.CANDY_PRICES[Cashier.IND_MARSHMELLOW]);
        Button lolliesDip = (Button)Cashier.dialog.findViewById(R.id.lolliesDipBbutton);
        lolliesDip.setTag(Cashier.CANDY_PRICES[Cashier.IND_LOLLIES_DIP]);
        Button toffeeFizz = (Button)Cashier.dialog.findViewById(R.id.toffeeFizzButton);
        toffeeFizz.setTag(Cashier.CANDY_PRICES[Cashier.IND_TOFFEE_FIZZ]);
        Button piesPolvo = (Button)Cashier.dialog.findViewById(R.id.piesPolvoButton);
        piesPolvo.setTag(Cashier.CANDY_PRICES[Cashier.IND_PIES_POLVO]);
        Button fizzNecklace = (Button)Cashier.dialog.findViewById(R.id.fizzButton);
        fizzNecklace.setTag(Cashier.CANDY_PRICES[Cashier.IND_FIZZ_NECKLACE]);
        Button heart = (Button)Cashier.dialog.findViewById(R.id.heartButton);
        heart.setTag(Cashier.CANDY_PRICES[Cashier.IND_HEART]);
        Button paragua = (Button)Cashier.dialog.findViewById(R.id.paraguaButton);
        paragua.setTag(Cashier.CANDY_PRICES[Cashier.IND_PARAGUA]);
        Button pipe = (Button)Cashier.dialog.findViewById(R.id.pipeButton);
        pipe.setTag(Cashier.CANDY_PRICES[Cashier.IND_PIPE]);
        Button zoom = (Button)Cashier.dialog.findViewById(R.id.zoonButton);
        zoom.setTag(Cashier.CANDY_PRICES[Cashier.IND_ZOOM]);
        Button extreme = (Button)Cashier.dialog.findViewById(R.id.extremeButton);
        extreme.setTag(Cashier.CANDY_PRICES[Cashier.IND_EXTREME]);
        Button tictac = (Button)Cashier.dialog.findViewById(R.id.tictacButton);
        tictac.setTag(Cashier.CANDY_PRICES[Cashier.IND_TICTAC]);
        Button mentos = (Button)Cashier.dialog.findViewById(R.id.mentosButton);
        mentos.setTag(Cashier.CANDY_PRICES[Cashier.IND_MENTOS]);
        Button kinder = (Button)Cashier.dialog.findViewById(R.id.kinderButton);
        kinder.setTag(Cashier.CANDY_PRICES[Cashier.IND_KINDER]);
        Button sneakers = (Button)Cashier.dialog.findViewById(R.id.sneakersButton);
        sneakers.setTag(Cashier.CANDY_PRICES[Cashier.IND_SNEAKERS]);

        Button mars = (Button)Cashier.dialog.findViewById(R.id.marsButton);
        mars.setTag(Cashier.CANDY_PRICES[Cashier.IND_MARS]);
        Button sourSpray = (Button)Cashier.dialog.findViewById(R.id.sourSprayButton);
        sourSpray.setTag(Cashier.CANDY_PRICES[Cashier.IND_SOUR_SPRAY]);
        Button twix = (Button)Cashier.dialog.findViewById(R.id.twixButton);
        twix.setTag(Cashier.CANDY_PRICES[Cashier.IND_TWIX]);
        Button teami = (Button)Cashier.dialog.findViewById(R.id.teamiButton);
        teami.setTag(Cashier.CANDY_PRICES[Cashier.IND_TEAMI]);
        Button nutella = (Button)Cashier.dialog.findViewById(R.id.nutellaButton);
        nutella.setTag(Cashier.CANDY_PRICES[Cashier.IND_NUTELLA]);
        Button bueno = (Button)Cashier.dialog.findViewById(R.id.buenoButton);
        bueno.setTag(Cashier.CANDY_PRICES[Cashier.IND_BUENO]);
        Button timeOut = (Button)Cashier.dialog.findViewById(R.id.timeOutButton);
        timeOut.setTag(Cashier.CANDY_PRICES[Cashier.IND_TIME_OUT]);
        Button clickBar = (Button)Cashier.dialog.findViewById(R.id.clickBarButton);
        clickBar.setTag(Cashier.CANDY_PRICES[Cashier.IND_CLICK_BAR]);
        Button clickBag = (Button)Cashier.dialog.findViewById(R.id.clickBagButton);
        clickBag.setTag(Cashier.CANDY_PRICES[Cashier.IND_CLICK_BAG]);
        Button clickTablet = (Button)Cashier.dialog.findViewById(R.id.clickTabletButton);
        clickTablet.setTag(Cashier.CANDY_PRICES[Cashier.IND_CLICK_TABLET]);
        Button bamba = (Button)Cashier.dialog.findViewById(R.id.bambaButton);
        bamba.setTag(Cashier.CANDY_PRICES[Cashier.IND_BAMBA]);
        Button doritos = (Button)Cashier.dialog.findViewById(R.id.doritosButton);
        doritos.setTag(Cashier.CANDY_PRICES[Cashier.IND_DORITOS]);
        Button bisli = (Button)Cashier.dialog.findViewById(R.id.bislyButton);
        bisli.setTag(Cashier.CANDY_PRICES[Cashier.IND_BISLI]);
        Button aproposito = (Button)Cashier.dialog.findViewById(R.id.apropositoButton);
        aproposito.setTag(Cashier.CANDY_PRICES[Cashier.IND_APROPOSITO]);
        Button bears = (Button)Cashier.dialog.findViewById(R.id.bearsButton);
        bears.setTag(Cashier.CANDY_PRICES[Cashier.IND_BEARS]);


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
