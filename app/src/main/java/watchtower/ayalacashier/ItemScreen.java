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

    public void chooseItem(View v)
    {
        Log.d("TKT_itemScreen","chooseItem");
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
                Log.d("TKT_itemScreen","reportMenu");
                Intent intent  = new Intent(this, Report.class);
                startActivity(intent);
                return true;
            }
            case R.id.endShiftMenu:
            {
                Log.d("TKT_itemScreen","endShift");
                return true;
            }
        }
        return false;
    }
}
