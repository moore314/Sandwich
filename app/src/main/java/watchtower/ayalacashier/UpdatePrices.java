package watchtower.ayalacashier;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class UpdatePrices extends AppCompatActivity {

    Dialog updateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prices);
    }



    public void sandwichesChangePrice (View v)
    {
        categoryDialog(Cashier.SANDWICH_NAMES, Cashier.SANDWICH_PRICES);
    }
    public void drinksChangePrice(View v)
    {
        categoryDialog(Cashier.DRINK_NAMES, Cashier.DRINK_PRICES);
    }
    public void paniniChangePrice(View v)
    {
        categoryDialog(Cashier.PANINI_NAMES, Cashier.PANINI_PRICES);
    }
    public void pastriesChangePrice(View v)
    {
        categoryDialog(Cashier.PASTRY_NAMES, Cashier.PASTRY_PRICES);
    }
    public void hotsChangePrice(View v)
    {
        categoryDialog(Cashier.HOTS_NAMES, Cashier.HOTS_PRICES);
    }
    public void saladsChangePrice(View v)
    {
        categoryDialog(Cashier.SALAD_NAMES, Cashier.SALAD_PRICES);
    }
    public void fruitMeusliChangePrice(View v)
    {
        categoryDialog(Cashier.FRUIT_NAMES, Cashier.FRUIT_PRICES);
    }
    public void candyChangePrice(View v)
    {
        categoryDialog(Cashier.CANDY_NAMES, Cashier.CANDY_PRICES);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_price, menu);
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

        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void cancel(View v)
    {
        Cashier.cancel();
    }


    public void categoryDialog(final String [] names, final double [] prices)
    {

        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_update_price);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        final ListView itemListView = (ListView)Cashier.dialog.findViewById(R.id.updatePriceListView);
        ArrayList<String>sandwichList = new ArrayList(Arrays.asList(names));
        ArrayAdapter<String>adapter = new ArrayAdapter(this, R.layout.custom_list_view,sandwichList );
        itemListView.setAdapter(adapter);
        itemListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TKT_updatePrices","onLongClick");
                String itemName = itemListView.getItemAtPosition(position).toString();
                Log.d("TKT_updatePrices","itemName: "+itemName);
                changeThisItemPrice(itemName, names, prices);
                return true;
            }
        });

        Cashier.dialog.show();
    }

    public void changeThisItemPrice(final String itemName, String [] names, double [] prices)
    {
        updateDialog = new Dialog(this);
        updateDialog.setContentView(R.layout.change_price);
        updateDialog.setCanceledOnTouchOutside(false);
        TextView name = (TextView)updateDialog.findViewById(R.id.itemNameTextView);
        name.setText(itemName);

        int itemIndex = Cashier.itemIndexInArray(itemName, names);
        final TextView currPrice = (TextView)updateDialog.findViewById(R.id.currentPriceTextView);
        currPrice.setText(prices[itemIndex]+"");


        final EditText newPrice = (EditText)updateDialog.findViewById(R.id.newPriceTextView);
        Button ok = (Button)updateDialog.findViewById(R.id.finishUpdt);
        final Button cancel = (Button)updateDialog.findViewById(R.id.cancelUpdt);
        cancel.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
            }
        });
        ok.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newPrice.getText().toString().equals("") || newPrice.getText().toString().equals(currPrice.getText().toString()))
                {
                    Toast.makeText(UpdatePrices.this, R.string.chooseNewPrice, Toast.LENGTH_SHORT).show();
                }
                else {
                    finishUpdate(Double.parseDouble(newPrice.getText().toString()),  itemName);
                    updateDialog.dismiss();
                }
            }
        });

        updateDialog.show();

    }


    public void finishUpdate(double newPrice, String itemName)
    {
        //// TODO: 9/14/2017 dismiss dialog and update shared with new price
        Cashier.finishUpdate(newPrice,itemName);
        Toast.makeText(this, R.string.updateSuccess, Toast.LENGTH_SHORT).show();
    }

}
