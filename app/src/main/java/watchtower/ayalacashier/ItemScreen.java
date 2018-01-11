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


public class ItemScreen extends AppCompatActivity {

    //Currency ILS = Currency.getInstance(Cashier.il);
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
        NIS.setText(Cashier.ILS.getSymbol(Cashier.il));
        Log.d("TKT_itemScreen",Cashier.FILE_NAME);

    }



    public void setPriceView(Button b, String [] nameArr, double [] priceArr, int i)
    {//

        b.setTag(Cashier.checkPrefs.getFloat(nameArr[i], (float)priceArr[i]));
        String price = b.getTag().toString();
        String [] itemText = b.getText().toString().split("\n");
        b.setText(itemText[0]+"\n"+price);
    }

    public void drinks(View v)
    {
        //Cashier.type = getString(R.string.drink);
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.drinks_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button ice5 = (Button)Cashier.dialog.findViewById(R.id.ice5button);
        setPriceView(ice5, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_ICE5);

        Button ice7 = (Button)Cashier.dialog.findViewById(R.id.ice7button);
        setPriceView(ice7, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_ICE7);

        Button slurpee5 = (Button)Cashier.dialog.findViewById(R.id.slurpee5button);
        setPriceView(slurpee5, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_SLURPEE5);

        Button slurpee7 = (Button)Cashier.dialog.findViewById(R.id.slurpee7button);
        setPriceView(slurpee7, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_SLURPEE7);

        Button lemonade5 = (Button)Cashier.dialog.findViewById(R.id.lemonade5button);
        setPriceView(lemonade5, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_LEMONADE5);

        Button lemonade7 = (Button)Cashier.dialog.findViewById(R.id.lemonade7button);
        setPriceView(lemonade7, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_LEMONADE7);

        Button orange5 = (Button)Cashier.dialog.findViewById(R.id.orange5button);
        setPriceView(orange5, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_ORANGE5);

        Button orange7 = (Button)Cashier.dialog.findViewById(R.id.orange7button);
        setPriceView(orange7, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_ORANGE7);

        Button hotChoc = (Button)Cashier.dialog.findViewById(R.id.hotChocoButton);
        setPriceView(hotChoc, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_HOT_CHOC);

        Button choc = (Button)Cashier.dialog.findViewById(R.id.chocoButton);
        setPriceView(choc, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_CHOC);

        Button cappuccino = (Button)Cashier.dialog.findViewById(R.id.cappuccinoButton);
        setPriceView(cappuccino, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_CAPPUCCINO);

        Button can5 = (Button)Cashier.dialog.findViewById(R.id.can5button);
        setPriceView(can5, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_CAN5);

        Button can6 = (Button)Cashier.dialog.findViewById(R.id.can6button);
        setPriceView(can6, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_CAN6);

        Button water = (Button)Cashier.dialog.findViewById(R.id.waterButton);
        setPriceView(water, Cashier.DRINK_NAMES, Cashier.DRINK_PRICES, Cashier.IND_WATER);


        Cashier.dialog.show();
    }

    public void sandwiches(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.sandwich_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button spicyEggplant = (Button)Cashier.dialog.findViewById(R.id.spicyEggplantButton);
        setPriceView(spicyEggplant, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_SPICY_EGGPLANT);

        Button shakshuka = (Button)Cashier.dialog.findViewById(R.id.shakshukaButton);
        setPriceView(shakshuka, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_SHAKSHUKA);

        Button sabiah = (Button)Cashier.dialog.findViewById(R.id.sabihButton);
        setPriceView(sabiah, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_SABIH);

        Button greenOmlette = (Button)Cashier.dialog.findViewById(R.id.greenOmletteButton);
        setPriceView(greenOmlette, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_GREEN_OMLETTE);

        Button tunaSalad = (Button)Cashier.dialog.findViewById(R.id.tunaSaladButton);
        setPriceView(tunaSalad, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_TUNA_SALAD);

        Button pesto = (Button)Cashier.dialog.findViewById(R.id.pestoButton);
        setPriceView(pesto, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_PESTO);

        Button eggplantCheese = (Button)Cashier.dialog.findViewById(R.id.cheeseEggplantBuutton);
        setPriceView(eggplantCheese, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_EGGPLANT_CHEESE);

        Button tuna = (Button)Cashier.dialog.findViewById(R.id.tunaButton);
        setPriceView(tuna, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_TUNA);

        Button creamCheese = (Button)Cashier.dialog.findViewById(R.id.creamCheeseButton);
        setPriceView(creamCheese, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_CREAM_CHEESE);

        Button bulgarian = (Button)Cashier.dialog.findViewById(R.id.bulgarianButton);
        setPriceView(bulgarian, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_BULGARIAN);

        Button eggSalad = (Button)Cashier.dialog.findViewById(R.id.eggSaladbutton);
        setPriceView(eggSalad, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_EGG_SALAD);

        Button avocado = (Button)Cashier.dialog.findViewById(R.id.avocadobutton);
        setPriceView(avocado, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_AVOCADO);

        Button omlette = (Button)Cashier.dialog.findViewById(R.id.omletteButton);
        setPriceView(omlette, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_OMLETTE);

        Button yellowCheese = (Button)Cashier.dialog.findViewById(R.id.yellowCheeseButton);
        setPriceView(yellowCheese, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_YELLOW_CHEESE);

        Button tivol = (Button)Cashier.dialog.findViewById(R.id.tivolButton);
        setPriceView(tivol, Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES, Cashier.IND_TIVOL);


        Cashier.dialog.show();

    }

    public void pastries(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.pastry_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button chocCroi = (Button)Cashier.dialog.findViewById(R.id.chocCroissantButton);
        setPriceView(chocCroi, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_CHOCO_CROI);

        Button cheeseCroi = (Button)Cashier.dialog.findViewById(R.id.cheeseCroissantButton);
        setPriceView(cheeseCroi, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_CHEESE_CROI);

        Button butterCroi = (Button)Cashier.dialog.findViewById(R.id.butterCroissantButton);
        setPriceView(butterCroi, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_BUTTER_CROI);

        Button cinnamonCroi = (Button)Cashier.dialog.findViewById(R.id.cinnamonCroissantButton);
        setPriceView(cinnamonCroi, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_CINNAMON_CROI);

        Button donut = (Button)Cashier.dialog.findViewById(R.id.donutButton);
        setPriceView(donut, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_DONUT);

        Button cheeseBurek = (Button)Cashier.dialog.findViewById(R.id.cheeseBurekButton);
        setPriceView(cheeseBurek, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_CHEESE_BUREK);

        Button potatoBurek = (Button)Cashier.dialog.findViewById(R.id.poratoBurektBuutton);
        setPriceView(potatoBurek, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_POTATO_BUREK);

        Button eggBurek = (Button)Cashier.dialog.findViewById(R.id.eggBurektButton);
        setPriceView(eggBurek, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_EGG_BUREK);

        Button melawac = (Button)Cashier.dialog.findViewById(R.id.melawacbutton);
        setPriceView(melawac, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_MELAWAC);

        Button addition = (Button)Cashier.dialog.findViewById(R.id.additionButton);
        setPriceView(addition, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_MELAWAC_ADDITION);

        Button pizza = (Button)Cashier.dialog.findViewById(R.id.pizzaButton);
        setPriceView(pizza, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_PIZZA);

        Button pizzaPlus = (Button)Cashier.dialog.findViewById(R.id.pizzaPlusButton);
        setPriceView(pizzaPlus, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_PIZZA_PLUS);

        Button bulgarianPizza = (Button)Cashier.dialog.findViewById(R.id.bulgarianPizzaButton);
        setPriceView(bulgarianPizza, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_PIZZA_BULGARIAN);


        Cashier.dialog.show();
    }

    public void panini(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.panini_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button fingerPanini = (Button)Cashier.dialog.findViewById(R.id.fingerPaniniButton);
        setPriceView(fingerPanini, Cashier.PANINI_NAMES, Cashier.PANINI_PRICES, Cashier.IND_PANINI_FINGER);
        
        Button baglePanini = (Button)Cashier.dialog.findViewById(R.id.baglePaniniButton);
        setPriceView(baglePanini, Cashier.PANINI_NAMES, Cashier.PANINI_PRICES, Cashier.IND_PANINI_BAGLE);

        Button addition1 = (Button)Cashier.dialog.findViewById(R.id.oneButton);
        setPriceView(addition1, Cashier.PANINI_NAMES, Cashier.PANINI_PRICES, Cashier.IND_PANINI_ADDITION1);

        Button addition3 = (Button)Cashier.dialog.findViewById(R.id.threeButton);
        setPriceView(addition3, Cashier.PANINI_NAMES, Cashier.PANINI_PRICES, Cashier.IND_PANINI_ADDITION3);

        Cashier.dialog.show();
    }

    public void salads(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.salad_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button salad = (Button)Cashier.dialog.findViewById(R.id.saladButton);
        setPriceView(salad, Cashier.SALAD_NAMES, Cashier.SALAD_PRICES, Cashier.IND_SALAD);

        Button bread = (Button)Cashier.dialog.findViewById(R.id.breadButton);
        setPriceView(bread, Cashier.SALAD_NAMES, Cashier.SALAD_PRICES, Cashier.IND_SALAD_BREAD);

        Button saladAddition = (Button)Cashier.dialog.findViewById(R.id.saladAdditionBuutton);
        setPriceView(saladAddition, Cashier.SALAD_NAMES, Cashier.SALAD_PRICES, Cashier.IND_SALAD_ADDITION);

        Cashier.dialog.show();
    }

    public void hots(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.hots_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button pasta = (Button)Cashier.dialog.findViewById(R.id.pastaButton);
        setPriceView(pasta, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_PASTA);

        Button kuskus = (Button)Cashier.dialog.findViewById(R.id.kuskusButton);
        setPriceView(kuskus, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_KUSKUS);

        Button yamSoup = (Button)Cashier.dialog.findViewById(R.id.smallSoup);
        setPriceView(yamSoup, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_YAM_SOUP);

        Button lentilSoup = (Button)Cashier.dialog.findViewById(R.id.lentilButton);
        setPriceView(lentilSoup, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_LENTIL_SOUP);

        Button veggieSoup = (Button)Cashier.dialog.findViewById(R.id.largeSoup);
        setPriceView(veggieSoup, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_VEGGIE_SOUP);

        Button cornMilanesa = (Button)Cashier.dialog.findViewById(R.id.milanesaButton);
        setPriceView(cornMilanesa, Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES, Cashier.IND_CORN_MILANESA);
        Cashier.dialog.show();
    }

    public void fruitMeusli(View v)
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.meusli_dialog_layout);
        Cashier.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Cashier.dialog.setCanceledOnTouchOutside(false);

        Button watermelon = (Button)Cashier.dialog.findViewById(R.id.watermelonButton);
        setPriceView(watermelon, Cashier.FRUIT_NAMES, Cashier.FRUIT_PRICES, Cashier.IND_WATERMELON);

        Button meusli = (Button)Cashier.dialog.findViewById(R.id.meusliButton);
        setPriceView(meusli, Cashier.FRUIT_NAMES, Cashier.FRUIT_PRICES, Cashier.IND_FRUIT_MEUSLI);

        Button mullerFroop = (Button)Cashier.dialog.findViewById(R.id.froopBuutton);
        setPriceView(mullerFroop, Cashier.FRUIT_NAMES, Cashier.FRUIT_PRICES, Cashier.IND_FROOP_MULLER);

        Button clickMuller = (Button)Cashier.dialog.findViewById(R.id.mullerClickButton);
        setPriceView(clickMuller, Cashier.FRUIT_NAMES, Cashier.FRUIT_PRICES, Cashier.IND_CLICK_MULLER);


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
        setPriceView(rug, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_RUG);

        Button selzer = (Button)Cashier.dialog.findViewById(R.id.selzerButton);
        setPriceView(selzer, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_SELZER);

        Button marshmellow = (Button)Cashier.dialog.findViewById(R.id.marshmellowButton);
        setPriceView(marshmellow, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_MARSHMELLOW);

        Button lolliesDip = (Button)Cashier.dialog.findViewById(R.id.lolliesDipBbutton);
        setPriceView(lolliesDip, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_LOLLIES_DIP);

        Button toffeeFizz = (Button)Cashier.dialog.findViewById(R.id.toffeeFizzButton);
        setPriceView(toffeeFizz, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_TOFFEE_FIZZ);

        Button piesPolvo = (Button)Cashier.dialog.findViewById(R.id.piesPolvoButton);
        setPriceView(piesPolvo, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_PIES_POLVO);

        Button fizzNecklace = (Button)Cashier.dialog.findViewById(R.id.fizzButton);
        setPriceView(fizzNecklace, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_FIZZ_NECKLACE);

        Button heart = (Button)Cashier.dialog.findViewById(R.id.heartButton);
        setPriceView(heart, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_HEART);

        Button paragua = (Button)Cashier.dialog.findViewById(R.id.paraguaButton);
        setPriceView(paragua, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_PARAGUA);

        Button pipe = (Button)Cashier.dialog.findViewById(R.id.pipeButton);
        setPriceView(pipe, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_PIPE);

        Button zoom = (Button)Cashier.dialog.findViewById(R.id.zoonButton);
        setPriceView(zoom, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_ZOOM);

        Button extreme = (Button)Cashier.dialog.findViewById(R.id.extremeButton);
        setPriceView(extreme, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_EXTREME);

        Button tictac = (Button)Cashier.dialog.findViewById(R.id.tictacButton);
        setPriceView(tictac, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_TICTAC);

        Button mentos = (Button)Cashier.dialog.findViewById(R.id.mentosButton);
        setPriceView(mentos, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_MENTOS);

        Button kinder = (Button)Cashier.dialog.findViewById(R.id.kinderButton);
        setPriceView(kinder, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_KINDER);

        Button sneakers = (Button)Cashier.dialog.findViewById(R.id.sneakersButton);
        setPriceView(sneakers, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_SNEAKERS);

        Button mars = (Button)Cashier.dialog.findViewById(R.id.marsButton);
        setPriceView(mars, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_MARS);

        Button sourSpray = (Button)Cashier.dialog.findViewById(R.id.sourSprayButton);
        setPriceView(sourSpray, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_SOUR_SPRAY);

        Button twix = (Button)Cashier.dialog.findViewById(R.id.twixButton);
        setPriceView(twix, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_TWIX);

        Button teami = (Button)Cashier.dialog.findViewById(R.id.teamiButton);
        setPriceView(teami, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_TEAMI);

        Button nutella = (Button)Cashier.dialog.findViewById(R.id.nutellaButton);
        setPriceView(nutella, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_NUTELLA);

        Button bueno = (Button)Cashier.dialog.findViewById(R.id.buenoButton);
        setPriceView(bueno, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_BUENO);

        Button timeOut = (Button)Cashier.dialog.findViewById(R.id.timeOutButton);
        setPriceView(timeOut, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_TIME_OUT);

        Button clickBar = (Button)Cashier.dialog.findViewById(R.id.clickBarButton);
        setPriceView(clickBar, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_CLICK_BAR);

        Button clickBag = (Button)Cashier.dialog.findViewById(R.id.clickBagButton);
        setPriceView(clickBag, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_CLICK_BAG);

        Button clickTablet = (Button)Cashier.dialog.findViewById(R.id.clickTabletButton);
        setPriceView(clickTablet, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_CLICK_TABLET);

        Button bamba = (Button)Cashier.dialog.findViewById(R.id.bambaButton);
        setPriceView(bamba, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_BAMBA);

        Button doritos = (Button)Cashier.dialog.findViewById(R.id.doritosButton);
        setPriceView(doritos, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_DORITOS);

        Button bisli = (Button)Cashier.dialog.findViewById(R.id.bislyButton);
        setPriceView(bisli, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_BISLI);

        Button aproposito = (Button)Cashier.dialog.findViewById(R.id.apropositoButton);
        setPriceView(aproposito, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_APROPOSITO);

        Button bears = (Button)Cashier.dialog.findViewById(R.id.bearsButton);
        setPriceView(bears, Cashier.CANDY_NAMES, Cashier.CANDY_PRICES, Cashier.IND_BEARS);


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
        Cashier.check((EditText) findViewById(R.id.cashReceivedEditText), (TextView) findViewById(R.id.changeText), this);
    }

    @Override
    public void onBackPressed()
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.are_you_sure);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        TextView q = (TextView)Cashier.dialog.findViewById(R.id.text);
        q.setText(R.string.exit);
        Button yes = (Button)Cashier.dialog.findViewById(R.id.hellYeah);
        Button no = (Button)Cashier.dialog.findViewById(R.id.heavensNo);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
                ItemScreen.super.onBackPressed();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
            }
        });

        // Cashier.dialog.dismiss();

        Cashier.dialog.show();


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
            /*
            case R.id.endShiftMenu:
            {
                Log.d("TKT_itemScreen","sendReportToA===================");
                try {
                    Cashier.sendReportToA(this);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("TKT_itemScreen","exception happened");
                }
                return true;
            }
            */
            case R.id.updatePrices:
            {
                //employeeName = Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null);
                Intent intent = new Intent(this, UpdatePrices.class);
                startActivity(intent);
                return true;
            }
            case R.id.hours:
            {
                Intent intent = new Intent(this, Hours.class);
                startActivity(intent);
                return true;
            }
        }
        return false;
    }


    public void general(View v)
    {
        Cashier.general(this);
    }

}
